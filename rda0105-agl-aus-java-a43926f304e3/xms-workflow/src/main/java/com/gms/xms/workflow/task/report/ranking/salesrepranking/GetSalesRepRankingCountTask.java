package com.gms.xms.workflow.task.report.ranking.salesrepranking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;
import com.gms.xms.persistence.dao.report.ranking.SalesRepRankingDao;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetSalesRepRankingCountTask.java
 * <p>
 * Author dattrinh 11:05:20 AM
 */
public class GetSalesRepRankingCountTask implements Task {
    private static final Log log = LogFactory.getLog(GetSalesRepRankingCountTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        SalesRepRankingDao dao = new SalesRepRankingDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        SalesRepRankingFilter filter = GsonUtils.fromGson(context.get(Attributes.SALES_REP_RANKING_FILTER), SalesRepRankingFilter.class);
        try {
            // Do DAO service to record count of the sales rep ranking report.
            Long recordCount = dao.checkSalesRepRankingReport(filter);

            // Puts result into the context
            context.put(Attributes.SALES_REP_RANKING_RECORD_COUNT_RESULT, String.valueOf(recordCount));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}