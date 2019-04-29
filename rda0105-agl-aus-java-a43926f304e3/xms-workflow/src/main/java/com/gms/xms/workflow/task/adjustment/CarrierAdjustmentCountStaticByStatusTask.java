package com.gms.xms.workflow.task.adjustment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CarrierAdjustmentDao;
import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentCountStaticVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from CarrierAdjustmentCountStaticByStatusTask
 * <p>
 * Author TanDT Date Jun 2, 2015
 */
public class CarrierAdjustmentCountStaticByStatusTask implements Task {
    private static final Log log = LogFactory.getLog(CarrierAdjustmentCountStaticByStatusTask.class);
    private CarrierAdjustmentDao dao = new CarrierAdjustmentDao();

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            CarrierAdjustmentCountStaticVo countStaticVo = dao.selectCarrierAdjustmentCountStaticVo();
            context.put(Attributes.CARRIER_ADJUSTMENT_RESPONSE, GsonUtils.toGson(countStaticVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
