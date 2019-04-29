package com.gms.xms.workflow.render.managecontact;

import com.gms.xms.filter.account.contact.ManageContactFilter;

public interface IManageContactRender {
    public void generateXlsFile(ManageContactFilter filter, String outputFilePath) throws Exception;
}
