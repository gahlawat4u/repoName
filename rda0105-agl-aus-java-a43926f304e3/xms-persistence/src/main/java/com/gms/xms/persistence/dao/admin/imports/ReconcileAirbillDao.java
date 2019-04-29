package com.gms.xms.persistence.dao.admin.imports;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.imports.reconcileairbill.ReconcileAirbillFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.imports.ReconcileAirbillVo;

import java.util.List;

/**
 * Posted from ReconcileAirbillDao
 * <p>
 * Author dattrinh Mar 15, 2016 9:36:54 AM
 */
public class ReconcileAirbillDao extends BaseDao<ReconcileAirbillVo> {
    public List<ReconcileAirbillVo> getReconcileAirbillByFilter(ReconcileAirbillFilter filter) throws DaoException {
        return this.selectList(filter, "ReconcileAirbill.getReconcileAirbillByFilter");
    }

    public ReconcileAirbillVo sumReconcileAirbillByFilter(ReconcileAirbillFilter filter) throws DaoException {
        return this.select(filter, "ReconcileAirbill.sumReconcileAirbillByFilter");
    }

    public long countReconcileAirbillByFilter(ReconcileAirbillFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "ReconcileAirbill.countReconcileAirbillByFilter");
    }
}