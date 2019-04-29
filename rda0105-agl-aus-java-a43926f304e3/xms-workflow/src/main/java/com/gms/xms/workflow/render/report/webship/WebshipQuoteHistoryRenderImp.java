package com.gms.xms.workflow.render.report.webship;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.webship.WebshipQuoteHistoryFilter;
import com.gms.xms.model.reports.webship.WebshipQuoteHistoryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.report.webship.IWebshipQuoteHistoryService;
import com.gms.xms.persistence.service.report.webship.WebshipQuoteHistoryServiceImp;
import com.gms.xms.txndb.vo.reports.webship.WebshipQuoteHistoryVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from WebshipQuoteHistoryRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class WebshipQuoteHistoryRenderImp extends BaseRender implements IWebshipQuoteHistoryRender {
    private IWebshipQuoteHistoryService service = new WebshipQuoteHistoryServiceImp();

    public WebshipQuoteHistoryRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderWebshipQuoteHistoryHtmlFile(WebshipQuoteHistoryFilter filter, String outputFilePath, String realPath) throws Exception {
        List<WebshipQuoteHistoryVo> webshipQuoteHistoryVos = service.getWebshipQuoteHistoryReport(filter);
        List<WebshipQuoteHistoryModel> webshipQuoteHistoryModels = ModelUtils.createListModelFromVo(webshipQuoteHistoryVos, WebshipQuoteHistoryModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("histories", webshipQuoteHistoryModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/report/webship/webship_quote_history/webship_quote_history.ftl", data);
    }

    @Override
    public void renderWebshipQuoteHistoryXlsFile(WebshipQuoteHistoryFilter filter, String outPutFilePath) throws Exception {
        List<WebshipQuoteHistoryVo> webshipQuoteHistoryVos = service.getWebshipQuoteHistoryReport(filter);
        List<WebshipQuoteHistoryModel> webshipQuoteHistoryModels = ModelUtils.createListModelFromVo(webshipQuoteHistoryVos, WebshipQuoteHistoryModel.class);

        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("histories", webshipQuoteHistoryModels);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/report/webship/webship_quote_history/webship_quote_history.xls", data);
    }
}
