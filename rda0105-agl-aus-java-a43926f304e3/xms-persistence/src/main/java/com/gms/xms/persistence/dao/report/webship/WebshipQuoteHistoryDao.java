package com.gms.xms.persistence.dao.report.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.webship.WebshipQuoteHistoryFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.webship.WebshipQuoteHistoryVo;

import java.util.List;

/**
 * Posted from WebshipQuoteHistoryDao.java
 * <p>
 * Author dattrinh 11:06:16 AM
 */
public class WebshipQuoteHistoryDao extends BaseDao<WebshipQuoteHistoryVo> {
    public WebshipQuoteHistoryDao() {
        super();
    }

    public WebshipQuoteHistoryDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<WebshipQuoteHistoryVo> getWebshipQuoteHistoryReport(WebshipQuoteHistoryFilter filter) throws DaoException {
        return this.selectList(filter, "WebshipQuoteHistory.getWebshipQuoteHistoryReport");
    }

    public long countWebshipQuoteHistoryReport(WebshipQuoteHistoryFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "WebshipQuoteHistory.countWebshipQuoteHistoryReport");
    }
}
