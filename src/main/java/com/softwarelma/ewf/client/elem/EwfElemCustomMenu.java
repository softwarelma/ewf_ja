package com.softwarelma.ewf.client.elem;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppRuntimeException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.softwarelma.ewf.main.EwfMain;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

public class EwfElemCustomMenu extends EwfElemAbstract {

	private static final long serialVersionUID = 1L;

	public EwfElemCustomMenu(EwfClient client, UI ui, EwfElemBean elemBean, EwfContentBean contentBean)
			throws EpeAppException {
		super(client, ui, elemBean, contentBean);
	}

	@Override
	public Component getComponent() throws EpeAppException {
		if (super.getComponent() != null) {
			return super.getComponent();
		}

		EpeAppUtils.checkNull("mapPageNameAndPageBean", getMapPageNameAndPageBean());
		MenuBar menuBar = new MenuBar();

		for (String pageName : getMapPageNameAndPageBean().keySet()) {
			menuBar.addItem(getMapPageNameAndPageBean().get(pageName).getDescription(), new MenuBar.Command() {

				private static final long serialVersionUID = 1L;

				@Override
				public void menuSelected(MenuBar.MenuItem selectedItem) {
					// getUi().getPage().setLocation("/" +
					// EwfCommonConstants.APPLICATION_NAME + "/" + pageName +
					// "/");
					try {
						String idSession = ((EwfMain) getUi()).getIdSession();
						getClient().loadPage(getUi(), idSession, pageName);
					} catch (EpeAppException e) {
						throw new EpeAppRuntimeException(e.getMessage(), e);
					}
				}

			});
		}

		return menuBar;
	}

}
