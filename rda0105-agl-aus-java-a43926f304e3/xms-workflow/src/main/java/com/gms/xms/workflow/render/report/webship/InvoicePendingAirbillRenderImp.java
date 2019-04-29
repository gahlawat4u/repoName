package com.gms.xms.workflow.render.report.webship;

import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.webship.InvoicePendingAirbillFilter;
import com.gms.xms.model.reports.webship.InvoicePendingAirbillModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.report.webship.IInvoicePendingAirbillService;
import com.gms.xms.persistence.service.report.webship.InvoicePendingAirbillServiceImp;
import com.gms.xms.txndb.vo.reports.webship.InvoicePendingAirbillVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.jxls.common.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from InvoicePendingAirbillRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class InvoicePendingAirbillRenderImp extends BaseRender implements IInvoicePendingAirbillRender {
    private IInvoicePendingAirbillService service = new InvoicePendingAirbillServiceImp();

    public InvoicePendingAirbillRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderInvoicePendingAirbillHtmlFile(InvoicePendingAirbillFilter filter, String outputFilePath, String realPath) throws Exception {
        List<InvoicePendingAirbillVo> invoicePendingAirbillVos = service.getInvoicePendingAirbillReport(filter);
        List<InvoicePendingAirbillModel> invoicePendingAirbillModels = ModelUtils.createListModelFromVo(invoicePendingAirbillVos, InvoicePendingAirbillModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("histories", invoicePendingAirbillModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/report/webship/invoice_pending_airbill/invoice_pending_airbill.ftl", data);
    }

    @Override
    public void renderInvoicePendingAirbillXlsFile(InvoicePendingAirbillFilter filter, String outPutFilePath) throws Exception {
        List<InvoicePendingAirbillVo> invoicePendingAirbillVos = service.getInvoicePendingAirbillReport(filter);
        List<InvoicePendingAirbillModel> invoicePendingAirbillModels = ModelUtils.createListModelFromVo(invoicePendingAirbillVos, InvoicePendingAirbillModel.class);
        for (InvoicePendingAirbillModel invoicePendingAirbillModel : invoicePendingAirbillModels) {
            String weight = "";
            if (invoicePendingAirbillModel.getWeight() != null) {
                weight = invoicePendingAirbillModel.getWeight();
            }
            invoicePendingAirbillModel.setWeight(weight);
        }

        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("histories", invoicePendingAirbillModels);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/report/webship/invoice_pending_airbill/invoice_pending_airbill.xls", data);
    }
}
