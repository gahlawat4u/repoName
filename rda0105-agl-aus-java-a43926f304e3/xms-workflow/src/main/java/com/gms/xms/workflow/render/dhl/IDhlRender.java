package com.gms.xms.workflow.render.dhl;

public interface IDhlRender {
    public void genAirbillFile(String outPutFilePath, Long shipmentId) throws Exception;
    
    public void genAirbillUpsFile(String outPutFilePath, Long shipmentId) throws Exception;
}
