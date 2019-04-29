package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.FranPabDeductFilter;
import com.gms.xms.txndb.vo.FranPabDeductVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from FranPabDeductDao
 * <p>
 * Author DatTV Date Apr 22, 2015 4:10:32 PM
 */
public class FranPabDeductDao extends BaseDao<FranPabDeductVo> {
    public FranPabDeductVo selectByFilter(FranPabDeductFilter filter) throws DaoException {
        return select(filter, "FranPabDeduct.selectByFilter");
    }

    public List<FranPabDeductVo> selectByRptTxnId(String rptTxnId) throws DaoException {
        return selectList(rptTxnId, "FranPabDeduct.selectByRptTxnId");
    }

    public void insert(Map<String, String> context, FranPabDeductVo deductVo) throws DaoException {
        insert(context, deductVo, "FranPabDeduct.insert");
    }

    public void updateFranchiseCharge(Map<String, String> context, FranPabDeductVo deductVo) throws DaoException {
        update(context, deductVo, "FranPabDeduct.updateFranchiseCharge");
    }

    public List<FranPabDeductVo> getFranchiseChargeByRptTxnId(String rptTxnId) throws DaoException {
        return selectList(rptTxnId, "FranPabDeduct.getFranchiseChargeByRptTxnId");
    }

    public List<FranPabDeductVo> getFranchiseChargeByRptTxnIdBefore6x(String rptTxnId) throws DaoException {
        return selectList(rptTxnId, "FranPabDeduct.getFranchiseChargeByRptTxnIdBefore6x");
    }

    public List<FranPabDeductVo> getFranchiseChargeByRptTxnIdAfter6x(String rptTxnId) throws DaoException {
        return selectList(rptTxnId, "FranPabDeduct.getFranchiseChargeByRptTxnIdAfter6x");
    }
}
