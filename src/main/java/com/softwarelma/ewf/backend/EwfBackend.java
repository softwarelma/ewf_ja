package com.softwarelma.ewf.backend;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.backend.dao.EwfDaoInterface;
import com.softwarelma.ewf.backend.dao.EwfDaoBinding;
import com.softwarelma.ewf.backend.dao.EwfDaoColumn;
import com.softwarelma.ewf.backend.dao.EwfDaoComponent;
import com.softwarelma.ewf.backend.dao.EwfDaoComponentComponent;
import com.softwarelma.ewf.backend.dao.EwfDaoCss;
import com.softwarelma.ewf.backend.dao.EwfDaoListener;
import com.softwarelma.ewf.backend.dao.EwfDaoNavigation;
import com.softwarelma.ewf.backend.dao.EwfDaoPage;
import com.softwarelma.ewf.backend.dao.EwfDaoSite;
import com.softwarelma.ewf.backend.dao.EwfDaoTable;
import com.softwarelma.ewf.backend.dao.EwfDaoTableTable;
import com.softwarelma.ewf.backend.dao.EwfDaoTrigger;
import com.softwarelma.ewf.backend.dao.EwfDaoUser;
import com.softwarelma.ewf.backend.dao.EwfDaoUserSite;
import com.softwarelma.ewf.backend.entity.EwfEntityAbstract;
import com.softwarelma.ewf.backend.entity.EwfEntitySite;
import com.softwarelma.ewf.backend.entity.EwfEntityUser;
import com.softwarelma.ewf.backend.entity.EwfEntityUserSite;

public class EwfBackend implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Map<String, EwfDaoInterface<?>> mapDaoClassNameAndDao = new HashMap<>();

    public EwfBackend() throws EpeAppException {
        this.init();
    }

    private void init() throws EpeAppException {
        this.mapDaoClassNameAndDao.put(EwfDaoUser.class.getName(), new EwfDaoUser(this));
        this.mapDaoClassNameAndDao.put(EwfDaoSite.class.getName(), new EwfDaoSite(this));
        this.mapDaoClassNameAndDao.put(EwfDaoUserSite.class.getName(), new EwfDaoUserSite(this));
        this.mapDaoClassNameAndDao.put(EwfDaoComponent.class.getName(), new EwfDaoComponent(this));
        this.mapDaoClassNameAndDao.put(EwfDaoCss.class.getName(), new EwfDaoCss(this));
        this.mapDaoClassNameAndDao.put(EwfDaoListener.class.getName(), new EwfDaoListener(this));
        this.mapDaoClassNameAndDao.put(EwfDaoPage.class.getName(), new EwfDaoPage(this));
        this.mapDaoClassNameAndDao.put(EwfDaoComponentComponent.class.getName(), new EwfDaoComponentComponent(this));
        this.mapDaoClassNameAndDao.put(EwfDaoNavigation.class.getName(), new EwfDaoNavigation(this));
        this.mapDaoClassNameAndDao.put(EwfDaoTable.class.getName(), new EwfDaoTable(this));
        this.mapDaoClassNameAndDao.put(EwfDaoTrigger.class.getName(), new EwfDaoTrigger(this));
        this.mapDaoClassNameAndDao.put(EwfDaoColumn.class.getName(), new EwfDaoColumn(this));
        this.mapDaoClassNameAndDao.put(EwfDaoBinding.class.getName(), new EwfDaoBinding(this));
        this.mapDaoClassNameAndDao.put(EwfDaoTableTable.class.getName(), new EwfDaoTableTable(this));
    }

    // TODO list of operations

    public <T extends EwfEntityAbstract> void create(String daoClassName, T entity) throws EpeAppException {
        EpeAppUtils.checkNull("daoClassName", daoClassName);
        @SuppressWarnings("unchecked")
        EwfDaoInterface<T> dao = (EwfDaoInterface<T>) this.mapDaoClassNameAndDao.get(daoClassName);
        EpeAppUtils.checkNull("dao", dao);
        dao.create(entity);
    }

    public <T extends EwfEntityAbstract> void update(String daoClassName, T entity) throws EpeAppException {
        EpeAppUtils.checkNull("daoClassName", daoClassName);
        @SuppressWarnings("unchecked")
        EwfDaoInterface<T> dao = (EwfDaoInterface<T>) this.mapDaoClassNameAndDao.get(daoClassName);
        EpeAppUtils.checkNull("dao", dao);
        dao.update(entity);
    }

    public <T extends EwfEntityAbstract> void delete(String daoClassName, T entity) throws EpeAppException {
        EpeAppUtils.checkNull("daoClassName", daoClassName);
        @SuppressWarnings("unchecked")
        EwfDaoInterface<T> dao = (EwfDaoInterface<T>) this.mapDaoClassNameAndDao.get(daoClassName);
        EpeAppUtils.checkNull("dao", dao);
        dao.delete(entity);
    }

    public <T extends EwfEntityAbstract> EwfEntityAbstract read(String daoClassName, Long id) throws EpeAppException {
        EpeAppUtils.checkNull("daoClassName", daoClassName);
        @SuppressWarnings("unchecked")
        EwfDaoInterface<T> dao = (EwfDaoInterface<T>) this.mapDaoClassNameAndDao.get(daoClassName);
        EpeAppUtils.checkNull(daoClassName, dao);
        return dao.read(id);
    }

    public <T extends EwfEntityAbstract> List<T> readList(String daoClassName, String attributeName, Long idFk)
            throws EpeAppException {
        EpeAppUtils.checkNull("daoClassName", daoClassName);
        @SuppressWarnings("unchecked")
        EwfDaoInterface<T> dao = (EwfDaoInterface<T>) this.mapDaoClassNameAndDao.get(daoClassName);
        EpeAppUtils.checkNull("dao", dao);
        return dao.readList(attributeName, idFk);
    }

    public <T extends EwfEntityAbstract> List<T> readList(String daoClassName, List<String> listAttName,
            List<Object> listAttValue) throws EpeAppException {
        EpeAppUtils.checkEmpty("listAttName", listAttName);
        EpeAppUtils.checkEmpty("listAttValue", listAttValue);
        EpeAppUtils.checkSize("listAttName", listAttName, listAttValue.size());
        List<Map.Entry<String, Object>> listAttNameAndValue = new ArrayList<>();
        String attName;
        Object attValue;
        Map.Entry<String, Object> entry;

        for (int i = 0; i < listAttName.size(); i++) {
            attName = listAttName.get(i);
            EpeAppUtils.checkEmpty("attName", attName);
            attValue = listAttValue.get(i);
            entry = new AbstractMap.SimpleEntry<>(attName, attValue);
            listAttNameAndValue.add(entry);
        }

        return this.readList(daoClassName, listAttNameAndValue);
    }

    public <T extends EwfEntityAbstract> List<T> readList(String daoClassName,
            List<Map.Entry<String, Object>> listAttNameAndValue) throws EpeAppException {
        EpeAppUtils.checkEmpty("daoClassName", daoClassName);
        @SuppressWarnings("unchecked")
        EwfDaoInterface<T> dao = (EwfDaoInterface<T>) this.mapDaoClassNameAndDao.get(daoClassName);
        EpeAppUtils.checkNull("dao", dao);
        return dao.readList(listAttNameAndValue);
    }

    public int readListSize(String daoClassName, String attributeName, Long idFk) throws EpeAppException {
        return this.readList(daoClassName, attributeName, idFk).size();
    }

    public int readListSize(String daoClassName, List<String> listAttName, List<Object> listAttValue)
            throws EpeAppException {
        return this.readList(daoClassName, listAttName, listAttValue).size();
    }

    public int readListSize(String daoClassName, List<Map.Entry<String, Object>> listAttNameAndValue)
            throws EpeAppException {
        return this.readList(daoClassName, listAttNameAndValue).size();
    }

    public List<EwfEntityUser> readListAdmin(EwfEntityUser admin) throws EpeAppException {
        EwfDaoUserSite daoAdminSite = (EwfDaoUserSite) this.mapDaoClassNameAndDao.get(EwfDaoUserSite.class.getName());
        return daoAdminSite.readListAdmin(admin);
    }

    public List<EwfEntitySite> readListSite(EwfEntityUser admin) throws EpeAppException {
        EwfDaoUserSite daoAdminSite = (EwfDaoUserSite) this.mapDaoClassNameAndDao.get(EwfDaoUserSite.class.getName());
        return daoAdminSite.readListSite(admin);
    }

    public List<EwfEntityUserSite> readListAdminSiteBySites(EwfEntityUser admin) throws EpeAppException {
        EwfDaoUserSite daoAdminSite = (EwfDaoUserSite) this.mapDaoClassNameAndDao.get(EwfDaoUserSite.class.getName());
        return daoAdminSite.readListAdminSiteBySites(admin);
    }

    public EwfBackendResult doOperations(List<EwfBackendOperation> listOperation) throws EpeAppException {
        EpeAppUtils.checkNull("listOperation", listOperation);
        EwfBackendResult result = new EwfBackendResult();

        for (EwfBackendOperation operation : listOperation) {
            EpeAppUtils.checkNull("operation", operation);
            EpeAppUtils.checkNull("operation.type", operation.getType());

            if (operation.getType().equals(EwfBackendOperation.Type.CREATE)) {
                this.create(operation.getDaoClassName(), operation.getEntity());
            } else if (operation.getType().equals(EwfBackendOperation.Type.UPDATE)) {
                this.update(operation.getDaoClassName(), operation.getEntity());
            } else if (operation.getType().equals(EwfBackendOperation.Type.DELETE)) {
                this.delete(operation.getDaoClassName(), operation.getEntity());
            } else if (operation.getType().equals(EwfBackendOperation.Type.READ)) {
                EwfEntityAbstract entity = this.read(operation.getDaoClassName(), operation.getId());
                result.setEntity(entity);
            } else {
                // TODO readList
                throw new EpeAppException("Unknown operation type " + operation.getType());
            }
        }

        return result;
    }

}
