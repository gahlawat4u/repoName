package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayableTaskDaoService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class FreezeFranchisePayableReportTask implements Task {
    private static final Log log = LogFactory.getLog(FreezeFranchisePayableReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayableTaskDaoService fpbTaskDao = new FranchisePayableTaskDaoService();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());

        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            // Do DAO service to freeze franchise payable report
            fpbTaskDao.freezeReportByDateRange(addInfo, filter);
        } catch (Exception ex) {
            log.error(ex);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }
}
