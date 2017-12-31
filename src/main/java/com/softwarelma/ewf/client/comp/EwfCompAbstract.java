package com.softwarelma.ewf.client.comp;

import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.cont.EwfContentAbstract;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.softwarelma.ewf.client.cont.EwfContentInterface;
import com.softwarelma.ewf.client.elem.EwfElemDefault;
import com.vaadin.ui.AbstractLayout;

public abstract class EwfCompAbstract extends EwfContentAbstract implements EwfCompInterface {

    private static final long serialVersionUID = 1L;
    private final List<EwfContentInterface> listContent;

    protected EwfCompAbstract(EwfClient client, String name) throws EpeAppException {
        super(client, name);
        this.listContent = new ArrayList<>();
        this.init();
    }

    private void init() throws EpeAppException {
        List<EwfContentBean> listContentBean = this.getClient().getListContentBeanNotNull(this.getName());

        for (EwfContentBean contentBean : listContentBean) {
            EpeAppUtils.checkNull("contentBean", contentBean);
            EwfContentInterface content = contentBean.isComp()
                    ? new EwfCompDefault(this.getClient(), contentBean.getName())
                    : new EwfElemDefault(this.getClient(), contentBean.getName());
            this.listContent.add(content);
            this.getLayout().addComponent(content.getComponent());
        }
    }

    @Override
    public AbstractLayout getLayout() {
        return (AbstractLayout) this.getComponent();
    }

    @Override
    public boolean isPage() {
        return false;
    }

    @Override
    public boolean isComp() {
        return true;
    }

    @Override
    public boolean isElem() {
        return false;
    }

}
