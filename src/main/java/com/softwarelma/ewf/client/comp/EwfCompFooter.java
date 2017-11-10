package com.softwarelma.ewf.client.comp;

import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.entity.EwfEntityAbstract;
import com.softwarelma.ewf.client.EwfClientWeb;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class EwfCompFooter<T extends EwfEntityAbstract> extends EwfCompAbstract<T> {

    private static final long serialVersionUID = 1L;

    public EwfCompFooter(EwfClientWeb web) throws EpeAppException {
        super(web);
    }

    @Override
    public Component getContent(T entity) throws EpeAppException {
        return new Label("www.softwarelma.com");
    }

    @Override
    public Component getContent(List<T> listEntity) throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

    @Override
    public Component getContent(List<T> listEntity, List<String> listAttributeForDescription,
            List<String> listAttributeForSession) throws EpeAppException {
        throw new EpeAppException("Invalid invocation");
    }

}
