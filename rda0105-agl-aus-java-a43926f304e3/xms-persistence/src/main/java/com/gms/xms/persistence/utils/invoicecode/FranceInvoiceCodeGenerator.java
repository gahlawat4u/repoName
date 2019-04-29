package com.gms.xms.persistence.utils.invoicecode;

import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.dto.InvoiceNumberingVo;
import com.gms.xms.persistence.dao.InvoiceNumberingDao;

import java.util.Calendar;
import java.util.Date;

/**
 * Posted from Aug 8, 2016 9:54:48 AM
 * <p>
 * Author dattrinh
 */
public class FranceInvoiceCodeGenerator implements IInvoiceCodeGenerator {

    @Override
    public String generateInvoiceCode(String customerCode, Date invoiceDate) throws Exception {
        InvoiceNumberingDao numberingDao = new InvoiceNumberingDao();
        String invoiceCode = GenCodeUtils.generateInvoiceCode(customerCode, invoiceDate);
        boolean isCustomer = customerCode.endsWith("00001") ? false : true;
        // Check if the invoice code exists in the xms_tbl_invoice_numbering
        // table.
        InvoiceNumberingVo numberingVo = numberingDao.getByInvoiceCode(invoiceCode);
        if (numberingVo != null) {
            // Get current counter and re-generate invoice code.
            if (isCustomer) {
                return GenCodeUtils.generateFRInvoiceCode(customerCode, invoiceDate, numberingVo.getCurrentUniqueNumber());
            } else {
                return GenCodeUtils.generateFRInvoiceCode4Printable(customerCode.substring(0, 4), invoiceDate, numberingVo.getCurrentUniqueNumber());
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(invoiceDate);
            int year = calendar.get(Calendar.YEAR) % 100;
            int month = calendar.get(Calendar.MONTH) + 1;
            InvoiceNumberingVo filter = new InvoiceNumberingVo();
            filter.setMonth(month);
            filter.setYear(year);
            filter.setFranchiseCode(customerCode.substring(0, 3));
            InvoiceNumberingVo invoiceNumberingVo = numberingDao.selectByFilter(filter);
            // Get new counter.
            int currentUniqueNumber;
            if (invoiceNumberingVo == null) {
                currentUniqueNumber = 1;
            } else {
                currentUniqueNumber = invoiceNumberingVo.getCurrentUniqueNumber() + 1;
            }
            // Generate new invoice code.
            if (isCustomer) {
                return GenCodeUtils.generateFRInvoiceCode(customerCode, invoiceDate, currentUniqueNumber);
            } else {
                return GenCodeUtils.generateFRInvoiceCode4Printable(customerCode.substring(0, 4), invoiceDate, currentUniqueNumber);
            }
        }
    }
}