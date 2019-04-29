package com.gms.xms.workflow.task.franchisepayable.sc;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.sc.FranchisePayableSCDaoService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCTechFeeTotalVo;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCTechFeeVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetFranchisePayableSCTechFeeTask
 * <p>
 * Author DatTV Oct 30, 2015
 */
public class GetFranchisePayableSCTechFeeTask implements Task {
    private static final Log log = LogFactory.getLog(GetFranchisePayableSCTechFeeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayableSCDaoService fpbSCDao = new FranchisePayableSCDaoService();
        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get information for Franchise Payable: Tech Fee Detail
            List<FranchisePayableSCTechFeeVo> result = fpbSCDao.getTechFeeDetails(filter);

            // Puts result into the context
            context.put(Attributes.FRANCHISE_PAYABLE_TECH_FEE_DETAILS_RESULT, GsonUtils.toGson(result));

            // Do DAO service to get Tech Fee Details records count.
            Long recordCount = fpbSCDao.getTechFeeDetailsRecordCount(filter);

            // Puts it into the context.
            context.put(Attributes.FRANCHISE_PAYABLE_TECH_FEE_DETAILS_RECORD_COUNT_RESULT, String.valueOf(recordCount));

            // Check if it's the final page then we must get summary record.
            Integer startRecord = filter.getStartRecord();
            Integer recordSize = filter.getRecordSize();
            if ((startRecord != null && recordSize != null && (recordCount - startRecord) <= recordSize) || (startRecord == null || recordSize == null)) {
                // Get summary record.
                FranchisePayableSCTechFeeTotalVo totalResult = fpbSCDao.getTechFeeDetailsTotal(filter);

                // Puts it into the context.
                context.put(Attributes.FRANCHISE_PAYABLE_TECH_FEE_DETAILS_TOTAL_RESULT, GsonUtils.toGson(totalResult));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
