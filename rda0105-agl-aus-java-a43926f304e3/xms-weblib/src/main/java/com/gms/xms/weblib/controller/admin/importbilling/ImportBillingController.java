package com.gms.xms.weblib.controller.admin.importbilling;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.account.customers.manage.ServiceSettingModel;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.model.admin.imports.ImportBillingDataModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.account.customers.manage.ServiceSettingVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.*;

/**
 * Posted from May 23, 2016 10:19:27 AM
 * <p>
 * Author huynd
 */
public class ImportBillingController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private List<ServiceSettingModel> services;
    private ImportBillingDataModel billingData;
    private String billingDataStr;
    private Boolean otherCarrier;

    // Upload rate sheet file
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String billingFilePath;

    private String selDateFormat;
    private Boolean isInternationalShipment;
    private Boolean applyCustomerTax;
    private Boolean applyCarrierTax;
    private Integer countSuccess;
    private Integer countUnSuccess;
    private String importType;
    private List<String> errorList;

    private Integer totalColumns;
    private Map<String, String> importFields;
    private List<String> selectedImportFields;
    private List<BillingRowDataModel> rowData;

    public String show() {
        try {
            loadCarriers();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doImport() {
        try {
            String billingData = GsonUtils.toGson(this.getBillingData());
            this.setBillingDataStr(billingData);
            this.setOtherCarrier(false);
            String carrierId = this.getBillingData().getCarrierId();
            if (!carrierId.equalsIgnoreCase("1") && !carrierId.equalsIgnoreCase("15") && !carrierId.equalsIgnoreCase("2") && !carrierId.equalsIgnoreCase("3") && !carrierId.equalsIgnoreCase("52") && !carrierId.equalsIgnoreCase("50") && !carrierId.equalsIgnoreCase("40") && !carrierId.equalsIgnoreCase("51") && !carrierId.equalsIgnoreCase("56") && !carrierId.equalsIgnoreCase("59") && !carrierId.equalsIgnoreCase("58") && !carrierId.equalsIgnoreCase("54") && !carrierId.equalsIgnoreCase("-1")) {
                // Carrier zero is usa freight carrier
                this.setOtherCarrier(true);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String uploadBilling() {
        try {
            ImportBillingDataModel billingData = this.getImportBillingData();
            String carrierId = billingData.getCarrierId();
            String prefix = "";
            switch (carrierId) {
                case "1":
                    prefix = "dhl_intl_";
                    break;
                case "3":
                    prefix = "tnt_dom_";
                    break;
                case "15":
                    prefix = "dhl_dom_";
                    break;
                case "54":
                    prefix = "tnt_intl_";
                    break;
                case "52":
                    prefix = "toll_priority_";
                    break;
                case "59":
                    prefix = "toll_ipec_";
                    break;
                case "72":
                    prefix = "startrack_";
                    break;
                default:
                    prefix = "other_carrier_";
                    break;
            }
            String billingFilePath = request.getSession().getServletContext().getRealPath("") + "/billing_files";
            // Get extension
            String billingFileName = this.getFileUploadFileName();
            String[] billingFileNameArr = billingFileName.split("\\.");
            String ext = billingFileNameArr[billingFileNameArr.length - 1];
            String outputFilePath = "/" + prefix + "import_billing_" + AppUtils.createMessageReference() + "." + ext;
            File billingFile = new File(billingFilePath, outputFilePath);
            // Upload file
            FileUtils.copyFile(this.getFileUpload(), billingFile);
            this.setBillingFilePath(billingFilePath + outputFilePath);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String saveImport() {
        try {
            ImportBillingDataModel billingData = this.getImportBillingData();
            String carrierId = billingData.getCarrierId();
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(Attributes.INVOICE_DATE, billingData.getInvoiceDate());
            context.put(Attributes.DATE_FORMAT, this.getSelDateFormat());
            if (this.getOtherCarrier()) {
                context.put(Attributes.IS_INTERNATIONAL_SHIPMENT, this.getIsInternationalShipment());
                context.put(Attributes.APPLY_CUSTOMER_TAX, this.getApplyCustomerTax());
                context.put(Attributes.APPLY_CARRIER_TAX, this.getApplyCarrierTax());
            }
            if ("true".equalsIgnoreCase(billingData.getHasHeader())) {
                context.put(Attributes.HAS_HEADER, true);
            }
            String filePath = this.getBillingFilePath().trim();
            if (StringUtils.isBlank(filePath)) {
                throw new CustomException("Choose file import.");
            }

            context.put(Attributes.EXTENSION, filePath.substring(filePath.length() - 3, filePath.length()));
            context.put(Attributes.SERVICE_ID, Integer.valueOf(carrierId));
            context.put(Attributes.BILLING_FILE, this.getBillingFilePath());
            switch (carrierId) {
                case "1":
                    context.put(Attributes.WFP_NAME, "Wfl-ImportBillingDhlIntl");
                    break;
                case "3":
                    context.put(Attributes.WFP_NAME, "Wfl-ImportBillingTntDomestic");
                    break;
                case "15":
                    context.put(Attributes.WFP_NAME, "Wfl-ImportBillingDhlDomestic");
                    break;
                case "54":
                    context.put(Attributes.WFP_NAME, "Wfl-ImportBillingTntIntl");
                    break;
                case "52":
                    context.put(Attributes.WFP_NAME, "Wfl-ImportBillingTollPriority");
                    break;
                case "59":
                    context.put(Attributes.WFP_NAME, "Wfl-ImportBillingTollIpec");
                    break;
                case "72":
                    context.put(Attributes.WFP_NAME, "Wfl-ImportBillingStartrack");
                    break;
                default:
                    if (!this.validateImportFields()) {
                        this.setErrorCode(ErrorCode.ACTION_ERROR);
                        return "error";
                    }
                    context.put(Attributes.IMPORT_FIELDS, this.getSelectedImportFields());
                    context.put(Attributes.WFP_NAME, "Wfl-ImportBillingOtherCarrier");
                    break;
            }
            context = WorkFlowManager2.getInstance().process(context);
            this.setCountSuccess(context.getInt(Attributes.COUNT_IMPORT_BILLING_SUCCESS));
            this.setCountUnSuccess(context.getInt(Attributes.COUNT_IMPORT_BILLING_UNSUCCESS));
            List<String> errorList = context.get(Attributes.ERROR_LIST);
            this.setErrorList(errorList);
            // Write log if there is error message.
            if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                setErrorCode(ErrorCode.ACTION_ERROR);
                setErrorMessage(context.getString(Attributes.ERROR_MESSAGE));
                log.error(context.getString(Attributes.ERROR_MESSAGE));
                return "error";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String validateImport() {
        try {
            ImportBillingDataModel billingData = this.getImportBillingData();
            String carrierId = billingData.getCarrierId();
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(Attributes.INVOICE_DATE, billingData.getInvoiceDate());
            context.put(Attributes.DATE_FORMAT, this.getSelDateFormat());
            if (this.getOtherCarrier()) {
                context.put(Attributes.IS_INTERNATIONAL_SHIPMENT, this.getIsInternationalShipment());
                context.put(Attributes.APPLY_CUSTOMER_TAX, this.getApplyCustomerTax());
                context.put(Attributes.APPLY_CARRIER_TAX, this.getApplyCarrierTax());
            }
            String filePath = this.getBillingFilePath().trim();
            if (StringUtils.isBlank(filePath)) {
                throw new CustomException("Choose file import.");
            }
            context.put(Attributes.EXTENSION, filePath.substring(filePath.length() - 3, filePath.length()));
            context.put(Attributes.SERVICE_ID, Integer.valueOf(carrierId));
            context.put(Attributes.BILLING_FILE, this.getBillingFilePath());
            switch (carrierId) {
                case "1":
                case "3":
                case "15":
                case "54":
                case "52":
                case "59":
                case "72":
                    this.setImportType("continue");
                    this.setTotalColumns(0);
                    return "success";
                default:
                    this.setImportType("other_carrier");
                    this.buildListImportFields();
                    context.put(Attributes.WFP_NAME, "Wfl-ReadImportBillingFile");
                    context = WorkFlowManager2.getInstance().process(context);
                    if (context.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.SUCCESS)) {
                        BillingDataModel dataSheet = context.get(Attributes.BILLING_DATA_FILE);
                        this.setTotalColumns(dataSheet.getRowData().get(0).getCellData().size());
                        this.setRowData(dataSheet.getRowData());
                    }
                    break;
            }

        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected Boolean validateImportFields() {
        this.buildListImportFields();

        // Check selected import fields for each column
        List<String> fields = this.getSelectedImportFields();
        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            if (field.equalsIgnoreCase("0")) {
                this.setErrorMessage(this.getLocalizationText("Please select a field mapping for each column."));
                return false;
            }
        }

        // Check if has a duplicate field in the selected import fields
        Set<String> duplicateFields = HelperUtils.getDuplicateField(fields);
        if (duplicateFields.size() > 0) {
            for (String field : duplicateFields) {
                this.setErrorMessage("Having duplications In '" + this.getImportFields().get(field) + "'");
                return false;
            }
        }

        List<String> requiredFields = getRequiredFields();
        for (String field : requiredFields) {
            if (!fields.contains(field)) {
                this.setErrorMessage("Please select '" + this.getImportFields().get(field) + "'");
                return false;
            }
        }

        return true;
    }

    protected List<String> getRequiredFields() {
        List<String> requiredFields = new LinkedList<String>();
        requiredFields.add("airbill_number");
        requiredFields.add("ship_date");
        return requiredFields;
    }

    protected void buildListImportFields() {
        Map<String, String> listImportFields = new LinkedHashMap<String, String>();
        listImportFields.put("invoice_date", this.getLocalizationText("Invoice Date"));
        listImportFields.put("customer_code", this.getLocalizationText("Customer Code"));
        listImportFields.put("currency_code", this.getLocalizationText("Currency Code"));
        listImportFields.put("dimension_l", this.getLocalizationText("Length"));
        listImportFields.put("dimension_w", this.getLocalizationText("Width"));
        listImportFields.put("dimension_h", this.getLocalizationText("Height"));
        listImportFields.put("dimension_unit", this.getLocalizationText("Dimension Unit"));
        listImportFields.put("weight_unit", this.getLocalizationText("Weight Unit"));
        listImportFields.put("description", this.getLocalizationText("Description"));
        listImportFields.put("zone", this.getLocalizationText("Zone"));
        listImportFields.put("pieces", this.getLocalizationText("Items"));
        listImportFields.put("customer_cost", this.getLocalizationText("Customer Freight Cost"));
        listImportFields.put("carrier_cost", this.getLocalizationText("Carrier Freight Cost"));
        listImportFields.put("security", this.getLocalizationText("Security Amount"));
        listImportFields.put("weight", this.getLocalizationText("Weight"));
        listImportFields.put("tax_amount", this.getLocalizationText("Tax Amount"));
        listImportFields.put("airbill_number", this.getLocalizationText("Airbill Number"));
        listImportFields.put("shipper_reference", this.getLocalizationText("Reference 1"));
        listImportFields.put("shipper_reference2", this.getLocalizationText("Reference 2"));
        listImportFields.put("shipper_reference3", this.getLocalizationText("Reference 3"));
        listImportFields.put("ship_date", this.getLocalizationText("Ship Date"));
        listImportFields.put("service_area_code_origin", this.getLocalizationText("Origin Code"));
        listImportFields.put("service_area_code_destination", this.getLocalizationText("Destination Code"));
        listImportFields.put("billing_account", this.getLocalizationText("Billing Account Number"));
        listImportFields.put("billingto_account", this.getLocalizationText("Billing To Account Number"));
        listImportFields.put("billing_type", this.getLocalizationText("Billing Type"));
        listImportFields.put("shipper_name", this.getLocalizationText("Shipper Name"));
        listImportFields.put("shipper_detail", this.getLocalizationText("Shipper Company Name"));
        listImportFields.put("sender_address1", this.getLocalizationText("Shipper Address1"));
        listImportFields.put("sender_address2", this.getLocalizationText("Shipper Address2"));
        listImportFields.put("sender_city", this.getLocalizationText("Shipper City"));
        listImportFields.put("sender_state", this.getLocalizationText("Shipper State"));
        listImportFields.put("sender_countrycode", this.getLocalizationText("Shipper Country Code"));
        listImportFields.put("sender_postcode", this.getLocalizationText("Sender Postcode"));
        listImportFields.put("receiver_name", this.getLocalizationText("Receiver Name"));
        listImportFields.put("receiver_detail", this.getLocalizationText("Receiver Company Name"));
        listImportFields.put("receiver_address1", this.getLocalizationText("Receiver Address1"));
        listImportFields.put("receiver_address2", this.getLocalizationText("Receiver Address2"));
        listImportFields.put("receiver_city", this.getLocalizationText("Receiver City"));
        listImportFields.put("receiver_countrycode", this.getLocalizationText("Receiver Country Code"));
        listImportFields.put("receiver_state", this.getLocalizationText("Receiver State"));
        listImportFields.put("receiver_postcode", this.getLocalizationText("Receiver Postcode"));
        listImportFields.put("other_changes", this.getLocalizationText("Other Changes"));
        listImportFields.put("content_type", this.getLocalizationText("Content Type"));
        listImportFields.put("origin_text", this.getLocalizationText("Sender Suburb"));
        listImportFields.put("destination_text", this.getLocalizationText("Receiver Suburb"));
        listImportFields.put("product", this.getLocalizationText("Service Type"));
        listImportFields.put("access_1_description", this.getLocalizationText("Access.1 Description"));
        listImportFields.put("access_1_cost", this.getLocalizationText("Access.1 Cost"));
        listImportFields.put("access_1_charge", this.getLocalizationText("Access.1 Charge"));
        listImportFields.put("access_2_description", this.getLocalizationText("Access.2 Description"));
        listImportFields.put("access_2_cost", this.getLocalizationText("Access.2 Cost"));
        listImportFields.put("access_2_charge", this.getLocalizationText("Access.2 Charge"));
        listImportFields.put("access_3_description", this.getLocalizationText("Access.3 Description"));
        listImportFields.put("access_3_cost", this.getLocalizationText("Access.3 Cost"));
        listImportFields.put("access_3_charge", this.getLocalizationText("Access.3 Charge"));
        this.setImportFields(listImportFields);
    }

    private void loadCarriers() throws Exception {
        IServiceService service = new ServiceServiceImp();
        List<ServiceVo> serviceVos = service.selectAll();
        // Create new Service Setting list
        List<ServiceSettingVo> serviceSettingVos = new ArrayList<ServiceSettingVo>();
        for (ServiceVo serviceVo : serviceVos) {
            ServiceSettingVo serviceSettingVo = new ServiceSettingVo();
            // Get Service Setting info
            serviceSettingVo.setServiceId(serviceVo.getServiceId());
            serviceSettingVo.setServiceName(serviceVo.getServiceName());
            // Add Service Setting to list
            serviceSettingVos.add(serviceSettingVo);
        }
        // Convert to model
        List<ServiceSettingModel> serviceSettingModels = ModelUtils.createListModelFromVo(serviceSettingVos, ServiceSettingModel.class);
        this.setServices(serviceSettingModels);
    }

    private ImportBillingDataModel getImportBillingData() {
        ImportBillingDataModel billingData = GsonUtils.fromGson(this.getBillingDataStr(), new TypeToken<ImportBillingDataModel>() {
        }.getType());
        return billingData;
    }

    public List<ServiceSettingModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceSettingModel> services) {
        this.services = services;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getBillingFilePath() {
        return billingFilePath;
    }

    public void setBillingFilePath(String billingFilePath) {
        this.billingFilePath = billingFilePath;
    }

    public ImportBillingDataModel getBillingData() {
        return billingData;
    }

    public void setBillingData(ImportBillingDataModel billingData) {
        this.billingData = billingData;
    }

    public String getBillingDataStr() {
        return billingDataStr;
    }

    public void setBillingDataStr(String billingDataStr) {
        this.billingDataStr = billingDataStr;
    }

    public Boolean getOtherCarrier() {
        return otherCarrier;
    }

    public void setOtherCarrier(Boolean otherCarrier) {
        this.otherCarrier = otherCarrier;
    }

    public String getSelDateFormat() {
        return selDateFormat;
    }

    public void setSelDateFormat(String selDateFormat) {
        this.selDateFormat = selDateFormat;
    }

    public Boolean getIsInternationalShipment() {
        return isInternationalShipment;
    }

    public void setIsInternationalShipment(Boolean isInternationalShipment) {
        this.isInternationalShipment = isInternationalShipment;
    }

    public Boolean getApplyCustomerTax() {
        return applyCustomerTax;
    }

    public void setApplyCustomerTax(Boolean applyCustomerTax) {
        this.applyCustomerTax = applyCustomerTax;
    }

    public Boolean getApplyCarrierTax() {
        return applyCarrierTax;
    }

    public void setApplyCarrierTax(Boolean applyCarrierTax) {
        this.applyCarrierTax = applyCarrierTax;
    }

    public Integer getCountSuccess() {
        return countSuccess;
    }

    public void setCountSuccess(Integer countSuccess) {
        this.countSuccess = countSuccess;
    }

    public Integer getCountUnSuccess() {
        return countUnSuccess;
    }

    public void setCountUnSuccess(Integer countUnSuccess) {
        this.countUnSuccess = countUnSuccess;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public Integer getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(Integer totalColumns) {
        this.totalColumns = totalColumns;
    }

    public Map<String, String> getImportFields() {
        return importFields;
    }

    public void setImportFields(Map<String, String> importFields) {
        this.importFields = importFields;
    }

    public List<BillingRowDataModel> getRowData() {
        return rowData;
    }

    public void setRowData(List<BillingRowDataModel> rowData) {
        this.rowData = rowData;
    }

    public List<String> getSelectedImportFields() {
        return selectedImportFields;
    }

    public void setSelectedImportFields(List<String> selectedImportFields) {
        this.selectedImportFields = selectedImportFields;
    }

}
