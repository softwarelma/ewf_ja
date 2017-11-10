package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityBinding;

public class EwfDaoBinding extends EwfDaoAbstract<EwfEntityBinding> {

    private static final long serialVersionUID = 1L;

    public EwfDaoBinding(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
