package com.gms.xms.persistence.dao.contact;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.contact.ManageContactFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.account.contact.ManageContactVo;

import java.util.List;

public class ManageContactDao extends BaseDao<Object> {
    public List<ManageContactVo> getManageContactByFilter(ManageContactFilter filter) throws DaoException {
        return this.selectList(filter, "ManageContact.getManageContactByFilter");
    }

    public long countManageContactByFilter(ManageContactFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "ManageContact.countManageContactByFilter");
    }

    public List<Long> getProspectByLogin(UserVo userVo) throws DaoException {
        return this.selectList(userVo, "ManageContact.getProspectByLogin");
    }
}
