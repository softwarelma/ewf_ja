package com.softwarelma.ewf.client.page;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class EwfPageHome3 {

    public EwfPageHome3() throws EpeAppException {
    }

    public Component getContent() throws EpeAppException {
        VerticalLayout verticalLayout = new VerticalLayout();
        Label label = new Label("hw");
        verticalLayout.addComponent(label);
        return verticalLayout;
    }

}
