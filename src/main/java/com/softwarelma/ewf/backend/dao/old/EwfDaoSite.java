package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntitySite;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoSite extends EwfDaoAbstract<EwfEntitySite> {

    private static final long serialVersionUID = 1L;

    public EwfDaoSite(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
