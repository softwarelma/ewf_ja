package com.softwarelma.ewf.client.elem;

import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.epe.p3.db.EpeDbMetaDataEntity;
import com.softwarelma.ewf.client.EwfClient;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.Setter;
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
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.ButtonRenderer;

public class EwfElemAbstractGrid {

    public void initGrid(EwfClient client, Grid<EpeDbEntity> grid, String select, String table) throws EpeAppException {
        grid.setSizeFull();
        // grid.setHeight("500px");
        grid.setHeightByRows(12);
        List<EpeDbEntity> listEntity = new ArrayList<>();
        EpeDbMetaDataEntity metadata = this.load(client, grid, select, table, listEntity);
        grid.getEditor().setEnabled(true);
        grid.getEditor().addSaveListener(event -> {
            try {
                client.update(event.getBean());
            } catch (EpeAppException e1) {
                e1.printStackTrace();
            }
        });
        grid.setSelectionMode(SelectionMode.MULTI);
        List<Column<EpeDbEntity, String>> listColumn = new ArrayList<>();
        Column<EpeDbEntity, String> column;

        for (int i = 0; i < metadata.getCols(); i++) {
            column = this.initGridColumn(grid, metadata.getAttribute(i));
            listColumn.add(column);
        }

        column = grid.addColumn(e -> "Delete", new ButtonRenderer<EpeDbEntity>(clickEvent -> {
            try {
                EpeDbEntity entity = clickEvent.getItem();
                client.delete(entity);
                load(client, grid, select, table);
            } catch (EpeAppException e1) {
                Notification.show("Error", e1.getMessage(), Type.ERROR_MESSAGE);
            }
        })).setCaption("OPERATIONS");
        listColumn.add(column);

        this.addFilters(client, grid, select, table, listColumn, listEntity);
    }

    private EpeDbMetaDataEntity load(EwfClient client, Grid<EpeDbEntity> grid, String select, String table)
            throws EpeAppException {
        return this.load(client, grid, select, table, new ArrayList<>());
    }

    private EpeDbMetaDataEntity load(EwfClient client, Grid<EpeDbEntity> grid, String select, String table,
            List<EpeDbEntity> listEntity) throws EpeAppException {
        EpeDbMetaDataEntity metatdata = client.retrieveListEntity(select, table, listEntity);
        grid.setItems(listEntity);
        return metatdata;
    }

    private void addFilters(EwfClient client, Grid<EpeDbEntity> grid, String select, String table,
            List<Column<EpeDbEntity, String>> listColumn, List<EpeDbEntity> listEntity) throws EpeAppException {
        HeaderRow headerRow = grid.appendHeaderRow();
        List<TextField> listTextField = new ArrayList<>();

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
                            client.insertBlank(table);
                            load(client, grid, select, table);
                        } catch (EpeAppException e) {
                            Notification.show("Error", e.getMessage(), Type.ERROR_MESSAGE);
                        }
                    }
                });
            } else {
                TextField textField = new TextField();
                listTextField.add(textField);
                textField.addValueChangeListener(new ValueChangeListener<String>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void valueChange(ValueChangeEvent<String> event) {
                        doFilter(grid, listColumn, listEntity, listTextField);
                    }
                });
                textField.addFocusListener(new FocusListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void focus(FocusEvent event) {
                        doFilter(grid, listColumn, listEntity, listTextField);
                    }
                });
                component = textField;
            }
            cell.setComponent(component);
        }
    }

    private void doFilter(Grid<EpeDbEntity> grid, List<Column<EpeDbEntity, String>> listColumn,
            List<EpeDbEntity> listEntity, List<TextField> listTextField) {
        try {
            List<EpeDbEntity> listEntityNew = this.retrieveListEntity(listEntity, listColumn, listTextField);
            grid.setItems(listEntityNew);
        } catch (EpeAppException e) {
            Notification.show("Error", e.getMessage(), Type.ERROR_MESSAGE);
        }
    }

    private List<EpeDbEntity> retrieveListEntity(List<EpeDbEntity> listEntityOriginal,
            List<Column<EpeDbEntity, String>> listColumn, List<TextField> listTextField) throws EpeAppException {
        List<EpeDbEntity> listEntityNew = new ArrayList<>();
        for (EpeDbEntity entityOriginal : listEntityOriginal) {
            if (this.isValid(entityOriginal, listColumn, listTextField))
                listEntityNew.add(entityOriginal);
        }
        return listEntityNew;
    }

    private boolean isValid(EpeDbEntity entityOriginal, List<Column<EpeDbEntity, String>> listColumn,
            List<TextField> listTextField) throws EpeAppException {
        for (int i = 0; i < listColumn.size() - 1; i++) {// avoid OPERATIONS
            Column<EpeDbEntity, String> column = listColumn.get(i);
            TextField textField = listTextField.get(i);
            String valueOriginal = entityOriginal.getToString(column.getCaption()).toUpperCase();
            String valueFilter = textField.getValue();
            valueFilter = valueFilter == null ? "" : valueFilter.toUpperCase();
            if (!valueOriginal.contains(valueFilter))
                return false;
        }
        return true;
    }

    private Column<EpeDbEntity, String> initGridColumn(Grid<EpeDbEntity> grid, String colName) throws EpeAppException {
        Column<EpeDbEntity, String> column = grid.addColumn(e -> {
            try {
                Object obj = e.getOrNull(colName);
                String val = obj == null ? "" : obj + "";
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
                    Object obj = bean.getOrNull(colName);
                    String val = obj == null ? "" : obj + "";
                    if (!val.equals(fieldvalue))
                        bean.setFromString(colName, fieldvalue);
                } catch (EpeAppException e1) {
                    Notification.show("Error", e1.getMessage(), Type.ERROR_MESSAGE);
                }
            }
        });
        return column;
    }

}
