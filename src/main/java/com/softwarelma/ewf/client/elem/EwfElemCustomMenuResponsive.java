package com.softwarelma.ewf.client.elem;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class EwfElemCustomMenuResponsive extends EwfElemAbstract {

	private static final long serialVersionUID = 1L;

	public EwfElemCustomMenuResponsive(EwfClient client, UI ui, String name, EwfElemBean elemBean)
			throws EpeAppException {
		super(client, ui, name, elemBean);
	}

	@Override
	public Component getComponent() throws EpeAppException {
		EpeAppUtils.checkNull("mapPageAndDescription", getMapPageAndDescription());
		HorizontalLayout layout = new HorizontalLayout();
		// layout.setWidth("100%");

		for (String pageName : getMapPageAndDescription().keySet()) {
			Button button = new Button(getMapPageAndDescription().get(pageName));
			button.addClickListener(e -> {
				// getUi().getPage().setLocation("/" +
				// EwfCommonConstants.APPLICATION_NAME + "/" + pageName +
				// "/");
				try {
					getClient().loadPage(getUi(), pageName);
				} catch (EpeAppException ex) {
				}
			});
			layout.addComponent(button);
			// layout.setComponentAlignment(button, Alignment.MIDDLE_LEFT);
		}

		return layout;
	}

}
