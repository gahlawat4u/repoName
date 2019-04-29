package com.gms.xms.workflow.service.webship.quotejob;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.QuoteJobFilter;
import com.gms.xms.txndb.vo.webship.quotejob.QuoteJobVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IQuoteJobService
 * <p>
 * Author HungNT Date Jul 9, 2015
 */
public interface IQuoteJobService {
    public List<QuoteJobVo> getQuoteJobList(QuoteJobFilter quoteJobFilter) throws Exception;

    public Long getQuoteJobListCount(QuoteJobFilter quoteJobFilter) throws Exception;

    public QuoteJobVo getQuoteJobDetail(QuoteJobFilter quoteJobFilter) throws Exception;

    public void saveQuoteLog(Map<String, String> context, QuoteJobVo quoteJobVo) throws DaoException;

    /**
     * @param customerCode
     * @return
     * @throws DaoException
     */
    public String getPreviousQuoteNumber(Long customerCode) throws DaoException;
}
