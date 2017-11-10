package com.softwarelma.ewf.client.comp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.backend.EwfBackendOperation;
import com.softwarelma.ewf.backend.dao.EwfDaoUtils;
import com.softwarelma.ewf.backend.entity.EwfEntityAbstract;
import com.softwarelma.ewf.client.EwfClientWeb;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.components.grid.HeaderRow;

public class EwfCompTable<T extends EwfEntityAbstract> extends EwfCompAbstract<T> {

    private static final long serialVersionUID = 1L;
    private Grid<T> grid;
    private List<String> listAttributeForDescription;
    private List<String> listAttributeForSession;
    private List<EwfCompSecondaryInsertBean> listSecondaryInsertBean;
    private List<EwfCompSecondaryDeleteBean> listSecondaryDeleteBeanUndeletable;
    private List<EwfCompSecondaryDeleteBean> listSecondaryDeleteBeanDeletable;
    private Map<String, TextField> mapAttributeAndTextFieldFilter;
    private EwfEntityAbstract entitySelected;
    private EwfEntityAbstract entitySelectedForFilterByRow;
    private boolean panel = true;

    private boolean clear = true;
    private boolean create = true;
    private boolean update = true;
    private boolean delete = true;

    private boolean marginTop = true;
    private boolean marginRight = true;
    private boolean marginBottom = true;
    private boolean marginLeft = true;

    private String idPanel;
    private String idLayout;
    private String idTable;

    public EwfCompTable(EwfClientWeb web) throws EpeAppException {
        super(web);
    }

    @Override
    public Component getContent(T entity) throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    @Override
    public Component getContent(List<T> listEntity) throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    @Override
    public Component getContent(List<T> listEntity, List<String> listAttributeForDescription,
            List<String> listAttributeForSession) throws EpeAppException {
        EpeAppUtils.checkEmptyList("listAttributeForDescription", listAttributeForDescription);
        EpeAppUtils.checkEmptyList("listAttributeForSession", listAttributeForSession);
        this.listAttributeForDescription = listAttributeForDescription;
        this.listAttributeForSession = listAttributeForSession;
        this.grid = new Grid<>();
        this.mapAttributeAndTextFieldFilter = new HashMap<>();

        if (!EpeAppUtils.isEmpty(this.idTable)) {
            this.grid.setId(this.idTable);
        }

        this.grid.setWidth((150 * this.listAttributeForDescription.size()) + "px");
        this.grid.setHeightByRows(5);
        ListDataProvider<T> dataProvider = new ListDataProvider<>(listEntity);

        for (String attribute : this.listAttributeForDescription) {
            this.grid.addColumn(entity -> {
                try {
                    return entity.retrieveDescription(attribute);
                } catch (EpeAppException e1) {
                    return null;
                }
            }).setId(attribute).setWidth(150);// .setCaption("Name");

            TextField textFieldFilter = new TextField();
            this.mapAttributeAndTextFieldFilter.put(attribute, textFieldFilter);
            textFieldFilter.setPlaceholder(this.retrievePlaceholder(attribute));
            textFieldFilter.setWidth("100px");
            textFieldFilter.setHeight("25px");
            textFieldFilter.addValueChangeListener(event -> {
                dataProvider.setFilter(entity -> {
                    try {
                        this.entitySelectedForFilterByRow = entity;
                        return entity.retrieveDescription(attribute);
                    } catch (EpeAppException e1) {
                        return null;
                    }
                }, entityDescription -> {
                    if (entityDescription == null) {
                        return false;
                    }
                    String filterLower = event.getValue().toLowerCase();

                    if (!entityDescription.toLowerCase().contains(filterLower)) {
                        return false;
                    }

                    try {
                        return this.matchFilters(attribute);
                    } catch (EpeAppException e) {
                        return false;
                    }
                });
            });

            // HeaderRow filterRow = grid.appendHeaderRow();
            HeaderRow filterRow = this.grid.getHeaderRow(0);
            filterRow.getCell(attribute).setComponent(textFieldFilter);
        }

        this.addListenerForSession();
        this.grid.setDataProvider(dataProvider);
        Component[] arrayButton = this.getArrayButton();

        if (arrayButton.length == 0) {
            if (this.panel) {
                return this.buildGenericPanel(true, this.marginTop, this.marginRight, this.marginBottom,
                        this.marginLeft, this.grid.getWidth(), Unit.PIXELS, null, this.idPanel, this.idLayout,
                        this.grid);
            } else {
                return this.buildGenericLayout(true, this.marginTop, this.marginRight, this.marginBottom,
                        this.marginLeft, this.grid.getWidth(), Unit.PIXELS, null, this.idLayout, this.grid);
            }
        } else {
            String idPanelButtons = EpeAppUtils.isEmpty(this.idLayout) ? null : this.idLayout + ".panelButtons";
            Component panelButtons = this.buildGenericLayout(false, false, false, this.marginBottom, false,
                    this.grid.getWidth(), Unit.PIXELS, null, idPanelButtons, arrayButton);

            if (this.panel) {
                return this.buildGenericPanel(true, this.marginTop, this.marginRight, false, this.marginLeft,
                        this.grid.getWidth(), Unit.PIXELS, null, this.idPanel, this.idLayout, this.grid, panelButtons);
            } else {
                return this.buildGenericLayout(true, this.marginTop, this.marginRight, false, this.marginLeft,
                        this.grid.getWidth(), Unit.PIXELS, null, this.idLayout, this.grid, panelButtons);
            }
        }
    }

    private boolean matchFilters(String currentAttribute) throws EpeAppException {
        try {
            for (String attribute : this.listAttributeForDescription) {
                if (attribute.equals(currentAttribute)) {
                    continue;
                }

                TextField textFieldFilter = this.mapAttributeAndTextFieldFilter.get(attribute);
                EpeAppUtils.checkNull("textFieldFilter", textFieldFilter);

                String filterLower = textFieldFilter.getValue().toLowerCase();
                String entityDescription = this.entitySelectedForFilterByRow.retrieveDescription(attribute);
                entityDescription = entityDescription == null ? "" : entityDescription;

                if (!entityDescription.toLowerCase().contains(filterLower)) {
                    return false;
                }
            }
        } catch (EpeAppException e1) {
            return false;
        }

        return true;
    }

    private Component[] getArrayButton() {
        List<Component> listComp = new ArrayList<>();

        if (this.clear) {
            Button button = new Button("x");
            this.setButtonSize(button);
            button.addClickListener(e -> {
                if (this.entitySelected == null) {
                    return;
                }

                try {
                    for (String attribute : this.listAttributeForDescription) {
                        TextField textFieldFilter = this.mapAttributeAndTextFieldFilter.get(attribute);
                        EpeAppUtils.checkNull("textFieldFilter", textFieldFilter);
                        textFieldFilter.setValue("");
                        textFieldFilter.removeStyleName("backColorGrey");
                    }

                    for (String attributeForSession : this.listAttributeForSession) {
                        this.getServer().removeSessionAttribute(
                                this.entitySelected.retrieveEntityClassName(attributeForSession));
                    }
                } catch (EpeAppException e1) {
                    // nothing
                }

                this.grid.deselectAll();
                this.entitySelected = null;
            });
            listComp.add(button);
        }

        if (this.create) {
            Button button = new Button("+");
            this.setButtonSize(button);
            button.addClickListener(e -> {
                if (this.entitySelected == null) {
                    return;
                }

                try {
                    EwfEntityAbstract entityNew = this.entitySelected.retrieveClone();
                    entityNew.setId(null);
                    this.introduceValuesFromFilters(entityNew);
                    String daoClassName = EwfDaoUtils.retrieveDaoClassName(entityNew.getClass());
                    this.getServer().create(daoClassName, entityNew);
                    this.createSecondaryEntities(entityNew);
                } catch (EpeAppException e1) {
                    // nothing
                }
            });
            listComp.add(button);
        }

        if (this.update) {
            Button button = new Button("v");
            this.setButtonSize(button);
            button.addClickListener(e -> {
                if (this.entitySelected == null) {
                    return;
                }

                try {
                    this.introduceValuesFromFilters(this.entitySelected);
                    String daoClassName = EwfDaoUtils.retrieveDaoClassName(this.entitySelected.getClass());
                    this.getServer().update(daoClassName, this.entitySelected);
                } catch (EpeAppException e1) {
                    // nothing
                }
            });
            listComp.add(button);
        }

        if (this.delete) {
            Button button = new Button("-");
            this.setButtonSize(button);
            button.addClickListener(e -> {
                try {
                    if (!this.validForUndeletable()) {
                        return;
                    }

                    if (!this.validForDeletableAndDeleteChildren()) {
                        return;
                    }

                    String daoClassName = EwfDaoUtils.retrieveDaoClassName(this.entitySelected.getClass());
                    this.getServer().delete(daoClassName, this.entitySelected);
                } catch (EpeAppException e1) {
                    // nothing
                }
            });
            listComp.add(button);
        }

        return listComp.toArray(new Component[listComp.size()]);
    }

    private boolean validForDeletableAndDeleteChildren() throws EpeAppException {
        if (this.listSecondaryDeleteBeanDeletable == null) {
            return true;
        }

        EpeAppUtils.checkNull("entitySelected", this.entitySelected);
        List<EwfBackendOperation> listOperation = new ArrayList<>();
        EwfBackendOperation operation;

        for (EwfCompSecondaryDeleteBean secondaryDeleteBean : this.listSecondaryDeleteBeanDeletable) {
            // deleting children, under conditions
            String daoClassNameChildDeletable = secondaryDeleteBean.getDaoClassNameChildDeletable();
            String attributeNameThisDeletable = secondaryDeleteBean.getAttributeNameThisDeletable();
            String attributeNameConditionDeletable = secondaryDeleteBean.getAttributeNameConditionDeletable();
            Long idConditionDeletable = secondaryDeleteBean.getIdConditionDeletable();
            EwfEntityAbstract entityChildConditionDeletable;
            List<EwfEntityAbstract> listEntityChildDeletable = this.getServer().readList(daoClassNameChildDeletable,
                    attributeNameThisDeletable, this.entitySelected.getId());

            for (EwfEntityAbstract entityChildDeletable : listEntityChildDeletable) {
                // condition
                entityChildConditionDeletable = entityChildDeletable.retrieveEntity(attributeNameConditionDeletable);

                if (entityChildConditionDeletable != null
                        && !entityChildConditionDeletable.getId().equals(idConditionDeletable)) {
                    String[] entityNameChild = daoClassNameChildDeletable.split("\\.EwfDao");
                    this.showAlert(false, "Invalid deletion", "The are children elements of type " + entityNameChild[1],
                            Type.ERROR_MESSAGE);
                    return false;
                }

                operation = new EwfBackendOperation();
                operation.setType(EwfBackendOperation.Type.DELETE);
                operation.setDaoClassName(daoClassNameChildDeletable);
                operation.setEntity(entityChildDeletable);
                listOperation.add(operation);
            }
        }

        this.getServer().doOperations(listOperation);
        return true;
    }

    private boolean validForUndeletable() throws EpeAppException {
        if (this.listSecondaryDeleteBeanUndeletable == null) {
            return true;
        }

        EpeAppUtils.checkNull("entitySelected", this.entitySelected);

        for (EwfCompSecondaryDeleteBean secondaryDeleteBean : this.listSecondaryDeleteBeanUndeletable) {
            String daoClassNameChildUndeletable = secondaryDeleteBean.getDaoClassNameChildUndeletable();
            String attributeNameThisUndeletable = secondaryDeleteBean.getAttributeNameThisUndeletable();
            int sizeUndeletable = this.getServer().readListSize(daoClassNameChildUndeletable,
                    attributeNameThisUndeletable, this.entitySelected.getId());

            if (sizeUndeletable > 0) {
                String[] entityNameChild = daoClassNameChildUndeletable.split("\\.EwfDao");
                this.showAlert(false, "Invalid deletion", "The are children elements of type " + entityNameChild[1],
                        Type.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    private void introduceValuesFromFilters(EwfEntityAbstract entityNew) throws EpeAppException {
        EpeAppUtils.checkNull("entityNew", entityNew);

        for (String attribute : this.listAttributeForDescription) {
            EpeAppUtils.checkNull("attribute", attribute);
            TextField textFieldFilter = this.mapAttributeAndTextFieldFilter.get(attribute);
            EpeAppUtils.checkNull("textFieldFilter", textFieldFilter);
            entityNew.introduceValueString(attribute, textFieldFilter.getValue());
        }
    }

    /**
     * Secondary entities are those that point to entityNew.
     * 
     * @param entityNew
     *            the visible entity being created
     */
    private void createSecondaryEntities(EwfEntityAbstract entityNew) throws EpeAppException {
        if (this.listSecondaryInsertBean == null) {
            return;
        }

        for (EwfCompSecondaryInsertBean secondaryInsertBean : this.listSecondaryInsertBean) {
            this.createSecondaryEntity(entityNew, secondaryInsertBean);
        }
    }

    private void createSecondaryEntity(EwfEntityAbstract entityNew, EwfCompSecondaryInsertBean secondaryInsertBean)
            throws EpeAppException {
        EpeAppUtils.checkNull("entityNew", entityNew);
        EpeAppUtils.checkNull("secondaryInsertBean", secondaryInsertBean);
        EpeAppUtils.checkNull("secondaryInsertBean.clazz", secondaryInsertBean.getClazz());
        EpeAppUtils.checkNull("secondaryInsertBean.attributeForEntityNew",
                secondaryInsertBean.getAttributeForEntityNew());
        EpeAppUtils.checkNull("secondaryInsertBean.mapAttributeAndValue",
                secondaryInsertBean.getMapAttributeAndValue());

        try {
            EwfEntityAbstract entity = (EwfEntityAbstract) Class.forName(secondaryInsertBean.getClazz().getName())
                    .newInstance();
            entity.introduceValue(secondaryInsertBean.getAttributeForEntityNew(), entityNew);

            for (String attribute : secondaryInsertBean.getMapAttributeAndValue().keySet()) {
                EpeAppUtils.checkNull("secondaryInsertBean.mapAttributeAndValue.attribute", attribute);
                entity.introduceValue(attribute, secondaryInsertBean.getMapAttributeAndValue().get(attribute));
            }

            String daoClassName = EwfDaoUtils.retrieveDaoClassName(entity.getClass());
            this.getServer().create(daoClassName, entity);
        } catch (InstantiationException e) {
            new EpeAppException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            new EpeAppException(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            new EpeAppException(e.getMessage(), e);
        }
    }

    private void setButtonSize(Button button) {
        button.setWidth("40px");
        button.setHeight("25px");
    }

    public void refresh(List<T> listEntity) throws EpeAppException {
        EpeAppUtils.checkNull("listEntity", listEntity);
        @SuppressWarnings("unchecked")
        Collection<T> items = ((ListDataProvider<T>) this.grid.getDataProvider()).getItems();
        items.clear();
        items.addAll(listEntity);
        this.grid.getDataProvider().refreshAll();
    }

    private void addListenerForSession() {
        this.grid.addSelectionListener(e -> {
            if (this.grid.getSelectedItems().size() != 1) {
                return;
            }

            try {
                EwfEntityAbstract entity = this.grid.getSelectedItems().iterator().next();
                EpeAppUtils.checkNull("entity", entity);
                this.entitySelected = entity;

                for (String attribute : this.listAttributeForDescription) {
                    TextField textFieldFilter = this.mapAttributeAndTextFieldFilter.get(attribute);
                    EpeAppUtils.checkNull("textFieldFilter", textFieldFilter);
                    textFieldFilter.setValue(this.entitySelected.retrieveDescription(attribute));
                    textFieldFilter.addStyleName("backColorGrey");
                }

                for (String attributeForSession : this.listAttributeForSession) {
                    EwfEntityAbstract entityForSession = this.entitySelected.retrieveEntity(attributeForSession);
                    this.getServer().setSessionAttributeOrNull(
                            this.entitySelected.retrieveEntityClassName(attributeForSession), entityForSession);
                }
            } catch (EpeAppException e1) {
                // nothing
            }
        });
    }

    private String retrievePlaceholder(String attribute) throws EpeAppException {
        EpeAppUtils.checkEmpty("attribute", attribute);
        int ind = attribute.indexOf(".");
        attribute = ind == -1 ? attribute : attribute.substring(0, ind);
        attribute = attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
        return attribute;
    }

    public boolean isPanel() {
        return panel;
    }

    public void setPanel(boolean panel) {
        this.panel = panel;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isMarginTop() {
        return marginTop;
    }

    public void setMarginTop(boolean marginTop) {
        this.marginTop = marginTop;
    }

    public boolean isMarginRight() {
        return marginRight;
    }

    public void setMarginRight(boolean marginRight) {
        this.marginRight = marginRight;
    }

    public boolean isMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(boolean marginBottom) {
        this.marginBottom = marginBottom;
    }

    public boolean isMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(boolean marginLeft) {
        this.marginLeft = marginLeft;
    }

    public String getIdPanel() {
        return idPanel;
    }

    public void setIdPanel(String idPanel) {
        this.idPanel = idPanel;
    }

    public String getIdLayout() {
        return idLayout;
    }

    public void setIdLayout(String idLayout) {
        this.idLayout = idLayout;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public List<EwfCompSecondaryInsertBean> getListSecondaryInsertBean() {
        return listSecondaryInsertBean;
    }

    public void setListSecondaryInsertBean(List<EwfCompSecondaryInsertBean> listSecondaryInsertBean) {
        this.listSecondaryInsertBean = listSecondaryInsertBean;
    }

    public List<EwfCompSecondaryDeleteBean> getListSecondaryDeleteBeanUndeletable() {
        return listSecondaryDeleteBeanUndeletable;
    }

    public void setListSecondaryDeleteBeanUndeletable(
            List<EwfCompSecondaryDeleteBean> listSecondaryDeleteBeanUndeletable) {
        this.listSecondaryDeleteBeanUndeletable = listSecondaryDeleteBeanUndeletable;
    }

    public List<EwfCompSecondaryDeleteBean> getListSecondaryDeleteBeanDeletable() {
        return listSecondaryDeleteBeanDeletable;
    }

    public void setListSecondaryDeleteBeanDeletable(List<EwfCompSecondaryDeleteBean> listSecondaryDeleteBeanDeletable) {
        this.listSecondaryDeleteBeanDeletable = listSecondaryDeleteBeanDeletable;
    }

}
