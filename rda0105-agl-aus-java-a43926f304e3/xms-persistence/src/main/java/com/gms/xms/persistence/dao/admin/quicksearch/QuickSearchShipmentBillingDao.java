package com.gms.xms.persistence.dao.admin.quicksearch;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminQuickSearchFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchShipmentBillingVo;

import java.util.List;

public class QuickSearchShipmentBillingDao extends BaseDao<QuickSearchShipmentBillingVo> {
    public List<QuickSearchShipmentBillingVo> getShipmentBillingsByAirbill(AdminQuickSearchFilter filter) throws DaoException {
        return this.selectList(filter, "AdminQuickSearch.getShipmentBillingsByAirbill");
    }

    public long countShipmentBillingsByAirbill(AdminQuickSearchFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "AdminQuickSearch.countShipmentBillingsByAirbill");
    }
}
