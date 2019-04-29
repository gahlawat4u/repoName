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

/**
 * Posted from GetCreditNoteListTask
 * <p>
 * Author HungNT Date May 21, 2015
 */
public class GetCreditNotesByCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetCreditNotesByCodeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CreditNoteDao creditNoteDao = new CreditNoteDao();
        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        creditNoteFilter = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTE_FILTER), CreditNoteFilter.class);
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            CreditNoteVo creditNoteVo = creditNoteDao.selectCreditNotesByCode(creditNoteFilter);
            Long creditNoteId = creditNoteVo.getCreditNoteId();
            context.put(Attributes.CREDIT_NOTE_ID_RESULT, GsonUtils.toGson(creditNoteId));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }

        return true;
    }

}
