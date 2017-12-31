package com.softwarelma.ewf.client.page;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.comp.EwfCompDefault;
import com.softwarelma.ewf.client.comp.EwfCompInterface;
import com.softwarelma.ewf.client.cont.EwfContainerAbstract;

public abstract class EwfPageAbstract extends EwfContainerAbstract implements EwfPageInterface {

    private static final long serialVersionUID = 1L;
    private final EwfCompInterface comp;

    protected EwfPageAbstract(EwfClient client, String name) throws EpeAppException {
        super(client, name);
        String compName = client.getCompNameNotNull(name);
        this.comp = new EwfCompDefault(client, compName);
    }

    @Override
    public EwfCompInterface getComp() {
        return comp;
    }

    @Override
    public boolean isPage() {
        return true;
    }

    @Override
    public boolean isComp() {
        return false;
    }

    @Override
    public boolean isElem() {
        return false;
    }

}
