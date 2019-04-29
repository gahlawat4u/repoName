package com.gms.xms.workflow.render.systemlogs;

import com.gms.xms.model.admin.EventLogModel;

import java.util.List;

public interface ISystemLogsRender {
    public void generatePrintHtml(List<EventLogModel> eventLogModels, String realPath, String outputFilePath);

    public void generateXlSFile(List<EventLogModel> eventLogModels, String outPutFilePath) throws Exception;
}
