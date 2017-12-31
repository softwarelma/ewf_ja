package com.softwarelma.ewf.main.old;

import javax.servlet.annotation.WebServlet;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.old.EwfClientWeb;
import com.softwarelma.ewf.server.old.EwfServer;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window (or tab) or some part of a html
 * page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be overridden to add component
 * to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class EwfMainWeb2 extends UI {

    private static final long serialVersionUID = 1L;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout mainLayout = new VerticalLayout();
        setContent(mainLayout);

        MenuBar mainMenuBar = new MenuBar();

        mainMenuBar.addItem("P1", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getPage().setLocation("p1/");
            }
        });

        mainMenuBar.addItem("P2", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getPage().setLocation("p2/");
            }
        });

        mainLayout.addComponent(mainMenuBar);

        CssLayout content = new CssLayout();
        // content.setSizeFull();
        // mainLayout.addComponent(content);

        // navigator = new Navigator(this, content);
        // navigator.addView("services", ServiceView.class);
        // navigator.addView("entities", EntityView.class);
        // navigator.navigateTo("services");
        // setNavigator(navigator);
    }

    private void init2() {
        try {
            // VaadinService.getCurrentRequest().getWrappedSession().setAttribute(EwfMainWeb.class.getName(),
            // this);
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("ewfUi", this);
            EwfServer server = EwfServer.getInstance();
            new EwfClientWeb(server, "home2");
        } catch (EpeAppException e) {
            // TODO goto login? just try once
        }

    }

    @WebServlet(urlPatterns = "/home2/*", name = "EwfMainWebServlet2", asyncSupported = true)
    @VaadinServletConfiguration(ui = EwfMainWeb2.class, productionMode = false)
    public static class EwfMainWebServlet2 extends VaadinServlet {
        private static final long serialVersionUID = 1L;
    }
}
