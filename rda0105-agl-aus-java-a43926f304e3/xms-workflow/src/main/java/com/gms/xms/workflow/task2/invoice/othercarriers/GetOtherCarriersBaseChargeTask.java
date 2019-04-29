package com.gms.xms.workflow.task2.invoice.othercarriers;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.txndb.vo.CustomerBaseRateFilter;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from Sep 22, 2016 3:32:50 PM
 * <p>
 * Author huynd
 */
public class GetOtherCarriersBaseChargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetOtherCarriersBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentBillingVo shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
            Long customerCode = context.get(Attributes.CUSTOMER_CODE);
            Double rowName = Math.ceil(shipmentBilling.getWeight());
            Double cRate = shipmentBilling.getCarrierCost() == null ? 0D : shipmentBilling.getCarrierCost();
            Double ncRate = shipmentBilling.getCustomerCost() == null ? 0D : shipmentBilling.getCustomerCost();

            // Get customer base rate
            CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
            CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
            customerBaseRateFilter.setCustomerCode(customerCode);
            customerBaseRateFilter.setShipmentTypeId(0);
            customerBaseRateFilter.setWeight(rowName);
            customerBaseRateFilter.setCarrierId(shipmentBilling.getCarrier().intValue());
            CustomerBaseRateVo customerBaseRateVo = customerBaseRateDao.selectCustomerBaseRateByCustomerCodeAndShipmentTypeId(customerBaseRateFilter);
            Double baseRate = customerBaseRateVo == null ? 0D : customerBaseRateVo.getRate();
            Integer rateType = customerBaseRateVo == null ? 0 : customerBaseRateVo.getRateType();

            // Get minimum base charge cost
            CustomerDao customerDao = new CustomerDao();
            CustomerVo customerVo = new CustomerVo();
            customerVo = customerDao.selectMinimumBaseChargeByCustomerCode(customerCode);
            Double miniumBaseChargePercent = customerVo == null ? 0D : customerVo.getMinimunBaseCharge();

            if (miniumBaseChargePercent > baseRate) {
                rateType = 2;
            }

            Double carrierBaseCharge = shipmentBilling.getCarrierCost();
            // Franchise cost for shipment billing
            Double franchiseCost = carrierBaseCharge;

            Map<String, Double> rate = ShipmentUtils.calculateBaseCharge(rateType, baseRate, miniumBaseChargePercent, rowName, cRate, ncRate);
            cRate = rate.get("cRate");
            ncRate = rate.get("ncRate");
            // Carrier cost for shipment billing
            Double carrierCost = cRate == 0 ? carrierBaseCharge : cRate;
            // Customer cost for shipment billing
            Double customerCost = ncRate == 0 ? carrierBaseCharge : ncRate;
            // Customer tax percent for shipment billing
            Double customerTaxPercent = shipmentBilling.getCustomerTaxPercent();
            // Customer tax amount for shipment billing
            Double customerTaxAmount = (customerCost * customerTaxPercent) / 100;
            customerTaxAmount = MathUtils.round(customerTaxAmount, 2);

            ShipmentBillingVo billingCost = new ShipmentBillingVo();
            billingCost.setCalculatedCarrierCost(carrierCost);
            billingCost.setFranchiseCost(franchiseCost);
            billingCost.setCustomerCost(customerCost);
            billingCost.setCustomerTaxPercent(customerTaxPercent);
            billingCost.setCustomerTaxAmount(customerTaxAmount);
            context.put(Attributes.BILLING_COST, billingCost);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }

        return true;
    }
}
