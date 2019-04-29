package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.TollIpecTotalRateFilter;
import com.gms.xms.txndb.vo.TollIpecTotalRateVo;


/**
 * @author tkvcl
 */
public class TollIpecTotalRateDao extends BaseDao<TollIpecTotalRateVo> {


    public TollIpecTotalRateVo selectByFilter(TollIpecTotalRateFilter filter) throws DaoException {
        return select(filter, "TollIpecTotalRate.selectByFilter");
    }
}