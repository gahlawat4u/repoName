package com.gms.xms.persistence.dao.customers;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.customer.SearchCustomerFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.CustomerAddressVo;

import java.util.List;

/**
 * Posted from Jul 5, 2016 3:54:44 PM
 * <p>
 * Author dattrinh
 */
public class SearchCustomerDao extends BaseDao<Object> {
    public SearchCustomerDao() {
        super();
    }

    public SearchCustomerDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CustomerAddressVo> selectCustomerByCode(SearchCustomerFilter filter) throws DaoException {
        return selectList(filter, "SearchCustomer.selectCustomerByCode");
    }
}