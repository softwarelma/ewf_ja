package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityListener;

public class EwfDaoListener extends EwfDaoAbstract<EwfEntityListener> {

    private static final long serialVersionUID = 1L;

    public EwfDaoListener(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
