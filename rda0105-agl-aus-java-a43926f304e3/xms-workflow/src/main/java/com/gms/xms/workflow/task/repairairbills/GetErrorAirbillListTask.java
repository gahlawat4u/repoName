package com.gms.xms.workflow.task.repairairbills;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from Jul 14, 2016 10:00:37 AM
 * <p>
 * Author dattrinh
 */
public class GetErrorAirbillListTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetErrorAirbillListTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get airbill number list.
            String airbillList = context.getString(Attributes.AIRBILL_NUMBER_LIST);
            // Get error airbill list.
            ShipmentBillingDao billingDao = new ShipmentBillingDao();
            List<ShipmentBillingVo> billingVos = billingDao.getErrorShipmentBillingByAWBList(airbillList);
            // Puts result into the context
            context.put(Attributes.ERROR_AIRBILL_LIST, billingVos);
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
