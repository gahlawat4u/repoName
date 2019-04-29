package com.gms.xms.workflow.task2.importbilling.dhl.intl;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.country.CountryFilter;
import com.gms.xms.filter.importbilling.CustomerAccountFilter;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.importbilling.ImportBillingDao;
import com.gms.xms.persistence.service.importbilling.IImportBillingService;
import com.gms.xms.persistence.service.importbilling.ImportBillingServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.CheckDuplicateAirbillVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
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
 * Posted from Aug 17, 2016 10:23:15 AM
 * <p>
 * Author huynd
 */
public class SaveBillingDataDhlIntlTask implements Task2 {

    private static final Log log = LogFactory.getLog(SaveBillingDataDhlIntlTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            String dateFormat = context.get(Attributes.DATE_FORMAT);
            BillingDataModel billingData = context.get(Attributes.BILLING_DATA);
            List<BillingRowDataModel> billingRowData = billingData.getRowData();
            Map<String, String> cellData;
            SaveImportBillingVo saveImport = null;
            AddressVo senderAddress = null, receiverAddress = null;
            Long carrier = Long.valueOf(context.getInt(Attributes.SERVICE_ID));
            ShipmentBillingVo billingBaseCharge = null, billingSurCharge;
            ShipmentVo shipmentVo = null;
            ShipmentInvoiceVo shipmentInvoice = null;
            InvoiceVo invoice = null;
            String oriAirbillNumber = "", airbillNumber = "", checkAirbillNumber = "";
            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ImportBillingDao importBillingDao = new ImportBillingDao();
            CountryDao countryDao = new CountryDao();
            CheckShipmentVo checkShipment;
            CheckShipmentBillingVo checkShipmentBilling;
            CheckDuplicateAirbillVo checkDuplicateAirbillVo = null;
            ShipmentTypeVo shipmentTypeVo;
            ShipmentTypeFilter shipmentTypeFilter;
            CustomerAccountFilter customerAccountFilter;
            CountryFilter countryFilter;
            Integer shipmentTypeId = 0;
            Long shipmentId, customerCode = 0L;
            String billingAccount = "";
            Long senderCountryId = 0L, receiverCountryId = 0L;
            String originText = "", destinationText = "";
            String[] originTextArr, destinationTextArr;
            String product = "", ediDescription = "", description = "", displayDescript = "";
            String isContent = "";
            Integer contents = 0;
            String productContentCode = "";
            String packageFlag = "0";
            Boolean billingContent = false;
            Boolean billingBound = false;
            Integer boundStatus = 0;
            Date shipDate, currentDate;
            Double defaultTaxPercent = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Tax Percentage"));
            Long defaultOriginCountry = Long.valueOf(SystemSettingCache.getInstance().getValueByKey("Default Origin Country"));
            String invoiceDate = context.getString(Attributes.INVOICE_DATE);
            Integer accessCount = 0;
            Double tax = 0D;
            Double carrierCost = 0D, carrierTaxPercent = 0D, taxAmount = 0D;
            Double franchiseCost = 0D, franchiseTaxAmount = 0D;
            Double customerCost = 0D, customerTaxAmount = 0D;
            List<ShipmentBillingVo> billingAccessorials = null;
            String accessDescription = "";
            Integer countSuccess = 0, countUnSuccess = 0;
            Long index = 0L;
            List<String> errorList = new ArrayList<String>();
            BillingRowDataModel rowData;
            Boolean lastLine = false;
            for (int j = 0; j < billingRowData.size(); j++) {
                if (j == billingRowData.size() - 1) {
                    lastLine = true;
                }
                index++;
                cellData = new HashMap<String, String>();
                if (!lastLine) {
                    rowData = billingRowData.get(j);
                    cellData = rowData.getCellData();
                    description = cellData.get("charge_description");
                    if ("FREIGHT CHARGE".equalsIgnoreCase(description)) {
                        if (!StringUtils.isBlank(validateRowData(rowData, dateFormat, index))) {
                            errorList.add(validateRowData(rowData, dateFormat, index));
                            continue;
                        }
                    }
                }
                if ((!StringUtils.isBlank(airbillNumber) && !oriAirbillNumber.equalsIgnoreCase(cellData.get("airway_bill"))) || lastLine) {
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

                    IImportBillingService service = new ImportBillingServiceImp();
                    IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(addInfo);
                    service.saveImportBilling(addInfo, saveImport, shipmentVo, checkDuplicateAirbillVo, shipmentInvoice, invoice, iRecalculateCharge);
                    // Reset
                    if (saveImport != null) {
                        saveImport = null;
                    }
                    if (shipmentVo != null) {
                        shipmentVo = null;
                    }
                    if (shipmentInvoice != null) {
                        shipmentInvoice = null;
                    }
                    if (invoice != null) {
                        invoice = null;
                    }
                    accessCount = 0;
                    countSuccess++;
                    if (lastLine) {
                        break;
                    }
                }
                product = cellData.get("product");
                if ("FREIGHT CHARGE".equalsIgnoreCase(description) || accessCount == 0) { // Basecharge
                    saveImport = new SaveImportBillingVo();
                    shipmentVo = null;
                    shipmentInvoice = null;
                    invoice = null;
                    checkDuplicateAirbillVo = null;
                    accessCount = 0;
                    customerCode = 0L;
                    ediDescription = ImportBillingUtils.removeCodeStr(product);
                    shipmentTypeFilter = new ShipmentTypeFilter();
                    shipmentTypeFilter.setEdiDescription(ediDescription);
                    shipmentTypeFilter.setServiceId(carrier.intValue());
                    shipmentTypeVo = shipmentTypeDao.selectByEdiDescription(shipmentTypeFilter);
                    if (shipmentTypeVo != null) {
                        shipmentTypeId = shipmentTypeVo.getShipmentTypeId();
                        description = shipmentTypeVo.getShipmentTypeName();
                    }

                    shipDate = DateUtils.convertStringToDate(cellData.get("ship_date"), dateFormat, null);
                    currentDate = new Date();
                    // Sender address
                    senderAddress = new AddressVo();
                    countryFilter = new CountryFilter();
                    originText = cellData.get("origin_text");
                    originTextArr = originText.split(",");
                    if (originTextArr.length > 1) {
                        senderAddress.setCity(originTextArr[Integer.valueOf(Attributes.CITY_NAME)].trim());
                        countryFilter.setCityName(originTextArr[Integer.valueOf(Attributes.CITY_NAME)].trim());
                        countryFilter.setStationCode(cellData.get("origin_code"));
                        countryFilter.setCountryId(defaultOriginCountry);
                        senderCountryId = countryDao.getCountryIdByCityName(countryFilter);
                        senderCountryId = senderCountryId == null ? 16L : senderCountryId;
                    } else {
                        senderAddress.setCity(originText);
                        countryFilter.setCityName(originText);
                        countryFilter.setStationCode(cellData.get("origin_code"));
                        countryFilter.setCountryId(defaultOriginCountry);
                        senderCountryId = countryDao.getCountryIdByCityName(countryFilter);
                        senderCountryId = senderCountryId == null ? 16L : senderCountryId;
                    }
                    senderAddress.setCompanyName(cellData.get("sender_name"));
                    senderAddress.setCountry(senderCountryId);
                    senderAddress.setState(cellData.get("origin_code"));
                    // Receiver address
                    receiverAddress = new AddressVo();
                    countryFilter = new CountryFilter();
                    destinationText = cellData.get("destination_text");
                    destinationTextArr = destinationText.split(",");
                    if (destinationTextArr.length > 1) {
                        receiverAddress.setCity(destinationTextArr[Integer.valueOf(Attributes.CITY_NAME)].trim());
                        countryFilter.setCityName(destinationTextArr[Integer.valueOf(Attributes.CITY_NAME)].trim());
                        countryFilter.setStationCode(cellData.get("destination_code"));
                        countryFilter.setCountryId(defaultOriginCountry);
                        receiverCountryId = countryDao.getCountryIdByCityName(countryFilter);
                        receiverCountryId = receiverCountryId == null ? 0L : receiverCountryId;
                    } else {
                        receiverAddress.setCity(cellData.get("destination_text"));
                        countryFilter.setCityName(destinationText);
                        countryFilter.setStationCode(cellData.get("destination_code"));
                        countryFilter.setCountryId(defaultOriginCountry);
                        receiverCountryId = countryDao.getCountryIdByCityName(countryFilter);
                        receiverCountryId = receiverCountryId == null ? 0L : receiverCountryId;
                    }
                    receiverAddress.setCompanyName(cellData.get("receiver_name"));
                    receiverAddress.setCountry(receiverCountryId);
                    receiverAddress.setState(cellData.get("destination_code"));

                    if (product.toUpperCase().contains("NON DOC")) {
                        contents = 1;
                        productContentCode = "WPX";
                        billingContent = true;
                    } else {
                        contents = 0;
                        isContent = "Doc";
                        productContentCode = "DOC";
                        billingContent = false;
                    }
                    if (defaultOriginCountry == senderCountryId && senderCountryId != receiverCountryId) {
                        billingBound = false;
                        boundStatus = 0;
                    } else {
                        billingBound = true;
                        boundStatus = 1;
                    }
                    displayDescript = ImportBillingUtils.getServiceDisplayDescription(description, billingBound, contents, carrier);
                    packageFlag = ImportBillingUtils.getPackageFlag(isContent, carrier);

                    // Check airbill in shipment exist or not
                    billingBaseCharge = new ShipmentBillingVo();
                    airbillNumber = cellData.get("airway_bill");
                    oriAirbillNumber = airbillNumber;
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
                        checkShipmentBilling.setLastChar(airbillNumber.substring(airbillNumber.length() - 1));
                        checkAirbillNumber = shipmentBillingDao.checkShipmentBilling(checkShipmentBilling);
                        if (!StringUtils.isBlank(checkAirbillNumber)) {
                            airbillNumber = ImportBillingUtils.duplicateAibillNumber(airbillNumber, checkAirbillNumber);
                        }

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
                        billingAccount = (cellData.get("account") == null) ? "" : cellData.get("account");
                        if (!StringUtils.isBlank(billingAccount)) {
                            customerAccountFilter = new CustomerAccountFilter();
                            customerAccountFilter.setAccountNo(billingAccount);
                            customerAccountFilter.setFieldStr("dhl_account");
                            customerCode = importBillingDao.selectCustomerByAccount(customerAccountFilter);
                            customerCode = customerCode == null ? 0L : customerCode;
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
                        shipmentVo.setContents(contents);
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
                        shipmentVo.setBillingType(1);
                        shipmentVo.setTotalCustomValue(BigDecimal.valueOf(0D));
                        shipmentVo.setBillingAccount("");
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
                        shipmentVo.setShipmentDate(shipDate);
                        shipmentVo.setNoOfPieces(Integer.valueOf(StringUtils.isBlank(cellData.get("pieces")) ? "0" : cellData.get("pieces")));
                        shipmentVo.setCurrencyCode(SystemSettingCache.getInstance().getValueByKey("CurrencyCode"));
                        shipmentVo.setServiceAreaCodeOrigin(cellData.get("origin_code"));
                        shipmentVo.setServiceAreaCodeDestination(cellData.get("destination_code"));
                        shipmentVo.setReference(cellData.get("shipper_reference"));
                        shipmentVo.setProductContentCode(productContentCode);
                        shipmentVo.setSalesRepId(ShipmentUtils.getSaleRepId(String.valueOf(customerCode)));

                        // Check airbill in shipment billing exist or not
                        checkShipmentBilling = new CheckShipmentBillingVo();
                        checkShipmentBilling.setCarrier(carrier);
                        checkShipmentBilling.setAirbillNumber(airbillNumber);
                        checkShipmentBilling.setLastChar(airbillNumber.substring(airbillNumber.length() - 1));
                        checkAirbillNumber = shipmentBillingDao.checkShipmentBilling(checkShipmentBilling);
                        if (!StringUtils.isBlank(checkAirbillNumber)) {
                            airbillNumber = ImportBillingUtils.duplicateAibillNumber(airbillNumber, checkAirbillNumber);
                        }

                        shipmentInvoice = new ShipmentInvoiceVo();
                        shipmentInvoice = ImportBillingUtils.buildShipmentInvoice(shipmentId, airbillNumber, DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null), customerCode);
                        if (shipmentInvoice.getInvoiceId() == 0) {
                            invoice = new InvoiceVo();
                            invoice = ImportBillingUtils.buildInvoice(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null), customerCode);
                        }
                    }

                    billingBaseCharge.setShipmentId(shipmentId);
                    billingBaseCharge.setPackageFlag(packageFlag);
                    billingBaseCharge.setPaid(0L);
                    billingBaseCharge.setAirbillNumber(airbillNumber);
                    billingBaseCharge.setBillingShipmentTypeId(shipmentTypeId);
                    billingBaseCharge.setDescription(description);
                    billingBaseCharge.setDisplayDescription(displayDescript);
                    billingBaseCharge.setCarrier(carrier);
                    billingBaseCharge.setShipperReference(cellData.get("shipper_reference"));
                    billingBaseCharge.setBillingContents(billingContent);
                    billingBaseCharge.setBillingBound(billingBound);
                    billingBaseCharge.setBillingFreightClass("");
                    billingBaseCharge.setBillingReceivedBy("");
                    billingBaseCharge.setBillingReceivedDate(null);
                    billingBaseCharge.setBillingReference2("");
                    billingBaseCharge.setBillingReference3("");
                    billingBaseCharge.setBillingReweightWeight(0);
                    billingBaseCharge.setWeight(Float.parseFloat(StringUtils.isBlank(cellData.get("weight")) ? "0" : cellData.get("weight")));
                    billingBaseCharge.setWeightUnit("kg");
                    billingBaseCharge.setShipDate(shipDate);
                    billingBaseCharge.setOriginCountryId(senderCountryId);
                    billingBaseCharge.setServiceAreaCodeOrigin(cellData.get("origin_code"));
                    billingBaseCharge.setDestinationCountryId(receiverCountryId);
                    billingBaseCharge.setServiceAreaCodeDestination(cellData.get("destination_code"));
                    billingBaseCharge.setPal(Integer.valueOf(StringUtils.isBlank(cellData.get("pieces")) ? "0" : cellData.get("pieces")));
                    billingBaseCharge.setTaxCode("");
                    billingBaseCharge.setBillingType(0);
                    billingBaseCharge.setBillingAccount("");
                    billingBaseCharge.setBillToAccount("");
                    billingBaseCharge.setDownloadFileId(0L);
                    billingBaseCharge.setBillingReweightWeight(0);

                    billingBaseCharge.setImportDate(currentDate);
                    billingBaseCharge.setIsBaseCharge(true);
                    billingBaseCharge.setAccessorialCount(accessCount);

                    tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                    carrierCost = (StringUtils.isBlank(cellData.get("charge"))) ? 0D : Double.valueOf(cellData.get("charge"));
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
                    billingBaseCharge.setInvoiceDate(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                    ConvertUtils.register(new DateConverter(null), Date.class);
                    billingAccessorials = new ArrayList<ShipmentBillingVo>();
                    accessCount++;
                } else { // Surcharge
                    billingSurCharge = new ShipmentBillingVo();
                    BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                    accessDescription = cellData.get("charge_description");
                    accessDescription = ImportBillingUtils.checkAndSaveAccessorial(context, accessDescription, carrier);
                    billingSurCharge.setDescription(accessDescription);
                    billingSurCharge.setDisplayDescription(accessDescription);

                    tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                    carrierCost = (StringUtils.isBlank(cellData.get("charge"))) ? 0D : Double.valueOf(cellData.get("charge"));
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
                    billingSurCharge.setAccessorialCount(accessCount++);
                    billingAccessorials.add(billingSurCharge);
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

    private String validateRowData(BillingRowDataModel rowData, String dateFormat, Long index) {
        String errorMsg = "";
        String comma = ",";
        Map<String, String> cellData = new HashMap<String, String>();
        cellData = rowData.getCellData();
        String airbillNumber = cellData.get("airway_bill");
        if (airbillNumber == null || StringUtils.isBlank(airbillNumber)) {
            errorMsg += "Airbill number is blank";
        }
        Date shipDate = DateUtils.convertStringToDate(cellData.get("ship_date"), dateFormat, null);
        if (shipDate == null || StringUtils.isBlank(cellData.get("ship_date"))) {
            errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid ship date" : comma + " Invalid ship date";
        }
        try {
            Integer.valueOf(cellData.get("pieces"));
        } catch (Exception e) {
            errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid number of pieces" : comma + " Invalid number of pieces";
        }
        try {
            Double.valueOf(cellData.get("weight"));
        } catch (Exception e) {
            errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid weight" : comma + " Invalid weight";
        }
        if (!StringUtils.isBlank(errorMsg)) {
            errorMsg = "In line " + String.valueOf(index) + ": " + errorMsg + "\n";
        }
        return errorMsg;
    }

}
