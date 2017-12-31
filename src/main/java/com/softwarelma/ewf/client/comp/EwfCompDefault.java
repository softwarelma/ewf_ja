package com.softwarelma.ewf.client.comp;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClient;

public class EwfCompDefault extends EwfCompAbstract {

    private static final long serialVersionUID = 1L;

    public EwfCompDefault(EwfClient client, String name) throws EpeAppException {
        super(client, name);
    }

}
