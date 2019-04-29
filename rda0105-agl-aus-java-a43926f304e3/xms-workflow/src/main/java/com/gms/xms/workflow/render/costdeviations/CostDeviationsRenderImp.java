package com.gms.xms.workflow.render.costdeviations;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.invoicing.CostDeviationFilter;
import com.gms.xms.model.invoicing.CostDeviationModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.invoicing.CostDeviationDao;
import com.gms.xms.txndb.vo.invoicing.CostDeviationVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.apache.commons.lang.StringEscapeUtils;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostDeviationsRenderImp extends BaseRender implements ICostDeviationsRender {

    public CostDeviationsRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlFile(CostDeviationFilter filter, String outputFilePath, String realPath) throws Exception {
        CostDeviationDao dao = new CostDeviationDao();
        List<CostDeviationVo> costDeviationVos = dao.getCostDeviationByFilter(filter);
        List<CostDeviationModel> costDeviationModels = ModelUtils.createListModelFromVo(costDeviationVos, CostDeviationModel.class);
        CostDeviationVo summaryVo = dao.sumCostDeviationByFilter(filter);
        CostDeviationModel summaryModel = ModelUtils.createModelFromVo(summaryVo, CostDeviationModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("realPath", realPath);
        data.put("listData", costDeviationModels);
        data.put("total", summaryModel);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/cost_deviations/cost_deviations.ftl", data);
    }

    @Override
    public void generateXlsFile(CostDeviationFilter filter, String outputFilePath) throws Exception {
        CostDeviationDao dao = new CostDeviationDao();
        List<CostDeviationVo> costDeviationVos = dao.getCostDeviationByFilter(filter);
        List<CostDeviationModel> costDeviationModels = ModelUtils.createListModelFromVo(costDeviationVos, CostDeviationModel.class);
        CostDeviationVo summaryVo = dao.sumCostDeviationByFilter(filter);
        CostDeviationModel summaryModel = ModelUtils.createModelFromVo(summaryVo, CostDeviationModel.class);
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(getAddInfo()));
        data.putVar("listData", costDeviationModels);
        data.putVar("total", summaryModel);
        data.putVar("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL)));
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/cost_deviations/cost_deviations.xls", data);
    }
}