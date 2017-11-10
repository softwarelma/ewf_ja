package com.softwarelma.ewf.client.sate;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppLogger;
import com.softwarelma.epe.p3.generic.EpeGenericFinalRead_line;
import com.softwarelma.ewf.server.EwfServer;

public class EwfStateFinalPage implements EwfState {

    private static final long serialVersionUID = 1L;

    @Override
    public EwfState doAndGetNext(EwfServer server) throws EpeAppException {
        EpeAppLogger.log("--- FINAL PAGE ---");
        EpeAppLogger.log("1. logout");
        EpeAppLogger.log("2. ...");
        String option = EpeGenericFinalRead_line.retrieveExternalInput(true, "choose an option ", "1|2", ":", true,
                false);

        if (option.equals("1")) {
            return new EwfStateLogin();
        } else if (option.equals("2")) {
            // TODO
            throw new EpeAppException("Unknown option " + option);
        } else {
            throw new EpeAppException("Unknown option " + option);
        }
    }

}
