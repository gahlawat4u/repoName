package com.gms.xms.workflow.task.franchisepayable.sc;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.sc.FranchisePayableSCDaoService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCCreditTotalVo;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCCreditVo;
import com.gms.xms.txndb.vo.franchisepayable.sc.NullFranchisePayableSCCreditTotalVo;
import com.gms.xms.txndb.vo.franchisepayable.sc.NullFranchisePayableSCCreditVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetFranchisePayableSCCreditTask
 * <p>
 * Author DatTV Oct 29, 2015
 */
public class GetFranchisePayableSCCreditTask implements Task {
    private static final Log log = LogFactory.getLog(GetFranchisePayableSCCreditTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayableSCDaoService fpbSCDao = new FranchisePayableSCDaoService();
        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get information for Franchise Payable
            // Carrier Credit Details.
            List<FranchisePayableSCCreditVo> result = fpbSCDao.getCarrierCreditDetails(filter);

            // Puts result into the context
            context.put(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_RESULT, GsonUtils.toGson(result));

            // Do DAO service to get Carrier Credit Details records count.
            Integer recordCount = fpbSCDao.getCarrierCreditDetailsRecordCount(filter);

            // Puts it into the context.
            context.put(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_RECORD_COUNT_RESULT, String.valueOf(recordCount));

            // Check if it's the final page then we must get summary record.
            Integer startRecord = filter.getStartRecord();
            Integer recordSize = filter.getRecordSize();
            if ((startRecord != null && recordSize != null && (recordCount - startRecord) <= recordSize) || (startRecord == null || recordSize == null)) {
                // Get summary record of taxable carrier credits.
                FranchisePayableSCCreditVo taxableRecord = fpbSCDao.getTaxableCarrierCreditDetailsTotal(filter);
                if (taxableRecord == null) {
                    taxableRecord = new NullFranchisePayableSCCreditVo();
                }

                // Get summary record of non-taxable carrier credits.
                FranchisePayableSCCreditVo nonTaxableRecord = fpbSCDao.getNonTaxableCarrierCreditDetailsTotal(filter);
                if (nonTaxableRecord == null) {
                    nonTaxableRecord = new NullFranchisePayableSCCreditVo();
                }

                // Get summary record.
                FranchisePayableSCCreditTotalVo totalResult = fpbSCDao.getCarrierCreditDetailsTotal(filter);
                if (totalResult == null) {
                    totalResult = new NullFranchisePayableSCCreditTotalVo();
                }

                // Puts it into the context.
                context.put(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_TAXABLE_TOTAL_RESULT, GsonUtils.toGson(taxableRecord));
                context.put(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_NON_TAXABLE_TOTAL_RESULT, GsonUtils.toGson(nonTaxableRecord));
                context.put(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_TOTAL_RESULT, GsonUtils.toGson(totalResult));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
