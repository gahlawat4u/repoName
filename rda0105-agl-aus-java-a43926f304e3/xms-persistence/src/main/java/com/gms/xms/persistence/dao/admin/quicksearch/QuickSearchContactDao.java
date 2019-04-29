package com.gms.xms.persistence.dao.admin.quicksearch;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminQuickSearchFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.account.contact.ManageContactVo;

import java.util.List;

/**
 * Posted from Apr 27, 2016 11:34:00 AM
 * <p>
 * Author huynd
 */
public class QuickSearchContactDao extends BaseDao<ManageContactVo> {
    public List<ManageContactVo> getContactsByContact(AdminQuickSearchFilter filter) throws DaoException {
        return this.selectList(filter, "AdminQuickSearch.getContactsByContact");
    }

    public long countContactsByContact(AdminQuickSearchFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "AdminQuickSearch.countContactsByContact");
    }
}
