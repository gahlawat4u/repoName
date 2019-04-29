package com.gms.xms.workflow.service.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.txndb.vo.AddressVo;

import java.util.Map;

public class HistoryAddressServiceImp implements IHistoryAddressService {

    @Override
    public Integer insertAddress(Map<String, String> context, AddressVo addressVo) throws DaoException {
        AddressDao dao = new AddressDao();
        return dao.insert(context, addressVo);
    }

    @Override
    public Integer updateAddress(Map<String, String> context, AddressVo addressVo) throws DaoException {
        AddressDao dao = new AddressDao();
        return dao.update(context, addressVo);
    }

    @Override
    public AddressVo selectById(Integer addressId) throws DaoException {
        AddressDao dao = new AddressDao();
        return dao.selectById(addressId);
    }

}
