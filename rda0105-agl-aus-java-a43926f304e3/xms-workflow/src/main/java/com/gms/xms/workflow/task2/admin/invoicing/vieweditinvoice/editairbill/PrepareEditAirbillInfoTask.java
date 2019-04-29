package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.editairbill;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.ShipmentBillingFilter;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.dhl.DhlZoneFilter;
import com.gms.xms.txndb.vo.dhl.DhlZoneVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AirbillInfoEditVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from PrepareEditAirbillInfoTask
 * <p>
 * Author TANDT
 */
public class PrepareEditAirbillInfoTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareEditAirbillInfoTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            AirbillInfoEditVo airbillInfoEditVo = context.get(Attributes.AIRBILL_INFO_VO);
            EditAirbillResultVo editAirbillResult = new EditAirbillResultVo();

            AddressVo senderAddress = new AddressVo();
            senderAddress.setCountry(Long.parseLong(String.valueOf(airbillInfoEditVo.getOriginId())));
            senderAddress.setCity(airbillInfoEditVo.getOriginCity());
            if (airbillInfoEditVo.getSenderAddressId() != null) {
                senderAddress.setAddressId(airbillInfoEditVo.getSenderAddressId());
            }
            AddressVo receiverAddress = new AddressVo();
            receiverAddress.setCountry(Long.parseLong(String.valueOf(airbillInfoEditVo.getDestinationId())));
            receiverAddress.setCity(airbillInfoEditVo.getDestinationCity());
            if (airbillInfoEditVo.getReceiverAddressId() != null) {
                receiverAddress.setAddressId(airbillInfoEditVo.getReceiverAddressId());
            }
            ShipmentBillingVo shipmentBilling = new ShipmentBillingVo();
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setShipmentId(airbillInfoEditVo.getShipmentId());
            shipmentBillingFilter.setAirbillNumber(airbillInfoEditVo.getAirbillNumber());
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
            shipmentBilling = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);

            String serviceCode = airbillInfoEditVo.getServiceCode();
            String[] serviceCodeData = serviceCode.split(",");
            Integer billingShipmentTypeId = Integer.parseInt(serviceCodeData[0]);
            Integer billingContents = Integer.parseInt(serviceCodeData[1]);
            Integer bound = Integer.valueOf(serviceCodeData[2]);
            Boolean billingBound = bound == 1 ? true : false;
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
            ShipmentTypeVo shipmentType = shipmentTypeDao.selectById(billingShipmentTypeId);
            String descript = "";
            if (shipmentType != null) {
                descript = shipmentType.getShipmentTypeName();
            }

            String displayDescript = "";
            displayDescript = descript + isContent + isInbound;

            if (StringUtils.isEmpty(airbillInfoEditVo.getZone())) {
                airbillInfoEditVo.setZone(this.getZoneFromAirbillInfo(airbillInfoEditVo));
            }
            airbillInfoEditVo.setTaxAmount(this.getTaxAmountFromAirbillInfo(airbillInfoEditVo));

            shipmentBilling.setDescription(descript);
            shipmentBilling.setDisplayDescription(displayDescript);
            shipmentBilling.setBillingShipmentTypeId(billingShipmentTypeId);
            shipmentBilling.setCarrier(carrier);
            shipmentBilling.setBillingContents(billingContents == 1 ? true : false);
            shipmentBilling.setBillingBound(billingBound);
            shipmentBilling.setBillingFreightClass(airbillInfoEditVo.getFreightClass());
            shipmentBilling.setBillingReceivedBy(airbillInfoEditVo.getReceivedBy());
            shipmentBilling.setBillingReceivedDate(airbillInfoEditVo.getReceivedDate());
            shipmentBilling.setBillingReference2(airbillInfoEditVo.getReference2());
            shipmentBilling.setShipperReference(airbillInfoEditVo.getReference());
            shipmentBilling.setBillingReference3(airbillInfoEditVo.getReference3());
            shipmentBilling.setBillingReweightWeight(airbillInfoEditVo.getReweightWeight());
            shipmentBilling.setCarrierCost(airbillInfoEditVo.getCarrierBaseCharge());
            shipmentBilling.setFranchiseCost(airbillInfoEditVo.getFranchiseCost());
            // Only calculate when customer cost is null
            shipmentBilling.setCustomerCost(airbillInfoEditVo.getCalculatedCharge());
            if (airbillInfoEditVo.getWeight() == null) {
                shipmentBilling.setWeight(0F);
            } else {
                shipmentBilling.setWeight(airbillInfoEditVo.getWeight().floatValue());
            }
            shipmentBilling.setShipDate(airbillInfoEditVo.getShipmentDate());
            shipmentBilling.setBillActualDimension(airbillInfoEditVo.getActualDimensions());
            shipmentBilling.setOriginCountryId(Long.parseLong(String.valueOf(airbillInfoEditVo.getOriginId())));
            shipmentBilling.setServiceAreaCodeOrigin(airbillInfoEditVo.getOriginCity());
            shipmentBilling.setDestinationCountryId(Long.parseLong(String.valueOf(airbillInfoEditVo.getDestinationId())));
            shipmentBilling.setServiceAreaCodeDestination(airbillInfoEditVo.getDestinationCity());
            shipmentBilling.setZone(airbillInfoEditVo.getZone());
            shipmentBilling.setPal(airbillInfoEditVo.getNoOfPieces());
            // Date currentDate = new Date();
            // shipmentBilling.setImportDate(currentDate);
            shipmentBilling.setIsBaseCharge(true);
            shipmentBilling.setCustomerTaxPercent(airbillInfoEditVo.getTax());
            shipmentBilling.setCustomerTaxAmount(airbillInfoEditVo.getTaxAmount());

            editAirbillResult.setSenderAddress(senderAddress);
            editAirbillResult.setReceiverAddress(receiverAddress);
            editAirbillResult.setShipmentBilling(shipmentBilling);
            context.put(Attributes.EDIT_AIRBILL_RESULT, editAirbillResult);
            context.put(Attributes.CUSTOMER_CODE, airbillInfoEditVo.getCustomerCode());
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
                    return "0";
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
                    return "0";
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
        if (airbillInfoEditVo.getTax() == null || airbillInfoEditVo.getTax() == 0D) {
            return 0D;
        } else {
            Double taxAmount = 0D;
            if (airbillInfoEditVo.getCalculatedCharge() != null) {
                taxAmount = (airbillInfoEditVo.getCalculatedCharge() * airbillInfoEditVo.getTax()) / 100;
            }
            return taxAmount;
        }
    }

}
