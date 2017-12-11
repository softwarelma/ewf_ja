package com.softwarelma.ewf.client.page;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClientWeb;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class EwfPageHomeP1 extends EwfPageAbstract {

	private static final long serialVersionUID = 1L;

	public EwfPageHomeP1(EwfClientWeb web) throws EpeAppException {
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

		Label label = new Label("Home P1");

		verticalLayout.addComponent(label);
		verticalLayout.addComponent(bLogout);
		return verticalLayout;
	}

}
