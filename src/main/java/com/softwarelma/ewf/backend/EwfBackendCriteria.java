package com.softwarelma.ewf.backend;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.epe.p3.db.EpeDbFinalDb_select;
import com.softwarelma.epe.p3.disk.EpeDiskFinalFread;

public class EwfBackendCriteria {

    private final List<String> listSelect = new LinkedList<>();
    private final List<String> listFrom = new LinkedList<>();
    private final List<String> listWhere = new LinkedList<>();
    private final Map<String, List<String>> mapTableAndListColumn = new HashMap<>();

    // TODO sql-injection
    public EwfBackendCriteria select(String... selectExpression) throws EpeAppException {
        this.listSelect.addAll(Arrays.asList(selectExpression));
        return this;
    }

    // TODO sql-injection
    public EwfBackendCriteria from(String... fromExpression) throws EpeAppException {
        this.listFrom.addAll(Arrays.asList(fromExpression));
        return this;
    }

    // TODO sql-injection
    public EwfBackendCriteria where(String... whereExpression) throws EpeAppException {
        this.listWhere.addAll(Arrays.asList(whereExpression));
        return this;
    }

    private String retrieveSelect() throws EpeAppException {
        // EpeDiskFinalFread.fReadAsString(doLog, filenameStr, encodingStr);
        // Map<String, List<String>> retrieveMapTableAndListColumn
        // =EpeDbFinalDb_select.retrieveMapTableAndListColumn(arg0, arg1)
        // TODO
        return null;
    }

    private String retrieveFrom() throws EpeAppException {
        this.orderFrom();
        this.normListFromAlias();
        this.normListFromJoin();
        StringBuilder sb = new StringBuilder();

        for (String from : this.listFrom) {
            sb.append(from);
        }

        return sb.toString();
    }

    private String retrieveWhere() throws EpeAppException {
        // TODO
        return null;
    }

    private void orderFrom() {
        // TODO
    }

    private void normListFromJoin() throws EpeAppException {
        // TODO
    }

    private void normListFromAlias() throws EpeAppException {
        List<String> listFrom = new LinkedList<>();

        for (String from : this.listFrom) {
            listFrom.add(this.normFrom(from));
        }

        this.listFrom.clear();
        this.listFrom.addAll(listFrom);
    }

    private String normFrom(String fromAndAlias) throws EpeAppException {
        EpeAppUtils.checkEmpty("fromAndAlias", fromAndAlias);
        String table;

        if (fromAndAlias.contains("__")) {
            table = fromAndAlias.split("__")[0];
        } else {
            table = fromAndAlias;
        }

        // TODO add connection
        this.mapTableAndListColumn.putAll(EpeDbFinalDb_select.retrieveMapTableAndListColumn(null, table));

        return table + " " + fromAndAlias;
    }

    private List<EpeDbEntity> execute(String query) throws EpeAppException {
        // TODO
        return null;
    }

    public List<EpeDbEntity> retrieveList() throws EpeAppException {
        StringBuilder sb = new StringBuilder(this.retrieveSelect());
        sb.append(this.retrieveFrom());
        sb.append(this.retrieveWhere());
        return this.execute(sb.toString());
    }

    public EpeDbEntity retrieveUnique() throws EpeAppException {
        List<EpeDbEntity> list = this.retrieveList();
        EpeAppUtils.checkRange(list.size(), 1, 1, false, false);
        return list.get(0);
    }

}
