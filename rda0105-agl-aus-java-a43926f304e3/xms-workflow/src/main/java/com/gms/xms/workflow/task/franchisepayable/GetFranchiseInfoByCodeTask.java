package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GetFranchiseInfoByCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetFranchiseListManagedByUserTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchiseDao franchiseDao = new FranchiseDao();
        String franchiseCode = context.getString(Attributes.FRANCHISE_CODE);
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get list of Franchise
            FranchiseInfoVo result = franchiseDao.getFranchiseInfoByCode(franchiseCode);

            // Puts result into the context
            context.put(Attributes.FRANCHISE_INFO_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }
}
