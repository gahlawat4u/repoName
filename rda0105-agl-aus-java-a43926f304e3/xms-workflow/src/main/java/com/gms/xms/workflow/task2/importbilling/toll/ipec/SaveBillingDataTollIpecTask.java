package com.gms.xms.workflow.task2.importbilling.toll.ipec;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.TollIpecZoneDao;
import com.gms.xms.persistence.service.importbilling.IImportBillingService;
import com.gms.xms.persistence.service.importbilling.ImportBillingServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.CheckDuplicateAirbillVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.TollIpecZoneFilter;
import com.gms.xms.txndb.vo.webship.TollIpecZoneVo;
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
 * Posted from Jun 2, 2016 8:36:31 AM
 * <p>
 * Author huynd
 */
public class SaveBillingDataTollIpecTask implements Task2 {

    private static final Log log = LogFactory.getLog(SaveBillingDataTollIpecTask.class);

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
            Long carrier = 59L;
            Long senderCountryId = 16L, receiverCountryId = 16L;
            ShipmentBillingVo billingBaseCharge, billingSurCharge;
            ShipmentInvoiceVo shipmentInvoice;
            InvoiceVo invoice;
            String airbillNumber = "", checkAirbillNumber = "";
            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            CheckShipmentVo checkShipment;
            CheckShipmentBillingVo checkShipmentBilling;
            CheckDuplicateAirbillVo checkDuplicateAirbillVo;
            ShipmentTypeVo shipmentTypeVo;
            ShipmentTypeFilter shipmentTypeFilter;
            Integer shipmentTypeId = 0;
            Long shipmentId, customerCode;
            String billingAccount = "";
            String serviceCode = "", description = "", displayDescript = "";
            String content = "Doc";
            Integer contentType = 1;
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
            String extraCharge1 = "", extraCharge2 = "", extraCharge3 = "", extraCharge4 = "", extraCharge5 = "";
            Double fuelSurcharge = 0D, baseCharge = 0D, extraAmt1 = 0D, extraAmt2 = 0D, extraAmt3 = 0D, extraAmt4 = 0D, extraAmt5 = 0D;
            Map<String, Double> extraCharges;
            List<ShipmentBillingVo> billingAccessorials;
            String accessDescription = "";
            Integer countSuccess = 0, countUnSuccess = 0;
            Long index = 0L;
            List<String> errorList = new ArrayList<String>();
            for (BillingRowDataModel rowData : billingRowData) {
                ShipmentVo shipmentVo = null;
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
                senderAddress.setCompanyName(cellData.get("sender_company"));
                senderAddress.setCity(cellData.get("from_suburb"));
                senderAddress.setPostalCode(cellData.get("from_pc"));
                // senderAddress.setState(cellData.get("from_zone"));
                // Receiver address
                receiverAddress = new AddressVo();
                receiverAddress.setCountry(receiverCountryId);
                receiverAddress.setCompanyName(cellData.get("receiver_company"));
                receiverAddress.setCity(cellData.get("to_suburb"));
                receiverAddress.setPostalCode(cellData.get("to_pc"));
                // receiverAddress.setState(cellData.get("to_zone"));

                description = cellData.get("description");
                serviceCode = cellData.get("service_code");
                if (!StringUtils.isBlank(serviceCode)) {
                    shipmentTypeFilter = new ShipmentTypeFilter();
                    shipmentTypeFilter.setServiceCode(serviceCode);
                    shipmentTypeFilter.setServiceId(59);
                    shipmentTypeVo = shipmentTypeDao.selectByServiceCodeAndCarrierTollIpec(shipmentTypeFilter);
                    if (shipmentTypeVo != null) {
                        shipmentTypeId = shipmentTypeVo.getShipmentTypeId();
                        description = shipmentTypeVo.getShipmentTypeName();
                    }
                    displayDescript = description;
                } else {
                    displayDescript = description;
                }

                packageFlag = ImportBillingUtils.getPackageFlag(content, carrier);

                // Get Toll Priority zone
                zone = getTollIpecZone(cellData.get("from_pc"), cellData.get("from_suburb"), cellData.get("to_pc"), cellData.get("to_suburb"));

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
                    customerCode = 10000001L;
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
                    shipmentVo.setNoOfPieces(Integer.valueOf(cellData.get("pieces")));
                    shipmentVo.setCurrencyCode(SystemSettingCache.getInstance().getValueByKey("CurrencyCode"));
                    shipmentVo.setServiceAreaCodeOrigin(cellData.get("from_zone"));
                    shipmentVo.setServiceAreaCodeDestination(cellData.get("to_zone"));
                    shipmentVo.setReference(cellData.get("shipper_reference"));
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
                billingBaseCharge.setBillingContents(billingContent);
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
                billingBaseCharge.setServiceAreaCodeOrigin(cellData.get("from_zone"));
                billingBaseCharge.setDestinationCountryId(receiverCountryId);
                billingBaseCharge.setServiceAreaCodeDestination(cellData.get("to_zone"));
                billingBaseCharge.setPal(Integer.valueOf(cellData.get("pieces")));
                billingBaseCharge.setTaxCode("");
                billingBaseCharge.setBillingType(billingType);
                billingBaseCharge.setBillingAccount("");
                billingBaseCharge.setBillToAccount("");
                billingBaseCharge.setDownloadFileId(0L);
                billingBaseCharge.setBillingReweightWeight(0);

                billingBaseCharge.setImportDate(currentDate);
                billingBaseCharge.setIsBaseCharge(true);
                billingBaseCharge.setAccessorialCount(accessCount);

                extraCharge1 = cellData.get("extra_charge1");
                extraCharge2 = cellData.get("extra_charge2");
                extraCharge3 = cellData.get("extra_charge3");
                extraCharge4 = cellData.get("extra_charge4");
                extraCharge5 = cellData.get("extra_charge5");
                fuelSurcharge = (StringUtils.isBlank(cellData.get("fuel_surcharge"))) ? 0D : Double.valueOf(cellData.get("fuel_surcharge"));
                baseCharge = (StringUtils.isBlank(cellData.get("base_charge"))) ? 0D : Double.valueOf(cellData.get("base_charge"));
                carrierCost = (StringUtils.isBlank(cellData.get("carrier_cost"))) ? 0D : Double.valueOf(cellData.get("carrier_cost"));
                extraAmt1 = (StringUtils.isBlank(cellData.get("extra_amt1"))) ? 0D : Double.valueOf(cellData.get("extra_amt1"));
                extraAmt2 = (StringUtils.isBlank(cellData.get("extra_amt2"))) ? 0D : Double.valueOf(cellData.get("extra_amt2"));
                extraAmt3 = (StringUtils.isBlank(cellData.get("extra_amt3"))) ? 0D : Double.valueOf(cellData.get("extra_amt3"));
                extraAmt4 = (StringUtils.isBlank(cellData.get("extra_amt4"))) ? 0D : Double.valueOf(cellData.get("extra_amt4"));
                extraAmt5 = (StringUtils.isBlank(cellData.get("extra_amt5"))) ? 0D : Double.valueOf(cellData.get("extra_amt5"));
                extraCharges = new HashMap<>();
                extraCharges.put(extraCharge1, extraAmt1);
                extraCharges.put(extraCharge2, extraAmt2);
                extraCharges.put(extraCharge3, extraAmt3);
                extraCharges.put(extraCharge4, extraAmt4);
                extraCharges.put(extraCharge5, extraAmt5);

                if (description.equalsIgnoreCase("RE-DELIVERY FEE") || extraCharge1.equalsIgnoreCase("FR") || extraCharge2.equalsIgnoreCase("FR") || extraCharge3.equalsIgnoreCase("FR")) {
                    carrierCost = 0D;
                    fuelSurcharge = 0D;
                    billingBaseCharge.setRequireCalculate(false);
                } else {
                    carrierCost = baseCharge + carrierCost - fuelSurcharge - extraAmt1 - extraAmt2 - extraAmt3;
                    billingBaseCharge.setRequireCalculate(true);
                }

                tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
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
                billingBaseCharge.setZone(zone);
                billingBaseCharge.setInvoiceDate(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                ConvertUtils.register(new DateConverter(null), Date.class);

                // Fuel surcharge
                if (fuelSurcharge != 0) {
                    billingSurCharge = new ShipmentBillingVo();
                    BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                    accessDescription = getFuelSurchargeDescription(serviceCode);
                    billingSurCharge.setDescription(accessDescription);
                    billingSurCharge.setDisplayDescription(accessDescription);

                    tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                    carrierCost = fuelSurcharge;
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

                if (description.equalsIgnoreCase("RE-DELIVERY FEE") || extraCharge1.equalsIgnoreCase("FR") || extraCharge2.equalsIgnoreCase("FR") || extraCharge3.equalsIgnoreCase("FR")) {
                    billingSurCharge = new ShipmentBillingVo();
                    BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                    accessDescription = "Redelivery";
                    billingSurCharge.setDescription(accessDescription);
                    billingSurCharge.setDisplayDescription(accessDescription);

                    tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                    carrierCost = 15D;
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
                } else {
                    for (Map.Entry<String, Double> entry : extraCharges.entrySet()) {
                        String key = entry.getKey();
                        Double value = entry.getValue();
                        if (key.equalsIgnoreCase("FO") || key.equalsIgnoreCase("MC") || key.equalsIgnoreCase("RP")) {
                            continue;
                        }
                        if (value != 0) {
                            billingSurCharge = new ShipmentBillingVo();
                            BeanUtils.copyProperties(billingSurCharge, billingBaseCharge);
                            accessDescription = ImportBillingUtils.checkAndSaveAccessorial(context, key, carrier);
                            billingSurCharge.setDescription(accessDescription);
                            billingSurCharge.setDisplayDescription(accessDescription);

                            tax = (defaultTaxPercent == null) ? 0D : defaultTaxPercent;
                            carrierCost = value;
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

                            if (key.equalsIgnoreCase("DG") || key.equalsIgnoreCase("OS")) {
                                billingSurCharge.setRequireCalculate(true);
                            } else {
                                billingSurCharge.setRequireCalculate(false);
                            }
                            billingSurCharge.setIsBaseCharge(false);
                            billingSurCharge.setAccessorialCount(++accessCount);
                            billingAccessorials.add(billingSurCharge);
                        }
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

//    private String getExtraChargeDescription(String key) {
//	String description = "";
//	switch (key) {
//	case "FO":
//	    description = "Third Party Fee";
//	    break;
//	case "MC":
//	    description = "Manual Connote";
//	    break;
//	case "RP":
//	    description = "Receiver Pays";
//	    break;
//	case "DG":
//	    description = "Dangerous Goods";
//	    break;
//	case "OS":
//	    description = "Off Shore";
//	    break;
//	case "RZ":
//	    description = "Remote Zone";
//	    break;
//	case "NZ":
//	    description = "Remote Zone";
//	    break;
//	case "Q1":
//	    description = "Remote Zone";
//	    break;
//	case "Q2":
//	    description = "Remote Zone";
//	    break;
//	}
//	return description;
//    }

    private String getFuelSurchargeDescription(String serviceCode) {
        String description = "";
        switch (serviceCode) {
            case "H":
                description = "Priority Fuel Surcharge";
                break;
            case "X":
                description = "Road Fuel Surcharge";
                break;
            case "V":
                description = "Direct Fuel Surcharge";
                break;
            default:
                description = "Fuel Surcharge";
        }
        return description;
    }

    private String getTollIpecZone(String senderPostalCode, String senderCity, String receiverPostalCode, String receiverCity) throws DaoException {
        TollIpecZoneDao tollIpecZoneDao = new TollIpecZoneDao();
        TollIpecZoneFilter filter = new TollIpecZoneFilter();
        // Check Toll Ipec Zone ( TO )
        TollIpecZoneVo ipecZoneToVo = new TollIpecZoneVo();
        filter.setCityName(senderCity);
        filter.setPostalCode(senderPostalCode);
        ipecZoneToVo = tollIpecZoneDao.selectTollIpecZone(filter);
        String senderZoneCode = "0";
        if (ipecZoneToVo != null) {
            senderZoneCode = ipecZoneToVo.getZone();
        }

        // Check Toll Ipec Zone ( FROM )
        TollIpecZoneVo ipecZoneFromVo = new TollIpecZoneVo();
        filter.setCityName(receiverCity);
        filter.setPostalCode(receiverPostalCode);
        ipecZoneFromVo = tollIpecZoneDao.selectTollIpecZone(filter);
        String receiverZoneCode = "0";
        if (ipecZoneFromVo != null) {
            receiverZoneCode = ipecZoneFromVo.getZone();
        }

        String zone = senderZoneCode + "-" + receiverZoneCode;
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
