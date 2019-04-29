package com.gms.xms.workflow.render.airbillerror;

import com.gms.xms.filter.invoicing.RepairAirbillErrorFilter;

public interface IAirbillErrorRender {
    public void generateHtmlFile(RepairAirbillErrorFilter filter, String outputFilePath, Boolean showSenderAddress, Boolean showReceiverAddress, Boolean showAccount) throws Exception;

    public void generateXlsFile(RepairAirbillErrorFilter filter, String outputFilePath, Boolean showSenderAddress, Boolean showReceiverAddress, Boolean showAccount) throws Exception;
}
