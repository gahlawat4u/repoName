package com.gms.xms.workflow.task.admin.invoicing;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CreditNoteDaoService;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from GetCreditNoteListTask
 * <p>
 * Author HoangPH Date May 21, 2015
 */
public class UnfreezeCreditNotesByCodeTask implements Task {
    private static final Log log = LogFactory.getLog(FreezeCreditNotesTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            Long creditNoteId = Long.parseLong(context.get(Attributes.CREDIT_NOTE_ID_RESULT));
            CreditNoteDaoService daoService = new CreditNoteDaoService();
            daoService.doUnFreezeCreditNotes(addInfo, creditNoteId);
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;

    }

}
