package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityUser;

public class EwfDaoUser extends EwfDaoAbstract<EwfEntityUser> {

    private static final long serialVersionUID = 1L;

    public EwfDaoUser(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
