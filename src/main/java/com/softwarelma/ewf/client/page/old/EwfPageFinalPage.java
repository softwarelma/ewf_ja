package com.softwarelma.ewf.client.page.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.old.EwfClientWeb;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class EwfPageFinalPage extends EwfPageAbstract {

    private static final long serialVersionUID = 1L;

    public EwfPageFinalPage(EwfClientWeb web) throws EpeAppException {
        super(web);
    }

    @Override
    public Component getContent() throws EpeAppException {
        if (!this.getServer().isLoggedUser()) {
            return this.getWeb().getContent(EwfPageLogin.class.getName());
        }

        VerticalLayout verticalLayout = new VerticalLayout();
        Button bLogout = new Button("Logout");
        bLogout.setWidth("200px");

        bLogout.addClickListener(e -> {
            try {
                this.getWeb().navigate(EwfPageLogin.class.getName());
            } catch (EpeAppException e1) {
                // nothing
            }
        });

        verticalLayout.addComponent(bLogout);
        return verticalLayout;
    }

}
