package com.gms.xms.persistence.dao.franchise;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.franchises.FranchiseDetailFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.account.franchises.FranchiseDetailVo;

import java.util.List;

/**
 * Posted from FranchiseDetailDao
 * <p>
 * Author DatTV Oct 22, 2015
 */
public class FranchiseDetailDao extends BaseDao<FranchiseDetailVo> {
    public List<FranchiseDetailVo> selectByFilter(FranchiseDetailFilter filter) throws DaoException {
        return this.selectList(filter, "FranchiseDetail.selectAll");
    }

    public long countByFilter(FranchiseDetailFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "FranchiseDetail.countAll");
    }
}
