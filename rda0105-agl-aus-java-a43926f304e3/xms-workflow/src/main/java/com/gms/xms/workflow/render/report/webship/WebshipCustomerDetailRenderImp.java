package com.gms.xms.workflow.render.report.webship;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.webship.WebshipCustomerDetailFilter;
import com.gms.xms.model.reports.webship.WebshipCustomerDetailChargeModel;
import com.gms.xms.model.reports.webship.WebshipCustomerDetailExportModel;
import com.gms.xms.model.reports.webship.WebshipCustomerDetailModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.report.webship.IWebshipCustomerDetailService;
import com.gms.xms.persistence.service.report.webship.WebshipCustomerDetailServiceImp;
import com.gms.xms.txndb.vo.reports.webship.WebshipCustomerDetailVo;
import com.gms.xms.txndb.vo.webship.addressbook.AddressBookVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from WebshipCustomerDetailRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class WebshipCustomerDetailRenderImp extends BaseRender implements IWebshipCustomerDetailRender {
    private IWebshipCustomerDetailService service = new WebshipCustomerDetailServiceImp();

    public WebshipCustomerDetailRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderWebshipCustomerDetailHtmlFile(WebshipCustomerDetailFilter filter, String outputFilePath, String realPath) throws Exception {
        List<WebshipCustomerDetailVo> detailVos = service.getWebshipCustomerDetailReport(filter);
        List<WebshipCustomerDetailModel> detailModels = ModelUtils.createListModelFromVo(detailVos, WebshipCustomerDetailModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("details", detailModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/report/webship/webship_customer_detail/webship_customer_detail.ftl", data);
    }

    @Override
    public void renderWebshipCustomerDetailXlsFile(WebshipCustomerDetailFilter filter, String outPutFilePath) throws Exception {
        List<WebshipCustomerDetailVo> detailVos = service.getWebshipCustomerDetailReport(filter);
        List<WebshipCustomerDetailModel> detailModels = ModelUtils.createListModelFromVo(detailVos, WebshipCustomerDetailModel.class);
        List<WebshipCustomerDetailExportModel> exportModels = new LinkedList<WebshipCustomerDetailExportModel>();
        String currencySymbol = SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL);
        ExportLocalizationHelper lang = new ExportLocalizationHelper(this.getAddInfo());
        for (WebshipCustomerDetailModel detailModel : detailModels) {
            WebshipCustomerDetailExportModel exportModel = new WebshipCustomerDetailExportModel();
            exportModel.setInvoiceCode(detailModel.getInvoiceCode());
            exportModel.setAirbillNumber(detailModel.getAirbillNumber());
            exportModel.setCustomerCode(detailModel.getCustomerCode());
            exportModel.setShipmentDate(detailModel.getShipmentDate());

            // Set sender address
            exportModel.setSenderCompanyName(detailModel.getSenderCompanyName());
            exportModel.setSenderContactName(detailModel.getSenderContactName());
            exportModel.setSenderAddress1(detailModel.getSenderAddress1());
            exportModel.setSenderAddress2(detailModel.getSenderAddress2());
            exportModel.setSenderCity(detailModel.getSenderCity());
            exportModel.setSenderState(detailModel.getSenderState());
            exportModel.setSenderPostalCode(detailModel.getSenderPostalCode());
            exportModel.setSenderCountryName(detailModel.getSenderCountryName());

            // Set receiver address
            exportModel.setReceiverCompanyName(detailModel.getReceiverCompanyName());
            exportModel.setReceiverContactName(detailModel.getReceiverContactName());
            exportModel.setReceiverAddress1(detailModel.getReceiverAddress1());
            exportModel.setReceiverAddress2(detailModel.getReceiverAddress2());
            exportModel.setReceiverCity(detailModel.getReceiverCity());
            exportModel.setReceiverState(detailModel.getReceiverState());
            exportModel.setReceiverPostalCode(detailModel.getReceiverPostalCode());
            exportModel.setReceiverCountryName(detailModel.getReceiverCountryName());

            exportModel.setNoOfPieces(detailModel.getNoOfPieces());
            exportModel.setWeight(detailModel.getWeight());
            exportModel.setWeightUnit(detailModel.getWeightUnit());
            exportModel.setQuoted(detailModel.getQuoted());
            exportModel.setServiceType(detailModel.getServiceType());
            exportModel.setTotalCustomerCost(detailModel.getTotalCustomerCost());
            exportModel.setTotalFranchiseCost(detailModel.getTotalFranchiseCost());
            exportModel.setTotalMargin(detailModel.getTotalMargin());
            String chargeStr = "";
            String surchargeStr = "";
            for (WebshipCustomerDetailChargeModel chargeModel : detailModel.getCharges()) {
                chargeStr += chargeModel.getDescription() + " - " + currencySymbol + chargeModel.getCustomerCost() + "(" + currencySymbol + chargeModel.getFranchiseCost() + ")[" + currencySymbol + chargeModel.getMargin() + "]\n";
                if (!chargeModel.getIsBaseCharge().equals("true")) {
                    surchargeStr += chargeModel.getDescription() + "\n";
                }
            }
            exportModel.setChargesString(chargeStr);
            exportModel.setSurchargeString(surchargeStr);
            if (detailModel.getInsurance().equals("true")) {
                exportModel.setInsurance(lang.translate("Yes"));
            } else {
                exportModel.setInsurance(lang.translate("No"));
            }
            if (detailModel.getDutiesTaxesFee().equals("true")) {
                exportModel.setDutiesTaxesFee(lang.translate("Yes"));
            } else {
                exportModel.setDutiesTaxesFee(lang.translate("No"));
            }
            if (detailModel.getDangerousGoods().equals("true")) {
                exportModel.setDangerousGoods(lang.translate("Yes"));
            } else {
                exportModel.setDangerousGoods(lang.translate("No"));
            }
            if (detailModel.getPreClearance().equals("true")) {
                exportModel.setPreClearance(lang.translate("Yes"));
            } else {
                exportModel.setPreClearance(lang.translate("No"));
            }
            exportModels.add(exportModel);
        }

        Context data = new Context();
        data.putVar("lang", lang);
        data.putVar("currencySymbol", currencySymbol);
        data.putVar("details", exportModels);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/report/webship/webship_customer_detail/webship_customer_detail.xls", data);
    }
}
