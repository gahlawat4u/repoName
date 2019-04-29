package com.gms.xms.workflow.render.searchairbill;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.admin.invoicing.searchairbill.SearchAirbillCsvModel;
import com.gms.xms.model.admin.invoicing.searchairbill.SearchAirbillModel;
import com.gms.xms.model.admin.invoicing.searchairbill.TotalSearchAirbillModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.admin.SearchAirbillDao;
import com.gms.xms.txndb.vo.invoicing.searchairbill.*;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IOUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SearchAirbillRenderImp extends BaseRender implements ISearchAirbillRender {

    public SearchAirbillRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHTMLForPDF(SearchAirbillFilter filter, String outputFilePath) throws Exception {
        SearchAirbillDao dao = new SearchAirbillDao();
        TotalSearchAirbillVo totalSearchAirbillVo = dao.selectCountAirbillList(filter);
        totalSearchAirbillVo.setTotalCustomerAmount(totalSearchAirbillVo.getTotalCustomerAmount());
        totalSearchAirbillVo.setTotalFranchiseAmount(totalSearchAirbillVo.getTotalFranchiseAmount());
        totalSearchAirbillVo.setTotalMargin(totalSearchAirbillVo.getTotalMargin());
        TotalSearchAirbillModel totalSearchAirbillModel = ModelUtils.createModelFromVo(totalSearchAirbillVo, TotalSearchAirbillModel.class);
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/invoicing/search_airbill/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String logo = "data:image/png;base64," + imgDataAsBase64;

        // Generate header of file
        Map<String, Object> data = new HashMap<>();
        data.put("logo", logo);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        AppUtils.template2File(outputFilePath, false, "templates/pdf/invoicing/search_airbill/search_airbill_head.ftl", data);

        int page = 1;
        int pageSize = AppConstants.APP_SETTINGS.getDefaultProcessRecordSize();
        List<SearchAirbillVo> airbillVos = new ArrayList<>();
        do {
            filter.setPage(page);
            filter.setPageSize(pageSize);
            airbillVos = dao.selectAirbillList(filter);
            refineAirbillList(airbillVos);
            List<SearchAirbillModel> airbillModels = ModelUtils.createListModelFromVo(airbillVos, SearchAirbillModel.class);
            data = new HashMap<>();
            data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
            data.put("lang", new ExportLocalizationHelper(getAddInfo()));
            data.put("listAirbills", airbillModels);
            AppUtils.template2File(outputFilePath, true, "templates/pdf/invoicing/search_airbill/search_airbill_body.ftl", data);
            page++;
        } while (airbillVos != null && airbillVos.size() > 0);

        data = new HashMap<>();
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("total", totalSearchAirbillModel);
        AppUtils.template2File(outputFilePath, true, "templates/pdf/invoicing/search_airbill/search_airbill_foot.ftl", data);
    }

    @Override
    public void generateCSVFile(SearchAirbillFilter filter, Integer userLevel, String outputFilePath) throws Exception {
        SearchAirbillDao dao = new SearchAirbillDao();

        // Generate first header line
        Map<String, Object> data = new HashMap<>();
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("userLevel", userLevel);
        AppUtils.template2File(outputFilePath, true, "templates/csv/search_airbill/search_airbill.ftl", data);

        // Generate data lines
        List<SearchAirbillVo> airbillVos = new ArrayList<>();
        int page = 1;
        int pageSize = AppConstants.APP_SETTINGS.getDefaultProcessRecordSize();
        do {
            filter.setPage(page);
            filter.setPageSize(pageSize);
            airbillVos = dao.selectAirbillList(filter);
            refineAirbillList(airbillVos);
            List<SearchAirbillCsvVo> airbillCsvVos = this.buildCSVVo(airbillVos);
            for (SearchAirbillCsvVo searchAirbillCsvVo : airbillCsvVos) {
                Double customerCreditTotal = 0D;
                Double carrierCreditTotal = 0D;
                List<ChargeAirbillVo> listChargesCheck = new LinkedList<>();
                List<ChargeAirbillVo> listCharges = new LinkedList<>();
                for (ChargeAirbillVo chargeAirbillVo : searchAirbillCsvVo.getListCharge()) {
                    if (chargeAirbillVo.getIsBaseCharge() && chargeAirbillVo.getAccessorialCount() == 0) {
                        searchAirbillCsvVo.setCustomerBaseCharge(chargeAirbillVo.getCustomerCost());
                        searchAirbillCsvVo.setFranchiseBaseCharge(chargeAirbillVo.getFranchiseCost());
                        searchAirbillCsvVo.setCarrierBaseCharge(chargeAirbillVo.getCarrierCost());
                    } else {
                        listChargesCheck.add(chargeAirbillVo);
                    }
                }
                listCharges.addAll(listChargesCheck);
                if (listChargesCheck.size() < 8) {
                    for (int i = 0; i < 8 - listChargesCheck.size(); i++) {
                        listCharges.add(new ChargeAirbillVo());
                    }
                }
                if (!searchAirbillCsvVo.getListAdjustment().isEmpty()) {
                    for (RefunAdjustmentVo refunAdjustmentVo : searchAirbillCsvVo.getListAdjustment()) {
                        customerCreditTotal += refunAdjustmentVo.getCustomerAmount();
                        carrierCreditTotal += refunAdjustmentVo.getCarrierAmount();
                    }
                }
                searchAirbillCsvVo.setCustomerCreditTotal(customerCreditTotal);
                searchAirbillCsvVo.setCarrierCreditTotal(carrierCreditTotal);
                searchAirbillCsvVo.setListCharge(listCharges);
            }
            List<SearchAirbillCsvModel> airbillCsvModels = ModelUtils.createListModelFromVo(airbillCsvVos, SearchAirbillCsvModel.class);

            data = new HashMap<>();
            data.put("lang", new ExportLocalizationHelper(getAddInfo()));
            data.put("userLevel", userLevel);
            data.put("listData", airbillCsvModels);
            AppUtils.template2File(outputFilePath, true, "templates/csv/search_airbill/search_airbill_datas.ftl", data);
            page++;
        } while (airbillVos != null && airbillVos.size() > 0);
    }

    protected void refineAirbillList(List<SearchAirbillVo> airbillVos) throws Exception {
        if (airbillVos != null) {
            for (SearchAirbillVo searchAirbillVo : airbillVos) {
                if (searchAirbillVo != null) {
                    if (searchAirbillVo.getGstTaxAmount() == 0) {
                        searchAirbillVo.setGstTaxAmount(null);
                    }
                    searchAirbillVo.setTotalCharges(determineQuoteDetails(searchAirbillVo));
                }
            }
        }
    }

    protected double determineQuoteDetails(SearchAirbillVo airbillVo) throws Exception {
        // Calculate total charges.
        double baseCharge = airbillVo.getBaseCharge();
        if (baseCharge == 0) {
            airbillVo.setBaseCharge(null); // TBA
        }
        double insurance = airbillVo.getWithInSurance();
        double nonStandardCharge = airbillVo.getNonStandardCharge();
        double manualHandlingSurcharge = airbillVo.getManualHandlingSurcharge();
        double totalCharges = baseCharge;
        for (QuoteAirbillVo quoteAirbillVo : airbillVo.getListQuote()) {
            totalCharges += quoteAirbillVo.getAmount();
        }
        // New charge.
        QuoteAirbillVo charge;
        // Add additional protection charge if it have.
        if (airbillVo.getWithInSurance() != null && airbillVo.getWithInSurance() != 0) {
            charge = new QuoteAirbillVo();
            charge.setQuoteDescription("Additional Protection");
            charge.setAmount(airbillVo.getWithInSurance());
            airbillVo.getListQuote().add(charge);
        }
        // Add non-standard charge if it have.
        if (airbillVo.getNonStandardCharge() != null && airbillVo.getNonStandardCharge() != 0) {
            charge = new QuoteAirbillVo();
            charge.setQuoteDescription("Non-standard Shipping Charge");
            charge.setAmount(airbillVo.getNonStandardCharge());
            airbillVo.getListQuote().add(charge);
        }
        // Add GST charge.
        String vatRate;
        if (airbillVo.getSenderCountryCode().equalsIgnoreCase(airbillVo.getReceiverCountryCode())) {
            vatRate = SystemSettingCache.getInstance().getValueByKey("Domestic Tax Percentage");
        } else {
            vatRate = SystemSettingCache.getInstance().getValueByKey("VAT percent based on Base Charge");
        }
        double vat = 0.0;
        if (!StringUtils.isBlank(vatRate)) {
            String taxName = SystemSettingCache.getInstance().getValueByKey("Tax Name");
            vat = (totalCharges + insurance + nonStandardCharge) * Double.parseDouble(vatRate) / 100;
            charge = new QuoteAirbillVo();
            charge.setQuoteDescription(taxName);
            charge.setAmount(vat);
            airbillVo.getListQuote().add(charge);
        }
        // Add manual handling surcharge
        if ( airbillVo.getManualHandlingSurcharge() != null && airbillVo.getManualHandlingSurcharge() !=0){
            charge = new QuoteAirbillVo();
            charge.setQuoteDescription("Manual Handling Surcharge");
            charge.setAmount(airbillVo.getManualHandlingSurcharge());
            airbillVo.getListQuote().add(charge);
        }
        return totalCharges + insurance + nonStandardCharge + manualHandlingSurcharge + vat;
    }

    protected List<SearchAirbillCsvVo> buildCSVVo(List<SearchAirbillVo> airbillVos) throws IllegalAccessException, InvocationTargetException {
        List<SearchAirbillCsvVo> airbillCsvVos = new LinkedList<>();
        for (SearchAirbillVo searchAirbillVo : airbillVos) {
            SearchAirbillCsvVo searchAirbillCsvVo = new SearchAirbillCsvVo();
            BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
            BeanUtils.copyProperties(searchAirbillCsvVo, searchAirbillVo);
            airbillCsvVos.add(searchAirbillCsvVo);
        }
        return airbillCsvVos;
    }
}
