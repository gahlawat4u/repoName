package com.gms.xms.workflow.render.report.ranking;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;
import com.gms.xms.model.reports.ranking.SalesRepRankingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.reports.ranking.SalesRepRankingVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import com.gms.xms.workflow.service.report.ranking.ISalesRepRankingService;
import com.gms.xms.workflow.service.report.ranking.SalesRepRankingServiceImp;
import org.apache.commons.lang.StringEscapeUtils;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from SalesRepRankingRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class SalesRepRankingRenderImp extends BaseRender implements ISalesRepRankingRender {
    private ISalesRepRankingService rankingService = new SalesRepRankingServiceImp(this.getAddInfo());

    public SalesRepRankingRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderSaleRepsRankingHtmlFile(SalesRepRankingFilter filter, String outputFilePath, String realPath, Boolean excludeGST) throws Exception {
        List<SalesRepRankingVo> salesRepRankingVos = rankingService.getSalesRepRankingReport(filter);
        List<SalesRepRankingModel> salesRepRankingModels = ModelUtils.createListModelFromVo(salesRepRankingVos, SalesRepRankingModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("realPath", realPath);
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("franchiseList", salesRepRankingModels);
        data.put("excludeGST", excludeGST);
        AppUtils.template2File(outputFilePath, false, "templates/html/report/ranking/sales_rep_ranking/sales_rep_ranking.ftl", data);
    }

    @Override
    public void renderSaleRepsRankingXlsFile(SalesRepRankingFilter filter, String outPutFilePath, Boolean excludeGST) throws Exception {
        List<SalesRepRankingVo> salesRepRankingVos = rankingService.getSalesRepRankingReport(filter);
        List<SalesRepRankingModel> salesRepRankingModels = ModelUtils.createListModelFromVo(salesRepRankingVos, SalesRepRankingModel.class);

        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL)));
        data.putVar("franchiseList", salesRepRankingModels);
        data.putVar("excludeGST", excludeGST);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/report/ranking/sales_rep_ranking/sales_rep_ranking.xls", data);
    }
}
