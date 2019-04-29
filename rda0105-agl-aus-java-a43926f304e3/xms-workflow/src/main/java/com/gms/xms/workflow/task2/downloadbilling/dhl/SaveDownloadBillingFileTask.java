package com.gms.xms.workflow.task2.downloadbilling.dhl;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.DownloadBillingConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.importbilling.CustomerAccountFilter;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingResultModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.importbilling.ImportBillingDao;
import com.gms.xms.persistence.service.downloadbilling.DownloadBillingServiceImp;
import com.gms.xms.persistence.service.downloadbilling.IDownloadBillingService;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.dhl.DhlEsiZoneStationFilter;
import com.gms.xms.txndb.vo.dhl.DhlEsiZoneStationVo;
import com.gms.xms.txndb.vo.dhl.DhlZoneFilter;
import com.gms.xms.txndb.vo.dhl.DhlZoneVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ImportBillingUtils;
import com.gms.xms.workflow.utils.RecalculateChargeImp;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * Posted from Jul 30, 2016 12:41:04 PM
 * <p>
 * Author huynd
 */
public class SaveDownloadBillingFileTask implements Task2 {

    private static final Log log = LogFactory.getLog(SaveDownloadBillingFileTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            List<BillingDataModel> listBilling = context.get(Attributes.BILLING_DATA_FILE);
            BillingDataModel billingData;
            String fileName = "";
            Long downloadLogId = 0L;
            Date importDate = new Date();
            List<BillingRowDataModel> billingRowData;
            Map<String, String> cellData;
            SaveImportBillingVo saveImport = null;
            CountryDao countryDao = new CountryDao();
            ShipmentBillingVo billingBaseCharge = null, billingSurCharge, billingOverPayment = null;
            ShipmentVo shipmentVo = null;
            ShipmentInvoiceVo shipmentInvoice = null;
            InvoiceVo invoice = null;
            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ImportBillingDao importBillingDao = new ImportBillingDao();
            CheckShipmentVo checkShipment;
            CheckShipmentBillingVo checkShipmentBilling;
            ShipmentTypeVo shipmentTypeVo;
            ShipmentTypeFilter shipmentTypeFilter;
            CustomerAccountFilter customerAccountFilter;
            Long shipmentId = 0L;
            String packageFlag = "0";
            Date currentDate;
            Double defaultTaxPercent = 0D;
            Double defaultTaxPercentIntl = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Tax Percentage"));
            Double defaultTaxPercentDom = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Domestic Tax Percentage"));
            String perZoneOutBound = (SystemSettingCache.getInstance().getValueByKey("DHL Outbound PER Zone") == null) ? "" : SystemSettingCache.getInstance().getValueByKey("DHL Outbound PER Zone");
            String perZoneInBound = (SystemSettingCache.getInstance().getValueByKey("DHL Inbound PER Zone") == null) ? "" : SystemSettingCache.getInstance().getValueByKey("DHL Inbound PER Zone");
            String isOneAccount = SystemSettingCache.getInstance().getValueByKey("DHL Account use only one");
            List<ShipmentBillingVo> billingAccessorials = null;
            String[] dutiesAndTaxDescArray = new String[]{"IMPORT/EXPORT TAX", "IMPORT/EXPORT DUTY", "IMPORT EXPORT TAX", "IMPORT EXPORT DUTY", "DUTIES & TAXES PAID", "DUTIES TAXES PAID"};
            List<BillingResultModel> results = new ArrayList<BillingResultModel>();
            BillingResultModel billingResult;
            if (listBilling.size() > 0) {
                for (int j = 0; j < listBilling.size(); j++) {
                    billingData = new BillingDataModel();
                    billingData = listBilling.get(j);
                    fileName = billingData.getFileName();
                    downloadLogId = Long.valueOf(billingData.getDownloadFileId());
                    // importDate =
                    // DateUtils.convertStringToDate(billingData.getImportDate(),
                    // "yyyy-MM-dd", null);
                    billingRowData = billingData.getRowData();

                    String tmpDate = "", invoiceDate = "", shipDate = "", billingAccount = "", customerCode = "";
                    Integer boundStatus = 0;
                    Boolean billingBound = false;
                    String billToAccount = "";
                    AddressVo senderAddress = null, receiverAddress = null;
                    Long senderCountryId = 0L, receiverCountryId = 0L, origCountryId = 0L, destCountryId = 0L;
                    String senderCity = "", senderPostalCode = "", senderState = "";
                    String receiverCity = "", receiverPostalCode = "", receiverState = "";
                    String oriAirbillNumber = "", airbillNumber = "", checkAirbillNumber = "", description = "", ediDescription = "", displayDescript = "", accessDescription = "";
                    Double carrierCost = 0D, taxAmount = 0D;
                    Double franchiseCost = 0D, franchiseTaxAmount = 0D;
                    Double customerCost = 0D, customerTaxAmount = 0D;
                    Double insuranceAmount = 0D, insuranceTaxAmount = 0D, insuranceDiscountAmount = 0D;
                    String code = "", accDescription = "", weightUnit = "";
                    Integer pieces = 0;
                    String fieldStr = "", isMinus = "";
                    Double grossDiscountAmount = 0D;
                    Float weight = 0F;
                    currentDate = new Date();
                    Integer shipmentTypeId = 0, carrier = 1, packageId = 0;
                    String content = "D", productContent = "DOX";
                    Integer contentType = 0;
                    String zone = "1";
                    Integer accessCount = 0;
                    Integer countSuccess = 0;
                    Integer noLines = 0;
                    List<String> errorList = new ArrayList<String>();
                    for (BillingRowDataModel rowData : billingRowData) {
                        noLines++;
                        // If value # 0 then skip
                        carrierCost = (carrierCost == 0) ? 0D : carrierCost;
                        taxAmount = (taxAmount == 0) ? 0D : taxAmount;
                        franchiseCost = (franchiseCost == 0) ? 0D : franchiseCost;
                        franchiseTaxAmount = (franchiseTaxAmount == 0) ? 0D : franchiseTaxAmount;
                        customerCost = (customerCost == 0) ? 0D : customerCost;
                        customerTaxAmount = (customerTaxAmount == 0) ? 0D : customerTaxAmount;
                        insuranceAmount = (insuranceAmount == 0) ? 0D : insuranceAmount;
                        insuranceTaxAmount = (insuranceTaxAmount == 0) ? 0D : insuranceTaxAmount;
                        insuranceDiscountAmount = (insuranceDiscountAmount == 0) ? 0D : insuranceDiscountAmount;

                        saveImport = new SaveImportBillingVo();

                        cellData = new HashMap<String, String>();
                        cellData = rowData.getCellData();

                        if (!StringUtils.isBlank(airbillNumber) && !oriAirbillNumber.equalsIgnoreCase(cellData.get(DownloadBillingConstants.AIRBILL_NUMBER)) && ("AWB".equalsIgnoreCase(cellData.get(DownloadBillingConstants.TYPE)) || "TRL".equalsIgnoreCase(cellData.get(DownloadBillingConstants.TYPE)))) {
                            if (!ImportBillingUtils.isExistedCustomer(Long.valueOf(customerCode))) {
                                customerCode = "0";
                                shipmentVo = null;
                                shipmentInvoice = null;
                                invoice = null;
                            }
                            saveImport.setSenderAddress(senderAddress);
                            saveImport.setReceiverAddress(receiverAddress);
                            saveImport.setBillingBaseCharge(billingBaseCharge);
                            saveImport.setBillingAccessorials(billingAccessorials);

                            IDownloadBillingService service = new DownloadBillingServiceImp();
                            IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(addInfo);
                            service.saveDownloadBilling(addInfo, saveImport, shipmentVo, shipmentInvoice, invoice, billingOverPayment, iRecalculateCharge);
                            // Reset
                            if (shipmentVo != null) {
                                shipmentVo = null;
                            }
                            if (shipmentInvoice != null) {
                                shipmentInvoice = null;
                            }
                            if (invoice != null) {
                                invoice = null;
                            }
                            if (billingOverPayment != null) {
                                billingOverPayment = null;
                            }
                            countSuccess++;
                        }

                        switch (cellData.get(DownloadBillingConstants.TYPE)) {
                            case "HDR": // Header
                                if (!StringUtils.isBlank(validateRowData(rowData, noLines))) {
                                    errorList.add(validateRowData(rowData, noLines));
                                    continue;
                                }
                                tmpDate = cellData.get(DownloadBillingConstants.INVOICE_DATE);
                                invoiceDate = tmpDate.substring(0, 2) + "-" + tmpDate.substring(2, 4) + "-" + tmpDate.substring(4); // dd-mm-yyyy
                                break;
                            case "INV": // Invoice
                                if (!StringUtils.isBlank(validateRowData(rowData, noLines))) {
                                    errorList.add(validateRowData(rowData, noLines));
                                    continue;
                                }
                                billToAccount = cellData.get(DownloadBillingConstants.BILL_TO_ACCOUNT);
                                boundStatus = (cellData.get(DownloadBillingConstants.BOUND_STATUS).equalsIgnoreCase("0") || cellData.get(DownloadBillingConstants.BOUND_STATUS).equalsIgnoreCase("O")) ? 0 : 1;
                                billingBound = (boundStatus == 0) ? false : true;
                                break;
                            case "AWB": // Airbill
                                if (!StringUtils.isBlank(validateRowData(rowData, noLines))) {
                                    errorList.add(validateRowData(rowData, noLines));
                                    continue;
                                }
                                // Create new list accessorials
                                billingAccessorials = new ArrayList<ShipmentBillingVo>();

                                accessCount = 0;
                                billingBaseCharge = new ShipmentBillingVo();
                                billingBaseCharge.setIsBaseCharge(true);
                                tmpDate = cellData.get(DownloadBillingConstants.SHIP_DATE);
                                shipDate = tmpDate.substring(0, 2) + "-" + tmpDate.substring(2, 4) + "-" + tmpDate.substring(4); // dd-mm-yyyy

                                // Service Area Code
                                billingBaseCharge.setServiceAreaCodeOrigin(cellData.get(DownloadBillingConstants.SERVICE_AREA_CODE_ORIGIN));
                                billingBaseCharge.setServiceAreaCodeDestination(cellData.get(DownloadBillingConstants.SERVICE_AREA_CODE_DESTINATION));

                                billingAccount = cellData.get(DownloadBillingConstants.BILLING_ACCOUNT);
                                customerCode = cellData.get(DownloadBillingConstants.BILLING_ACCOUNT);

                                // Sender Address
                                senderAddress = new AddressVo();
                                senderCountryId = (countryDao.getCountryIdByCountryCode(cellData.get(DownloadBillingConstants.SENDER_COUNTRY_CODE)) == null) ? 0 : countryDao.getCountryIdByCountryCode(cellData.get(DownloadBillingConstants.SENDER_COUNTRY_CODE));
                                senderAddress.setCountry(senderCountryId);
                                senderAddress.setCompanyName(cellData.get(DownloadBillingConstants.SENDER_COMPANY));
                                senderAddress.setContactName(cellData.get(DownloadBillingConstants.SENDER_CONTACT));
                                senderAddress.setAddress(cellData.get(DownloadBillingConstants.SENDER_ADDRESS));
                                senderAddress.setAddress2(cellData.get(DownloadBillingConstants.SENDER_ADDRESS_2));
                                senderCity = cellData.get(DownloadBillingConstants.SENDER_CITY);
                                senderPostalCode = cellData.get(DownloadBillingConstants.SENDER_POSTCODE);
                                senderState = cellData.get(DownloadBillingConstants.SENDER_STATE);
                                senderAddress.setCity(senderCity);
                                senderAddress.setPostalCode(senderPostalCode);
                                senderAddress.setState(senderState);

                                // Receiver address
                                receiverAddress = new AddressVo();
                                receiverCountryId = (countryDao.getCountryIdByCountryCode(cellData.get(DownloadBillingConstants.RECEIVER_COUNTRY_CODE)) == null) ? 0 : countryDao.getCountryIdByCountryCode(cellData.get(DownloadBillingConstants.RECEIVER_COUNTRY_CODE));
                                receiverAddress.setCountry(receiverCountryId);
                                receiverAddress.setCompanyName(cellData.get(DownloadBillingConstants.RECEIVER_COMPANY));
                                receiverAddress.setContactName(cellData.get(DownloadBillingConstants.RECEIVER_CONTACT));
                                receiverAddress.setAddress(cellData.get(DownloadBillingConstants.RECEIVER_ADDRESS));
                                receiverAddress.setAddress2(cellData.get(DownloadBillingConstants.RECEIVER_ADDRESS_2));
                                receiverCity = cellData.get(DownloadBillingConstants.RECEIVER_CITY);
                                receiverPostalCode = cellData.get(DownloadBillingConstants.RECEIVER_POSTCODE);
                                receiverState = cellData.get(DownloadBillingConstants.RECEIVER_STATE);
                                receiverAddress.setCity(receiverCity);
                                receiverAddress.setPostalCode(receiverPostalCode);
                                receiverAddress.setState(receiverState);

                                origCountryId = (countryDao.getCountryIdByCountryCode(cellData.get(DownloadBillingConstants.ORIGIN_COUNTRY_CODE)) == null) ? 0 : countryDao.getCountryIdByCountryCode(cellData.get(DownloadBillingConstants.ORIGIN_COUNTRY_CODE));
                                destCountryId = (countryDao.getCountryIdByCountryCode(cellData.get(DownloadBillingConstants.DESTINATION_COUNTRY_CODE)) == null) ? 0 : countryDao.getCountryIdByCountryCode(cellData.get(DownloadBillingConstants.DESTINATION_COUNTRY_CODE));

                                // Get zone
                                description = cellData.get(DownloadBillingConstants.DESCRIPTION);
                                if (boundStatus == 0) { // Outbound
                                    String[] perZoneOutBoundArr = perZoneOutBound.split("#");
                                    if (!StringUtils.isBlank(perZoneOutBound) && perZoneOutBoundArr.length > 0 && senderCountryId == 16L && receiverCountryId == 150L && (senderState.equalsIgnoreCase("PER") || senderState.equalsIgnoreCase("WA"))) {
                                        // New Zealand zone
                                        zone = perZoneOutBoundArr[0];
                                    } else if (!StringUtils.isBlank(perZoneOutBound) && perZoneOutBoundArr.length > 1 && senderCountryId == 16L && receiverCountryId == 181L && (senderState.equalsIgnoreCase("PER") || senderState.equalsIgnoreCase("WA"))) {
                                        // Singpore zone
                                        zone = perZoneOutBoundArr[1];
                                    } else if (description.equalsIgnoreCase("ECONOMY SELECT")) {
                                        zone = getDhlESIZone(senderPostalCode, senderCity, boundStatus);
                                    } else {
                                        zone = getDhlIntlZone(receiverState, receiverCity, receiverCountryId, receiverPostalCode, boundStatus);
                                    }
                                } else { // Inbound
                                    String[] perZoneInBoundArr = perZoneInBound.split("#");
                                    if (!StringUtils.isBlank(perZoneInBound) && perZoneInBoundArr.length > 0 && senderCountryId == 150L && receiverCountryId == 16L && (senderState.equalsIgnoreCase("PER") || senderState.equalsIgnoreCase("WA"))) {
                                        // New Zealand zone
                                        zone = perZoneInBoundArr[0];
                                    } else if (!StringUtils.isBlank(perZoneInBound) && perZoneInBoundArr.length > 1 && senderCountryId == 181L && receiverCountryId == 16L && (senderState.equalsIgnoreCase("PER") || senderState.equalsIgnoreCase("WA"))) {
                                        // Singpore zone
                                        zone = perZoneInBoundArr[1];
                                    } else if (description.equalsIgnoreCase("ECONOMY SELECT")) {
                                        zone = getDhlESIZone(receiverPostalCode, receiverCity, boundStatus);
                                    } else {
                                        zone = getDhlIntlZone(senderState, senderCity, senderCountryId, senderPostalCode, boundStatus);
                                    }
                                }

                                ediDescription = ImportBillingUtils.removeCodeStr(description);
                                shipmentTypeFilter = new ShipmentTypeFilter();
                                shipmentTypeFilter.setEdiDescription(ediDescription);
                                shipmentTypeVo = shipmentTypeDao.selectByEdiDescription(shipmentTypeFilter);
                                if (shipmentTypeVo != null) {
                                    shipmentTypeId = shipmentTypeVo.getShipmentTypeId();
                                    description = shipmentTypeVo.getShipmentTypeName();
                                    carrier = shipmentTypeVo.getServiceId();
                                }

                                // Check International or Domestic Carrier
                                if (senderCountryId == receiverCountryId) { // Domestic
                                    carrier = 15;
                                    defaultTaxPercent = defaultTaxPercentDom;
                                    zone = "1";
                                } else { // International
                                    carrier = 1;
                                    defaultTaxPercent = defaultTaxPercentIntl;
                                }
                                content = cellData.get(DownloadBillingConstants.CONTENT_TYPE);
                                contentType = (content.equalsIgnoreCase("D")) ? 0 : 1;
                                productContent = (contentType == 0) ? "DOX" : "WPX";
                                displayDescript = ImportBillingUtils.getServiceDisplayDescription(description, billingBound, contentType, Long.valueOf(carrier));

                                // Check airbill in shipment exist or not
                                airbillNumber = cellData.get(DownloadBillingConstants.AIRBILL_NUMBER);
                                oriAirbillNumber = airbillNumber;
                                billingBaseCharge.setAirbillNumber(airbillNumber);
                                if (carrier == 1) {
                                    billingBaseCharge.setCarrier(null);
                                } else {
                                    billingBaseCharge.setCarrier(Long.valueOf(carrier));
                                }
                                checkShipment = shipmentDao.checkShipment(billingBaseCharge);
                                if (checkShipment != null) {
                                    shipmentId = checkShipment.getShipmentId();
                                    customerCode = checkShipment.getCustomerCode().toString();
                                    if (checkShipment.getShipmentTypeId() != 0) {
                                        shipmentTypeVo = new ShipmentTypeVo();
                                        shipmentTypeVo = shipmentTypeDao.selectById(checkShipment.getShipmentTypeId());
                                        shipmentTypeId = shipmentTypeVo.getShipmentTypeId();
                                        description = shipmentTypeVo.getShipmentTypeName();
                                        carrier = shipmentTypeVo.getServiceId();
                                        displayDescript = ImportBillingUtils.getServiceDisplayDescription(description, billingBound, contentType, Long.valueOf(carrier));
                                    }
                                    // Check airbill in shipment billing exist
                                    checkShipmentBilling = new CheckShipmentBillingVo();
                                    checkShipmentBilling.setCarrier(Long.valueOf(carrier));
                                    checkShipmentBilling.setAirbillNumber(airbillNumber);
                                    checkShipmentBilling.setLastChar(airbillNumber.substring(airbillNumber.length() - 1));
                                    checkAirbillNumber = shipmentBillingDao.checkShipmentBillingDHL(checkShipmentBilling);
                                    if (!StringUtils.isBlank(checkAirbillNumber)) {
                                        airbillNumber = ImportBillingUtils.duplicateAibillNumber(airbillNumber, checkAirbillNumber);
                                    }
                                } else {
                                    // No shipment
                                    shipmentId = 0L;
                                    shipmentVo = new ShipmentVo();
                                    if (isOneAccount.equalsIgnoreCase("1")) {
                                        fieldStr = "dhl_account";
                                    } else {
                                        if (origCountryId == destCountryId) { // DHL
                                            // domestic
                                            fieldStr = "dhl_domestic_account";
                                        } else {
                                            if (boundStatus == 0) { // DHL
                                                // international
                                                fieldStr = "dhl_international_account"; // Outbound
                                            } else {
                                                fieldStr = "dhl_inbound_account"; // Inbound
                                            }
                                        }
                                    }

                                    // Create new airbill with Billing account
                                    if (!StringUtils.isBlank(customerCode)) {
                                        if (importBillingDao.checkCustomerOrFranchise(customerCode) != null) {
                                            customerCode = importBillingDao.checkCustomerOrFranchise(customerCode);
                                        }
                                    }
                                    if (!StringUtils.isBlank(billingAccount)) {
                                        customerAccountFilter = new CustomerAccountFilter();
                                        customerAccountFilter.setAccountNo(billingAccount);
                                        customerAccountFilter.setFieldStr(fieldStr);
                                        customerCode = (importBillingDao.selectCustomerByAccount(customerAccountFilter) == null) ? customerCode : importBillingDao.selectCustomerByAccount(customerAccountFilter).toString();
                                    }
                                    packageId = getPackageIdByContent(contentType, carrier);

                                    shipmentVo.setShipmentId(shipmentId);
                                    shipmentVo.setCustomerCode(Long.valueOf(customerCode));
                                    shipmentVo.setWebshipId(0L);
                                    shipmentVo.setAirbillNumber(airbillNumber);
                                    shipmentVo.setShipmentTypeId(shipmentTypeId);
                                    shipmentVo.setPackageId(packageId);
                                    shipmentVo.setWithInsurance(BigDecimal.valueOf(0D));
                                    shipmentVo.setDay(0);
                                    shipmentVo.setSenderAddressId(0);
                                    shipmentVo.setReceiverAddressId(0);
                                    shipmentVo.setContents(contentType);
                                    shipmentVo.setWeightUnit("kg");
                                    shipmentVo.setDimensionUnit("cm");
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
                                    shipmentVo.setBillingType(1);
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
                                    shipmentVo.setNonStandardCharge(BigDecimal.ZERO);
                                    shipmentVo.setModifiedDate(currentDate);
                                    shipmentVo.setCreateDate(currentDate);
                                    shipmentVo.setBaseCharge(BigDecimal.valueOf(0D));
                                    shipmentVo.setShipmentDate(DateUtils.convertStringToDate(shipDate, "dd-MM-yyyy", null));
                                    shipmentVo.setNoOfPieces(0);
                                    shipmentVo.setCurrencyCode(SystemSettingCache.getInstance().getValueByKey("CurrencyCode"));
                                    shipmentVo.setServiceAreaCodeOrigin(cellData.get(DownloadBillingConstants.SERVICE_AREA_CODE_ORIGIN));
                                    shipmentVo.setServiceAreaCodeDestination(cellData.get(DownloadBillingConstants.SERVICE_AREA_CODE_DESTINATION));
                                    shipmentVo.setReference(cellData.get(DownloadBillingConstants.SHIPPER_REF));
                                    shipmentVo.setProductContentCode(productContent);
                                    shipmentVo.setSalesRepId(ShipmentUtils.getSaleRepId(String.valueOf(customerCode)));
                                    shipmentVo.setZone(zone);

                                    // Check airbill in shipment billing exist or
                                    // not
                                    checkShipmentBilling = new CheckShipmentBillingVo();
                                    checkShipmentBilling.setCarrier(Long.valueOf(carrier));
                                    checkShipmentBilling.setAirbillNumber(airbillNumber);
                                    checkShipmentBilling.setLastChar(airbillNumber.substring(airbillNumber.length() - 1));
                                    checkAirbillNumber = shipmentBillingDao.checkShipmentBillingDHL(checkShipmentBilling);
                                    if (!StringUtils.isBlank(checkAirbillNumber)) {
                                        airbillNumber = ImportBillingUtils.duplicateAibillNumber(airbillNumber, checkAirbillNumber);
                                    }

                                }
                                billingBaseCharge.setBillingShipmentTypeId(shipmentTypeId);
                                billingBaseCharge.setShipmentId(shipmentId);
                                billingBaseCharge.setPackageFlag(packageFlag);
                                billingBaseCharge.setPaid(0L);
                                billingBaseCharge.setAirbillNumber(airbillNumber);
                                billingBaseCharge.setDescription(description);
                                billingBaseCharge.setDisplayDescription(displayDescript);
                                billingBaseCharge.setCarrier(Long.valueOf(carrier));
                                billingBaseCharge.setShipperReference(cellData.get(DownloadBillingConstants.SHIPPER_REF));
                                if (contentType.intValue() == 0) {
                                    billingBaseCharge.setBillingContents(false);
                                } else {
                                    billingBaseCharge.setBillingContents(true);
                                }
                                billingBaseCharge.setBillingBound(billingBound);
                                billingBaseCharge.setBillingFreightClass("");
                                billingBaseCharge.setBillingReceivedBy("");
                                billingBaseCharge.setBillingReceivedDate(null);
                                billingBaseCharge.setBillingReference2("");
                                billingBaseCharge.setBillingReference3("");
                                billingBaseCharge.setBillingReweightWeight(0);
                                billingBaseCharge.setWeight(0F);
                                billingBaseCharge.setWeightUnit("kg");
                                billingBaseCharge.setShipDate(DateUtils.convertStringToDate(shipDate, "dd-MM-yyyy", null));
                                billingBaseCharge.setOriginCountryId(origCountryId);
                                billingBaseCharge.setDestinationCountryId(destCountryId);
                                billingBaseCharge.setPal(0);
                                billingBaseCharge.setTaxCode("");
                                billingBaseCharge.setBillingType(1);
                                billingBaseCharge.setBillingAccount("");
                                billingBaseCharge.setBillToAccount(billToAccount);
                                billingBaseCharge.setDownloadFileId(downloadLogId);
                                billingBaseCharge.setBillingReweightWeight(0);

                                billingBaseCharge.setPal(0);
                                billingBaseCharge.setWeight(0F);
                                billingBaseCharge.setWeightUnit(weightUnit);

                                billingBaseCharge.setCarrierCost(0D);
                                billingBaseCharge.setCalculatedCarrierCost(0D);
                                billingBaseCharge.setTaxAmount(0D);
                                billingBaseCharge.setCarrierTaxPercent(0D);
                                billingBaseCharge.setFranchiseCost(0D);
                                billingBaseCharge.setCalculatedFranchiseCost(0D);
                                billingBaseCharge.setFranchiseTaxAmount(0D);
                                billingBaseCharge.setCustomerCost(0D);
                                billingBaseCharge.setCustomerTaxAmount(0D);
                                billingBaseCharge.setCustomerTaxPercent(0D);
                                billingBaseCharge.setGstPercent(0D);

                                billingBaseCharge.setInsuranceDiscountAmount(insuranceDiscountAmount);
                                billingBaseCharge.setInsuranceAmount(insuranceAmount);
                                billingBaseCharge.setInsuranceTaxAmount(insuranceTaxAmount);

                                billingBaseCharge.setOldCarrierCost(0D);
                                billingBaseCharge.setOldTaxAmount(0D);
                                billingBaseCharge.setOldTotalAccessorialCount(0);

                                billingBaseCharge.setImportDate(importDate);
                                billingBaseCharge.setIsBaseCharge(true);
                                billingBaseCharge.setAccessorialCount(accessCount);

                                billingBaseCharge.setRequireCalculate(true);
                                billingBaseCharge.setZone(zone);
                                billingBaseCharge.setInvoiceDate(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                                ConvertUtils.register(new DateConverter(null), Date.class);
                                break;
                            case "CHG": // Surcharge
                                if (!StringUtils.isBlank(validateRowData(rowData, noLines))) {
                                    errorList.add(validateRowData(rowData, noLines));
                                    continue;
                                }
                                billingSurCharge = new ShipmentBillingVo();
                                BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                                code = cellData.get(DownloadBillingConstants.CODE);
                                accDescription = cellData.get(DownloadBillingConstants.ACC_DESCRIPTION);

                                // if carrier cost is negative amt,

                                // 1.delete the data in tbl_shipment_billing by
                                // airbill_number, shipmentid and description

                                // 2.delete the data in tbl_shipment_invoice by
                                // airbill_number and shipmentid

                                // 3.save as payments in customer_payment tbl

                                // 4.credit note create and save in
                                // tbl_credit_note,tbl_credit_note_detail

                                // 5.save in over payment table and others payment
                                // tables
                                isMinus = cellData.get(DownloadBillingConstants.CARRIER_COST).substring(0, 1);
                                if ("-".equalsIgnoreCase(isMinus)) {
                                    // billingOverPayment = new ShipmentBillingVo();
                                    // billingOverPayment.setAirbillNumber(airbillNumber);
                                    // billingOverPayment.setAirbillNumberEdi(oriAirbillNumber);
                                    // billingOverPayment.setShipmentId(shipmentId);
                                    // billingOverPayment.setDescription(description);
                                    // billingOverPayment.setCarrierCost(Double.valueOf(cellData.get(DownloadBillingConstants.CARRIER_COST).substring(1)));
                                    // billingOverPayment.setTaxAmount(taxAmount);
                                    continue;
                                }

                                // check amount for accessorial row
                                // if amount is zero, not save
                                // if description is blank, just update
                                // basecharge
                                String carrierCostStr = StringUtils.isBlank(cellData.get(DownloadBillingConstants.CARRIER_COST)) ? "0" : cellData.get(DownloadBillingConstants.CARRIER_COST);
                                carrierCost = Double.valueOf(carrierCostStr);
                                if (carrierCost <= 0) {
                                    continue;
                                }
                                String taxAmountStr = StringUtils.isBlank(cellData.get(DownloadBillingConstants.TAX_AMOUNT)) ? "0" : cellData.get(DownloadBillingConstants.TAX_AMOUNT);
                                taxAmount = Double.valueOf(taxAmountStr);
                                String grossDiscountAmountStr = StringUtils.isBlank(cellData.get(DownloadBillingConstants.GROSS_DISCOUNT_AMOUNT)) ? "0" : cellData.get(DownloadBillingConstants.GROSS_DISCOUNT_AMOUNT);
                                grossDiscountAmount = Double.valueOf(grossDiscountAmountStr);
                                carrierCost -= grossDiscountAmount;

                                franchiseCost = carrierCost;
                                franchiseTaxAmount = franchiseCost * defaultTaxPercent / 100;
                                customerCost = carrierCost;
                                customerTaxAmount = customerCost * defaultTaxPercent / 100;

                                String insuranceAmountStr = StringUtils.isBlank(cellData.get(DownloadBillingConstants.INSURANCE_AMOUNT)) ? "0" : cellData.get(DownloadBillingConstants.INSURANCE_AMOUNT);
                                insuranceAmount = Double.valueOf(insuranceAmountStr);
                                String insuranceTaxAmountStr = StringUtils.isBlank(cellData.get(DownloadBillingConstants.INSURANCE_TAX_AMOUNT)) ? "0" : cellData.get(DownloadBillingConstants.INSURANCE_TAX_AMOUNT);
                                insuranceTaxAmount = Double.valueOf(insuranceTaxAmountStr);
                                String insuranceDiscountAmountStr = StringUtils.isBlank(cellData.get(DownloadBillingConstants.INSURANCE_DISCOUNT_AMOUNT)) ? "0" : cellData.get(DownloadBillingConstants.INSURANCE_DISCOUNT_AMOUNT);
                                insuranceDiscountAmount = Double.valueOf(insuranceDiscountAmountStr);
                                insuranceAmount -= insuranceDiscountAmount;

                                if (StringUtils.isBlank(accDescription)) {
                                    pieces = Integer.valueOf(cellData.get(DownloadBillingConstants.PIECES));
                                    weight = Float.valueOf(cellData.get(DownloadBillingConstants.WEIGHT));
                                    weightUnit = cellData.get(DownloadBillingConstants.WEIGHT_UNIT);

                                    billingBaseCharge.setPal(pieces);
                                    billingBaseCharge.setWeight(weight);
                                    billingBaseCharge.setWeightUnit(weightUnit);

                                    // Update shipment base charge info
                                    billingBaseCharge.setCarrierCost(carrierCost);
                                    billingBaseCharge.setCalculatedCarrierCost(carrierCost);
                                    billingBaseCharge.setTaxAmount(taxAmount);
                                    billingBaseCharge.setCarrierTaxPercent(defaultTaxPercent);
                                    billingBaseCharge.setFranchiseCost(franchiseCost);
                                    billingBaseCharge.setCalculatedFranchiseCost(franchiseCost);
                                    billingBaseCharge.setFranchiseTaxAmount(franchiseTaxAmount);
                                    billingBaseCharge.setCustomerCost(customerCost);
                                    billingBaseCharge.setCustomerTaxAmount(customerTaxAmount);
                                    billingBaseCharge.setCustomerTaxPercent(defaultTaxPercent);
                                    billingBaseCharge.setGstPercent(defaultTaxPercent);

                                    billingBaseCharge.setInsuranceDiscountAmount(insuranceDiscountAmount);
                                    billingBaseCharge.setInsuranceAmount(insuranceAmount);
                                    billingBaseCharge.setInsuranceTaxAmount(insuranceTaxAmount);
                                    continue;
                                }

                                shipmentInvoice = new ShipmentInvoiceVo();
                                shipmentInvoice = ImportBillingUtils.buildShipmentInvoice(shipmentId, airbillNumber, DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null), Long.valueOf(customerCode));
                                if (shipmentInvoice.getInvoiceId() == 0) {
                                    invoice = new InvoiceVo();
                                    invoice = ImportBillingUtils.buildInvoice(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null), Long.valueOf(customerCode));
                                }

                                if (insuranceAmount != 0) {
                                    accessDescription = ImportBillingUtils.checkAndSaveAccessorialByCode(context, "INS", "INSURANCE", Long.valueOf(carrier));
                                    billingSurCharge.setDescription(accessDescription);
                                    billingSurCharge.setDisplayDescription(accessDescription);

                                    billingSurCharge.setCarrierCost(insuranceAmount);
                                    billingSurCharge.setCalculatedCarrierCost(insuranceAmount);
                                    billingSurCharge.setTaxAmount(0D);
                                    billingSurCharge.setCarrierTaxPercent(defaultTaxPercent);
                                    billingSurCharge.setFranchiseCost(insuranceAmount);
                                    billingSurCharge.setCalculatedFranchiseCost(insuranceAmount);
                                    billingSurCharge.setFranchiseTaxAmount(0D);
                                    billingSurCharge.setCustomerCost(insuranceAmount);
                                    billingSurCharge.setCustomerTaxAmount(0D);
                                    billingSurCharge.setCustomerTaxPercent(0D);
                                    billingSurCharge.setGstPercent(0D);
                                    billingSurCharge.setInsuranceAmount(0D);
                                    billingSurCharge.setGrossDiscountAmount(grossDiscountAmount);
                                    billingSurCharge.setInsuranceDiscountAmount(insuranceDiscountAmount);
                                    billingSurCharge.setInsuranceTaxAmount(insuranceTaxAmount);
                                    billingSurCharge.setTaxAmount(taxAmount);

                                    billingSurCharge.setRequireCalculate(true);
                                    billingSurCharge.setIsBaseCharge(false);
                                    billingSurCharge.setAccessorialCount(++accessCount);
                                    billingAccessorials.add(billingSurCharge);
                                }

                                // List accessorials
                                accessDescription = ImportBillingUtils.checkAndSaveAccessorialByCode(context, code, cellData.get(DownloadBillingConstants.ACC_DESCRIPTION), Long.valueOf(carrier));
                                if (ArrayUtils.contains(dutiesAndTaxDescArray, accessDescription.toUpperCase())) {
                                    billingSurCharge.setRequireCalculate(false);
                                } else {
                                    billingSurCharge.setRequireCalculate(true);
                                }
                                if (billingSurCharge.getCarrier() != null) {
                                    if (billingSurCharge.getCarrier() == 15) {
                                        defaultTaxPercent = defaultTaxPercentDom;
                                    } else if (billingSurCharge.getCarrier() == 1) {
                                        defaultTaxPercent = defaultTaxPercentIntl;
                                    }
                                }
                                billingSurCharge.setDescription(accessDescription);
                                billingSurCharge.setDisplayDescription(accessDescription);

                                billingSurCharge.setCarrierCost(carrierCost);
                                billingSurCharge.setCalculatedCarrierCost(carrierCost);
                                billingSurCharge.setTaxAmount(carrierCost * defaultTaxPercent / 100);
                                billingSurCharge.setCarrierTaxPercent(defaultTaxPercent);
                                billingSurCharge.setFranchiseCost(franchiseCost);
                                billingSurCharge.setCalculatedFranchiseCost(franchiseCost);
                                billingSurCharge.setFranchiseTaxAmount(franchiseCost * defaultTaxPercent / 100);
                                billingSurCharge.setCustomerCost(customerCost);
                                billingSurCharge.setCustomerTaxAmount(customerCost * defaultTaxPercent / 100);
                                billingSurCharge.setCustomerTaxPercent(defaultTaxPercent);
                                billingSurCharge.setGstPercent(defaultTaxPercent);
                                billingSurCharge.setInsuranceAmount(insuranceAmount);
                                billingSurCharge.setGrossDiscountAmount(grossDiscountAmount);
                                billingSurCharge.setInsuranceDiscountAmount(insuranceDiscountAmount);
                                billingSurCharge.setInsuranceTaxAmount(insuranceTaxAmount);
                                // billingSurCharge.setTaxAmount(taxAmount);

                                billingSurCharge.setRequireCalculate(true);
                                billingSurCharge.setIsBaseCharge(false);
                                billingSurCharge.setAccessorialCount(++accessCount);
                                billingAccessorials.add(billingSurCharge);
                                break;
                        }
                    }
                    billingResult = new BillingResultModel();
                    billingResult.setFileName(fileName);
                    billingResult.setNoLines(String.valueOf(noLines));
                    billingResult.setNoAirbill(String.valueOf(countSuccess));
                    billingResult.setErrorList(errorList);
                    results.add(billingResult);
                }
            } else {
                throw new Exception("No data to import");
            }
            context.put(Attributes.BILLING_RESULTS, results);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    private String validateRowData(BillingRowDataModel rowData, Integer noLines) {
        String errorMsg = "";
        String comma = ",";
        Map<String, String> cellData = new HashMap<String, String>();
        cellData = rowData.getCellData();
        switch (cellData.get(DownloadBillingConstants.TYPE)) {
            case "HDR":
                if (StringUtils.isBlank(cellData.get(DownloadBillingConstants.INVOICE_DATE))) {
                    errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invoice date is blank" : comma + " Invoice date is blank";
                }
                break;
            case "INV":
                break;
            case "AWB":
                if (StringUtils.isBlank(cellData.get(DownloadBillingConstants.SHIP_DATE))) {
                    errorMsg += (StringUtils.isBlank(errorMsg)) ? "Ship date is blank" : comma + " Ship date is blank";
                }
                if (StringUtils.isBlank(cellData.get(DownloadBillingConstants.AIRBILL_NUMBER))) {
                    errorMsg += (StringUtils.isBlank(errorMsg)) ? "Airbill number is blank" : comma + " Airbill number is blank";
                }
                break;
            case "CHG":
                try {
                    Integer.valueOf(cellData.get(DownloadBillingConstants.PIECES));
                } catch (Exception e) {
                    errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid number of pieces" : comma + " Invalid number of pieces";
                }
                try {
                    Float.valueOf(cellData.get(DownloadBillingConstants.WEIGHT));
                } catch (Exception e) {
                    errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid weight" : comma + " Invalid weight";
                }
                break;
        }
        if (!StringUtils.isBlank(errorMsg)) {
            errorMsg = "In line " + String.valueOf(noLines) + ": " + errorMsg + "\n";
        }
        return errorMsg;
    }

    private Integer getPackageIdByContent(Integer contentType, Integer carrier) throws DaoException {
        PackageDao packageDao = new PackageDao();
        PackageVo packageVo = new PackageVo();
        packageVo.setAddPiece(Byte.valueOf(contentType.toString()));
        packageVo.setCarrier(carrier);
        Integer packageId = (packageDao.getPackageIdByContent(packageVo) == null) ? 0 : packageDao.getPackageIdByContent(packageVo);
        return packageId;
    }

    private String getDhlESIZone(String postalCode, String city, Integer bound) throws DaoException {
        DhlEsiZoneStationVo dhlESI = new DhlEsiZoneStationVo();
        DhlEsiZoneStationFilter filter = new DhlEsiZoneStationFilter();
        DhlEsiZoneStationDao dao = new DhlEsiZoneStationDao();
        filter.setBound(bound);
        filter.setCityName(city);
        filter.setPostalCode(postalCode);
        dhlESI = dao.selectEsiZone(filter);
        if (dhlESI != null) {
            return dhlESI.getZone().toString();
        } else {
            return "0";
        }
    }

    private String getDhlIntlZone(String state, String city, Long countryId, String postalCode, Integer bound) throws DaoException {
        String[] dhlZone = {"0"};
        DhlZoneFilter filter = new DhlZoneFilter();
        DhlZoneDao dao = new DhlZoneDao();
        filter.setStateCode(state);
        filter.setCityName(city);
        filter.setCountryId(String.valueOf(countryId));
        filter.setPostalCode(postalCode);
        DhlZoneVo dhlZoneVo = dao.getDhlZoneByFilter(filter);
        if (dhlZoneVo != null) {
            String zone = dhlZoneVo.getDhlApZone();
            dhlZone = zone.split(",");
        }
        if (bound == null || bound == 0) {
            return dhlZone[0];
        } else if (bound != 0 && dhlZone.length > 1) {
            return dhlZone[1];
        } else {
            return dhlZone[0];
        }
    }

}
