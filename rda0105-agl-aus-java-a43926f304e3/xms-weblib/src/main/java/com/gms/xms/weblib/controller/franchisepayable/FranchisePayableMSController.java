package com.gms.xms.weblib.controller.franchisepayable;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.franchisepayable.*;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.workflow.client.FranchisePayableMSClient;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from FranchisePayableMSController.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:57:31 PM
 */
public class FranchisePayableMSController extends FranchisePayableController {

    private static final long serialVersionUID = 1L;

    private FranchisePayableMSOverviewModel overview;
    private List<FranchisePayableMSMarginModel> paymentMarginDetails;
    private FranchisePayableMSMarginModel paymentMarginDetailsTotal;

    private List<FranchisePayableMSDeductModel> carrierCostDeduction;
    private FranchisePayableMSDeductModel carrierCostDeductionTotal;

    private List<FranchisePayableMS61DaysModel> paymentCreditDetails61;
    private FranchisePayableMS61DaysModel paymentCreditDetails61Total;

    private List<FranchisePayableMSNonCentralModel> nonCentralDetails;
    private FranchisePayableMSNonCentralModel nonCentralDetailsTotal;

    private List<FranchisePayableMSCreditModel> carrierCreditDetails;
    private FranchisePayableMSCreditModel carrierCreditDetailsTotal;

    private List<FranchisePayableMSOverpaymentModel> overpayment;
    private FranchisePayableMSOverpaymentModel overpaymentTotal;

    public String doOverview() {
        try {
            prepareDataForFilter();

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }

            FranchisePayableMSClient payableMSClient = new FranchisePayableMSClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableMSClient.getOverviewData(this.buildFilter(), true);
            this.setOverview((FranchisePayableMSOverviewModel) resultMap.get(Attributes.RESULT));
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            // Set RptTxnId to model.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
            this.setRptTxnId(filter.getRptTxnId());
            // Set frozen message
            determineFrozenMessage(context);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            String errorMgs = e.getMessage();
            setErrorMessage(errorMgs);
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doPaymentMarginDetails() {
        try {
            this.prepareDataForFilter();

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }

            FranchisePayableMSClient payableSCHelper = new FranchisePayableMSClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableSCHelper.getMarginDetails(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setPaymentMarginDetails((List<FranchisePayableMSMarginModel>) resultMap.get(Attributes.RESULT));
            this.setPaymentMarginDetailsTotal((FranchisePayableMSMarginModel) resultMap.get(Attributes.RESULT_TOTAL));
            String recordCountResult = context.get(Attributes.FRANCHISE_PAYABLE_PAYMENT_MARGIN_DETAILS_RECORD_COUNT_RESULT);
            // Set RptTxnId to model.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
            this.setRptTxnId(filter.getRptTxnId());
            Integer recordCount = Integer.valueOf(recordCountResult);
            Integer pageCount = (int) Math.ceil((double) recordCount / getRecordSize());
            this.setRecordCount(recordCount);
            this.setPageCount(pageCount);
            // Set frozen message
            this.determineFrozenMessage(context);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doPaymentMargin61Details() {
        try {
            this.prepareDataForFilter();

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }

            FranchisePayableMSClient payableMSHelper = new FranchisePayableMSClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableMSHelper.getMarginDetails61(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setPaymentCreditDetails61((List<FranchisePayableMS61DaysModel>) resultMap.get(Attributes.RESULT));
            this.setPaymentCreditDetails61Total((FranchisePayableMS61DaysModel) resultMap.get(Attributes.RESULT_TOTAL));
            String recordCountResult = context.get(Attributes.FRANCHISE_PAYABLE_61DAYS_PAYMENT_CREDIT_DETAILS_RECORD_COUNT_RESULT);
            Integer recordCount = Integer.valueOf(recordCountResult);
            Integer pageCount = (int) Math.ceil((double) recordCount / getRecordSize());
            // Set RptTxnId to model.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
            this.setRptTxnId(filter.getRptTxnId());
            this.setRecordCount(recordCount);
            this.setPageCount(pageCount);
            // Set frozen message
            determineFrozenMessage(context);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doCarrierCostDeduction() {
        try {
            this.prepareDataForFilter();

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }

            FranchisePayableMSClient payableMSHelper = new FranchisePayableMSClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableMSHelper.getDeductDetails(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setCarrierCostDeduction((List<FranchisePayableMSDeductModel>) resultMap.get(Attributes.RESULT));
            this.setCarrierCostDeductionTotal((FranchisePayableMSDeductModel) resultMap.get(Attributes.RESULT_TOTAL));
            String recordCountResult = context.get(Attributes.FRANCHISE_PAYABLE_CARRIER_COST_DEDUCTION_RECORD_COUNT_RESULT);
            Integer recordCount = Integer.valueOf(recordCountResult);
            Integer pageCount = (int) Math.ceil((double) recordCount / getRecordSize());
            // Set RptTxnId to model.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
            this.setRptTxnId(filter.getRptTxnId());
            this.setRecordCount(recordCount);
            this.setPageCount(pageCount);
            // Set frozen message
            this.determineFrozenMessage(context);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doNonCentralizedDetails() {
        try {
            prepareDataForFilter();

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }

            FranchisePayableMSClient payableMSHelper = new FranchisePayableMSClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableMSHelper.getNonCentralDetails(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setNonCentralDetails((List<FranchisePayableMSNonCentralModel>) resultMap.get(Attributes.RESULT));
            this.setNonCentralDetailsTotal((FranchisePayableMSNonCentralModel) resultMap.get(Attributes.RESULT_TOTAL));
            String recordCountResult = context.get(Attributes.FRANCHISE_PAYABLE_NON_CENTRALIZED_DETAILS_RECORD_COUNT_RESULT);
            Integer recordCount = Integer.valueOf(recordCountResult);
            Integer pageCount = (int) Math.ceil((double) recordCount / getRecordSize());
            // Set RptTxnId to model.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
            this.setRptTxnId(filter.getRptTxnId());
            this.setRecordCount(recordCount);
            this.setPageCount(pageCount);
            // Set frozen message
            this.determineFrozenMessage(context);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doCarrierCreditDetails() {
        try {
            this.prepareDataForFilter();

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }

            FranchisePayableMSClient payableMSHelper = new FranchisePayableMSClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableMSHelper.getCreditDetails(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setCarrierCreditDetails((List<FranchisePayableMSCreditModel>) resultMap.get(Attributes.RESULT));
            this.setCarrierCreditDetailsTotal((FranchisePayableMSCreditModel) resultMap.get(Attributes.RESULT_TOTAL));
            String recordCountResult = context.get(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_RECORD_COUNT_RESULT);
            Integer recordCount = Integer.valueOf(recordCountResult);
            Integer pageCount = (int) Math.ceil((double) recordCount / getRecordSize());
            // Set RptTxnId to model.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
            this.setRptTxnId(filter.getRptTxnId());
            this.setRecordCount(recordCount);
            this.setPageCount(pageCount);
            // Set frozen message
            this.determineFrozenMessage(context);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doOverPayment() {
        try {
            this.prepareDataForFilter();

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }

            FranchisePayableMSClient payableMSHelper = new FranchisePayableMSClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableMSHelper.getOveraymentDetails(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setOverpayment((List<FranchisePayableMSOverpaymentModel>) resultMap.get(Attributes.RESULT));
            this.setOverpaymentTotal((FranchisePayableMSOverpaymentModel) resultMap.get(Attributes.RESULT_TOTAL));
            String recordCountResult = context.get(Attributes.FRANCHISE_PAYABLE_OVERPAYMENT_RECORD_COUNT_RESULT);
            Integer recordCount = Integer.valueOf(recordCountResult);
            Integer pageCount = (int) Math.ceil((double) recordCount / getRecordSize());
            // Set RptTxnId to model.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
            this.setRptTxnId(filter.getRptTxnId());
            this.setRecordCount(recordCount);
            this.setPageCount(pageCount);

            // Set frozen message
            this.determineFrozenMessage(context);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    public String doMSExportPDF() {
        try {
            String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + getRptTxnId().replace("-", "") + ".html";
            String pdfFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + getRptTxnId().replace("-", "") + ".pdf";
            FranchisePayableFilter filter = this.buildFilter();
            FranchisePayableMSClient helper = new FranchisePayableMSClient(this.getAddInfoMap());
            filter.setRecordSize(null);
            filter.setStartRecord(null);
            helper.renderPayableHtmlReportForPdf(filter, htmlFilePath, this.getEnableNonCentralizedTab());
            this.setFilename(getRptTxnId().replace("-", "") + ".pdf");
            AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);
            this.setStream(new FileInputStream(new File(pdfFilePath)));
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "export";
    }

    public String doMSExportHTML() {
        try {
            String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + getRptTxnId().replace("-", "") + ".html";
            FranchisePayableFilter filter = this.buildFilter();

            FranchisePayableMSClient helper = new FranchisePayableMSClient(this.getAddInfoMap());
            filter.setRecordSize(null);
            filter.setStartRecord(null);
            helper.renderPayableHtmlReport(filter, outputFilePath, this.getEnableNonCentralizedTab());
            this.setHtmlContents(AppUtils.readUTF8File2String(outputFilePath));
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "export";
    }

    public String doMSFranchisePayableInvoice() {
        try {
            this.prepareDataForFilter();

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }

            // get franchise address by Code
            // buildFranchiseInfoByCode();
            FranchisePayableMSClient payableMSHelper = new FranchisePayableMSClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableMSHelper.getFranchiseInfoByCode(this.getFranchiseCode(), false);
            this.setFranchise((FranchiseInfoModel) resultMap.get(Attributes.RESULT));

            resultMap = payableMSHelper.getFranchiseInfoByCode("100", false);
            FranchiseInfoModel franchiseInfoModel = (FranchiseInfoModel) resultMap.get(Attributes.RESULT);
            String registrationId = franchiseInfoModel.getRegistrationid();
            String systemAddress = this.getSystemAddress();
            systemAddress += "<br/>" + registrationId;
            this.setSystemAddress(systemAddress);

            resultMap = payableMSHelper.getOverviewData(this.buildFilter(), true);
            this.setOverview((FranchisePayableMSOverviewModel) resultMap.get(Attributes.RESULT));
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            // Set RptTxnId to model.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
            this.setRptTxnId(filter.getRptTxnId());
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    public String doMSExportFranchiseInvoicePDF() {
        try {
            String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + getRptTxnId().replace("-", "") + "_inv.html";
            String pdfFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + getRptTxnId().replace("-", "") + ".pdf";
            FranchisePayableFilter filter = this.buildFilter();
            FranchisePayableMSClient helper = new FranchisePayableMSClient(this.getAddInfoMap());
            filter.setRecordSize(null);
            filter.setStartRecord(null);
            helper.renderPayableInvoiceHtmlReport(filter, this.getFranchiseCode(), htmlFilePath, this.getEnableNonCentralizedTab());
            setFilename(getRptTxnId().replace("-", "") + ".pdf");
            AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);

            this.setStream(new FileInputStream(new File(pdfFilePath)));
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "export";
    }

    @SuppressWarnings("unchecked")
    public String doFreeze() {
        this.setErrorMessage("");
        try {
            // Build date range from list of unfrozen franchise payable report
            FranchisePayableMSClient helper = new FranchisePayableMSClient(this.getAddInfoMap());
            Map<String, Object> resultMap = helper.getUnFrozenPeriodList();
            this.setPeriodList((List<FranchisePayablePeriodModel>) resultMap.get(Attributes.RESULT));

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "input";
            }

            // Get configuration franchise payable start date
            Date fpbStartDate = DateUtils.convertStringToDate(AppConstants.APP_SETTINGS.getFranchisePayableStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            Date start = DateUtils.convertStringToDate(getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);

            // Check start date not less then franchise payable start date in configuration file
            if (start.before(fpbStartDate) || start.equals(fpbStartDate)) {
                throw new Exception("No support for report before " + DateUtils.convertDateToString(fpbStartDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
            }

            // Create and put the filter into the context
            FranchisePayableFilter filter = new FranchisePayableFilter();
            filter.setStartDate(DateUtils.convertStringToDate(getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
            filter.setEndDate(DateUtils.convertStringToDate(getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
            this.buildFranchiseList();
            filter.setFranchiseCodeList(buildFranchiseCodeList());

            // Do freeze the franchise payable report by date range
            resultMap = helper.freezePayableReport(filter);

            // Return new unfrozen period list
            resultMap = helper.getUnFrozenPeriodList();
            this.setPeriodList((List<FranchisePayablePeriodModel>) resultMap.get(Attributes.RESULT));
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doSendEmail() {
        this.setErrorMessage("");
        try {
            // Get frozen period list
            FranchisePayableMSClient helper = new FranchisePayableMSClient(this.getAddInfoMap());
            Map<String, Object> resultMap = helper.getFrozenPeriodList();
            this.setPeriodList((List<FranchisePayablePeriodModel>) resultMap.get(Attributes.RESULT));

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "input";
            }

            // Get configuration franchise payable start date
            Date fpbStartDate = DateUtils.convertStringToDate(AppConstants.APP_SETTINGS.getFranchisePayableStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            Date start = DateUtils.convertStringToDate(getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);

            // Check start date not less then franchise payable start date in configuration file
            if (start.before(fpbStartDate) || start.equals(fpbStartDate)) {
                throw new Exception("No support for report before " + DateUtils.convertDateToString(fpbStartDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
            }

            // Create and put the filter into the context
            FranchisePayableFilter filter = new FranchisePayableFilter();
            filter.setStartDate(DateUtils.convertStringToDate(getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
            filter.setEndDate(DateUtils.convertStringToDate(getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
            this.buildFranchiseList();
            filter.setFranchiseCodeList(buildFranchiseCodeList());

            // Do send email the franchise payable report by date range
            String tmpPath = this.getServerPath("/tmp");
            resultMap = helper.sendPayableReport(filter, tmpPath);

            // Return new frozen period list
            resultMap = helper.getFrozenPeriodList();
            this.setPeriodList((List<FranchisePayablePeriodModel>) resultMap.get(Attributes.RESULT));
        } catch (Exception e) {
            setErrorCode(ErrorCode.ERROR);
            setErrorMessage(e.getMessage());
            setErrorType(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    public FranchisePayableMSOverviewModel getOverview() {
        return overview;
    }

    public void setOverview(FranchisePayableMSOverviewModel overview) {
        this.overview = overview;
    }

    public List<FranchisePayableMSMarginModel> getPaymentMarginDetails() {
        return paymentMarginDetails;
    }

    public void setPaymentMarginDetails(List<FranchisePayableMSMarginModel> paymentMarginDetails) {
        this.paymentMarginDetails = paymentMarginDetails;
    }

    public FranchisePayableMSMarginModel getPaymentMarginDetailsTotal() {
        return paymentMarginDetailsTotal;
    }

    public void setPaymentMarginDetailsTotal(FranchisePayableMSMarginModel paymentMarginDetailsTotal) {
        this.paymentMarginDetailsTotal = paymentMarginDetailsTotal;
    }

    public List<FranchisePayableMSDeductModel> getCarrierCostDeduction() {
        return carrierCostDeduction;
    }

    public void setCarrierCostDeduction(List<FranchisePayableMSDeductModel> carrierCostDeduction) {
        this.carrierCostDeduction = carrierCostDeduction;
    }

    public FranchisePayableMSDeductModel getCarrierCostDeductionTotal() {
        return carrierCostDeductionTotal;
    }

    public void setCarrierCostDeductionTotal(FranchisePayableMSDeductModel carrierCostDeductionTotal) {
        this.carrierCostDeductionTotal = carrierCostDeductionTotal;
    }

    public List<FranchisePayableMS61DaysModel> getPaymentCreditDetails61() {
        return paymentCreditDetails61;
    }

    public void setPaymentCreditDetails61(List<FranchisePayableMS61DaysModel> paymentCreditDetails61) {
        this.paymentCreditDetails61 = paymentCreditDetails61;
    }

    public FranchisePayableMS61DaysModel getPaymentCreditDetails61Total() {
        return paymentCreditDetails61Total;
    }

    public void setPaymentCreditDetails61Total(FranchisePayableMS61DaysModel paymentCreditDetails61Total) {
        this.paymentCreditDetails61Total = paymentCreditDetails61Total;
    }

    public List<FranchisePayableMSNonCentralModel> getNonCentralDetails() {
        return nonCentralDetails;
    }

    public void setNonCentralDetails(List<FranchisePayableMSNonCentralModel> nonCentralDetails) {
        this.nonCentralDetails = nonCentralDetails;
    }

    public FranchisePayableMSNonCentralModel getNonCentralDetailsTotal() {
        return nonCentralDetailsTotal;
    }

    public void setNonCentralDetailsTotal(FranchisePayableMSNonCentralModel nonCentralDetailsTotal) {
        this.nonCentralDetailsTotal = nonCentralDetailsTotal;
    }

    public List<FranchisePayableMSCreditModel> getCarrierCreditDetails() {
        return carrierCreditDetails;
    }

    public void setCarrierCreditDetails(List<FranchisePayableMSCreditModel> carrierCreditDetails) {
        this.carrierCreditDetails = carrierCreditDetails;
    }

    public FranchisePayableMSCreditModel getCarrierCreditDetailsTotal() {
        return carrierCreditDetailsTotal;
    }

    public void setCarrierCreditDetailsTotal(FranchisePayableMSCreditModel carrierCreditDetailsTotal) {
        this.carrierCreditDetailsTotal = carrierCreditDetailsTotal;
    }

    public List<FranchisePayableMSOverpaymentModel> getOverpayment() {
        return overpayment;
    }

    public void setOverpayment(List<FranchisePayableMSOverpaymentModel> overpayment) {
        this.overpayment = overpayment;
    }

    public FranchisePayableMSOverpaymentModel getOverpaymentTotal() {
        return overpaymentTotal;
    }

    public void setOverpaymentTotal(FranchisePayableMSOverpaymentModel overpaymentTotal) {
        this.overpaymentTotal = overpaymentTotal;
    }
}