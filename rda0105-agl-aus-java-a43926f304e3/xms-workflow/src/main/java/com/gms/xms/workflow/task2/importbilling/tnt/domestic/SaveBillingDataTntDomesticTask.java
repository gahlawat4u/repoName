package com.gms.xms.workflow.task2.importbilling.tnt.domestic;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.importbilling.CustomerAccountFilter;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.model.admin.imports.ImportBillingFieldsModel;
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
 * Posted from May 30, 2016 11:49:38 AM
 * <p>
 * Author huynd
 */
public class SaveBillingDataTntDomesticTask implements Task2 {

    private static final Log log = LogFactory.getLog(SaveBillingDataTntDomesticTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            String dateFormat = context.get(Attributes.DATE_FORMAT);
            BillingDataModel billingData = context.get(Attributes.BILLING_DATA);
            List<BillingRowDataModel> billingRowData = billingData.getRowData();
            Map<String, String> cellData;
            SaveImportBillingVo saveImport;
            AddressVo senderAddress, receiverAddress;
            Long carrier = 3L;
            Long senderCountryId = 16L, receiverCountryId = 16L;
            ShipmentBillingVo billingBaseCharge, billingSurCharge;
            ShipmentVo shipmentVo = null;
            ShipmentInvoiceVo shipmentInvoice;
            InvoiceVo invoice;
            String airbillNumber = "", checkAirbillNumber = "";
            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ImportBillingDao importBillingDao = new ImportBillingDao();
            CheckShipmentVo checkShipment;
            CheckShipmentBillingVo checkShipmentBilling;
            CheckDuplicateAirbillVo checkDuplicateAirbillVo;
            ShipmentTypeVo shipmentTypeVo;
            ShipmentTypeFilter shipmentTypeFilter;
            CustomerAccountFilter customerAccountFilter;
            Integer shipmentTypeId = 0;
            Long shipmentId, customerCode;
            String billingAccount;
            String serviceCode = "", description = "", displayDescript = "";
            String isContent = "";
            Boolean billingBound = false;
            String packageFlag = "0";
            Integer boundStatus = 0;
            Date shipDate, currentDate;
            Double defaultTaxPercent = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Domestic Tax Percentage"));
            String invoiceDate = context.getString(Attributes.INVOICE_DATE);
            Integer billingType = 0;
            Integer accessCount;
            Double netBasic = 0D, netFreight = 0D, netOther = 0D, netMinAddon = 0D;
            Double tax = 0D;
            Double carrierCost = 0D, carrierTaxPercent = 0D, taxAmount = 0D;
            Double franchiseCost = 0D, franchiseTaxAmount = 0D;
            Double customerCost = 0D, customerTaxAmount = 0D;
            List<ShipmentBillingVo> billingAccessorials;
            ImportBillingFieldsModel fields = context.get(Attributes.FIELDS);
            Map<String, String> accessorialData = fields.getAccessorials();
            String accessDescription = "";
            Integer countSuccess = 0, countUnSuccess = 0;
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
                customerCode = 0L;
                saveImport = new SaveImportBillingVo();
                billingAccessorials = new ArrayList<ShipmentBillingVo>();
                shipmentVo = null;
                shipmentInvoice = null;
                invoice = null;
                checkDuplicateAirbillVo = null;
                // Get data by fields
                shipDate = DateUtils.convertStringToDate(cellData.get("ship_date"), dateFormat, null);
                currentDate = new Date();
                // Sender address
                senderAddress = new AddressVo();
                senderAddress.setCountry(senderCountryId);
                senderAddress.setCompanyName(cellData.get("shipper_detail"));
                senderAddress.setCity(cellData.get("sender_city"));
                senderAddress.setPostalCode(cellData.get("sender_postcode"));
                // senderAddress.setState(cellData.get("service_area_code_origin"));
                // Receiver address
                receiverAddress = new AddressVo();
                receiverAddress.setCountry(receiverCountryId);
                receiverAddress.setCompanyName(cellData.get("receiver_detail"));
                receiverAddress.setCity(cellData.get("receiver_city"));
                receiverAddress.setPostalCode(cellData.get("receiver_postcode"));
                // receiverAddress.setState(cellData.get("service_area_code_destination"));

                serviceCode = cellData.get("service_code");
                shipmentTypeFilter = new ShipmentTypeFilter();
                shipmentTypeFilter.setServiceCode(serviceCode);
                shipmentTypeFilter.setServiceId(3);
                shipmentTypeVo = shipmentTypeDao.selectByServiceCodeAndCarrier(shipmentTypeFilter);
                if (shipmentTypeVo != null) {
                    shipmentTypeId = shipmentTypeVo.getShipmentTypeId();
                    description = shipmentTypeVo.getShipmentTypeName();
                }
                displayDescript = description;

                packageFlag = ImportBillingUtils.getPackageFlag(isContent, carrier);

                if ("S".equalsIgnoreCase(cellData.get("S_R_T_payer"))) {
                    billingType = 1;
                } else if ("R".equalsIgnoreCase(cellData.get("S_R_T_payer"))) {
                    billingType = 2;
                } else if ("T".equalsIgnoreCase(cellData.get("S_R_T_payer"))) {
                    billingType = 3;
                }

                // Check airbill in shipment exist or not
                billingBaseCharge = new ShipmentBillingVo();
                airbillNumber = cellData.get("airbill_number");
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
                        customerAccountFilter.setFieldStr("tnt_account");
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
                    shipmentVo.setContents(1);
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
                    shipmentVo.setNonStandardCharge(BigDecimal.ZERO);
                    shipmentVo.setModifiedDate(currentDate);
                    shipmentVo.setCreateDate(currentDate);
                    shipmentVo.setBaseCharge(BigDecimal.valueOf(0D));
                    shipmentVo.setShipmentDate(shipDate);
                    shipmentVo.setNoOfPieces(Integer.valueOf(cellData.get("pal")));
                    shipmentVo.setCurrencyCode(SystemSettingCache.getInstance().getValueByKey("CurrencyCode"));
                    shipmentVo.setServiceAreaCodeOrigin(cellData.get("service_area_code_origin"));
                    shipmentVo.setServiceAreaCodeDestination(cellData.get("service_area_code_destination"));
                    shipmentVo.setReference(cellData.get("shipper_reference"));
                    shipmentVo.setProductContentCode("WPX");
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
                billingBaseCharge.setDisplayDescription(displayDescript);
                billingBaseCharge.setCarrier(carrier);
                billingBaseCharge.setShipperReference(cellData.get("shipper_reference"));
                billingBaseCharge.setBillingContents(true);
                billingBaseCharge.setBillingBound(billingBound);
                billingBaseCharge.setBillingFreightClass("");
                billingBaseCharge.setBillingReceivedBy("");
                billingBaseCharge.setBillingReceivedDate(null);
                billingBaseCharge.setBillingReference2("");
                billingBaseCharge.setBillingReference3("");
                billingBaseCharge.setBillingReweightWeight(0);
                billingBaseCharge.setWeight(Float.parseFloat(cellData.get("weight")));
                billingBaseCharge.setWeightUnit("kg");
                billingBaseCharge.setShipDate(shipDate);
                billingBaseCharge.setOriginCountryId(senderCountryId);
                billingBaseCharge.setServiceAreaCodeOrigin(cellData.get("service_area_code_origin"));
                billingBaseCharge.setDestinationCountryId(receiverCountryId);
                billingBaseCharge.setServiceAreaCodeDestination(cellData.get("service_area_code_destination"));
                billingBaseCharge.setPal(Integer.valueOf(cellData.get("pal")));
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
                netBasic = (StringUtils.isBlank(cellData.get("NetBasic"))) ? 0D : Double.valueOf(cellData.get("NetBasic"));
                netFreight = (StringUtils.isBlank(cellData.get("NetFreight"))) ? 0D : Double.valueOf(cellData.get("NetFreight"));
                netOther = (StringUtils.isBlank(cellData.get("NetOther"))) ? 0D : Double.valueOf(cellData.get("NetOther"));
                netMinAddon = (StringUtils.isBlank(cellData.get("NetMinAddon"))) ? 0D : Double.valueOf(cellData.get("NetMinAddon"));
                carrierCost = netBasic + netFreight + netOther + netMinAddon;
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

                // Fuel surcharge
                if (!StringUtils.isBlank(cellData.get("surcharge")) && Double.valueOf(cellData.get("surcharge")) != 0) {
                    billingSurCharge = new ShipmentBillingVo();
                    BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                    billingSurCharge.setDescription("FUEL SURCHARGE");
                    billingSurCharge.setDisplayDescription("FUEL SURCHARGE");

                    tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                    carrierCost = Double.valueOf(cellData.get("surcharge"));
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
                for (int i = 1; i < 22; i++) {
                    if (!StringUtils.isBlank(cellData.get(String.valueOf(i))) && Double.valueOf(cellData.get(String.valueOf(i))) != 0) {
                        billingSurCharge = new ShipmentBillingVo();
                        BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                        accessDescription = accessorialData.get(String.valueOf(i));
                        accessDescription = ImportBillingUtils.checkAndSaveAccessorial(context, accessDescription, carrier);
                        billingSurCharge.setDescription(accessDescription);
                        billingSurCharge.setDisplayDescription(accessDescription);

                        tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                        carrierCost = Double.valueOf(cellData.get(String.valueOf(i)));
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
            Integer.valueOf(cellData.get("pal"));
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
