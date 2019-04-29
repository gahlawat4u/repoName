package com.gms.xms.persistence.dao.customers;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.customer.CustomerAccessorialFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.CustomerAccessorialVo;

import java.util.Map;

/**
 * Posted from CustomerAccessorialDao
 * <p>
 * Author DatTV Oct 7, 2015
 */
public class CustomerAccessorialDao extends BaseDao<CustomerAccessorialVo> {
    public CustomerAccessorialDao() {
        super();
    }

    public CustomerAccessorialDao(SqlSessionClient sessionClient) {
        super(sessionClient);
    }

    public CustomerAccessorialVo select(CustomerAccessorialFilter filter) throws DaoException {
        return this.select(filter, "CustomerAccessorial.select");
    }

    public void insert(Map<String, String> context, CustomerAccessorialVo accessorialVo) throws DaoException {
        this.insert(context, accessorialVo, "CustomerAccessorial.insert");
    }

    public void update(Map<String, String> context, CustomerAccessorialVo accessorialVo) throws DaoException {
        this.update(context, accessorialVo, "CustomerAccessorial.update");
    }

    public void delete(Map<String, String> context, CustomerAccessorialFilter filter) throws DaoException {
        this.delete(context, filter, "CustomerAccessorial.delete");
    }
}
