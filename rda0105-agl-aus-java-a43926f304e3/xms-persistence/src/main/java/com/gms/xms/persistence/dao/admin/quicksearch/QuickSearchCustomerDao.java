package com.gms.xms.persistence.dao.admin.quicksearch;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminQuickSearchFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchCustomerVo;

import java.util.List;

/**
 * Posted from Apr 27, 2016 11:01:36 AM
 * <p>
 * Author huynd
 */
public class QuickSearchCustomerDao extends BaseDao<QuickSearchCustomerVo> {
    public List<QuickSearchCustomerVo> getCustomersByCustomerCode(AdminQuickSearchFilter filter) throws DaoException {
        return this.selectList(filter, "AdminQuickSearch.getCustomersByCustomerCode");
    }

    public long countCustomersByCustomerCode(AdminQuickSearchFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "AdminQuickSearch.countCustomersByCustomerCode");
    }
}
