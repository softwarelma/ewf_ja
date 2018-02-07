package com.softwarelma.ewf.client.page;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.comp.EwfCompInterface;
import com.softwarelma.ewf.client.cont.EwfContainerInterface;
import com.vaadin.ui.UI;

public interface EwfPageInterface extends EwfContainerInterface {

    public EwfCompInterface getComp();

    public void init(EwfClient client, UI ui, String name) throws EpeAppException;

}
