package com.gms.xms.persistence.service.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.filter.admin.EventLogFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.admin.EventLogDao;
import com.gms.xms.persistence.daoservice.webship.history.HistoryDetailAccessorialDaoService;
import com.gms.xms.persistence.service.InvoiceNumberingService;
import com.gms.xms.persistence.service.customer.IManageCustomerService;
import com.gms.xms.persistence.service.customer.ManageCustomerServiceImp;
import com.gms.xms.persistence.service.utils.ShipmentBillingUtils;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.persistence.utils.ImportBillingServiceUtils;
import com.gms.xms.persistence.utils.ShipmentHelper;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.EventLogVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailAccessorialVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jul 14, 2016 4:07:42 PM
 * <p>
 * Author huynd
 */
public class MassEditServiceImp implements IMassEditService {
    private static final Log logger = LogFactory.getLog(MassEditServiceImp.class);

    @Override
    public void recalcBaseCharge(Map<String, String> context, Long shipmentId, String airbillNumber, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            String filter = "\'shipmentid = \\'" + shipmentId.toString() + "\\' and airbill_number = \\'" + airbillNumber + "\\' and accessorial_count = \\'0\\'\'";
            EventLogDao eventLogDao = new EventLogDao(sessionClient);
            EventLogFilter eventLogFilter = new EventLogFilter();
            eventLogFilter.setActionType("Insert");
            eventLogFilter.setActionTable("shipment_billing");
            eventLogFilter.setFilter(filter);
            EventLogVo eventLogVo = eventLogDao.selectInitValueShipmentBilling(eventLogFilter);
            String changesMode = "";
            if (eventLogVo != null) {
                changesMode = eventLogVo.getChangesMode();
                String[] changesModeArr = changesMode.split("@,@");
                String[] modeArr;
                for (int i = 0; i < changesModeArr.length; i++) {
                    modeArr = changesModeArr[i].split(">");
                    if ("carrier_cost".equalsIgnoreCase(modeArr[Integer.valueOf(Attributes.FIELD_CHANGES_MODE_EVENTLOG)])) {
                        Double initCarrierCost = Double.valueOf(modeArr[Integer.valueOf(Attributes.VALUE_CHANGES_MODE_EVENTLOG)]);
                        ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
                        ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
                        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
                        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
                        shipmentBillingFilter.setShipmentId(shipmentId);
                        shipmentBillingFilter.setAirbillNumber(airbillNumber);
                        ShipmentBillingVo shipmentBillingBaseCharge = new ShipmentBillingVo();
                        List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectByFilter(shipmentBillingFilter);
                        for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
                            if (shipmentBilling.getIsBaseCharge()) {
                                Double carrierCostTaxPercent = shipmentBilling.getCarrierTaxPercent();
                                Double carrierCostTaxAmount = initCarrierCost * carrierCostTaxPercent / 100;
                                shipmentBilling.setCarrierCost(initCarrierCost);
                                shipmentBilling.setTaxAmount(carrierCostTaxAmount);
                                shipmentBillingBaseCharge = ShipmentBillingUtils.recalculateBaseCharge(shipmentVo.getCustomerCode(), shipmentBilling, iRecalculateCharge);
                                shipmentBillingDao.updateShipmentBillingForViewEdit(context, shipmentBillingBaseCharge);
                            } else {
                                shipmentBilling = ShipmentBillingUtils.recalculateAccessorialCharge(shipmentVo.getCustomerCode(), shipmentBillingBaseCharge, shipmentBilling);
                                shipmentBillingDao.updateShipmentBillingForViewEdit(context, shipmentBillingBaseCharge);
                            }
                        }
                    }
                }
            }
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
    public void recalcCustomerBaseCharge(Map<String, String> context, Long shipmentId, String airbillNumber, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            String filter = "\'shipmentid = \\'" + shipmentId.toString() + "\\' and airbill_number = \\'" + airbillNumber + "\\' and accessorial_count = \\'0\\'\'";
            EventLogDao eventLogDao = new EventLogDao(sessionClient);
            EventLogFilter eventLogFilter = new EventLogFilter();
            eventLogFilter.setActionType("Update");
            eventLogFilter.setActionTable("shipment_billing");
            eventLogFilter.setFilter(filter);
            EventLogVo eventLogVo = eventLogDao.selectInitValueShipmentBilling(eventLogFilter);
            String changesMode = "";
            if (eventLogVo != null) {
                changesMode = eventLogVo.getChangesMode();
                String[] changesModeArr = changesMode.split("@,@");
                String[] modeArr;
                for (int i = 0; i < changesModeArr.length; i++) {
                    modeArr = changesModeArr[i].split(">");
                    if ("customer_cost".equalsIgnoreCase(modeArr[Integer.valueOf(Attributes.FIELD_CHANGES_MODE_EVENTLOG)])) {
                        Double initCustomerCost = Double.valueOf(modeArr[Integer.valueOf(Attributes.VALUE_CHANGES_MODE_EVENTLOG)]);
                        ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
                        ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
                        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
                        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
                        shipmentBillingFilter.setShipmentId(shipmentId);
                        shipmentBillingFilter.setAirbillNumber(airbillNumber);
                        ShipmentBillingVo shipmentBillingBaseCharge = new ShipmentBillingVo();
                        List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectByFilter(shipmentBillingFilter);
                        for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
                            if (shipmentBilling.getIsBaseCharge()) {
                                Double customerCostTaxPercent = shipmentBilling.getCustomerTaxPercent();
                                Double customerCostTaxAmount = initCustomerCost * customerCostTaxPercent / 100;
                                shipmentBilling.setCustomerCost(initCustomerCost);
                                shipmentBilling.setCustomerTaxAmount(customerCostTaxAmount);
                                shipmentBillingBaseCharge = ShipmentBillingUtils.recalculateBaseCharge(shipmentVo.getCustomerCode(), shipmentBilling, iRecalculateCharge);
                                shipmentBillingDao.updateShipmentBillingForViewEdit(context, shipmentBillingBaseCharge);
                            } else {
                                shipmentBilling = ShipmentBillingUtils.recalculateAccessorialCharge(shipmentVo.getCustomerCode(), shipmentBillingBaseCharge, shipmentBilling);
                                shipmentBillingDao.updateShipmentBillingForViewEdit(context, shipmentBillingBaseCharge);
                            }
                        }
                    }
                }
            }
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
    public void deleteAirbill(Map<String, String> context, Long shipmentId, String airbillNumber, String invoiceId) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            // Delete shipment invoice
            ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao(sessionClient);
            ShipmentInvoiceVo shipmentInvoice = new ShipmentInvoiceVo();
            shipmentInvoice.setAirbillNumber(airbillNumber);
            shipmentInvoice.setShipmentId(shipmentId);
            shipmentInvoiceDao.deleteShipment(context, shipmentInvoice);
            logger.info("Delete shipment invoice: " + shipmentInvoice);

            // Delete credit note detail
            AirbillAdjustmentDao airbillAdjustmentDao = new AirbillAdjustmentDao(sessionClient);
            CreditNoteDetailDao creditNoteDetailDao = new CreditNoteDetailDao(sessionClient);
            List<Long> adjustmentIdList = airbillAdjustmentDao.selectAdjustmentId();
            for (Long adjustmentId : adjustmentIdList) {
                creditNoteDetailDao.deleteCreditDetailByAdjustmentId(context, adjustmentId);
                logger.info("Delete credit note detail by adjustment id: " + adjustmentId);
                // Delete airbill adjustment
                airbillAdjustmentDao.deleteById(context, adjustmentId);
                logger.info("Delete airbill adjustment by adjustment id: " + adjustmentId);
            }
            InvoicePaymentDao invoicePaymentDao = new InvoicePaymentDao(sessionClient);
            List<InvoicePaymentVo> invoicePaymentVos = invoicePaymentDao.selectPaymentByInvoiceId(Long.valueOf(invoiceId));
            if (invoicePaymentVos != null) {
                for (InvoicePaymentVo invoicePaymentVo : invoicePaymentVos) {
                    BigDecimal overAmount = invoicePaymentVo.getAmount();
                    OverpaymentDao overpaymentDao = new OverpaymentDao(sessionClient);
                    OverpaymentVo overpaymentVo = overpaymentDao.selectByCustomerPaymentId(invoicePaymentVo.getCusPaymentId());
                    if (overpaymentVo != null) {
                        overAmount = overAmount.add(overpaymentVo.getOverAmount());
                    }
                    // Insert over payment
                    OverpaymentVo overpaymentNew = new OverpaymentVo();
                    overpaymentNew.setCusPaymentId(invoicePaymentVo.getCusPaymentId());
                    overpaymentNew.setOverAmount(overAmount);
                    overpaymentDao.insert(context, overpaymentNew);
                    logger.info("Insert over payment: " + overpaymentNew);
                    // Delete invoice payment
                    invoicePaymentDao.delete(context, invoicePaymentVo.getInvoicePaymentId());
                    logger.info("Delete invoice payment by invoice payment id: " + invoicePaymentVo.getInvoicePaymentId());
                    // Check existing invoice payment detail
                    InvoicePaymentDetailDao invoicePaymentDetailDao = new InvoicePaymentDetailDao(sessionClient);
                    List<InvoicePaymentDetailVo> invoicePaymentDetailVos = invoicePaymentDetailDao.selectByInvoicePayemntId(invoicePaymentVo.getInvoicePaymentId());
                    if (invoicePaymentDetailVos != null) {
                        for (InvoicePaymentDetailVo invoicePaymentDetailVo : invoicePaymentDetailVos) {
                            invoicePaymentDetailDao.delete(context, invoicePaymentDetailVo);
                            logger.info("Delete invoice payment detail: " + invoicePaymentDetailVo);
                        }
                    }
                }
            }
            // Delete invoice
            InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
            InvoiceVo invoiceVo = invoiceDao.selectById(Long.valueOf(invoiceId));
            Long countShipments = invoiceDao.countShipmentInvoiceByInvoiceId(Long.valueOf(invoiceId));
            if (countShipments == 0) {
                if (invoiceVo != null) {
                    Long customerCode = invoiceVo.getCustomerCode();
                    if (customerCode != 0) {
                        invoiceDao.deleteById(context, Long.valueOf(invoiceId));
                        logger.info("Delete invoice by invoice id: " + invoiceId);
                        // Update customer activation date.
                        IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
                        manageCustomerService.updateCustomerActivationDate(context, String.valueOf(invoiceVo.getCustomerCode()), sessionClient);
                    }
                }
            }

            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingVo shipmentBilling = new ShipmentBillingVo();
            shipmentBilling.setAirbillNumber(airbillNumber);
            shipmentBilling.setShipmentId(shipmentId);
            shipmentBillingDao.deleteShipment(context, shipmentBilling);
            logger.info("Delete shipment: " + shipmentBilling);
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
    public String moveAirbill(Map<String, String> context, Long invoiceId, ShipmentInvoiceVo shipmentInvoice, Long customerCode, String invoiceDate, String invoiceCode, String moveInvoiceType, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception {
        // Create new transaction.
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
        InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
        try {
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao(sessionClient);
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
            // Detect enable Agl warranty of new customer.
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
        } catch (Exception e) {
            // Log database action error
            logger.error(e);
            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }
        return invoiceCode;
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
        } catch (Exception e) {
            // Log database action error
            logger.error(e);
            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }
        return invoiceCode;
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
        shipmentBillingBaseCharge.setIsBaseCharge(true);
        shipmentBillingBaseCharge = ShipmentBillingUtils.recalculateBaseCharge(customerCode, shipmentBillingBaseCharge, iRecalculateCharge);
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
    public void addAccessorialCharge(Map<String, String> context, Long shipmentId, String airbillNumber, AccessorialInfoVo accessorialInfoVo, Integer adminLevel, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
            Long customerCode = shipmentVo.getCustomerCode();

            InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
            ShipmentInvoiceVo shipmentInvoiceVo = new ShipmentInvoiceVo();
            shipmentInvoiceVo.setShipmentId(shipmentId);
            shipmentInvoiceVo.setAirbillNumber(airbillNumber);
            MarkUpByAirbillShipmentVo markUpByAirbillShipmentVo = invoiceDao.selectMarkUpByAirbillShipment(shipmentInvoiceVo);
            Double markup = 0D;
            if (markUpByAirbillShipmentVo != null) {
                markup = markUpByAirbillShipmentVo.getMarkupRate();
                customerCode = markUpByAirbillShipmentVo.getCustomerCode();
            }
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(airbillNumber);
            shipmentBillingFilter.setShipmentId(shipmentId);
            ShipmentBillingVo shipmentBillingBaseCharge = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
            int accCount = shipmentBillingDao.selectMaxAccessorialCount(shipmentBillingFilter);
            ShipmentBillingVo shipmentBillingSurcharge = new ShipmentBillingVo();
            BeanUtils.copyProperties(shipmentBillingSurcharge, shipmentBillingBaseCharge);
            shipmentBillingSurcharge.setDescription(accessorialInfoVo.getDescription());
            shipmentBillingSurcharge.setDisplayDescription(accessorialInfoVo.getDescription());

            Double tax = (accessorialInfoVo.getCustomerTaxPercent() == null) ? 0D : accessorialInfoVo.getCustomerTaxPercent();
            Double customerCost = (accessorialInfoVo.getCustomerCost() == null) ? 0D : accessorialInfoVo.getCustomerCost();
            Double customerTaxAmount = customerCost * tax / 100;
            Double franchiseCost = (accessorialInfoVo.getFranchiseCost() == null) ? 0D : accessorialInfoVo.getFranchiseCost();
            Double carrierCost = 0D;
            if (adminLevel < 3) {
                carrierCost = (accessorialInfoVo.getCarrierCost() == null) ? 0D : accessorialInfoVo.getCarrierCost();
                if ("1".equalsIgnoreCase(shipmentBillingBaseCharge.getCarrier().toString())) {
                    franchiseCost = carrierCost + carrierCost * markup / 100;
                }
            } else {
                carrierCost = franchiseCost;
            }
            Double franchiseTaxAmount = franchiseCost * tax / 100;
            Double carrierTaxAmount = carrierCost * tax / 100;

            shipmentBillingSurcharge.setCarrierCost(carrierCost);
            shipmentBillingSurcharge.setCarrierTaxPercent(tax);
            shipmentBillingSurcharge.setTaxAmount(carrierTaxAmount);
            shipmentBillingSurcharge.setCalculatedCarrierCost(carrierCost);
            shipmentBillingSurcharge.setFranchiseCost(franchiseCost);
            shipmentBillingSurcharge.setFranchiseTaxAmount(franchiseTaxAmount);
            shipmentBillingSurcharge.setCalculatedFranchiseCost(franchiseCost);
            shipmentBillingSurcharge.setCustomerCost(customerCost);
            shipmentBillingSurcharge.setCustomerTaxAmount(customerTaxAmount);
            shipmentBillingSurcharge.setCustomerTaxPercent(tax);
            shipmentBillingSurcharge.setGstPercent(tax);

            shipmentBillingSurcharge.setRequireCalculate(accessorialInfoVo.getRequireCalculate());
            shipmentBillingSurcharge.setIsBaseCharge(false);
            shipmentBillingSurcharge.setAccessorialCount(++accCount);

            if (shipmentBillingSurcharge.getRequireCalculate()) {
                shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge(customerCode, shipmentBillingBaseCharge, shipmentBillingSurcharge);
            }
            // Save accessorials
            shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingSurcharge);
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
    public void recalcFranchiseBaseCost(Map<String, String> context, Long shipmentId, String airbillNumber, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            String filter = "\'shipmentid = \\'" + shipmentId.toString() + "\\' and airbill_number = \\'" + airbillNumber + "\\' and accessorial_count = \\'0\\'\'";
            EventLogDao eventLogDao = new EventLogDao(sessionClient);
            EventLogFilter eventLogFilter = new EventLogFilter();
            eventLogFilter.setActionType("Insert");
            eventLogFilter.setActionTable("shipment_billing");
            eventLogFilter.setFilter(filter);
            EventLogVo eventLogVo = eventLogDao.selectInitValueShipmentBilling(eventLogFilter);
            String changesMode = "";
            if (eventLogVo != null) {
                changesMode = eventLogVo.getChangesMode();
                String[] changesModeArr = changesMode.split("@,@");
                String[] modeArr;
                for (int i = 0; i < changesModeArr.length; i++) {
                    modeArr = changesModeArr[i].split(">");
                    if ("franchise_cost".equalsIgnoreCase(modeArr[Integer.valueOf(Attributes.FIELD_CHANGES_MODE_EVENTLOG)])) {
                        Double initCarrierCost = Double.valueOf(modeArr[Integer.valueOf(Attributes.VALUE_CHANGES_MODE_EVENTLOG)]);
                        ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
                        ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
                        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
                        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
                        shipmentBillingFilter.setShipmentId(shipmentId);
                        shipmentBillingFilter.setAirbillNumber(airbillNumber);
                        ShipmentBillingVo shipmentBillingBaseCharge = new ShipmentBillingVo();
                        List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectByFilter(shipmentBillingFilter);
                        for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
                            if (shipmentBilling.getIsBaseCharge()) {
                                Double carrierCostTaxPercent = shipmentBilling.getCarrierTaxPercent();
                                Double franchiseCostTaxAmount = initCarrierCost * carrierCostTaxPercent / 100;
                                shipmentBilling.setFranchiseCost(initCarrierCost);
                                shipmentBilling.setFranchiseTaxAmount(franchiseCostTaxAmount);
                                shipmentBillingDao.updateShipmentBillingForViewEdit(context, shipmentBillingBaseCharge);
                            } else {
                                shipmentBilling = ShipmentBillingUtils.recalculateAccessorialCharge(shipmentVo.getCustomerCode(), shipmentBillingBaseCharge, shipmentBilling);
                                shipmentBillingDao.updateShipmentBillingForViewEdit(context, shipmentBillingBaseCharge);
                            }
                        }
                    }
                }
            }
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
    public void recalcAccessorialMarkup(Map<String, String> context, Long shipmentId, String airbillNumber) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
            Long customerCode = shipmentVo.getCustomerCode();
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(airbillNumber);
            shipmentBillingFilter.setShipmentId(shipmentId);
            List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectSurchargesByFilter(shipmentBillingFilter);
            ShipmentBillingVo shipmentBillingBaseCharge = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
            ShipmentBillingVo shipmentBillingSurcharge = new ShipmentBillingVo();

            //New calculate FS XI 52
            Double baseChargeForFuelSurcharge = shipmentBillingBaseCharge.getCustomerCost();
            for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {

                //New calculate FS XI 52
                if (shipmentBilling.getDescription().equals("FUEL SURCHARGE")) {
                    baseChargeForFuelSurcharge = getBaseChargeforRecalculateFuelSurcharge(shipmentBillingBaseCharge, shipmentBillingVos, customerCode);
                    shipmentBillingBaseCharge.setCustomerCost(baseChargeForFuelSurcharge);
                } else {
                    shipmentBillingBaseCharge.setCustomerCost(shipmentBillingBaseCharge.getCustomerCost());
                }

                shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge(customerCode, shipmentBillingBaseCharge, shipmentBilling);
                shipmentBillingDao.updateShipmentBilling(context, shipmentBillingSurcharge);
            }
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
    public void markupAccessorial(Map<String, String> context, Double customerCost, Long shipmentId, String airbillNumber) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(airbillNumber);
            shipmentBillingFilter.setShipmentId(shipmentId);
            List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectSurchargesByFilter(shipmentBillingFilter);
            Double newCustomerCost = 0D, newCustomerTaxAmount = 0D;
            for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
                newCustomerCost = shipmentBilling.getCustomerCost() + customerCost;
                newCustomerTaxAmount = newCustomerCost * shipmentBilling.getCustomerTaxPercent() / 100;
                shipmentBilling.setCustomerCost(newCustomerCost);
                shipmentBilling.setCustomerTaxAmount(newCustomerTaxAmount);
                shipmentBillingDao.updateShipmentBilling(context, shipmentBilling);
            }
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
    public void changeServiceType(Map<String, String> context, Long shipmentId, String airbillNumber, String serviceLevel, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            String[] serviceLevelArr = serviceLevel.split(",");
            Integer shipmentTypeId = Integer.valueOf(serviceLevelArr[0]);
            Integer contents = Integer.valueOf(serviceLevelArr[1]);
            contents = (contents == -1) ? 1 : contents;
            Boolean billingContents = (contents == 0) ? false : true;
            Integer boundStatus = Integer.valueOf(serviceLevelArr[2]);
            String strBound = (boundStatus == 0) ? "" : " (Inbound)";
            Integer carrierId = Integer.valueOf(serviceLevelArr[3]);
            ShipmentHelper helper = new ShipmentHelper();
            String strContent = helper.getContentTypeName(contents, carrierId, Integer.valueOf(serviceLevelArr[1]));
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao(sessionClient);
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
            String description = "";
            if (shipmentTypeVo != null) {
                description = shipmentTypeVo.getShipmentTypeName();
            }
            String displayDescription = "";
            if (shipmentTypeVo.getDocument() || shipmentTypeVo.getDocumentInbound()) {
                if (contents == 1 && carrierId == 50) {
                    displayDescription = description + strBound;
                } else {
                    String blank = (StringUtils.isBlank(strContent)) ? "" : " ";
                    displayDescription = description + blank + strContent + strBound;
                }
            } else {
                displayDescription = description;
            }
            // Change service type for shipment
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentVo shipmentVo = new ShipmentVo();
            shipmentVo.setShipmentTypeId(shipmentTypeId);
            shipmentVo.setBoundStatus(boundStatus);
            shipmentVo.setContents(contents);
            shipmentVo.setShipmentId(shipmentId);
            shipmentDao.update(context, shipmentVo);

            // Change description for shipment billing
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingVo shipmentBillingVo = new ShipmentBillingVo();
            shipmentBillingVo.setPackageFlag(serviceLevelArr[1]);
            shipmentBillingVo.setBillingContents(billingContents);
            shipmentBillingVo.setDescription(description);
            shipmentBillingVo.setDisplayDescription(displayDescription);
            shipmentBillingVo.setShipmentId(shipmentId);
            shipmentBillingVo.setAirbillNumber(airbillNumber);
            shipmentBillingDao.updateShipmentBillingForViewEdit(context, shipmentBillingVo);
            if (checkRecalcCustomerCost) {
                ShipmentVo shipmentRecalc = shipmentDao.selectById(shipmentId);
                ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
                shipmentBillingFilter.setShipmentId(shipmentId);
                shipmentBillingFilter.setAirbillNumber(airbillNumber);
                ShipmentBillingVo shipmentBillingRecalc = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
                ShipmentBillingUtils.recalculateBaseCharge(shipmentRecalc.getCustomerCode(), shipmentBillingRecalc, iRecalculateCharge);
            }
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
    public void changeAccessorialType(Map<String, String> context, Long shipmentId, String airbillNumber, List<AccessorialInfoVo> accessorialInfoVos, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
            Long customerCode = shipmentVo.getCustomerCode();

            InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
            ShipmentInvoiceVo shipmentInvoiceVo = new ShipmentInvoiceVo();
            shipmentInvoiceVo.setShipmentId(shipmentId);
            shipmentInvoiceVo.setAirbillNumber(airbillNumber);
            MarkUpByAirbillShipmentVo markUpByAirbillShipmentVo = invoiceDao.selectMarkUpByAirbillShipment(shipmentInvoiceVo);
            Double markup = 0D;
            if (markUpByAirbillShipmentVo != null) {
                markup = markUpByAirbillShipmentVo.getMarkupRate();
                customerCode = markUpByAirbillShipmentVo.getCustomerCode();
            }
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(airbillNumber);
            shipmentBillingFilter.setShipmentId(shipmentId);
            ShipmentBillingVo shipmentBillingBaseCharge = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
            String[] descriptionExtArr;
            if (accessorialInfoVos != null && accessorialInfoVos.size() > 0) {
                ShipmentBillingVo shipmentBillingVoDel = new ShipmentBillingVo();
                shipmentBillingVoDel.setIsBaseCharge(false);
                shipmentBillingVoDel.setShipmentId(shipmentId);
                shipmentBillingVoDel.setAirbillNumber(airbillNumber);
                shipmentBillingDao.deleteShipmentBillingSurcharge(context, shipmentBillingVoDel);
                int accCount = 0;
                Double tax = 0D, customerCost = 0D, customerTaxAmount = 0D, carrierCost = 0D, franchiseCost = 0D, franchiseTaxAmount = 0D;
                for (AccessorialInfoVo accessorialInfo : accessorialInfoVos) {
                    descriptionExtArr = accessorialInfo.getDescriptionExt().split("#");
                    if (descriptionExtArr[0] != null && descriptionExtArr[0].equalsIgnoreCase(shipmentBillingBaseCharge.getCarrier().toString())) {
                        ShipmentBillingVo shipmentBillingSurcharge = new ShipmentBillingVo();
                        BeanUtils.copyProperties(shipmentBillingSurcharge, shipmentBillingBaseCharge);
                        shipmentBillingSurcharge.setDescription(descriptionExtArr[1]);
                        shipmentBillingSurcharge.setDisplayDescription(descriptionExtArr[1]);

                        tax = (accessorialInfo.getCustomerTaxPercent() == null) ? 0D : accessorialInfo.getCustomerTaxPercent();
                        customerCost = (accessorialInfo.getCustomerCost() == null) ? 0D : accessorialInfo.getCustomerCost();
                        customerTaxAmount = customerCost * tax / 100;
                        carrierCost = (accessorialInfo.getCarrierCost() == null) ? 0D : accessorialInfo.getCarrierCost();
                        franchiseCost = (accessorialInfo.getFranchiseCost() == null) ? 0D : accessorialInfo.getFranchiseCost();
                        if ("1".equalsIgnoreCase(shipmentBillingBaseCharge.getCarrier().toString())) {
                            franchiseCost = carrierCost + carrierCost * markup / 100;
                        }
                        franchiseTaxAmount = franchiseCost * tax / 100;
                        shipmentBillingSurcharge.setCarrierCost(carrierCost);
                        shipmentBillingSurcharge.setCalculatedCarrierCost(carrierCost);
                        shipmentBillingSurcharge.setFranchiseCost(franchiseCost);
                        shipmentBillingSurcharge.setFranchiseTaxAmount(franchiseTaxAmount);
                        shipmentBillingSurcharge.setCalculatedFranchiseCost(franchiseCost);
                        shipmentBillingSurcharge.setCustomerCost(customerCost);
                        shipmentBillingSurcharge.setCustomerTaxAmount(customerTaxAmount);
                        shipmentBillingSurcharge.setCustomerTaxPercent(tax);
                        shipmentBillingSurcharge.setGstPercent(tax);

                        shipmentBillingSurcharge.setRequireCalculate(accessorialInfo.getRequireCalculate());
                        shipmentBillingSurcharge.setIsBaseCharge(false);
                        shipmentBillingSurcharge.setAccessorialCount(++accCount);

                        if (shipmentBillingSurcharge.getRequireCalculate()) {
                            shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge(customerCode, shipmentBillingBaseCharge, shipmentBillingSurcharge);
                        }
                        // Save accessorials
                        shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingSurcharge);
                    }
                }
            }
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
    public void changeZone(Map<String, String> context, Long shipmentId, String airbillNumber, String zone, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            // Change zone
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingVo shipmentBillingVo = new ShipmentBillingVo();
            shipmentBillingVo.setAirbillNumber(airbillNumber);
            shipmentBillingVo.setShipmentId(shipmentId);
            shipmentBillingVo.setZone(zone);
            shipmentBillingDao.updateShipmentBilling(context, shipmentBillingVo);
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Log database action error
            logger.error(e);
            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }
        SqlSessionClient sessionClientRecal = new SqlSessionClient();
        sessionClientRecal.startTransaction();
        try {
            // Recalculate customer cost
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClientRecal);
            if (checkRecalcCustomerCost) {
                ShipmentDao shipmentDao = new ShipmentDao(sessionClientRecal);
                ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
                Long customerCode = shipmentVo.getCustomerCode();
                ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
                shipmentBillingFilter.setAirbillNumber(airbillNumber);
                shipmentBillingFilter.setShipmentId(shipmentId);
                List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectByFilter(shipmentBillingFilter);
                ShipmentBillingVo shipmentBillingBaseCharge = new ShipmentBillingVo();
                ShipmentBillingVo shipmentBillingSurcharge = new ShipmentBillingVo();
                for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
                    if (shipmentBilling.getIsBaseCharge()) {
                        shipmentBillingBaseCharge = shipmentBilling;
                        shipmentBillingBaseCharge = ShipmentBillingUtils.recalculateBaseCharge(customerCode, shipmentBillingBaseCharge, iRecalculateCharge);
                        shipmentBillingDao.updateShipmentBilling(context, shipmentBillingBaseCharge);
                    } else {
                        shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge(customerCode, shipmentBillingBaseCharge, shipmentBilling);
                        shipmentBillingDao.updateShipmentBilling(context, shipmentBillingSurcharge);
                    }
                }
            }
            // Commit transaction
            sessionClientRecal.endTransaction();
        } catch (Exception e) {
            // Log database action error
            logger.error(e);
            // Roll back transaction
            sessionClientRecal.rollback();
            throw e;
        }
    }

    @Override
    public void recalcCustomerCost(Map<String, String> context, Long shipmentId, String airbillNumber, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
            Long customerCode = shipmentVo.getCustomerCode();

            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(airbillNumber);
            shipmentBillingFilter.setShipmentId(shipmentId);
            // Recalculate customer cost basecharge
            ShipmentBillingVo shipmentBillingBaseCharge = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
            shipmentBillingBaseCharge = ShipmentBillingUtils.recalculateBaseCharge(customerCode, shipmentBillingBaseCharge, iRecalculateCharge);
            shipmentBillingDao.updateShipmentBilling(context, shipmentBillingBaseCharge);
            // Recalculate customer cost surcharge
            Double carrierTaxAmount = 0D, franchiseTaxAmount = 0D, customerTaxPercent = 0D;
            List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectSurchargesByFilter(shipmentBillingFilter);
            ShipmentBillingVo shipmentBillingSurcharge = new ShipmentBillingVo();
            for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
                if (shipmentBilling.getRequireCalculate()) {
                    shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge(customerCode, shipmentBillingBaseCharge, shipmentBilling);
                } else {
                    BeanUtils.copyProperties(shipmentBillingSurcharge, shipmentBilling);
                    customerTaxPercent = shipmentBilling.getCustomerTaxPercent();
                    franchiseTaxAmount = shipmentBilling.getFranchiseCost() * customerTaxPercent / 100;
                    shipmentBillingSurcharge.setFranchiseTaxAmount(franchiseTaxAmount);
                    carrierTaxAmount = shipmentBilling.getCarrierCost() * customerTaxPercent / 100;
                    shipmentBillingSurcharge.setTaxAmount(carrierTaxAmount);
                }
                shipmentBillingDao.updateShipmentBilling(context, shipmentBillingSurcharge);
            }
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
    public void forceQuotedCharge(Map<String, String> context, Long shipmentId, String airbillNumber, Integer adminLevel) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
            ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
            BigDecimal baseCharge = shipmentVo.getBaseCharge();

            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(airbillNumber);
            shipmentBillingFilter.setShipmentId(shipmentId);
            // Recalculate customer cost basecharge
            ShipmentBillingVo shipmentBillingBaseCharge = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
            Double defaultTaxPercent = getDefaultTaxPercent(shipmentBillingFilter, sessionClient);
            Double newCustomerBaseCost = 0D, newCustomerBaseTaxAmount = 0D;
            newCustomerBaseCost = baseCharge.doubleValue();
            newCustomerBaseTaxAmount = newCustomerBaseCost * defaultTaxPercent / 100;
            shipmentBillingBaseCharge.setCustomerCost(newCustomerBaseCost);
            shipmentBillingBaseCharge.setCustomerTaxAmount(newCustomerBaseTaxAmount);
            shipmentBillingBaseCharge.setCustomerTaxPercent(defaultTaxPercent);
            shipmentBillingDao.updateShipmentBilling(context, shipmentBillingBaseCharge);
            // Recalculate customer cost surcharge
            List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectSurchargesByFilter(shipmentBillingFilter);
            for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
                shipmentBillingDao.updateShipmentBilling(context, shipmentBilling);
            }

            // Get shipment detail
            List<ShipmentBillingVo> surcharges = shipmentBillingDao.selectSurchargesByFilter(shipmentBillingFilter);

            HistoryDetailFilter filter = new HistoryDetailFilter();
            filter.setShipmentId(shipmentId);
            HistoryDetailAccessorialDaoService dao = new HistoryDetailAccessorialDaoService();
            List<HistoryDetailAccessorialVo> detailAccessorials = dao.historyDetailAccessorial(filter);
            if (detailAccessorials != null && detailAccessorials.size() > 0) {
                for (ShipmentBillingVo surcharge : surcharges) {
                    for (HistoryDetailAccessorialVo detailAccessorial : detailAccessorials) {
                        try {
                            if (surcharge.getDescription().equalsIgnoreCase(detailAccessorial.getDescription())) {
                                surcharge.setCustomerCost(detailAccessorial.getAmount());
                                surcharge.setCustomerTaxAmount(surcharge.getCustomerCost() * surcharge.getCustomerTaxPercent() / 100);
                                shipmentBillingDao.updateShipmentBilling(context, surcharge);
                            }
                        } catch (Exception e) {
                            logger.error(e);
                        }
                    }
                }
            }
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
    public void forceQuotedAccessorialCharge(Map<String, String> context, Long shipmentId, String airbillNumber) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(airbillNumber);
            shipmentBillingFilter.setShipmentId(shipmentId);
            Double defaultTaxPercent = getDefaultTaxPercent(shipmentBillingFilter, sessionClient);
            List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectSurchargesByFilter(shipmentBillingFilter);
            Double amount = 0D, newCustomerCost = 0D, newCustomerTaxAmount = 0D;
            ShipmentDetailDao shipmentDetailDao = new ShipmentDetailDao(sessionClient);
            List<ShipmentDetailVo> shipmentDetailVos = shipmentDetailDao.getAllAccessorialByShipmentId(shipmentId);
            for (ShipmentDetailVo shipmentDetailVo : shipmentDetailVos) {
                amount = shipmentDetailVo.getAmount().doubleValue();
                AccessorialVo accessorialVo = shipmentDetailDao.joinAccessorial(shipmentDetailVo.getAccessorialId());
                for (ShipmentBillingVo shipmentBillingVo : shipmentBillingVos) {
                    if (accessorialVo.getDescription().equalsIgnoreCase(shipmentBillingVo.getDescription())) {
                        if (amount > shipmentBillingVo.getCustomerCost()) {
                            newCustomerCost = amount;
                            newCustomerTaxAmount = newCustomerCost * defaultTaxPercent / 100;
                            shipmentBillingVo.setCustomerCost(newCustomerCost);
                            shipmentBillingVo.setCustomerTaxAmount(newCustomerTaxAmount);
                            shipmentBillingVo.setCustomerTaxPercent(defaultTaxPercent);
                            shipmentBillingDao.updateShipmentBilling(context, shipmentBillingVo);
                        }
                    }
                }
            }
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
    public void forceMarkupCustomerCost(Map<String, String> context, Long shipmentId, String airbillNumber, Double markup) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(airbillNumber);
            shipmentBillingFilter.setShipmentId(shipmentId);
            Double defaultTaxPercent = getDefaultTaxPercent(shipmentBillingFilter, sessionClient);
            List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectByFilter(shipmentBillingFilter);
            Double newCustomerCost = 0D, newCustomerTaxAmount = 0D, carrierCost = 0D;
            for (ShipmentBillingVo shipmentBillingVo : shipmentBillingVos) {
                carrierCost = shipmentBillingVo.getCarrierCost();
                newCustomerCost = carrierCost + carrierCost * markup / 100;
                newCustomerTaxAmount = newCustomerCost * defaultTaxPercent / 100;
                shipmentBillingVo.setCustomerCost(newCustomerCost);
                shipmentBillingVo.setCustomerTaxAmount(newCustomerTaxAmount);
                shipmentBillingVo.setCustomerTaxPercent(defaultTaxPercent);
                shipmentBillingDao.updateShipmentBilling(context, shipmentBillingVo);
            }
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

    private Double getDefaultTaxPercent(ShipmentBillingFilter shipmentBillingFilter, SqlSessionClient sessionClient) throws DaoException {
        Double defaultTaxPercent = 0D;
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
        ShipmentBillingVo shipmentBillingBaseCharge = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
        ServiceDao serviceDao = new ServiceDao(sessionClient);
        ServiceVo serviceVo = serviceDao.selectById(shipmentBillingBaseCharge.getCarrier().intValue());
        Integer carrierType = serviceVo.getCarrierType();
        SystemSettingDao systemSettingDao = new SystemSettingDao(sessionClient);
        SystemSettingVo systemSettingVo;
        Long defaultCountryId = 0L;
        if (carrierType == 0) {
            systemSettingVo = systemSettingDao.getSystemSettingByName("Tax Percentage");
            defaultTaxPercent = Double.valueOf(systemSettingVo.getSettingValue());
        } else if (carrierType == 1) {
            systemSettingVo = systemSettingDao.getSystemSettingByName("Domestic Tax Percentage");
            defaultTaxPercent = Double.valueOf(systemSettingVo.getSettingValue());
        } else if (carrierType == 2) {
            systemSettingVo = systemSettingDao.getSystemSettingByName("Default Origin Country");
            defaultCountryId = Long.valueOf(systemSettingVo.getSettingValue());
            if (defaultCountryId == shipmentBillingBaseCharge.getOriginCountryId() && defaultCountryId == shipmentBillingBaseCharge.getDestinationCountryId()) {
                systemSettingVo = systemSettingDao.getSystemSettingByName("Domestic Tax Percentage");
                defaultTaxPercent = Double.valueOf(systemSettingVo.getSettingValue());
            } else {
                systemSettingVo = systemSettingDao.getSystemSettingByName("Tax Percentage");
                defaultTaxPercent = Double.valueOf(systemSettingVo.getSettingValue());
            }
        }
        return defaultTaxPercent;
    }

    private Double getBaseChargeforRecalculateFuelSurcharge(ShipmentBillingVo shipmentBillingBaseCharge, List<ShipmentBillingVo> listShipmentBilling, Long customerCode) throws Exception{
        Double baseCharge = shipmentBillingBaseCharge.getCustomerCost();

        for (ShipmentBillingVo baseChargeForRecalFS : listShipmentBilling) {
            baseChargeForRecalFS = ShipmentBillingUtils.recalculateAccessorialCharge(customerCode, shipmentBillingBaseCharge, baseChargeForRecalFS);

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
