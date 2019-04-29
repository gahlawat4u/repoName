package com.gms.xms.workflow.task2.downloadbilling.dhl;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ServiceDao;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jun 2, 2016 3:57:40 PM
 * <p>
 * Author huynd
 */
public class PrepareFtpAccountInfoTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareFtpAccountInfoTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ServiceDao dao = new ServiceDao();
            ServiceVo service = dao.selectById(1);
            if (service == null) {
                throw new Exception("No Ftp account information!");
            }
            context.put(Attributes.SERVICE_VO, service);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
