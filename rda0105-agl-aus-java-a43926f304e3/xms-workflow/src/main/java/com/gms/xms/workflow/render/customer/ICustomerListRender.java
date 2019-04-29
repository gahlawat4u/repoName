package com.gms.xms.workflow.render.customer;

import com.gms.xms.txndb.vo.CustomerFilter;
import com.gms.xms.txndb.vo.account.customers.SearchCustomerColumnFlagsVo;

/**
 * Posted from ICustomerListRender
 * <p>
 * Author DatTV Dec 24, 2015
 */
public interface ICustomerListRender {
    public void renderSearchCustomerXLSFile(CustomerFilter filter, String outPutFilePath, SearchCustomerColumnFlagsVo columnFlags) throws Exception;

    public void renderCustomerListHtmlFile(CustomerFilter filter, String outputFilePath, String realPath) throws Exception;

    public void renderCustomerListXlsFile(CustomerFilter filter, String outPutFilePath) throws Exception;
}
