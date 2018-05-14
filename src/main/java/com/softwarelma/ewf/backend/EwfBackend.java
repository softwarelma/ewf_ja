package com.softwarelma.ewf.backend;

import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.epe.p3.db.EpeDbMetaDataEntity;

public class EwfBackend {

    // TODO global params
    // TODO global param: seconds to reload

    private final EwfBackendDaoNativeQueries daoNativeQueries = new EwfBackendDaoNativeQueries();
    private final EwfBackendDaoNaturalJoin daoNaturalJoin = new EwfBackendDaoNaturalJoin();

    public EwfBackend() throws EpeAppException {
    }

    public EpeDbMetaDataEntity retrieveSelectAllElems(List<EpeDbEntity> listEntity) throws EpeAppException {
        return this.daoNativeQueries.retrieveSelectAllElems(listEntity);
    }

    public EpeDbMetaDataEntity retrieveSelectAllContents(List<EpeDbEntity> listEntity) throws EpeAppException {
        return this.daoNativeQueries.retrieveSelectAllContents(listEntity);
    }

    public EpeDbMetaDataEntity retrieveSelectAllComps(List<EpeDbEntity> listEntity) throws EpeAppException {
        return this.daoNativeQueries.retrieveSelectAllComps(listEntity);
    }

    public EpeDbMetaDataEntity retrieveSelectAllPages(List<EpeDbEntity> listEntity) throws EpeAppException {
        return this.daoNativeQueries.retrieveSelectAllPages(listEntity);
    }

    public EpeDbMetaDataEntity retrieveListEntity(String select, String table, List<EpeDbEntity> listEntity)
            throws EpeAppException {
        return this.daoNativeQueries.retrieveListEntity(select, table, listEntity);
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
