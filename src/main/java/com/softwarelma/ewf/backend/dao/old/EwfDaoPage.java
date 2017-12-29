package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityPage;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoPage extends EwfDaoAbstract<EwfEntityPage> {

    private static final long serialVersionUID = 1L;

    public EwfDaoPage(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
