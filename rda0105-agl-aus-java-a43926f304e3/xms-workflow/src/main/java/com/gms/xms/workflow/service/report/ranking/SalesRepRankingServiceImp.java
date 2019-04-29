package com.gms.xms.workflow.service.report.ranking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;
import com.gms.xms.txndb.vo.reports.ranking.SalesRepRankingVo;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.service.BaseService;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Posted from SalesRepRankingServiceImp.java
 * <p>
 * Author dattrinh 11:11:35 AM
 */
public class SalesRepRankingServiceImp extends BaseService implements ISalesRepRankingService {
    public SalesRepRankingServiceImp(Map<String, String> context) {
        super(context);
    }

    @Override
    public List<SalesRepRankingVo> getSalesRepRankingReport(SalesRepRankingFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.SALES_REP_RANKING_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetSalesRepRankingReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<SalesRepRankingVo> rankingVos = GsonUtils.fromGson(context.get(Attributes.SALES_REP_RANKING_REPORT_RESULT), new TypeToken<List<SalesRepRankingVo>>() {
            }.getType());
            return rankingVos;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public long getSalesRepRankingCount(SalesRepRankingFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.SALES_REP_RANKING_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetSalesRepRankingCount");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            Long recordCount = Long.valueOf(context.get(Attributes.SALES_REP_RANKING_RECORD_COUNT_RESULT));
            return recordCount;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }
}
