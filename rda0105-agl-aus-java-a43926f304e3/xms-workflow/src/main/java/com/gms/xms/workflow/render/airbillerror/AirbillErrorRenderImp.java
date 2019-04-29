package com.gms.xms.workflow.render.airbillerror;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.invoicing.RepairAirbillErrorFilter;
import com.gms.xms.model.invoicing.AirbillErrorModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.invoicing.IRepairAirbillErrorService;
import com.gms.xms.persistence.service.invoicing.RepairAirbillErrorServiceImp;
import com.gms.xms.txndb.vo.invoicing.AirbillErrorVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirbillErrorRenderImp extends BaseRender implements IAirbillErrorRender {

    public AirbillErrorRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlFile(RepairAirbillErrorFilter filter, String outputFilePath, Boolean showSenderAddress, Boolean showReceiverAddress, Boolean showAccount) throws Exception {
        IRepairAirbillErrorService airbillErrorService = new RepairAirbillErrorServiceImp();
        List<AirbillErrorVo> airbillErrorVos = airbillErrorService.getAirbillErrorByFilter(filter);
        List<AirbillErrorModel> airbillErrorModels = ModelUtils.createListModelFromVo(airbillErrorVos, AirbillErrorModel.class);

        ExportLocalizationHelper lang = new ExportLocalizationHelper(getAddInfo());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("showSenderAddress", showSenderAddress);
        data.put("showReceiverAddress", showReceiverAddress);
        data.put("showAccount", showAccount);
        data.put("lang", lang);
        data.put("listData", airbillErrorModels);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/airbill_error/airbill_error.ftl", data);
    }

    @Override
    public void generateXlsFile(RepairAirbillErrorFilter filter, String outputFilePath, Boolean showSenderAddress, Boolean showReceiverAddress, Boolean showAccount) throws Exception {
        IRepairAirbillErrorService airbillErrorService = new RepairAirbillErrorServiceImp();
        List<AirbillErrorVo> airbillErrorVos = airbillErrorService.getAirbillErrorByFilter(filter);
        List<AirbillErrorModel> airbillErrorModels = ModelUtils.createListModelFromVo(airbillErrorVos, AirbillErrorModel.class);

        ExportLocalizationHelper lang = new ExportLocalizationHelper(getAddInfo());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("showSenderAddress", showSenderAddress);
        data.put("showReceiverAddress", showReceiverAddress);
        data.put("showAccount", showAccount);
        data.put("lang", lang);
        data.put("listData", airbillErrorModels);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/airbill_error/airbill_error.xls", data);
    }
}
