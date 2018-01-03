package com.softwarelma.ewf.client.cont;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.vaadin.ui.Component;

public interface EwfContentInterface extends EwfContInterface {

	public Component getComponent() throws EpeAppException;

}
