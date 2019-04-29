package com.gms.xms.workflow.task2.admin.ratesheet.viewbyairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.txndb.vo.airbill.ShipmentBillingInfoVo;
import com.gms.xms.workflow.core2.Task2;

/**
 * Posted from May 16, 2016 3:45:33 PM
 * <p>
 * Author dattrinh
 */
public class GetMinimumBaseChargeTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentBillingInfoVo billingInfoVo = context.get(Attributes.SHIPMENT_BILLING_VO);
            // Get customer code.
            String customerCode = billingInfoVo.getCustomerCode();
            CustomerDao dao = new CustomerDao();
            Double minimumBaseCharge = dao.getCustomerBaseChargeByCode(customerCode);
            // Put into the context.
            context.put(Attributes.MINIMUM_BASE_CHARGE, minimumBaseCharge);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
