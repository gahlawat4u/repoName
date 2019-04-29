package com.gms.xms.workflow.task.franchisepayable.sc;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.sc.FranchisePayableSCOverviewDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCOverviewVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetFranchisePayableSCOverviewTask
 * <p>
 * Author DatTV Oct 30, 2015
 */
public class GetFranchisePayableSCOverviewTask implements Task {
    private static final Log log = LogFactory.getLog(GetFranchisePayableSCOverviewTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayableSCOverviewDao overviewDao = new FranchisePayableSCOverviewDao();
        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get information for Franchise Payable: SC Overview
            FranchisePayableSCOverviewVo overviewVo = overviewDao.getOverview(filter);

            // Puts result into the context
            context.put(Attributes.FRANCHISE_PAYABLE_OVERVIEW_RESULT, GsonUtils.toGson(overviewVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
