package com.gms.xms.workflow.task.admin.invoicing;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CreditNoteDaoService;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from GetCreditNoteListTask
 * <p>
 * Author HoangPH Date May 21, 2015
 */
public class FreezeCreditNotesTask implements Task {
    private static final Log log = LogFactory.getLog(FreezeCreditNotesTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            List<CreditNoteVo> creditNoteVos = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTE_LIST_RESULT), new TypeToken<List<CreditNoteVo>>() {
            }.getType());

            for (CreditNoteVo creditNoteVo : creditNoteVos) {
                Long id = creditNoteVo.getCreditNoteId();
                // Do DAO service to update airbill adjustment
                CreditNoteDaoService daoService = new CreditNoteDaoService();
                daoService.doFreezeCreditNotes(addInfo, id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;

    }

}
