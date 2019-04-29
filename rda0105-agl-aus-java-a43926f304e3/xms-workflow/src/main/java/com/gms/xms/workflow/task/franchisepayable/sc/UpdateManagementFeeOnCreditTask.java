package com.gms.xms.workflow.task.franchisepayable.sc;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.sc.FranchisePayableSCOverviewDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCOverviewVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from Aug 9, 2016 2:00:57 PM
 * <p>
 * Author dattrinh
 */
public class UpdateManagementFeeOnCreditTask implements Task {
    private static final Log log = LogFactory.getLog(UpdateManagementFeeOnCreditTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            // Update management fee on credit if it hasn't got a cache.
            if (!AppConstants.YES_FLAG.equalsIgnoreCase(context.getString(Attributes.HAS_CACHE_ID))) {
                Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
                }.getType());
                // Get the filter from the context.
                FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);
                FranchisePayableSCOverviewDao overviewDao = new FranchisePayableSCOverviewDao();
                for (String franchiseCode : filter.getFranchiseCodeList()) {
                    FranchisePayableFilter newFilter = new FranchisePayableFilter();
                    newFilter.setRptTxnId(filter.getRptTxnId());
                    newFilter.setFranchiseCode(franchiseCode);
                    // Get management fee on credit.
                    FranchisePayableSCOverviewVo overviewVo = overviewDao.getManagementFeeOnCredit(newFilter);
                    if (overviewVo == null) {
                        overviewVo = new FranchisePayableSCOverviewVo();
                        overviewVo.setRptTxnId(filter.getRptTxnId());
                        overviewVo.setManagementFeeOnCreditRevenue(0.0);
                        overviewVo.setManagementFeeOnCreditProfitShared(0.0);
                        overviewVo.setFranchiseCode(franchiseCode);
                    }
                    // Update management fee on credit for this overview
                    // report.
                    overviewDao.updateManagementFeeOnCredit(addInfo, overviewVo);
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
