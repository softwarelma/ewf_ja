package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityNavigation;

public class EwfDaoNavigation extends EwfDaoAbstract<EwfEntityNavigation> {

    private static final long serialVersionUID = 1L;

    public EwfDaoNavigation(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
