package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityTableTable;

public class EwfDaoTableTable extends EwfDaoAbstract<EwfEntityTableTable> {

    private static final long serialVersionUID = 1L;

    public EwfDaoTableTable(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
