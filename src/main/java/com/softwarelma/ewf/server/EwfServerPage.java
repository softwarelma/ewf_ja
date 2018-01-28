package com.softwarelma.ewf.server;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.backend.EwfBackend;
import com.softwarelma.ewf.client.page.EwfPageBean;

public class EwfServerPage {

    private final EwfBackend backend;

    protected EwfServerPage(EwfBackend backend) {
        this.backend = backend;
    }

    public EwfPageBean getPageBeanNotNull(String pageName) throws EpeAppException {
        
        
        
        //TODO
        
        return null;
    }

}
