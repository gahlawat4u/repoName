package com.gms.xms.persistence.dao.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.StatementInvoiceFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementCustomerBillingAddressVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementCustomerVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementInvoiceVo;

import java.util.List;

/**
 * Posted from StatementDao
 * <p>
 * Author dattrinh Mar 16, 2016 11:38:24 AM
 */
public class StatementDao extends BaseDao<Object> {
    public List<StatementCustomerVo> getCustomerByFranchises(String franchiseList) throws DaoException {
        return this.selectList(franchiseList, "Statement.getCustomerByFranchises");
    }

    public StatementCustomerBillingAddressVo getBillingAddressByCustCode(String customerCode) throws DaoException {
        return (StatementCustomerBillingAddressVo) this.select(customerCode, "Statement.getBillingAddressByCustCode");
    }

    public List<StatementInvoiceVo> getInvoiceByCustCode(StatementInvoiceFilter filter) throws DaoException {
        return this.selectList(filter, "Statement.getInvoiceByCustCode");
    }

    public StatementInvoiceVo sumInvoiceByCustCode(StatementInvoiceFilter filter) throws DaoException {
        return (StatementInvoiceVo) this.select(filter, "Statement.sumInvoiceByCustCode");
    }

    public long countInvoiceByCustCode(StatementInvoiceFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Statement.countInvoiceByCustCode");
    }
}
