package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityListener;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoListener extends EwfDaoAbstract<EwfEntityListener> {

    private static final long serialVersionUID = 1L;

    public EwfDaoListener(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
