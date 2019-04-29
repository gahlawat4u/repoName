package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.AirbillPausingDeductVo;

import java.util.Map;

/**
 * Posted from AirbillPausingDeductDao
 * <p>
 * Author HungNT Date Apr 24, 2015
 */
public class AirbillPausingDeductDao extends BaseDao<AccessorialVo> {

    public AirbillPausingDeductDao() {
        super();
    }

    public AirbillPausingDeductDao(SqlSessionClient sessionClient) {
        super(sessionClient);
    }

    public AirbillPausingDeductVo selectByAirbillNumber(String airbillNumber) throws DaoException {
        return (AirbillPausingDeductVo) selectObject(airbillNumber, "AirbillPausingDeduct.selectByAirbillNumber");
    }

    public Integer updateByAirbillNumber(Map<String, String> context, AirbillPausingDeductVo airbillPausingDeductVo) throws DaoException {
        return update(context, airbillPausingDeductVo, "AirbillPausingDeduct.updateByAirbillNumber");
    }

    public Integer insert(Map<String, String> context, AirbillPausingDeductVo airbillPausingDeductVo) throws DaoException {
        return insert(context, airbillPausingDeductVo, "AirbillPausingDeduct.insert");
    }

    public void updatePausingDay(Map<String, String> context, AirbillAdjustmentVo adjustment) throws DaoException {
        this.update(context, adjustment, "AirbillPausingDeduct.updatePausingDay");
    }
}
