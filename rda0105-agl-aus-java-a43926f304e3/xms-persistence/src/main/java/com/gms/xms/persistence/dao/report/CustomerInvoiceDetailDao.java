package com.gms.xms.persistence.dao.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerInvoiceDetailFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerInvoiceDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerInvoiceDetailDao.java
 * <p>
 * Author dattrinh 2:56:33 PM
 */
public class CustomerInvoiceDetailDao extends BaseDao<CustomerInvoiceDetailVo> {
    public CustomerInvoiceDetailDao() {
        super();
    }

    public CustomerInvoiceDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CustomerInvoiceDetailVo> getInvoiceDetailReport(CustomerInvoiceDetailFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerInvoiceDetail.getInvoiceDetailReport");
    }

    public CustomerInvoiceDetailVo sumInvoiceDetailReport(CustomerInvoiceDetailFilter filter) throws DaoException {
        return this.select(filter, "CustomerInvoiceDetail.sumInvoiceDetailReport");
    }

    public long checkInvoiceDetailReport(CustomerInvoiceDetailFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CustomerInvoiceDetail.checkInvoiceDetailReport");
    }

    public void prepareInvoiceDetailReport(Map<String, String> context, CustomerInvoiceDetailFilter filter) throws DaoException {
        this.insert(context, filter, "CustomerInvoiceDetail.prepareInvoiceDetailReport");
    }
}
