package com.gms.xms.workflow.task.common;

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
 * Posted from GetPackageListTask
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class GetPackageListTask implements Task {
    private static final Log log = LogFactory.getLog(GetPackageListTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        PackageDao packageDao = new PackageDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            List<PackageVo> packageList = packageDao.getPackageList();
            context.put(Attributes.PACKAGE_LIST_RESULT, GsonUtils.toGson(packageList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}
