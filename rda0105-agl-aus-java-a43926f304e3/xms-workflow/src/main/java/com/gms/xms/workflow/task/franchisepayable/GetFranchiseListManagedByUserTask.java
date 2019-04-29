package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class GetFranchiseListManagedByUserTask implements Task {
    private static final Log log = LogFactory.getLog(GetFranchiseListManagedByUserTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchiseDao franchiseDao = new FranchiseDao();
        UserDao userDao = new UserDao();
        try {
            String userId = context.getString(Attributes.USER_ID);
            UserVo user = userDao.getUserById(userId);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get list of Franchise
            List<FranchiseInfoVo> result = franchiseDao.getFranchiseListManagedByUser(user);

            // Puts result into the context
            context.put(Attributes.FRANCHISE_LIST_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }
}
