package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayableMSDaoService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayableInvoiceExportVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GetFranchisePayableInvExportDataTask implements Task {
    private static final Log log = LogFactory.getLog(GetFranchisePayableInvExportDataTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayableMSDaoService fpbMSDao = new FranchisePayableMSDaoService();
        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get summary information for franchise payable
            // overview.
            FranchisePayableInvoiceExportVo msInvExportVo = fpbMSDao.getInvoiceExportData(filter);

            // Puts result into the context
            context.put(Attributes.FRANCHISE_PAYABLE_INV_EXPORT_DATA_RESULT, GsonUtils.toGson(msInvExportVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
