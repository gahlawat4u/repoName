package com.gms.xms.persistence.dao.importbilling;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.importbilling.CustomerAccountFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;

public class ImportBillingDao extends BaseDao<Object> {

    public ImportBillingDao() {
        super();
    }

    public ImportBillingDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public Long selectCustomerByAccount(CustomerAccountFilter filter) throws DaoException {
        return (Long) selectObject(filter, "ImportBilling.selectCustomerByAccount");
    }

    public String checkCustomerOrFranchise(String customerCode) throws DaoException {
        return (String) selectObject(customerCode, "ImportBilling.checkCustomerOrFranchise");
    }

}
