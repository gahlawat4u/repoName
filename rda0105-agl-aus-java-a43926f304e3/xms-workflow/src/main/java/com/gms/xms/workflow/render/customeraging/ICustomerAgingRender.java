package com.gms.xms.workflow.render.customeraging;

import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingColumnFlagsVo;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;

/**
 * Posted from ICustomerAgingRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface ICustomerAgingRender {
    public void renderHTMLFile(CustomerAgingFilter filter, CustomerAgingColumnFlagsVo columnFlags, String htmlFilePath) throws Exception;

    public void renderXLSFile(CustomerAgingFilter filter, CustomerAgingColumnFlagsVo columnFlags, String xlsFilePath) throws Exception;
}
