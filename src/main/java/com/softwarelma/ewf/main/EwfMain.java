package com.softwarelma.ewf.main;

import javax.servlet.annotation.WebServlet;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppRuntimeException;
import com.softwarelma.ewf.common.EwfCommonConstants;
import com.softwarelma.ewf.server.EwfServer;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Push
@Theme("mytheme")
public class EwfMain extends UI {

    private static final long serialVersionUID = 1L;
    private UI ui;
    private String idSession;

    public EwfMain() {
        System.out.println("----- main -> home");// FIXME remove
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        setContent(mainLayout);

        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label loadingText = new Label("Loading UI, please wait...");
        loadingText.setSizeUndefined();
        mainLayout.addComponent(loadingText);

        try {
            EwfServer server = EwfServer.getInstance();
            this.idSession = server.getIdSession();
        } catch (EpeAppException e) {
        }

        this.ui = this;
        new InitializerThread().start();
    }

    class InitializerThread extends Thread {
        @Override
        public void run() {
            try {
                // FIXME
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            try {
                EwfServer server = EwfServer.getInstance();
                System.out.println("id session for loading: " + idSession);
                server.loadPage(ui, idSession, EwfCommonConstants.PAGE_HOME);
            } catch (EpeAppException e) {
                throw new EpeAppRuntimeException(e.getMessage(), e);
            }
        }
    }

    @WebServlet(urlPatterns = "/*", name = "EwfMainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = EwfMain.class, productionMode = false)
    public static class EwfMainServlet extends VaadinServlet {
        private static final long serialVersionUID = 1L;
    }

    public String getIdSession() {
        return idSession;
    }

}
