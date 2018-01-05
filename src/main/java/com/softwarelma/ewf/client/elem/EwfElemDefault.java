package com.softwarelma.ewf.client.elem;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.vaadin.ui.UI;

public class EwfElemDefault extends EwfElemAbstract {

	private static final long serialVersionUID = 1L;

	public EwfElemDefault(EwfClient client, UI ui, EwfElemBean elemBean, EwfContentBean contentBean)
			throws EpeAppException {
		super(client, ui, elemBean, contentBean);
	}

}
