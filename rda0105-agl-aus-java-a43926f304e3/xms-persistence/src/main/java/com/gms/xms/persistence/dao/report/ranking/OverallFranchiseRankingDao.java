package com.gms.xms.persistence.dao.report.ranking;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.ranking.OverallFranchiseRankingFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.ranking.OverallFranchiseRankingVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from OverallFranchiseRankingDao.java
 * <p>
 * Author dattrinh 5:05:18 PM
 */
public class OverallFranchiseRankingDao extends BaseDao<OverallFranchiseRankingVo> {
    public OverallFranchiseRankingDao() {
        super();
    }

    public OverallFranchiseRankingDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<OverallFranchiseRankingVo> getOverallFranchiseRankingReport(OverallFranchiseRankingFilter filter) throws DaoException {
        return this.selectList(filter, "OverallFranchiseRanking.getOverallFranchiseRankingReport");
    }

    public long checkOverallFranchiseRankingReport(OverallFranchiseRankingFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "OverallFranchiseRanking.checkOverallFranchiseRankingReport");
    }

    public void prepareOverallFranchiseRankingReport(Map<String, String> context, OverallFranchiseRankingFilter filter) throws DaoException {
        this.insert(context, filter, "OverallFranchiseRanking.prepareOverallFranchiseRankingReport");
    }
}
