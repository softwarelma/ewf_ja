package com.softwarelma.ewf.client.cont;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;

public abstract class EwfContAbstract implements EwfContInterface {

    private static final long serialVersionUID = 1L;
    private final EwfClient client;
    private final String name;

    protected EwfContAbstract(EwfClient client, String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("client", client);
        EpeAppUtils.checkEmpty("name", name);
        this.client = client;
        this.name = name;
    }

    @Override
    public EwfClient getClient() {
        return client;
    }

    @Override
    public String getName() {
        return name;
    }

}
