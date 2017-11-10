package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityTrigger;

public class EwfDaoTrigger extends EwfDaoAbstract<EwfEntityTrigger> {

    private static final long serialVersionUID = 1L;

    public EwfDaoTrigger(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
