package com.softwarelma.ewf.client.elem;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class EwfElemCustomMenuResponsive extends EwfElemAbstract {

	private static final long serialVersionUID = 1L;

	public EwfElemCustomMenuResponsive(EwfClient client, UI ui, EwfElemBean elemBean, EwfContentBean contentBean)
			throws EpeAppException {
		super(client, ui, elemBean, contentBean);
	}

	@Override
	public Component getComponent() throws EpeAppException {
		if (super.getComponent() != null) {
			return super.getComponent();
		}

		EpeAppUtils.checkNull("mapPageAndDescription", getMapPageAndDescription());

		// TODO
		// 1. make a vert lay with both: hor and vert menu
		// 2. add toggle classes
		// 3. set scss ewf classes
		// 4. add menu main button for vert

		// HorizontalLayout layout = new HorizontalLayout();
		VerticalLayout layout = new VerticalLayout();
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
