package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityNavigation;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoNavigation extends EwfDaoAbstract<EwfEntityNavigation> {

    private static final long serialVersionUID = 1L;

    public EwfDaoNavigation(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
