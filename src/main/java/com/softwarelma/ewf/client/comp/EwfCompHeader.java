package com.softwarelma.ewf.client.comp;

import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.EwfEntityAbstract;
import com.softwarelma.ewf.backend.entity.EwfEntityUser;
import com.softwarelma.ewf.client.EwfClientWeb;
import com.softwarelma.ewf.client.page.EwfPageLogin;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TextField;

public class EwfCompHeader<T extends EwfEntityAbstract> extends EwfCompAbstract<T> {

    private static final long serialVersionUID = 1L;

    public EwfCompHeader(EwfClientWeb web) throws EpeAppException {
        super(web);
    }

    @Override
    public Component getContent(T entity) throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    @Override
    public Component getContent(List<T> listEntity) throws EpeAppException {
        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setWidth("100%");
        gridLayout.setColumnExpandRatio(0, 0f);
        gridLayout.setColumnExpandRatio(1, 1f);
        // hl.setSizeFull();
        // hl.setSizeUndefined();
        // layout.setSpacing(false);
        // gridLayout.addStyleName("backColorGrey");

        MenuBar mb = new MenuBar();
        // mb.setWidth("30px");
        MenuBar.Command commandName = new MenuBar.Command() {
            private static final long serialVersionUID = 1L;

            public void menuSelected(MenuItem selectedItem) {
                // nothing
            }
        };
        MenuItem miMenu = mb.addItem("", null);
        EwfEntityUser user = this.getServer().getUser();
        // MenuItem miName =
        miMenu.addItem(user.retrieveDescriptionShort(), commandName);
        MenuBar.Command commandLogout = new MenuBar.Command() {
            private static final long serialVersionUID = 1L;

            public void menuSelected(MenuItem selectedItem) {
                try {
                    getWeb().navigate(EwfPageLogin.class.getName());
                } catch (EpeAppException e) {
                    // nothing
                }
            }
        };
        // MenuItem miName =
        miMenu.addItem("Logout", commandLogout);
        gridLayout.addComponent(mb, 0, 0);

        TextField tfSearch = new TextField();
        tfSearch.setPlaceholder("Search");
        tfSearch.setWidth("100%");
        gridLayout.addComponent(tfSearch, 1, 0);

        // hl.setExpandRatio(mb, 0.1f);
        // hl.setExpandRatio(tfSearch, 0.9f);

        return gridLayout;
    }

    @Override
    public Component getContent(List<T> listEntity, List<String> listAttributeForDescription,
            List<String> listAttributeForSession) throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

}
