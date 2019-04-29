package com.gms.xms.persistence.dao.webship.ship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.ship.UsedRateWebshipFilter;
import com.gms.xms.txndb.vo.webship.ship.UsedRateWebshipVo;

/**
 * @author tkvcl
 */
public class UsedRateWebshipDao extends BaseDao<UsedRateWebshipVo> {

    public UsedRateWebshipVo selectUsedRateWebship(UsedRateWebshipFilter filter) throws DaoException {
        return select(filter, "UsedRateWebship.selectUsedRateWebship");
    }
}