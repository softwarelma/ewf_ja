package com.softwarelma.ewf.client.sate;

import java.io.Serializable;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.server.EwfServer;

public interface EwfState extends Serializable {

    public EwfState doAndGetNext(EwfServer server) throws EpeAppException;

}
