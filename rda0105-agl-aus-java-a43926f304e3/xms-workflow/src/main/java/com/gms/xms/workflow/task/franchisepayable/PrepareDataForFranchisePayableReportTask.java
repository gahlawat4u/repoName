package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.AppConstants;
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
import java.util.UUID;

public class PrepareDataForFranchisePayableReportTask implements Task {
    private static final Log log = LogFactory.getLog(PrepareDataForFranchisePayableReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {

        FranchisePayableTaskDaoService fpbTaskDao = new FranchisePayableTaskDaoService();

        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());

        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            if (!AppConstants.YES_FLAG.equalsIgnoreCase(context.getString(Attributes.HAS_CACHE_ID))) {
                // Create new report transaction id
                String newRptTxnId = UUID.randomUUID().toString();
                filter.setRptTxnId(newRptTxnId);

                // Put filter back into the context
                context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));

                // Do DAO service to prepare data for franchise payable
                // report.
                fpbTaskDao.prepareDataForReportFromLive(addInfo, filter);
            }
        } catch (Exception ex) {
            log.error(ex);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}
