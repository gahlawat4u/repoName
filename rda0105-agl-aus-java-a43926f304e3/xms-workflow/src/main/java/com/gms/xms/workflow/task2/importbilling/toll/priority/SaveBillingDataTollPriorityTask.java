package com.gms.xms.workflow.task2.importbilling.toll.priority;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.importbilling.CustomerAccountFilter;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.importbilling.ImportBillingDao;
import com.gms.xms.persistence.dao.webship.TollPrioritySuburbDao;
import com.gms.xms.persistence.service.importbilling.IImportBillingService;
import com.gms.xms.persistence.service.importbilling.ImportBillingServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.CheckDuplicateAirbillVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.toll.TollPrioritySuburbFilter;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ImportBillingUtils;
import com.gms.xms.workflow.utils.RecalculateChargeImp;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * Posted from May 31, 2016 2:19:07 PM
 * <p>
 * Author huynd
 */
public class SaveBillingDataTollPriorityTask implements Task2 {

    private static final Log log = LogFactory.getLog(SaveBillingDataTollPriorityTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            String dateFormat = context.get(Attributes.DATE_FORMAT);
            BillingDataModel billingData = context.get(Attributes.BILLING_DATA);
            List<BillingRowDataModel> billingRowData = billingData.getRowData();
            Map<String, String> cellData;
            SaveImportBillingVo saveImport;
            AddressVo senderAddress = null, receiverAddress = null;
            Long carrier = 52L;
            Long senderCountryId = 16L, receiverCountryId = 16L;
            ShipmentBillingVo billingBaseCharge, billingSurCharge;
            ShipmentVo shipmentVo = null;
            ShipmentInvoiceVo shipmentInvoice = null;
            InvoiceVo invoice = null;
            String airbillNumber = "", checkAirbillNumber = "";
            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ImportBillingDao importBillingDao = new ImportBillingDao();
            CheckShipmentVo checkShipment;
            CheckShipmentBillingVo checkShipmentBilling;
            CheckDuplicateAirbillVo checkDuplicateAirbillVo = null;
            ShipmentTypeVo shipmentTypeVo;
            ShipmentTypeFilter shipmentTypeFilter;
            CustomerAccountFilter customerAccountFilter;
            Integer shipmentTypeId = 0;
            Long shipmentId, customerCode;
            String billingAccount;
            String serviceCode = "", description = "", displayDescript = "";
            String content = "Doc";
            Integer contentType = 0;
            String productContent = "DOX";
            Boolean billingContent = false;
            Boolean billingBound = false;
            String packageFlag = "0";
            String zone = "";
            Integer boundStatus = 0;
            Date shipDate, currentDate;
            Double defaultTaxPercent = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Domestic Tax Percentage"));
            String invoiceDate = context.getString(Attributes.INVOICE_DATE);
            Integer billingType = 0;
            Integer accessCount;
            Double tax = 0D;
            Double carrierCost = 0D, carrierTaxPercent = 0D, taxAmount = 0D;
            Double franchiseCost = 0D, franchiseTaxAmount = 0D;
            Double customerCost = 0D, customerTaxAmount = 0D;
            List<ShipmentBillingVo> billingAccessorials;
            Integer countSuccess = 0, countUnSuccess = 0;
            Long index = 0L;
            List<String> errorList = new ArrayList<String>();
            List<Map<String, Object>> saveDatas = new LinkedList<>();
            String oldAirbillNumber = "";
            billingBaseCharge = new ShipmentBillingVo();
            billingAccessorials = new ArrayList<ShipmentBillingVo>();
            customerCode = 0L;
            senderAddress = new AddressVo();
            receiverAddress = new AddressVo();
            for (BillingRowDataModel rowData : billingRowData) {
                accessCount = 0;
                index++;
                cellData = new HashMap<String, String>();
                cellData = rowData.getCellData();
                if (cellData != null) {
                    if ((!StringUtils.isBlank(cellData.get("ship_date")) && !StringUtils.isBlank(cellData.get("airbill_number"))) || (StringUtils.isBlank(cellData.get("ship_date")) && StringUtils.isBlank(cellData.get("airbill_number")))) {
                        if (!StringUtils.isBlank(validateRowData(rowData, dateFormat, index))) {
                            errorList.add(validateRowData(rowData, dateFormat, index));
                            continue;
                        }
                    }
                    if (StringUtils.isBlank(cellData.get("service_code"))) {
                        continue;
                    }
                }

                saveImport = new SaveImportBillingVo();
                log.info("Airbill number: " + airbillNumber);
                String shipDateStr = cellData != null ? cellData.get("ship_date") : "";
                if (!StringUtils.isBlank(oldAirbillNumber) && !StringUtils.isBlank(airbillNumber) && (cellData == null || !StringUtils.isBlank(shipDateStr))) {
                    if (!ImportBillingUtils.isExistedCustomer(customerCode)) {
                        customerCode = 0L;
                        shipmentVo = null;
                        shipmentInvoice = null;
                        invoice = null;
                    }
                    saveImport.setCustomerCode(customerCode);
                    saveImport.setSenderAddress(senderAddress);
                    saveImport.setReceiverAddress(receiverAddress);
                    saveImport.setBillingBaseCharge(billingBaseCharge);
                    saveImport.setBillingAccessorials(billingAccessorials);
                    Map<String, Object> billingDatas = new HashMap<>();
                    billingDatas.put("saveImport", saveImport);
                    billingDatas.put("shipmentVo", shipmentVo);
                    billingDatas.put("shipmentInvoice", shipmentInvoice);
                    billingDatas.put("invoice", invoice);
                    billingDatas.put("checkDuplicateAirbillVo", checkDuplicateAirbillVo);
                    saveDatas.add(billingDatas);
                    oldAirbillNumber = "";
                    shipmentVo = null;
                    shipmentInvoice = null;
                    checkDuplicateAirbillVo = null;
                    customerCode = 0L;
                    billingBaseCharge = new ShipmentBillingVo();
                    billingAccessorials = new ArrayList<ShipmentBillingVo>();
                    saveImport = new SaveImportBillingVo();
                    senderAddress = new AddressVo();
                    receiverAddress = new AddressVo();
                    if (cellData == null) {
                        continue;
                    }
                }

                // Get data by fields
                shipDate = DateUtils.convertStringToDate(cellData.get("ship_date"), dateFormat, null);
                currentDate = new Date();

                serviceCode = !StringUtils.isBlank(cellData.get("service_code")) && StringUtils.isNumeric(cellData.get("service_code")) ? Integer.valueOf(cellData.get("service_code")).toString() : "";
                String productCode = !StringUtils.isBlank(cellData.get("product_code")) && StringUtils.isNumeric(cellData.get("product_code")) ? Integer.valueOf(cellData.get("product_code")).toString() : "";
                String serviceGroup = serviceCode + "#" + productCode;
                shipmentTypeFilter = new ShipmentTypeFilter();
                shipmentTypeFilter.setServiceGroup(serviceGroup);
                shipmentTypeFilter.setServiceId(52);
                shipmentTypeVo = shipmentTypeDao.selectByServiceGroupAndServiceId(shipmentTypeFilter);
                if (shipmentTypeVo != null) {
                    shipmentTypeId = shipmentTypeVo.getShipmentTypeId();
                    description = shipmentTypeVo.getShipmentTypeName();
                }
                displayDescript = description;
                airbillNumber = cellData.get("airbill_number");

                if (StringUtils.isBlank(oldAirbillNumber) && shipDate != null) {
                    // Sender address
                    senderAddress.setCountry(senderCountryId);
                    senderAddress.setCompanyName(cellData.get("shipper_detail"));
                    senderAddress.setCity(cellData.get("sender_city"));
                    senderAddress.setPostalCode(cellData.get("sender_postcode"));
                    senderAddress.setState(cellData.get("service_area_code_origin"));
                    // Receiver address
                    receiverAddress.setCountry(receiverCountryId);
                    receiverAddress.setCompanyName(cellData.get("receiver_detail"));
                    receiverAddress.setCity(cellData.get("receiver_city"));
                    receiverAddress.setPostalCode(cellData.get("receiver_postcode"));
                    receiverAddress.setState(cellData.get("service_area_code_destination"));
                    billingAccessorials = new ArrayList<ShipmentBillingVo>();
                    billingBaseCharge = new ShipmentBillingVo();
                    customerCode = 0L;

                    // Check airbill in shipment exist or not
                    String noOfPiecesStr = !StringUtils.isBlank(cellData.get("pieces")) ? cellData.get("pieces") : "0";
                    Integer noOfPieces = Double.valueOf(noOfPiecesStr).intValue();

                    if (noOfPieces > 1) {
                        contentType = 1;
                        content = "";
                        productContent = "WPX";
                        billingContent = true;
                    }

                    packageFlag = ImportBillingUtils.getPackageFlag(content, carrier);

                    // Get Toll Priority zone
                    zone = getTollZone(cellData.get("sender_postcode"), cellData.get("sender_city"), cellData.get("receiver_postcode"), cellData.get("receiver_city"));
                    oldAirbillNumber = airbillNumber;
                    billingBaseCharge.setAirbillNumber(airbillNumber);
                    billingBaseCharge.setCarrier(carrier);
                    checkShipment = shipmentDao.checkShipment(billingBaseCharge);

                    if (checkShipment != null) {
                        shipmentId = checkShipment.getShipmentId();
                        customerCode = (checkShipment.getCustomerCode() == 0 || checkShipment.getCustomerCode() == null) ? 10000001L : checkShipment.getCustomerCode();
                        // Check airbill in shipment billing exist or not
                        checkShipmentBilling = new CheckShipmentBillingVo();
                        checkShipmentBilling.setCarrier(carrier);
                        checkShipmentBilling.setAirbillNumber(airbillNumber);
                        if (!StringUtils.isBlank(airbillNumber) && airbillNumber.length() > 2) {
                            checkShipmentBilling.setLastChar(airbillNumber.substring(airbillNumber.length() - 1));
                        } else {
                            checkShipmentBilling.setLastChar("");
                        }
                        checkAirbillNumber = shipmentBillingDao.checkShipmentBilling(checkShipmentBilling);
                        if (!StringUtils.isBlank(checkAirbillNumber)) {
                            String airbillNumberX = ImportBillingUtils.duplicateAibillNumber(airbillNumber, checkAirbillNumber);
                            checkDuplicateAirbillVo = new CheckDuplicateAirbillVo();
                            checkDuplicateAirbillVo.setAirbillNumber(checkAirbillNumber);
                            checkDuplicateAirbillVo.setAirbillNumberX(airbillNumberX);
                            checkDuplicateAirbillVo.setShipmentId(shipmentId);
                            checkDuplicateAirbillVo.setCarrier(carrier);
                            checkDuplicateAirbillVo.setImportDate(new Date());
                            checkDuplicateAirbillVo.setInvoiceDate(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                            countUnSuccess++;
                            countSuccess--;
                        }
                        billingBaseCharge.setBillingShipmentTypeId(shipmentTypeId);
                        billingBaseCharge.setDescription(checkShipment.getOrgShipmentTypeName());

                        shipmentInvoice = new ShipmentInvoiceVo();
                        shipmentInvoice = ImportBillingUtils.buildShipmentInvoice(shipmentId, airbillNumber, DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null), customerCode);
                        if (shipmentInvoice.getInvoiceId() == 0) {
                            invoice = new InvoiceVo();
                            invoice = ImportBillingUtils.buildInvoice(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null), customerCode);
                        }
                    } else {
                        // No shipment
                        shipmentVo = new ShipmentVo();
                        shipmentId = 0L;
                        billingAccount = (cellData.get("billing_account") == null) ? "" : cellData.get("billing_account");
                        if (!StringUtils.isBlank(billingAccount)) {
                            customerAccountFilter = new CustomerAccountFilter();
                            customerAccountFilter.setAccountNo(billingAccount);
                            customerAccountFilter.setFieldStr("toll_priority_account");
                            customerCode = importBillingDao.selectCustomerByAccount(customerAccountFilter);
                        }
                        shipmentVo.setShipmentId(shipmentId);
                        shipmentVo.setCustomerCode(customerCode);
                        shipmentVo.setWebshipId(0L);
                        shipmentVo.setAirbillNumber(airbillNumber);
                        shipmentVo.setShipmentTypeId(shipmentTypeId);
                        shipmentVo.setPackageId(0);
                        shipmentVo.setWithInsurance(BigDecimal.valueOf(0D));
                        shipmentVo.setDay(0);
                        shipmentVo.setSenderAddressId(0);
                        shipmentVo.setReceiverAddressId(0);
                        shipmentVo.setDimensionUnit("");
                        shipmentVo.setContents(contentType);
                        shipmentVo.setWeightUnit("kg");
                        shipmentVo.setCarrierCost(BigDecimal.valueOf(0D));
                        shipmentVo.setCarrierPayment(0);
                        shipmentVo.setDhlNote("");
                        shipmentVo.setBillingCode("");
                        shipmentVo.setCourierMessage("");
                        shipmentVo.setDhlRoutingCode("");
                        shipmentVo.setAwbBarcode("");
                        shipmentVo.setOriginDestiBarcode("");
                        shipmentVo.setDhlRoutingBarcode("");
                        shipmentVo.setStatus(0);
                        shipmentVo.setContentDescription("");
                        shipmentVo.setCommercialInvoiceId(0);
                        shipmentVo.setCollectionTypeId(0);
                        shipmentVo.setBillingType(billingType);
                        shipmentVo.setTotalCustomValue(BigDecimal.valueOf(0D));
                        shipmentVo.setBillingAccount(billingAccount);
                        shipmentVo.setDutiesType(2);
                        shipmentVo.setDutiesAccount("1");
                        shipmentVo.setTermOfTrade("DTU");
                        shipmentVo.setReceiverTaxId("");
                        shipmentVo.setReasonForExport("");
                        shipmentVo.setPackingList(1);
                        shipmentVo.setBoundStatus(boundStatus);
                        shipmentVo.setOldCustomerCode(0L);
                        shipmentVo.setAwbProductContentCode("");
                        shipmentVo.setZone(zone);
                        shipmentVo.setNonStandardCharge(BigDecimal.ZERO);
                        shipmentVo.setModifiedDate(currentDate);
                        shipmentVo.setCreateDate(currentDate);
                        shipmentVo.setBaseCharge(BigDecimal.valueOf(0D));
                        shipmentVo.setShipmentDate(shipDate);
                        shipmentVo.setNoOfPieces(noOfPieces);
                        shipmentVo.setCurrencyCode(SystemSettingCache.getInstance().getValueByKey("CurrencyCode"));
                        shipmentVo.setServiceAreaCodeOrigin(cellData.get("service_area_code_origin"));
                        shipmentVo.setServiceAreaCodeDestination(cellData.get("service_area_code_destination"));
                        shipmentVo.setReference(cellData.get("shipper_reference"));
                        shipmentVo.setReference2(cellData.get("shipper_reference2"));
                        shipmentVo.setProductContentCode(productContent);
                        shipmentVo.setSalesRepId(ShipmentUtils.getSaleRepId(String.valueOf(customerCode)));

                        // Check airbill in shipment billing exist or not
                        checkShipmentBilling = new CheckShipmentBillingVo();
                        checkShipmentBilling.setCarrier(carrier);
                        checkShipmentBilling.setAirbillNumber(airbillNumber);
                        if (!StringUtils.isBlank(airbillNumber) && airbillNumber.length() > 2) {
                            checkShipmentBilling.setLastChar(airbillNumber.substring(airbillNumber.length() - 1));
                        } else {
                            checkShipmentBilling.setLastChar("");
                        }

                        checkAirbillNumber = shipmentBillingDao.checkShipmentBilling(checkShipmentBilling);
                        if (!StringUtils.isBlank(checkAirbillNumber)) {
                            String airbillNumberX = ImportBillingUtils.duplicateAibillNumber(airbillNumber, checkAirbillNumber);
                            checkDuplicateAirbillVo = new CheckDuplicateAirbillVo();
                            checkDuplicateAirbillVo.setAirbillNumber(checkAirbillNumber);
                            checkDuplicateAirbillVo.setAirbillNumberX(airbillNumberX);
                            checkDuplicateAirbillVo.setShipmentId(shipmentId);
                            checkDuplicateAirbillVo.setCarrier(carrier);
                            checkDuplicateAirbillVo.setImportDate(new Date());
                            checkDuplicateAirbillVo.setInvoiceDate(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                            countUnSuccess++;
                            countSuccess--;
                        }
                        billingBaseCharge.setBillingShipmentTypeId(shipmentTypeId);
                        billingBaseCharge.setDescription(description);

                        shipmentInvoice = new ShipmentInvoiceVo();
                        shipmentInvoice = ImportBillingUtils.buildShipmentInvoice(shipmentId, airbillNumber, DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null), customerCode);
                        if (shipmentInvoice.getInvoiceId() == 0) {
                            invoice = new InvoiceVo();
                            invoice = ImportBillingUtils.buildInvoice(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null), customerCode);
                        }
                    }
                    saveImport.setCustomerCode(customerCode);
                    billingBaseCharge.setShipmentId(shipmentId);
                    billingBaseCharge.setPackageFlag(packageFlag);
                    billingBaseCharge.setPaid(0L);
                    billingBaseCharge.setAirbillNumber(airbillNumber);
                    billingBaseCharge.setDisplayDescription(displayDescript);
                    billingBaseCharge.setCarrier(carrier);
                    billingBaseCharge.setShipperReference(cellData.get("shipper_reference"));
                    billingBaseCharge.setBillingContents(billingContent);
                    billingBaseCharge.setBillingBound(billingBound);
                    billingBaseCharge.setBillingFreightClass("");
                    billingBaseCharge.setBillingReceivedBy("");
                    billingBaseCharge.setBillingReceivedDate(null);
                    billingBaseCharge.setBillingReference2(cellData.get("shipper_reference2"));
                    billingBaseCharge.setBillingReference3("");
                    billingBaseCharge.setBillingReweightWeight(0);
                    Float weight = !StringUtils.isBlank(cellData.get("weight")) ? Float.valueOf(cellData.get("weight")) : 0f;
                    billingBaseCharge.setWeight(weight);
                    billingBaseCharge.setWeightUnit("kg");
                    billingBaseCharge.setShipDate(shipDate);
                    billingBaseCharge.setOriginCountryId(senderCountryId);
                    billingBaseCharge.setServiceAreaCodeOrigin(cellData.get("service_area_code_origin"));
                    billingBaseCharge.setDestinationCountryId(receiverCountryId);
                    billingBaseCharge.setServiceAreaCodeDestination(cellData.get("service_area_code_destination"));
                    billingBaseCharge.setPal(noOfPieces);
                    billingBaseCharge.setTaxCode("");
                    billingBaseCharge.setBillingType(billingType);
                    billingBaseCharge.setBillingAccount("");
                    billingBaseCharge.setBillToAccount("");
                    billingBaseCharge.setDownloadFileId(0L);
                    billingBaseCharge.setBillingReweightWeight(0);

                    billingBaseCharge.setImportDate(currentDate);
                    billingBaseCharge.setIsBaseCharge(true);
                    billingBaseCharge.setAccessorialCount(accessCount);

                    tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                    carrierCost = (StringUtils.isBlank(cellData.get("carrier_cost"))) ? 0D : Double.valueOf(cellData.get("carrier_cost"));
                    carrierTaxPercent = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                    taxAmount = carrierCost * carrierTaxPercent / 100;
                    franchiseCost = carrierCost;
                    franchiseTaxAmount = franchiseCost * carrierTaxPercent / 100;
                    customerCost = carrierCost;
                    customerTaxAmount = customerCost * tax / 100;

                    billingBaseCharge.setCarrierCost(carrierCost);
                    billingBaseCharge.setCalculatedCarrierCost(carrierCost);
                    billingBaseCharge.setTaxAmount(taxAmount);
                    billingBaseCharge.setCarrierTaxPercent(carrierTaxPercent);
                    billingBaseCharge.setFranchiseCost(franchiseCost);
                    billingBaseCharge.setCalculatedFranchiseCost(franchiseCost);
                    billingBaseCharge.setFranchiseTaxAmount(franchiseTaxAmount);
                    billingBaseCharge.setCustomerCost(customerCost);
                    billingBaseCharge.setCustomerTaxAmount(customerTaxAmount);
                    billingBaseCharge.setCustomerTaxPercent(tax);
                    billingBaseCharge.setGstPercent(tax);
                    billingBaseCharge.setOldCarrierCost(0D);
                    billingBaseCharge.setOldTaxAmount(0D);
                    billingBaseCharge.setOldTotalAccessorialCount(0);

                    billingBaseCharge.setInsuranceDiscountAmount(0D);
                    billingBaseCharge.setInsuranceAmount(0D);
                    billingBaseCharge.setInsuranceTaxAmount(0D);
                    billingBaseCharge.setRequireCalculate(true);
                    billingBaseCharge.setZone(zone);
                    billingBaseCharge.setInvoiceDate(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                    ConvertUtils.register(new DateConverter(null), Date.class);

                    // Fuel surcharge
                    if (!StringUtils.isBlank(cellData.get("fuel_surcharge")) && Double.valueOf(cellData.get("fuel_surcharge")) != 0) {
                        billingSurCharge = new ShipmentBillingVo();
                        BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                        billingSurCharge.setDescription("FUEL SURCHARGE");
                        billingSurCharge.setDisplayDescription("FUEL SURCHARGE");

                        tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                        carrierCost = Double.valueOf(cellData.get("fuel_surcharge"));
                        carrierTaxPercent = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                        taxAmount = carrierCost * carrierTaxPercent / 100;
                        franchiseCost = carrierCost;
                        franchiseTaxAmount = franchiseCost * carrierTaxPercent / 100;
                        customerCost = carrierCost;
                        customerTaxAmount = customerCost * tax / 100;

                        billingSurCharge.setCarrierCost(carrierCost);
                        billingSurCharge.setCalculatedCarrierCost(carrierCost);
                        billingSurCharge.setTaxAmount(0D);
                        billingSurCharge.setCarrierTaxPercent(0D);
                        billingSurCharge.setFranchiseCost(franchiseCost);
                        billingSurCharge.setCalculatedFranchiseCost(franchiseCost);
                        billingSurCharge.setFranchiseTaxAmount(0D);
                        billingSurCharge.setCustomerCost(customerCost);
                        billingSurCharge.setCustomerTaxAmount(customerTaxAmount);
                        billingSurCharge.setCustomerTaxPercent(tax);
                        billingSurCharge.setGstPercent(tax);

                        billingSurCharge.setRequireCalculate(true);
                        billingSurCharge.setIsBaseCharge(false);
                        billingSurCharge.setAccessorialCount(++accessCount);
                        billingAccessorials.add(billingSurCharge);
                    }

                    // Security surcharge
                    if (!StringUtils.isBlank(cellData.get("security")) && Double.valueOf(cellData.get("security")) != 0) {
                        billingSurCharge = new ShipmentBillingVo();
                        BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                        billingSurCharge.setDescription("SECURITY");
                        billingSurCharge.setDisplayDescription("SECURITY");

                        tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                        carrierCost = Double.valueOf(cellData.get("security"));
                        carrierTaxPercent = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                        taxAmount = carrierCost * carrierTaxPercent / 100;
                        franchiseCost = carrierCost;
                        franchiseTaxAmount = franchiseCost * carrierTaxPercent / 100;
                        customerCost = carrierCost;
                        customerTaxAmount = customerCost * tax / 100;

                        billingSurCharge.setCarrierCost(carrierCost);
                        billingSurCharge.setCalculatedCarrierCost(carrierCost);
                        billingSurCharge.setTaxAmount(0D);
                        billingSurCharge.setCarrierTaxPercent(0D);
                        billingSurCharge.setFranchiseCost(franchiseCost);
                        billingSurCharge.setCalculatedFranchiseCost(franchiseCost);
                        billingSurCharge.setFranchiseTaxAmount(0D);
                        billingSurCharge.setCustomerCost(customerCost);
                        billingSurCharge.setCustomerTaxAmount(customerTaxAmount);
                        billingSurCharge.setCustomerTaxPercent(tax);
                        billingSurCharge.setGstPercent(tax);

                        billingSurCharge.setRequireCalculate(true);
                        billingSurCharge.setIsBaseCharge(false);
                        billingSurCharge.setAccessorialCount(++accessCount);
                        billingAccessorials.add(billingSurCharge);
                    }

                    // Carbon surcharge
                    if (!StringUtils.isBlank(cellData.get("carbon")) && Double.valueOf(cellData.get("carbon")) != 0) {
                        billingSurCharge = new ShipmentBillingVo();
                        BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                        billingSurCharge.setDescription("CARBON");
                        billingSurCharge.setDisplayDescription("CARBON");

                        tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                        carrierCost = Double.valueOf(cellData.get("carbon"));
                        carrierTaxPercent = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                        taxAmount = carrierCost * carrierTaxPercent / 100;
                        franchiseCost = carrierCost;
                        franchiseTaxAmount = franchiseCost * carrierTaxPercent / 100;
                        customerCost = carrierCost;
                        customerTaxAmount = customerCost * tax / 100;

                        billingSurCharge.setCarrierCost(carrierCost);
                        billingSurCharge.setCalculatedCarrierCost(carrierCost);
                        billingSurCharge.setTaxAmount(0D);
                        billingSurCharge.setCarrierTaxPercent(0D);
                        billingSurCharge.setFranchiseCost(franchiseCost);
                        billingSurCharge.setCalculatedFranchiseCost(franchiseCost);
                        billingSurCharge.setFranchiseTaxAmount(0D);
                        billingSurCharge.setCustomerCost(customerCost);
                        billingSurCharge.setCustomerTaxAmount(customerTaxAmount);
                        billingSurCharge.setCustomerTaxPercent(tax);
                        billingSurCharge.setGstPercent(tax);

                        billingSurCharge.setRequireCalculate(true);
                        billingSurCharge.setIsBaseCharge(false);
                        billingSurCharge.setAccessorialCount(++accessCount);
                        billingAccessorials.add(billingSurCharge);
                    }

                    // Area surcharge take the name of NT Surcharge
                    if (!StringUtils.isBlank(cellData.get("area")) && Double.valueOf(cellData.get("area")) != 0) {
                        billingSurCharge = new ShipmentBillingVo();
                        BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                        billingSurCharge.setDescription("NT Surcharge");
                        billingSurCharge.setDisplayDescription("NT Surcharge");

                        tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                        carrierCost = Double.valueOf(cellData.get("area"));
                        carrierTaxPercent = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                        taxAmount = carrierCost * carrierTaxPercent / 100;
                        franchiseCost = carrierCost;
                        franchiseTaxAmount = franchiseCost * carrierTaxPercent / 100;
                        customerCost = carrierCost;
                        customerTaxAmount = customerCost * tax / 100;

                        billingSurCharge.setCarrierCost(carrierCost);
                        billingSurCharge.setCalculatedCarrierCost(carrierCost);
                        billingSurCharge.setTaxAmount(0D);
                        billingSurCharge.setCarrierTaxPercent(0D);
                        billingSurCharge.setFranchiseCost(franchiseCost);
                        billingSurCharge.setCalculatedFranchiseCost(franchiseCost);
                        billingSurCharge.setFranchiseTaxAmount(0D);
                        billingSurCharge.setCustomerCost(customerCost);
                        billingSurCharge.setCustomerTaxAmount(customerTaxAmount);
                        billingSurCharge.setCustomerTaxPercent(tax);
                        billingSurCharge.setGstPercent(tax);

                        billingSurCharge.setRequireCalculate(true);
                        billingSurCharge.setIsBaseCharge(false);
                        billingSurCharge.setAccessorialCount(++accessCount);
                        billingAccessorials.add(billingSurCharge);
                    }
                } else if (!StringUtils.isBlank(oldAirbillNumber) && StringUtils.isBlank(cellData.get("ship_date")) && !StringUtils.isBlank(airbillNumber) && !StringUtils.isBlank(cellData.get("carrier_cost"))) {
                    billingSurCharge = new ShipmentBillingVo();
                    BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                    airbillNumber = airbillNumber.replace("Plus      ", "");
                    billingSurCharge.setDescription(airbillNumber);
                    billingSurCharge.setDisplayDescription(airbillNumber);

                    tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                    carrierCost = Double.valueOf(cellData.get("carrier_cost"));
                    carrierTaxPercent = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                    taxAmount = carrierCost * carrierTaxPercent / 100;
                    franchiseCost = carrierCost;
                    franchiseTaxAmount = franchiseCost * carrierTaxPercent / 100;
                    customerCost = carrierCost;
                    customerTaxAmount = customerCost * tax / 100;

                    billingSurCharge.setCarrierCost(carrierCost);
                    billingSurCharge.setCalculatedCarrierCost(carrierCost);
                    billingSurCharge.setTaxAmount(0D);
                    billingSurCharge.setCarrierTaxPercent(0D);
                    billingSurCharge.setFranchiseCost(franchiseCost);
                    billingSurCharge.setCalculatedFranchiseCost(franchiseCost);
                    billingSurCharge.setFranchiseTaxAmount(0D);
                    billingSurCharge.setCustomerCost(customerCost);
                    billingSurCharge.setCustomerTaxAmount(customerTaxAmount);
                    billingSurCharge.setCustomerTaxPercent(tax);
                    billingSurCharge.setGstPercent(tax);

                    billingSurCharge.setRequireCalculate(true);
                    billingSurCharge.setIsBaseCharge(false);
                    billingSurCharge.setAccessorialCount(++accessCount);
                    billingAccessorials.add(billingSurCharge);
                }
            }
            Map<String, String> checkMap = new HashMap<String, String>();
            for (Map<String, Object> map : saveDatas) {
                IImportBillingService service = new ImportBillingServiceImp();
                IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(addInfo);
                SaveImportBillingVo saveImportDatas = (SaveImportBillingVo) map.get("saveImport");
                String key = saveImportDatas.getBillingBaseCharge().getAirbillNumber();
                if (!checkMap.containsKey(key)) {
                    checkMap.put(key, key);
                    ShipmentVo saveShipmentVo = (ShipmentVo) map.get("shipmentVo");
                    ShipmentInvoiceVo saveShipmentInvoice = (ShipmentInvoiceVo) map.get("shipmentInvoice");
                    InvoiceVo saveInvoice = (InvoiceVo) map.get("invoice");
                    CheckDuplicateAirbillVo saveCheckDuplicateAirbillVo = (CheckDuplicateAirbillVo) map.get("checkDuplicateAirbillVo");
                    service.saveImportBilling(addInfo, saveImportDatas, saveShipmentVo, saveCheckDuplicateAirbillVo, saveShipmentInvoice, saveInvoice, iRecalculateCharge);
                    countSuccess++;
                } else {
                    countUnSuccess++;
                }
            }
            context.put(Attributes.COUNT_IMPORT_BILLING_SUCCESS, countSuccess);
            context.put(Attributes.COUNT_IMPORT_BILLING_UNSUCCESS, countUnSuccess);
            context.put(Attributes.ERROR_LIST, errorList);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    private String getTollZone(String senderPostalCode, String senderCity, String receiverPostalCode, String receiverCity) throws DaoException {
        TollPrioritySuburbDao tollPrioritySuburbDao = new TollPrioritySuburbDao();
        TollPrioritySuburbFilter filter = new TollPrioritySuburbFilter();
        // Get sender zone
        filter.setPostCode(senderPostalCode);
        filter.setSuburbName(senderCity);
        String senderZone = "0";
        if (tollPrioritySuburbDao.selectTollPriorityZone(filter) != null) {
            senderZone = tollPrioritySuburbDao.selectTollPriorityZone(filter);
        }
        // Get receiver zone
        filter.setPostCode(receiverPostalCode);
        filter.setSuburbName(receiverCity);
        String receiverZone = "0";
        if (tollPrioritySuburbDao.selectTollPriorityZone(filter) != null) {
            receiverZone = tollPrioritySuburbDao.selectTollPriorityZone(filter);
        }
        String zone = senderZone + "-" + receiverZone;
        return zone;
    }

    private String validateRowData(BillingRowDataModel rowData, String dateFormat, Long index) {
        String errorMsg = "";
        String comma = ",";
        Map<String, String> cellData = new HashMap<String, String>();
        cellData = rowData.getCellData();
        String airbillNumber = cellData.get("airbill_number");
        if (airbillNumber == null || StringUtils.isBlank(airbillNumber)) {
            errorMsg += "Airbill number is blank";
        }
        Date shipDate = DateUtils.convertStringToDate(cellData.get("ship_date"), dateFormat, null);
        if (shipDate == null || StringUtils.isBlank(cellData.get("ship_date"))) {
            errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid ship date" : comma + " Invalid ship date";
        }
        try {
            StringUtils.isNumeric(cellData.get("pieces"));
        } catch (Exception e) {
            errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid number of pieces" : comma + " Invalid number of pieces";
        }
        try {
            if (!StringUtils.isBlank(cellData.get("weight"))) {
                Double.valueOf(cellData.get("weight"));
            }
        } catch (Exception e) {
            errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid weight" : comma + " Invalid weight";
        }
        if (!StringUtils.isBlank(errorMsg)) {
            errorMsg = "In line " + String.valueOf(index) + ": " + errorMsg + "\n";
        }
        return errorMsg;
    }

}
