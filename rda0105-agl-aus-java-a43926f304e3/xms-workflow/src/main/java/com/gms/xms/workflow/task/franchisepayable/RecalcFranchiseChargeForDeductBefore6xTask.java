package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.FranPabDeductDao;
import com.gms.xms.txndb.vo.FranPabDeductFilter;
import com.gms.xms.txndb.vo.FranPabDeductVo;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

public class RecalcFranchiseChargeForDeductBefore6xTask implements Task {
    private static final Log log = LogFactory.getLog(RecalcFranchiseChargeForDeductBefore6xTask.class);
    private FranPabDeductDao pabDeductDao = new FranPabDeductDao();

    @Override
    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());

        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            if (!AppConstants.YES_FLAG.equalsIgnoreCase(context.getString(Attributes.HAS_CACHE_ID))) {
                // Gets report transaction id
                String rptTxnId = filter.getRptTxnId();

                // Recalculate franchise charge for franchise payable deduct
                // report.
                List<FranPabDeductVo> deductVos = pabDeductDao.getFranchiseChargeByRptTxnIdBefore6x(rptTxnId);

                for (FranPabDeductVo deductAdd : deductVos) {
                    FranPabDeductFilter pabDeductFilter = new FranPabDeductFilter();
                    pabDeductFilter.setRptTxnId(rptTxnId);
                    pabDeductFilter.setAirbillNumber(deductAdd.getAirbillNumber());

                    FranPabDeductVo deduct = pabDeductDao.selectByFilter(pabDeductFilter);
                    // Update deduct record
                    if (deduct != null) {
                        // Update franchise charge for franchise payable deduct
                        deduct.setFranchiseCharge(deduct.getFranchiseCharge().add(deductAdd.getFranchiseCharge()));
                        pabDeductDao.updateFranchiseCharge(addInfo, deduct);
                    } else {
                        pabDeductDao.insert(addInfo, deductAdd);
                    }
                }
            }
        } catch (Exception ex) {
            log.error(ex);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}
