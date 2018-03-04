package com.softwarelma.ewf.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.client.comp.EwfCompBean;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.softwarelma.ewf.client.elem.EwfElemBean;
import com.softwarelma.ewf.client.page.EwfPageBean;

public class EwfServerPage {

    private final EwfBackend backend;

    protected EwfServerPage(EwfBackend backend) {
        this.backend = backend;
    }

    public List<EpeDbEntity> retrieveListEntity(String select, String table) throws EpeAppException {
        return this.backend.retrieveListEntity(select, table);
    }

    public Map<String, EwfPageBean> retrieveMapPageNameAndPageBean() throws EpeAppException {
        Map<String, EwfPageBean> mapPageNameAndPageBean = new LinkedHashMap<>();
        List<EpeDbEntity> listPage = this.backend.retrieveSelectAllPages();

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
        List<EpeDbEntity> listComp = this.backend.retrieveSelectAllComps();
        List<EpeDbEntity> listContent = this.backend.retrieveSelectAllContents();

        for (EpeDbEntity entityComp : listComp) {
            EwfCompBean compBean = new EwfCompBean();
            compBean.setClassNameLayout(entityComp.getString("class_name_layout"));
            compBean.setListContentBean(this.retrieveListContentBean(entityComp, listContent));
            mapCompNameAndCompBean.put(entityComp.getString("name"), compBean);
        }

        return mapCompNameAndCompBean;
    }

    private List<EwfContentBean> retrieveListContentBean(EpeDbEntity entityComp, List<EpeDbEntity> listContent)
            throws EpeAppException {
        List<EwfContentBean> listContentBean = new ArrayList<>();

        for (EpeDbEntity entityContent : listContent) {
            if (!entityContent.get("id_ewf_comp").equals(entityComp.get("id"))) {
                continue;
            }

            EwfContentBean contentBean = new EwfContentBean();
            contentBean.setName(entityContent.getString("name_comp_or_elem"));
            contentBean.setComp(entityContent.get("id_ewf_comp__2") != null);
            Object obj = entityContent.get("style_names");
            if (obj != null)
                contentBean.setListStyleName(Arrays.asList(((String) obj).split("\\,")));
            contentBean.setElemCustomClassName(entityContent.getString("elem_custom_class_name"));
            listContentBean.add(contentBean);
        }

        return listContentBean;
    }

    public Map<String, EwfElemBean> retrieveMapElemNameAndElemBean() throws EpeAppException {
        Map<String, EwfElemBean> mapElemNameAndElemBean = new LinkedHashMap<>();
        List<EpeDbEntity> listElem = this.backend.retrieveSelectAllElems();

        for (EpeDbEntity entityElem : listElem) {
            EwfElemBean elemBean = new EwfElemBean();
            elemBean.setComponentClassName(entityElem.getString("component_class_name"));
            elemBean.setText(entityElem.getString("text"));
            elemBean.setFileName(entityElem.getString("file_name"));
            elemBean.setElemCustomClassName(entityElem.getString("elem_custom_class_name"));
            elemBean.setQuerySelect(entityElem.getString("query_select"));
            elemBean.setQueryTable(entityElem.getString("query_table"));

            // on EwfClient
            // elemBean.setMapPageNameAndPageBean(mapPageNameAndPageBean);

            mapElemNameAndElemBean.put(entityElem.getString("name"), elemBean);
        }

        return mapElemNameAndElemBean;
    }

    public void insertBlank(String table) throws EpeAppException {
        this.backend.insertBlank(table);
    }

    public void update(EpeDbEntity entity) throws EpeAppException {
        this.backend.update(entity);
    }

    public void delete(EpeDbEntity entity) throws EpeAppException {
        this.backend.delete(entity);
    }

}
