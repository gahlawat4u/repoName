package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.PackageDao;
import com.gms.xms.txndb.vo.PackageFilter;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetPackageByShipmentTypeIdAndPackageIdTask
 * <p>
 * Author HungNT Date Apr 21, 2015
 */
public class GetPackageByShipmentTypeIdAndPackageIdTask implements Task {
    private static final Log log = LogFactory.getLog(GetPackageByShipmentTypeIdAndPackageIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        PackageDao packageDao = new PackageDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            PackageFilter packageFilter = GsonUtils.fromGson(context.get(Attributes.PACKAGE_FILTER), PackageFilter.class);
            PackageVo packageVo = packageDao.selectPackageByShipmentTypeIdAndPackageId(packageFilter);
            context.put(Attributes.PACKAGE_RESULT, GsonUtils.toGson(packageVo));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
