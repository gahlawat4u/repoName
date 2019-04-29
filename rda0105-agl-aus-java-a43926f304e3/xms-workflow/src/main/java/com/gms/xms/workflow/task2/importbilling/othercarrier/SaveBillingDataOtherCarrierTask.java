package com.gms.xms.workflow.task2.importbilling.othercarrier;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.persistence.dao.*;
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
 * Posted from Jul 23, 2016 9:23:54 AM
 * <p>
 * Author huynd
 */
public class SaveBillingDataOtherCarrierTask implements Task2 {

    private static final Log log = LogFactory.getLog(SaveBillingDataOtherCarrierTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            String dateFormat = context.get(Attributes.DATE_FORMAT);
            BillingDataModel billingData = context.get(Attributes.BILLING_DATA);
            List<BillingRowDataModel> billingRowData = billingData.getRowData();
            Map<String, String> cellData;
            List<String> selectedFields = context.get(Attributes.IMPORT_FIELDS);
            Integer maxAccCount = 6, per = 2;
            if ((selectedFields.contains("access_1_description") && selectedFields.contains("access_1_cost") && selectedFields.contains("access_1_charge")) || (selectedFields.contains("access_2_description") && selectedFields.contains("access_2_cost") && selectedFields.contains("access_2_charge")) || (selectedFields.contains("access_3_description") && selectedFields.contains("access_3_cost") && selectedFields.contains("access_3_charge"))) {
                maxAccCount = 9;
                per = 3;
            }
            SaveImportBillingVo saveImport;
            AddressVo senderAddress, receiverAddress;
            Long carrier = Long.valueOf(context.getInt(Attributes.SERVICE_ID));
            ServiceDao serviceDao = new ServiceDao();
            ServiceVo serviceVo = serviceDao.selectById(context.getInt(Attributes.SERVICE_ID));
            Double defaultTaxPercent = 0D;
            if (serviceVo != null) {
                if (serviceVo.getCarrierType() == 0) {
                    defaultTaxPercent = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Tax Percentage"));
                } else {
                    defaultTaxPercent = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Domestic Tax Percentage"));
                }
            }
            Double carrierTaxPercent = 0D, customerTaxPercent = 0D;
            Boolean isInternationalShipment = context.get(Attributes.IS_INTERNATIONAL_SHIPMENT);
            Boolean applyCustomerTax = context.get(Attributes.APPLY_CUSTOMER_TAX);
            if (applyCustomerTax) {
                customerTaxPercent = defaultTaxPercent;
            }
            Boolean applyCarrierTax = context.get(Attributes.APPLY_CARRIER_TAX);
            if (applyCarrierTax) {
                carrierTaxPercent = defaultTaxPercent;
            }
            Long senderCountryId = null, receiverCountryId = null;
            if (!isInternationalShipment) {
                senderCountryId = 16L;
                receiverCountryId = 16L;
            }
            ShipmentBillingVo billingBaseCharge, billingSurCharge;
            ShipmentVo shipmentVo;
            ShipmentInvoiceVo shipmentInvoice;
            InvoiceVo invoice;
            String airbillNumber = "", checkAirbillNumber = "";
            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
            CheckShipmentVo checkShipment;
            CheckShipmentBillingVo checkShipmentBilling;
            CheckDuplicateAirbillVo checkDuplicateAirbillVo = null;
            Integer shipmentTypeId = 0;
            Long shipmentId, customerCode;
            String billingAccount = "";
            String description = "", displayDescript = "", ediDescription = "";
            String content = "Doc";
            Integer contentType = 1;
            String productContent = "DOX";
            Boolean billingContent = false;
            Boolean billingBound = false;
            String packageFlag = "0", zone = "";
            String serviceAreaCodeOrigin = "", serviceAreaCodeDestination = "";
            String shipperRef = "", shipperRef2 = "", shipperRef3 = "";
            Integer boundStatus = 0;
            Date shipDate, currentDate;
            String invoiceDate = context.getString(Attributes.INVOICE_DATE);
            Integer billingType = 0;
            Integer accessCount;
            Double carrierCost = 0D, taxAmount = 0D;
            Double franchiseCost = 0D, franchiseTaxAmount = 0D;
            Double customerCost = 0D, customerTaxAmount = 0D;
            CustomerDao customerDao = new CustomerDao();
            CustomerVo customerVo;
            Double miniumBaseChargePercent;
            List<ShipmentBillingVo> billingAccessorials;
            String accessDescription = "";
            Integer countSuccess = 0;
            Integer countUnSuccess = 0;
            Long index = 0L;
            List<String> errorList = new ArrayList<String>();
            for (BillingRowDataModel rowData : billingRowData) {
                accessCount = 0;
                index++;
                cellData = new HashMap<String, String>();
                cellData = rowData.getCellData();
                if (!StringUtils.isBlank(validateRowData(rowData, dateFormat, index))) {
                    errorList.add(validateRowData(rowData, dateFormat, index));
                    continue;
                }
                customerCode = StringUtils.isBlank(cellData.get("customer_code")) ? 0L : Long.valueOf(cellData.get("customer_code"));
                saveImport = new SaveImportBillingVo();
                shipmentVo = null;
                shipmentInvoice = null;
                invoice = null;
                checkDuplicateAirbillVo = null;
                billingAccessorials = new ArrayList<ShipmentBillingVo>();
                // Get data by fields
                shipDate = DateUtils.convertStringToDate(cellData.get("ship_date"), dateFormat, null);
                currentDate = new Date();
                // Sender address
                senderAddress = new AddressVo();
                senderAddress.setCompanyName(cellData.get("shipper_detail") == null ? "" : cellData.get("shipper_detail"));
                senderAddress.setContactName(cellData.get("shipper_name") == null ? "" : cellData.get("shipper_name"));
                senderAddress.setAddress(cellData.get("sender_address1") == null ? "" : cellData.get("sender_address1"));
                senderAddress.setAddress2(cellData.get("sender_address2") == null ? "" : cellData.get("sender_address2"));
                senderAddress.setCountry(senderCountryId);
                senderAddress.setCity(cellData.get("sender_city") == null ? "" : cellData.get("sender_city"));
                senderAddress.setPostalCode(cellData.get("sender_postcode") == null ? "" : cellData.get("sender_postcode"));
                senderAddress.setState(cellData.get("sender_state") == null ? "" : cellData.get("sender_state"));
                // Receiver address
                receiverAddress = new AddressVo();
                receiverAddress.setCompanyName(cellData.get("receiver_detail") == null ? "" : cellData.get("receiver_detail"));
                receiverAddress.setContactName(cellData.get("receiver_name") == null ? "" : cellData.get("receiver_name"));
                receiverAddress.setAddress(cellData.get("receiver_address1") == null ? "" : cellData.get("receiver_address1"));
                receiverAddress.setAddress2(cellData.get("receiver_address2") == null ? "" : cellData.get("receiver_address2"));
                receiverAddress.setCountry(receiverCountryId);
                receiverAddress.setCity(cellData.get("receiver_city") == null ? "" : cellData.get("receiver_city"));
                receiverAddress.setPostalCode(cellData.get("receiver_postcode") == null ? "" : cellData.get("receiver_postcode"));
                receiverAddress.setState(cellData.get("receiver_state") == null ? "" : cellData.get("receiver_state"));

                description = cellData.get("description") == null ? "" : cellData.get("description");
                ediDescription = cellData.get("product") == null ? "" : cellData.get("product");
                if (!StringUtils.isBlank(ediDescription)) {
                    ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
                    ShipmentTypeFilter shipmentTypeFilter = new ShipmentTypeFilter();
                    shipmentTypeFilter.setEdiDescription(ediDescription);
                    shipmentTypeFilter.setServiceId(carrier.intValue());
                    ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectByEdiDescription(shipmentTypeFilter);
                    if (shipmentTypeVo != null) {
                        description = shipmentTypeVo.getShipmentTypeName();
                        shipmentTypeId = shipmentTypeVo.getShipmentTypeId();
                    }
                }
                displayDescript = description;

                packageFlag = ImportBillingUtils.getPackageFlag(content, carrier);

                zone = (cellData.get("zone") == null) ? "" : cellData.get("zone");
                serviceAreaCodeOrigin = (cellData.get("origin_text") == null) ? "" : cellData.get("origin_text");
                serviceAreaCodeDestination = (cellData.get("destination_text") == null) ? "" : cellData.get("destination_text");
                shipperRef = (cellData.get("shipper_reference") == null) ? "" : cellData.get("shipper_reference");
                shipperRef2 = (cellData.get("shipper_reference2") == null) ? "" : cellData.get("shipper_reference2");
                shipperRef3 = (cellData.get("shipper_reference3") == null) ? "" : cellData.get("shipper_reference3");

                // Check airbill in shipment exist or not
                billingBaseCharge = new ShipmentBillingVo();
                airbillNumber = cellData.get("airbill_number");
                billingBaseCharge.setAirbillNumber(airbillNumber);
                billingBaseCharge.setCarrier(carrier);
                checkShipment = shipmentDao.checkShipment(billingBaseCharge);
                if (checkShipment != null) {
                    shipmentId = checkShipment.getShipmentId();
                    customerCode = (checkShipment.getCustomerCode() == 0 || checkShipment.getCustomerCode() == null) ? customerCode : checkShipment.getCustomerCode();
                    // Check airbill in shipment billing exist or not
                    checkShipmentBilling = new CheckShipmentBillingVo();
                    checkShipmentBilling.setCarrier(carrier);
                    checkShipmentBilling.setAirbillNumber(airbillNumber);
                    checkShipmentBilling.setLastChar(airbillNumber.substring(airbillNumber.length() - 1));
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
                    billingBaseCharge.setDisplayDescription(checkShipment.getOrgShipmentTypeName());

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
                    shipmentVo.setNoOfPieces(Double.valueOf(cellData.get("pieces") == null ? "0" : cellData.get("pieces")).intValue());
                    shipmentVo.setCurrencyCode(SystemSettingCache.getInstance().getValueByKey("CurrencyCode"));
                    shipmentVo.setServiceAreaCodeOrigin(serviceAreaCodeOrigin);
                    shipmentVo.setServiceAreaCodeDestination(serviceAreaCodeDestination);
                    shipmentVo.setReference(shipperRef);
                    shipmentVo.setReference2(shipperRef2);
                    shipmentVo.setReference3(shipperRef3);
                    shipmentVo.setProductContentCode(productContent);
                    shipmentVo.setSalesRepId(ShipmentUtils.getSaleRepId(String.valueOf(customerCode)));

                    // Check airbill in shipment billing exist or not
                    checkShipmentBilling = new CheckShipmentBillingVo();
                    checkShipmentBilling.setCarrier(carrier);
                    checkShipmentBilling.setAirbillNumber(airbillNumber);
                    checkShipmentBilling.setLastChar(airbillNumber.substring(airbillNumber.length() - 1));
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
                    billingBaseCharge.setDisplayDescription(displayDescript);

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
                billingBaseCharge.setCarrier(carrier);
                billingBaseCharge.setShipperReference(shipperRef);
                billingBaseCharge.setBillingReference2(shipperRef2);
                billingBaseCharge.setBillingReference3(shipperRef3);
                billingBaseCharge.setBillingContents(billingContent);
                billingBaseCharge.setBillingBound(billingBound);
                billingBaseCharge.setBillingFreightClass("");
                billingBaseCharge.setBillingReceivedBy("");
                billingBaseCharge.setBillingReceivedDate(null);
                billingBaseCharge.setBillingReference2("");
                billingBaseCharge.setBillingReference3("");
                billingBaseCharge.setBillingReweightWeight(0);
                billingBaseCharge.setWeight(Float.parseFloat(cellData.get("weight") == null ? "0" : cellData.get("weight")));
                billingBaseCharge.setWeightUnit("kg");
                billingBaseCharge.setShipDate(shipDate);
                billingBaseCharge.setOriginCountryId(senderCountryId);
                billingBaseCharge.setServiceAreaCodeOrigin(serviceAreaCodeOrigin);
                billingBaseCharge.setDestinationCountryId(receiverCountryId);
                billingBaseCharge.setServiceAreaCodeDestination(serviceAreaCodeDestination);
                billingBaseCharge.setPal(Double.valueOf(cellData.get("pieces") == null ? "0" : cellData.get("pieces")).intValue());
                billingBaseCharge.setTaxCode("");
                billingBaseCharge.setBillingType(billingType);
                billingBaseCharge.setBillingAccount("");
                billingBaseCharge.setBillToAccount("");
                billingBaseCharge.setDownloadFileId(0L);
                billingBaseCharge.setBillingReweightWeight(0);

                billingBaseCharge.setImportDate(currentDate);
                billingBaseCharge.setIsBaseCharge(true);
                billingBaseCharge.setAccessorialCount(accessCount);

                carrierCost = (StringUtils.isBlank(cellData.get("carrier_cost"))) ? 0D : Double.valueOf(cellData.get("carrier_cost"));
                taxAmount = carrierCost * carrierTaxPercent / 100;
                franchiseCost = carrierCost;
                franchiseTaxAmount = franchiseCost * carrierTaxPercent / 100;
                customerCost = (StringUtils.isBlank(cellData.get("customer_cost"))) ? 0D : Double.valueOf(cellData.get("customer_cost"));
                customerCost = (customerCost == 0) ? carrierCost : customerCost;
                miniumBaseChargePercent = 0D;
                customerVo = new CustomerVo();
                customerVo = customerDao.selectMinimumBaseChargeByCustomerCode(customerCode);
                if (customerVo != null) {
                    miniumBaseChargePercent = customerVo.getMinimunBaseCharge();
                }
                // Spread up customer cost
                customerCost = customerCost + customerCost * miniumBaseChargePercent / 100;
                customerTaxAmount = customerCost * customerTaxPercent / 100;

                billingBaseCharge.setCarrierCost(carrierCost);
                billingBaseCharge.setCalculatedCarrierCost(carrierCost);
                billingBaseCharge.setTaxAmount(taxAmount);
                billingBaseCharge.setCarrierTaxPercent(carrierTaxPercent);
                billingBaseCharge.setFranchiseCost(franchiseCost);
                billingBaseCharge.setCalculatedFranchiseCost(franchiseCost);
                billingBaseCharge.setFranchiseTaxAmount(franchiseTaxAmount);
                billingBaseCharge.setCustomerCost(customerCost);
                billingBaseCharge.setCustomerTaxAmount(customerTaxAmount);
                billingBaseCharge.setCustomerTaxPercent(customerTaxPercent);
                billingBaseCharge.setGstPercent(customerTaxPercent);
                billingBaseCharge.setOldCarrierCost(0D);
                billingBaseCharge.setOldTaxAmount(0D);
                billingBaseCharge.setOldTotalAccessorialCount(0);
                billingBaseCharge.setRequireCalculate(true);

                billingBaseCharge.setInsuranceDiscountAmount(0D);
                billingBaseCharge.setInsuranceAmount(0D);
                billingBaseCharge.setInsuranceTaxAmount(0D);
                billingBaseCharge.setZone(zone);
                billingBaseCharge.setInvoiceDate(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                ConvertUtils.register(new DateConverter(null), Date.class);

                // Accessorials surcharge
                for (int i = 1; i < maxAccCount; i += per) {
                    if (!StringUtils.isBlank(cellData.get(String.valueOf(i))) && !StringUtils.isBlank(cellData.get(String.valueOf(i + 1))) && Double.valueOf(cellData.get(String.valueOf(i + 1))) != 0) {
                        billingSurCharge = new ShipmentBillingVo();
                        BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                        accessDescription = cellData.get(String.valueOf(i));
                        accessDescription = ImportBillingUtils.checkAndSaveAccessorial(context, accessDescription, carrier);
                        billingSurCharge.setDescription(accessDescription);
                        billingSurCharge.setDisplayDescription(accessDescription);

                        carrierCost = Double.valueOf(cellData.get(String.valueOf(i + 1)));
                        taxAmount = carrierCost * carrierTaxPercent / 100;
                        franchiseCost = carrierCost;
                        franchiseTaxAmount = franchiseCost * carrierTaxPercent / 100;
                        customerCost = carrierCost;
                        customerTaxAmount = customerCost * customerTaxPercent / 100;

                        billingSurCharge.setCarrierCost(carrierCost);
                        billingSurCharge.setCalculatedCarrierCost(carrierCost);
                        billingSurCharge.setTaxAmount(0D);
                        billingSurCharge.setCarrierTaxPercent(0D);
                        billingSurCharge.setFranchiseCost(franchiseCost);
                        billingSurCharge.setCalculatedFranchiseCost(franchiseCost);
                        billingSurCharge.setFranchiseTaxAmount(0D);
                        billingSurCharge.setCustomerCost(customerCost);
                        billingSurCharge.setCustomerTaxAmount(customerTaxAmount);
                        billingSurCharge.setCustomerTaxPercent(customerTaxPercent);
                        billingSurCharge.setGstPercent(customerTaxPercent);

                        billingSurCharge.setRequireCalculate(true);
                        billingSurCharge.setIsBaseCharge(false);
                        billingSurCharge.setAccessorialCount(++accessCount);
                        billingAccessorials.add(billingSurCharge);
                    }
                }
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
                countSuccess++;
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
        String airbillNumber = cellData.get("airbill_number");
        if (airbillNumber == null || StringUtils.isBlank(airbillNumber)) {
            errorMsg += "Airbill number is blank";
        }
        Date shipDate = DateUtils.convertStringToDate(cellData.get("ship_date"), dateFormat, null);
        if (shipDate == null || StringUtils.isBlank(cellData.get("ship_date"))) {
            errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid ship date" : comma + " Invalid ship date";
        }
        try {
            Double.valueOf(cellData.get("pieces") == null ? "0" : cellData.get("pieces")).intValue();
        } catch (Exception e) {
            errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid number of pieces" : comma + " Invalid number of pieces";
        }
        try {
            Float.parseFloat(cellData.get("weight") == null ? "0" : cellData.get("weight"));
        } catch (Exception e) {
            errorMsg += (StringUtils.isBlank(errorMsg)) ? "Invalid weight" : comma + " Invalid weight";
        }
        if (!StringUtils.isBlank(errorMsg)) {
            errorMsg = "In line " + String.valueOf(index) + ": " + errorMsg + "\n";
        }
        return errorMsg;
    }

}
