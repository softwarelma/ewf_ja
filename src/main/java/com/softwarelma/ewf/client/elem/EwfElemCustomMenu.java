package com.softwarelma.ewf.client.elem;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.common.EwfCommonConstants;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

public class EwfElemCustomMenu extends EwfElemAbstract {

	private static final long serialVersionUID = 1L;

	public EwfElemCustomMenu(EwfClient client, UI ui, String name, EwfElemBean elemBean) throws EpeAppException {
		super(client, ui, name, elemBean);
	}

	@Override
	public Component getComponent() {
		MenuBar menuBar = new MenuBar();

		for (String pageName : EwfCommonConstants.mapPageAndDescription.keySet()) {
			menuBar.addItem(EwfCommonConstants.mapPageAndDescription.get(pageName), new MenuBar.Command() {

				private static final long serialVersionUID = 1L;

				public void menuSelected(MenuBar.MenuItem selectedItem) {
					getUi().getPage().setLocation("/" + EwfCommonConstants.APPLICATION_NAME + "/" + pageName + "/");
				}

			});
		}

		return menuBar;
	}

}
