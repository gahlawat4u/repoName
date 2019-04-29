package com.gms.xms.workflow.render.airbillmargins;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.invoicing.AirbillMarginFilter;
import com.gms.xms.model.invoicing.AirbillMarginModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.invoicing.AirbillMarginDao;
import com.gms.xms.txndb.vo.invoicing.AirbillMarginVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.apache.commons.lang.StringEscapeUtils;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirbillMarginsRenderImp extends BaseRender implements IAirbillMarginsRender {
    public AirbillMarginsRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlFile(AirbillMarginFilter filter, String outputFilePath, String realPath) throws Exception {
        AirbillMarginDao dao = new AirbillMarginDao();
        List<AirbillMarginVo> airbillMarginVos = dao.getAirbillMarginByFilter(filter);
        List<AirbillMarginModel> airbillMarginModels = ModelUtils.createListModelFromVo(airbillMarginVos, AirbillMarginModel.class);
        AirbillMarginVo summaryVo = dao.sumAirbillMarginByFilter(filter);
        AirbillMarginModel summaryModel = ModelUtils.createModelFromVo(summaryVo, AirbillMarginModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("realPath", realPath);
        data.put("listData", airbillMarginModels);
        data.put("total", summaryModel);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/airbill_margins/airbill_margins.ftl", data);
    }

    @Override
    public void generateXlsFile(AirbillMarginFilter filter, String outputFilePath) throws Exception {
        AirbillMarginDao dao = new AirbillMarginDao();
        List<AirbillMarginVo> airbillMarginVos = dao.getAirbillMarginByFilter(filter);
        List<AirbillMarginModel> airbillMarginModels = ModelUtils.createListModelFromVo(airbillMarginVos, AirbillMarginModel.class);
        AirbillMarginVo summaryVo = dao.sumAirbillMarginByFilter(filter);
        AirbillMarginModel summaryModel = ModelUtils.createModelFromVo(summaryVo, AirbillMarginModel.class);
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(getAddInfo()));
        data.putVar("listData", airbillMarginModels);
        data.putVar("total", summaryModel);
        data.putVar("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL)));
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/airbill_margins/airbill_margins.xls", data);
    }
}
