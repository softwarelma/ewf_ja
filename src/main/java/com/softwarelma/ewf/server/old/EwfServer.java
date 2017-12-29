package com.softwarelma.ewf.server.old;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppLogger;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;
import com.softwarelma.ewf.backend.entity.old.EwfEntitySite;
import com.softwarelma.ewf.backend.entity.old.EwfEntityUser;
import com.softwarelma.ewf.backend.entity.old.EwfEntityUserSite;
import com.softwarelma.ewf.backend.old.EwfBackend;
import com.softwarelma.ewf.backend.old.EwfBackendOperation;
import com.softwarelma.ewf.backend.old.EwfBackendResult;
import com.vaadin.server.VaadinService;

public class EwfServer implements Serializable {

    private static final long serialVersionUID = 1L;
    private static EwfServer server;
    private final EwfBackend backend = new EwfBackend();

    public static EwfServer getInstance() throws EpeAppException {
        if (EwfServer.server != null) {
            return EwfServer.server;
        }

        synchronized (EwfServer.class) {
            if (EwfServer.server != null) {
                return EwfServer.server;
            }

            EwfServer server = new EwfServer();
            EwfServer.server = server;
        }

        return EwfServer.server;
    }

    private EwfServer() throws EpeAppException {
    }

    public <T extends EwfEntityAbstract> void create(String daoClassName, T entity) throws EpeAppException {
        this.backend.create(daoClassName, entity);
    }

    public <T extends EwfEntityAbstract> void update(String daoClassName, T entity) throws EpeAppException {
        this.backend.update(daoClassName, entity);
    }

    public <T extends EwfEntityAbstract> void delete(String daoClassName, T entity) throws EpeAppException {
        this.backend.delete(daoClassName, entity);
    }

    public <T extends EwfEntityAbstract> EwfEntityAbstract read(String daoClassName, Long id) throws EpeAppException {
        return this.backend.read(daoClassName, id);
    }

    public <T extends EwfEntityAbstract> List<T> readList(String daoClassName, String attributeName, Long idFk)
            throws EpeAppException {
        return this.backend.readList(daoClassName, attributeName, idFk);
    }

    public <T extends EwfEntityAbstract> List<T> readList(String daoClassName, List<String> listAttName,
            List<Object> listAttValue) throws EpeAppException {
        return this.backend.readList(daoClassName, listAttName, listAttValue);
    }

    public <T extends EwfEntityAbstract> List<T> readList(String daoClassName,
            List<Map.Entry<String, Object>> listAttNameAndValue) throws EpeAppException {
        return this.backend.readList(daoClassName, listAttNameAndValue);
    }

    public int readListSize(String daoClassName, String attributeName, Long idFk) throws EpeAppException {
        return this.backend.readList(daoClassName, attributeName, idFk).size();
    }

    public int readListSize(String daoClassName, List<String> listAttName, List<Object> listAttValue)
            throws EpeAppException {
        return this.backend.readList(daoClassName, listAttName, listAttValue).size();
    }

    public int readListSize(String daoClassName, List<Map.Entry<String, Object>> listAttNameAndValue)
            throws EpeAppException {
        return this.backend.readList(daoClassName, listAttNameAndValue).size();
    }

    public List<EwfEntityUser> readListAdmin(EwfEntityUser admin) throws EpeAppException {
        return this.backend.readListAdmin(admin);
    }

    public List<EwfEntitySite> readListSite(EwfEntityUser admin) throws EpeAppException {
        return this.backend.readListSite(admin);
    }

    public List<EwfEntityUserSite> readListAdminSiteBySites(EwfEntityUser admin) throws EpeAppException {
        return this.backend.readListAdminSiteBySites(admin);
    }

    // TODO use also constants
    public void setSessionAttribute(String name, Object value) throws EpeAppException {
        EpeAppUtils.checkNull("value", value);
        setSessionAttributeOrNull(name, value);
    }

    public void setSessionAttributeOrNull(String name, Object value) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        EpeAppLogger.log("Setting session attribute with name: " + name + ", and value: " + value);
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute(name, value);
    }

    public Object getSessionAttribute(String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        Object value = VaadinService.getCurrentRequest().getWrappedSession().getAttribute(name);
        EpeAppUtils.checkNull("value", value);
        return value;
    }

    public Object getSessionAttributeOrNull(String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        Object value = VaadinService.getCurrentRequest().getWrappedSession().getAttribute(name);
        return value;
    }

    public void removeSessionAttribute(String name) throws EpeAppException {
        EpeAppUtils.checkEmpty("name", name);
        VaadinService.getCurrentRequest().getWrappedSession().removeAttribute(name);
    }

    public boolean isLoggedUser() throws EpeAppException {
        return this.getSessionAttributeOrNull(EwfEntityUser.class.getName()) != null;
    }

    /**
     * @return the logged user (admin or final)
     */
    public EwfEntityUser getUser() throws EpeAppException {
        return (EwfEntityUser) this.getSessionAttribute(EwfEntityUser.class.getName());
    }

    public EwfBackendResult doOperations(List<EwfBackendOperation> listOperation) throws EpeAppException {
        return this.backend.doOperations(listOperation);
    }

}
