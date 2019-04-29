package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.admin.invoicing.ViewEditInvoiceDao;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetInvoiceInfoByIdTask
 * <p>
 * Author TANDT
 */
public class GetInvoiceInfoByIdTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetInvoiceInfoByIdTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get invoice id from the context.
            Long invoiceId = context.getLong(Attributes.INVOICE_ID);
            ViewEditInvoiceDao invoiceInfoDao = new ViewEditInvoiceDao();
            // Get invoice detail info.
            InvoiceInfoVo invoiceInfoVo = invoiceInfoDao.selectInvoiceInfoById(invoiceId);
            // Re-calculate some value.
            // Total amount of GST shipments.
            Double gstTotalAmount = 0.0;
            gstTotalAmount = invoiceInfoVo.getGstTotalCost() + invoiceInfoVo.getGstTotalTax();
            invoiceInfoVo.setGstTotalAmount(gstTotalAmount);
            // Total amount of Non GST shipments.
            Double nonGstTotalAmount = 0.0;
            nonGstTotalAmount = invoiceInfoVo.getNonGstTotalCost() + invoiceInfoVo.getNonGstTotalTax();
            invoiceInfoVo.setNonGstTotalAmount(nonGstTotalAmount);
            // Total amount of the invoice.
            Double totalAmount = 0.0;
            totalAmount = invoiceInfoVo.getTotalCost() + invoiceInfoVo.getTotalTax();
            invoiceInfoVo.setTotalAmount(totalAmount);
            // Remaining due.
            Double remainingDue = 0.0;
            remainingDue = invoiceInfoVo.getTotalAmount() - invoiceInfoVo.getTotalPaid();
            invoiceInfoVo.setRemainingDue(remainingDue);
            // Put the invoice detail info into the context.
            context.put(Attributes.INVOICE_VO, invoiceInfoVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
