package com.gms.xms.workflow.task.report.ranking.franchiseranking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.ranking.OverallFranchiseRankingFilter;
import com.gms.xms.persistence.dao.report.ranking.OverallFranchiseRankingDao;
import com.gms.xms.txndb.vo.reports.ranking.OverallFranchiseRankingVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetOverallFranchiseRankingReportTask.java
 * <p>
 * Author dattrinh 9:25:40 AM
 */
public class GetOverallFranchiseRankingReportTask implements Task {
    private static final Log log = LogFactory.getLog(GetOverallFranchiseRankingReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        OverallFranchiseRankingDao dao = new OverallFranchiseRankingDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        OverallFranchiseRankingFilter filter = GsonUtils.fromGson(context.get(Attributes.OVERALL_FRANCHISE_RANKING_FILTER), OverallFranchiseRankingFilter.class);
        try {
            // Do DAO service to get information for the overall franchise ranking report.
            List<OverallFranchiseRankingVo> result = dao.getOverallFranchiseRankingReport(filter);
            // Puts result into the context
            context.put(Attributes.OVERALL_FRANCHISE_RANKING_REPORT_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}