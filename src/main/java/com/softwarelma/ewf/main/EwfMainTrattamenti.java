package com.softwarelma.ewf.main;

import javax.servlet.annotation.WebServlet;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.common.EwfCommonConstants;
import com.softwarelma.ewf.server.EwfServer;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
public class EwfMainTrattamenti extends UI {

    private static final long serialVersionUID = 1L;
    private static final String pageName = EwfCommonConstants.PAGE_NAME_TRATTAMENTI;
    private static final String pageUrl = "/" + pageName + "/*";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            // FIXME
            System.out.println("--- session id (" + pageName + "): " + this.getSession().getSession().getId());

            EwfServer server = EwfServer.getInstance();
            server.loadPage(this, pageName);
        } catch (EpeAppException e) {
        }
    }

    @WebServlet(urlPatterns = pageUrl, name = "EwfMainTrattamentiServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = EwfMainTrattamenti.class, productionMode = false)
    public static class EwfMainTrattamentiServlet extends VaadinServlet {
        private static final long serialVersionUID = 1L;
    }

}
