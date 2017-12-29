package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityComponent;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoComponent extends EwfDaoAbstract<EwfEntityComponent> {

    private static final long serialVersionUID = 1L;

    public EwfDaoComponent(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
