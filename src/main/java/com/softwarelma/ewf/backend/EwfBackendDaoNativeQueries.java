package com.softwarelma.ewf.backend;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppLogger;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_datasource;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_select;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_update;
import com.softwarelma.ewf.common.EwfCommonConstants;

public class EwfBackendDaoNativeQueries {

    private DataSource dataSource;

    private static final String selectAllPages = //
            "select ewf_page.id, ewf_page.name, ewf_page.description, ewf_comp.name as comp_name \n"
                    + "from ewf_page ewf_page, ewf_comp ewf_comp \n"//
                    + "where ewf_page.id_ewf_comp = ewf_comp.id";

    private static final String selectAllContents = //
            "select ewf_comp_content.id, ewf_comp_content.id_ewf_comp, \n"//
                    + "   ifnull(ewf_comp__2.name, ewf_elem__2.name) as name_comp_or_elem, \n"//
                    + "   ewf_comp_content.id_ewf_comp__2, \n"//
                    + "   ifnull(ewf_comp__2.style_names, ewf_elem__2.style_names) as style_names \n"//
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

    public List<EpeDbEntity> retrieveSelectAllElems() throws EpeAppException {
        return this.retrieveListEntity(selectAllElems);
    }

    public List<EpeDbEntity> retrieveSelectAllComps() throws EpeAppException {
        return this.retrieveListEntity(selectAllComps);
    }

    public List<EpeDbEntity> retrieveSelectAllContents() throws EpeAppException {
        return this.retrieveListEntity(selectAllContents);
    }

    public List<EpeDbEntity> retrieveSelectAllPages() throws EpeAppException {
        return this.retrieveListEntity(selectAllPages);
    }

    public List<EpeDbEntity> retrieveListEntity(String select, String table) throws EpeAppException {
        EpeAppUtils.checkEmpty("select", select);
        EpeAppUtils.checkEmpty("table", table);
        List<EpeDbEntity> listPage = new ArrayList<>();
        String limitStr = EwfCommonConstants.QUERY_SELECT_LIMIT + "";
        EpeDbFinalDb_select.readQueryAsEntity(this.getDataSource(), select, table, limitStr, listPage);
        return listPage;
    }

    private List<EpeDbEntity> retrieveListEntity(String select) throws EpeAppException {
        List<EpeDbEntity> listPage = new ArrayList<>();
        String table = "fake";
        String limitStr = EwfCommonConstants.QUERY_SELECT_LIMIT + "";
        EpeDbFinalDb_select.readQueryAsEntity(this.getDataSource(), select, table, limitStr, listPage);
        return listPage;
    }

    public void insertBlank(String table) throws EpeAppException {
        String query = EpeDbEntity.retrieveInsertBlank(table);

        if (EwfCommonConstants.SHOW_SQL) {
            EpeAppLogger.log("Executing blank insert");
            EpeAppLogger.log(query);
        }

        EpeDbFinalDb_update.executeUpdate(this.getDataSource(), query);
    }

    public void update(EpeDbEntity entity) throws EpeAppException {
        String query = entity.retrieveUpdate();

        if (EwfCommonConstants.SHOW_SQL) {
            EpeAppLogger.log("Executing update");
            EpeAppLogger.log(query);
        }

        EpeDbFinalDb_update.executeUpdate(this.getDataSource(), query);
    }

    public void delete(EpeDbEntity entity) throws EpeAppException {
        String query = entity.retrieveDelete();

        if (EwfCommonConstants.SHOW_SQL) {
            EpeAppLogger.log("Executing delete");
            EpeAppLogger.log(query);
        }

        EpeDbFinalDb_update.executeUpdate(this.getDataSource(), query);
    }

}
