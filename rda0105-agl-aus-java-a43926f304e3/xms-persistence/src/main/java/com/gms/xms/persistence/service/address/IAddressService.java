package com.gms.xms.persistence.service.address;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.webship.AddressBookDetailFilter;
import com.gms.xms.txndb.vo.webship.AddressBookDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IAddressService
 * <p>
 * Author DatTV Date Jul 15, 2015 2:50:27 PM
 */
public interface IAddressService {
    public List<AddressBookDetailVo> selectByFilter(AddressBookDetailFilter filter) throws DaoException;

    public AddressBookDetailVo selectById(Long addressId) throws DaoException;

    public AddressVo getAddressById(Integer addressId) throws DaoException;

    public Integer update(Map<String, String> context, AddressVo addressVo) throws DaoException;
}
