package com.gms.xms.workflow.task2.admin.account.franchise.managefranchise;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.admin.account.managefranchise.BaseRateDaoService;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * File Name: ProcessSaveBaseRateTask.java <br/>
 * Author: TANDT <br/>
 * Create Date: 14-03-2016 <br/>
 * Project Name: xms-workflow <br/>
 * package Name: com.gms.xms.workflow.task2.admin.account.franchise.managefranchise <br/>
 */
public class ProcessSaveBaseRateTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessSaveBaseRateTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            List<CustomerBaseRateVo> customerBaseRates = context.get(Attributes.CUSTOMER_BASE_RATE);
            BaseRateDaoService baseRateDaoService = new BaseRateDaoService();
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            baseRateDaoService.saveFranchiseBaseRate(addInfo, customerBaseRates);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }

        return true;
    }
}
