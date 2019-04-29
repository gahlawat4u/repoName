package com.gms.xms.workflow.task.franchisepayable.sc;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayableRptTxnIdDao;
import com.gms.xms.persistence.dao.franchisepayable.sc.FranchisePayableSCTaskDaoService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from SCDetermineReportCacheTask
 * <p>
 * Author DatTV Oct 28, 2015
 */
public class DetermineReportCacheSCTask implements Task {
    private static final Log log = LogFactory.getLog(DetermineReportCacheSCTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        log.info("Going to determine the report is cached or not...");
        FranchisePayableRptTxnIdDao fpbRptTxnIdDao = new FranchisePayableRptTxnIdDao();
        FranchisePayableSCTaskDaoService fpbTaskDao = new FranchisePayableSCTaskDaoService();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            // Get the filter from the context.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

            if (StringUtils.isBlank(filter.getRptTxnId())) {
                context.put(Attributes.HAS_CACHE_ID, AppConstants.NO_FLAG);
            } else {
                // Check if Report Transaction ID existed or not from the
                // database?
                int count = fpbRptTxnIdDao.getRptTxnIdCount(filter);

                if (count <= 0) {
                    // Check if report is frozen or not?
                    if (AppConstants.YES_FLAG.equalsIgnoreCase(context.getString(Attributes.FRANCHISE_PAYABLE_IS_XMS2))) {
                        // If it's not -> prepare data for report from the
                        // frozen
                        fpbTaskDao.prepareDataForReportFromFrozen(addInfo, filter.getRptTxnId());

                        // and set HAS_CACHE_ID is YES
                        context.put(Attributes.HAS_CACHE_ID, AppConstants.YES_FLAG);
                    }
                    context.put(Attributes.HAS_CACHE_ID, AppConstants.NO_FLAG);
                } else {
                    context.put(Attributes.HAS_CACHE_ID, AppConstants.YES_FLAG);
                }
            }
        } catch (Exception ex) {
            log.error(ex);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }
}
