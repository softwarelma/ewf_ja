package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityComponentComponent;

public class EwfDaoComponentComponent extends EwfDaoAbstract<EwfEntityComponentComponent> {

    private static final long serialVersionUID = 1L;

    public EwfDaoComponentComponent(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
