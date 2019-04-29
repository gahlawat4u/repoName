package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayableRptTxnIdVo;

import java.util.Map;

/**
 * Posted from FranchisePayableRptTxnIdDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableRptTxnIdDao extends BaseDao<FranchisePayableRptTxnIdVo> {

    public FranchisePayableRptTxnIdDao() {
        super();
    }

    public FranchisePayableRptTxnIdDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void saveRptTxnId(Map<String, String> context, FranchisePayableRptTxnIdVo rptTxnIdVo) throws DaoException {
        insert(context, rptTxnIdVo, "FranchisePayableRptTxnId.insert");
    }

    public int getRptTxnIdCount(FranchisePayableFilter filter) throws DaoException {
        return (int) selectObject(filter, "FranchisePayableRptTxnId.selectCountById");
    }

    public void deleteRptTxnId(Map<String, String> context, String rptTxnId) throws DaoException {
        delete(context, rptTxnId, "FranchisePayableRptTxnId.deleteById");
    }
}