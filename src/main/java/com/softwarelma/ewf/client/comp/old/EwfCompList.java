package com.softwarelma.ewf.client.comp.old;

import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;
import com.softwarelma.ewf.client.old.EwfClientWeb;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class EwfCompList<T extends EwfEntityAbstract> extends EwfCompAbstract<T> {

    private static final long serialVersionUID = 1L;
    private final ListSelect<T> listSelect = new ListSelect<>();
    private String searchPlaceholder;
    private String navigationCaption;
    private String navigationClassName;

    public EwfCompList(EwfClientWeb web) throws EpeAppException {
        super(web);
    }

    @Override
    public Component getContent(T entity) throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    @Override
    public Component getContent(List<T> listEntity) throws EpeAppException {
        this.setListEntity(listEntity);
        this.listSelect.setRows(5);
        // this.listSelect.set.setMultiSelect(true);TODO
        this.listSelect.addSelectionListener(e -> {
            if (this.listSelect.getSelectedItems().size() != 1) {
                return;
            }

            try {
                EwfEntityAbstract entity = this.listSelect.getSelectedItems().iterator().next();
                String name = this.retrieveSessionAttName(entity);
                this.getServer().setSessionAttribute(name, entity);
            } catch (EpeAppException e1) {
                // nothing
            }
        });

        Panel panel = new Panel();
        panel.setWidth("150px");
        VerticalLayout verticalLayout = new VerticalLayout();
        panel.setContent(verticalLayout);

        TextField tfSearch = new TextField();
        tfSearch.setWidth("100%");
        tfSearch.setPlaceholder(this.getSearchPlaceholderOrDefault());
        tfSearch.addValueChangeListener(e -> {
            try {
                this.listSelect.setItems(this.retrieveListLabel(tfSearch.getValue()));
            } catch (EpeAppException e1) {
                // nothing
            }
        });
        verticalLayout.addComponent(tfSearch);

        this.listSelect.setItems(this.retrieveListLabel(null));
        this.listSelect.setWidth("100%");
        verticalLayout.addComponent(this.listSelect);

        Button bNav = new Button(this.navigationCaption == null ? "->" : this.navigationCaption);
        bNav.setWidth("100%");
        bNav.addClickListener(e -> {
            try {
                getWeb().navigate(navigationClassName);
            } catch (EpeAppException e1) {
                // nothing
            }
        });
        verticalLayout.addComponent(bNav);

        return panel;
    }

    @Override
    public Component getContent(List<T> listEntity, List<String> listAttributeForDescription,
            List<String> listAttributeForSession) throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    private List<T> retrieveListLabel(String filter) throws EpeAppException {
        List<T> listLabel = new ArrayList<>();
        List<T> listEntity = this.getListEntity();
        T entity;

        for (int i = 0; i < listEntity.size(); i++) {
            entity = listEntity.get(i);

            if (filter == null || entity.retrieveDescriptionShort().toUpperCase().contains(filter.toUpperCase())) {
                listLabel.add(entity);
            }
        }

        return listLabel;
    }

    private String retrieveSessionAttName(EwfEntityAbstract entity) throws EpeAppException {
        EpeAppUtils.checkNull("entity", entity);
        return entity.getClass().getName() + "$List$" + this.getSearchPlaceholderOrDefault();
    }

    private String getSearchPlaceholderOrDefault() {
        return this.searchPlaceholder == null ? "Search" : this.searchPlaceholder;
    }

    public String getSearchPlaceholder() {
        return searchPlaceholder;
    }

    public void setSearchPlaceholder(String searchPlaceholder) {
        this.searchPlaceholder = searchPlaceholder;
    }

    public String getNavigationCaption() {
        return navigationCaption;
    }

    public void setNavigationCaption(String navigationCaption) {
        this.navigationCaption = navigationCaption;
    }

    public String getNavigationClassName() {
        return navigationClassName;
    }

    public void setNavigationClassName(String navigationClassName) {
        this.navigationClassName = navigationClassName;
    }

}
