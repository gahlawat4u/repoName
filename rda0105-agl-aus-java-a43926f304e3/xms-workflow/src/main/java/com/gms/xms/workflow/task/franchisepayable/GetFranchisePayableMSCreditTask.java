package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayableMSDaoService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayableMSCreditVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class GetFranchisePayableMSCreditTask implements Task {
    private static final Log log = LogFactory.getLog(GetFranchisePayableMSCreditTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayableMSDaoService fpbMSDao = new FranchisePayableMSDaoService();
        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get information for Franchise Payable
            // Carrier Credit Details.
            List<FranchisePayableMSCreditVo> result = fpbMSDao.getCarrierCreditDetails(filter);
            for (FranchisePayableMSCreditVo franchisePayableMSCreditVo : result) {
                franchisePayableMSCreditVo.setCustomerName(StringEscapeUtils.escapeXml(franchisePayableMSCreditVo.getCustomerName()));
            }

            // Puts result into the context
            context.put(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_RESULT, GsonUtils.toGson(result));

            // Do DAO service to get Carrier Credit Details records count.
            Integer recordCount = fpbMSDao.getCarrierCreditDetailsRecordCount(filter);

            // Puts it into the context.
            context.put(Attributes.FRANCHISE_PAYABLE_CARRIER_CREDIT_DETAILS_RECORD_COUNT_RESULT, String.valueOf(recordCount));

            // Check if it's the final page then we must get summary record.
            Integer startRecord = filter.getStartRecord();
            Integer recordSize = filter.getRecordSize();
            if ((startRecord != null && recordSize != null && (recordCount - startRecord) <= recordSize) || (startRecord == null || recordSize == null)) {
                // Get summary record.
                FranchisePayableMSCreditVo totalResult = fpbMSDao.getCarrierCreditDetailsTotal(filter);

                // Puts it into the context.
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
