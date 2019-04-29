package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayablePeriodDao;
import com.gms.xms.txndb.vo.FranchisePayablePeriodVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class GetFrozenPeriodListTask implements Task {
    private static final Log log = LogFactory.getLog(GetFrozenPeriodListTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayablePeriodDao fpbPeriodDao = new FranchisePayablePeriodDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get list of frozen Franchise Payable Report
            List<FranchisePayablePeriodVo> fpbPeriodList = fpbPeriodDao.getFrozenFranchisePayableReport();

            // Puts result into the context
            context.put(Attributes.PERIOD_LIST_RESULT, GsonUtils.toGson(fpbPeriodList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
