package com.gms.xms.workflow.task.report.ranking.franchiseranking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.ranking.OverallFranchiseRankingFilter;
import com.gms.xms.persistence.dao.report.ranking.OverallFranchiseRankingDao;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from PrepareOverallFranchiseRankingReportTask.java
 * <p>
 * Author dattrinh 9:19:51 AM
 */
public class PrepareOverallFranchiseRankingReportTask implements Task {
    private static final Log log = LogFactory.getLog(PrepareOverallFranchiseRankingReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        OverallFranchiseRankingDao dao = new OverallFranchiseRankingDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        OverallFranchiseRankingFilter filter = GsonUtils.fromGson(context.get(Attributes.OVERALL_FRANCHISE_RANKING_FILTER), OverallFranchiseRankingFilter.class);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            // Check if report data was prepared or not?
            long count = dao.checkOverallFranchiseRankingReport(filter);
            // Prepare data for the report if it wasn't prepared
            if (count <= 0) {
                dao.prepareOverallFranchiseRankingReport(addInfo, filter);
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