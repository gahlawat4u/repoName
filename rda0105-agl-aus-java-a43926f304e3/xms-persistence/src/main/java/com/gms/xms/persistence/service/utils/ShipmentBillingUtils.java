package com.gms.xms.persistence.service.utils;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.filter.account.franchises.MarkupRateFilter;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;

/**
 * Posted from May 30, 2016 10:11:51 AM
 * <p>
 * Author huynd
 */
public class ShipmentBillingUtils {
    protected static Log log = LogFactory.getLog(ShipmentBillingUtils.class);

    public static ShipmentBillingVo recalculateBaseCharge(Long customerCode, ShipmentBillingVo shipmentBilling, IRecalculateCharge iRecalculateCharge) throws Exception {
        ShipmentBillingVo shipmentBillingResult = new ShipmentBillingVo();
        BeanUtils.copyProperties(shipmentBillingResult, shipmentBilling);
        Integer carrierId = Integer.parseInt(shipmentBilling.getCarrier().toString());
        ContextBase2 context = iRecalculateCharge.recalculateBaseCharge(customerCode, shipmentBilling);
        ShipmentBillingVo billingCost = context.get(Attributes.BILLING_COST);
        if (billingCost != null) {
            // Recalculate tax
            Double taxPercent = billingCost.getCustomerTaxPercent();
            Double franchiseTaxAmount = 0d;
            try {
                franchiseTaxAmount = billingCost.getFranchiseCost() * taxPercent / 100;
            } catch (Exception e) {
                log.error(e);
            }
            shipmentBillingResult.setFranchiseTaxAmount(franchiseTaxAmount);
            shipmentBillingResult.setCalculatedCarrierCost(billingCost.getCalculatedCarrierCost() != null ? billingCost.getCalculatedCarrierCost() : 0D);
            shipmentBillingResult.setFranchiseCost(billingCost.getFranchiseCost() != null ? billingCost.getFranchiseCost() : 0D);
            shipmentBillingResult.setCustomerCost(billingCost.getCustomerCost() != null ? billingCost.getCustomerCost() : 0D);
            shipmentBillingResult.setCustomerTaxPercent(billingCost.getCustomerTaxPercent() != null ? billingCost.getCustomerTaxPercent() : 0D);
            shipmentBillingResult.setCustomerTaxAmount(billingCost.getCustomerTaxAmount() != null ? billingCost.getCustomerTaxAmount() : 0D);
        }

        if (carrierId == 1 || carrierId == 15 || carrierId == 52 || carrierId == 59) {
            String zone = context.get(Attributes.ZONE);
            shipmentBillingResult.setZone(zone);
        }
        return shipmentBillingResult;
    }

    public static ShipmentBillingVo recalculateBaseCharge4Import(Long customerCode, ShipmentBillingVo shipmentBilling, IRecalculateCharge iRecalculateCharge) throws Exception {
        ShipmentBillingVo shipmentBillingResult = new ShipmentBillingVo();
        BeanUtils.copyProperties(shipmentBillingResult, shipmentBilling);
        Integer carrierId = Integer.parseInt(shipmentBilling.getCarrier().toString());
        ContextBase2 context = iRecalculateCharge.recalculateBaseCharge(customerCode, shipmentBilling);
        ShipmentBillingVo billingCost = context.get(Attributes.BILLING_COST);
        if (billingCost != null) {
            // Recalculate tax
            Double taxPercent = billingCost.getCustomerTaxPercent();
            Double franchiseTaxAmount = 0d;
            try {
                franchiseTaxAmount = billingCost.getFranchiseCost() * taxPercent / 100;
            } catch (Exception e) {
                log.error(e);
            }
            shipmentBillingResult.setFranchiseTaxAmount(franchiseTaxAmount);
            shipmentBillingResult.setCalculatedCarrierCost(billingCost.getCalculatedCarrierCost() != null ? billingCost.getCalculatedCarrierCost() : 0D);
            shipmentBillingResult.setFranchiseCost(billingCost.getFranchiseCost() != null ? billingCost.getFranchiseCost() : 0D);
            shipmentBillingResult.setCustomerCost(billingCost.getCustomerCost() != null ? billingCost.getCustomerCost() : 0D);
            shipmentBillingResult.setCustomerTaxPercent(billingCost.getCustomerTaxPercent() != null ? billingCost.getCustomerTaxPercent() : 0D);
            shipmentBillingResult.setCustomerTaxAmount(billingCost.getCustomerTaxAmount() != null ? billingCost.getCustomerTaxAmount() : 0D);
        }

        if (carrierId == 1 || carrierId == 15 || carrierId == 52 || carrierId == 59) {
            String zone = context.get(Attributes.ZONE);
            shipmentBillingResult.setZone(zone);
        }
        return shipmentBillingResult;
    }

    public static ShipmentBillingVo recalculateBaseCharge(Long customerCode, SaveImportBillingVo saveImportBillingVo, IRecalculateCharge iRecalculateCharge) throws Exception {
        ShipmentBillingVo shipmentBillingResult = saveImportBillingVo.getBillingBaseCharge();
        String carrierId = String.valueOf(saveImportBillingVo.getBillingBaseCharge().getCarrier());
        ContextBase2 context = iRecalculateCharge.recalculateBaseCharge(customerCode, saveImportBillingVo);
        ShipmentBillingVo billingCost = context.get(Attributes.BILLING_COST);
        if (billingCost != null) {
            shipmentBillingResult.setCalculatedCarrierCost(billingCost.getCalculatedCarrierCost() != null ? billingCost.getCalculatedCarrierCost() : 0d);
            shipmentBillingResult.setFranchiseCost(billingCost.getFranchiseCost() != null ? billingCost.getFranchiseCost() : 0d);
            shipmentBillingResult.setCustomerCost(billingCost.getCustomerCost() != null ? billingCost.getCustomerCost() : 0d);
            shipmentBillingResult.setCustomerTaxPercent(billingCost.getCustomerTaxPercent() != null ? billingCost.getCustomerTaxPercent() : 0d);
            shipmentBillingResult.setCustomerTaxAmount(billingCost.getCustomerTaxAmount() != null ? billingCost.getCustomerTaxAmount() : 0d);
        }

        if (carrierId.equalsIgnoreCase("52") || carrierId.equalsIgnoreCase("59")) {
            String zone = context.get(Attributes.ZONE);
            shipmentBillingResult.setZone(zone);
        }
        return shipmentBillingResult;
    }

    public static ShipmentBillingVo recalculateAccessorialCharge(Long customerCode, ShipmentBillingVo shipmentBillingBaseCharge, ShipmentBillingVo shipmentBilling) throws Exception {
        ShipmentBillingVo shipmentBillingResult = new ShipmentBillingVo();
        BeanUtils.copyProperties(shipmentBillingResult, shipmentBilling);
        FranchiseDao franchiseDao = new FranchiseDao();
        MarkupRateFilter markupRateFilter = new MarkupRateFilter();
        markupRateFilter.setCustomerCode(customerCode);
        Integer carrierId = Integer.valueOf(shipmentBilling.getCarrier().toString());
        if (carrierId != null && carrierId == 54) {
            markupRateFilter.setShipmentTypeId(shipmentBilling.getBillingShipmentTypeId());
        }
        MarkupRateVo markupRateVo = franchiseDao.selectMarkupRateByCustomerCode(markupRateFilter);
        Double franchiseMarkup;

        switch (carrierId) {
            case 1: // DHL International
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getMarkupRate() : 0D;
                break;
            case 15: // DHL Domestic
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getDhlDomMarkupRate() : 0D;
                break;
            case 3: // TNT Domestic
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getTntMarkupRate() : 0D;
                break;
            case 54: // TNT International
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getTntServiceMarkupRate() : 0D;
                break;
            case 52: // Toll Priority
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getTollMarkupRate() : 0D;
                break;
            case 59: // Toll Ipec
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getTollIpecMarkupRate() : 0D;
                break;
            case 72: // Startrack
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getStartrackMarkupRate() : 0D;
                break;
            default:
                franchiseMarkup = 0D;
        }

        SystemSettingDao systemSettingDao = new SystemSettingDao();
        SystemSettingVo internationalTaxAccessorialVo = systemSettingDao.getSystemSettingByName("International Tax Accessorial");
        Double internationalTaxAccessorial = Double.valueOf(internationalTaxAccessorialVo == null ? "0" : internationalTaxAccessorialVo.getSettingValue());
        SystemSettingVo internationalTaxAccessorialListVo = systemSettingDao.getSystemSettingByName("International Tax Accessorial List");
        String internationalTaxAccessorialList = internationalTaxAccessorialListVo == null ? "" : internationalTaxAccessorialListVo.getSettingValue();

        Double customerBaseCharge = shipmentBillingBaseCharge.getCustomerCost();
        Double customerTaxPerCent = (shipmentBilling.getCustomerTaxPercent() == null) ? 0D : shipmentBilling.getCustomerTaxPercent();
        String description = shipmentBilling.getDescription();
        Double customerCost = 0D;
        Integer surchargeType = 0;

        AccessorialFilter accessorialFilter = new AccessorialFilter();
        AccessorialDao accessorialDao = new AccessorialDao();
        accessorialFilter.setCustomerCode(customerCode);
        accessorialFilter.setCarrier(Long.parseLong(carrierId.toString()));
        accessorialFilter.setBaseCharge(customerBaseCharge);
        accessorialFilter.setQuotable(null);
        accessorialFilter.setDescription(description);
        accessorialFilter.setShipmentDate(shipmentBillingResult.getShipDate());
        AccessorialVo accessorial = new AccessorialVo();
        accessorial = accessorialDao.getAccessorialValueByFilterForRecalculate(accessorialFilter);
        if (accessorial != null) {
            customerCost = accessorial.getValue();
            surchargeType = accessorial.getTypeId();
            if (!StringUtils.isBlank(internationalTaxAccessorialList)) {
                customerTaxPerCent = getCustomerTaxPerCentForSpecialAccess(internationalTaxAccessorialList, customerTaxPerCent, accessorial.getAccessorialId(), internationalTaxAccessorial);
            }
        }
        Double carrierAccCharge = shipmentBilling.getCarrierCost();
        // Franchise cost for shipment billing
        Double franchiseCost = 0D;
        if (surchargeType == 1) {
            franchiseCost = carrierAccCharge;
        } else {
            franchiseCost = carrierAccCharge + (carrierAccCharge * franchiseMarkup) / 100;
        }
        franchiseCost = MathUtils.round(franchiseCost, 2);

        shipmentBillingResult.setFranchiseCost(franchiseCost);
        Double franchiseTaxAmount = MathUtils.round(franchiseCost * customerTaxPerCent / 100, 2);
        shipmentBillingResult.setFranchiseTaxAmount(franchiseTaxAmount);

        if (customerCost == 0) {
            customerCost = shipmentBilling.getFranchiseCost();
            if (surchargeType != 1) {
                customerCost = franchiseCost;
            }
        }
        // Customer cost for shipment billing
        shipmentBillingResult.setCustomerTaxPercent(customerTaxPerCent);
        shipmentBillingResult.setCustomerCost(customerCost);
        Double customerTaxAmount = MathUtils.round(customerCost * customerTaxPerCent / 100, 2);
        shipmentBillingResult.setCustomerTaxAmount(customerTaxAmount);
        shipmentBillingResult.setCarrierTaxPercent(customerTaxPerCent);
        Double carrierTaxAmount = MathUtils.round(carrierAccCharge * customerTaxPerCent / 100, 2);
        shipmentBillingResult.setTaxAmount(carrierTaxAmount);
        shipmentBillingResult.setGstPercent(customerTaxPerCent);
        return shipmentBillingResult;
    }

    public static ShipmentBillingVo recalculateAccessorialCharge4Import(Long customerCode, ShipmentBillingVo shipmentBillingBaseCharge, ShipmentBillingVo shipmentBilling) throws Exception {
        ShipmentBillingVo shipmentBillingResult = new ShipmentBillingVo();
        BeanUtils.copyProperties(shipmentBillingResult, shipmentBilling);
        FranchiseDao franchiseDao = new FranchiseDao();
        MarkupRateFilter markupRateFilter = new MarkupRateFilter();
        markupRateFilter.setCustomerCode(customerCode);
        MarkupRateVo markupRateVo = franchiseDao.selectMarkupRateByCustomerCode(markupRateFilter);
        Double franchiseMarkup;
        Integer carrierId = Integer.valueOf(shipmentBilling.getCarrier().toString());
        switch (carrierId) {
            case 1: // DHL International
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getMarkupRate() : 0D;
                break;
            case 15: // DHL Domestic
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getDhlDomMarkupRate() : 0D;
                break;
            case 3: // TNT Domestic
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getTntMarkupRate() : 0D;
                break;
            case 54: // TNT International
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getTntServiceMarkupRate() : 0D;
                break;
            case 52: // Toll Priority
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getTollMarkupRate() : 0D;
                break;
            case 59: // Toll Ipec
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getTollIpecMarkupRate() : 0D;
                break;
            case 72: // Startrack
                franchiseMarkup = (markupRateVo != null) ? markupRateVo.getStartrackMarkupRate() : 0D;
                break;
            default:
                franchiseMarkup = 0D;
        }

        SystemSettingDao systemSettingDao = new SystemSettingDao();
        SystemSettingVo internationalTaxAccessorialVo = systemSettingDao.getSystemSettingByName("International Tax Accessorial");
        Double internationalTaxAccessorial = Double.valueOf(internationalTaxAccessorialVo == null ? "0" : internationalTaxAccessorialVo.getSettingValue());
        SystemSettingVo internationalTaxAccessorialListVo = systemSettingDao.getSystemSettingByName("International Tax Accessorial List");
        String internationalTaxAccessorialList = internationalTaxAccessorialListVo == null ? "" : internationalTaxAccessorialListVo.getSettingValue();

        Double customerBaseCharge = shipmentBillingBaseCharge.getCustomerCost();
        Double customerTaxPerCent = (shipmentBilling.getCustomerTaxPercent() == null) ? 0D : shipmentBilling.getCustomerTaxPercent();
        String description = shipmentBilling.getDescription();
        Double customerCost = 0D;
        Integer surchargeType = 0;

        AccessorialFilter accessorialFilter = new AccessorialFilter();
        AccessorialDao accessorialDao = new AccessorialDao();
        accessorialFilter.setCustomerCode(customerCode);
        accessorialFilter.setCarrier(Long.parseLong(carrierId.toString()));
        accessorialFilter.setBaseCharge(customerBaseCharge);
        accessorialFilter.setQuotable(null);
        accessorialFilter.setDescription(description);
        accessorialFilter.setShipmentDate(shipmentBillingResult.getShipDate());
        AccessorialVo accessorial = new AccessorialVo();
        accessorial = accessorialDao.getAccessorialValueByFilterForRecalculate(accessorialFilter);
        if (accessorial != null) {
            customerCost = accessorial.getValue();
            surchargeType = accessorial.getTypeId();
            if (!StringUtils.isBlank(internationalTaxAccessorialList)) {
                customerTaxPerCent = getCustomerTaxPerCentForSpecialAccess(internationalTaxAccessorialList, customerTaxPerCent, accessorial.getAccessorialId(), internationalTaxAccessorial);
            }
        }
        Double carrierAccCharge = shipmentBilling.getCarrierCost();
        // Franchise cost for shipment billing
        Double franchiseCost = 0D;
        if (surchargeType == 1) {
            franchiseCost = carrierAccCharge;
        } else {
            franchiseCost = carrierAccCharge + (carrierAccCharge * franchiseMarkup) / 100;
        }
        franchiseCost = MathUtils.round(franchiseCost, 2);

        // Recalculate franchise tax amount
        Double franchiseTaxAmount = franchiseCost * customerTaxPerCent / 100;

        shipmentBillingResult.setFranchiseTaxAmount(franchiseTaxAmount);
        shipmentBillingResult.setFranchiseCost(franchiseCost);

        if (customerCost == 0) {
            customerCost = shipmentBilling.getCustomerCost();
            if (surchargeType != 1) {
                customerCost = franchiseCost;
            }
        }
        // Customer cost for shipment billing
        shipmentBillingResult.setCustomerTaxPercent(customerTaxPerCent);
        shipmentBillingResult.setCustomerCost(customerCost);
        Double customerTaxAmount = MathUtils.round(customerCost * customerTaxPerCent / 100, 2);
        shipmentBillingResult.setCustomerTaxAmount(customerTaxAmount);
        shipmentBillingResult.setCarrierTaxPercent(customerTaxPerCent);
        Double carrierTaxAmount = MathUtils.round(carrierAccCharge * customerTaxPerCent / 100, 2);
        shipmentBillingResult.setTaxAmount(carrierTaxAmount);
        shipmentBillingResult.setGstPercent(customerTaxPerCent);
        return shipmentBillingResult;
    }

    private static Double getCustomerTaxPerCentForSpecialAccess(String internationalTaxAccessorialList, Double customerTaxPerCent, Long accessorialId, Double internationalTaxAccessorial) {
        Double result = customerTaxPerCent;
        String[] list = internationalTaxAccessorialList.split("\\,");
        ArrayList<Long> arrayList = new ArrayList<Long>();
        for (String item : list) {
            arrayList.add(Long.valueOf(item.trim()));
        }
        if (arrayList.contains(accessorialId)) {
            result = internationalTaxAccessorial;
        }
        return result;
    }
}
