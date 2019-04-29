package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.editairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetListAccessorialTask
 * <p>
 * Author TANDT
 */
public class GetListShipmentTypeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetListShipmentTypeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentTypeDao dao = new ShipmentTypeDao();
            List<ShipmentTypeVo> listShipmentTypes = dao.selectByServiceIdRename(context.getInt(Attributes.SERVICE_ID));
            context.put(Attributes.SHIPMENT_TYPE_LIST_RESULT, listShipmentTypes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
