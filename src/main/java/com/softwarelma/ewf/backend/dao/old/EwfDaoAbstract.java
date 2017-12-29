package com.softwarelma.ewf.backend.dao.old;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.epe.p3.disk.EpeDiskFinalFread;
import com.softwarelma.epe.p3.disk.EpeDiskFinalFwrite;
import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;
import com.softwarelma.ewf.backend.old.EwfBackend;

public abstract class EwfDaoAbstract<T extends EwfEntityAbstract> implements EwfDaoInterface<T> {

    private static final long serialVersionUID = 1L;
    protected Long idAutoInc;
    protected final Map<Long, T> mapIdAndEntity = new TreeMap<>();

    protected EwfDaoAbstract(EwfBackend backend) throws EpeAppException {
        this.readFromFile(backend);
    }

    @Override
    public void create(T entity) throws EpeAppException {
        this.checkCreate(entity);
        this.idAutoInc++;
        entity.setId(this.idAutoInc);
        this.mapIdAndEntity.put(entity.getId(), entity);
        this.saveToFile();
    }

    @Override
    public void update(T entity) throws EpeAppException {
        this.checkUpdate(entity);
        this.mapIdAndEntity.put(entity.getId(), (T) entity);
        this.saveToFile();
    }

    @Override
    public void delete(T entity) throws EpeAppException {
        this.checkDelete(entity);
        this.mapIdAndEntity.remove(entity.getId());
        this.saveToFile();
    }

    @Override
    public T read(Long id) throws EpeAppException {
        this.checkRead(id);
        T entity = (T) this.mapIdAndEntity.get(id);
        EpeAppUtils.checkNull("entity", entity);
        return entity;
    }

    @Override
    public List<T> readList(String attributeName, Long idFk) throws EpeAppException {
        this.checkReadList(attributeName, idFk);
        List<T> listEntity = new ArrayList<>();
        Method method = null;
        String excepMessage = "attributeName=" + attributeName + ", idFk=" + idFk;

        for (T entity : this.mapIdAndEntity.values()) {
            if (method == null) {
                Method[] arrayMethod = entity.getClass().getMethods();
                String methodName = "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
                method = EwfDaoUtils.findMethod(arrayMethod, methodName);
            }

            try {
                // TODO entity.retrieveEntity(attributeName)
                Object obj = method.invoke(entity);

                if (obj == null) {
                    continue;
                }

                @SuppressWarnings("unchecked")
                T entityAtt = (T) obj;

                if (idFk.equals(entityAtt.getId())) {
                    listEntity.add((T) entity);
                }
            } catch (IllegalAccessException e) {
                throw new EpeAppException(excepMessage, e);
            } catch (IllegalArgumentException e) {
                throw new EpeAppException(excepMessage, e);
            } catch (InvocationTargetException e) {
                throw new EpeAppException(excepMessage, e);
            }
        }

        return listEntity;
    }

    @Override
    public List<T> readList(List<Map.Entry<String, Object>> listAttNameAndValue) throws EpeAppException {
        EpeAppUtils.checkEmpty("listAttNameAndValue", listAttNameAndValue);
        List<T> listEntity = new ArrayList<>();
        List<Method> listMethod = null;

        for (T entity : this.mapIdAndEntity.values()) {
            if (listMethod == null) {
                Method[] arrayMethod = entity.getClass().getMethods();
                listMethod = EwfDaoUtils.findListMethod(arrayMethod, listAttNameAndValue, "get");
            }

            // TODO avoid reflection
            List<Object> listValue = EwfDaoUtils.invoke(listMethod, entity);

            if (EwfDaoUtils.isValidEnity(listAttNameAndValue, listValue)) {
                listEntity.add((T) entity);
            }
        }

        return listEntity;
    }

    protected String getFullFileName() throws EpeAppException {
        String dirName = EpeAppUtils.cleanDirName(EwfDaoUtils.FOLDER);
        String[] arrayClassName = this.getClass().getName().split("\\.");
        String fileName = arrayClassName[arrayClassName.length - 1];
        String fullFileName = dirName + fileName;
        return fullFileName;
    }

    protected void saveToFile() throws EpeAppException {
        StringBuilder sb = new StringBuilder();
        sb.append("idAutoInc=");
        sb.append(this.idAutoInc);

        for (T entity : this.mapIdAndEntity.values()) {
            sb.append("\n");
            sb.append(EwfDaoUtils.toString(entity));
        }

        String fullFileName = this.getFullFileName();
        EpeDiskFinalFwrite.fWrite(false, fullFileName, sb.toString(), "UTF-8", false);
    }

    protected void readFromFile(EwfBackend backend) throws EpeAppException {
        try {
            String fullFileName = this.getFullFileName();

            if (!new File(fullFileName).exists()) {
                this.idAutoInc = 0L;
                return;
            }

            String content = EpeDiskFinalFread.fReadAsString(false, fullFileName, "UTF-8");
            String[] arrayEntityStr = content.split("\n");

            String[] arrayIdAutoInc = arrayEntityStr[0].split("=");
            this.idAutoInc = Long.parseLong(arrayIdAutoInc[1]);

            for (int i = 1; i < arrayEntityStr.length; i++) {
                String entityStr = arrayEntityStr[i];
                String entityClassName = this.getEntityClassName();
                @SuppressWarnings("unchecked")
                Class<T> classEntity = (Class<T>) Class.forName(entityClassName);

                T entity = EwfDaoUtils.newInstance(backend, classEntity, entityStr);
                this.mapIdAndEntity.put(entity.getId(), entity);
            }
        } catch (Exception e) {
            throw new EpeAppException("Reading from file", e);
        }
    }

    protected void checkCreate(T entity) throws EpeAppException {
        EpeAppUtils.checkNull("idAutoInc", this.idAutoInc);
        EpeAppUtils.checkNull("entity", entity);
        EpeAppUtils.checkNullForceNull("entity.id", entity.getId());
        EpeAppUtils.checkEmpty("entity.name", entity.getName());
        this.checkEntityClass(entity);
    }

    protected void checkUpdate(T entity) throws EpeAppException {
        EpeAppUtils.checkNull("entity", entity);
        EpeAppUtils.checkNull("entity.id", entity.getId());
        EpeAppUtils.checkEmpty("entity.name", entity.getName());
        this.checkEntityClass(entity);
    }

    protected void checkDelete(T entity) throws EpeAppException {
        EpeAppUtils.checkNull("entity", entity);
        EpeAppUtils.checkNull("entity.id", entity.getId());
        this.checkEntityClass(entity);
    }

    protected void checkRead(Long id) throws EpeAppException {
        EpeAppUtils.checkNull("id", id);
    }

    protected void checkReadList(String attributeName, Long idFk) throws EpeAppException {
        EpeAppUtils.checkEmpty("attributeName", attributeName);
        EpeAppUtils.checkNull("idFk", idFk);
    }

    protected void checkEntityClass(T entity) throws EpeAppException {
        String expectedEntityClassName = this.getEntityClassName();
        EpeAppUtils.checkEquals("expectedEntityClassName", "entityClassName", expectedEntityClassName,
                entity.getClass().getName());
    }

    protected String getEntityClassName() {
        String daoClassName = this.getClass().getName();
        int ind = daoClassName.lastIndexOf(".");
        String entityClassName = "com.softwarelma.ewf.backend.entity.EwfEntity" + daoClassName.substring(ind + 7);
        return entityClassName;
    }

}
