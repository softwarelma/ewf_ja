package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityTable;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoTable extends EwfDaoAbstract<EwfEntityTable> {

    private static final long serialVersionUID = 1L;

    public EwfDaoTable(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
