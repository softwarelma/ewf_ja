package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityComponentComponent;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoComponentComponent extends EwfDaoAbstract<EwfEntityComponentComponent> {

    private static final long serialVersionUID = 1L;

    public EwfDaoComponentComponent(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
