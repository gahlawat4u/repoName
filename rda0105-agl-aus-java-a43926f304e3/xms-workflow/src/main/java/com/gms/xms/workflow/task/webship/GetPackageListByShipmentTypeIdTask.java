package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.PackageDao;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetPackageListByShipmentTypeIdTask
 * <p>
 * Author DatTV Date Mar 30, 2015
 */
public class GetPackageListByShipmentTypeIdTask implements Task {
    private static final Log log = LogFactory.getLog(GetPackageListByShipmentTypeIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        PackageDao packageDao = new PackageDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        int shipmentTypeId = Integer.parseInt(context.getString(Attributes.SHIPMENT_TYPE_ID));
        try {
            List<PackageVo> packageVoList = packageDao.getPackageListByShipmentTypeId(shipmentTypeId);
            context.put(Attributes.PACKAGE_LIST_RESULT, GsonUtils.toGson(packageVoList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }

        return true;
    }

}
