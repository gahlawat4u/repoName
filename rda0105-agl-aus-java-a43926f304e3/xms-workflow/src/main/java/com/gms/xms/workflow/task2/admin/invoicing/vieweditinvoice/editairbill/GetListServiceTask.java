package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.editairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ServiceDao;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetBillingInfoBaseChargeTask
 * <p>
 * Author TANDT
 */
public class GetListServiceTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetListServiceTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ServiceDao dao = new ServiceDao();
            List<ServiceVo> listService = dao.getIntegratedServices();
            context.put(Attributes.SERVICE_LIST_RESULT, listService);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
