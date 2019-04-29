package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.InvoiceModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.InvoiceFilter;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class WebshipInvoiceClient extends WorkflowBaseClient {

    public WebshipInvoiceClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public ContextBase getPaidInvoicesByCustCode(InvoiceFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.INVOICE_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetPaidInvoicesByCustCode");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    public ContextBase getOutstandingInvoicesByCustCode(InvoiceFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.INVOICE_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetOutstandingInvoicesByCustCode");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    public ContextBase getPaidInvoicesCountByCustCode(InvoiceFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.INVOICE_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetPaidInvoicesCountByCustCode");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    public ContextBase getOutstandingInvoicesCountByCustCode(InvoiceFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.INVOICE_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetOutstandingInvoicesCountByCustCode");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    public ContextBase getOutstandingInvoiceTotalByCustCode(InvoiceFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.INVOICE_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetOutstandingInvoiceTotalByCustCode");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    public ContextBase getPaidInvoiceTotalByCustCode(InvoiceFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.INVOICE_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetPaidInvoiceTotalByCustCode");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    public void renderOutstandingInvoicesHtmlReport(InvoiceFilter filter, String outputFilePath) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        ContextBase context = getOutstandingInvoicesByCustCode(filter);
        List<InvoiceVo> outstandingInvoices = GsonUtils.fromGson(context.get(Attributes.OUTSTANDING_INVOICE_LIST_RESULT), new TypeToken<List<InvoiceVo>>() {
        }.getType());
        List<InvoiceModel> outstandingList = new Vector<InvoiceModel>();
        outstandingList = ModelUtils.createListModelFromVo(outstandingInvoices, InvoiceModel.class);
        data.put("invoices", outstandingList);
        AppUtils.template2File(outputFilePath, false, "templates/webship/ws_invoices.ftl", data);
    }

    public void renderPaidInvoicesHtmlReport(InvoiceFilter filter, String outputFilePath) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        ContextBase context = getPaidInvoicesByCustCode(filter);
        List<InvoiceVo> outstandingInvoices = GsonUtils.fromGson(context.get(Attributes.PAID_INVOICE_LIST_RESULT), new TypeToken<List<InvoiceVo>>() {
        }.getType());
        List<InvoiceModel> outstandingList = new Vector<InvoiceModel>();
        outstandingList = ModelUtils.createListModelFromVo(outstandingInvoices, InvoiceModel.class);
        data.put("invoices", outstandingList);
        AppUtils.template2File(outputFilePath, false, "templates/webship/ws_invoices.ftl", data);
    }
}
