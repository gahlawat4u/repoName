package com.gms.xms.persistence.dao.markup;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.customers.manage.MarkupFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.account.customers.manage.MarkupVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from MarkupDao
 * <p>
 * Author DatTV Oct 6, 2015
 */
public class MarkupDao extends BaseDao<MarkupVo> {
    public List<MarkupVo> selectByFilter(MarkupFilter filter) throws DaoException {
        return this.selectList(filter, "Markup.selectByFilter");
    }

    public long countByFilter(MarkupFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Markup.countByFilter");
    }

    public List<MarkupVo> selectMarkupForCustomer(MarkupFilter filter) throws DaoException {
        return this.selectList(filter, "Markup.selectMarkupForCustomer");
    }

    public long selectMarkupForCustomerCount(MarkupFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Markup.selectMarkupForCustomerCount");
    }

    public MarkupVo selectDetailByIds(MarkupFilter filter) throws DaoException {
        return this.select(filter, "Markup.selectDetailByIds");
    }

    public void updateCustomerProfile(Map<String, String> context, MarkupVo markupVo) throws DaoException {
        this.update(context, markupVo, "Markup.updateCustomerProfile");
    }

    public void insertCustomerProfileAccessorial(Map<String, String> context, MarkupVo markupVo) throws DaoException {
        this.insert(context, markupVo, "Markup.insertCustomerProfileAccessorial");
    }

}
