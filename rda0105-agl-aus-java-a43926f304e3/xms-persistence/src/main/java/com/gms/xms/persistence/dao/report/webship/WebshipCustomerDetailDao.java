package com.gms.xms.persistence.dao.report.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.webship.WebshipCustomerDetailFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.webship.WebshipCustomerDetailVo;

import java.util.List;

/**
 * Posted from WebshipCustomerDetailDao
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class WebshipCustomerDetailDao extends BaseDao<WebshipCustomerDetailVo> {
    public WebshipCustomerDetailDao() {
        super();
    }

    public WebshipCustomerDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<WebshipCustomerDetailVo> getWebshipCustomerDetailReport(WebshipCustomerDetailFilter filter) throws DaoException {
        return this.selectList(filter, "WebshipCustomerDetail.getWebshipCustomerDetailReport");
    }

    public long countWebshipCustomerDetailReport(WebshipCustomerDetailFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "WebshipCustomerDetail.countWebshipCustomerDetailReport");
    }
}
