package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityTable;

public class EwfDaoTable extends EwfDaoAbstract<EwfEntityTable> {

    private static final long serialVersionUID = 1L;

    public EwfDaoTable(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
