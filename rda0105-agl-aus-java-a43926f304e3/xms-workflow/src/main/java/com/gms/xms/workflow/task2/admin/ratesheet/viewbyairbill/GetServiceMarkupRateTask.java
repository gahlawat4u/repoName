package com.gms.xms.workflow.task2.admin.ratesheet.viewbyairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.admin.ratesheets.ServiceMarkupRateFilter;
import com.gms.xms.persistence.dao.admin.ServiceMarkupRateDao;
import com.gms.xms.txndb.vo.airbill.ShipmentBillingInfoVo;
import com.gms.xms.workflow.core2.Task2;

/**
 * Posted from May 16, 2016 3:45:33 PM
 * <p>
 * Author dattrinh
 */
public class GetServiceMarkupRateTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentBillingInfoVo billingInfoVo = context.get(Attributes.SHIPMENT_BILLING_VO);
            // Get customer code.
            String customerCode = billingInfoVo.getCustomerCode();
            boolean isCustomer = false;
            if (customerCode.length() == 8) {
                if ("00001".equalsIgnoreCase(customerCode.substring(3, customerCode.length()))) {
                    isCustomer = false;
                } else {
                    isCustomer = true;
                }
            } else {
                isCustomer = true;
            }
            // Get service rate.
            Double serviceMarkupRate = null;
            ServiceMarkupRateDao markupRateDao = new ServiceMarkupRateDao();
            ServiceMarkupRateFilter filter = new ServiceMarkupRateFilter();
            filter.setServiceId(billingInfoVo.getServiceId());
            if (isCustomer) {
                filter.setCode(customerCode);
                serviceMarkupRate = markupRateDao.getServiceMarkupRateByCustomerCode(filter);
            } else {
                filter.setCode(customerCode.substring(0, 3));
                serviceMarkupRate = markupRateDao.getServiceMarkupRateByFranchiseCode(filter);
            }
            if (serviceMarkupRate == null) {
                serviceMarkupRate = 0.0;
            }
            // Put into the context.
            context.put(Attributes.SERVICE_MARKUP_RATE, serviceMarkupRate);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
