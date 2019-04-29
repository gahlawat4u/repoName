package com.gms.xms.persistence.dao.admin.account.managefranchise;

import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerBaseRateDetailDao;
import com.gms.xms.txndb.vo.CustomerBaseRateDetailVo;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * File Name: BaseRateDaoService.java <br/>
 * Author: TANDT <br/>
 * Create Date: 14-03-2016 <br/>
 * Project Name: xms-persistence <br/>
 * package Name: com.gms.xms.persistence.dao.admin.account.managefranchise <br/>
 */
public class BaseRateDaoService {

    private static final Log logger = LogFactory.getLog(BaseRateDaoService.class);

    public void saveFranchiseBaseRate(Map<String, String> context, List<CustomerBaseRateVo> customerBaseRates) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao(sessionClient);
        CustomerBaseRateDetailDao customerBaseRateDetailDao = new CustomerBaseRateDetailDao(sessionClient);

        try {
            for (CustomerBaseRateVo customerBaseRate : customerBaseRates) {
                customerBaseRateDao.delete(context, customerBaseRate.getCustomerBaseRateId());
                customerBaseRateDetailDao.delete(context, customerBaseRate.getCustomerBaseRateId());
                customerBaseRateDao.insert(context, customerBaseRate);
                if (!customerBaseRate.getZoneCheck()) {
                    for (CustomerBaseRateDetailVo customerBaseRateDetail : customerBaseRate.getCustomerBaseRateDetails()) {
                        customerBaseRateDetailDao.insert(context, customerBaseRateDetail);
                    }
                }
            }
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Log database action error
            logger.error(e);
            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }
    }
}
