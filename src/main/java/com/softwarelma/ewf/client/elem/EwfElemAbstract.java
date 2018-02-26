package com.softwarelma.ewf.client.elem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.epe.p3.db.EpeDbMetaDataEntity;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.cont.EwfContentAbstract;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.server.Setter;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.ButtonRenderer;

public abstract class EwfElemAbstract extends EwfContentAbstract implements EwfElemInterface {

	private static final long serialVersionUID = 1L;

	protected EwfElemAbstract(EwfClient client, UI ui, EwfElemBean elemBean, EwfContentBean contentBean)
			throws EpeAppException {
		super(client, ui, elemBean, contentBean);
		this.init(client, elemBean);
	}

	private void init(EwfClient client, EwfElemBean elemBean) throws EpeAppException {
		if (elemBean == null)
			return;
		this.initText(elemBean);
		this.initQuery(client, elemBean);
	}

	private void initText(EwfElemBean elemBean) throws EpeAppException {
		if (elemBean.getText() == null)
			return;
		try {
			Method method = this.getComponent().getClass().getMethod("setValue", String.class);
			method.invoke(this.getComponent(), elemBean.getText());
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new EpeAppException("Invalid method setValue", e);
		}
	}

	private void initQuery(EwfClient client, EwfElemBean elemBean) throws EpeAppException {
		if (elemBean.getQuerySelect() == null)
			return;
		List<EpeDbEntity> listEntity = client.retrieveListEntity(elemBean.getQuerySelect(), elemBean.getQueryTable());
		if (this.getComponent() instanceof Grid) {
			@SuppressWarnings("unchecked")
			Grid<EpeDbEntity> grid = (Grid<EpeDbEntity>) this.getComponent();
			this.initGrid(grid, listEntity);
		} else {
			throw new EpeAppException("Unknown class instance: " + this.getComponent().getClass().getName());
		}
	}

	private void initGrid(Grid<EpeDbEntity> grid, List<EpeDbEntity> listEntity) throws EpeAppException {
		grid.setSizeFull();
		grid.setItems(listEntity);
		grid.getEditor().setEnabled(true);
		grid.getEditor().addSaveListener(e -> {
			try {
				System.out.println(
						"Saving " + e.getBean().getMetaData().getTable() + " with id " + e.getBean().get("ID"));
			} catch (EpeAppException e1) {
				e1.printStackTrace();
			}
		});
		grid.setSelectionMode(SelectionMode.MULTI);
		List<Column<EpeDbEntity, String>> listColumn = new ArrayList<>();
		Column<EpeDbEntity, String> column;
		EpeDbMetaDataEntity meta = listEntity.get(0).getMetaData();// TODO if no rows?

		for (int i = 0; i < meta.getCols(); i++) {
			column = this.initGridColumn(grid, meta.getAttribute(i));
			listColumn.add(column);
		}

		column = grid.addColumn(e -> "Delete", new ButtonRenderer<EpeDbEntity>(clickEvent -> {
			listEntity.remove(clickEvent.getItem());
			grid.setItems(listEntity);
		})).setCaption("OPERATIONS");
		listColumn.add(column);

		this.addFilters(grid, listColumn, listEntity);
	}

	private void addFilters(Grid<EpeDbEntity> grid, List<Column<EpeDbEntity, String>> listColumn,
			List<EpeDbEntity> listEntity) throws EpeAppException {
		HeaderRow headerRow = grid.appendHeaderRow();

		for (Column<EpeDbEntity, String> column : listColumn) {
			HeaderCell cell = headerRow.getCell(column);
			Component component;
			if (column.getCaption().equals("IMAGE")) {
				// component = this.getUpload(grid, listBean);//TODO
				component = null;
			} else if (column.getCaption().equals("OPERATIONS")) {
				Button buttonInsert = new Button("Insert");
				component = buttonInsert;
				buttonInsert.addClickListener(new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						try {
							EpeDbEntity beanToInsert = new EpeDbEntity(listEntity.get(0).getMetaData());// TODO metadata
																										// with 0
																										// entities
							listEntity.add(beanToInsert);
							grid.setItems(listEntity);
						} catch (EpeAppException e) {
							Notification.show("Error", e.getMessage(), Type.ERROR_MESSAGE);
						}
					}
				});
			} else {
				TextField textField = new TextField();
				textField.addValueChangeListener(new ValueChangeListener<String>() {
					@Override
					public void valueChange(ValueChangeEvent<String> event) {
						System.out.println("filtering col " + column.getCaption() + " by: " + event.getValue());
					}
				});
				component = textField;
			}
			cell.setComponent(component);
		}
	}

	private Column<EpeDbEntity, String> initGridColumn(Grid<EpeDbEntity> grid, String colName) throws EpeAppException {
		Column<EpeDbEntity, String> column = grid.addColumn(e -> {
			try {
				Object obj = e.getOrNull(colName);
				String val = obj == null ? "" : obj + "";
				System.out.println("Get " + colName + "=" + val);
				return val;
			} catch (EpeAppException e1) {
				e1.printStackTrace();
				return null;
			}
		}).setCaption(colName).setEditorComponent(new TextField(), new Setter<EpeDbEntity, String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(EpeDbEntity bean, String fieldvalue) {
				try {
					bean.setFromString(colName, fieldvalue);
					System.out.println("Set " + colName + "=" + fieldvalue);
				} catch (EpeAppException e1) {
					Notification.show("Error", e1.getMessage(), Type.ERROR_MESSAGE);
				}
			}
		});
		return column;
	}

	@Override
	public boolean isPage() {
		return false;
	}

	@Override
	public boolean isComp() {
		return false;
	}

	@Override
	public boolean isElem() {
		return true;
	}

}
