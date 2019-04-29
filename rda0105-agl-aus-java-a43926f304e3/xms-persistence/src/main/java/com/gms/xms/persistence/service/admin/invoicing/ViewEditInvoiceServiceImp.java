package com.gms.xms.persistence.service.admin.invoicing;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.admin.invoicing.ViewEditInvoiceDao;
import com.gms.xms.persistence.service.InvoiceNumberingService;
import com.gms.xms.persistence.service.customer.IManageCustomerService;
import com.gms.xms.persistence.service.customer.ManageCustomerServiceImp;
import com.gms.xms.persistence.service.utils.ShipmentBillingUtils;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.persistence.utils.ImportBillingServiceUtils;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.AirbillInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * Posted from InvoiceInfoServiceImp
 * <p>
 * Author TANDT Date Jul 11, 2015
 */
public class ViewEditInvoiceServiceImp implements IViewEditInvoiceService {
    private static final Log logger = LogFactory.getLog(ViewEditInvoiceServiceImp.class);

    @Override
    public InvoiceInfoVo selectInvoiceInfoById(Long invoiceId) throws DaoException {
        ViewEditInvoiceDao invoiceInfoDao = new ViewEditInvoiceDao();
        return invoiceInfoDao.selectInvoiceInfoById(invoiceId);
    }

    @Override
    public List<AirbillInfoVo> selectAirbillList(Long invoiceId) throws DaoException {
        ViewEditInvoiceDao invoiceInfoDao = new ViewEditInvoiceDao();
        return invoiceInfoDao.selectAirbillList(invoiceId);
    }

    @Override
    public void editAirbillDo(Map<String, String> context, EditAirbillResultVo editAirbillResultVo, Long customerCode, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            // Update shipment
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentVo shipmentVo = new ShipmentVo();
            Long shipmentId = editAirbillResultVo.getShipmentBilling().getShipmentId();
            shipmentVo.setShipmentId(shipmentId);
            shipmentVo.setShipmentTypeId(editAirbillResultVo.getShipmentBilling().getBillingShipmentTypeId());
            shipmentVo.setReference(editAirbillResultVo.getShipmentBilling().getShipperReference());
            shipmentVo.setReference2(editAirbillResultVo.getShipmentBilling().getBillingReference2());
            shipmentVo.setReference3(editAirbillResultVo.getShipmentBilling().getBillingReference3());
            shipmentDao.update(context, shipmentVo);

            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);

            ShipmentBillingVo shipmentBillingVoDel = new ShipmentBillingVo();
            shipmentBillingVoDel.setIsBaseCharge(false);
            shipmentBillingVoDel.setShipmentId(editAirbillResultVo.getShipmentBilling().getShipmentId());
            shipmentBillingVoDel.setAirbillNumber(editAirbillResultVo.getShipmentBilling().getAirbillNumber());
            shipmentBillingDao.deleteShipmentBillingSurcharge(context, shipmentBillingVoDel);
            logger.info("delete surcharge of shipment billing [shipmentid=" + editAirbillResultVo.getShipmentBilling().getShipmentId() + ", airbill_number=" + editAirbillResultVo.getShipmentBilling().getAirbillNumber() + "]");

            // Update shipment billing base charge
            ShipmentBillingVo shipmentBillingBaseCharge = editAirbillResultVo.getShipmentBilling();
            ShipmentBillingVo shipmentBillingBaseChargeRecalculate = editAirbillResultVo.getShipmentBilling();
            shipmentBillingBaseCharge.setIsBaseCharge(true);
            if (shipmentBillingBaseCharge.getCustomerCost() == null) {
                shipmentBillingBaseChargeRecalculate = ShipmentBillingUtils.recalculateBaseCharge(customerCode, shipmentBillingBaseCharge, iRecalculateCharge);
            }
            Double carrierCost = 0D, carrierTaxAmount = 0D;
            Double franchiseCost = 0D, franchiseTaxAmount = 0D;
            Double customerCost = 0D, customerTaxPercent = 0D, customerTaxAmount = 0D;
            if (shipmentBillingBaseChargeRecalculate == null || shipmentBillingBaseChargeRecalculate.getCustomerCost() == null || shipmentBillingBaseChargeRecalculate.getCustomerCost() == 0) {
                customerCost = shipmentBillingBaseCharge.getFranchiseCost();
                customerTaxPercent = shipmentBillingBaseCharge.getCustomerTaxPercent();
                customerTaxAmount = MathUtils.round(customerCost * customerTaxPercent / 100, 2);
                shipmentBillingBaseCharge.setCustomerCost(customerCost);
                shipmentBillingBaseCharge.setCustomerTaxAmount(customerTaxAmount);
            } else {
                customerCost = shipmentBillingBaseChargeRecalculate.getCustomerCost();
                customerTaxPercent = shipmentBillingBaseCharge.getCustomerTaxPercent();
                customerTaxAmount = MathUtils.round(customerCost * customerTaxPercent / 100, 2);
                shipmentBillingBaseCharge.setCustomerCost(customerCost);
                shipmentBillingBaseCharge.setCustomerTaxAmount(customerTaxAmount);
                franchiseCost = shipmentBillingBaseChargeRecalculate.getFranchiseCost();
                franchiseTaxAmount = MathUtils.round(franchiseCost * customerTaxPercent / 100, 2);
                shipmentBillingBaseCharge.setFranchiseCost(franchiseCost);
                shipmentBillingBaseCharge.setFranchiseTaxAmount(franchiseTaxAmount);
                carrierCost = shipmentBillingBaseChargeRecalculate.getCarrierCost();
                carrierTaxAmount = MathUtils.round(carrierCost * customerTaxPercent / 100, 2);
                shipmentBillingBaseCharge.setCarrierCost(carrierCost);
                shipmentBillingBaseCharge.setTaxAmount(carrierTaxAmount);
            }

            shipmentBillingDao.updateShipmentBillingForViewEdit(context, shipmentBillingBaseCharge);

            // Update shipment billing accessorials
            if (editAirbillResultVo.getShipmentBillings() != null && editAirbillResultVo.getShipmentBillings().size() > 0) {
                List<ShipmentBillingVo> listShipmentBilling = editAirbillResultVo.getShipmentBillings();

                //New calculate FS XI 52
                Double baseChargeForFuelSurcharge;
                for (ShipmentBillingVo shipmentBillingSurcharge : listShipmentBilling) {
                    if (shipmentBillingSurcharge.getRequireCalculate() != null && shipmentBillingSurcharge.getRequireCalculate()) {
                        //New calculate FS XI 52
                        if (shipmentBillingSurcharge.getDescription().equals("FUEL SURCHARGE")) {
                            baseChargeForFuelSurcharge = getBaseChargeforRecalculateFuelSurcharge(shipmentBillingBaseCharge, listShipmentBilling, customerCode);
                            shipmentBillingBaseCharge.setCustomerCost(baseChargeForFuelSurcharge);
                        } else {
                            shipmentBillingBaseCharge.setCustomerCost(shipmentBillingBaseCharge.getCustomerCost());
                        }

                        shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge(customerCode, shipmentBillingBaseCharge, shipmentBillingSurcharge);
                    }
                    if (!shipmentBillingSurcharge.getIsBaseCharge()) {
                        // Recalculate tax amount
                        customerTaxPercent = shipmentBillingSurcharge.getCustomerTaxPercent();
                        franchiseCost = shipmentBillingSurcharge.getFranchiseCost();
                        franchiseTaxAmount = MathUtils.round(franchiseCost * customerTaxPercent / 100, 2);
                        shipmentBillingSurcharge.setFranchiseTaxAmount(franchiseTaxAmount);
                        carrierCost = shipmentBillingSurcharge.getCarrierCost();
                        carrierTaxAmount = MathUtils.round(carrierCost * customerTaxPercent / 100, 2);
                        shipmentBillingSurcharge.setTaxAmount(carrierTaxAmount);
                        shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingSurcharge);
                    }
                }
            }

            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll back transaction
            logger.error(e);
            sessionClient.rollback();
            throw e;
        }

    }

    @Override
    public String moveAirbill(Map<String, String> context, ShipmentInvoiceVo shipmentInvoice, Long customerCode, String invoiceDate, String invoiceCode, String moveInvoiceType, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao(sessionClient);
            InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
            String newInvoiceCode;
            InvoiceVo newInvoice;
            // Create new Invoice Code.
            if (invoiceDate != null && !StringUtils.isBlank(invoiceDate) && !StringUtils.isEmpty(invoiceDate) && moveInvoiceType.equals("1")) {
                newInvoiceCode = GenCodeUtils.generateInvoiceCode(String.valueOf(customerCode), DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                // Check if the new invoice code exists or not?
                newInvoice = invoiceDao.getByCode(newInvoiceCode);
                if (newInvoice != null) {
                    // If new invoice code existed.
                    // Update shipment invoice.
                    shipmentInvoice.setInvoiceId(newInvoice.getInvoiceId());
                    shipmentInvoiceDao.updateInvoiceIdFromShipment(context, shipmentInvoice);
                    invoiceCode = newInvoiceCode;
                } else {
                    // New invoice code not existed.
                    // Update invoice code and invoice date.
                    newInvoice = new InvoiceVo();
                    newInvoice.setInvoiceCode(newInvoiceCode);
                    newInvoice.setCustomerCode(customerCode);
                    newInvoice.setStatus(0);
                    newInvoice.setLateFee(BigDecimal.valueOf(0D));
                    newInvoice.setPaid(0);
                    newInvoice.setInvCreateDate(new Date());
                    newInvoice.setInvoiceDate(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                    invoiceDao.insert(context, newInvoice);
                    // Update customer activation date.
                    IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
                    manageCustomerService.updateCustomerActivationDate(context, String.valueOf(newInvoice.getCustomerCode()), sessionClient);
                    // Update invoice numbering counter.
                    InvoiceNumberingService numberingService = new InvoiceNumberingService();
                    numberingService.updateCounter(context, newInvoice, sessionClient);
                    shipmentInvoice.setInvoiceId(newInvoice.getInvoiceId());
                    shipmentInvoiceDao.updateInvoiceIdFromShipment(context, shipmentInvoice);
                    invoiceCode = newInvoiceCode;
                }
                if (checkRecalcCustomerCost) {
                    recalcCustomerCost(context, shipmentInvoice, customerCode, sessionClient, iRecalculateCharge);
                }
            } else {
                newInvoice = invoiceDao.selectByCode(invoiceCode);
                shipmentInvoice.setInvoiceId(newInvoice.getInvoiceId());
                shipmentInvoiceDao.updateInvoiceIdFromShipment(context, shipmentInvoice);
                if (checkRecalcCustomerCost) {
                    recalcCustomerCost(context, shipmentInvoice, Long.valueOf(invoiceCode.substring(0, 8)), sessionClient, iRecalculateCharge);
                }
            }
            // Update shipment
            ShipmentVo shipmentVo = new ShipmentVo();
            shipmentVo.setShipmentId(shipmentInvoice.getShipmentId());
            shipmentVo.setCustomerCode(newInvoice.getCustomerCode());
            shipmentDao.update(context, shipmentVo);
            // Commit transaction
            sessionClient.endTransaction();
            return invoiceCode;
        } catch (Exception e) {
            // Log database action error
            logger.error(e);

            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }

    }

    @Override
    public String moveAirbill(Map<String, String> context, Long invoiceId, ShipmentInvoiceVo shipmentInvoice, Long customerCode, String invoiceDate, String invoiceCode, String moveInvoiceType, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao(sessionClient);
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
            // Get old invoice info.
            String newInvoiceCode;
            InvoiceVo newInvoice;
            // Create new Invoice Code.
            if (invoiceDate != null && !StringUtils.isBlank(invoiceDate) && !StringUtils.isEmpty(invoiceDate) && moveInvoiceType.equals("1")) {
                newInvoiceCode = GenCodeUtils.generateInvoiceCode(String.valueOf(customerCode), DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                // Check if the new invoice code exists or not?
                newInvoice = invoiceDao.getByCode(newInvoiceCode);
                if (newInvoice != null) {
                    // If new invoice code existed.
                    // Update shipment invoice.
                    shipmentInvoice.setInvoiceId(newInvoice.getInvoiceId());
                    shipmentInvoiceDao.updateInvoiceIdFromShipment(context, shipmentInvoice);
                } else {
                    // New invoice code not existed.
                    // Update invoice code and invoice date.
                    newInvoice = new InvoiceVo();
                    newInvoice.setInvoiceCode(newInvoiceCode);
                    newInvoice.setCustomerCode(customerCode);
                    newInvoice.setStatus(0);
                    newInvoice.setLateFee(BigDecimal.valueOf(0D));
                    newInvoice.setPaid(0);
                    newInvoice.setInvCreateDate(new Date());
                    newInvoice.setInvoiceDate(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                    invoiceDao.insert(context, newInvoice);
                    // Update customer activation date.
                    IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
                    manageCustomerService.updateCustomerActivationDate(context, String.valueOf(newInvoice.getCustomerCode()), sessionClient);
                    // Update invoice numbering counter.
                    InvoiceNumberingService numberingService = new InvoiceNumberingService();
                    numberingService.updateCounter(context, newInvoice, sessionClient);
                    shipmentInvoice.setInvoiceId(newInvoice.getInvoiceId());
                    shipmentInvoiceDao.updateInvoiceIdFromShipment(context, shipmentInvoice);
                }
                invoiceCode = newInvoiceCode;
                if (checkRecalcCustomerCost) {
                    recalcCustomerCost(context, shipmentInvoice, customerCode, sessionClient, iRecalculateCharge);
                }
            } else {
                newInvoice = invoiceDao.selectByCode(invoiceCode);
                shipmentInvoice.setInvoiceId(newInvoice.getInvoiceId());
                shipmentInvoiceDao.updateInvoiceIdFromShipment(context, shipmentInvoice);
                if (checkRecalcCustomerCost) {
                    recalcCustomerCost(context, shipmentInvoice, Long.valueOf(invoiceCode.substring(0, 8)), sessionClient, iRecalculateCharge);
                }
            }

            // Update shipment
            ShipmentVo shipmentVo = new ShipmentVo();
            shipmentVo.setShipmentId(shipmentInvoice.getShipmentId());
            shipmentVo.setCustomerCode(newInvoice.getCustomerCode());
            shipmentDao.update(context, shipmentVo);

            // Get new invoice info.
            InvoiceVo newInvoiceVo = invoiceDao.selectByCode(invoiceCode);
            // Get new shipment billing info.
            ShipmentBillingFilter filter = new ShipmentBillingFilter();
            filter.setShipmentId(shipmentInvoice.getShipmentId());
            filter.setAirbillNumber(shipmentInvoice.getAirbillNumber());
            ShipmentBillingVo newShipmentVo = shipmentBillingDao.selectIsBaseChargeByFilter(filter);
            // Check and add Agl Warranty.
            Date startDate = DateUtils.convertStringToDate(AppConstants.APP_SETTINGS.getSelfInsuranceStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            Date shipDate = newShipmentVo.getShipDate();
            Date importDate = newShipmentVo.getImportDate();
            if (shipDate == null || DateUtils.convertDateToString(shipDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null).equalsIgnoreCase("00-00-0000")) {
                if (importDate.after(startDate)) {
                    ImportBillingServiceUtils.determineWarrantySerice(context, newInvoiceVo.getCustomerCode(), newShipmentVo.getShipmentId(), newShipmentVo.getAirbillNumber(), newShipmentVo.getCarrier().intValue(), newShipmentVo.getDescription(), sessionClient);
                }
            } else {
                if (shipDate.after(startDate)) {
                    ImportBillingServiceUtils.determineWarrantySerice(context, newInvoiceVo.getCustomerCode(), newShipmentVo.getShipmentId(), newShipmentVo.getAirbillNumber(), newShipmentVo.getCarrier().intValue(), newShipmentVo.getDescription(), sessionClient);
                }
            }
            // Commit transaction
            sessionClient.endTransaction();
            return invoiceCode;
        } catch (Exception e) {
            // Log database action error
            logger.error(e);

            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }

    }

    private void recalcCustomerCost(Map<String, String> context, ShipmentInvoiceVo shipmentInvoice, Long customerCode, SqlSessionClient sessionClient, IRecalculateCharge iRecalculateCharge) throws Exception {
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
        shipmentBillingFilter.setAirbillNumber(shipmentInvoice.getAirbillNumber());
        shipmentBillingFilter.setShipmentId(shipmentInvoice.getShipmentId());
        List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectByFilter(shipmentBillingFilter);
        List<ShipmentBillingVo> shipmentBillings = new ArrayList<ShipmentBillingVo>();
        EditAirbillResultVo editAirbillResultVo = new EditAirbillResultVo();
        for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
            if (shipmentBilling.getIsBaseCharge()) {
                editAirbillResultVo.setShipmentBilling(shipmentBilling);
            } else {
                shipmentBillings.add(shipmentBilling);
            }
        }
        editAirbillResultVo.setShipmentBillings(shipmentBillings);

        // Update shipment
        ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
        ShipmentVo shipmentVo = new ShipmentVo();
        shipmentVo.setCustomerCode(customerCode);
        shipmentVo.setShipmentId(shipmentInvoice.getShipmentId());
        shipmentDao.update(context, shipmentVo);

        ShipmentBillingVo shipmentBillingVoDel = new ShipmentBillingVo();
        shipmentBillingVoDel.setIsBaseCharge(false);
        shipmentBillingVoDel.setShipmentId(shipmentInvoice.getShipmentId());
        shipmentBillingVoDel.setAirbillNumber(shipmentInvoice.getAirbillNumber());
        shipmentBillingDao.deleteShipmentBillingSurcharge(context, shipmentBillingVoDel);

        // Update shipment billing base charge
        ShipmentBillingVo shipmentBillingBaseCharge = editAirbillResultVo.getShipmentBilling();
        ShipmentBillingVo shipmentBillingBaseChargeRecaculate = editAirbillResultVo.getShipmentBilling();

        shipmentBillingBaseCharge.setIsBaseCharge(true);
        shipmentBillingBaseChargeRecaculate = ShipmentBillingUtils.recalculateBaseCharge(customerCode, shipmentBillingBaseCharge, iRecalculateCharge);

        if (shipmentBillingBaseChargeRecaculate == null || shipmentBillingBaseChargeRecaculate.getCustomerCost() == null || shipmentBillingBaseChargeRecaculate.getCustomerCost() == 0) {
            shipmentBillingBaseCharge.setCustomerCost(shipmentBillingBaseCharge.getFranchiseCost());
        } else {
            shipmentBillingBaseCharge.setFranchiseCost(shipmentBillingBaseChargeRecaculate.getFranchiseCost());
            shipmentBillingBaseCharge.setFranchiseTaxAmount(shipmentBillingBaseChargeRecaculate.getFranchiseTaxAmount());
            shipmentBillingBaseCharge.setCalculatedCarrierCost(shipmentBillingBaseChargeRecaculate.getCalculatedCarrierCost());
            shipmentBillingBaseCharge.setCustomerCost(shipmentBillingBaseChargeRecaculate.getCustomerCost());
            shipmentBillingBaseCharge.setCustomerTaxPercent(shipmentBillingBaseChargeRecaculate.getCustomerTaxPercent());
            shipmentBillingBaseCharge.setCustomerTaxAmount(shipmentBillingBaseChargeRecaculate.getCustomerTaxAmount());
        }

        shipmentBillingDao.updateShipmentBillingForViewEdit(context, shipmentBillingBaseCharge);

        // Update shipment billing accessorials
        if (editAirbillResultVo.getShipmentBillings() != null && editAirbillResultVo.getShipmentBillings().size() > 0) {
            List<ShipmentBillingVo> listShipmentBilling = editAirbillResultVo.getShipmentBillings();
            for (ShipmentBillingVo shipmentBillingSurcharge : listShipmentBilling) {
                if (shipmentBillingSurcharge.getInvoiceDate() == null) {
                    shipmentBillingSurcharge.setInvoiceDate(shipmentBillingBaseCharge.getInvoiceDate());
                }
                shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge(customerCode, shipmentBillingBaseCharge, shipmentBillingSurcharge);
                if (!shipmentBillingSurcharge.getIsBaseCharge()) {
                    shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingSurcharge);
                }
            }
        }
    }

    @Override
    public void deleteAirbill(Map<String, String> context, String shipmentId, String airbillNumber) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
        ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao(sessionClient);
        try {
            ShipmentBillingVo shipmentBilling = new ShipmentBillingVo();
            shipmentBilling.setAirbillNumber(airbillNumber);
            shipmentBilling.setShipmentId(Long.parseLong(shipmentId));
            ShipmentInvoiceVo shipmentInvoice = new ShipmentInvoiceVo();
            shipmentInvoice.setAirbillNumber(airbillNumber);
            shipmentInvoice.setShipmentId(Long.parseLong(shipmentId));
            shipmentBillingDao.deleteShipment(context, shipmentBilling);
            shipmentInvoiceDao.deleteShipment(context, shipmentInvoice);
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Log database action error
            logger.error(e);

            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }

    }

    @Override
    public void createInvoiceAndAirbillDo(Map<String, String> context, InvoiceVo newInvoice, EditAirbillResultVo createAirbillResultVo, ShipmentVo shipmentVo, ShipmentInvoiceVo shipmentInvoice, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        // Insert new invoice and shipment billing
        sessionClient.startTransaction();
        try {
            // Insert new invoice
            InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
            String newInvoiceCode = GenCodeUtils.generateInvoiceCode(String.valueOf(newInvoice.getCustomerCode()), newInvoice.getInvoiceDate());
            // Check if the new invoice code exists or not?
            InvoiceVo isNewInvoice = invoiceDao.getByCode(newInvoiceCode);
            if (isNewInvoice != null) {
                throw new Exception("Invoice Existed In System.");
            }
            // New invoice code not existed.
            invoiceDao.insert(context, newInvoice);
            // Update customer activation date.
            IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
            manageCustomerService.updateCustomerActivationDate(context, String.valueOf(newInvoice.getCustomerCode()), sessionClient);
            // Update invoice numbering counter.
            InvoiceNumberingService numberingService = new InvoiceNumberingService();
            numberingService.updateCounter(context, newInvoice, sessionClient);
            Long invoiceId = newInvoice.getInvoiceId();

            // Check duplicate airbill number
            if (isExistAirbillNumber(shipmentVo.getAirbillNumber(), createAirbillResultVo.getShipmentBilling().getCarrier())) {
                throw new Exception("Duplicate airbill number!");
            }

            // Insert Sender & Receiver Address
            AddressDao addressDao = new AddressDao(sessionClient);
            AddressVo senderAddress = new AddressVo();
            AddressVo receiverAddress = new AddressVo();
            senderAddress = createAirbillResultVo.getSenderAddress();
            receiverAddress = createAirbillResultVo.getReceiverAddress();
            addressDao.insert(context, senderAddress);
            addressDao.insert(context, receiverAddress);

            // Insert shipment
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            shipmentVo.setReceiverAddressId(receiverAddress.getAddressId());
            shipmentVo.setSenderAddressId(senderAddress.getAddressId());
            shipmentDao.insertShipment(context, shipmentVo);

            // Insert shipment invoice
            ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao(sessionClient);
            shipmentInvoice.setShipmentId(shipmentVo.getShipmentId());
            shipmentInvoice.setInvoiceId(invoiceId);
            shipmentInvoiceDao.insert(context, shipmentInvoice);

            Long customerCode = shipmentVo.getCustomerCode();
            // Insert shipment billing base charge
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingVo shipmentBillingBaseCharge = createAirbillResultVo.getShipmentBilling();
            shipmentBillingBaseCharge.setShipmentId(shipmentVo.getShipmentId());
            shipmentBillingBaseCharge.setSenderAddressId(senderAddress.getAddressId());
            shipmentBillingBaseCharge.setReceiverAddressId(receiverAddress.getAddressId());
            shipmentBillingBaseCharge.setInvoiceDate(newInvoice.getInvoiceDate());
            if (shipmentBillingBaseCharge.getRequireCalculate() != null && shipmentBillingBaseCharge.getRequireCalculate()) {
                shipmentBillingBaseCharge = ShipmentBillingUtils.recalculateBaseCharge(customerCode, shipmentBillingBaseCharge, iRecalculateCharge);
            }
            shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingBaseCharge);

            // Insert shipment billing surcharge
            List<ShipmentBillingVo> listShipmentBilling = createAirbillResultVo.getShipmentBillings();
            if (createAirbillResultVo.getShipmentBillings() != null && createAirbillResultVo.getShipmentBillings().size() > 0) {
                Double baseChargeForFuelSurcharge;
                for (ShipmentBillingVo shipmentBillingSurcharge : listShipmentBilling) {
                    shipmentBillingSurcharge.setShipmentId(shipmentVo.getShipmentId());
                    shipmentBillingSurcharge.setSenderAddressId(senderAddress.getAddressId());
                    shipmentBillingSurcharge.setReceiverAddressId(receiverAddress.getAddressId());
                    shipmentBillingSurcharge.setInvoiceDate(newInvoice.getInvoiceDate());
                    if (shipmentBillingSurcharge.getRequireCalculate() != null && shipmentBillingSurcharge.getRequireCalculate()) {
                        //New calculate FS XI 52
                        if (shipmentBillingSurcharge.getDescription().equals("FUEL SURCHARGE")) {
                            baseChargeForFuelSurcharge = getBaseChargeforRecalculateFuelSurcharge(shipmentBillingBaseCharge, listShipmentBilling, customerCode);
                            shipmentBillingBaseCharge.setCustomerCost(baseChargeForFuelSurcharge);
                        } else {
                            shipmentBillingBaseCharge.setCustomerCost(shipmentBillingBaseCharge.getCustomerCost());
                        }

                        shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge(customerCode, shipmentBillingBaseCharge, shipmentBillingSurcharge);
                    }
                    shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingSurcharge);
                }
            }
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll back transaction
            logger.error(e);
            sessionClient.rollback();
            throw e;
        }
        // Update shipment billing
        sessionClient.startTransaction();
        try {
            updateShipmentBilling(context, shipmentVo, iRecalculateCharge, sessionClient);
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll back transaction
            logger.error(e);
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void createAirbillDo(Map<String, String> context, EditAirbillResultVo createAirbillResultVo, ShipmentVo shipmentVo, ShipmentInvoiceVo shipmentInvoice, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        // Insert shipment billing
        sessionClient.startTransaction();
        try {
            // Check duplicate airbill number
            if (isExistAirbillNumber(shipmentVo.getAirbillNumber(), createAirbillResultVo.getShipmentBilling().getCarrier())) {
                throw new Exception("Duplicate airbill number!");
            }
            // Insert Sender & Receiver Address
            AddressDao addressDao = new AddressDao(sessionClient);
            AddressVo senderAddress = new AddressVo();
            AddressVo receiverAddress = new AddressVo();
            senderAddress = createAirbillResultVo.getSenderAddress();
            receiverAddress = createAirbillResultVo.getReceiverAddress();
            addressDao.insert(context, senderAddress);
            addressDao.insert(context, receiverAddress);
            shipmentVo.setReceiverAddressId(receiverAddress.getAddressId());
            shipmentVo.setSenderAddressId(senderAddress.getAddressId());

            // Insert shipment
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            shipmentDao.insertShipment(context, shipmentVo);

            // Insert shipment invoice
            ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao(sessionClient);
            shipmentInvoice.setShipmentId(shipmentVo.getShipmentId());
            shipmentInvoiceDao.insert(context, shipmentInvoice);

            // Insert shipment billing base charge
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingVo shipmentBillingBaseCharge = createAirbillResultVo.getShipmentBilling();
            shipmentBillingBaseCharge.setShipmentId(shipmentVo.getShipmentId());
            shipmentBillingBaseCharge.setSenderAddressId(senderAddress.getAddressId());
            shipmentBillingBaseCharge.setReceiverAddressId(receiverAddress.getAddressId());
            shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingBaseCharge);
            // Insert shipment billing surcharge
            List<ShipmentBillingVo> listShipmentBilling = createAirbillResultVo.getShipmentBillings();
            if (createAirbillResultVo.getShipmentBillings() != null && createAirbillResultVo.getShipmentBillings().size() > 0) {
                for (ShipmentBillingVo shipmentBillingSurcharge : listShipmentBilling) {
                    shipmentBillingSurcharge.setShipmentId(shipmentVo.getShipmentId());
                    shipmentBillingSurcharge.setSenderAddressId(senderAddress.getAddressId());
                    shipmentBillingSurcharge.setReceiverAddressId(receiverAddress.getAddressId());
                    shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingSurcharge);
                }
            }
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll back transaction
            logger.error(e);
            sessionClient.rollback();
            throw e;
        }
        // Update shipment billing
        sessionClient.startTransaction();
        try {
            updateShipmentBilling(context, shipmentVo, iRecalculateCharge, sessionClient);
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll back transaction
            logger.error(e);
            sessionClient.rollback();
            throw e;
        }
    }

    private boolean isExistAirbillNumber(String airbillNumber, Long carrier) throws DaoException {
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
        shipmentBillingFilter.setAirbillNumber(airbillNumber);
        shipmentBillingFilter.setCarrier(carrier);
        List<ShipmentBillingVo> listShipmentBillingVo = shipmentBillingDao.selectByAirbillNumber(shipmentBillingFilter);
        if (listShipmentBillingVo != null && listShipmentBillingVo.size() > 0) {
            return true;
        }
        return false;
    }

    private void updateShipmentBilling(Map<String, String> context, ShipmentVo shipmentVo, IRecalculateCharge iRecalculateCharge, SqlSessionClient sessionClient) throws Exception {
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
        shipmentBillingFilter.setAirbillNumber(shipmentVo.getAirbillNumber());
        shipmentBillingFilter.setShipmentId(shipmentVo.getShipmentId());
        List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectByFilter(shipmentBillingFilter);
        ShipmentBillingVo shipmentBillingBaseCharge = new ShipmentBillingVo(), shipmentBillingSurcharge = new ShipmentBillingVo();
        Double baseChargeForFuelSurcharge;
        for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
            if (shipmentBilling.getRequireCalculate() != null && shipmentBilling.getRequireCalculate()) {
                if (shipmentBilling.getIsBaseCharge()) {
                    shipmentBillingBaseCharge = ShipmentBillingUtils.recalculateBaseCharge(shipmentVo.getCustomerCode(), shipmentBilling, iRecalculateCharge);
                    shipmentBillingDao.updateShipmentBilling(context, shipmentBillingBaseCharge);
                } else {
                    //New calculate FS XI 52
                    if (shipmentBillingSurcharge.getDescription().equals("FUEL SURCHARGE")) {
                        baseChargeForFuelSurcharge = getBaseChargeforRecalculateFuelSurcharge(shipmentBillingBaseCharge, shipmentBillingVos, shipmentVo.getCustomerCode());
                        shipmentBillingBaseCharge.setCustomerCost(baseChargeForFuelSurcharge);
                    } else {
                        shipmentBillingBaseCharge.setCustomerCost(shipmentBillingBaseCharge.getCustomerCost());
                    }

                    shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge(shipmentVo.getCustomerCode(), shipmentBillingBaseCharge, shipmentBilling);
                    shipmentBillingDao.updateShipmentBilling(context, shipmentBillingSurcharge);
                }
            }
        }
    }

    private Double getBaseChargeforRecalculateFuelSurcharge(ShipmentBillingVo shipmentBillingBaseCharge, List<ShipmentBillingVo> listShipmentBilling, Long customerCode) throws Exception {
        Double baseCharge = shipmentBillingBaseCharge.getCustomerCost();

        for (ShipmentBillingVo baseChargeForRecalFS : listShipmentBilling) {
            if (baseChargeForRecalFS.getRequireCalculate()) {
                baseChargeForRecalFS = ShipmentBillingUtils.recalculateAccessorialCharge(customerCode, shipmentBillingBaseCharge, baseChargeForRecalFS);
            }

            switch (baseChargeForRecalFS.getDescription()) {
                case "REMOTE AREA SERVICE":
                    baseCharge += baseChargeForRecalFS.getCustomerCost();
                    break;
                case "REMOTE AREA PICKUP":
                    baseCharge += baseChargeForRecalFS.getCustomerCost();
                    break;
                case "RESTRICTED DESTINATION":
                    baseCharge += baseChargeForRecalFS.getCustomerCost();
                    break;
                case "OVER HANDLED PIECE":
                    baseCharge += baseChargeForRecalFS.getCustomerCost();
                    break;
                case "OVER SIZED PIECE":
                    baseCharge += baseChargeForRecalFS.getCustomerCost();
                    break;
                case "OVER WEIGHT PIECE":
                    baseCharge += baseChargeForRecalFS.getCustomerCost();
                    break;
            }
        }
        return baseCharge;
    }
}
