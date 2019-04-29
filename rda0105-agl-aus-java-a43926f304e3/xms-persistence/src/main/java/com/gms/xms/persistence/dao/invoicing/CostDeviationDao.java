package com.gms.xms.persistence.dao.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.CostDeviationFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.invoicing.CostDeviationVo;

import java.util.List;

/**
 * Posted from CostDeviationDao
 * <p>
 * Author dattrinh Mar 14, 2016 11:02:32 AM
 */
public class CostDeviationDao extends BaseDao<CostDeviationVo> {
    public List<CostDeviationVo> getCostDeviationByFilter(CostDeviationFilter filter) throws DaoException {
        return this.selectList(filter, "CostDeviation.getCostDeviationByFilter");
    }

    public long countCostDeviationByFilter(CostDeviationFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CostDeviation.countCostDeviationByFilter");
    }

    public CostDeviationVo sumCostDeviationByFilter(CostDeviationFilter filter) throws DaoException {
        return this.select(filter, "CostDeviation.sumCostDeviationByFilter");
    }
}
