package com.softwarelma.ewf.backend.dao.old;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.old.EwfEntityAbstract;

public interface EwfDaoInterface<T extends EwfEntityAbstract> extends Serializable {

    public void create(T entity) throws EpeAppException;

    public void update(T entity) throws EpeAppException;

    public void delete(T entity) throws EpeAppException;

    public T read(Long id) throws EpeAppException;

    public List<T> readList(String attributeName, Long idFk) throws EpeAppException;

    public List<T> readList(List<Map.Entry<String, Object>> listAttNameAndValue) throws EpeAppException;

}
