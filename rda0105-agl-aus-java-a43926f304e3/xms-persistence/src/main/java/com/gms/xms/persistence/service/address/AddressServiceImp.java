package com.gms.xms.persistence.service.address;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.AddressBookDetailDao;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.webship.AddressBookDetailFilter;
import com.gms.xms.txndb.vo.webship.AddressBookDetailVo;

import java.util.List;
import java.util.Map;

public class AddressServiceImp implements IAddressService {
    private AddressBookDetailDao addressDetailDao = new AddressBookDetailDao();

    @Override
    public List<AddressBookDetailVo> selectByFilter(AddressBookDetailFilter filter) throws DaoException {
        return addressDetailDao.selectByFilter(filter);
    }

    @Override
    public AddressBookDetailVo selectById(Long addressId) throws DaoException {
        return addressDetailDao.selectById(addressId);
    }

    @Override
    public AddressVo getAddressById(Integer addressId) throws DaoException {
        AddressDao addressDao = new AddressDao();
        return addressDao.selectById(addressId);
    }

    @Override
    public Integer update(Map<String, String> context, AddressVo addressVo) throws DaoException {
        AddressDao addressDao = new AddressDao();
        return addressDao.update(context, addressVo);
    }
}
