package com.softwarelma.ewf.backend;

import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;

public class EwfBackend {

    // TODO global params
    // TODO global param: seconds to reload

    private final EwfBackendDaoNativeQueries daoNativeQueries = new EwfBackendDaoNativeQueries();
    private final EwfBackendDaoNaturalJoin daoNaturalJoin = new EwfBackendDaoNaturalJoin();

    public List<EpeDbEntity> retrieveSelectAllElems() throws EpeAppException {
        return this.daoNativeQueries.retrieveSelectAllElems();
    }

    public List<EpeDbEntity> retrieveSelectAllContents() throws EpeAppException {
        return this.daoNativeQueries.retrieveSelectAllContents();
    }

    public List<EpeDbEntity> retrieveSelectAllComps() throws EpeAppException {
        return this.daoNativeQueries.retrieveSelectAllComps();
    }

    public List<EpeDbEntity> retrieveSelectAllPages() throws EpeAppException {
        return this.daoNativeQueries.retrieveSelectAllPages();
    }

    public List<EpeDbEntity> retrieveListEntity(String select, String table) throws EpeAppException {
        return this.daoNativeQueries.retrieveListEntity(select, table);
    }

    public void insertBlank(String table) throws EpeAppException {
        this.daoNativeQueries.insertBlank(table);
    }

    public void update(EpeDbEntity entity) throws EpeAppException {
        this.daoNativeQueries.update(entity);
    }

    public void delete(EpeDbEntity entity) throws EpeAppException {
        this.daoNativeQueries.delete(entity);
    }

}
