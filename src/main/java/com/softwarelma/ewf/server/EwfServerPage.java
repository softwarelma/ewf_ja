package com.softwarelma.ewf.server;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.ewf.backend.EwfBackend;
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

}
