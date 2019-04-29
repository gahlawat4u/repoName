package com.gms.xms.persistence.dao.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerCreditNoteDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerCreditNoteDetailDao.java
 * <p>
 * Author dattrinh 2:58:38 PM
 */
public class CustomerCreditNoteDetailDao extends BaseDao<CustomerCreditNoteDetailVo> {
    public CustomerCreditNoteDetailDao() {
        super();
    }

    public CustomerCreditNoteDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CustomerCreditNoteDetailVo> getCreditNoteDetailReport(CustomerCreditNoteDetailFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerCreditNoteDetail.getCreditNoteDetailReport");
    }

    public CustomerCreditNoteDetailVo sumCreditNoteDetailReport(CustomerCreditNoteDetailFilter filter) throws DaoException {
        return this.select(filter, "CustomerCreditNoteDetail.sumCreditNoteDetailReport");
    }

    public long checkCreditNoteDetailReport(CustomerCreditNoteDetailFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CustomerCreditNoteDetail.checkCreditNoteDetailReport");
    }

    public void prepareCreditNoteDetailReport(Map<String, String> context, CustomerCreditNoteDetailFilter filter) throws DaoException {
        this.insert(context, filter, "CustomerCreditNoteDetail.prepareCreditNoteDetailReport");
    }
}
