package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityColumn;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoColumn extends EwfDaoAbstract<EwfEntityColumn> {

    private static final long serialVersionUID = 1L;

    public EwfDaoColumn(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
