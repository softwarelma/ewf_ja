package com.softwarelma.ewf.client;

import java.io.Serializable;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.sate.EwfState;
import com.softwarelma.ewf.client.sate.EwfStateLogin;
import com.softwarelma.ewf.server.EwfServer;

public class EwfClientGraph implements Serializable {

    private static final long serialVersionUID = 1L;

    public void init(EwfServer server) throws EpeAppException {
        EwfState state = new EwfStateLogin();

        while (true) {
            state = state.doAndGetNext(server);
        }
    }

}
