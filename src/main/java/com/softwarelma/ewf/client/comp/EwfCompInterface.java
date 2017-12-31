package com.softwarelma.ewf.client.comp;

import com.softwarelma.ewf.client.cont.EwfContainerInterface;
import com.softwarelma.ewf.client.cont.EwfContentInterface;
import com.vaadin.ui.AbstractLayout;

public interface EwfCompInterface extends EwfContainerInterface, EwfContentInterface {

    public AbstractLayout getLayout();

}
