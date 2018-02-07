package com.softwarelma.ewf.client.cont;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.vaadin.ui.UI;

public abstract class EwfContAbstract implements EwfContInterface {

    private static final long serialVersionUID = 1L;
    private EwfClient client;// application scope
    private UI ui;// request scope
    private String name;

    protected EwfContAbstract() {
    }

    protected void init(EwfClient client, UI ui, String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("client", client);
        EpeAppUtils.checkEmpty("ui", ui);
        EpeAppUtils.checkEmpty("name", name);
        this.client = client;
        this.ui = ui;
        this.name = name;
    }

    @Override
    public EwfClient getClient() {
        return client;
    }

    @Override
    public UI getUi() {
        return ui;
    }

    @Override
    public String getName() {
        return name;
    }

}
