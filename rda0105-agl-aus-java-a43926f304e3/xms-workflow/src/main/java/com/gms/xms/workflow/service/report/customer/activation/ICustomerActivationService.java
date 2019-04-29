package com.gms.xms.workflow.service.report.customer.activation;

import com.gms.xms.filter.reports.customer.activation.CustomerActivationFilter;
import com.gms.xms.txndb.vo.reports.customer.activation.CustomerActivationVo;

import java.util.List;

/**
 * Posted from ICustomerActivationService.java
 * <p>
 * Author dattrinh 11:57:02 AM
 */
public interface ICustomerActivationService {
    public List<CustomerActivationVo> getActivationReport(CustomerActivationFilter filter) throws Exception;

    public long getActivationCount(CustomerActivationFilter filter) throws Exception;
}
