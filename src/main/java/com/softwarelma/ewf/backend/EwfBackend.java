package com.softwarelma.ewf.backend;

import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;

public class EwfBackend {

    private final EwfBackendDaoNativeQueries daoNativeQueries = new EwfBackendDaoNativeQueries();
    private final EwfBackendDaoNaturalJoin daoNaturalJoin = new EwfBackendDaoNaturalJoin();

    public List<EpeDbEntity> retrieveListElem() throws EpeAppException {
        return this.daoNativeQueries.retrieveListElem();
    }

    public List<EpeDbEntity> retrieveListContent() throws EpeAppException {
        return this.daoNativeQueries.retrieveListContent();
    }

    public List<EpeDbEntity> retrieveListComp() throws EpeAppException {
        return this.daoNativeQueries.retrieveListComp();
    }

    public List<EpeDbEntity> retrieveListPage() throws EpeAppException {
        return this.daoNativeQueries.retrieveListPage();
    }

}
