package com.gms.xms.persistence.service.report.webship;

import com.gms.xms.filter.reports.webship.WebshipQuoteHistoryFilter;
import com.gms.xms.persistence.dao.report.webship.WebshipQuoteHistoryDao;
import com.gms.xms.txndb.vo.reports.webship.WebshipQuoteHistoryVo;

import java.util.List;

/**
 * Posted from WebshipQuoteHistoryServiceImp.java
 * <p>
 * Author dattrinh 11:31:35 AM
 */
public class WebshipQuoteHistoryServiceImp implements IWebshipQuoteHistoryService {
    private WebshipQuoteHistoryDao dao = new WebshipQuoteHistoryDao();

    @Override
    public List<WebshipQuoteHistoryVo> getWebshipQuoteHistoryReport(WebshipQuoteHistoryFilter filter) throws Exception {
        return dao.getWebshipQuoteHistoryReport(filter);
    }

    @Override
    public long countWebshipQuoteHistoryReport(WebshipQuoteHistoryFilter filter) throws Exception {
        return dao.countWebshipQuoteHistoryReport(filter);
    }
}
