package com.gms.xms.workflow.render.searchpayment;

import com.gms.xms.filter.admin.receivables.payments.PaymentFilter;
import com.gms.xms.txndb.vo.admin.receivables.payments.SearchPaymentColumnFlagsVo;

public interface ISearchPaymentRender {
    public void generateHtmlFile(PaymentFilter filter, SearchPaymentColumnFlagsVo columnFlags, String outputFilePath, String realPath) throws Exception;

    public void generateXlsFile(PaymentFilter filter, SearchPaymentColumnFlagsVo columnFlags, String outputFilePath) throws Exception;
}
