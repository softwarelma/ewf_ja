package com.softwarelma.ewf.client.comp;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;
import com.vaadin.ui.UI;

public class EwfCompDefault extends EwfCompAbstract {

	private static final long serialVersionUID = 1L;

	public EwfCompDefault(EwfClient client, UI ui, String name) throws EpeAppException {
		super(client, ui, name);
	}

}
