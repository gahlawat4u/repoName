package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.TollIpecKgRateFilter;
import com.gms.xms.txndb.vo.TollIpecKgRateVo;

/**
 * @author tkvcl
 */
public class TollIpecKgRateDao extends BaseDao<TollIpecKgRateVo> {

    public TollIpecKgRateVo selectByFilter(TollIpecKgRateFilter filter) throws DaoException {
        return select(filter, "TollIpecKgRate.selectByFilter");
    }

}
