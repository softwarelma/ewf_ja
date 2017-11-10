package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityComponent;

public class EwfDaoComponent extends EwfDaoAbstract<EwfEntityComponent> {

    private static final long serialVersionUID = 1L;

    public EwfDaoComponent(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
