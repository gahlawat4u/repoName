package com.gms.xms.workflow.render.franchisepayablesc;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.workflow.client.FranchisePayableSCClient;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;

import java.util.HashMap;
import java.util.Map;

public class FranchisePayableSCRenderImp extends BaseRender implements IFranchisePayableSCRender {

    public FranchisePayableSCRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderPayableHtmlReport(FranchisePayableFilter filter, String outputFilePath) throws Exception {
        FranchisePayableSCClient client = new FranchisePayableSCClient(this.getAddInfo());
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> resultMap = null;
        ExportLocalizationHelper lang = new ExportLocalizationHelper(this.getAddInfo());
        String franchiseName = lang.translate("All");
        String franchiseCode = lang.translate("All");
        if (filter.getFranchiseCodeList() != null && filter.getFranchiseCodeList().size() == 1) {
            franchiseCode = filter.getFranchiseCodeList().get(0);
            resultMap = client.getFranchiseInfoByCode(franchiseCode, false);
            FranchiseInfoModel franchiseInfoModel = (FranchiseInfoModel) resultMap.get(Attributes.RESULT);
            if (franchiseInfoModel != null) {
                franchiseName = franchiseInfoModel.getName();
            }
        }
        resultMap = client.getOverview(filter);
        data.put("lang", lang);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("overview", resultMap.get(Attributes.RESULT));
        data.put("franchiseName", franchiseName);
        data.put("franchiseCode", franchiseCode);
        data.put("startDate", DateUtils.convertDateToString(filter.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        data.put("endDate", DateUtils.convertDateToString(filter.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        // shipment details
        resultMap = client.getShipmentDetails(filter);
        data.put("shipmentDetails", resultMap.get(Attributes.RESULT));
        data.put("shipmentDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        data.put("taxableShipmentTotal", resultMap.get(Attributes.RESULT_TAXABLE_TOTAL));
        data.put("nonTaxableShipmentTotal", resultMap.get(Attributes.RESULT_NON_TAXABLE_TOTAL));
        //Carrier Credit Details
        resultMap = client.getCreditDetails(filter);
        data.put("carrierCreditDetails", resultMap.get(Attributes.RESULT));
        data.put("carrierCreditDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        data.put("taxableCarrierCreditTotal", resultMap.get(Attributes.RESULT_TAXABLE_TOTAL));
        data.put("nonTaxableCarrierCreditTotal", resultMap.get(Attributes.RESULT_NON_TAXABLE_TOTAL));
        // teach fee details
        resultMap = client.getTechFeeDetails(filter);
        data.put("techFeeDetails", resultMap.get(Attributes.RESULT));
        data.put("techFeeDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        // overayment details
        resultMap = client.getOveraymentDetails(filter);
        data.put("overpayment", resultMap.get(Attributes.RESULT));
        data.put("overpaymentTotal", resultMap.get(Attributes.RESULT_TOTAL));
        AppUtils.template2File(outputFilePath, false, "templates/pdf/history/franchise_payable_reports_sc.ftl", data);
    }

    @Override
    public void renderPayableHtmlReportForPdf(FranchisePayableFilter filter, String outputFilePath) throws Exception {
        FranchisePayableSCClient client = new FranchisePayableSCClient(this.getAddInfo());
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> resultMap = null;
        ExportLocalizationHelper lang = new ExportLocalizationHelper(this.getAddInfo());
        String franchiseName = lang.translate("All");
        String franchiseCode = lang.translate("All");
        if (filter.getFranchiseCodeList() != null && filter.getFranchiseCodeList().size() == 1) {
            franchiseCode = filter.getFranchiseCodeList().get(0);
            resultMap = client.getFranchiseInfoByCode(franchiseCode, false);
            FranchiseInfoModel franchiseInfoModel = (FranchiseInfoModel) resultMap.get(Attributes.RESULT);
            if (franchiseInfoModel != null) {
                franchiseName = franchiseInfoModel.getName();
            }
        }
        resultMap = client.getOverview(filter);
        data.put("lang", lang);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("overview", resultMap.get(Attributes.RESULT));
        data.put("franchiseName", franchiseName);
        data.put("franchiseCode", franchiseCode);
        data.put("startDate", DateUtils.convertDateToString(filter.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        data.put("endDate", DateUtils.convertDateToString(filter.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        // shipment details
        resultMap = client.getShipmentDetails(filter);
        data.put("shipmentDetails", resultMap.get(Attributes.RESULT));
        data.put("shipmentDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        data.put("taxableShipmentTotal", resultMap.get(Attributes.RESULT_TAXABLE_TOTAL));
        data.put("nonTaxableShipmentTotal", resultMap.get(Attributes.RESULT_NON_TAXABLE_TOTAL));
        //Carrier Credit Details
        resultMap = client.getCreditDetails(filter);
        data.put("carrierCreditDetails", resultMap.get(Attributes.RESULT));
        data.put("carrierCreditDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        data.put("taxableCarrierCreditTotal", resultMap.get(Attributes.RESULT_TAXABLE_TOTAL));
        data.put("nonTaxableCarrierCreditTotal", resultMap.get(Attributes.RESULT_NON_TAXABLE_TOTAL));
        // teach fee details
        resultMap = client.getTechFeeDetails(filter);
        data.put("techFeeDetails", resultMap.get(Attributes.RESULT));
        data.put("techFeeDetailsTotal", resultMap.get(Attributes.RESULT_TOTAL));
        // overayment details
        resultMap = client.getOveraymentDetails(filter);
        data.put("overpayment", resultMap.get(Attributes.RESULT));
        data.put("overpaymentTotal", resultMap.get(Attributes.RESULT_TOTAL));
        AppUtils.template2File(outputFilePath, false, "templates/pdf/history/franchise_payable_reports_sc.ftl", data);
    }
}
