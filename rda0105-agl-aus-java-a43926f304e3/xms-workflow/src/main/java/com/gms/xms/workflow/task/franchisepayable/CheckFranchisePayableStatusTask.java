package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayablePeriodDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayablePeriodVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CheckFranchisePayableStatusTask implements Task {
    private static final Log log = LogFactory.getLog(CheckFranchisePayableStatusTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        log.info("determine franchise payable/receivable report status is frozen or not...");
        FranchisePayablePeriodDao fpbPeriodDao = new FranchisePayablePeriodDao();
        try {
            // Get the filter from the context.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

            // Check franchise payable status.
            FranchisePayablePeriodVo fpbPeriodVo = fpbPeriodDao.getFranchisePayablePeriodByDateRange(filter);

            if (fpbPeriodVo == null) {
                context.put(Attributes.FRANCHISE_PAYABLE_IS_FROZEN, AppConstants.NO_FLAG);
            } else {
                String rptTxnId = fpbPeriodVo.getRptTxnId();
                if (fpbPeriodVo.getFranchisePayableStatus() == 0) {
                    context.put(Attributes.FRANCHISE_PAYABLE_IS_FROZEN, AppConstants.NO_FLAG);
                } else {
                    context.put(Attributes.FRANCHISE_PAYABLE_IS_FROZEN, AppConstants.YES_FLAG);
                    if (StringUtils.isNotBlank(rptTxnId)) {
                        // Put RptTxnId into the filter from context and set
                        // frozen status.
                        filter.setRptTxnId(rptTxnId);
                        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));

                        // Put frozen status into the context.
                        context.put(Attributes.FRANCHISE_PAYABLE_IS_XMS2, AppConstants.YES_FLAG);
                    } else {
                        context.put(Attributes.FRANCHISE_PAYABLE_IS_XMS2, AppConstants.NO_FLAG);
                    }
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
