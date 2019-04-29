package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.createairbill;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ComponentDao;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AirbillInfoEditVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.Date;

/**
 * File Name: PrepareShipmentVoTask.java <br/>
 * Author: TANDT <br/>
 * Create Date: 18-03-2016 <br/>
 * Project Name: xms-workflow <br/>
 * package Name:
 * com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.createairbill
 * <br/>
 */
public class PrepareShipmentVoTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareShipmentVoTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            EditAirbillResultVo createAirbillResult = context.get(Attributes.CREATE_AIRBILL_RESULT);
            ShipmentBillingVo shipmentBillingVo = createAirbillResult.getShipmentBilling();
            AirbillInfoEditVo airbillInfoEditVo = context.get(Attributes.AIRBILL_INFO_VO);
            ShipmentVo shipmentVo = new ShipmentVo();
            shipmentVo = buildShipmentVo(shipmentBillingVo, airbillInfoEditVo);
            context.put(Attributes.SHIPMENT_VO, shipmentVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    private ShipmentVo buildShipmentVo(ShipmentBillingVo shipmentBillingVo, AirbillInfoEditVo airbillInfoEditVo) throws Exception {
        ShipmentVo shipmentVo = new ShipmentVo();
        Date currentDate = new Date();
        // Not null
        shipmentVo.setCustomerCode(0L);
        shipmentVo.setWebshipId(0L);
        shipmentVo.setAirbillNumber("");
        shipmentVo.setShipmentDate(currentDate);
        shipmentVo.setShipmentTypeId(0);
        shipmentVo.setPackageId(0);
        shipmentVo.setWithInsurance(BigDecimal.valueOf(0D));
        shipmentVo.setDay(0);
        shipmentVo.setCurrencyCode("");
        shipmentVo.setSenderAddressId(0);
        shipmentVo.setReceiverAddressId(0);
        shipmentVo.setDimensionUnit("");
        shipmentVo.setContents(1);
        shipmentVo.setWeightUnit("");
        shipmentVo.setCarrierCost(BigDecimal.valueOf(0D));
        shipmentVo.setCarrierPayment(0);
        shipmentVo.setDhlNote("");
        shipmentVo.setBillingCode("");
        shipmentVo.setCourierMessage("");
        shipmentVo.setDhlRoutingCode("");
        shipmentVo.setAwbBarcode("");
        shipmentVo.setOriginDestiBarcode("");
        shipmentVo.setDhlRoutingBarcode("");
        shipmentVo.setProductContentCode("");
        shipmentVo.setStatus(0);
        shipmentVo.setContentDescription("");
        shipmentVo.setCommercialInvoiceId(0);
        shipmentVo.setCollectionTypeId(0);
        shipmentVo.setBillingType(1);
        shipmentVo.setTotalCustomValue(new BigDecimal("0.00"));
        ComponentDao componentDao = new ComponentDao();
        String customerCode = "0";
        String billingAccount = componentDao.getTollPriorityAccount(customerCode);
        shipmentVo.setBillingAccount(billingAccount);
        shipmentVo.setDutiesType(2);
        shipmentVo.setDutiesAccount("1");
        shipmentVo.setTermOfTrade("DTU");
        shipmentVo.setServiceAreaCodeOrigin("");
        shipmentVo.setServiceAreaCodeDestination("");
        shipmentVo.setReceiverTaxId("");
        shipmentVo.setReasonForExport("");
        shipmentVo.setPackingList(1);
        shipmentVo.setBoundStatus(15);
        shipmentVo.setSalesRepId(0L);
        shipmentVo.setOldCustomerCode(0L);
        shipmentVo.setAwbProductContentCode("");
        shipmentVo.setNonStandardCharge(BigDecimal.ZERO);
        shipmentVo.setModifiedDate(new Date());
        shipmentVo.setCreateDate(new Date());
        shipmentVo.setBaseCharge(BigDecimal.valueOf(0D));

        if (airbillInfoEditVo.getCustomerCode() != null) {
            shipmentVo.setCustomerCode(airbillInfoEditVo.getCustomerCode());
        }
        if (airbillInfoEditVo.getAirbillNumber() != null) {
            shipmentVo.setAirbillNumber(airbillInfoEditVo.getAirbillNumber());
        }
        if (airbillInfoEditVo.getShipmentDate() != null) {
            shipmentVo.setShipmentDate(airbillInfoEditVo.getShipmentDate());
        }
        if (shipmentBillingVo.getBillingShipmentTypeId() != null) {
            shipmentVo.setShipmentTypeId(shipmentBillingVo.getBillingShipmentTypeId());
        }
        if (shipmentBillingVo.getPal() != null) {
            shipmentVo.setNoOfPieces(shipmentBillingVo.getPal());
        }
        shipmentVo.setCurrencyCode(SystemSettingCache.getInstance().getValueByKey("CurrencyCode"));
        if (shipmentBillingVo.getServiceAreaCodeDestination() != null) {
            shipmentVo.setServiceAreaCodeDestination(shipmentBillingVo.getServiceAreaCodeDestination());
        }
        if (shipmentBillingVo.getServiceAreaCodeOrigin() != null) {
            shipmentVo.setServiceAreaCodeOrigin(shipmentBillingVo.getServiceAreaCodeOrigin());
        }
        if (shipmentBillingVo.getShipperReference() != null) {
            shipmentVo.setReference(shipmentBillingVo.getShipperReference());
        }
        if (shipmentBillingVo.getBillingReference2() != null) {
            shipmentVo.setReference2(shipmentBillingVo.getBillingReference2());
        }
        if (shipmentBillingVo.getBillingReference3() != null) {
            shipmentVo.setReference3(shipmentBillingVo.getBillingReference3());
        }
        if (shipmentBillingVo.getBillingReceivedBy() != null) {
            shipmentVo.setReceivedBy(shipmentBillingVo.getBillingReceivedBy());
        }
        if (shipmentBillingVo.getBillingReceivedDate() != null) {
            shipmentVo.setReceivedDate(shipmentBillingVo.getBillingReceivedDate());
        }
        if (shipmentBillingVo.getBillingFreightClass() != null) {
            shipmentVo.setFreightClass(shipmentBillingVo.getBillingFreightClass());
        }
        if (shipmentBillingVo.getBillingReweightWeight() != null) {
            shipmentVo.setRewiightWeight(shipmentBillingVo.getBillingReweightWeight());
        }
        if (shipmentBillingVo.getBillActualDimension() != null) {
            shipmentVo.setActualDimension(shipmentBillingVo.getBillActualDimension());
        }
        if (shipmentBillingVo.getFranchiseCost() != null) {
            shipmentVo.setFranchiseBaseCharge(BigDecimal.valueOf(shipmentBillingVo.getFranchiseCost()));
        }
        if (shipmentBillingVo.getBillingBound() != null) {
            if (shipmentBillingVo.getBillingBound()) {
                shipmentVo.setBoundStatus(1);
            } else {
                shipmentVo.setBoundStatus(0);
            }
        }
        if (!shipmentBillingVo.getBillingContents()) {
            shipmentVo.setProductContentCode("DOX");
        } else {
            shipmentVo.setProductContentCode("WPX");
        }
        shipmentVo.setSalesRepId(ShipmentUtils.getSaleRepId(String.valueOf(airbillInfoEditVo.getCustomerCode())));
        shipmentVo.setReceiverAddressId(airbillInfoEditVo.getReceiverAddressId());
        shipmentVo.setSenderAddressId(airbillInfoEditVo.getSenderAddressId());
        return shipmentVo;
    }

}
