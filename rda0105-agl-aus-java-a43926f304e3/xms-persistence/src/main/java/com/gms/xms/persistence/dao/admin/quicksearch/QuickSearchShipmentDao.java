package com.gms.xms.persistence.dao.admin.quicksearch;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminQuickSearchFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchShipmentVo;

import java.util.List;

public class QuickSearchShipmentDao extends BaseDao<Object> {
    public List<QuickSearchShipmentVo> getShipmentsByAirbill(AdminQuickSearchFilter filter) throws DaoException {
        return this.selectList(filter, "AdminQuickSearch.getShipmentsByAirbill");
    }

    public long countShipmentsByAirbill(AdminQuickSearchFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "AdminQuickSearch.countShipmentsByAirbill");
    }

    public Long getSaleRepIdOfAirbill(String airbillNumber) throws DaoException {
        return (Long) this.selectObject(airbillNumber, "AdminQuickSearch.getSaleRepIdOfAirbill");
    }
}
