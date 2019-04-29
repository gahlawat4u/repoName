package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.createairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name: PrepareAccessorialTask.java <br/>
 * Author: TANDT <br/>
 * Create Date: 18-03-2016 <br/>
 * Project Name: xms-workflow <br/>
 * package Name:
 * com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.createairbill
 * <br/>
 */
public class PrepareAccessorialTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareAccessorialTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            EditAirbillResultVo createAirbillResult = context.get(Attributes.CREATE_AIRBILL_RESULT);
            Integer adminLevel = context.getInt(Attributes.ADMIN_LEVEL);
            ShipmentBillingVo shipmentBillingBaseVo = createAirbillResult.getShipmentBilling();
            List<AccessorialInfoVo> accessorialInfos = context.get(Attributes.ACCESSORIAL_INFO_VO);
            List<ShipmentBillingVo> listShipmentBilling = new ArrayList<ShipmentBillingVo>();
            int count = 0;
            for (AccessorialInfoVo accessorialInfoVo : accessorialInfos) {
                if (!accessorialInfoVo.getDescription().equals("")) {
                    ShipmentBillingVo shipmentBillingVo = new ShipmentBillingVo();
                    BeanUtils.copyProperties(shipmentBillingVo, shipmentBillingBaseVo);
                    shipmentBillingVo.setDescription(accessorialInfoVo.getDescription());
                    shipmentBillingVo.setDisplayDescription(accessorialInfoVo.getDescription());

                    Double tax = (accessorialInfoVo.getCustomerTaxPercent() == null) ? 0D : accessorialInfoVo.getCustomerTaxPercent();
                    Double customerCost = (accessorialInfoVo.getCustomerCost() == null) ? 0D : accessorialInfoVo.getCustomerCost();
                    Double customerTaxAmount = customerCost * tax / 100;
                    Double franchiseCost = (accessorialInfoVo.getFranchiseCost() == null) ? 0D : accessorialInfoVo.getFranchiseCost();
                    Double carrierCost = 0D;
                    if (adminLevel < 3) {
                        carrierCost = (accessorialInfoVo.getCarrierCost() == null) ? 0D : accessorialInfoVo.getCarrierCost();
                    } else {
                        carrierCost = (accessorialInfoVo.getCarrierCost() != null && accessorialInfoVo.getCarrierCost() != 0) ? accessorialInfoVo.getCarrierCost() : franchiseCost;
                    }

                    shipmentBillingVo.setCarrierCost(carrierCost);
                    shipmentBillingVo.setCalculatedCarrierCost(carrierCost);
                    shipmentBillingVo.setTaxAmount(0D);
                    shipmentBillingVo.setCarrierTaxPercent(0D);
                    shipmentBillingVo.setFranchiseCost(franchiseCost);
                    shipmentBillingVo.setCalculatedFranchiseCost(franchiseCost);
                    shipmentBillingVo.setFranchiseTaxAmount(0D);
                    shipmentBillingVo.setCustomerCost(customerCost);
                    shipmentBillingVo.setCustomerTaxAmount(customerTaxAmount);
                    shipmentBillingVo.setCustomerTaxPercent(tax);
                    shipmentBillingVo.setGstPercent(tax);
                    if (accessorialInfoVo.getRequireCalculate() == null) {
                        shipmentBillingVo.setRequireCalculate(false);
                    } else {
                        shipmentBillingVo.setRequireCalculate(accessorialInfoVo.getRequireCalculate());
                    }

                    shipmentBillingVo.setIsBaseCharge(false);
                    shipmentBillingVo.setAccessorialCount(++count);
                    listShipmentBilling.add(shipmentBillingVo);
                }
            }
            createAirbillResult.setShipmentBilling(shipmentBillingBaseVo);
            if (listShipmentBilling.size() > 0) {
                createAirbillResult.setShipmentBillings(listShipmentBilling);
            }
            context.put(Attributes.CREATE_AIRBILL_RESULT, createAirbillResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
