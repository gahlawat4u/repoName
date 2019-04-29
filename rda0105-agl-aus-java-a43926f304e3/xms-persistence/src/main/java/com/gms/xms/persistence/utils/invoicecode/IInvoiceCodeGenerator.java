package com.gms.xms.persistence.utils.invoicecode;

import java.util.Date;

/**
 * Posted from Aug 8, 2016 9:53:18 AM
 * <p>
 * Author dattrinh
 */
public interface IInvoiceCodeGenerator {
    String generateInvoiceCode(String customerCode, Date invoiceDate) throws Exception;
}
