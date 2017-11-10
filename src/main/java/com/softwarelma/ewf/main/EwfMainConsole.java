package com.softwarelma.ewf.main;

import java.io.Serializable;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.ewf.client.EwfClientConsole;
import com.softwarelma.ewf.server.EwfServer;

public class EwfMainConsole implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        try {
            EwfServer server = EwfServer.getInstance();
            new EwfClientConsole(server);
        } catch (EpeAppException e) {
            System.exit(0);
        }
    }

}
