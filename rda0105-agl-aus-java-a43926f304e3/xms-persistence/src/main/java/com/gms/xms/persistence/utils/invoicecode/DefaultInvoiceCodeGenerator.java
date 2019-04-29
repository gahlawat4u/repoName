package com.gms.xms.persistence.utils.invoicecode;

import com.gms.xms.common.utils.GenCodeUtils;

import java.util.Date;

/**
 * Posted from Aug 8, 2016 9:54:48 AM
 * <p>
 * Author dattrinh
 */
public class DefaultInvoiceCodeGenerator implements IInvoiceCodeGenerator {

    @Override
    public String generateInvoiceCode(String customerCode, Date invoiceDate) throws Exception {
        return GenCodeUtils.generateInvoiceCode(customerCode, invoiceDate);
    }
}
