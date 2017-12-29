package com.softwarelma.ewf.main.old;

import javax.servlet.annotation.WebServlet;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.page.old.EwfPageHome3;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
public class EwfMainWeb3 extends UI {

    private static final long serialVersionUID = 1L;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            System.out.println("init url: ");
            EwfPageHome3 h3 = new EwfPageHome3();
            setContent(h3.getContent());
        } catch (EpeAppException e) {
            e.printStackTrace();
        }
    }

    @WebServlet(urlPatterns = "/h3/*", name = "EwfMainWeb3Servlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = EwfMainWeb3.class, productionMode = false)
    public static class EwfMainWeb3Servlet extends VaadinServlet {
        private static final long serialVersionUID = 1L;

        public EwfMainWeb3Servlet() {
            System.out.println("h3");
        }
    }

    @WebServlet(urlPatterns = "/h4/*", name = "EwfMainWeb4Servlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = EwfMainWeb3.class, productionMode = false)
    public static class EwfMainWeb4Servlet extends VaadinServlet {
        private static final long serialVersionUID = 1L;

        public EwfMainWeb4Servlet() {
            System.out.println("h4");
        }
    }

}
