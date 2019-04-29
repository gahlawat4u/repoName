package com.gms.xms.workflow.task.report.ranking.salesrepranking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;
import com.gms.xms.persistence.dao.report.ranking.SalesRepRankingDao;
import com.gms.xms.txndb.vo.reports.ranking.SalesRepRankingVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetSalesRepRankingReportTask.java
 * <p>
 * Author dattrinh 11:03:24 AM
 */
public class GetSalesRepRankingReportTask implements Task {
    private static final Log log = LogFactory.getLog(GetSalesRepRankingReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        SalesRepRankingDao dao = new SalesRepRankingDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        SalesRepRankingFilter filter = GsonUtils.fromGson(context.get(Attributes.SALES_REP_RANKING_FILTER), SalesRepRankingFilter.class);
        try {
            // Do DAO service to get information for the sales rep ranking report.
            List<SalesRepRankingVo> result = dao.getSalesRepRankingReport(filter);
            // Puts result into the context
            context.put(Attributes.SALES_REP_RANKING_REPORT_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}