package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetShipmentTypeListTask
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class GetShipmentTypeListTask implements Task {
    private static final Log log = LogFactory.getLog(GetShipmentTypeListTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            List<ShipmentTypeVo> shipmentTypeList = shipmentTypeDao.selectAll();
            context.put(Attributes.SHIPMENT_TYPE_LIST_RESULT, GsonUtils.toGson(shipmentTypeList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}
