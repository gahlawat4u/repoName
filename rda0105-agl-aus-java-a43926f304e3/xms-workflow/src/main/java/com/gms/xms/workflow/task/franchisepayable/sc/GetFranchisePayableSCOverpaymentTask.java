package com.gms.xms.workflow.task.franchisepayable.sc;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.sc.FranchisePayableSCDaoService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCOverpaymentVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetFranchisePayableSCOverpaymentTask
 * <p>
 * Author DatTV Oct 29, 2015
 */
public class GetFranchisePayableSCOverpaymentTask implements Task {
    private static final Log log = LogFactory.getLog(GetFranchisePayableSCOverpaymentTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayableSCDaoService fpbSCDao = new FranchisePayableSCDaoService();
        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get information for Franchise Payable
            // Over payment.
            List<FranchisePayableSCOverpaymentVo> result = fpbSCDao.getOverpayment(filter);

            // Puts result into the context
            context.put(Attributes.FRANCHISE_PAYABLE_OVERPAYMENT_RESULT, GsonUtils.toGson(result));

            // Do DAO service to get Over payment records count.
            Integer recordCount = fpbSCDao.getOverpaymentRecordCount(filter);

            // Puts it into the context.
            context.put(Attributes.FRANCHISE_PAYABLE_OVERPAYMENT_RECORD_COUNT_RESULT, String.valueOf(recordCount));

            // Check if it's the final page then we must get summary record.
            Integer startRecord = filter.getStartRecord();
            Integer recordSize = filter.getRecordSize();
            if ((startRecord != null && recordSize != null && (recordCount - startRecord) <= recordSize) || (startRecord == null || recordSize == null)) {
                // Get summary record.
                FranchisePayableSCOverpaymentVo totalResult = fpbSCDao.getOverpaymentTotal(filter);

                // Puts it into the context.
                context.put(Attributes.FRANCHISE_PAYABLE_OVERPAYMENT_TOTAL_RESULT, GsonUtils.toGson(totalResult));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
