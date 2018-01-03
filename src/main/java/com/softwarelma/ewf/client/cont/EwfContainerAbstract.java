package com.softwarelma.ewf.client.cont;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;
import com.vaadin.ui.UI;

public abstract class EwfContainerAbstract extends EwfContAbstract implements EwfContainerInterface {

	private static final long serialVersionUID = 1L;

	protected EwfContainerAbstract(EwfClient client, UI ui, String name) throws EpeAppException {
		super(client, ui, name);
	}

	@Override
	public boolean isContainer() {
		return true;
	}

	@Override
	public boolean isContent() {
		return false;
	}

}
