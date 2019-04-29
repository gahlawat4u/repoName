package com.gms.xms.workflow.service.report.ranking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.ranking.OverallFranchiseRankingFilter;
import com.gms.xms.txndb.vo.reports.ranking.OverallFranchiseRankingVo;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.service.BaseService;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Posted from OverallFranchiseRankingServiceImp.java
 * <p>
 * Author dattrinh 9:41:46 AM
 */
public class OverallFranchiseRankingServiceImp extends BaseService implements IOverallFranchiseRankingService {
    public OverallFranchiseRankingServiceImp(Map<String, String> context) {
        super(context);
    }

    @Override
    public List<OverallFranchiseRankingVo> getOverallFranchiseRankingReport(OverallFranchiseRankingFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.OVERALL_FRANCHISE_RANKING_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetOverallFranchiseRankingReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<OverallFranchiseRankingVo> rankingVos = GsonUtils.fromGson(context.get(Attributes.OVERALL_FRANCHISE_RANKING_REPORT_RESULT), new TypeToken<List<OverallFranchiseRankingVo>>() {
            }.getType());
            return rankingVos;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public long getOverallFranchiseRankingCount(OverallFranchiseRankingFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.OVERALL_FRANCHISE_RANKING_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetOverallFranchiseRankingCount");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            Long recordCount = Long.valueOf(context.get(Attributes.OVERALL_FRANCHISE_RANKING_RECORD_COUNT_RESULT));
            return recordCount;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }
}
