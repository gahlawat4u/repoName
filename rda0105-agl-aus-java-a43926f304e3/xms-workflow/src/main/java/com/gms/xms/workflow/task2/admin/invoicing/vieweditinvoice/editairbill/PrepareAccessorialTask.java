package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.editairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from GetListAccessorialTask
 * <p>
 * Author TANDT
 */
public class PrepareAccessorialTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareAccessorialTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            EditAirbillResultVo editAirbillResult = context.get(Attributes.EDIT_AIRBILL_RESULT);
            Integer adminLevel = context.getInt(Attributes.ADMIN_LEVEL);
            ShipmentBillingVo shipmentBillingBaseVo = editAirbillResult.getShipmentBilling();
            List<AccessorialInfoVo> accessorialInfos = context.get(Attributes.ACCESSORIAL_INFO_VO);
            List<ShipmentBillingVo> listShipmentBilling = new ArrayList<ShipmentBillingVo>();
            int count = 0;
            for (AccessorialInfoVo accessorialInfoVo : accessorialInfos) {
                if (!StringUtils.isBlank(accessorialInfoVo.getDescription())) {
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

                    // Calculate tax
                    Double franchiseTaxAmount = 0d;
                    try {
                        franchiseTaxAmount = franchiseCost * tax / 100;
                    } catch (Exception e) {
                    }

                    shipmentBillingVo.setCarrierCost(carrierCost);
                    shipmentBillingVo.setCalculatedCarrierCost(carrierCost);
                    shipmentBillingVo.setTaxAmount(0D);
                    shipmentBillingVo.setCarrierTaxPercent(0D);
                    shipmentBillingVo.setFranchiseTaxAmount(franchiseTaxAmount);
                    shipmentBillingVo.setFranchiseCost(franchiseCost);
                    shipmentBillingVo.setCalculatedFranchiseCost(franchiseCost);
                    shipmentBillingVo.setCustomerCost(customerCost);
                    shipmentBillingVo.setCustomerTaxAmount(customerTaxAmount);
                    shipmentBillingVo.setCustomerTaxPercent(tax);
                    shipmentBillingVo.setGstPercent(tax);

                    shipmentBillingVo.setRequireCalculate(accessorialInfoVo.getRequireCalculate() == null ? false : accessorialInfoVo.getRequireCalculate());
                    shipmentBillingVo.setIsBaseCharge(false);
                    shipmentBillingVo.setAccessorialCount(++count);
                    listShipmentBilling.add(shipmentBillingVo);
                }
            }
            editAirbillResult.setShipmentBilling(shipmentBillingBaseVo);
            if (listShipmentBilling.size() > 0) {
                editAirbillResult.setShipmentBillings(listShipmentBilling);
            }
            context.put(Attributes.EDIT_AIRBILL_RESULT, editAirbillResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
