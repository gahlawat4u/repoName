package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.Paging;
import com.gms.xms.model.SendAirlbillHistoryFillterModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryFilterModel;
import com.gms.xms.model.webship.history.HistoryModel;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.txndb.vo.webship.history.HistoryFilter;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.workflow.service.webship.history.HistoryServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryService;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from ManageCustomerWebshipHistoryController
 * <p>
 * Author TANDT 06-11-2015
 */
public class ManageCustomerWebshipHistoryController extends JsonBaseController {

    /**
     *
     */
    private static final long serialVersionUID = -2259485026681256408L;
    private String customerCode;
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

    /**
     * index()
     *
     * @return view customer/manager/webship_history_data.jsp
     */
    public String index() {
        this.setPageTitle("Webship History");
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

    public String exportPdf() {
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            pageSizeList = this.buildPageSizeList();
            if (!prepareHistoryFilter()) {
                return "success";
            }
            prepareDataIndex();
            String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/ShippingHistoryReport.html";
            data.put("historyModels", historyModels.getRecords());
            AppUtils.template2File(outputFilePath, false, "templates/pdf/history/history.ftl", data);

            this.setFileName("ShippingHistoryReport.pdf");
            FileOutputStream fos = new FileOutputStream(new File(request.getSession().getServletContext().getRealPath("") + "/tmp/ShippingHistoryReport.pdf"));
            ITextRenderer render = new ITextRenderer();
            render.setDocument(new File(outputFilePath));
            render.layout();
            render.createPDF(fos);
            fos.close();
            this.setStream(new FileInputStream(new File(request.getSession().getServletContext().getRealPath("") + "/tmp/ShippingHistoryReport.pdf")));
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
            prepareDataIndex();
            String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/ShippingHistoryReportExcel.xls";

            data.put("listShipment", historyModels.getRecords());
            InputStream is = new BufferedInputStream(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/history/history.xls"));
            XLSTransformer transformer = new XLSTransformer();

            Workbook resultWorkbook = transformer.transformXLS(is, data);
            saveWorkbook(resultWorkbook, outputFilePath);

            this.setFileName("ShippingHistoryReportExcel.xls");
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

    protected boolean validViewAirbill() {
        if (StringUtils.isEmpty(this.getShipmentId())) {
            addFieldError("shipmentId", "Please choice a shipment!");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected boolean validEmail() {
        if (!HelperUtils.isEmailAddress(sendAirlbillHistoryFillterModel.getEmails())) {
            addFieldError("sendAirlbillHistoryFillterModel", "Email is not valid!");
            setErrorCode(ErrorCode.FIELD_ERROR);
            return false;
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
     * @param filter
     * @throws Exception
     */
    protected void prepareDataIndex() throws Exception {
        HistoryFilter filter = new HistoryFilter();
        filter = ModelUtils.createVoFromModel(this.getFilterModel(), HistoryFilter.class);
        filter.setStartRecord((filter.getPage() - 1) * filter.getRecordSize());
        IHistoryService iHistoryService = new HistoryServiceImp();
        Paging<HistoryModel> paging = new Paging<>(filter.getPage(), 5, filter.getRecordSize(), iHistoryService.selectCountByFilter(filter));
        this.setHistoryModels(paging);
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
            if (filterModel.getSenderCity() != null) {
                filterModel.setSenderCity(filterModel.getSenderCity().trim());
            }
            if (filterModel.getReciverCity() != null) {
                filterModel.setReciverCity(filterModel.getReciverCity().trim());
            }
            if (filterModel.getSenderName() != null) {
                filterModel.setSenderName(filterModel.getSenderName().trim());
            }
            if (filterModel.getReciverName() != null) {
                filterModel.setReciverName(filterModel.getReciverName().trim());
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
                filterModelN.setCustomerCode(String.valueOf(customerCode));
                validDateSearch();
            }

            if ("1".equals(filterModelN.getTotalDate())) {
                filterModelN.setTotalDate("1");
            }
            if ("2".equals(filterModelN.getTotalDate())) {
                filterModelN.setTotalDate("30");
            }
            if ("3".equals(filterModelN.getTotalDate())) {
                filterModelN.setTotalDate("60");
            }
            if ("4".equals(filterModelN.getTotalDate())) {
                filterModelN.setTotalDate("90");
            }
            if ("6".equals(filterModelN.getTotalDate())) {
                filterModelN.setTotalDate(null);
            }

        }
        filterModelN.setCustomerCode(String.valueOf(customerCode));
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

}
