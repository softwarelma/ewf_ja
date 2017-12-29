package com.softwarelma.ewf.common.old;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;

/**
 * session params
 */
public class EwfCommonGlobalParams implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Map<String, String> mapConstantAndValue = new HashMap<>();
    private final Map<Long, EwfEntityAbstract> mapIdAndEntity = new HashMap<>();

    @Override
    public String toString() {
        return "EwfCommonGlobalParams [\n\tmapConstantAndValue=" + mapConstantAndValue + ", \n\tmapIdAndEntity="
                + mapIdAndEntity + "\n]";
    }

    public String getValueOrNull(String constant) throws EpeAppException {
        EpeAppUtils.checkNull("constant", constant);
        return this.mapConstantAndValue.get(constant);
    }

    public String getValueNotNull(String constant) throws EpeAppException {
        EpeAppUtils.checkNull("constant", constant);
        String value = this.mapConstantAndValue.get(constant);
        EpeAppUtils.checkNull("value", value);
        return value;
    }

    public String putValueOrNull(String constant, String value) throws EpeAppException {
        EpeAppUtils.checkNull("constant", constant);
        return this.mapConstantAndValue.put(constant, value);
    }

    public String putValueNotNull(String constant, String value) throws EpeAppException {
        EpeAppUtils.checkNull("constant", constant);
        EpeAppUtils.checkNull("value", value);
        return this.mapConstantAndValue.put(constant, value);
    }

    public EwfEntityAbstract getEntityOrNull(Long id) throws EpeAppException {
        EpeAppUtils.checkNull("id", id);
        return this.mapIdAndEntity.get(id);
    }

    public EwfEntityAbstract getEntityNotNull(Long id) throws EpeAppException {
        EpeAppUtils.checkNull("id", id);
        EwfEntityAbstract entity = this.mapIdAndEntity.get(id);
        EpeAppUtils.checkNull("entity", entity);
        return entity;
    }

    public EwfEntityAbstract putEntityNotNull(Long id, EwfEntityAbstract entity) throws EpeAppException {
        EpeAppUtils.checkNull("id", id);
        EpeAppUtils.checkNull("entity", entity);
        return this.mapIdAndEntity.put(id, entity);
    }

}
