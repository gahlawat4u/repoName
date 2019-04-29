package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.QuoteJobFilter;
import com.gms.xms.txndb.vo.WebshipQuoteLogVo;
import com.gms.xms.txndb.vo.webship.quotejob.QuoteJobVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from WebshipQuoteLogDao
 * <p>
 * Author TanDT Date May 12, 2015
 */
public class WebshipQuoteLogDao extends BaseDao<WebshipQuoteLogVo> {
    public WebshipQuoteLogDao() {
        super();
    }

    public WebshipQuoteLogDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * @return
     * @throws DaoException
     */

    public Integer update(Map<String, String> context, WebshipQuoteLogVo webshipQuoteLogVo) throws DaoException {
        return update(context, webshipQuoteLogVo, "WebshipQuoteLog.update");
    }

    public WebshipQuoteLogVo selWebshipQuoteLogByCustomer(Long customerCode) throws DaoException {
        return select(customerCode, "WebshipQuoteLog_SelectWebshipQuoteLogByCustomer");
    }

    public Integer insert(Map<String, String> context, WebshipQuoteLogVo webshipQuoteLogVo) throws DaoException {
        return insert(context, webshipQuoteLogVo, "WebshipQuoteLog.insert");
    }

    /**
     * Get quote job list by quote number
     *
     * @param filter
     * @return
     * @throws DaoException
     */
    public List<QuoteJobVo> selectQuoteJobList(QuoteJobFilter filter) throws DaoException {
        return selectList(filter, "WebshipQuoteLog.selectQuoteJobList");
    }

    /**
     * Get quote job count by quote number
     *
     * @param filter
     * @return
     * @throws DaoException
     */
    public Long selectCountQuoteJobList(QuoteJobFilter filter) throws DaoException {
        return (Long) selectObject(filter, "WebshipQuoteLog.selectCountQuoteJobList");
    }

    /**
     * Get quote job's detail by quoteId
     *
     * @param {@link Long} customerCode
     * @return {@link QuoteJobVo} detail
     * @throws DaoException
     */
    public QuoteJobVo selectQuoteJobDetailById(Long quoteId) throws DaoException {
        return (QuoteJobVo) select(quoteId, "WebshipQuoteLog.selectQuoteJobDetailById");
    }

    public String selectPreviousQuoteNumber(Long customerCode) throws DaoException {
        return (String) selectObject(customerCode, "WebshipQuoteLog.selectPreviousQuoteNumber");
    }
}
