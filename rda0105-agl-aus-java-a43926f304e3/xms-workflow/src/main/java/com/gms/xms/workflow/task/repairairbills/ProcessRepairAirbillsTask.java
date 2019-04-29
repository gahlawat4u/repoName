package com.gms.xms.workflow.task.repairairbills;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.ShipmentInvoiceDao;
import com.gms.xms.persistence.service.InvoiceNumberingService;
import com.gms.xms.persistence.service.customer.IManageCustomerService;
import com.gms.xms.persistence.service.customer.ManageCustomerServiceImp;
import com.gms.xms.persistence.service.utils.ShipmentBillingUtils;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.persistence.utils.ShipmentHelper;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.RecalculateChargeImp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jul 14, 2016 10:00:37 AM
 * <p>
 * Author dattrinh
 */
public class ProcessRepairAirbillsTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessRepairAirbillsTask.class);
    private ShipmentHelper helper = new ShipmentHelper();

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        // Create new transaction.
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            // Get customer code.
            String customerCode = context.getString(Attributes.CUSTOMER_CODE);
            // Get error airbill list.
            List<ShipmentBillingVo> billingVos = context.get(Attributes.ERROR_AIRBILL_LIST);
            String airbillNumber;
            String realAirbillNumber;
            ShipmentVo shipmentVo;
            InvoiceVo invoiceVo;
            ShipmentInvoiceVo shipmentInvoiceVo;
            // Process for each airbill.
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
            ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao(sessionClient);
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(addInfo);
            for (ShipmentBillingVo shipmentBillingVo : billingVos) {
                // Get original airbill number by remove all X char from end of
                // airbill number.
                airbillNumber = shipmentBillingVo.getAirbillNumber();
                realAirbillNumber = helper.getRealAirbillNumber(airbillNumber);
                // Get shipment info by airbill number.
                ShipmentBillingVo filter = new ShipmentBillingVo();
                filter.setAirbillNumber(realAirbillNumber);
                filter.setCarrier(shipmentBillingVo.getCarrier());
                shipmentVo = shipmentDao.checkShipment(filter);
                if (shipmentVo == null) {
                    // Create new shipment if there isn't shipment with given
                    // airbill number.
                    shipmentVo = prepareNewShipment(customerCode, realAirbillNumber, shipmentBillingVo);
                    shipmentDao.insertShipment(addInfo, shipmentVo);
                }
                // Update shipment id for the shipment billing.
                shipmentBillingVo.setShipmentId(shipmentVo.getShipmentId());
                // Update shipment id and description for the shipment billing.
                shipmentBillingDao.updateIdAndDescriptionForErrorAirbill(addInfo, shipmentBillingVo);
                // Generate new invoice code.
                String invoiceCode = GenCodeUtils.generateInvoiceCode(customerCode, shipmentBillingVo.getInvoiceDate());
                // Check if this invoice was existed?
                invoiceVo = invoiceDao.getByCode(invoiceCode);
                if (invoiceVo == null) {
                    // Create new invoice if it's not exists.
                    invoiceVo = prepareNewInvoice(invoiceCode, shipmentBillingVo.getInvoiceDate(), customerCode);
                    invoiceDao.insert(addInfo, invoiceVo);
                    // Update customer activation date.
                    IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
                    manageCustomerService.updateCustomerActivationDate(addInfo, String.valueOf(invoiceVo.getCustomerCode()), sessionClient);
                    // Update invoice numbering counter.
                    InvoiceNumberingService numberingService = new InvoiceNumberingService();
                    numberingService.updateCounter(addInfo, invoiceVo, sessionClient);
                }
                // Create new shipment invoice.
                shipmentInvoiceVo = prepareNewShipmentInvoice(invoiceVo.getInvoiceId(), shipmentVo.getShipmentId(), airbillNumber);
                shipmentInvoiceDao.insert(addInfo, shipmentInvoiceVo);
                if (shipmentBillingVo.getRequireCalculate()) {
                    // Recalcuate for base charges.
                    ShipmentBillingVo baseCharge = ShipmentBillingUtils.recalculateBaseCharge(Long.valueOf(customerCode), shipmentBillingVo, iRecalculateCharge);
                    baseCharge.setAccessorialCount(0);
                    shipmentBillingDao.updateCharges(addInfo, baseCharge);
                    // Recalcuate for surcharges.
                    // Get surcharges list.
                    List<ShipmentBillingVo> surchages = shipmentBillingDao.getShipmentBillingSurcharges(shipmentBillingVo);
                    for (ShipmentBillingVo surchage : surchages) {
                        ShipmentBillingVo surchageVo = ShipmentBillingUtils.recalculateAccessorialCharge(Long.valueOf(customerCode), shipmentBillingVo, surchage);
                        shipmentBillingDao.updateCharges(addInfo, surchageVo);
                    }
                }
            }
            // Commit transaction if there isn't any error.
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Rollback transaction.
            sessionClient.rollback();
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    private ShipmentVo prepareNewShipment(String customerCode, String airbillNumber, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        ShipmentHelper helper = new ShipmentHelper();
        ShipmentVo shipmentVo;
        // Get some shipment billing info.
        String description = shipmentBillingVo.getDescription();
        String displayDescription = shipmentBillingVo.getDisplayDescription();
        Integer carrier = shipmentBillingVo.getCarrier().intValue();
        // Create new shipment if there isn't shipment with the given airbill
        // number.
        shipmentVo = new ShipmentVo();
        shipmentVo.setCustomerCode(Long.valueOf(customerCode));
        shipmentVo.setWebshipId(0L);
        shipmentVo.setAirbillNumber(airbillNumber);
        shipmentVo.setCreateDate(new Date());
        shipmentVo.setShipmentDate(shipmentBillingVo.getShipDate());
        shipmentVo.setSenderAddressId(shipmentBillingVo.getSenderAddressId());
        shipmentVo.setReceiverAddressId(shipmentBillingVo.getReceiverAddressId());
        // Set shipment type id.
        ShipmentTypeVo shipmentTypeVo = helper.getShipmentType(description, displayDescription, carrier);
        Integer shipmentTypeId = shipmentTypeVo == null ? 0 : shipmentTypeVo.getShipmentTypeId();
        shipmentVo.setShipmentTypeId(shipmentTypeId);
        // Set content type.
        Integer contentType = helper.getContentType(displayDescription);
        shipmentVo.setContents(contentType);
        // Set package id.
        Integer packageId = helper.getPackageIdByContentType(contentType, carrier);
        shipmentVo.setPackageId(packageId == null ? 0 : packageId);
        shipmentVo.setWeightUnit(helper.getWeightUnit(shipmentBillingVo.getWeightUnit()));
        shipmentVo.setDimensionUnit(helper.getDimensionUnit(shipmentVo.getWeightUnit()));
        shipmentVo.setNoOfPieces(shipmentBillingVo.getPal());
        shipmentVo.setWithInsurance(BigDecimal.ZERO);
        shipmentVo.setBaseCharge(BigDecimal.ZERO);
        shipmentVo.setCarrierCost(BigDecimal.ZERO);
        shipmentVo.setDay(0);
        shipmentVo.setDhlNote("");
        shipmentVo.setBillingCode("0");
        // Set currency code.
        shipmentVo.setCurrencyCode(""); // ???????
        shipmentVo.setCourierMessage("");
        shipmentVo.setDhlRoutingCode("0");
        shipmentVo.setAwbBarcode("");
        shipmentVo.setOriginDestiBarcode("");
        shipmentVo.setDhlRoutingBarcode("");
        // Set product content code.
        shipmentVo.setProductContentCode(contentType == 0 ? "DOX" : "WPX");
        shipmentVo.setStatus(0);
        shipmentVo.setTermOfTrade("");
        shipmentVo.setServiceAreaCodeDestination(shipmentBillingVo.getServiceAreaCodeDestination());
        shipmentVo.setServiceAreaCodeOrigin(shipmentBillingVo.getServiceAreaCodeOrigin());
        shipmentVo.setTotalCustomValue(BigDecimal.ZERO);
        shipmentVo.setCommercialInvoiceId(0);
        shipmentVo.setCollectionTypeId(0);
        shipmentVo.setBillingType(shipmentBillingVo.getBillingType());
        shipmentVo.setBillingAccount(shipmentBillingVo.getBillToAccount());
        shipmentVo.setDutiesType(0);
        shipmentVo.setDutiesAccount("");
        shipmentVo.setReceiverTaxId("");
        shipmentVo.setReasonForExport("");
        shipmentVo.setReference(shipmentBillingVo.getShipperReference());
        shipmentVo.setReference2(shipmentBillingVo.getBillingReference2());
        shipmentVo.setReference3(shipmentBillingVo.getBillingReference3());
        shipmentVo.setReceivedBy("");
        shipmentVo.setFreightClass("");
        shipmentVo.setRewiightWeight(0);
        shipmentVo.setActualDimension("");
        shipmentVo.setFranchiseBaseCharge(BigDecimal.ZERO);
        shipmentVo.setModifiedDate(new Date());
        // Set sale reps id.
        Long saleRepId = helper.getSaleRepIdByCustomerCode(customerCode);
        if (saleRepId == null) {
            saleRepId = 0L;
        }
        shipmentVo.setSalesRepId(saleRepId);
        // Get bound.
        Integer bound = helper.getBound(displayDescription);
        shipmentVo.setBoundStatus(bound);
        shipmentVo.setZone(shipmentBillingVo.getZone());
        shipmentVo.setContentDescription("");
        shipmentVo.setCarrierPayment(0);
        shipmentVo.setOldCustomerCode(0L);
        // Get package flag.
        Integer packageFlag = helper.getPackageFlag(carrier, contentType, shipmentTypeId);
        // Get descrption and new description.
        String newDesc = shipmentTypeVo == null ? "" : shipmentTypeVo.getShipmentTypeName();
        String newDisplayDesc = newDesc;
        newDisplayDesc += " " + helper.getContentTypeName(contentType, carrier, packageFlag);
        newDisplayDesc += " " + helper.getBoundName(bound);
        // No description and display description if shipment type is 0.
        if (shipmentTypeId == 0) {
            newDesc = "";
            newDisplayDesc = "";
        }
        // Update new description and display description for shipment billing.
        shipmentBillingVo.setDescription(newDesc);
        shipmentBillingVo.setDisplayDescription(newDisplayDesc);
        return shipmentVo;
    }

    private InvoiceVo prepareNewInvoice(String invoiceCode, Date invoiceDate, String customerCode) {
        InvoiceVo invoiceVo = new InvoiceVo();
        invoiceVo.setInvoiceCode(invoiceCode);
        invoiceVo.setInvoiceDate(invoiceDate);
        invoiceVo.setCustomerCode(Long.valueOf(customerCode));
        invoiceVo.setLateFee(BigDecimal.ZERO);
        invoiceVo.setStatus(0);
        invoiceVo.setPaid(0);
        return invoiceVo;
    }

    private ShipmentInvoiceVo prepareNewShipmentInvoice(Long invoiceId, Long shipmentId, String airbillNumber) {
        ShipmentInvoiceVo shipmentInvoiceVo = new ShipmentInvoiceVo();
        shipmentInvoiceVo.setInvoiceId(invoiceId);
        shipmentInvoiceVo.setShipmentId(shipmentId);
        shipmentInvoiceVo.setAirbillNumber(airbillNumber);
        shipmentInvoiceVo.setPaidCustomerCost(BigDecimal.ZERO);
        shipmentInvoiceVo.setPaidCarrierCost(BigDecimal.ZERO);
        return shipmentInvoiceVo;
    }
}