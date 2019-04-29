package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.createairbill;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.dhl.DhlZoneFilter;
import com.gms.xms.txndb.vo.dhl.DhlZoneVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AirbillInfoEditVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ImportBillingUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * File Name: PrepareCreateAirbillInfoTask.java <br/>
 * Author: TANDT <br/>
 * Create Date: 18-03-2016 <br/>
 * Project Name: xms-workflow <br/>
 * package Name:
 * com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.createairbill
 * <br/>
 */
public class PrepareCreateAirbillInfoTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareCreateAirbillInfoTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            AirbillInfoEditVo airbillInfoEditVo = context.get(Attributes.AIRBILL_INFO_VO);
            EditAirbillResultVo createAirbillResult = new EditAirbillResultVo();

            AddressVo senderAddress = new AddressVo();
            senderAddress.setCountry(Long.parseLong(String.valueOf(airbillInfoEditVo.getOriginId())));
            senderAddress.setCity(airbillInfoEditVo.getOriginCity());

            AddressVo receiverAddress = new AddressVo();
            receiverAddress.setCountry(Long.parseLong(String.valueOf(airbillInfoEditVo.getDestinationId())));
            receiverAddress.setCity(airbillInfoEditVo.getDestinationCity());

            ShipmentBillingVo shipmentBilling = new ShipmentBillingVo();
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(airbillInfoEditVo.getAirbillNumber());

            String serviceCode = airbillInfoEditVo.getServiceCode();
            String[] serviceCodeData = serviceCode.split(",");
            Integer billingShipmentTypeId = Integer.parseInt(serviceCodeData[0]);
            Integer billingContents = Integer.parseInt(serviceCodeData[1]);
            Boolean billingBound = Boolean.parseBoolean(serviceCodeData[2]);
            Long carrier = Long.parseLong(serviceCodeData[3]);

            String isInbound = "";
            String isContent = "";
            if (billingBound) {
                isInbound = " (Inbound)";
            }
            if (billingContents == 0) {
                if (airbillInfoEditVo.getServiceId() == 40 || airbillInfoEditVo.getServiceId() == 51) {
                    isContent = " Env";
                } else {
                    isContent = " Doc";
                }
            } else if (billingContents == 2) {
                isContent = " Pak";
            } else if (billingContents == 1 || billingContents == -1) {
                isContent = "";
            }

            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(billingShipmentTypeId);
            String description = "";
            String displayDescript = "";
            if (shipmentTypeVo != null) {
                description = shipmentTypeVo.getShipmentTypeName();
                displayDescript = description + isContent + isInbound;
            }

            if (StringUtils.isEmpty(airbillInfoEditVo.getZone())) {
                airbillInfoEditVo.setZone(this.getZoneFromAirbillInfo(airbillInfoEditVo));
            }
            airbillInfoEditVo.setTaxAmount(this.getTaxAmountFromAirbillInfo(airbillInfoEditVo));

            String packageFlag = ImportBillingUtils.getPackageFlag(isContent, Long.valueOf(airbillInfoEditVo.getServiceId()));
            shipmentBilling.setPackageFlag(packageFlag);
            shipmentBilling.setPaid(0L);
            shipmentBilling.setAirbillNumber(airbillInfoEditVo.getAirbillNumber());
            shipmentBilling.setDescription(description);
            shipmentBilling.setDisplayDescription(displayDescript);
            shipmentBilling.setBillingShipmentTypeId(billingShipmentTypeId);
            shipmentBilling.setCarrier(carrier);
            shipmentBilling.setBillingContents(Boolean.parseBoolean(String.valueOf(billingContents)));
            shipmentBilling.setBillingBound(billingBound);
            shipmentBilling.setBillingFreightClass(airbillInfoEditVo.getFreightClass());
            shipmentBilling.setBillingReceivedBy(airbillInfoEditVo.getReceivedBy());
            shipmentBilling.setBillingReceivedDate(airbillInfoEditVo.getReceivedDate());
            shipmentBilling.setBillingReference2(airbillInfoEditVo.getReference2());
            shipmentBilling.setShipperReference(airbillInfoEditVo.getReference());
            shipmentBilling.setBillingReference3(airbillInfoEditVo.getReference3());
            shipmentBilling.setBillingReweightWeight(airbillInfoEditVo.getReweightWeight());
            if (airbillInfoEditVo.getWeight() == null) {
                shipmentBilling.setWeight(0F);
            } else {
                shipmentBilling.setWeight(Float.parseFloat(String.valueOf(airbillInfoEditVo.getWeight())));
            }
            shipmentBilling.setWeightUnit("kg");

            shipmentBilling.setShipDate(airbillInfoEditVo.getShipmentDate());
            shipmentBilling.setBillActualDimension(airbillInfoEditVo.getActualDimensions());
            shipmentBilling.setOriginCountryId(Long.parseLong(String.valueOf(airbillInfoEditVo.getOriginId())));
            shipmentBilling.setServiceAreaCodeOrigin(airbillInfoEditVo.getOriginCity());
            shipmentBilling.setDestinationCountryId(Long.parseLong(String.valueOf(airbillInfoEditVo.getDestinationId())));
            shipmentBilling.setServiceAreaCodeDestination(airbillInfoEditVo.getDestinationCity());
            shipmentBilling.setZone(airbillInfoEditVo.getZone());
            if (airbillInfoEditVo.getNoOfPieces() == null) {
                shipmentBilling.setPal(1);
            } else {
                shipmentBilling.setPal(airbillInfoEditVo.getNoOfPieces());
            }
            if (airbillInfoEditVo.getTaxCode() == null) {
                shipmentBilling.setTaxCode("");
            }
            shipmentBilling.setBillingType(0);
            shipmentBilling.setBillingAccount("");
            shipmentBilling.setBillToAccount("");
            shipmentBilling.setDownloadFileId(0L);
            shipmentBilling.setBillingReweightWeight(0);

            Date currentDate = new Date();
            shipmentBilling.setImportDate(currentDate);
            shipmentBilling.setIsBaseCharge(true);
            shipmentBilling.setAccessorialCount(0);

            Double tax = (airbillInfoEditVo.getTax() == null) ? 0D : airbillInfoEditVo.getTax();
            Double customerCost = (airbillInfoEditVo.getCalculatedCharge() == null) ? 0D : airbillInfoEditVo.getCalculatedCharge();
            Double customerTaxAmount = customerCost * tax / 100;
            Double carrierCost = (airbillInfoEditVo.getCarrierBaseCharge() == null) ? 0D : airbillInfoEditVo.getCarrierBaseCharge();
            Double carrierTaxPercent = (airbillInfoEditVo.getCarrierTaxPercent() == null) ? 0D : airbillInfoEditVo.getCarrierTaxPercent();
            Double taxAmount = carrierCost * carrierTaxPercent / 100;
            Double franchiseCost = (airbillInfoEditVo.getFranchiseCost() == null) ? 0D : airbillInfoEditVo.getFranchiseCost();
            Double franchiseTaxAmount = franchiseCost * carrierTaxPercent / 100;

            shipmentBilling.setCarrierCost(carrierCost);
            shipmentBilling.setCalculatedCarrierCost(carrierCost);
            shipmentBilling.setTaxAmount(taxAmount);
            shipmentBilling.setCarrierTaxPercent(carrierTaxPercent);
            shipmentBilling.setFranchiseCost(franchiseCost);
            shipmentBilling.setCalculatedFranchiseCost(franchiseCost);
            shipmentBilling.setFranchiseTaxAmount(franchiseTaxAmount);
            shipmentBilling.setCustomerCost(customerCost);
            shipmentBilling.setCustomerTaxAmount(customerTaxAmount);
            shipmentBilling.setCustomerTaxPercent(tax);
            shipmentBilling.setGstPercent(tax);
            shipmentBilling.setOldCarrierCost(0D);
            shipmentBilling.setOldTaxAmount(0D);
            shipmentBilling.setOldTotalAccessorialCount(0);

            shipmentBilling.setInsuranceDiscountAmount(0D);
            shipmentBilling.setInsuranceAmount(0D);
            shipmentBilling.setInsuranceTaxAmount(0D);
            shipmentBilling.setRequireCalculate(true);

            InvoiceDao invoiceDao = new InvoiceDao();
            InvoiceVo invoiceVo = invoiceDao.selectById(context.getLong(Attributes.INVOICE_ID));
            if (invoiceVo != null) {
                shipmentBilling.setInvoiceDate(invoiceVo.getInvoiceDate());
            }

            createAirbillResult.setSenderAddress(senderAddress);
            createAirbillResult.setReceiverAddress(receiverAddress);
            createAirbillResult.setShipmentBilling(shipmentBilling);
            context.put(Attributes.CREATE_AIRBILL_RESULT, createAirbillResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    public String getZoneFromAirbillInfo(AirbillInfoEditVo airbillInfoEditVo) throws Exception {
        String serviceCode = airbillInfoEditVo.getServiceCode();
        String[] serviceCodeData = serviceCode.split(",");
        Integer billingShipmentTypeId = Integer.parseInt(serviceCodeData[0]);
        Boolean billingBound = Boolean.parseBoolean(serviceCodeData[2]);

        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        ShipmentTypeVo shipmentType = new ShipmentTypeVo();
        shipmentType = shipmentTypeDao.selectById(billingShipmentTypeId);

        if (!billingBound) {
            if (shipmentType.getServiceCode().equals("EUROPLUS")) {
                CountryDao countryDao = new CountryDao();
                CountryVo countryVo = new CountryVo();
                countryVo = countryDao.getCountryById(Long.parseLong(String.valueOf(airbillInfoEditVo.getOriginId())));
                return countryVo.getCountryCode();
            } else {
                DhlZoneDao dhlZoneDao = new DhlZoneDao();
                DhlZoneFilter dhlZoneFilter = new DhlZoneFilter();
                dhlZoneFilter.setCityName(airbillInfoEditVo.getOriginCity());
                dhlZoneFilter.setCountryId(String.valueOf(airbillInfoEditVo.getOriginId()));
                String detaulCountryId = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
                AddressVo senderAddress = new AddressVo();
                AddressDao addressDao = new AddressDao();
                if (airbillInfoEditVo.getSenderAddressId() == null) {
                    return "1";
                }
                senderAddress = addressDao.selectById(airbillInfoEditVo.getSenderAddressId());
                dhlZoneFilter.setDefaultCountryId(detaulCountryId);
                dhlZoneFilter.setPostalCode(senderAddress.getPostalCode());
                dhlZoneFilter.setStateCode(senderAddress.getState());
                DhlZoneVo dhlZoneVo = dhlZoneDao.getDhlZoneByFilter(dhlZoneFilter);
                return dhlZoneVo.getDhlApZone();
            }
        } else {
            if (shipmentType.getServiceCode().equals("EUROPLUS")) {
                CountryDao countryDao = new CountryDao();
                CountryVo countryVo = new CountryVo();
                countryVo = countryDao.getCountryById(Long.parseLong(String.valueOf(airbillInfoEditVo.getDestinationId())));
                return countryVo.getCountryCode();
            } else {
                DhlZoneDao dhlZoneDao = new DhlZoneDao();
                DhlZoneFilter dhlZoneFilter = new DhlZoneFilter();
                dhlZoneFilter.setCityName(airbillInfoEditVo.getDestinationCity());
                dhlZoneFilter.setCountryId(String.valueOf(airbillInfoEditVo.getDestinationId()));
                String detaulCountryId = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
                AddressVo reveiverAddress = new AddressVo();
                AddressDao addressDao = new AddressDao();
                if (airbillInfoEditVo.getReceiverAddressId() == null) {
                    return "1";
                }
                reveiverAddress = addressDao.selectById(airbillInfoEditVo.getReceiverAddressId());
                dhlZoneFilter.setDefaultCountryId(detaulCountryId);
                dhlZoneFilter.setPostalCode(reveiverAddress.getPostalCode());
                dhlZoneFilter.setStateCode(reveiverAddress.getState());
                DhlZoneVo dhlZoneVo = dhlZoneDao.getDhlZoneByFilter(dhlZoneFilter);
                return dhlZoneVo.getDhlApZone();
            }
        }
    }

    public Double getTaxAmountFromAirbillInfo(AirbillInfoEditVo airbillInfoEditVo) throws Exception {
        if (airbillInfoEditVo.getCarrierTaxPercent() == null || airbillInfoEditVo.getCarrierTaxPercent() == 0D) {
            return 0D;
        } else {
            Double taxAmount = 0D;
            taxAmount = (airbillInfoEditVo.getCarrierBaseCharge() * airbillInfoEditVo.getCarrierTaxPercent()) / 100;
            return taxAmount;
        }
    }

}
