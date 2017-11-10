package com.softwarelma.ewf.backend.dao;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntityCss;

public class EwfDaoCss extends EwfDaoAbstract<EwfEntityCss> {

    private static final long serialVersionUID = 1L;

    public EwfDaoCss(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
