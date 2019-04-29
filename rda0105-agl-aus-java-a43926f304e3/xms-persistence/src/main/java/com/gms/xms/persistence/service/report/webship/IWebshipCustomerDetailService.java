package com.gms.xms.persistence.service.report.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.webship.WebshipCustomerDetailFilter;
import com.gms.xms.txndb.vo.reports.webship.WebshipCustomerDetailVo;

import java.util.List;

/**
 * Posted from IWebshipCustomerDetailService
 * <p>
 * Author DatTV Dec 11, 2015
 */
public interface IWebshipCustomerDetailService {
    public List<WebshipCustomerDetailVo> getWebshipCustomerDetailReport(WebshipCustomerDetailFilter filter) throws DaoException;

    public long countWebshipCustomerDetailReport(WebshipCustomerDetailFilter filter) throws DaoException;
}
