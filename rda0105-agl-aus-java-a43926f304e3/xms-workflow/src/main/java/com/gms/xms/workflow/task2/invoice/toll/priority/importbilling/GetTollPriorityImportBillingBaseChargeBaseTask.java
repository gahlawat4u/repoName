package com.gms.xms.workflow.task2.invoice.toll.priority.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.webship.TollPriorityRateSheetDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.toll.TollPriorityRateSheetFilter;
import com.gms.xms.txndb.vo.webship.toll.TollPriorityRateSheetVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from May 20, 2016 10:35:41 AM
 * <p>
 * Author huynd
 */
public class GetTollPriorityImportBillingBaseChargeBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollPriorityImportBillingBaseChargeBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            SaveImportBillingVo saveImportBillingVo = context.get(Attributes.IMPORT_BILLING_VO);
            ShipmentBillingVo shipmentBilling = saveImportBillingVo.getBillingBaseCharge();
            Long customerCode = context.get(Attributes.CUSTOMER_CODE);
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ShipmentTypeFilter shipmentTypeFilter = new ShipmentTypeFilter();
            shipmentTypeFilter.setShipmentTypeName(shipmentBilling.getDescription());
            shipmentTypeFilter.setServiceId(Integer.valueOf(shipmentBilling.getCarrier().toString()));
            ShipmentTypeVo shipmentType = shipmentTypeDao.selectByShipmentTypeNameAndCarrier(shipmentTypeFilter);

            Double rowName = Math.ceil(shipmentBilling.getWeight());
            Double cRate = 0D;
            Double ncRate = 0D;
            // Minium 1 KG
            if (rowName < 1) {
                rowName = 1D;
            }
            Integer shipmentTypeId = shipmentType.getShipmentTypeId();
            if ((shipmentTypeId == 175 && rowName > 1) || (shipmentTypeId == 176 && rowName > 3) || (shipmentTypeId == 177 && rowName > 5)) {
                ShipmentBillingVo billingCost = new ShipmentBillingVo();
                billingCost.setCarrierCost(shipmentBilling.getCarrierCost());
                billingCost.setFranchiseCost(shipmentBilling.getFranchiseCost());
                billingCost.setCustomerCost(0D);
                billingCost.setCustomerTaxPercent(shipmentBilling.getCustomerTaxPercent());
                billingCost.setCustomerTaxAmount(0D);
                context.put(Attributes.BILLING_COST, billingCost);
                return true;
            }

            String senderZoneCode = context.getString(Attributes.SENDER_ZONE_CODE);
            String receiverZoneCode = context.getString(Attributes.RECEIVER_ZONE_CODE);
            if (senderZoneCode == null || StringUtils.isBlank(senderZoneCode) || receiverZoneCode == null || StringUtils.isBlank(receiverZoneCode)) {
                ShipmentBillingVo billingCost = new ShipmentBillingVo();
                billingCost.setCarrierCost(shipmentBilling.getCarrierCost());
                billingCost.setFranchiseCost(shipmentBilling.getFranchiseCost());
                billingCost.setCustomerCost(shipmentBilling.getCustomerCost());
                billingCost.setCustomerTaxPercent(shipmentBilling.getCustomerTaxPercent());
                billingCost.setCustomerTaxAmount(shipmentBilling.getCustomerTaxAmount());
                context.put(Attributes.BILLING_COST, billingCost);
                return true;
            }

            Double baseCharge = shipmentType.getBasicCharge();
            String[] serviceGroup = shipmentType.getServiceGroup().split("#");

            String serviceCode = !StringUtils.isBlank(serviceGroup[1]) ? serviceGroup[1] : shipmentType.getServiceCode();
            String productCode = !StringUtils.isBlank(serviceGroup[0]) ? serviceGroup[0] : "2";
            if (shipmentType.getPerWeightStatus() == 1) {
                Integer totalPiece = 1;
                if (shipmentTypeId == 176 || shipmentTypeId == 175 || shipmentTypeId == 177) {
                    totalPiece = 1;
                } else {
                    totalPiece = (shipmentBilling.getPal() == null) ? 1 : shipmentBilling.getPal();
                }
                cRate = baseCharge * totalPiece;
            } else {
                TollPriorityRateSheetDao priorityRateSheetDao = new TollPriorityRateSheetDao();
                TollPriorityRateSheetFilter priorityRateSheetFilter = new TollPriorityRateSheetFilter();
                priorityRateSheetFilter.setProduct(productCode);
                priorityRateSheetFilter.setService(serviceCode);
                priorityRateSheetFilter.setZoneFrom(senderZoneCode);
                priorityRateSheetFilter.setZoneTo(receiverZoneCode);
                TollPriorityRateSheetVo priorityRateSheetVo = priorityRateSheetDao.selectTollPriorityRateSheet(priorityRateSheetFilter);
                if (priorityRateSheetVo != null) {
                    Double conRate = (priorityRateSheetVo.getConRate() == null) ? 0D : Double.parseDouble(priorityRateSheetVo.getConRate());
                    Double kgRate = (priorityRateSheetVo.getKgsRate() == null) ? 0D : Double.parseDouble(priorityRateSheetVo.getKgsRate());
                    cRate = conRate + (rowName * kgRate);
                }
            }

            // Franchise markup
            IFranchiseService franchiseService = new FranchiseServiceImp();
            FranchiseVo franchiseVo = franchiseService.getFranchiseInfoByCode(String.valueOf(customerCode).substring(0, 3));
            Double franchiseMarkup = 0D;
            if (franchiseVo.getTollMarkupRate() != null) {
                franchiseMarkup = franchiseVo.getTollMarkupRate();
            }

            Double carrierBaseCharge = shipmentBilling.getCarrierCost();
            // Franchise cost for shipment billing
            Double franchiseCost = carrierBaseCharge + (carrierBaseCharge * franchiseMarkup) / 100;
            franchiseCost = MathUtils.round(franchiseCost, 2);

            // Get customer base rate
            CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
            CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
            customerBaseRateFilter.setShipmentTypeId(shipmentTypeId);
            customerBaseRateFilter.setWeight(rowName);
            customerBaseRateFilter.setCustomerCode(customerCode);
            CustomerBaseRateVo customerBaseRateVo = customerBaseRateDao.selectCustomerBaseRateByCustomerCodeAndShipmentTypeId(customerBaseRateFilter);

            // Get minimum base charge cost
            Double miniumBaseChargePercent = 0D;
            CustomerDao customerDao = new CustomerDao();
            CustomerVo customerVo = new CustomerVo();
            customerVo = customerDao.selectMinimumBaseChargeByCustomerCode(customerCode);
            if (customerVo != null) {
                miniumBaseChargePercent = customerVo.getMinimunBaseCharge();
            }
            Double baseRate = 0D;
            cRate = cRate + (cRate * franchiseMarkup) / 100;
            if (customerBaseRateVo != null) {
                baseRate = customerBaseRateVo.getRate();
                ncRate = ShipmentUtils.calculateCostByBaseRate(customerBaseRateVo.getRateType(), baseRate, miniumBaseChargePercent, cRate, ncRate);
            } else {
                ncRate = cRate;
            }

            cRate = (cRate == 0) ? shipmentBilling.getCarrierCost() : MathUtils.round(cRate, 2);
            ncRate = (ncRate == 0) ? shipmentBilling.getCustomerCost() : MathUtils.round(ncRate, 2);

            // Carrier cost for shipment billing
            Double carrierCost = cRate;
            // Customer cost for shipment billing
            Double customerCost = ncRate;
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
            log.error(e);
            return false;
        }

        return true;
    }
}
