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

/**
 * Posted from GetPackageByIdTask
 * <p>
 * Author DatTV Date Apr 2, 2015
 */
public class GetPackageByIdTask implements Task {
    private static final Log log = LogFactory.getLog(GetPackageByIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        PackageDao packageDao = new PackageDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        int packageId = Integer.parseInt(context.getString(Attributes.PACKAGE_ID));
        try {
            PackageVo packageVo = packageDao.getPackageById(packageId);
            context.put(Attributes.PACKAGE_RESULT, GsonUtils.toGson(packageVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}