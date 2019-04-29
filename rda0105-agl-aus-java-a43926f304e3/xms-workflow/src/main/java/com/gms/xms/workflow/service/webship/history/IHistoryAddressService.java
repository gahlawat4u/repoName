package com.gms.xms.workflow.service.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.AddressVo;

import java.util.Map;

public interface IHistoryAddressService {
    public Integer insertAddress(Map<String, String> context, AddressVo addressVo) throws DaoException;

    public Integer updateAddress(Map<String, String> context, AddressVo addressVo) throws DaoException;

    public AddressVo selectById(Integer addressId) throws DaoException;
}
