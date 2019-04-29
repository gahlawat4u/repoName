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
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.admin.InvoiceSettingFilter;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.franchisepayable.sc.*;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.admin.InvoiceSettingDao;
import com.gms.xms.persistence.service.FranInvoiceNumberingService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.*;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
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
 * Posted from FranchisePayableSCClient
 * <p>
 * Author DatTV Oct 28, 2015
 */
public class FranchisePayableSCClient extends FranchisePayableClient {

    public FranchisePayableSCClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public Map<String, Object> getCreditDetails(FranchisePayableFilter filter) throws Exception {
        return getCreditDetails(filter, false);
    }

    public Map<String, Object> getCreditDetails(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableSCCredit");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_RESULT);
            Type type = new TypeToken<List<FranchisePayableSCCreditVo>>() {
            }.getType();
            List<FranchisePayableSCCreditVo> payableSCCreditVos = GsonUtils.fromGson(result, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(payableSCCreditVos, FranchisePayableSCCreditModel.class));
            // Taxable total record
            String taxableTotal = context.get(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_TAXABLE_TOTAL_RESULT);
            if (StringUtils.isNotBlank(taxableTotal)) {
                FranchisePayableSCCreditVo taxableTotalRecord = GsonUtils.fromGson(taxableTotal, FranchisePayableSCCreditVo.class);
                resultMap.put(Attributes.RESULT_TAXABLE_TOTAL, ModelUtils.createModelFromVo(taxableTotalRecord, FranchisePayableSCCreditModel.class));
            }
            // Non-taxable total record
            String nonTaxableTotal = context.get(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_NON_TAXABLE_TOTAL_RESULT);
            if (StringUtils.isNotBlank(nonTaxableTotal)) {
                FranchisePayableSCCreditVo nonTaxableTotalRecord = GsonUtils.fromGson(nonTaxableTotal, FranchisePayableSCCreditVo.class);
                resultMap.put(Attributes.RESULT_NON_TAXABLE_TOTAL, ModelUtils.createModelFromVo(nonTaxableTotalRecord, FranchisePayableSCCreditModel.class));
            }
            // Total record
            String resultTotal = context.get(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_TOTAL_RESULT);
            if (StringUtils.isNotBlank(resultTotal)) {
                FranchisePayableSCCreditTotalVo totalObjectVo = GsonUtils.fromGson(resultTotal, FranchisePayableSCCreditTotalVo.class);
                resultMap.put(Attributes.RESULT_TOTAL, ModelUtils.createModelFromVo(totalObjectVo, FranchisePayableSCCreditTotalModel.class));
            }
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    public Map<String, Object> getOveraymentDetails(FranchisePayableFilter filter) throws Exception {
        return getOveraymentDetails(filter, false);
    }

    public Map<String, Object> getOveraymentDetails(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableSCOverpayment");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_OVERPAYMENT_RESULT);
            Type type = new TypeToken<List<FranchisePayableSCOverpaymentVo>>() {
            }.getType();
            List<FranchisePayableSCOverpaymentVo> overpaymentVos = GsonUtils.fromGson(result, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(overpaymentVos, FranchisePayableSCOverpaymentModel.class));
            String resultTotal = context.get(Attributes.FRANCHISE_PAYABLE_OVERPAYMENT_TOTAL_RESULT);
            if (StringUtils.isNotBlank(resultTotal)) {
                FranchisePayableSCOverpaymentVo totalObjectVo = GsonUtils.fromGson(resultTotal, FranchisePayableSCOverpaymentVo.class);
                resultMap.put(Attributes.RESULT_TOTAL, ModelUtils.createModelFromVo(totalObjectVo, FranchisePayableSCOverpaymentModel.class));
            }
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;

    }

    public Map<String, Object> getShipmentDetails(FranchisePayableFilter filter) throws Exception {
        return getShipmentDetails(filter, false);
    }

    public Map<String, Object> getShipmentDetails(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableSCShipmentDetail");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_SHIPMENT_DETAILS_RESULT);
            Type type = new TypeToken<List<FranchisePayableSCShipmentVo>>() {
            }.getType();
            List<FranchisePayableSCShipmentVo> payableSCShipmentVos = GsonUtils.fromGson(result, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(payableSCShipmentVos, FranchisePayableSCShipmentModel.class));
            // Taxable total record
            String taxableTotal = context.get(Attributes.FRANCHISE_PAYABLE_SHIPMENT_DETAILS_TAXABLE_TOTAL_RESULT);
            if (StringUtils.isNotBlank(taxableTotal)) {
                FranchisePayableSCShipmentVo taxableTotalRecord = GsonUtils.fromGson(taxableTotal, FranchisePayableSCShipmentVo.class);
                resultMap.put(Attributes.RESULT_TAXABLE_TOTAL, ModelUtils.createModelFromVo(taxableTotalRecord, FranchisePayableSCShipmentModel.class));
            }
            // Non-taxable total record
            String nonTaxableTotal = context.get(Attributes.FRANCHISE_PAYABLE_SHIPMENT_DETAILS_NON_TAXABLE_TOTAL_RESULT);
            if (StringUtils.isNotBlank(nonTaxableTotal)) {
                FranchisePayableSCShipmentVo nonTaxableTotalRecord = GsonUtils.fromGson(nonTaxableTotal, FranchisePayableSCShipmentVo.class);
                resultMap.put(Attributes.RESULT_NON_TAXABLE_TOTAL, ModelUtils.createModelFromVo(nonTaxableTotalRecord, FranchisePayableSCShipmentModel.class));
            }
            // Total record
            String resultTotal = context.get(Attributes.FRANCHISE_PAYABLE_SHIPMENT_DETAILS_TOTAL_RESULT);
            if (StringUtils.isNotBlank(resultTotal)) {
                FranchisePayableSCShipmentTotalVo totalObjectVo = GsonUtils.fromGson(resultTotal, FranchisePayableSCShipmentTotalVo.class);
                resultMap.put(Attributes.RESULT_TOTAL, ModelUtils.createModelFromVo(totalObjectVo, FranchisePayableSCShipmentTotalModel.class));
            }
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    public Map<String, Object> getTechFeeDetails(FranchisePayableFilter filter) throws Exception {
        return getTechFeeDetails(filter, false);
    }

    public Map<String, Object> getTechFeeDetails(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableSCTechFeeDetail");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_TECH_FEE_DETAILS_RESULT);
            Type type = new TypeToken<List<FranchisePayableSCTechFeeVo>>() {
            }.getType();
            List<FranchisePayableSCTechFeeVo> payableSCTechFeeVos = GsonUtils.fromGson(result, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(payableSCTechFeeVos, FranchisePayableSCTechFeeModel.class));
            String resultTotal = context.get(Attributes.FRANCHISE_PAYABLE_TECH_FEE_DETAILS_TOTAL_RESULT);
            if (StringUtils.isNotBlank(resultTotal)) {
                FranchisePayableSCTechFeeTotalVo totalObjectVo = GsonUtils.fromGson(resultTotal, FranchisePayableSCTechFeeTotalVo.class);
                resultMap.put(Attributes.RESULT_TOTAL, ModelUtils.createModelFromVo(totalObjectVo, FranchisePayableSCTechFeeTotalModel.class));
            }
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    public Map<String, Object> getOverview(FranchisePayableFilter filter) throws Exception {
        return getOverview(filter, false);
    }

    public Map<String, Object> getOverview(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchisePayableSCOverview");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_OVERVIEW_RESULT);
            FranchisePayableSCOverviewVo overviewVo = GsonUtils.fromGson(result, FranchisePayableSCOverviewVo.class);
            resultMap.put(Attributes.RESULT, ModelUtils.createModelFromVo(overviewVo, FranchisePayableSCOverviewModel.class));
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    public void renderPayableInvoiceHtmlReport(FranchisePayableFilter filter, String franchiseCode, String outputFilePath) throws Exception {
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
        resultMap = this.getOverview(filter);
        data.put("invoice", resultMap.get(Attributes.RESULT));
        data.put("franchiseName", "");
        data.put("franchiseCode", "");
        data.put("startDate", DateUtils.convertDateToString(filter.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        data.put("endDate", DateUtils.convertDateToString(filter.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));

        FranInvoiceNumberingService invoiceNumberingService = new FranInvoiceNumberingService();
        String invCode = invoiceNumberingService.getFranInvoiceNumbering(franchiseCode + "00001", filter.getStartDate(), filter.getEndDate());
        InvoiceSettingDao invoiceSettingDao = new InvoiceSettingDao();
        InvoiceSettingFilter invoiceSettingfilter = new InvoiceSettingFilter();
        // invoiceSettingfilter.setFranchiseCode(Long.valueOf(franchiseCode + "00001"));
        invoiceSettingfilter.setFranchiseCode(10000001L);
        invoiceSettingfilter.setInvSettingName("Invoice Payment Text");
        String invoicePaymentText = invoiceSettingDao.getInvoiceSetting(invoiceSettingfilter);
        invoicePaymentText = StringUtils.isBlank(invoicePaymentText) ? "" : invoicePaymentText;
        data.put("invoiceCode", invCode);
        data.put("date", DateUtils.convertDateToString(filter.getDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        invoicePaymentText = invoicePaymentText.replaceAll("(\r\n|\n)", "<br />");
        data.put("invoicePaymentText", invoicePaymentText);
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/franchise_payable_inv/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        data.put("logo", imgAsBase64);
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/pdf/franchise_payable_inv/fpb_inv_sc.ftl", data);
    }

    public Map<String, Object> freezeReceivableReport(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-FreezeFranchiseReceivableReport");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String result = context.get(Attributes.FRANCHISE_PAYABLE_OVERVIEW_RESULT);
            FranchisePayableSCOverviewVo overviewVo = GsonUtils.fromGson(result, FranchisePayableSCOverviewVo.class);
            resultMap.put(Attributes.RESULT, ModelUtils.createModelFromVo(overviewVo, FranchisePayableSCOverviewModel.class));
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    public Map<String, Object> freezeReceivableReport(FranchisePayableFilter filter) throws Exception {
        return this.freezeReceivableReport(filter, false);
    }
}
