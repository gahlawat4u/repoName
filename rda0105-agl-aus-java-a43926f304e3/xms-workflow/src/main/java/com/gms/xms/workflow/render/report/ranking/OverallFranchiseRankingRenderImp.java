package com.gms.xms.workflow.render.report.ranking;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.ranking.OverallFranchiseRankingFilter;
import com.gms.xms.model.reports.ranking.OverallFranchiseRankingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.reports.ranking.OverallFranchiseRankingVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import com.gms.xms.workflow.service.report.ranking.IOverallFranchiseRankingService;
import com.gms.xms.workflow.service.report.ranking.OverallFranchiseRankingServiceImp;
import org.apache.commons.lang.StringEscapeUtils;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from OverallFranchiseRankingRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class OverallFranchiseRankingRenderImp extends BaseRender implements IOverallFranchiseRankingRender {
    private IOverallFranchiseRankingService rankingService = new OverallFranchiseRankingServiceImp(this.getAddInfo());

    public OverallFranchiseRankingRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderOverallFranchiseRankingsHtmlFile(OverallFranchiseRankingFilter filter, String outputFilePath, String realPath, Boolean excludeGST) throws Exception {
        List<OverallFranchiseRankingVo> overallFranchiseRankingVos = rankingService.getOverallFranchiseRankingReport(filter);
        List<OverallFranchiseRankingModel> overallFranchiseRankingModels = ModelUtils.createListModelFromVo(overallFranchiseRankingVos, OverallFranchiseRankingModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("franchiseList", overallFranchiseRankingModels);
        data.put("excludeGST", excludeGST);
        AppUtils.template2File(outputFilePath, false, "templates/html/report/ranking/overall_franchise_rankings/overall_franchise_rankings.ftl", data);
    }

    @Override
    public void renderOverallFranchiseRankingsXlsFile(OverallFranchiseRankingFilter filter, String outPutFilePath, Boolean excludeGST) throws Exception {
        List<OverallFranchiseRankingVo> overallFranchiseRankingVos = rankingService.getOverallFranchiseRankingReport(filter);
        List<OverallFranchiseRankingModel> overallFranchiseRankingModels = ModelUtils.createListModelFromVo(overallFranchiseRankingVos, OverallFranchiseRankingModel.class);

        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL)));
        data.putVar("franchiseList", overallFranchiseRankingModels);
        data.putVar("excludeGST", excludeGST);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/report/ranking/overall_franchise_rankings/overall_franchise_rankings.xls", data);
    }
}
