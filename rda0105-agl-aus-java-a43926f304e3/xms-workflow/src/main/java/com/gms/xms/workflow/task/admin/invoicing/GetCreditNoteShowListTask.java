package com.gms.xms.workflow.task.admin.invoicing;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.invoicing.CreditNoteShowListFilter;
import com.gms.xms.persistence.dao.CreditNoteShowListDaoService;
import com.gms.xms.txndb.vo.CreditNoteShowListVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetCreditNoteShowListTask
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class GetCreditNoteShowListTask implements Task {
    private static final Log log = LogFactory.getLog(GetCreditNoteShowListTask.class);

    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        CreditNoteShowListDaoService creditNoteDao = new CreditNoteShowListDaoService();
        try {
            String creditNoteCode = context.get(Attributes.CREDIT_NOTES_CODE).toString();
            CreditNoteShowListFilter filter = new CreditNoteShowListFilter();
            filter.setCreditCode(creditNoteCode);
            List<CreditNoteShowListVo> listCreditNoteShowList = creditNoteDao.selectCreditNoteShowList(filter);
            context.put(Attributes.LIST_ADJ_CREDIT_NOTE, GsonUtils.toGson(listCreditNoteShowList));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
