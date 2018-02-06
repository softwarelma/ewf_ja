package com.softwarelma.ewf.server;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.client.comp.EwfCompBean;
import com.softwarelma.ewf.client.elem.EwfElemBean;
import com.softwarelma.ewf.client.page.EwfPageBean;

public class EwfServerPage {

    private final EwfBackend backend;

    protected EwfServerPage(EwfBackend backend) {
        this.backend = backend;
    }

    public Map<String, EwfPageBean> retrieveMapPageNameAndPageBean() throws EpeAppException {
        Map<String, EwfPageBean> mapPageNameAndPageBean = new LinkedHashMap<>();
        List<EpeDbEntity> listPage = this.backend.retrieveListPage();

        for (EpeDbEntity entity : listPage) {
            EwfPageBean pageBean = new EwfPageBean();
            pageBean.setName(entity.getString("name"));
            pageBean.setDescription(entity.getString("description"));
            pageBean.setCompName(entity.getString("comp_name"));
            mapPageNameAndPageBean.put(pageBean.getName(), pageBean);
        }

        return mapPageNameAndPageBean;
    }

    public Map<String, EwfCompBean> retrieveMapCompNameAndCompBean() throws EpeAppException {
        Map<String, EwfCompBean> mapCompNameAndCompBean = new LinkedHashMap<>();
        List<EpeDbEntity> listComp = this.backend.retrieveListComp();
        List<EpeDbEntity> listContent = this.backend.retrieveListContent();
        // TODO
        return mapCompNameAndCompBean;
    }

    public Map<String, EwfElemBean> retrieveMapElemNameAndElemBean() throws EpeAppException {
        Map<String, EwfElemBean> mapElemNameAndElemBean = new LinkedHashMap<>();
        List<EpeDbEntity> listElem = this.backend.retrieveListElem();
        // TODO
        return mapElemNameAndElemBean;
    }

}
