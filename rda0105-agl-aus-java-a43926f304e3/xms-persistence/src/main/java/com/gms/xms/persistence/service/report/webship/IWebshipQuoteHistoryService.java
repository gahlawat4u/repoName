package com.gms.xms.persistence.service.report.webship;

import com.gms.xms.filter.reports.webship.WebshipQuoteHistoryFilter;
import com.gms.xms.txndb.vo.reports.webship.WebshipQuoteHistoryVo;

import java.util.List;

/**
 * Posted from IWebshipQuoteHistoryService.java
 * <p>
 * Author dattrinh 11:29:40 AM
 */
public interface IWebshipQuoteHistoryService {
    public List<WebshipQuoteHistoryVo> getWebshipQuoteHistoryReport(WebshipQuoteHistoryFilter filter) throws Exception;

    public long countWebshipQuoteHistoryReport(WebshipQuoteHistoryFilter filter) throws Exception;
}
