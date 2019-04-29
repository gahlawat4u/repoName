package com.gms.xms.persistence.dao.report.ranking;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.ranking.SalesRepRankingVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SalesRepRankingDao.java
 * <p>
 * Author dattrinh 10:47:36 AM
 */
public class SalesRepRankingDao extends BaseDao<SalesRepRankingVo> {
    public SalesRepRankingDao() {
        super();
    }

    public SalesRepRankingDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<SalesRepRankingVo> getSalesRepRankingReport(SalesRepRankingFilter filter) throws DaoException {
        return this.selectList(filter, "SalesRepRanking.getSalesRepRankingReport");
    }

    public long checkSalesRepRankingReport(SalesRepRankingFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "SalesRepRanking.checkSalesRepRankingReport");
    }

    public void prepareSalesRepRankingReport(Map<String, String> context, SalesRepRankingFilter filter) throws DaoException {
        this.insert(context, filter, "SalesRepRanking.prepareSalesRepRankingReport");
    }
}
