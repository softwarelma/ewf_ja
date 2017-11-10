package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntitySite;

public class EwfDaoSite extends EwfDaoAbstract<EwfEntitySite> {

    private static final long serialVersionUID = 1L;

    public EwfDaoSite(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
