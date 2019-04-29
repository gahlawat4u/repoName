package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CurrencyDao;
import com.gms.xms.txndb.vo.CurrencyVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetCurrencyListTask
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class GetCurrencyListTask implements Task {
    private static final Log log = LogFactory.getLog(GetCurrencyListTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CurrencyDao currencyDao = new CurrencyDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            List<CurrencyVo> currencyList = currencyDao.getCurrencyList();
            context.put(Attributes.CURRENCY_LIST_RESULT, GsonUtils.toGson(currencyList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}
