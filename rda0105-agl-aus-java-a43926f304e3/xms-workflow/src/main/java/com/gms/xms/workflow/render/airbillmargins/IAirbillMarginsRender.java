package com.gms.xms.workflow.render.airbillmargins;

import com.gms.xms.filter.invoicing.AirbillMarginFilter;

public interface IAirbillMarginsRender {
    public void generateHtmlFile(AirbillMarginFilter filter, String outputFilePath, String realPath) throws Exception;

    public void generateXlsFile(AirbillMarginFilter filter, String outputFilePath) throws Exception;
}
