package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityUser;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoUser extends EwfDaoAbstract<EwfEntityUser> {

    private static final long serialVersionUID = 1L;

    public EwfDaoUser(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
