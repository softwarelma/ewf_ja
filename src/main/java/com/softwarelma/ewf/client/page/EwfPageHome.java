package com.softwarelma.ewf.client.page;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClientWeb;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class EwfPageHome extends EwfPageAbstract {

	private static final long serialVersionUID = 1L;

	public EwfPageHome(EwfClientWeb web) throws EpeAppException {
		super(web);
	}

	@Override
	public Component getContent() throws EpeAppException {
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

		Label label = new Label("Home");

		// Have some component with an appropriate style name
		Label c = new Label("Here be text");
		c.setSizeFull();
		c.addStyleName("myresponsive");
		// content.addComponent(c);

		// Enable Responsive CSS selectors for the component
		Responsive.makeResponsive(c);
		
		return c;

//		verticalLayout.addComponent(label);
//		verticalLayout.addComponent(bLogout);
//		verticalLayout.addComponent(c);
//		return verticalLayout;
	}

}
