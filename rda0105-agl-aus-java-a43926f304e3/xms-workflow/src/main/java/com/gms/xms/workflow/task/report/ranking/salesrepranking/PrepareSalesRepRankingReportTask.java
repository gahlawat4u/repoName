package com.gms.xms.workflow.task.report.ranking.salesrepranking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;
import com.gms.xms.persistence.dao.report.ranking.SalesRepRankingDao;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from PrepareSalesRepRankingReportTask.java
 * <p>
 * Author dattrinh 11:00:58 AM
 */
public class PrepareSalesRepRankingReportTask implements Task {
    private static final Log log = LogFactory.getLog(PrepareSalesRepRankingReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        SalesRepRankingDao dao = new SalesRepRankingDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        SalesRepRankingFilter filter = GsonUtils.fromGson(context.get(Attributes.SALES_REP_RANKING_FILTER), SalesRepRankingFilter.class);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            // Check if report data was prepared or not?
            long count = dao.checkSalesRepRankingReport(filter);
            // Prepare data for the report if it wasn't prepared
            if (count <= 0) {
                dao.prepareSalesRepRankingReport(addInfo, filter);
            }
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}