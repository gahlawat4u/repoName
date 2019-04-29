/**
 *
 */
package com.gms.xms.workflow.client;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.franchisepayable.*;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from FranchisePayableMSClient.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 6:14:06 PM
 */
public class FranchisePayableMSClient extends FranchisePayableClient {

    public FranchisePayableMSClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    /**
     * render franchise payable report in html
     *
     * @param filter         - report filter
     * @param outputFilePath - output file path
     * @throws Exception - on error
     */
    public void renderPayableHtmlReport(FranchisePayableFilter filter, String outputFilePath, Boolean enableNonCentralizedTab) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> resultMap = null;
        String franchiseName = "All";
        String franchiseCode = "All";
        if (filter.getFranchiseCodeList() != null && filter.getFranchiseCodeList().size() == 1) {
            franchiseCode = filter.getFranchiseCodeList().get(0);
            resultMap = getFranchiseInfoByCode(franchiseCode, false);
            FranchiseInfoModel franchiseInfoModel = (FranchiseInfoModel) resultMap.get(Attributes.RESULT);
            if (franchiseInfoModel != null) {
                franchiseName = franchiseInfoModel.getName();
            }
        }
        resultMap = getOverviewData(filter);
        data.put("overview", resultMap.get(Attributes.RESULT));
        data.put("franchiseName", franchiseName);
        data.put("franchiseCode", franchiseCode);
        data.put("startDate", DateUtils.convertDateToString(filter.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        data.put("endDate", DateUtils.convertDateToString(filter.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        // margin details
        resultMap = getMarginDetails(filter);
        data.put("paymentMarginDetails", resultMap.get(Attributes.RESULT));
        data.put("paymentMarginDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        // credit details
        resultMap = getCreditDetails(filter);
        data.put("carrierCreditDetails", resultMap.get(Attributes.RESULT));
        data.put("carrierCreditDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        // deduct details
        resultMap = getDeductDetails(filter);
        data.put("carrierCostDeduction", resultMap.get(Attributes.RESULT));
        data.put("carrierCostDeductionTotal", resultMap.get(Attributes.RESULT_TOTAL));
        // margin 61 details
        resultMap = getMarginDetails61(filter);
        data.put("paymentMarginDetails61", resultMap.get(Attributes.RESULT));
        data.put("paymentMarginDetails61Total", resultMap.get(Attributes.RESULT_TOTAL));
        // non central details
        resultMap = getNonCentralDetails(filter);
        data.put("nonCentralDetails", resultMap.get(Attributes.RESULT));
        data.put("nonCentralDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        // overayment details
        resultMap = getOveraymentDetails(filter);
        data.put("overpayment", resultMap.get(Attributes.RESULT));
        data.put("overpaymentTotal", resultMap.get(Attributes.RESULT_TOTAL));
        data.put("enableNonCentralizedTab", enableNonCentralizedTab);

        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/franchise_payable_reports_ms/franchise_payable_reports_ms.ftl", data);
    }

    public void renderPayableHtmlReportForPdf(FranchisePayableFilter filter, String outputFilePath, Boolean enableNonCentralizedTab) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> resultMap = null;
        String franchiseName = "All";
        String franchiseCode = "All";
        if (filter.getFranchiseCodeList() != null && filter.getFranchiseCodeList().size() == 1) {
            franchiseCode = filter.getFranchiseCodeList().get(0);
            resultMap = getFranchiseInfoByCode(franchiseCode, false);
            FranchiseInfoModel franchiseInfoModel = (FranchiseInfoModel) resultMap.get(Attributes.RESULT);
            if (franchiseInfoModel != null) {
                franchiseName = franchiseInfoModel.getName();
            }
        }
        resultMap = getOverviewData(filter);
        data.put("overview", resultMap.get(Attributes.RESULT));
        data.put("franchiseName", franchiseName);
        data.put("franchiseCode", franchiseCode);
        data.put("startDate", DateUtils.convertDateToString(filter.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        data.put("endDate", DateUtils.convertDateToString(filter.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        // margin details
        resultMap = getMarginDetails(filter);
        data.put("paymentMarginDetails", resultMap.get(Attributes.RESULT));
        data.put("paymentMarginDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        // credit details
        resultMap = getCreditDetails(filter);
        data.put("carrierCreditDetails", resultMap.get(Attributes.RESULT));
        data.put("carrierCreditDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        // deduct details
        resultMap = getDeductDetails(filter);
        data.put("carrierCostDeduction", resultMap.get(Attributes.RESULT));
        data.put("carrierCostDeductionTotal", resultMap.get(Attributes.RESULT_TOTAL));
        // margin 61 details
        resultMap = getMarginDetails61(filter);
        data.put("paymentMarginDetails61", resultMap.get(Attributes.RESULT));
        data.put("paymentMarginDetails61Total", resultMap.get(Attributes.RESULT_TOTAL));
        // non central details
        resultMap = getNonCentralDetails(filter);
        data.put("nonCentralDetails", resultMap.get(Attributes.RESULT));
        data.put("nonCentralDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        // overayment details
        resultMap = getOveraymentDetails(filter);
        data.put("overpayment", resultMap.get(Attributes.RESULT));
        data.put("overpaymentTotal", resultMap.get(Attributes.RESULT_TOTAL));
        data.put("enableNonCentralizedTab", enableNonCentralizedTab);

        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/franchise_payable_reports_ms/franchise_payable_reports_ms.ftl", data);
    }

    /**
     * render franchise payable invoice report in html
     *
     * @param filter         - report filter
     * @param franchiseCode  - franchise code
     * @param outputFilePath - output file path
     * @throws Exception - on error
     */
    public void renderPayableInvoiceHtmlReport(FranchisePayableFilter filter, String franchiseCode, String outputFilePath, Boolean enableNonCentralizedTab) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        String systemAddress = SystemSettingCache.getInstance().getValueByKey("System Address");
        Map<String, Object> resultMap = null;
        resultMap = this.getFranchiseInfoByCode("100", false);
        FranchiseInfoModel franchiseInfoModel = (FranchiseInfoModel) resultMap.get(Attributes.RESULT);
        String registrationId = franchiseInfoModel.getRegistrationid();
        systemAddress = systemAddress.toUpperCase();
        systemAddress = systemAddress.replaceAll("(\r\n|\n)", "<br />");
        systemAddress += "<br/>" + registrationId;
        data.put("systemAddress", systemAddress);
        String siteAddress = SystemSettingCache.getInstance().getValueByKey("Site Address");
        data.put("siteAddress", siteAddress);

        resultMap = this.getFranchiseInfoByCode(franchiseCode, false);
        data.put("franchise", resultMap.get(Attributes.RESULT));
        resultMap = getInvoiceExportData(filter);
        data.put("invoice", resultMap.get(Attributes.RESULT));
        data.put("franchiseName", "");
        data.put("franchiseCode", "");
        data.put("startDate", DateUtils.convertDateToString(filter.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        data.put("endDate", DateUtils.convertDateToString(filter.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));

        String invCode = GenCodeUtils.generateInvoiceCode(franchiseCode + "00001", filter.getDate());
        data.put("invoiceCode", invCode);
        data.put("date", DateUtils.convertDateToString(filter.getDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/franchise_payable_inv/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        data.put("logo", imgAsBase64);
        data.put("enableNonCentralizedTab", enableNonCentralizedTab);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/pdf/franchise_payable_inv/fpb_inv_ms.ftl", data);
    }

    /**
     * return overview data of franchise payable report
     *
     * @param filter - report filter
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getOverviewData(FranchisePayableFilter filter) throws Exception {
        return getOverviewData(filter, false);
    }

    /**
     * return overview data of franchise payable report
     *
     * @param filter    - report filter
     * @param ctxReturn - return context (true: return, false no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getOverviewData(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableMSOverview");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String overview = context.get(Attributes.FRANCHISE_PAYABLE_OVERVIEW_RESULT);
            FranchisePayableMSOverviewVo overviewVo = GsonUtils.fromGson(overview, FranchisePayableMSOverviewVo.class);
            resultMap.put(Attributes.RESULT, ModelUtils.createModelFromVo(overviewVo, FranchisePayableMSOverviewModel.class));
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    public Map<String, Object> getInvoiceExportData(FranchisePayableFilter filter) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableInvExportData");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String invoice = context.get(Attributes.FRANCHISE_PAYABLE_INV_EXPORT_DATA_RESULT);
            FranchisePayableInvoiceExportVo invoiceVo = GsonUtils.fromGson(invoice, FranchisePayableInvoiceExportVo.class);
            resultMap.put(Attributes.RESULT, ModelUtils.createModelFromVo(invoiceVo, FranchisePayableInvoiceExportModel.class));
        }
        return resultMap;
    }

    /**
     * return margin details
     *
     * @param filter - report filter
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getMarginDetails(FranchisePayableFilter filter) throws Exception {
        return getMarginDetails(filter, false);
    }

    /**
     * return margin details
     *
     * @param filter    - report filter
     * @param ctxReturn - return context (true: return, false no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getMarginDetails(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableMSMargin");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);
        resultMap.put(Attributes.CONTEXT_RESULT, context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_PAYMENT_MARGIN_DETAILS_RESULT);
            Type type = new TypeToken<List<FranchisePayableMSMarginVo>>() {
            }.getType();
            List<FranchisePayableMSMarginVo> paymentMarginDetails = GsonUtils.fromGson(result, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(paymentMarginDetails, FranchisePayableMSMarginModel.class));
            String resultTotal = context.get(Attributes.FRANCHISE_PAYABLE_PAYMENT_MARGIN_DETAILS_TOTAL_RESULT);
            if (StringUtils.isNotBlank(resultTotal)) {
                FranchisePayableMSMarginVo totalObjectVo = GsonUtils.fromGson(resultTotal, FranchisePayableMSMarginVo.class);
                resultMap.put(Attributes.RESULT_TOTAL, ModelUtils.createModelFromVo(totalObjectVo, FranchisePayableMSMarginModel.class));
            }
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    /**
     * return credit details
     *
     * @param filter - report filter
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getCreditDetails(FranchisePayableFilter filter) throws Exception {
        return getCreditDetails(filter, false);
    }

    /**
     * return credit details
     *
     * @param filter    - report filter
     * @param ctxReturn - return context (true: return, false no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getCreditDetails(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableMSCredit");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_RESULT);
            Type type = new TypeToken<List<FranchisePayableMSCreditVo>>() {
            }.getType();
            List<FranchisePayableMSCreditVo> payableMSCreditVos = GsonUtils.fromGson(result, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(payableMSCreditVos, FranchisePayableMSCreditModel.class));
            String resultTotal = context.get(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_TOTAL_RESULT);
            if (StringUtils.isNotBlank(resultTotal)) {
                FranchisePayableMSCreditVo totalObjectVo = GsonUtils.fromGson(resultTotal, FranchisePayableMSCreditVo.class);
                resultMap.put(Attributes.RESULT_TOTAL, ModelUtils.createModelFromVo(totalObjectVo, FranchisePayableMSCreditModel.class));
            }
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    /**
     * return deduct details
     *
     * @param filter - report filter
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getDeductDetails(FranchisePayableFilter filter) throws Exception {
        return getDeductDetails(filter, false);
    }

    /**
     * return deduct details
     *
     * @param filter    - report filter
     * @param ctxReturn - return context (true: return, false no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getDeductDetails(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableMSDeduct");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_CARRIER_COST_DEDUCTION_RESULT);
            Type type = new TypeToken<List<FranchisePayableMSDeductVo>>() {
            }.getType();
            List<FranchisePayableMSDeductVo> deductVos = GsonUtils.fromGson(result, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(deductVos, FranchisePayableMSDeductModel.class));
            String resultTotal = context.get(Attributes.FRANCHISE_PAYABLE_CARRIER_COST_DEDUCTION_TOTAL_RESULT);
            if (StringUtils.isNotBlank(resultTotal)) {
                FranchisePayableMSDeductVo totalObjectVo = GsonUtils.fromGson(resultTotal, FranchisePayableMSDeductVo.class);
                resultMap.put(Attributes.RESULT_TOTAL, ModelUtils.createModelFromVo(totalObjectVo, FranchisePayableMSDeductModel.class));
            }
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    /**
     * return 61 margin details
     *
     * @param filter - report filter
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getMarginDetails61(FranchisePayableFilter filter) throws Exception {
        return getMarginDetails61(filter, false);
    }

    /**
     * return 61 margin details
     *
     * @param filter    - report filter
     * @param ctxReturn - return context (true: return, false no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getMarginDetails61(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableMS61Days");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_61DAYS_PAYMENT_CREDIT_DETAILS_RESULT);
            Type type = new TypeToken<List<FranchisePayableMS61DaysVo>>() {
            }.getType();
            List<FranchisePayableMS61DaysVo> sc61DaysVos = GsonUtils.fromGson(result, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(sc61DaysVos, FranchisePayableMS61DaysModel.class));
            String resultTotal = context.get(Attributes.FRANCHISE_PAYABLE_61DAYS_PAYMENT_CREDIT_DETAILS_TOTAL_RESULT);
            if (StringUtils.isNotBlank(resultTotal)) {
                FranchisePayableMS61DaysVo totalObjectVo = GsonUtils.fromGson(resultTotal, FranchisePayableMS61DaysVo.class);
                resultMap.put(Attributes.RESULT_TOTAL, ModelUtils.createModelFromVo(totalObjectVo, FranchisePayableMS61DaysModel.class));
            }
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    /**
     * return non central details
     *
     * @param filter - report filter
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getNonCentralDetails(FranchisePayableFilter filter) throws Exception {
        return getNonCentralDetails(filter, false);
    }

    /**
     * return non central details
     *
     * @param filter    - report filter
     * @param ctxReturn - return context (true: return, false no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getNonCentralDetails(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableMSNonCentral");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_NON_CENTRALIZED_DETAILS_RESULT);
            Type type = new TypeToken<List<FranchisePayableMSNonCentralVo>>() {
            }.getType();
            List<FranchisePayableMSNonCentralVo> nonCentralVos = GsonUtils.fromGson(result, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(nonCentralVos, FranchisePayableMSNonCentralModel.class));
            String resultTotal = context.get(Attributes.FRANCHISE_PAYABLE_NON_CENTRALIZED_DETAILS_TOTAL_RESULT);
            if (StringUtils.isNotBlank(resultTotal)) {
                FranchisePayableMSNonCentralVo totalObjectVo = GsonUtils.fromGson(resultTotal, FranchisePayableMSNonCentralVo.class);
                resultMap.put(Attributes.RESULT_TOTAL, ModelUtils.createModelFromVo(totalObjectVo, FranchisePayableMSNonCentralModel.class));
            }
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    /**
     * return overpayment details
     *
     * @param filter - report filter
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getOveraymentDetails(FranchisePayableFilter filter) throws Exception {
        return getOveraymentDetails(filter, false);
    }

    /**
     * return overpayment details
     *
     * @param filter    - report filter
     * @param ctxReturn - return context (true: return, false no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getOveraymentDetails(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableMSOverpayment");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_OVERPAYMENT_RESULT);
            Type type = new TypeToken<List<FranchisePayableMSOverpaymentVo>>() {
            }.getType();
            List<FranchisePayableMSOverpaymentVo> overpaymentVos = GsonUtils.fromGson(result, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(overpaymentVos, FranchisePayableMSOverpaymentModel.class));
            String resultTotal = context.get(Attributes.FRANCHISE_PAYABLE_OVERPAYMENT_TOTAL_RESULT);
            if (StringUtils.isNotBlank(resultTotal)) {
                FranchisePayableMSOverpaymentVo totalObjectVo = GsonUtils.fromGson(resultTotal, FranchisePayableMSOverpaymentVo.class);
                resultMap.put(Attributes.RESULT_TOTAL, ModelUtils.createModelFromVo(totalObjectVo, FranchisePayableMSOverpaymentModel.class));
            }
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.gms.xms.workflow.client.FranchisePayableClient#getFranchiseInfoByCode
     * (java.lang.String, boolean)
     */
    @Override
    public Map<String, Object> getFranchiseInfoByCode(String franchiseCode, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.FRANCHISE_CODE, franchiseCode);
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchiseInfoByCode");
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String franchise = context.get(Attributes.FRANCHISE_INFO_RESULT);
            FranchiseInfoVo franchiseInfoVo = GsonUtils.fromGson(franchise, FranchiseInfoVo.class);
            resultMap.put(Attributes.RESULT, ModelUtils.createModelFromVo(franchiseInfoVo, FranchiseInfoModel.class));
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    /**
     * send payable report email
     *
     * @param filter    - report filter
     * @param ctxReturn - return context (true: return, false no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> sendPayableReport(FranchisePayableFilter filter, String tmpPath, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-SendEmailMSPayableReport");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.TMP_PATH, tmpPath);
        context = WorkFlowManager.getInstance().process(context);

        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    /**
     * send franchise payable report email
     *
     * @param filter    - report filter
     * @param ctxReturn - return context (true: return, false no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> sendPayableReport(FranchisePayableFilter filter, String tmpPath) throws Exception {
        return this.sendPayableReport(filter, tmpPath, false);
    }
}
