package com.gms.xms.workflow.task.overpayment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.OverpaymentDao;
import com.gms.xms.txndb.vo.OverpaymentVo;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoFilter;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetOverpaymentVoByFilterTask
 * <p>
 * Author DatTV Date Apr 27, 2015 4:11:22 PM
 */
public class GetOverpaymentVoByFilterTask implements Task {
    private static final Log log = LogFactory.getLog(GetOverpaymentVoByFilterTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        OverpaymentDao overpaymentDao = new OverpaymentDao();
        try {
            OverpaymentInfoFilter filter = GsonUtils.fromGson(context.get(Attributes.OVERPAYMENT_INFO_FILTER), OverpaymentInfoFilter.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get over payment by the filter
            List<OverpaymentVo> overpaymentList = overpaymentDao.selectByCustCodeAndSource(filter);

            // Puts result into the context
            context.put(Attributes.OVERPAYMENT_LIST_RESULT, GsonUtils.toGson(overpaymentList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
