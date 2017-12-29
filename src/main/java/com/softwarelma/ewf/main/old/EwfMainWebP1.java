package com.softwarelma.ewf.main.old;

import javax.servlet.annotation.WebServlet;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.old.EwfClientWeb;
import com.softwarelma.ewf.server.old.EwfServer;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class EwfMainWebP1 extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		try {
			// VaadinService.getCurrentRequest().getWrappedSession().setAttribute(EwfMainWebP1.class.getName(),
			// this);
			VaadinService.getCurrentRequest().getWrappedSession().setAttribute("ewfUi", this);
			EwfServer server = EwfServer.getInstance();
			new EwfClientWeb(server, "p1");
		} catch (EpeAppException e) {
			// TODO goto login? just try once
		}

	}

	@WebServlet(urlPatterns = { "/p1/*", "/VAADIN/*" }, name = "EwfMainWebP1Servlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = EwfMainWebP1.class, productionMode = false)
	public static class EwfMainWebP1Servlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}
}
