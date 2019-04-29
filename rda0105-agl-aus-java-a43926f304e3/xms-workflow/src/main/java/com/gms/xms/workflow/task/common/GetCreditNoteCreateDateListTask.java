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
 * Posted from GetCreditNoteCreateDateListTask
 * <p>
 * Author HungNT Date May 20, 2015
 */
public class GetCreditNoteCreateDateListTask implements Task {
    private static final Log log = LogFactory.getLog(GetAllStateTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CreditNoteDao creditNoteDao = new CreditNoteDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            CreditNoteFilter creditNoteFilter = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTE_FILTER), CreditNoteFilter.class);
            List<CreditNoteVo> creditNoteVos = creditNoteDao.selectCreateDateList(creditNoteFilter);

            context.put(Attributes.CREDIT_NOTE_RESULT, GsonUtils.toGson(creditNoteVos));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
