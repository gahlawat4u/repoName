package com.gms.xms.workflow.render.report.customer.invoicedetail;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerInvoiceDetailFilter;
import com.gms.xms.model.reports.customer.invoicedetail.CustomerCreditNoteDetailModel;
import com.gms.xms.model.reports.customer.invoicedetail.CustomerInvoiceDetailModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerCreditNoteDetailVo;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerInvoiceDetailVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import com.gms.xms.workflow.service.report.customer.invoicedetail.CustomerInvoiceDetailServiceImp;
import com.gms.xms.workflow.service.report.customer.invoicedetail.ICustomerInvoiceDetailService;
import org.apache.commons.lang.StringEscapeUtils;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerInvoiceDetailRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class CustomerInvoiceDetailRenderImp extends BaseRender implements ICustomerInvoiceDetailRender {
    private ICustomerInvoiceDetailService service = new CustomerInvoiceDetailServiceImp(this.getAddInfo());

    public CustomerInvoiceDetailRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderCustomerInvoiceDetailHtmlFile(CustomerInvoiceDetailFilter filter, String outputFilePath, String realPath) throws Exception {
        List<CustomerInvoiceDetailVo> customerInvoiceDetailVos = service.getInvoiceDetailReport(filter);
        List<CustomerInvoiceDetailModel> customerInvoiceDetailModels = ModelUtils.createListModelFromVo(customerInvoiceDetailVos, CustomerInvoiceDetailModel.class);
        CustomerInvoiceDetailVo invoiceDetailSummaryVo = service.sumInvoiceDetailReport(filter);
        CustomerInvoiceDetailModel invoiceDetailSummaryModel = ModelUtils.createModelFromVo(invoiceDetailSummaryVo, CustomerInvoiceDetailModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("customerList", customerInvoiceDetailModels);
        data.put("summary", invoiceDetailSummaryModel);
        AppUtils.template2File(outputFilePath, false, "templates/html/report/customer/customer_invoice_detail/customer_invoice_detail.ftl", data);
    }

    @Override
    public void renderCustomerInvoiceDetailXlsFile(CustomerInvoiceDetailFilter filter, String outPutFilePath) throws Exception {
        List<CustomerInvoiceDetailVo> customerInvoiceDetailVos = service.getInvoiceDetailReport(filter);
        List<CustomerInvoiceDetailModel> customerInvoiceDetailModels = ModelUtils.createListModelFromVo(customerInvoiceDetailVos, CustomerInvoiceDetailModel.class);
        CustomerInvoiceDetailVo invoiceDetailSummaryVo = service.sumInvoiceDetailReport(filter);
        CustomerInvoiceDetailModel invoiceDetailSummaryModel = ModelUtils.createModelFromVo(invoiceDetailSummaryVo, CustomerInvoiceDetailModel.class);
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL)));
        data.putVar("customerList", customerInvoiceDetailModels);
        data.putVar("summary", invoiceDetailSummaryModel);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/report/customer/customer_invoice_detail/customer_invoice_detail.xls", data);
    }

    @Override
    public void renderCustomerCreditNoteDetailHtmlFile(CustomerCreditNoteDetailFilter filter, String outputFilePath, String realPath) throws Exception {
        List<CustomerCreditNoteDetailVo> creditNoteDetailVos = service.getCreditNoteDetailReport(filter);
        List<CustomerCreditNoteDetailModel> creditNoteDetailModels = ModelUtils.createListModelFromVo(creditNoteDetailVos, CustomerCreditNoteDetailModel.class);
        CustomerCreditNoteDetailVo creditNoteDetailSummaryVo = service.sumCreditNoteDetailReport(filter);
        CustomerCreditNoteDetailModel creditNoteDetailSummaryModel = ModelUtils.createModelFromVo(creditNoteDetailSummaryVo, CustomerCreditNoteDetailModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("customerList", creditNoteDetailModels);
        data.put("summary", creditNoteDetailSummaryModel);
        AppUtils.template2File(outputFilePath, false, "templates/html/report/customer/customer_credit_note_detail_report/customer_credit_note_detail_report.ftl", data);
    }

    @Override
    public void renderCustomerCreditNoteDetailXlsFile(CustomerCreditNoteDetailFilter filter, String outPutFilePath) throws Exception {
        List<CustomerCreditNoteDetailVo> creditNoteDetailVos = service.getCreditNoteDetailReport(filter);
        List<CustomerCreditNoteDetailModel> creditNoteDetailModels = ModelUtils.createListModelFromVo(creditNoteDetailVos, CustomerCreditNoteDetailModel.class);
        CustomerCreditNoteDetailVo creditNoteDetailSummaryVo = service.sumCreditNoteDetailReport(filter);
        CustomerCreditNoteDetailModel creditNoteDetailSummaryModel = ModelUtils.createModelFromVo(creditNoteDetailSummaryVo, CustomerCreditNoteDetailModel.class);
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL)));
        data.putVar("customerList", creditNoteDetailModels);
        data.putVar("summary", creditNoteDetailSummaryModel);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/report/customer/customer_credit_note_detail_report/customer_credit_note_detail_report.xls", data);
    }
}
