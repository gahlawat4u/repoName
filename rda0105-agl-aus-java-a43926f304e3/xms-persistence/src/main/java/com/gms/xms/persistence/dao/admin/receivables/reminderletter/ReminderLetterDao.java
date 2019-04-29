package com.gms.xms.persistence.dao.admin.receivables.reminderletter;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.receivables.reminderletter.ReminderLetterFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.receivables.reminderletter.ReminderInvoiceDetailVo;
import com.gms.xms.txndb.vo.admin.receivables.reminderletter.ReminderLetterVo;

import java.util.List;

/**
 * Posted from Mar 31, 2016 2:59:11 PM
 * <p>
 * Author dattrinh
 */
public class ReminderLetterDao extends BaseDao<Object> {
    public List<ReminderLetterVo> getEmailInvoices(ReminderLetterFilter filter) throws DaoException {
        return this.selectList(filter, "ReminderLetter.getEmailInvoices");
    }

    public List<ReminderLetterVo> getPrintInvoices(ReminderLetterFilter filter) throws DaoException {
        return this.selectList(filter, "ReminderLetter.getPrintInvoices");
    }

    public ReminderInvoiceDetailVo getInvoiceDetailById(Long invoiceId) throws DaoException {
        return (ReminderInvoiceDetailVo) this.select(invoiceId, "ReminderLetter.getInvoiceDetailById");
    }
}
