package com.gms.xms.workflow.task.franchisepayable.sc;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.sc.FranchisePayableSCTaskDaoService;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from ReconcileFranchiseTaxAmountSCTask
 * <p>
 * Author DatTV Oct 29, 2015
 */
public class ReconcileFranchiseTaxAmountSCTask implements Task {
    private static final Log log = LogFactory.getLog(ReconcileFranchiseTaxAmountSCTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayableSCTaskDaoService fpbTaskDao = new FranchisePayableSCTaskDaoService();
        if (!AppConstants.YES_FLAG.equalsIgnoreCase(context.getString(Attributes.HAS_CACHE_ID))) {
            log.info("Cache id is not exited. Going to reconcile franchise tax amount");
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());

            // Do DAO service to reconcile franchise tax amount.
            fpbTaskDao.reconcileFranchiseTaxAmount(addInfo);
        } else {
            log.info("Cache id is exited. continue process...");
        }
        return true;
    }

}
