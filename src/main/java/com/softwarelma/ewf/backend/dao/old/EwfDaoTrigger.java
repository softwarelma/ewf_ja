package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityTrigger;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoTrigger extends EwfDaoAbstract<EwfEntityTrigger> {

    private static final long serialVersionUID = 1L;

    public EwfDaoTrigger(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
