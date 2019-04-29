package com.gms.xms.workflow.task2.admin.ratesheet.viewbyairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.admin.customer.baserate.ManageCustBaseRateDao;
import com.gms.xms.txndb.vo.admin.customer.baserate.ServiceTypeVo;
import com.gms.xms.txndb.vo.airbill.ShipmentBillingInfoVo;
import com.gms.xms.workflow.core2.Task2;

/**
 * Posted from May 16, 2016 3:45:23 PM
 * <p>
 * Author dattrinh
 */
public class GetShipmentTypeInfoTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentBillingInfoVo billingInfoVo = context.get(Attributes.SHIPMENT_BILLING_VO);
            // Create filter to get shipment type info.
            ServiceTypeVo filter = new ServiceTypeVo();
            filter.setShipmentTypeId(billingInfoVo.getShipmentTypeId());
            filter.setContent(billingInfoVo.getContent());
            filter.setBound(billingInfoVo.getBound());
            // Get shipment type info.
            ManageCustBaseRateDao baseRateDao = new ManageCustBaseRateDao();
            ServiceTypeVo serviceTypeVo = baseRateDao.getServiceTypeByShipmentTypeId(filter);
            // Put into the context.
            context.put(Attributes.SERVICE_TYPE_VO, serviceTypeVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
