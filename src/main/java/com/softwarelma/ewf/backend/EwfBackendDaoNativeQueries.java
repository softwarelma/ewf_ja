package com.softwarelma.ewf.backend;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_datasource;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_select;

public class EwfBackendDaoNativeQueries {

    private DataSource dataSource;

    private static final String selectAllPages = //
            "select ewf_page.id, ewf_page.name, ewf_page.description, ewf_comp.name as comp_name "
                    + "from ewf_page ewf_page, ewf_comp ewf_comp "//
                    + "where ewf_page.id_ewf_comp = ewf_comp.id";

    // TODO
    private static final String selectContentsFromComp = //
            "";

    private DataSource getDataSource() throws EpeAppException {
        if (this.dataSource == null) {
            this.dataSource = EpeDbFinalDb_datasource.retrieveOrCreateDataSource("jdbc:mysql://localhost:3306/ewf",
                    "ewf_usr", "#~[}à1e%|B");
        }

        return this.dataSource;
    }

    public List<EpeDbEntity> retrieveListContent() throws EpeAppException {
        return this.retrieveListEntity(selectContentsFromComp);
    }

    public List<EpeDbEntity> retrieveListPage() throws EpeAppException {
        return this.retrieveListEntity(selectAllPages);
    }

    private List<EpeDbEntity> retrieveListEntity(String select) throws EpeAppException {
        List<EpeDbEntity> listPage = new ArrayList<>();
        String table = "fake";
        String limitStr = "limit=200";
        EpeDbFinalDb_select.readQueryAsEntity(this.getDataSource(), select, table, limitStr, listPage);
        return listPage;
    }

}
