package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CreditNoteDao;
import com.gms.xms.txndb.vo.CreditNoteFilter;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetCreditNoteListTask
 * <p>
 * Author HungNT Date May 21, 2015
 */
public class GetCreditNotesListTask implements Task {
    private static final Log log = LogFactory.getLog(GetCreditNotesListTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CreditNoteDao creditNoteDao = new CreditNoteDao();
        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        creditNoteFilter = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTE_FILTER), CreditNoteFilter.class);
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            List<CreditNoteVo> creditNoteVos = creditNoteDao.selectListCreditNotesByFilter(creditNoteFilter);
            context.put(Attributes.CREDIT_NOTE_LIST_RESULT, GsonUtils.toGson(creditNoteVos));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }

        return true;
    }

}
