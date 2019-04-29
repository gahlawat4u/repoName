package com.gms.xms.workflow.render.csvinvoices;

public interface ICsvInvoicesRender {
    public void generateCsvFile(Long invoiceId, String franchiseCode, String outputFilePath) throws Exception;
}
