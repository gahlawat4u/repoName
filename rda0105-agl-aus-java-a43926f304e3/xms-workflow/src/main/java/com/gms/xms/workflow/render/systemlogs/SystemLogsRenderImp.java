package com.gms.xms.workflow.render.systemlogs;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.admin.EventLogModel;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemLogsRenderImp extends BaseRender implements ISystemLogsRender {
    public SystemLogsRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generatePrintHtml(List<EventLogModel> eventLogModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("eventLogs", eventLogModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_logs/system_logs.ftl", data);
    }

    @Override
    public void generateXlSFile(List<EventLogModel> eventLogModels, String outPutFilePath) throws Exception {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("eventLogs", eventLogModels);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/system_logs/system_logs.xls", data);
    }
}
