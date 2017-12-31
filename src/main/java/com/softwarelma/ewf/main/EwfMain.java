package com.softwarelma.ewf.main;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
public class EwfMain extends UI {

    private static final long serialVersionUID = 1L;
    private static final String pageUrl = "";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getUI().getPage().setLocation("home/");
    }

    @WebServlet(urlPatterns = pageUrl, name = "EwfMainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = EwfMain.class, productionMode = false)
    public static class EwfMainServlet extends VaadinServlet {
        private static final long serialVersionUID = 1L;
    }

}
