package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityBinding;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoBinding extends EwfDaoAbstract<EwfEntityBinding> {

    private static final long serialVersionUID = 1L;

    public EwfDaoBinding(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
