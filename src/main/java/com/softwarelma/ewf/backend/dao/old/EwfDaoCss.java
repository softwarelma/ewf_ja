package com.softwarelma.ewf.backend.dao.old;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityCss;
import com.softwarelma.ewf.backend.old.EwfBackend;

public class EwfDaoCss extends EwfDaoAbstract<EwfEntityCss> {

    private static final long serialVersionUID = 1L;

    public EwfDaoCss(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

}
