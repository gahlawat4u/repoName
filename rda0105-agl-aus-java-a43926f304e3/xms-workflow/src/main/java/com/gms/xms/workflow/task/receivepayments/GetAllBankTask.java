package com.gms.xms.workflow.task.receivepayments;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.BankDao;
import com.gms.xms.txndb.vo.BankVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetAllBankTask
 * <p>
 * Author DatTV Date Apr 9, 2015 11:43:52 AM
 */
public class GetAllBankTask implements Task {
    private static final Log log = LogFactory.getLog(GetAllBankTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        BankDao bankDao = new BankDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to gets list of banks
            List<BankVo> bankList = bankDao.getAll();

            // Puts result into the context
            context.put(Attributes.BANK_LIST_RESULT, GsonUtils.toGson(bankList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
