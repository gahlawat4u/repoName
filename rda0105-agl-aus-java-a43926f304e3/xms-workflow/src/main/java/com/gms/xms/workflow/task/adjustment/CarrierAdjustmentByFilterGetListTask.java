package com.gms.xms.workflow.task.adjustment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CarrierAdjustmentDao;
import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentFilter;
import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from CountCarrierAdjustmentByFilterTask
 * <p>
 * Author TanDT Date May 26, 2015
 */
public class CarrierAdjustmentByFilterGetListTask implements Task {
    private static final Log log = LogFactory.getLog(CarrierAdjustmentByFilterGetListTask.class);
    private CarrierAdjustmentDao dao = new CarrierAdjustmentDao();

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            CarrierAdjustmentFilter filter = GsonUtils.fromGson(context.get(Attributes.CARRIER_ADJUSTMENT_FILTER), CarrierAdjustmentFilter.class);
            List<CarrierAdjustmentVo> listCarrierAdjustment = new ArrayList<CarrierAdjustmentVo>();
            listCarrierAdjustment = dao.selectCarrierAdjustment(filter);
            context.put(Attributes.CARRIER_ADJUSTMENT_LIST, GsonUtils.toGson(listCarrierAdjustment));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
