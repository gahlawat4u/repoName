package com.gms.xms.persistence.dao.admin.quicksearch;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminQuickSearchFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchShipmentVo;

import java.util.List;

public class QuickSearchReferenceDao extends BaseDao<QuickSearchShipmentVo> {
    public List<QuickSearchShipmentVo> getShipmentsByReference(AdminQuickSearchFilter filter) throws DaoException {
        return this.selectList(filter, "AdminQuickSearch.getShipmentsByReference");
    }

    public long countShipmentsByReference(AdminQuickSearchFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "AdminQuickSearch.countShipmentsByReference");
    }
}
