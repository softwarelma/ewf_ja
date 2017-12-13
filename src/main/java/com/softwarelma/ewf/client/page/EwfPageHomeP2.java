package com.softwarelma.ewf.client.page;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClientWeb;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class EwfPageHomeP2 extends EwfPageAbstract {

	private static final long serialVersionUID = 1L;

	public EwfPageHomeP2(EwfClientWeb web) throws EpeAppException {
		super(web);
	}

	@Override
	public Component getContent() throws EpeAppException {
		CssLayout layout = new CssLayout();
		layout.setWidth("100%");
		layout.addStyleName("toggledisplay");
		// content.addComponent(layout);

		// Enable Responsive CSS selectors for the layout
		Responsive.makeResponsive(layout);

		Label enoughspace = new Label("This space is big, mindbogglingly big");
		enoughspace.addStyleName("enoughspace");
		layout.addComponent(enoughspace);

		Label notenoughspace = new Label("Quite small space");
		notenoughspace.addStyleName("notenoughspace");
		layout.addComponent(notenoughspace);
		return layout;
	}

	private Component init() {
		// if (!this.getServer().isLoggedUser()) {
		// return this.getWeb().getContent(EwfPageLogin.class.getName());
		// }

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

		Label label = new Label("Home P2");

		verticalLayout.addComponent(label);
		verticalLayout.addComponent(bLogout);
		return verticalLayout;
	}

}
