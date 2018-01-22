package com.softwarelma.ewf.client.elem;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;

public class EwfElemCustomImage extends EwfElemAbstract {

	private static final long serialVersionUID = 1L;

	public EwfElemCustomImage(EwfClient client, UI ui, EwfElemBean elemBean, EwfContentBean contentBean)
			throws EpeAppException {
		super(client, ui, elemBean, contentBean);
	}

	@Override
	public Component getComponent() throws EpeAppException {
		EpeAppUtils.checkEmpty("fileName", this.getFileName());
		Image image = new Image(null, new ThemeResource("img/" + this.getFileName()));
//		image.addStyleName("itembox");//FIXME din
		return image;
	}

}
