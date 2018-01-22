package com.softwarelma.ewf.client.page.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.old.EwfClientWeb;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class EwfPageHomeP1 extends EwfPageAbstract {

	private static final long serialVersionUID = 1L;

	public EwfPageHomeP1(EwfClientWeb web) throws EpeAppException {
		super(web);
	}

	@Override
	public Component getContent() throws EpeAppException {
		CssLayout layout = new CssLayout();
		layout.setWidth("100%");
		layout.addStyleName("flexwrap");
		// content.addComponent(layout);

		// Enable Responsive CSS selectors for the layout
		Responsive.makeResponsive(layout);

		Label title = new Label("Space is big, really big");
		title.addStyleName("title");
//		layout.addComponent(title);

		Label description = new Label("This is a " + "long description of the image shown "
				+ "on the right or below, depending on the " + "screen width. The text here could continue long.");
		description.addStyleName("itembox");
		description.setSizeUndefined();
		layout.addComponent(description);

		Image image = new Image(null, new ThemeResource("img/robot.jpeg"));
		image.addStyleName("itembox");
		layout.addComponent(image);
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

		Label label = new Label("Home P1");

		verticalLayout.addComponent(label);
		verticalLayout.addComponent(bLogout);
		return verticalLayout;
	}

}
