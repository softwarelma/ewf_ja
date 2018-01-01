package com.softwarelma.ewf.client.elem;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;

public class EwfElemDefault extends EwfElemAbstract {

    private static final long serialVersionUID = 1L;

    public EwfElemDefault(EwfClient client, String name, EwfElemBean elemBean) throws EpeAppException {
        super(client, name, elemBean);
    }

}
