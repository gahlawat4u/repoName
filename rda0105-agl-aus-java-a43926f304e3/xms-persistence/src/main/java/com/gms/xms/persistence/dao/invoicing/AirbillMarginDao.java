package com.gms.xms.persistence.dao.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.AirbillMarginFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.invoicing.AirbillMarginVo;

import java.util.List;

/**
 * Posted from AirbillMarginDao
 * <p>
 * Author dattrinh Mar 15, 2016 3:37:17 PM
 */
public class AirbillMarginDao extends BaseDao<AirbillMarginVo> {
    public List<AirbillMarginVo> getAirbillMarginByFilter(AirbillMarginFilter filter) throws DaoException {
        return this.selectList(filter, "AirbillMargin.getAirbillMarginByFilter");
    }

    public long countAirbillMarginByFilter(AirbillMarginFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "AirbillMargin.countAirbillMarginByFilter");
    }

    public AirbillMarginVo sumAirbillMarginByFilter(AirbillMarginFilter filter) throws DaoException {
        return this.select(filter, "AirbillMargin.sumAirbillMarginByFilter");
    }
}
