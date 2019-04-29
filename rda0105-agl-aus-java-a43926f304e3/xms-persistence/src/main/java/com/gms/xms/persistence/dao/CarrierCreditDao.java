package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.CarrierCreditVo;

import java.util.Map;

/**
 * Posted from AddressDaoService
 * <p>
 * Author DatTV Date Apr 3, 2015
 */
public class CarrierCreditDao extends BaseDao<CarrierCreditVo> {
    public void insert(Map<String, String> context, CarrierCreditVo carrierCreditVo) throws DaoException {
        insert(context, carrierCreditVo, "CarrierCredit.insert");
    }
}
