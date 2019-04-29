package com.gms.xms.persistence.dao.period;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;

import java.util.Map;

/**
 * Posted from Sep 19, 2016 11:58:13 AM
 * <p>
 * Author dattrinh
 */
public class PeriodDao extends BaseDao<Object> {
    public void fillPeriod(Map<String, String> context) throws DaoException {
        insert(context, null, "Period.fillPeriod");
    }
}
