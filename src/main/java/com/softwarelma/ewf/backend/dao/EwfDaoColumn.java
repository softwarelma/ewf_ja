package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityColumn;

public class EwfDaoColumn extends EwfDaoAbstract<EwfEntityColumn> {

    private static final long serialVersionUID = 1L;

    public EwfDaoColumn(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
