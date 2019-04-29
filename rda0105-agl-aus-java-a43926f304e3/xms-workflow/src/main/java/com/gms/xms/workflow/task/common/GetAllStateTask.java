package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.StateDao;
import com.gms.xms.txndb.vo.StateVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetAllStateTask
 * <p>
 * Author HungNT Date May 14, 2015
 */
public class GetAllStateTask implements Task {
    private static final Log log = LogFactory.getLog(GetAllStateTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        StateDao stateDao = new StateDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            List<StateVo> stateVos = stateDao.selectAllState();
            context.put(Attributes.STATE_LIST_RESULT, GsonUtils.toGson(stateVos));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
