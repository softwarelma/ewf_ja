package com.softwarelma.ewf.main;

import javax.servlet.annotation.WebServlet;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.server.EwfServer;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Push
@Theme("mytheme")
public class EwfMain extends UI {

	private static final long serialVersionUID = 1L;
	private UI ui;

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
		this.ui = this;
		new InitializerThread().start();
	}

	class InitializerThread extends Thread {
		@Override
		public void run() {
			// Do initialization which takes some time.
			// Here represented by a 1s sleep
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}

			// Init done, update the UI after doing locking
			access(new Runnable() {
				@Override
				public void run() {
					// Here the UI is locked and can be updated
					try {
						EwfServer server = EwfServer.getInstance();
						server.loadPage(ui, "home");
						// setContent(new Label("aaaaa"));
					} catch (EpeAppException e) {
					}
				}
			});
		}
	}

	@WebServlet(urlPatterns = "/*", name = "EwfMainServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = EwfMain.class, productionMode = false)
	public static class EwfMainServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}

}
