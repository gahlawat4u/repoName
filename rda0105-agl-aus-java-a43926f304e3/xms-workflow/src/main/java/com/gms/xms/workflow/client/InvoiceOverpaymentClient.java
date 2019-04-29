package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.OverpaymentDao;
import com.gms.xms.txndb.vo.InvoicePaymentVo;
import com.gms.xms.txndb.vo.OverpaymentVo;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoFilter;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoVo;
import com.gms.xms.workflow.client.integration.request.InvoiceOverpaymentRequest;
import com.gms.xms.workflow.client.integration.response.InvoiceOverpaymentResponse;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.helper.LocalizationHelper;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.math.NumberUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Posted from InvoiceOverpaymentClient
 * <p>
 * Author DatTV Date Apr 27, 2015 10:40:14 AM
 */
public class InvoiceOverpaymentClient extends WorkflowBaseClient {

    public InvoiceOverpaymentClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public long countOverpaymentInfoByFilter(OverpaymentInfoFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.OVERPAYMENT_INFO_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-CountOverpaymentInfoByFilter");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            long count = Long.parseLong(context.get(Attributes.OVERPAYMENT_INFO_RECORD_COUNT_RESULT));
            return count;
        } else {
            throw new Exception(LocalizationHelper.getInstance().getLocalization(this.getAddInfo(), context.get(Attributes.ERROR_MESSAGE)));
        }
    }

    public InvoiceOverpaymentResponse getOverpaymentInfoByFilter(InvoiceOverpaymentRequest request) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.OVERPAYMENT_INFO_FILTER, GsonUtils.toGson(request.getFilter()));
        context.put(Attributes.WFP_NAME, "Wfl-GetOverpaymentInfoByFilter");
        context = WorkFlowManager.getInstance().process(context);
        InvoiceOverpaymentResponse response = new InvoiceOverpaymentResponse();
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<OverpaymentInfoVo> overpaymentList = GsonUtils.fromGson(context.get(Attributes.OVERPAYMENT_INFO_LIST_RESULT), new TypeToken<List<OverpaymentInfoVo>>() {
            }.getType());
            response.setOverpaymentList(overpaymentList);
            String total = context.get(Attributes.OVERPAYMENT_INFO_TOTAL_RESULT);
            if (NumberUtils.isNumber(total)) {
                response.setTotalOverpayment(BigDecimal.valueOf(Double.parseDouble(total)));
            } else {
                response.setTotalOverpayment(BigDecimal.ZERO);
            }
        } else {
            throw new Exception(LocalizationHelper.getInstance().getLocalization(this.getAddInfo(), context.get(Attributes.ERROR_MESSAGE)));
        }
        return response;
    }

    public List<OverpaymentVo> getOverpaymentVoByCustCodeAndSource(OverpaymentInfoFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.OVERPAYMENT_INFO_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetOverpaymentVoByFilter");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<OverpaymentVo> overpaymentList = GsonUtils.fromGson(context.get(Attributes.OVERPAYMENT_LIST_RESULT), new TypeToken<List<OverpaymentVo>>() {
            }.getType());
            return overpaymentList;
        } else {
            throw new Exception(LocalizationHelper.getInstance().getLocalization(this.getAddInfo(), context.get(Attributes.ERROR_MESSAGE)));
        }
    }

    public void deleteOverpayment(Map<String, String> context, String cusPaymentId) throws DaoException, Exception {
        OverpaymentDao overpaymentDao = new OverpaymentDao();
        long paymentId = Long.parseLong(cusPaymentId);
        overpaymentDao.delete(context, paymentId);
    }

    public void saveInvoicePayments(List<InvoicePaymentVo> invoiceList, OverpaymentVo overpayment) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.INVOICE_PAYMENT_LIST, GsonUtils.toGson(invoiceList));
        context.put(Attributes.OVERPAYMENT_OBJECT, GsonUtils.toGson(overpayment));
        context.put(Attributes.WFP_NAME, "Wfl-SaveInvoicePayments");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.ERROR.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(LocalizationHelper.getInstance().getLocalization(this.getAddInfo(), context.get(Attributes.ERROR_MESSAGE)));
        }
    }
}