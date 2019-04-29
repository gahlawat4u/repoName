package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AirbillLabelFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.AirbillLabelVo;

import java.util.List;

/**
 * Posted from Aug 27, 2016 6:33:20 PM
 * <p>
 * Author dattrinh
 */
public class AirbillLabelDao extends BaseDao<Object> {
    public List<AirbillLabelVo> getAirbillLabels(AirbillLabelFilter filter) throws DaoException {
        return selectList(filter, "AirbillLabel.getAirbillLabels");
    }

    public long countAirbillLabels(AirbillLabelFilter filter) throws DaoException {
        return (long) selectObject(filter, "AirbillLabel.countAirbillLabels");
    }
}
