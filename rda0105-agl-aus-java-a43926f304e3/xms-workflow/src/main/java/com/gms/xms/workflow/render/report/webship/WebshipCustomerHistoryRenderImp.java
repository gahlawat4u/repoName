package com.gms.xms.workflow.render.report.webship;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.webship.WebshipCustomerHistoryFilter;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import com.gms.xms.workflow.service.report.webship.IWebshipCustomerHistoryService;
import com.gms.xms.workflow.service.report.webship.WebshipCustomerHistoryServiceImp;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from WebshipCustomerHistoryRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class WebshipCustomerHistoryRenderImp extends BaseRender implements IWebshipCustomerHistoryRender {
    private IWebshipCustomerHistoryService service = new WebshipCustomerHistoryServiceImp(this.getAddInfo());

    public WebshipCustomerHistoryRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderWebshipCustomerHistoryHtmlFile(WebshipCustomerHistoryFilter filter, List<String> columns, List<String> displayColumns, String outputFilePath, String realPath) throws Exception {
        List<Map<String, String>> histories = service.getWebshipCustomerHistoryReport(filter);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("realPath", realPath);
        data.put("columns", columns);
        data.put("displayColumns", displayColumns);
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("histories", histories);
        AppUtils.template2File(outputFilePath, false, "templates/html/report/webship/webship_customer_history/webship_customer_history.ftl", data);
    }

    @Override
    public void renderWebshipCustomerHistoryXlsFile(WebshipCustomerHistoryFilter filter, List<String> columns, List<String> displayColumns, String outPutFilePath) throws Exception {
        List<Map<String, String>> histories = service.getWebshipCustomerHistoryReport(filter);

        Context data = new Context();
        data.putVar("columns", columns);
        data.putVar("displayColumns", displayColumns);
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("histories", histories);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/report/webship/webship_customer_history/webship_customer_history.xls", data);
    }
}
