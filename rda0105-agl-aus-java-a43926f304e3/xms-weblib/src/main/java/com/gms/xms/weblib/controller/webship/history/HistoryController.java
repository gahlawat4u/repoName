package com.gms.xms.weblib.controller.webship.history;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.Paging;
import com.gms.xms.model.SendAirlbillHistoryFillterModel;
import com.gms.xms.model.admin.SaveWebshipHistoryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryFilterModel;
import com.gms.xms.model.webship.history.HistoryModel;
import com.gms.xms.persistence.dao.webship.TollIpecConnoteDao;
import com.gms.xms.persistence.service.admin.ISaveWebshipHistoryService;
import com.gms.xms.persistence.service.admin.SaveWebshipHistoryServiceImp;
import com.gms.xms.persistence.service.tntconnote.ITntConnoteService;
import com.gms.xms.persistence.service.tntconnote.TntConnoteServiceImp;
import com.gms.xms.persistence.service.tollconnote.TollConnoteServiceImp;
import com.gms.xms.persistence.service.webship.settings.IUserSettingsService;
import com.gms.xms.persistence.service.webship.settings.UserSettingsServiceImp;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.txndb.vo.admin.SaveWebshipHistoryVo;
import com.gms.xms.txndb.vo.tnt.TntConnoteShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;
import com.gms.xms.txndb.vo.webship.history.HistoryFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.weblib.utils.StringConvertEscapeUtils;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.service.webship.history.HistoryServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.util.*;

/**
 * Posted from HistoryController
 * <p>
 * Author TanDT Date Jul 9, 2015
 */
public class HistoryController extends JsonBaseController {

    /**
     *
     */
    private static final long serialVersionUID = -7901889480717968757L;

    private Paging<HistoryModel> historyModels;
    private HashMap<Integer, String> listTotalDate;
    private HistoryFilterModel filterModel;
    private HashMap<String, String> listSearch;
    private String shipmentId;
    private List<String> pageSizeList;
    private String awbBarcode;
    private String fileName;
    private SendAirlbillHistoryFillterModel sendAirlbillHistoryFillterModel;
    private InputStream stream;
    private String customerCode;
    private SaveWebshipHistoryModel saveWebshipHistory;
    private Long tntManifestStatus;
    private Long tollPriorityManifestStatus;
    private Long tollIpecManifestStatus;
    private String columnHide;

    public String index() {
        this.setPageTitle("History");
        this.setBreadCrumb("History");
        prepareListTotalDate();
        prepareListSearch();
        try {
            pageSizeList = this.buildPageSizeList();
            if (!prepareHistoryFilter()) {
                return "success";
            }
            prepareDataIndex();
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String search() {
        try {
            if (!prepareHistoryFilter()) {
                return "success";
            }
            prepareDataIndex();
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String generateTntManifestList() {
        try {
            Long customerCode = this.getWebshipLoginInfo().getCustomerCode();
            ITntConnoteService tntConnoteService = new TntConnoteServiceImp();
            List<TntConnoteShipmentInfoVo> tntConnoteShipmentInfoVos = tntConnoteService.selectTntDomConnoteInfo(customerCode);
            Map<Long, Long> shipmentTypeConnoteIdMap = new LinkedHashMap<>();
            List<String> shipmentIds = new ArrayList<>();
            for (TntConnoteShipmentInfoVo tntConnoteShipmentInfoVo : tntConnoteShipmentInfoVos) {
                shipmentIds.add(String.valueOf(tntConnoteShipmentInfoVo.getShipmentid()));
                Long shipmentTypeId = tntConnoteShipmentInfoVo.getShipmentTypeId();
                shipmentTypeConnoteIdMap.put(shipmentTypeId, tntConnoteShipmentInfoVo.getConnoteId());
            }
            tntConnoteService.generateTntManifestList(this.getAddInfoMap(), shipmentTypeConnoteIdMap, shipmentIds);
        }catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String generateTollPriorityManifestList() {
        try {
            Long customerCode = this.getWebshipLoginInfo().getCustomerCode();
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(GenerateETFileConstants.CUSTOMER_CODE , customerCode);
            String filePath = request.getSession().getServletContext().getRealPath("") + "/edi_file/toll_manifest_file/";
            context.put(GenerateETFileConstants.FILE_PATH, filePath);
            context.put(Attributes.WFP_NAME, "Wfl-GenerateTollFlagManifest");
            WorkFlowManager2.getInstance().process(context);
        }catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String generateTollIpecManifestList() {
        try {
            Long customerCode = this.getWebshipLoginInfo().getCustomerCode();
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(GenerateETFileConstants.CUSTOMER_CODE , customerCode);
            String filePath = request.getSession().getServletContext().getRealPath("") + "/edi_file/toll_ipec_manifest_file/";
            context.put(GenerateETFileConstants.FILE_PATH, filePath);
            context.put(Attributes.WFP_NAME, "Wfl-GenerateTollIpecFlagManifest");
            WorkFlowManager2.getInstance().process(context);
        }catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String exportPdf() {
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            pageSizeList = this.buildPageSizeList();
            if (!prepareHistoryFilter()) {
                return "success";
            }
            String[] hiddenColumns = columnHide.length()==0 ? new String[]{}:columnHide.split(",");
            List<HistoryDynamicCL> historyDynamicCLS = new ArrayList<>(EnumSet.allOf(HistoryDynamicCL.class));
            for(String hiddenColumn : hiddenColumns){
                HistoryDynamicCL historyDynamicCL = HistoryDynamicCL.valueOf(hiddenColumn);
                historyDynamicCLS.remove(historyDynamicCL);
            }

            for(HistoryDynamicCL historyDynamicCL : historyDynamicCLS){
                data.put(historyDynamicCL.getFielName(), historyDynamicCL.getTitle());
            }

            prepareDataIndex();
            StringConvertEscapeUtils.convertObjectsEscape(historyModels.getRecords());
            String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/ShippingHistoryReport.html";
            data.put("historyModels", historyModels.getRecords());
            AppUtils.template2File(outputFilePath, false, "templates/pdf/history/historyNew.ftl", data);

            this.setFileName("ShippingHistoryReport.pdf");
            String pdfFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/ShippingHistoryReport.pdf";
            AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
            this.setStream(new FileInputStream(new File(pdfFilePath)));
            return "export";

        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String exportExcel() {
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            pageSizeList = this.buildPageSizeList();
            if (!prepareHistoryFilter()) {
                return "success";
            }
            String[] hiddenColumns = columnHide.length()==0 ? new String[]{}:columnHide.split(",");
            List<HistoryDynamicCL> historyDynamicCLS = new ArrayList<>(EnumSet.allOf(HistoryDynamicCL.class));
            for(String hiddenColumn : hiddenColumns){
                HistoryDynamicCL historyDynamicCL = HistoryDynamicCL.valueOf(hiddenColumn);
                historyDynamicCLS.remove(historyDynamicCL);
            }
            List<String> fieldAvailable = new ArrayList<>();
            List<String> titleAvailable = new ArrayList<>();
            for(HistoryDynamicCL historyDynamicCL : historyDynamicCLS){
                fieldAvailable.add(historyDynamicCL.getFielName());
                titleAvailable.add(historyDynamicCL.getTitle());
            }

            prepareDataIndex();
            List<HistoryVo> listModelFromVo = ModelUtils.createListVoFromModel(historyModels.getRecords(), HistoryVo.class);
            for(HistoryVo historyVo : listModelFromVo)
            {
                if(historyVo.getTotal().equals("0.00")){
                    historyVo.setTotal("TBA");
                }
            }

            InputStream is = new BufferedInputStream(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/history/historynew.xls"));

            String temFolder = request.getSession().getServletContext().getRealPath("")+"tmp";
            File file = new File(temFolder); 
            if( !file.exists() ){
            	file.mkdirs();
            }
            
            OutputStream os = new FileOutputStream(request.getSession().getServletContext().getRealPath("") + "/tmp/ShippingHistoryReportExcel.xls");
            
            
            Context context = new Context();
            context.putVar("headers", titleAvailable);
            context.putVar("data",  listModelFromVo);
            JxlsHelper.getInstance().processGridTemplateAtCell(is, os, context, fieldAvailable.toString().replaceAll("[\\[\\]]",""), "Sheet2!A1");

            this.setFileName("ShippingHistoryReportExcel");
            this.setStream(new FileInputStream(new File(request.getSession().getServletContext().getRealPath("") + "/tmp/ShippingHistoryReportExcel.xls")));
            return "export";

        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String sendAirbill() {
        try {
            if (sendAirlbillHistoryFillterModel.getEmails() != null) {
                if (!validEmail()) {
                    return "error";
                }
                sentEmail();
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String saveWebshipHistory() {
        try {
            if (this.getSaveWebshipHistory() != null) {
                if(this.getSaveWebshipHistory().getBaseCharge() != null) {
                    String baseChargeS = this.getSaveWebshipHistory().getBaseCharge().replace(",", "");
                    this.getSaveWebshipHistory().setBaseCharge(baseChargeS);
                }
                SaveWebshipHistoryVo saveWebshipHistoryVo = ModelUtils.createVoFromModel(this.getSaveWebshipHistory(), SaveWebshipHistoryVo.class);
                ISaveWebshipHistoryService saveWebshipHistoryService = new SaveWebshipHistoryServiceImp();
                saveWebshipHistoryService.saveWebshipHistory(this.getAddInfoMap(), saveWebshipHistoryVo);
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean validViewAirbill() {
        if (StringUtils.isEmpty(this.getShipmentId())) {
            addFieldError("shipmentId", "Please choice a shipment!");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected boolean validEmail() {
        if (StringUtils.contains(sendAirlbillHistoryFillterModel.getEmails(), ";")) {
            String[] emailAddresses = sendAirlbillHistoryFillterModel.getEmails().split(";");
            for (int i = 0; i < emailAddresses.length; i++) {
                String email = emailAddresses[i];
                if (!HelperUtils.isEmailAddress(email)) {
                    addFieldError("sendAirlbillHistoryFillterModel", "Email number " + i + " is not valid!");
                    setErrorCode(ErrorCode.FIELD_ERROR);
                    return false;
                }
            }
        } else {
            if (!HelperUtils.isEmailAddress(sendAirlbillHistoryFillterModel.getEmails())) {
                addFieldError("sendAirlbillHistoryFillterModel", "Email is not valid! Please use \";\" if you want to send multiple email.");
                setErrorCode(ErrorCode.FIELD_ERROR);
                return false;
            }
        }
        return true;
    }

    protected void sentEmail() throws Exception {
        IHistoryService iHistoryService = new HistoryServiceImp();
        this.setAwbBarcode(iHistoryService.getAwbBarcode(Long.parseLong(shipmentId)));
        String airbill_pdf = this.getServerPath("/barcode") + "/" + this.getShipmentId() + ".pdf";
        AppUtils.createPDFFromBarCode(airbill_pdf, awbBarcode);
        String[] filepath = {airbill_pdf};
        sendAirlbillHistoryFillterModel.setFilepath(filepath);
        String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
        String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
        String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
        String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
        int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
        String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
        EmailTemplateVo emailTemplateVo = iHistoryService.getEmailTemplateByName(sendAirlbillHistoryFillterModel.getTemplateEmail());
        String subject = emailTemplateVo.getSubject();
        String content = emailTemplateVo.getTemplateContent();
        AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, sendAirlbillHistoryFillterModel.getEmails(), "", "", subject, content, filepath);
    }

    protected boolean validAirbillBarcode() throws Exception {
        IHistoryService iHistoryService = new HistoryServiceImp();
        if (StringUtils.isEmpty(iHistoryService.getAwbBarcode(Long.parseLong(shipmentId)))) {
            addFieldError("historyViewAirbillModel.barcode", "Can not Find Barcode!");
        } else {
            this.setAwbBarcode(iHistoryService.getAwbBarcode(Long.parseLong(shipmentId)));
            this.setShipmentId(shipmentId);
            String filePath = this.getServerPath("/barcode") + "/" + shipmentId + ".pdf";
            AppUtils.createPDFFromBarCode(filePath, awbBarcode);
            this.setFileName(shipmentId + ".pdf");
            this.setStream(new FileInputStream(new File(filePath)));

        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void saveWorkbook(Workbook resultWorkbook, String fileName) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
        resultWorkbook.write(os);
        os.flush();
        os.close();
    }

    /**
     * prepareDataIndex
     *
     * @throws Exception
     */
    protected void prepareDataIndex() throws Exception {
        IHistoryService iHistoryService = new HistoryServiceImp();
        HistoryFilter filter = new HistoryFilter();
        filter = ModelUtils.createVoFromModel(this.getFilterModel(), HistoryFilter.class);
        filter.setStartRecord((filter.getPage() - 1) * filter.getRecordSize());
        Paging<HistoryModel> paging = new Paging<>(filter.getPage(), 5, filter.getRecordSize(), iHistoryService.selectCountByFilter(filter));
        this.setHistoryModels(paging);
        String custCode = this.getWebshipLoginInfo() != null ? String.valueOf(this.getWebshipLoginInfo().getCustomerCode()) : this.getCustomerCode();
        setTntManifestStatus(new TntConnoteServiceImp().countTntDomConnoteInfo(Long.valueOf(custCode)));
        setTollPriorityManifestStatus(new TollConnoteServiceImp().countTollPriorityConnoteInfo(custCode));
        setTollIpecManifestStatus(new TollIpecConnoteDao().countTollIpecConnoteInfo(custCode));
        historyModels.setRecords(ModelUtils.createListModelFromVo(iHistoryService.selectByFilter(filter), HistoryModel.class));
    }

    /**
     * prepareHistoryFilter
     *
     * @return HistoryFilter
     * @throws Exception
     */
    protected boolean prepareHistoryFilter() throws Exception {
        HistoryFilterModel filterModelN = new HistoryFilterModel();

        if (this.getFilterModel() == null) {
            // Load default index history
            filterModelN.setTotalDate("30");
            filterModelN.setPage("1");
            filterModelN.setRecordSize("25");
        } else {
            filterModelN = this.getFilterModel();
            if (filterModel.getConnoteNumber() != null) {
                filterModel.setConnoteNumber(filterModel.getConnoteNumber().trim());
            }
            if (filterModel.getConnoteNumber() != null && !StringUtils.isEmpty(filterModel.getConnoteNumber().trim())){
                filterModel.setConnoteNumber(filterModel.getConnoteNumber().trim());
                filterModel.setTotalDate("6");
            }
            if (filterModel.getSenderCity() != null) {
                filterModel.setSenderCity(filterModel.getSenderCity().trim());
            }
            if (filterModel.getSenderCity() != null && !StringUtils.isEmpty(filterModel.getSenderCity())) {
                filterModel.setSenderCity(filterModel.getSenderCity().trim());
                filterModel.setTotalDate("6");
            }
            if (filterModel.getReciverCity() != null) {
                filterModel.setReciverCity(filterModel.getReciverCity().trim());
            }
            if (filterModel.getReciverCity() != null && !StringUtils.isEmpty(filterModel.getReciverCity())) {
                filterModel.setReciverCity(filterModel.getReciverCity().trim());
                filterModel.setTotalDate("6");
            }

            if (filterModel.getSenderName() != null) {
                filterModel.setSenderName(filterModel.getSenderName().trim());
            }
            if (filterModel.getSenderName() != null && !StringUtils.isEmpty(filterModel.getSenderName())) {
                filterModel.setSenderName(filterModel.getSenderName().trim());
                filterModel.setTotalDate("6");
            }
            if (filterModel.getReciverName() != null) {
                filterModel.setReciverName(filterModel.getReciverName().trim());
            }
            if (filterModel.getReciverName() != null && !StringUtils.isEmpty(filterModel.getReciverName())) {
                filterModel.setReciverName(filterModel.getReciverName().trim());
                filterModel.setTotalDate("6");
            }

            if (StringUtils.isBlank(filterModelN.getFromDate())) {
                filterModelN.setFromDate(null);
            }
            if (StringUtils.isBlank(filterModelN.getToDate())) {
                filterModelN.setToDate(null);
            }
            if (StringUtils.isBlank(filterModelN.getReciverCity())) {
                filterModelN.setReciverCity(null);
            }
            if (StringUtils.isBlank(filterModelN.getReciverName())) {
                filterModelN.setReciverName(null);
            }
            if (StringUtils.isBlank(filterModelN.getSenderCity())) {
                filterModelN.setSenderCity(null);
            }
            if (StringUtils.isBlank(filterModelN.getSenderName())) {
                filterModelN.setSenderName(null);
            }

            if (!"5".equals(filterModelN.getTotalDate())) {
                filterModelN.setToDate(null);
                filterModelN.setFromDate(null);
            } else {
                filterModelN.setTotalDate(null);
                String custCode = this.getWebshipLoginInfo() != null ? String.valueOf(this.getWebshipLoginInfo().getCustomerCode()) : this.getCustomerCode();
                filterModelN.setCustomerCode(custCode);
                validDateSearch();
            }

            switch(filterModelN.getTotalDate()) {
                case "1":
                    filterModelN.setTotalDate("1");
                    break;
                case "2":
                    filterModelN.setTotalDate("30");
                    break;
                case "3":
                    filterModelN.setTotalDate("60");
                    break;
                case "4":
                    filterModelN.setTotalDate("90");
                    break;
                case "6":
                    filterModelN.setTotalDate(null);
                    break;
                default:
                    filterModelN.setTotalDate(null);
                    break;
            }

        }
        if (customerCode != null && !StringUtils.isEmpty(customerCode)) {
            filterModelN.setCustomerCode(customerCode);
        } else {
            filterModelN.setCustomerCode(String.valueOf(this.getWebshipLoginInfo().getCustomerCode()));
        }

        this.setFilterModel(filterModelN);
        return true;
    }

    protected boolean validDateSearch() throws Exception {
        HistoryFilter filter = new HistoryFilter();
        if (!HelperUtils.isValidDate(this.getFilterModel().getFromDate(), "dd-MM-yyyy")) {
            setErrorMessage("Start Date is not valid.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }

        if (!HelperUtils.isValidDate(this.getFilterModel().getToDate(), "dd-MM-yyyy")) {
            setErrorMessage("End Date is not valid.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
        filter = ModelUtils.createVoFromModel(this.getFilterModel(), HistoryFilter.class);
        if (filter.getFromDate() != null && filter.getToDate() != null) {
            if (filter.getFromDate().compareTo(filter.getToDate()) > 0) {
                setErrorMessage("Start date must be before the end date.");
                setErrorCode(ErrorCode.ACTION_ERROR);
                return false;
            }
        }

        return true;
    }

    /**
     * prepareListTotalDate
     */
    protected void prepareListTotalDate() {
        HashMap<Integer, String> listShowDate = new HashMap<>();
        listShowDate.put(1, "Show Today Only");
        listShowDate.put(2, "Show Last 30 Days");
        listShowDate.put(3, "Show Last 60 Days");
        listShowDate.put(4, "Show Last 90 Days");
        listShowDate.put(5, "Show Range");
        listShowDate.put(6, "Show All");
        this.setListTotalDate(listShowDate);
    }

    protected void prepareListSearch() {
        HashMap<String, String> listSearchN = new HashMap<>();
        listSearchN.put("filterModel.connoteNumber", "Connote Number");
        listSearchN.put("filterModel.senderCity", "Sender City");
        listSearchN.put("filterModel.reciverCity", "Receiver City");
        listSearchN.put("filterModel.senderName", "Sender Name");
        listSearchN.put("filterModel.reciverName", "Receiver Name");
        this.setListSearch(listSearchN);
    }

    public boolean isQuotable() {
        // Get customer default settings
        CustomerDefaultSettingVo customerDefaultSetting = null;
        try {
            IUserSettingsService service = new UserSettingsServiceImp();
            customerDefaultSetting = service.selectByCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
        } catch (Exception e) {
        }
        // Determine this customer was disabled quote or not?
        if (customerDefaultSetting == null) {
            return true;
        }
        return !customerDefaultSetting.getDisableQuote();
    }

    public Paging<HistoryModel> getHistoryModels() {
        return historyModels;
    }

    public void setHistoryModels(Paging<HistoryModel> historyModels) {
        this.historyModels = historyModels;
    }

    public HashMap<Integer, String> getListTotalDate() {
        return listTotalDate;
    }

    public void setListTotalDate(HashMap<Integer, String> listTotalDate) {
        this.listTotalDate = listTotalDate;
    }

    public HistoryFilterModel getFilterModel() {
        return filterModel;
    }

    public void setFilterModel(HistoryFilterModel filterModel) {
        this.filterModel = filterModel;
    }

    public HashMap<String, String> getListSearch() {
        return listSearch;
    }

    public void setListSearch(HashMap<String, String> listSearch) {
        this.listSearch = listSearch;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public List<String> getPageSizeList() {
        return pageSizeList;
    }

    public void setPageSizeList(List<String> pageSizeList) {
        this.pageSizeList = pageSizeList;
    }

    public String getAwbBarcode() {
        return awbBarcode;
    }

    public void setAwbBarcode(String awbBarcode) {
        this.awbBarcode = awbBarcode;
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

    public SendAirlbillHistoryFillterModel getSendAirlbillHistoryFillterModel() {
        return sendAirlbillHistoryFillterModel;
    }

    public void setSendAirlbillHistoryFillterModel(SendAirlbillHistoryFillterModel sendAirlbillHistoryFillterModel) {
        this.sendAirlbillHistoryFillterModel = sendAirlbillHistoryFillterModel;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public SaveWebshipHistoryModel getSaveWebshipHistory() {
        return saveWebshipHistory;
    }

    public void setSaveWebshipHistory(SaveWebshipHistoryModel saveWebshipHistory) {
        this.saveWebshipHistory = saveWebshipHistory;
    }

    public Long getTntManifestStatus() {
        return tntManifestStatus;
    }

    public void setTntManifestStatus(Long tntManifestStatus) {
        this.tntManifestStatus = tntManifestStatus;
    }

    public Long getTollPriorityManifestStatus() {
        return tollPriorityManifestStatus;
    }

    public void setTollPriorityManifestStatus(Long tollPriorityManifestStatus) {
        this.tollPriorityManifestStatus = tollPriorityManifestStatus;
    }

    public Long getTollIpecManifestStatus() {
        return tollIpecManifestStatus;
    }

    public void setTollIpecManifestStatus(Long tollIpecManifestStatus) {
        this.tollIpecManifestStatus = tollIpecManifestStatus;
    }

    public String getColumnHide() {
        return columnHide;
    }

    public void setColumnHide(String columnHide) {
        this.columnHide = columnHide;
    }
}