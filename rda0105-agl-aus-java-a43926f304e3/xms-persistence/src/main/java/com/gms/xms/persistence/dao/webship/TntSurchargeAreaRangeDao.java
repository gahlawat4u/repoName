package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.TntSurchargeAreaRangeFilter;
import com.gms.xms.txndb.vo.webship.TntSurchargeAreaRangeVo;

/**
 * Posted from TntSurchargeAreaRangeDao
 * <p>
 * Author HungNT Date Aug 21, 2015
 */
public class TntSurchargeAreaRangeDao extends BaseDao<TntSurchargeAreaRangeVo> {
    public TntSurchargeAreaRangeVo selectWARegionalSurcharge(TntSurchargeAreaRangeFilter filter) throws DaoException {
        return select(filter, "TntSurchargeAreaRange.selectWARegionalSurcharge");
    }
}
