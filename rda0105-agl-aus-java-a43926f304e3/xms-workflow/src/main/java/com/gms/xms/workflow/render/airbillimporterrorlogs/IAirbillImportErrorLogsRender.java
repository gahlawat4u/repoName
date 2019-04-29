package com.gms.xms.workflow.render.airbillimporterrorlogs;

import com.gms.xms.filter.invoicing.DuplicateAirbillFilter;

public interface IAirbillImportErrorLogsRender {
    public void generateHtmlFile(DuplicateAirbillFilter filter, String outputFilePath) throws Exception;

    public void generateXlsFile(DuplicateAirbillFilter filter, String outputFilePath) throws Exception;
}
