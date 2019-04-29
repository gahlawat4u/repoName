package com.gms.xms.workflow.task.admin.invoicing;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CreditNoteDao;
import com.gms.xms.txndb.vo.CreditNoteFilter;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetListCreateDateCreditNoteByCustomerTask
 * <p>
 * Author TanDT Date May 21, 2015
 */
public class GetListCreateDateCreditNoteByCustomerTask implements Task {
    private static final Log log = LogFactory.getLog(GetListCreateDateCreditNoteByCustomerTask.class);

    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        CreditNoteDao creditNoteDao = new CreditNoteDao();
        try {
            List<CustomerVo> listCustomerVos = GsonUtils.fromGson(context.get(Attributes.LIST_CUSTOMER_CODE_BY_LOGIN), new TypeToken<List<CustomerVo>>() {
            }.getType());
            CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
            creditNoteFilter.setListCustomerVos(listCustomerVos);
            List<CreditNoteVo> listCreditNoteVos = creditNoteDao.selectCreateDateListByCustomerCode(creditNoteFilter);
            context.put(Attributes.LIST_CREDIT_NOTE_BY_CUSTOMER_CODE, GsonUtils.toGson(listCreditNoteVos));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
