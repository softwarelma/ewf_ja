package com.softwarelma.ewf.backend;

import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p3.db.EpeDbEntity;

public class EwfBackendDaoNaturalJoin {

    // select ewf_comp.name from ewf_page ewf_page, ewf_comp ewf_comp
    // where ewf_page.id_ewf_comp = ewf_comp.id and ewf_page.name = 'home';

    public List<EpeDbEntity> retrieveList() throws EpeAppException {
        // TODO
        return criteria().select("ewf_comp.name").where("ewf_page.name", "'home'").retrieveList();
    }

    public EwfBackendCriteria criteria() {
        return new EwfBackendCriteria();
    }

}
