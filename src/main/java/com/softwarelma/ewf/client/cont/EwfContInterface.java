package com.softwarelma.ewf.client.cont;

import java.io.Serializable;

import com.softwarelma.ewf.client.EwfClient;
import com.vaadin.ui.UI;

public interface EwfContInterface extends Serializable {

	public EwfClient getClient();

	public UI getUi();

	public String getName();

	public boolean isContainer();

	public boolean isContent();

	public boolean isPage();

	public boolean isComp();

	public boolean isElem();

}
