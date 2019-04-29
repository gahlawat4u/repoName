package com.gms.xms.weblib.controller.invoicing.manageinvoice;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.invoicing.manageinvoice.ViewEditInvoiceFilter;
import com.gms.xms.model.InvoiceModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.AirbillInfoModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.InvoiceInfoModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.invoicing.IViewEditInvoiceService;
import com.gms.xms.persistence.service.admin.invoicing.ViewEditInvoiceServiceImp;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.AirbillInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.render.invoice.vieweditinvoice.IViewEditInvoiceRender;
import com.gms.xms.workflow.render.invoice.vieweditinvoice.ViewEditInvoiceRenderImp;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Posted from ViewEditInvoiceController
 * <p>
 * Author TanDT Date May 20, 2015
 */
public class ViewEditInvoiceController extends AdminJsonBaseController {

    /**
     *
     */
    private static final long serialVersionUID = -827572544691365216L;

    private List<InvoiceModel> dateList;
    private List<InvoiceModel> invoiceList;
    private List<AirbillInfoModel> airbillInfoModels;
    private InvoiceInfoModel invoiceInfoModel;
    private String companyAddress;
    private String mailToPayment;
    // Request Parameter
    private String invoiceId;
    private List<String> listInvoices;
    private List<String> franchiseList;
    private String fromDate;
    private String toDate;
    private String invoiceDate;
    private String invoiceCode;
    private String franchiseSearchTypeValue;
    private String franchiseSearchType;
    private String minAirbills;
    private String maxAirbills;
    private String nonEmailInvoice;
    private String status;
    private String customerCode;
    private String searchType;
    private String airbillNumber;
    private String shipmentId;
    private String isDeleteAirbill;
    private List<String> massEditShipments;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;
    private String showPayments;

    public String show() {
        this.setPageTitle("View Edit Invoice");
        try {
            prepareDateList();
            // Load default invoice list.
            if (this.getDateList() != null && this.getDateList().size() > 0) {
                InvoiceModel firstInvoice = this.getDateList().get(0);
                this.setSearchType("1");
                if (!StringUtils.isBlank(this.getInvoiceDate())) {
                    this.setInvoiceDate(this.getInvoiceDate());
                } else {
                    this.setInvoiceDate(firstInvoice.getInvoiceDate());
                }
                this.setFranchiseSearchType("all");
                List<InvoiceModel> invoiceModels = new ArrayList<InvoiceModel>();
                try {
                    List<InvoiceVo> invoiceVos = this.prepareInvoiceList();
                    invoiceModels = ModelUtils.createListModelFromVo(invoiceVos, InvoiceModel.class);
                } catch (Exception e) {
                }
                if (!StringUtils.isBlank(this.getInvoiceId())) {
                    this.setInvoiceId(this.getInvoiceId());
                }
                this.setInvoiceList(invoiceModels);
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String deleteAirbill() {
        try {
            this.setAirbillNumber(airbillNumber);
            this.setShipmentId(shipmentId);
            this.setInvoiceId(invoiceId);
            this.setMassEditShipments(massEditShipments);

        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String findAirbillDo() {
        try {
            boolean checkAirbill = false;
            this.getInvoiceDetail();
            for (AirbillInfoModel airbillInfoModel : airbillInfoModels) {
                if (airbillInfoModel.getAirbillNumber().equals(this.getAirbillNumber())) {
                    checkAirbill = true;
                }
            }
            if (!checkAirbill) {
                this.setErrorCode(ErrorCode.ACTION_ERROR);
                this.setErrorMessage("Airbill #" + this.getAirbillNumber() + " not found");
                log.error("Airbill #" + this.getAirbillNumber() + " not found");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);

        }
        return "success";
    }

    public String deleteAirbillDo() {
        try {
            if (massEditShipments != null) {
                for (String massEditShipment : massEditShipments) {
                    String massEditShipmentSplit[] = StringUtils.split(massEditShipment, ",");
                    shipmentId = massEditShipmentSplit[0];
                    airbillNumber = massEditShipmentSplit[1];
                    invoiceId = massEditShipmentSplit[2];
                    invoiceDate = massEditShipmentSplit[3];
                    customerCode = massEditShipmentSplit[4];
                    IViewEditInvoiceService service = new ViewEditInvoiceServiceImp();
                    service.deleteAirbill(this.getAddInfoMap(), shipmentId, airbillNumber);
                }
            } else {
                IViewEditInvoiceService service = new ViewEditInvoiceServiceImp();
                service.deleteAirbill(this.getAddInfoMap(), shipmentId, airbillNumber);
            }
            this.getInvoiceDetail();

        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String searchInvoice() {
        try {
            List<InvoiceVo> invoiceVos = this.prepareInvoiceList();
            this.setInvoiceList(ModelUtils.createListModelFromVo(invoiceVos, InvoiceModel.class));
        } catch (Exception e) {
            this.setInvoiceList(new ArrayList<InvoiceModel>());
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getInvoiceDetail() {
        try {
            if (StringUtils.isEmpty(invoiceId)) {
                addActionError("Invoice Invalid");
                return "success";
            }
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(Attributes.INVOICE_ID, Long.parseLong(invoiceId));
            context.put(Attributes.WFP_NAME, "Wfl-ViewEditInvoiceSearchInvoiceDetail");
            context = WorkFlowManager2.getInstance().process(context);
            // Write log if there is error message.
            if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                log.error(context.getString(Attributes.ERROR_MESSAGE));
                setErrorCode(ErrorCode.ACTION_ERROR);
                setErrorMessage(context.getString(Attributes.ERROR_MESSAGE));
                return "success";
            }
            InvoiceInfoVo invoiceInfoVo = context.get(Attributes.INVOICE_VO);
            String mailPaymentToResult = context.getString(Attributes.MAIL_PAYMENT_TO);
            String companyAddressResult = context.getString(Attributes.COMPANY_ADDRESS);
            List<AirbillInfoVo> airbillInfoVos = context.get(Attributes.AIRBILL_INFO_VO);

            this.setInvoiceInfoModel(ModelUtils.createModelFromVo(invoiceInfoVo, InvoiceInfoModel.class));
            this.setCompanyAddress(companyAddressResult);
            this.setMailToPayment(mailPaymentToResult);
            this.setAirbillInfoModels(ModelUtils.createListModelFromVo(airbillInfoVos, AirbillInfoModel.class));
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String doExportPdf() {
        try {
            if (!StringUtils.isBlank(this.getInvoiceId())) {
                this.createExportPdf(this.getInvoiceId(), "", "");
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            } else {
                throw new CustomException("Please select a invoice to export.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String downloadInvoicesZip() {
        try {
            if (this.getListInvoices() != null) {
                List<String> listInvoices = this.getListInvoices();
                // Create invoices folder
                String tmpFolder = AppConstants.APP_SETTINGS.getAppTmpPath() + "/Invoices_" + AppUtils.createMessageReference();
                new File(tmpFolder).mkdir();
                // Prepare data to download zip file
                File zipFile = new File(AppConstants.APP_SETTINGS.getAppTmpPath() + "/zipfile.zip");
                FileOutputStream fout = new FileOutputStream(zipFile);
                ZipOutputStream zip = new ZipOutputStream(fout);
                for (String invoiceId : listInvoices) {
                    this.createExportPdf(invoiceId, "download", tmpFolder);
                }
                addFolderToZip("", tmpFolder, zip);
                zip.flush();
                zip.close();
                this.setFileName("zipfile.zip");
                this.setStream(new FileInputStream(new File(AppConstants.APP_SETTINGS.getAppTmpPath() + "/zipfile.zip")));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "download";
            } else {
                throw new CustomException("Please select a invoice to export.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void addFolderToZip(String path, String tmpFolder, ZipOutputStream zip) throws IOException {
        File folder = new File(tmpFolder);
        for (String fileName : folder.list()) {
            if (path.equals("")) {
                addFileToZip(folder.getName(), tmpFolder + "/" + fileName, zip);
            } else {
                addFileToZip(path + "/" + folder.getName(), tmpFolder + "/" + fileName, zip);
            }
        }
    }

    @SuppressWarnings("resource")
    private void addFileToZip(String path, String tmpFolder, ZipOutputStream zip) throws IOException {
        File folder = new File(tmpFolder);
        if (folder.isDirectory()) {
            addFolderToZip(path, tmpFolder, zip);
        } else {
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(tmpFolder);
            zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
            while ((len = in.read(buf)) > 0) {
                zip.write(buf, 0, len);
            }
        }
    }

    private void createExportPdf(String invoiceId, String type, String tmpFolder) throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfoMap());
        context.put(Attributes.INVOICE_ID, Long.parseLong(invoiceId));
        context.put(Attributes.WFP_NAME, "Wfl-ViewEditInvoiceSearchInvoiceDetail");
        IViewEditInvoiceRender render = new ViewEditInvoiceRenderImp(this.getAddInfoMap());
        Boolean showPayments = false;
        showPayments = !StringUtils.isBlank(this.getShowPayments()) ? Boolean.parseBoolean(this.getShowPayments()) : showPayments;
        render.generateHtmlViewPdfFile(context, showPayments);
        InvoiceInfoVo invoiceInfoVo = context.get(Attributes.INVOICE_VO);
        String invoiceCode = invoiceInfoVo.getInvoiceCode();
        this.setFileName(invoiceCode + ".pdf");
        String pdfFilePath = "";
        if (!StringUtils.isBlank(type) && "download".equalsIgnoreCase(type)) {
            pdfFilePath = tmpFolder + "/" + invoiceCode + "_Invoice.pdf";
        } else {
            pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/invoices_" + AppUtils.createMessageReference() + ".pdf";
        }
        AppUtils.createPDFFromHTMLWithFont(context.getString(Attributes.OUT_PUT_FILE_PATH), pdfFilePath, "arial", true);
        this.setStream(new FileInputStream(new File(pdfFilePath)));
    }

    public String doExportHtml() {
        try {
            if (!StringUtils.isBlank(this.getInvoiceId())) {
                Long invoiceId = Long.parseLong(this.getInvoiceId());
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.INVOICE_ID, invoiceId);
                context.put(Attributes.WFP_NAME, "Wfl-ViewEditInvoiceSearchInvoiceDetail");
                IViewEditInvoiceRender render = new ViewEditInvoiceRenderImp(this.getAddInfoMap());
                Boolean showPayments = false;
                showPayments = !StringUtils.isBlank(this.getShowPayments()) ? Boolean.parseBoolean(this.getShowPayments()) : showPayments;
                render.generateHtmlViewPrintableFile(context, showPayments);
                this.setHtmlExportString(AppUtils.readUTF8File2String(context.getString(Attributes.OUT_PUT_FILE_PATH)));
                return "export";
            } else {
                throw new CustomException("Please select a invoice to export.");
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    protected List<InvoiceVo> prepareInvoiceList() throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfoMap());
        context.put(Attributes.INVOICE_FILTER, this.buildFilter());
        context.put(Attributes.WFP_NAME, "Wfl-ViewEditInvoiceSearchInvoice");
        context = WorkFlowManager2.getInstance().process(context);
        if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
            throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
        }
        List<InvoiceVo> invoices = context.get(Attributes.INVOICE_LIST_RESULT);
        List<InvoiceVo> finalInvoiceList = new ArrayList<InvoiceVo>();
        // Add Select an Invoice option.
        InvoiceVo invoice = new InvoiceVo();
        invoice.setInvoiceId(-1L);
        invoice.setInvoiceCode(this.getLocalizationText("Select an Invoice"));
        finalInvoiceList.add(invoice);
        // Add Print All option.
        invoice = new InvoiceVo();
        invoice.setInvoiceId(-2L);
        invoice.setInvoiceCode(this.getLocalizationText("Print All"));
        finalInvoiceList.add(invoice);
        // Add Download All in Zip option.
        invoice = new InvoiceVo();
        invoice.setInvoiceId(-3L);
        invoice.setInvoiceCode(this.getLocalizationText("Download All in Zip"));
        finalInvoiceList.add(invoice);
        // Add all searched invoices.
        finalInvoiceList.addAll(invoices);
        return finalInvoiceList;
    }

    private void prepareDateList() throws Exception {
        IInvoiceService service = new InvoiceServiceImp();
        List<InvoiceModel> invoiceModels = ModelUtils.createListModelFromVo(service.selectAllInvoiceForViewEdit(this.buildDateFilter()), InvoiceModel.class);
        this.setDateList(invoiceModels);
    }

    private ViewEditInvoiceFilter buildDateFilter() throws Exception {
        ViewEditInvoiceFilter filter = new ViewEditInvoiceFilter();
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        return filter;
    }

    private ViewEditInvoiceFilter buildFilter() throws Exception {
        ViewEditInvoiceFilter filter = new ViewEditInvoiceFilter();
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        // Search Type.
        Integer sType = StringUtils.isBlank(this.getSearchType()) ? null : Integer.valueOf(this.getSearchType());
        filter.setSearchType(sType);
        // From Date.
        if (fromDate != null) {
            filter.setFromDate(DateUtils.convertStringToDate(fromDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        }
        // To Date.
        if (toDate != null) {
            filter.setToDate(DateUtils.convertStringToDate(toDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        }
        // Invoice Date.
        if (!StringUtils.isBlank(invoiceDate)) {
            filter.setInvoiceDate(DateUtils.convertStringToDate(invoiceDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        }
        // Franchise Search Type.
        filter.setFranchiseSearchType(this.getFranchiseSearchType());
        // Franchise Search Type Value.
        if (!this.getFranchiseSearchType().equals("all") && StringUtils.isBlank(this.getFranchiseSearchTypeValue())) {
            throw new CustomException("Franchises cannot leave blank.");
        }
        if (!isValidatefranchiseSearchTypeValue()) {
            throw new CustomException("Franchises format is [100,...,102]");
        }
        if (!isValidateMinMaxAirbill(this.getMinAirbills())) {
            throw new CustomException("MinAirbills is integer");
        }
        if (!isValidateMinMaxAirbill(this.getMaxAirbills())) {
            throw new CustomException("MinAirbills is integer");
        }
        if (!StringUtils.isBlank(this.getMinAirbills()) && !StringUtils.isBlank(this.getMaxAirbills())) {
            if (Integer.valueOf(this.getMinAirbills()) > Integer.valueOf(this.getMaxAirbills())) {
                throw new CustomException("MinAirbills is smaller than MaxAirbills");
            }
        }

        filter.setFranchiseSearchTypeValue(this.getFranchiseSearchTypeValue());
        // Status.
        Integer st = StringUtils.isBlank(this.getStatus()) ? null : Integer.valueOf(this.getStatus());
        filter.setStatus(st);
        // Non-email invoices only.
        if (!StringUtils.isBlank(nonEmailInvoice)) {
            Boolean isNonEmailInvoice = Boolean.parseBoolean(nonEmailInvoice);
            filter.setEmailInvoice(isNonEmailInvoice ? 0 : null);
        }
        // Min Airbills.
        Integer min = StringUtils.isBlank(this.getMinAirbills()) ? null : Integer.valueOf(this.getMinAirbills());
        filter.setMinAirbills(min);
        // Max Airbills.
        Integer max = StringUtils.isBlank(this.getMaxAirbills()) ? null : Integer.valueOf(this.getMaxAirbills());
        filter.setMaxAirbills(max);
        filter.setCustomerCode(customerCode);
        return filter;
    }

    public List<AirbillInfoModel> getAirbillInfoModels() {
        return airbillInfoModels;
    }

    public void setAirbillInfoModels(List<AirbillInfoModel> airbillInfoModels) {
        this.airbillInfoModels = airbillInfoModels;
    }

    public InvoiceInfoModel getInvoiceInfoModel() {
        return invoiceInfoModel;
    }

    public void setInvoiceInfoModel(InvoiceInfoModel invoiceInfoModel) {
        this.invoiceInfoModel = invoiceInfoModel;
    }

    public String getMailToPayment() {
        return mailToPayment;
    }

    public void setMailToPayment(String mailToPayment) {
        this.mailToPayment = mailToPayment;
    }

    public List<String> getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(List<String> franchiseList) {
        this.franchiseList = franchiseList;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getFranchiseSearchTypeValue() {
        return franchiseSearchTypeValue;
    }

    public void setFranchiseSearchTypeValue(String franchiseSearchTypeValue) {
        this.franchiseSearchTypeValue = franchiseSearchTypeValue;
    }

    public String getFranchiseSearchType() {
        return franchiseSearchType;
    }

    public void setFranchiseSearchType(String franchiseSearchType) {
        this.franchiseSearchType = franchiseSearchType;
    }

    public String getMinAirbills() {
        return minAirbills;
    }

    public void setMinAirbills(String minAirbills) {
        this.minAirbills = minAirbills;
    }

    public String getMaxAirbills() {
        return maxAirbills;
    }

    public void setMaxAirbills(String maxAirbills) {
        this.maxAirbills = maxAirbills;
    }

    public String getNonEmailInvoice() {
        return nonEmailInvoice;
    }

    public void setNonEmailInvoice(String nonEmailInvoice) {
        this.nonEmailInvoice = nonEmailInvoice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<String> getListInvoices() {
        return listInvoices;
    }

    public void setListInvoices(List<String> listInvoices) {
        this.listInvoices = listInvoices;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public List<InvoiceModel> getDateList() {
        return dateList;
    }

    public void setDateList(List<InvoiceModel> dateList) {
        this.dateList = dateList;
    }

    public List<InvoiceModel> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceModel> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
    }

    public String getShowPayments() {
        return showPayments;
    }

    public void setShowPayments(String showPayments) {
        this.showPayments = showPayments;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getIsDeleteAirbill() {
        return isDeleteAirbill;
    }

    public void setIsDeleteAirbill(String isDeleteAirbill) {
        this.isDeleteAirbill = isDeleteAirbill;
    }

    public List<String> getMassEditShipments() {
        return massEditShipments;
    }

    public void setMassEditShipments(List<String> massEditShipments) {
        this.massEditShipments = massEditShipments;
    }

    private boolean isValidatefranchiseSearchTypeValue() {
        if (this.getFranchiseSearchType().equals("all")) {
            return true;
        }
        String franchiseSearchTypePattern = "^([0-9]{3},)*[0-9]{3}$";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(franchiseSearchTypePattern);
        matcher = pattern.matcher(this.getFranchiseSearchTypeValue());
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    private boolean isValidateMinMaxAirbill(String airbills) {
        if (StringUtils.isBlank(airbills)) {
            return true;
        }
        String parttennumber = "^[0-9]+$";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(parttennumber);
        matcher = pattern.matcher(airbills);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
}
