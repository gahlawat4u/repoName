package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.ups.UpsAreaSurchargeFilter;
import com.gms.xms.txndb.vo.ups.UpsAreaSurchargeVo;

/**
 * Created by thinhdd
 * Date 3/29/2017.
 */
public class UpsAreaSurchargeDao extends BaseDao<UpsAreaSurchargeVo> {
    public UpsAreaSurchargeVo selectSurchargeArea(UpsAreaSurchargeFilter upsAreaSurchargeFilter) throws DaoException {
        return select(upsAreaSurchargeFilter, "UpsAreaSurcharge.selectSurchargeArea");
    }
}
