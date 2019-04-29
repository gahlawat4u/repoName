package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.txndb.vo.WebshipChangePassVo;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from ChangeWebshipPassTask
 * <p>
 * Author DatTV Date Apr 2, 2015
 */
public class ChangeWebshipPassTask implements Task {
    private static final Log log = LogFactory.getLog(ChangeWebshipPassTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        WebshipDao webshipDao = new WebshipDao();
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            WebshipChangePassVo webship = GsonUtils.fromGson(context.get(Attributes.WEBSHIP_CHANGE_PASSWORD_OBJECT), WebshipChangePassVo.class);
            WebshipVo webshipVo = new WebshipVo();
            webshipVo.setWebshipId(webship.getWebshipId());
            webshipVo.setPassword(webship.getNewPassword());
            webshipDao.changePassword(addInfo, webshipVo);
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }

        return true;
    }

}
