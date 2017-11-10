package com.softwarelma.ewf.backend.dao;

import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.backend.entity.EwfEntitySite;
import com.softwarelma.ewf.backend.entity.EwfEntityUser;
import com.softwarelma.ewf.backend.entity.EwfEntityUserSite;
import com.softwarelma.ewf.common.EwfCommonConstants;

public class EwfDaoUserSite extends EwfDaoAbstract<EwfEntityUserSite> {

    private static final long serialVersionUID = 1L;

    public EwfDaoUserSite(EwfBackend backend) throws EpeAppException {
        super(backend);
    }

    public void initFake() throws EpeAppException {
        EwfEntityUserSite adminSite = new EwfEntityUserSite();
        adminSite.setName(EwfCommonConstants.ENTITY_NAME_UNUSED);
        EwfEntityUser user = new EwfEntityUser();
        user.setId(1L);
        user.setAdmin(true);
        user.setName(EwfCommonConstants.ENTITY_NAME_UNUSED);
        adminSite.setUser(user);
        EwfEntitySite site = new EwfEntitySite();
        site.setId(1L);
        site.setName(EwfCommonConstants.ENTITY_NAME_UNUSED);
        adminSite.setSite(site);
        this.create(adminSite);
    }

    public List<EwfEntityUser> readListAdmin(EwfEntityUser admin) throws EpeAppException {
        List<EwfEntityUserSite> listAdminSiteBySites = this.readListAdminSiteBySites(admin);
        List<EwfEntityUser> listAdmin = new ArrayList<>();

        for (EwfEntityUserSite adminSite : listAdminSiteBySites) {
            if (!listAdmin.contains(adminSite.getUser())) {
                listAdmin.add(adminSite.getUser());
            }
        }

        return listAdmin;
    }

    public List<EwfEntitySite> readListSite(EwfEntityUser admin) throws EpeAppException {
        EpeAppUtils.checkNull("admin", admin);
        EpeAppUtils.checkNull("admin.id", admin.getId());
        EpeAppUtils.checkEquals("user.admin", "true", admin.getAdmin(), true, "The user must be an admin");
        List<EwfEntityUserSite> listAdminSite = this.readList("admin", admin.getId());
        List<EwfEntitySite> listSite = new ArrayList<>();

        for (EwfEntityUserSite adminSite : listAdminSite) {
            // if (!listSite.contains(adminSite.getSite())) {
            listSite.add(adminSite.getSite());
            // }
        }

        return listSite;
    }

    public List<EwfEntityUserSite> readListAdminSiteBySites(EwfEntityUser admin) throws EpeAppException {
        List<EwfEntitySite> listSite = this.readListSite(admin);
        List<EwfEntityUserSite> listAdminSiteBySites = new ArrayList<>();

        for (EwfEntitySite site : listSite) {
            List<EwfEntityUserSite> listAdminSiteBySitesEntity = this.readList("site", site.getId());

            for (EwfEntityUserSite adminSite : listAdminSiteBySitesEntity) {
                // if (!listAdminSiteBySites.contains(adminSite)) {
                listAdminSiteBySites.add(adminSite);
                // }
            }
        }

        return listAdminSiteBySites;
    }

}
