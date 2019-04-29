package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.BillingTypeDao;
import com.gms.xms.txndb.vo.webship.BillingTypeVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class GetBillingTypeListTask implements Task {
    private static final Log log = LogFactory.getLog(GetBillingTypeListTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        BillingTypeDao billingTypeDao = new BillingTypeDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            List<BillingTypeVo> billingTypeVo = billingTypeDao.getBillingTypeList();
            context.put(Attributes.BILLING_TYPE_LIST_RESULT, GsonUtils.toGson(billingTypeVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
