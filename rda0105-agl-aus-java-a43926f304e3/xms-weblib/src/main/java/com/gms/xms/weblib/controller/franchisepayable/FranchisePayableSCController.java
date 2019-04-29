package com.gms.xms.weblib.controller.franchisepayable;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.dto.FranInvoiceNumberingVo;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.franchisepayable.FranchisePayablePeriodModel;
import com.gms.xms.model.franchisepayable.sc.*;
import com.gms.xms.persistence.dao.FranInvoiceNumberingDao;
import com.gms.xms.persistence.service.FranInvoiceNumberingService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.workflow.client.FranchisePayableSCClient;
import com.gms.xms.workflow.render.franchisepayablesc.FranchisePayableSCRenderImp;
import com.gms.xms.workflow.render.franchisepayablesc.IFranchisePayableSCRender;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from FranchisePayableSCController
 * <p>
 * Author DatTV Oct 28, 2015
 */
public class FranchisePayableSCController extends FranchisePayableController {

    private static final long serialVersionUID = 1L;

    private List<FranchisePayableSCCreditModel> carrierCreditDetails;
    private FranchisePayableSCCreditModel taxableCarrierCreditTotal;
    private FranchisePayableSCCreditModel nonTaxableCarrierCreditTotal;
    private FranchisePayableSCCreditTotalModel carrierCreditDetailsTotal;

    private List<FranchisePayableSCOverpaymentModel> overpayment;
    private FranchisePayableSCOverpaymentModel overpaymentTotal;

    private List<FranchisePayableSCShipmentModel> shipments;
    private FranchisePayableSCShipmentModel taxableShipmentTotal;
    private FranchisePayableSCShipmentModel nonTaxableShipmentTotal;
    private FranchisePayableSCShipmentTotalModel shipmentTotal;

    private List<FranchisePayableSCTechFeeModel> techFees;
    private FranchisePayableSCTechFeeTotalModel techFeeTotal;

    private FranchisePayableSCOverviewModel overview;
    private String invoiceNumber;

    private List<String> listPageSize = this.buildPageSizeList();

    public String show() {
        try {
            this.prepareDataForFilterSC();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doCarrierCreditDetails() {
        try {
            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }
            this.prepareDataForFilterSC();

            FranchisePayableSCClient payableSCHelper = new FranchisePayableSCClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableSCHelper.getCreditDetails(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setCarrierCreditDetails((List<FranchisePayableSCCreditModel>) resultMap.get(Attributes.RESULT));
            this.setTaxableCarrierCreditTotal((FranchisePayableSCCreditModel) resultMap.get(Attributes.RESULT_TAXABLE_TOTAL));
            this.setNonTaxableCarrierCreditTotal((FranchisePayableSCCreditModel) resultMap.get(Attributes.RESULT_NON_TAXABLE_TOTAL));
            this.setCarrierCreditDetailsTotal((FranchisePayableSCCreditTotalModel) resultMap.get(Attributes.RESULT_TOTAL));
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
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doOverPayment() {
        try {
            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }
            this.prepareDataForFilterSC();

            FranchisePayableSCClient payableSCHelper = new FranchisePayableSCClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableSCHelper.getOveraymentDetails(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setOverpayment((List<FranchisePayableSCOverpaymentModel>) resultMap.get(Attributes.RESULT));
            this.setOverpaymentTotal((FranchisePayableSCOverpaymentModel) resultMap.get(Attributes.RESULT_TOTAL));
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
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doShipments() {
        try {
            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }
            this.prepareDataForFilterSC();

            FranchisePayableSCClient payableSCHelper = new FranchisePayableSCClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableSCHelper.getShipmentDetails(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setShipments((List<FranchisePayableSCShipmentModel>) resultMap.get(Attributes.RESULT));
            this.setShipmentTotal((FranchisePayableSCShipmentTotalModel) resultMap.get(Attributes.RESULT_TOTAL));
            this.setTaxableShipmentTotal((FranchisePayableSCShipmentModel) resultMap.get(Attributes.RESULT_TAXABLE_TOTAL));
            this.setNonTaxableShipmentTotal((FranchisePayableSCShipmentModel) resultMap.get(Attributes.RESULT_NON_TAXABLE_TOTAL));
            String recordCountResult = context.get(Attributes.FRANCHISE_PAYABLE_SHIPMENT_DETAILS_RECORD_COUNT_RESULT);
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
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String doTechFees() {
        try {
            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }
            this.prepareDataForFilterSC();

            FranchisePayableSCClient payableSCHelper = new FranchisePayableSCClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableSCHelper.getTechFeeDetails(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setTechFees((List<FranchisePayableSCTechFeeModel>) resultMap.get(Attributes.RESULT));
            this.setTechFeeTotal((FranchisePayableSCTechFeeTotalModel) resultMap.get(Attributes.RESULT_TOTAL));
            String recordCountResult = context.get(Attributes.FRANCHISE_PAYABLE_TECH_FEE_DETAILS_RECORD_COUNT_RESULT);
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
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doOverview() {
        try {
            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }
            this.prepareDataForFilterSC();

            FranchisePayableSCClient payableSCHelper = new FranchisePayableSCClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableSCHelper.getOverview(this.buildFilter(), true);
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            this.setOverview((FranchisePayableSCOverviewModel) resultMap.get(Attributes.RESULT));
            // Set RptTxnId to model.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
            this.setRptTxnId(filter.getRptTxnId());

            // Set frozen message
            this.determineFrozenMessage(context);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String print() {
        try {
            String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/franchise_payable_sc_" + getRptTxnId().replace("-", "") + ".html";
            FranchisePayableFilter filter = this.buildFilter();

            IFranchisePayableSCRender render = new FranchisePayableSCRenderImp(this.getAddInfoMap());
            filter.setRecordSize(null);
            filter.setStartRecord(null);
            render.renderPayableHtmlReport(filter, outputFilePath);
            this.setHtmlContents(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String exportPdf() {
        try {
            String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/franchise_payable_sc_" + getRptTxnId().replace("-", "") + ".html";
            String pdfFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/franchise_payable_sc_" + getRptTxnId().replace("-", "") + ".pdf";
            FranchisePayableFilter filter = this.buildFilter();
            IFranchisePayableSCRender render = new FranchisePayableSCRenderImp(this.getAddInfoMap());
            filter.setRecordSize(null);
            filter.setStartRecord(null);
            render.renderPayableHtmlReportForPdf(filter, htmlFilePath);
            this.setFilename("franchise_payable_sc_" + getRptTxnId().replace("-", "") + ".pdf");
            AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);
            this.setStream(new FileInputStream(new File(pdfFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doSCFranchisePayableInvoice() {
        try {
            this.prepareDataForFilterSC();
            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "success";
            }
            // Prepare filter.
            FranchisePayableFilter filter = this.buildFilter();
            // Generate invoice number.
            FranInvoiceNumberingService invoiceNumberingService = new FranInvoiceNumberingService();
            String invoiceCode = invoiceNumberingService.getFranInvoiceNumbering(this.getFranchiseCode() + "00001", filter.getStartDate(), filter.getEndDate());
            this.setInvoiceNumber(invoiceCode);
            // Get franchise address by code.
            FranchisePayableSCClient payableSCHelper = new FranchisePayableSCClient(this.getAddInfoMap());
            Map<String, Object> resultMap = payableSCHelper.getFranchiseInfoByCode(this.getFranchiseCode(), false);
            this.setFranchise((FranchiseInfoModel) resultMap.get(Attributes.RESULT));
            resultMap = payableSCHelper.getFranchiseInfoByCode("100", false);
            FranchiseInfoModel franchiseInfoModel = (FranchiseInfoModel) resultMap.get(Attributes.RESULT);
            String registrationId = franchiseInfoModel.getRegistrationid();
            String systemAddress = this.getSystemAddress();
            systemAddress += "<br/>" + registrationId;
            this.setSystemAddress(systemAddress);
            resultMap = payableSCHelper.getOverview(filter, true);
            this.setOverview((FranchisePayableSCOverviewModel) resultMap.get(Attributes.RESULT));
            ContextBase context = (ContextBase) resultMap.get(Attributes.CONTEXT_RESULT);
            // Set RptTxnId to model.
            filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
            this.setRptTxnId(filter.getRptTxnId());
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doSCExportFranchiseInvoicePDF() {
        try {
            String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + getRptTxnId().replace("-", "") + "_inv.html";
            String pdfFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + getRptTxnId().replace("-", "") + ".pdf";
            FranchisePayableFilter filter = this.buildFilter();
            FranchisePayableSCClient helper = new FranchisePayableSCClient(this.getAddInfoMap());
            filter.setRecordSize(null);
            filter.setStartRecord(null);
            helper.renderPayableInvoiceHtmlReport(filter, this.getFranchiseCode(), htmlFilePath);
            setFilename(getRptTxnId().replace("-", "") + ".pdf");
            AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);

            this.setStream(new FileInputStream(new File(pdfFilePath)));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "export";
    }

    @SuppressWarnings("unchecked")
    public String doFreeze() {
        this.setErrorMessage("");
        try {
            // Build date range from list of unfrozen franchise payable report
            FranchisePayableSCClient helper = new FranchisePayableSCClient(this.getAddInfoMap());
            Map<String, Object> resultMap = helper.getUnFrozenPeriodList();
            this.setPeriodList((List<FranchisePayablePeriodModel>) resultMap.get(Attributes.RESULT));

            if (StringUtils.isBlank(getStartDate()) || StringUtils.isBlank(getEndDate())) {
                return "input";
            }

            // Get configuration franchise payable start date
            Date fpbStartDate = DateUtils.convertStringToDate(AppConstants.APP_SETTINGS.getFranchisePayableStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            Date start = DateUtils.convertStringToDate(getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);

            // Check start date not less then franchise payable start date in
            // configuration file
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
            resultMap = helper.freezeReceivableReport(filter);

            // Update franchise receivable counter.
            updateFranchiseReceivableCounter(filter);

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

    protected void updateFranchiseReceivableCounter(FranchisePayableFilter filter) throws Exception {
        Date startDate = filter.getStartDate();
        Date endDate = filter.getEndDate();
        for (String franchiseCode : filter.getFranchiseCodeList()) {
            // Create franchise invoice numbering filter.
            FranInvoiceNumberingVo numberingFilter = new FranInvoiceNumberingVo();
            numberingFilter.setStartDate(startDate);
            numberingFilter.setEndDate(endDate);
            numberingFilter.setFranchiseCode(franchiseCode);
            FranInvoiceNumberingDao invoiceNumberingDao = new FranInvoiceNumberingDao();
            // Get counter.
            Integer counter = invoiceNumberingDao.getCurrentCounter(numberingFilter);
            if (counter == null) {
                counter = 1;
            } else {
                counter++;
            }
            // Check exists.
            FranInvoiceNumberingVo numberingVo = invoiceNumberingDao.selectByFilter(numberingFilter);
            if (numberingVo == null) {
                numberingFilter.setCounter(counter);
                // Insert new counter.
                invoiceNumberingDao.insertCounter(this.getAddInfoMap(), numberingFilter);
            }
        }
    }

    public List<FranchisePayableSCCreditModel> getCarrierCreditDetails() {
        return carrierCreditDetails;
    }

    public void setCarrierCreditDetails(List<FranchisePayableSCCreditModel> carrierCreditDetails) {
        this.carrierCreditDetails = carrierCreditDetails;
    }

    public FranchisePayableSCCreditTotalModel getCarrierCreditDetailsTotal() {
        return carrierCreditDetailsTotal;
    }

    public void setCarrierCreditDetailsTotal(FranchisePayableSCCreditTotalModel carrierCreditDetailsTotal) {
        this.carrierCreditDetailsTotal = carrierCreditDetailsTotal;
    }

    public List<FranchisePayableSCOverpaymentModel> getOverpayment() {
        return overpayment;
    }

    public void setOverpayment(List<FranchisePayableSCOverpaymentModel> overpayment) {
        this.overpayment = overpayment;
    }

    public FranchisePayableSCOverpaymentModel getOverpaymentTotal() {
        return overpaymentTotal;
    }

    public void setOverpaymentTotal(FranchisePayableSCOverpaymentModel overpaymentTotal) {
        this.overpaymentTotal = overpaymentTotal;
    }

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

    public List<FranchisePayableSCShipmentModel> getShipments() {
        return shipments;
    }

    public void setShipments(List<FranchisePayableSCShipmentModel> shipments) {
        this.shipments = shipments;
    }

    public FranchisePayableSCShipmentTotalModel getShipmentTotal() {
        return shipmentTotal;
    }

    public void setShipmentTotal(FranchisePayableSCShipmentTotalModel shipmentTotal) {
        this.shipmentTotal = shipmentTotal;
    }

    public List<FranchisePayableSCTechFeeModel> getTechFees() {
        return techFees;
    }

    public void setTechFees(List<FranchisePayableSCTechFeeModel> techFees) {
        this.techFees = techFees;
    }

    public FranchisePayableSCTechFeeTotalModel getTechFeeTotal() {
        return techFeeTotal;
    }

    public void setTechFeeTotal(FranchisePayableSCTechFeeTotalModel techFeeTotal) {
        this.techFeeTotal = techFeeTotal;
    }

    public FranchisePayableSCOverviewModel getOverview() {
        return overview;
    }

    public void setOverview(FranchisePayableSCOverviewModel overview) {
        this.overview = overview;
    }

    public FranchisePayableSCCreditModel getTaxableCarrierCreditTotal() {
        return taxableCarrierCreditTotal;
    }

    public void setTaxableCarrierCreditTotal(FranchisePayableSCCreditModel taxableCarrierCreditTotal) {
        this.taxableCarrierCreditTotal = taxableCarrierCreditTotal;
    }

    public FranchisePayableSCCreditModel getNonTaxableCarrierCreditTotal() {
        return nonTaxableCarrierCreditTotal;
    }

    public void setNonTaxableCarrierCreditTotal(FranchisePayableSCCreditModel nonTaxableCarrierCreditTotal) {
        this.nonTaxableCarrierCreditTotal = nonTaxableCarrierCreditTotal;
    }

    public FranchisePayableSCShipmentModel getTaxableShipmentTotal() {
        return taxableShipmentTotal;
    }

    public void setTaxableShipmentTotal(FranchisePayableSCShipmentModel taxableShipmentTotal) {
        this.taxableShipmentTotal = taxableShipmentTotal;
    }

    public FranchisePayableSCShipmentModel getNonTaxableShipmentTotal() {
        return nonTaxableShipmentTotal;
    }

    public void setNonTaxableShipmentTotal(FranchisePayableSCShipmentModel nonTaxableShipmentTotal) {
        this.nonTaxableShipmentTotal = nonTaxableShipmentTotal;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}