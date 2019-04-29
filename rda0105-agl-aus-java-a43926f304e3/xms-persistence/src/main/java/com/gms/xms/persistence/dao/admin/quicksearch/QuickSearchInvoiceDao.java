package com.gms.xms.persistence.dao.admin.quicksearch;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminQuickSearchFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchInvoiceVo;

import java.util.List;

public class QuickSearchInvoiceDao extends BaseDao<QuickSearchInvoiceVo> {
    public List<QuickSearchInvoiceVo> getInvoicesByInvoiceCode(AdminQuickSearchFilter filter) throws DaoException {
        return this.selectList(filter, "AdminQuickSearch.getInvoicesByInvoiceCode");
    }

    public long countInvoicesByInvoiceCode(AdminQuickSearchFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "AdminQuickSearch.countInvoicesByInvoiceCode");
    }
}
