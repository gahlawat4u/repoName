package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ShipmentAddressVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AddressDaoService
 * <p>
 * Author DatTV Date Apr 3, 2015
 */
public class AddressDao extends BaseDao<Object> {
    public AddressDao() {
        super();
    }

    public AddressDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public AddressVo selectById(Integer addressId) throws DaoException {
        return (AddressVo) select(addressId, "Address.selectById");
    }

    public List<AddressVo> selectAll() throws DaoException {
        return selectList(null, "Address.selectAll");
    }

    public ShipmentAddressVo getShipmentAddressById(Integer addressId) throws DaoException {
        return (ShipmentAddressVo) select(addressId, "Address.getShipmentAddressById");
    }

    public Integer insert(Map<String, String> context, AddressVo addressVo) throws DaoException {
        return insert(context, addressVo, "Address.insert");
    }

    public Integer update(Map<String, String> context, AddressVo addressVo) throws DaoException {
        return update(context, addressVo, "Address.update");
    }
}