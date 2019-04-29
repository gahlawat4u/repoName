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
 * Author HoangPH Date May 22, 2015
 */
public class UnfreezeCreditNotesTask implements Task {
    private static final Log log = LogFactory.getLog(FreezeCreditNotesTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CreditNoteDaoService daoService = new CreditNoteDaoService();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            List<CreditNoteVo> creditNoteVos = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTE_LIST_RESULT), new TypeToken<List<CreditNoteVo>>() {
            }.getType());
            int i = 0;
            while (i < creditNoteVos.size()) {
                System.out.println(creditNoteVos.get(i));
                Long creditNoteId = creditNoteVos.get(i).getCreditNoteId();
                // Do DAO service to update creditnote and delet invoice paymen,
                // invoice payment detail
                daoService.doUnFreezeCreditNotes(addInfo, creditNoteId);
                i++;
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
