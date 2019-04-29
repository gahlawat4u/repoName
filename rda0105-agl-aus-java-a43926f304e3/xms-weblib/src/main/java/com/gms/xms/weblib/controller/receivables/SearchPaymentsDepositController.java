package com.gms.xms.weblib.controller.receivables;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.admin.receivables.payments.PaymentFilter;
import com.gms.xms.model.BankModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.receivables.payments.PaymentDetailModel;
import com.gms.xms.model.admin.receivables.payments.PaymentTotalModel;
import com.gms.xms.model.admin.receivables.payments.SearchPaymentColumnFlagsModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.BankDao;
import com.gms.xms.persistence.dao.admin.receivables.payments.PaymentDao;
import com.gms.xms.txndb.vo.BankVo;
import com.gms.xms.txndb.vo.admin.receivables.payments.PaymentDetailVo;
import com.gms.xms.txndb.vo.admin.receivables.payments.PaymentTotalVo;
import com.gms.xms.txndb.vo.admin.receivables.payments.SearchPaymentColumnFlagsVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.searchpayment.ISearchPaymentRender;
import com.gms.xms.workflow.render.searchpayment.SearchPaymentRenderImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Posted from SearchPaymentsDepositController
 * <p>
 * Author dattrinh Mar 17, 2016 3:32:27 PM
 */
public class SearchPaymentsDepositController extends AdminJsonBaseController {

    private static final long serialVersionUID = 6332252892717977053L;

    // Filter properties.
    private String startDate;
    private String endDate;
    private String bankId;
    private String customerCode;
    private String min;
    private String max;
    private String note;
    private String cheque;
    private String franchiseCode;
    private String page;
    private String pageSize;
    private String orderType;
    private String orderField;
    private SearchPaymentColumnFlagsModel columnFlags;

    // Models.
    private Paging<PaymentDetailModel> report;
    private List<BankModel> bankList;
    private PaymentTotalModel summary;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;

    public String show() {
        try {
            this.prepareFranchises();
            this.preparePageSizes();
            this.prepareBankList();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            // Get filter.
            PaymentFilter filter = this.buildFilter();
            // Get the setting number links on the page.
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Get record count.
            PaymentDao dao = new PaymentDao();
            long recordCount = dao.countPaymentByFilter(filter);
            // Build paging object.
            Paging<PaymentDetailModel> paging = new Paging<PaymentDetailModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            // Get list of records of payments.
            List<PaymentDetailVo> paymentDetailVos = dao.getPaymentByFilter(filter);
            List<PaymentDetailModel> paymentDetailModels = ModelUtils.createListModelFromVo(paymentDetailVos, PaymentDetailModel.class);
            paging.setRecords(paymentDetailModels);
            // Set payments report.
            this.setReport(paging);
            // Get summary report.
            PaymentTotalVo totalVo = dao.sumPaymentByFilter(filter);
            PaymentTotalModel totalModel = ModelUtils.createModelFromVo(totalVo, PaymentTotalModel.class);
            this.setSummary(totalModel == null ? new PaymentTotalModel() : totalModel);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportHtml() {
        try {
            PaymentFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            ISearchPaymentRender render = new SearchPaymentRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/search_payment_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            SearchPaymentColumnFlagsVo columnFlagsVo = ModelUtils.createVoFromModel(this.getColumnFlags(), SearchPaymentColumnFlagsVo.class);
            render.generateHtmlFile(filter, columnFlagsVo, outputFilePath, realPath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportXls() {
        try {
            PaymentFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            ISearchPaymentRender render = new SearchPaymentRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/payment_deposits_slip_" + AppUtils.createMessageReference() + ".xls";
            SearchPaymentColumnFlagsVo columnFlagsVo = ModelUtils.createVoFromModel(this.getColumnFlags(), SearchPaymentColumnFlagsVo.class);
            render.generateXlsFile(filter, columnFlagsVo, outputFilePath);
            this.setFileName("payment_deposits_slip.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected PaymentFilter buildFilter() throws Exception {
        PaymentFilter filter = new PaymentFilter();
        // Set franchise list.
        filter.setFranchiseList(this.buildFranchiseCodeList(this.getFranchiseCode()));
        // Set start date.
        Date startDate = null;
        try {
            startDate = DateUtils.convertStringToDate(this.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (startDate == null) {
                throw new CustomException("Please choose start date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid start date.");
        }
        filter.setStartDate(startDate);
        // Set end date.
        Date endDate = null;
        try {
            endDate = DateUtils.convertStringToDate(this.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (endDate == null) {
                throw new CustomException("Please choose end date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid end date.");
        }
        filter.setEndDate(endDate);
        // Check date range.
        if (endDate.before(startDate)) {
            throw new CustomException("Start date must before End date.");
        }
        // Set bank id.
        Long bankId = null;
        try {
            bankId = StringUtils.isBlank(this.getBankId()) ? null : Long.valueOf(this.getBankId());
        } catch (Exception e) {
            throw new CustomException("Please choose a bank.");
        }
        filter.setBankId(bankId);
        // Set customer code.
        filter.setCustomerCode(this.getCustomerCode());
        // Set min.
        Double min = null;
        try {
            min = StringUtils.isBlank(this.getMin()) ? null : Double.valueOf(this.getMin());
        } catch (Exception e) {
            throw new CustomException("Min is invalid number.");
        }
        filter.setMin(min);
        // Set max.
        Double max = null;
        try {
            max = StringUtils.isBlank(this.getMax()) ? null : Double.valueOf(this.getMax());
        } catch (Exception e) {
            throw new CustomException("Max is invalid number.");
        }
        filter.setMax(max);
        // Set note.
        filter.setNote(this.getNote());
        // Set cheque.
        filter.setCheque(this.getCheque());
        // Set page.
        Integer page = null;
        try {
            page = StringUtils.isBlank(this.getPage()) ? 1 : Integer.valueOf(this.getPage());
            if (page <= 0) {
                throw new CustomException("The page number cannot be less than 0.");
            }
            filter.setPage(page);
        } catch (Exception e) {
            throw new CustomException("Invalid page number.");
        }
        // Set page size.
        Integer pageSize = null;
        try {
            pageSize = StringUtils.isBlank(this.getPageSize()) ? Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize()) : Integer.valueOf(this.getPageSize());
            if (pageSize <= 0) {
                throw new CustomException("The page size cannot be less than 0.");
            }
            filter.setPageSize(pageSize);
        } catch (Exception e) {
            throw new CustomException("Invalid page size number.");
        }
        // Set order type.
        Integer order = null;
        try {
            order = StringUtils.isBlank(this.getOrderType()) ? 0 : Integer.valueOf(this.getOrderType());
            if (order != 0 && order != 1) {
                throw new CustomException("The order type cannot be other value exception (0: ascending, 1: descending)");
            }
            filter.setOrderType(order);
        } catch (Exception e) {
            throw new CustomException("Invalid order type value.");
        }
        // Set order field.
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "invoice_code" : this.getOrderField());
        return filter;
    }

    protected void prepareBankList() throws Exception {
        BankDao dao = new BankDao();
        List<BankVo> bankVos = dao.getAll();
        List<BankModel> bankModels = ModelUtils.createListModelFromVo(bankVos, BankModel.class);
        this.setBankList(bankModels);
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Paging<PaymentDetailModel> getReport() {
        return report;
    }

    public void setReport(Paging<PaymentDetailModel> report) {
        this.report = report;
    }

    public List<BankModel> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankModel> bankList) {
        this.bankList = bankList;
    }

    public PaymentTotalModel getSummary() {
        return summary;
    }

    public void setSummary(PaymentTotalModel summary) {
        this.summary = summary;
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

    public SearchPaymentColumnFlagsModel getColumnFlags() {
        return columnFlags;
    }

    public void setColumnFlags(SearchPaymentColumnFlagsModel columnFlags) {
        this.columnFlags = columnFlags;
    }
}
