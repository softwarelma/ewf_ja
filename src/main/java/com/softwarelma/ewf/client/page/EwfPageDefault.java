package com.softwarelma.ewf.client.page;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;
import com.vaadin.ui.UI;

public class EwfPageDefault extends EwfPageAbstract {

    private static final long serialVersionUID = 1L;

    public EwfPageDefault() {
    }

    /**
     * must be launched
     */
    public void init(EwfClient client, UI ui, String name) throws EpeAppException {
        super.init(client, ui, name);
    }

}
