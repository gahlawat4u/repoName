package com.gms.xms.workflow.service.report.customer.invoicedetail;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerInvoiceDetailFilter;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerCreditNoteDetailVo;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerInvoiceDetailVo;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.service.BaseService;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerActivationServiceImp.java
 * <p>
 * Author dattrinh 11:58:23 AM
 */
public class CustomerInvoiceDetailServiceImp extends BaseService implements ICustomerInvoiceDetailService {
    public CustomerInvoiceDetailServiceImp(Map<String, String> context) {
        super(context);
    }

    @Override
    public List<CustomerInvoiceDetailVo> getInvoiceDetailReport(CustomerInvoiceDetailFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_INVOICE_DETAIL_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCustomerInvoiceDetailReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<CustomerInvoiceDetailVo> detailVos = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_INVOICE_DETAIL_REPORT_RESULT), new TypeToken<List<CustomerInvoiceDetailVo>>() {
            }.getType());
            return detailVos;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public CustomerInvoiceDetailVo sumInvoiceDetailReport(CustomerInvoiceDetailFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_INVOICE_DETAIL_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-SumCustomerInvoiceDetailReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            CustomerInvoiceDetailVo invoiceDetailSummaryVo = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_INVOICE_DETAIL_SUMMARY_RESULT), CustomerInvoiceDetailVo.class);
            return invoiceDetailSummaryVo;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public long getInvoiceDetailCount(CustomerInvoiceDetailFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_INVOICE_DETAIL_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCustomerInvoiceDetailCount");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            Long recordCount = Long.valueOf(context.get(Attributes.CUSTOMER_INVOICE_DETAIL_RECORD_COUNT_RESULT));
            return recordCount;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public List<CustomerCreditNoteDetailVo> getCreditNoteDetailReport(CustomerCreditNoteDetailFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCustomerCreditNoteDetailReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<CustomerCreditNoteDetailVo> detailVos = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_REPORT_RESULT), new TypeToken<List<CustomerCreditNoteDetailVo>>() {
            }.getType());
            return detailVos;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public CustomerCreditNoteDetailVo sumCreditNoteDetailReport(CustomerCreditNoteDetailFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-SumCustomerCreditNoteDetailReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            CustomerCreditNoteDetailVo creditNoteDetailSummaryVo = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_SUMMARY_RESULT), CustomerCreditNoteDetailVo.class);
            return creditNoteDetailSummaryVo;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public long getCreditNoteDetailCount(CustomerCreditNoteDetailFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCustomerCreditNoteDetailCount");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            Long recordCount = Long.valueOf(context.get(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_RECORD_COUNT_RESULT));
            return recordCount;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }
}
