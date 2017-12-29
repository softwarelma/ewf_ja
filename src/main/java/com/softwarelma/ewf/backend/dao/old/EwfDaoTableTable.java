package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityTableTable;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoTableTable extends EwfDaoAbstract<EwfEntityTableTable> {

    private static final long serialVersionUID = 1L;

    public EwfDaoTableTable(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
