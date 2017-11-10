package com.softwarelma.ewf.client.sate;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppLogger;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.epe.p3.generic.EpeGenericFinalRead_line;
import com.softwarelma.ewf.backend.dao.EwfDaoUser;
import com.softwarelma.ewf.backend.entity.EwfEntityAbstract;
import com.softwarelma.ewf.backend.entity.EwfEntityUser;
import com.softwarelma.ewf.server.EwfServer;

public class EwfStateLogin implements EwfState {

    private static final long serialVersionUID = 1L;

    @Override
    public EwfState doAndGetNext(EwfServer server) throws EpeAppException {
        EwfEntityUser user = this.login(server);

        if (user == null) {
            return this;
        } else if (user.getAdmin()) {
            return new EwfStateAdminHome();
        } else if (!user.getAdmin()) {
            return new EwfStateFinalPage();
        } else {
            throw new EpeAppException("Unknown user type");
        }
    }

    private EwfEntityUser login(EwfServer server) throws EpeAppException {
        EpeAppUtils.checkNull("server", server);
        EpeAppLogger.log("--- LOGIN ---");
        String username = EpeGenericFinalRead_line.retrieveExternalInput(true, "Username:", "*", null, true, false);
        String password = EpeGenericFinalRead_line.retrieveExternalInput(true, "Password:", "*", null, true, false);
        boolean admin = EpeGenericFinalRead_line.retrieveExternalInput(true, "Admin? ", "y|n", ":", false, false)
                .equals("y");

        List<Map.Entry<String, Object>> listAttNameAndValue = new ArrayList<>();
        listAttNameAndValue.add(new AbstractMap.SimpleEntry<>("username", username));
        listAttNameAndValue.add(new AbstractMap.SimpleEntry<>("password", password));

        String daoClassName = EwfDaoUser.class.getName();
        List<EwfEntityAbstract> listEntity = server.readList(daoClassName, listAttNameAndValue);

        if (listEntity.size() != 1) {
            return null;
        }

        EwfEntityUser user = (EwfEntityUser) listEntity.get(0);

        if (user.getAdmin() != admin) {
            return null;
        }

        return user;
    }

}
