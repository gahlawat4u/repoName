package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GetUserByIdTask implements Task {
    private static final Log log = LogFactory.getLog(GetUserByIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        UserDao userDao = new UserDao();
        try {
            String userId = context.getString(Attributes.USER_ID);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get user
            UserVo user = userDao.getUserById(userId);

            // Puts result into the context
            context.put(Attributes.USER_INFO_RESULT, GsonUtils.toGson(user));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
