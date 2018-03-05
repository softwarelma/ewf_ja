package com.softwarelma.ewf.backend;

import java.util.List;

import javax.sql.DataSource;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppLogger;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_datasource;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_select;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_update;
import com.softwarelma.epe.p3.db.EpeDbMetaDataEntity;
import com.softwarelma.ewf.common.EwfCommonConstants;

public class EwfBackendDaoNativeQueries {

    enum QUERY_TYPE {
        SELECT, INSERT, UPDATE, DELETE
    }

    private DataSource dataSource;

    private static final String selectAllPages = //
            "select ewf_page.id, ewf_page.name, ewf_page.description, ewf_comp.name as comp_name \n"
                    + "from ewf_page ewf_page, ewf_comp ewf_comp \n"//
                    + "where ewf_page.id_ewf_comp = ewf_comp.id";

    private static final String selectAllContents = //
            "select ewf_comp_content.id, ewf_comp_content.id_ewf_comp, \n"//
                    + "   ifnull(ewf_comp__2.name, ewf_elem__2.name) as name_comp_or_elem, \n"//
                    + "   ewf_comp_content.id_ewf_comp__2, \n"//
                    + "   ifnull(ewf_comp__2.style_names, ewf_elem__2.style_names) as style_names, \n"//
                    + "   ewf_elem__2.elem_custom_class_name \n"//
                    + " from ewf_comp_content ewf_comp_content \n"//
                    + "   join ewf_comp ewf_comp \n"//
                    + "     on ewf_comp_content.id_ewf_comp = ewf_comp.id \n"//
                    + "   left outer join ewf_comp ewf_comp__2 \n"//
                    + "     on ewf_comp_content.id_ewf_comp__2 = ewf_comp__2.id \n"//
                    + "   left outer join ewf_elem ewf_elem__2 \n"//
                    + "     on ewf_comp_content.id_ewf_elem__2 = ewf_elem__2.id \n"//
                    + " order by ewf_comp_content.id_ewf_comp, ewf_comp_content.ordinal";

    private static final String selectAllComps = "SELECT id, name, class_name_layout FROM ewf.ewf_comp";

    private static final String selectAllElems = //
            "SELECT id, name, component_class_name, text, file_name, \n"//
                    + "elem_custom_class_name, query_select, query_table \n"//
                    + "FROM ewf.ewf_elem";

    private DataSource getDataSource() throws EpeAppException {
        if (this.dataSource == null) {
            this.dataSource = EpeDbFinalDb_datasource.retrieveOrCreateDataSource("jdbc:mysql://localhost:3306/ewf",
                    "ewf_usr", "#~[}à1e%|B");
        }

        return this.dataSource;
    }

    public EpeDbMetaDataEntity retrieveSelectAllElems(List<EpeDbEntity> listEntity) throws EpeAppException {
        return this.retrieveListEntity(selectAllElems, listEntity);
    }

    public EpeDbMetaDataEntity retrieveSelectAllComps(List<EpeDbEntity> listEntity) throws EpeAppException {
        return this.retrieveListEntity(selectAllComps, listEntity);
    }

    public EpeDbMetaDataEntity retrieveSelectAllContents(List<EpeDbEntity> listEntity) throws EpeAppException {
        return this.retrieveListEntity(selectAllContents, listEntity);
    }

    public EpeDbMetaDataEntity retrieveSelectAllPages(List<EpeDbEntity> listEntity) throws EpeAppException {
        return this.retrieveListEntity(selectAllPages, listEntity);
    }

    public EpeDbMetaDataEntity retrieveListEntity(String select, String table, List<EpeDbEntity> listEntity)
            throws EpeAppException {
        EpeAppUtils.checkEmpty("select", select);
        EpeAppUtils.checkEmpty("table", table);
        String limitStr = EwfCommonConstants.QUERY_SELECT_LIMIT + "";
        EpeDbMetaDataEntity metaData = EpeDbFinalDb_select.readQueryAsEntity(this.getDataSource(), select, table,
                limitStr, listEntity);
        return metaData;
    }

    private EpeDbMetaDataEntity retrieveListEntity(String select, List<EpeDbEntity> listEntity) throws EpeAppException {
        String table = "fake";
        String limitStr = EwfCommonConstants.QUERY_SELECT_LIMIT + "";
        EpeDbMetaDataEntity metaData = EpeDbFinalDb_select.readQueryAsEntity(this.getDataSource(), select, table,
                limitStr, listEntity);
        return metaData;
    }

    public void insertBlank(String table) throws EpeAppException {
        this.execute(null, QUERY_TYPE.INSERT, table);
    }

    public void update(EpeDbEntity entity) throws EpeAppException {
        this.execute(entity, QUERY_TYPE.UPDATE, null);
    }

    public void delete(EpeDbEntity entity) throws EpeAppException {
        this.execute(entity, QUERY_TYPE.DELETE, null);
    }

    private void execute(EpeDbEntity entity, QUERY_TYPE queryType, String table) throws EpeAppException {
        EpeAppUtils.checkNull("queryType", queryType);
        String query;

        if (queryType.equals(QUERY_TYPE.INSERT)) {
            EpeAppUtils.checkEmpty("table", table);
            query = EpeDbEntity.retrieveInsertBlank(table);
        } else if (queryType.equals(QUERY_TYPE.UPDATE)) {
            EpeAppUtils.checkNull("entity", entity);
            query = entity.retrieveUpdate();
        } else if (queryType.equals(QUERY_TYPE.DELETE)) {
            EpeAppUtils.checkNull("entity", entity);
            query = entity.retrieveDelete();
        } else {
            throw new EpeAppException("Unknown query type: " + queryType);
        }

        this.logDb(query, queryType);
        this.log(query, queryType);
        EpeDbFinalDb_update.executeUpdate(this.getDataSource(), query);
    }

    private void log(String query, QUERY_TYPE queryType) throws EpeAppException {
        if (!EwfCommonConstants.SHOW_SQL)
            return;
        EpeAppUtils.checkEmpty("query", query);
        EpeAppUtils.checkNull("queryType", queryType);
        EpeAppLogger.log("Executing " + queryType);
        EpeAppLogger.log(query);
    }

    private void logDb(String query, QUERY_TYPE queryType) throws EpeAppException {
        if (!EwfCommonConstants.LOG_DB)
            return;
        EpeAppUtils.checkEmpty("query", query);
        EpeAppUtils.checkNull("queryType", queryType);
        query = query.replace("'", "''");
        String insert = "INSERT INTO ewf_log (name, query_type, query_text) VALUES ('" + System.currentTimeMillis()
                + "-" + System.nanoTime() + "', '" + queryType + "', '" + query + "')";
        EpeDbFinalDb_update.executeUpdate(this.getDataSource(), insert);
    }

}
