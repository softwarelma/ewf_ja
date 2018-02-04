package com.softwarelma.ewf.backend;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_datasource;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_select;

public class EwfBackendDaoNativeQueries {

    private static final String selectAllPages = //
            "select ewf_page.name, ewf_page.description, ewf_comp.name comp_name "
                    + "from ewf_page ewf_page, ewf_comp ewf_comp "//
                    + "where ewf_page.id_ewf_comp = ewf_comp.id";

    public List<EpeDbEntity> retrieveListPage() throws EpeAppException {
        List<EpeDbEntity> listPage = new ArrayList<>();
        DataSource dataSource = EpeDbFinalDb_datasource.retrieveOrCreateDataSource("jdbc:mysql://localhost:3306/ewf",
                "ewf_usr", "#~[}à1e%|B");
        String select = selectAllPages;
        String table = "ewf_page";
        String limitStr = "limit=200";
        EpeDbFinalDb_select.readQueryAsEntity(dataSource, select, table, limitStr, listPage);
        return listPage;
    }

}
