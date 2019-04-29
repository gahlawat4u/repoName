package com.gms.xms.workflow.task.overpayment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.OverpaymentInfoDao;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoFilter;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from CountOverpaymentInfoByFilterTask
 * <p>
 * Author DatTV Date Apr 27, 2015 9:49:02 AM
 */
public class CountOverpaymentInfoByFilterTask implements Task {
    private static final Log log = LogFactory.getLog(CountOverpaymentInfoByFilterTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        OverpaymentInfoDao overpaymentInfoDao = new OverpaymentInfoDao();
        try {
            OverpaymentInfoFilter filter = GsonUtils.fromGson(context.get(Attributes.OVERPAYMENT_INFO_FILTER), OverpaymentInfoFilter.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get record count of over payment by the filter
            long recordCount = overpaymentInfoDao.countByFilter(filter);

            // Puts result into the context
            context.put(Attributes.OVERPAYMENT_INFO_RECORD_COUNT_RESULT, String.valueOf(recordCount));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
