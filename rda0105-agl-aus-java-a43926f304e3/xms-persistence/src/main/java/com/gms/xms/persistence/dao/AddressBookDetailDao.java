package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.AddressBookDetailFilter;
import com.gms.xms.txndb.vo.webship.AddressBookDetailVo;

import java.util.List;

/**
 * Posted from AddressDetailDao
 * <p>
 * Author DatTV Date Jul 15, 2015 2:26:29 PM
 */
public class AddressBookDetailDao extends BaseDao<AddressBookDetailVo> {
    public List<AddressBookDetailVo> selectByFilter(AddressBookDetailFilter filter) throws DaoException {
        return selectList(filter, "AddressBookDetail.selectByFilter");
    }

    public AddressBookDetailVo selectById(Long addressId) throws DaoException {
        return select(addressId, "AddressBookDetail.selectById");
    }
}
