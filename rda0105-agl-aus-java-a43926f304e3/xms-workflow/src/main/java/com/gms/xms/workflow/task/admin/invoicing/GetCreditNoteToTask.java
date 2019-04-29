package com.gms.xms.workflow.task.admin.invoicing;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CreditNoteDao;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetCreditNoteToTask
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class GetCreditNoteToTask implements Task {
    private static final Log log = LogFactory.getLog(GetCreditNoteToTask.class);

    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        CreditNoteDao creditNoteDao = new CreditNoteDao();
        try {
            Long creditNoteId = Long.parseLong(context.get(Attributes.CREDIT_NOTES_ID));
            List<CreditNoteVo> listCreditNoteVos = creditNoteDao.selectCreditNoteTo(creditNoteId);
            context.put(Attributes.LIST_CREDIT_NOTES_TO, GsonUtils.toGson(listCreditNoteVos));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
