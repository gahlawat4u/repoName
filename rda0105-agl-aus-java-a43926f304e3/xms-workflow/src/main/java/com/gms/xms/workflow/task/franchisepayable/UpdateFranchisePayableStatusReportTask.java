package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayablePeriodDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayablePeriodVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class UpdateFranchisePayableStatusReportTask implements Task {
    private static final Log log = LogFactory.getLog(UpdateFranchisePayableStatusReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayablePeriodDao fpbPeriodDao = new FranchisePayablePeriodDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());

        try {
            // Get the filter from the context.
            FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

            // Get franchise payable report status.
            FranchisePayablePeriodVo fpbPeriodVo = fpbPeriodDao.getFranchisePayablePeriodByDateRange(filter);

            if (fpbPeriodVo != null) {
                fpbPeriodDao.updateFranchisePayablePeriod(addInfo, fpbPeriodVo);
            }
        } catch (Exception ex) {
            log.error(ex);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}
