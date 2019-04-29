package com.gms.xms.workflow.task2.admin.ratesheet.viewbyairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.admin.customer.baserate.ManageCustBaseRateDao;
import com.gms.xms.txndb.vo.admin.customer.baserate.CustBaseRateDetailByFilter;
import com.gms.xms.txndb.vo.admin.customer.baserate.CustBaseRateDetailVo;
import com.gms.xms.txndb.vo.admin.customer.baserate.CustBaseRateVo;
import com.gms.xms.txndb.vo.admin.customer.baserate.ServiceTypeVo;
import com.gms.xms.txndb.vo.airbill.ShipmentBillingInfoVo;
import com.gms.xms.workflow.core2.Task2;

import java.util.List;

/**
 * Posted from May 16, 2016 3:45:33 PM
 * <p>
 * Author dattrinh
 */
public class GetCustomerBaseRateTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentBillingInfoVo billingInfoVo = context.get(Attributes.SHIPMENT_BILLING_VO);
            ServiceTypeVo serviceTypeVo = context.get(Attributes.SERVICE_TYPE_VO);
            // Get customer base rate.
            ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
            CustBaseRateVo baseRateFilter = new CustBaseRateVo();
            baseRateFilter.setCustomerCode(billingInfoVo.getCustomerCode());
            baseRateFilter.setShipmentTypeId(serviceTypeVo.getShipmentTypeId());
            baseRateFilter.setContent(serviceTypeVo.getContent() == -1 ? 0 : serviceTypeVo.getContent());
            baseRateFilter.setBound(serviceTypeVo.getBound());
            List<CustBaseRateVo> baseRateVos = dao.getCustBaseRateByFilter(baseRateFilter);
            if (baseRateVos != null && baseRateVos.size() > 0) {
                for (CustBaseRateVo custBaseRateVo : baseRateVos) {
                    if (custBaseRateVo.getZoneCheck()) {
                        // Load customer base rate detail.
                        CustBaseRateDetailByFilter filter = new CustBaseRateDetailByFilter();
                        filter.setCustomerBaseRateId(custBaseRateVo.getCustomerBaseRateId());
                        filter.setZones(serviceTypeVo.getZones());
                        List<CustBaseRateDetailVo> baseRateDetailVos = dao.getCustBaseRateDetailByFilter(filter);
                        custBaseRateVo.setCustBaseRateDetails(baseRateDetailVos);
                    }
                }
            }
            serviceTypeVo.setCustBaseRates(baseRateVos);
            // Update into the context.
            context.put(Attributes.SERVICE_TYPE_VO, serviceTypeVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
