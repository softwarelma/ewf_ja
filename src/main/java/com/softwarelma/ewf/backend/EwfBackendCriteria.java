package com.softwarelma.ewf.backend;

import java.util.Arrays;
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
//        EpeDiskFinalFread.fReadAsString(doLog, filenameStr, encodingStr);
//         Map<String, List<String>> retrieveMapTableAndListColumn
//         =EpeDbFinalDb_select.retrieveMapTableAndListColumn(arg0, arg1)
        // TODO
        return null;
    }

    private String retrieveFrom() throws EpeAppException {
        // TODO
        return null;
    }

    private String retrieveWhere() throws EpeAppException {
        // TODO
        return null;
    }

    public List<EpeDbEntity> retrieveList() throws EpeAppException {
        StringBuilder sb = new StringBuilder(this.retrieveSelect());
        sb.append(this.retrieveFrom());
        sb.append(this.retrieveWhere());
        // TODO exec
        return null;
    }

    public EpeDbEntity retrieveUnique() throws EpeAppException {
        List<EpeDbEntity> list = this.retrieveList();
        EpeAppUtils.checkRange(list.size(), 1, 1, false, false);
        return list.get(0);
    }

}
