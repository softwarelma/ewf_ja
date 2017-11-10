package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityPage;

public class EwfDaoPage extends EwfDaoAbstract<EwfEntityPage> {

    private static final long serialVersionUID = 1L;

    public EwfDaoPage(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
