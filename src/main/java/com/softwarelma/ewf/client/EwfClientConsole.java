package com.softwarelma.ewf.client;

import java.io.Serializable;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.server.EwfServer;

public class EwfClientConsole implements Serializable {

    private static final long serialVersionUID = 1L;
    private final EwfClientGraph graph = new EwfClientGraph();

    public EwfClientConsole(EwfServer server) throws EpeAppException {
        this.init(server);
    }

    private void init(EwfServer server) throws EpeAppException {
        this.graph.init(server);
    }

}
