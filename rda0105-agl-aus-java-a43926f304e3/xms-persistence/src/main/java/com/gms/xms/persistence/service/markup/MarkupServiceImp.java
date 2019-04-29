package com.gms.xms.persistence.service.markup;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.customers.manage.MarkupFilter;
import com.gms.xms.filter.customer.CustomerAccessorialFilter;
import com.gms.xms.persistence.dao.customers.CustomerAccessorialDao;
import com.gms.xms.persistence.dao.markup.MarkupDao;
import com.gms.xms.txndb.vo.CustomerAccessorialVo;
import com.gms.xms.txndb.vo.account.customers.manage.MarkupVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from MarkupServiceImp
 * <p>
 * Author DatTV Oct 6, 2015
 */
public class MarkupServiceImp implements IMarkupService {

    @Override
    public List<MarkupVo> selectByFilter(MarkupFilter filter) throws DaoException {
        MarkupDao markupDao = new MarkupDao();
        return markupDao.selectByFilter(filter);
    }

    @Override
    public long countByFilter(MarkupFilter filter) throws DaoException {
        MarkupDao markupDao = new MarkupDao();
        return markupDao.countByFilter(filter);
    }

    @Override
    public void editMarkup(Map<String, String> context, CustomerAccessorialVo accessorialVo) throws DaoException {
        CustomerAccessorialDao accessorialDao = new CustomerAccessorialDao();
        CustomerAccessorialFilter filter = new CustomerAccessorialFilter();
        filter.setCustomerCode(accessorialVo.getCustomerCode());
        filter.setAccessorialId(accessorialVo.getAccessorialid());
        CustomerAccessorialVo checkAccessorialVo = accessorialDao.select(filter);
        if (checkAccessorialVo == null) {
            accessorialDao.insert(context, accessorialVo);
        } else {
            // Delete if amount=0
            if (accessorialVo.getAmount().doubleValue() == 0.00) {
                accessorialDao.delete(context, filter);
            } else {
                accessorialDao.update(context, accessorialVo);
            }
        }
    }

    @Override
    public List<MarkupVo> selectMarkupForCustomer(MarkupFilter filter) throws DaoException {
        MarkupDao markupDao = new MarkupDao();
        return markupDao.selectMarkupForCustomer(filter);
    }

    @Override
    public long selectMarkupForCustomerCount(MarkupFilter filter) throws DaoException {
        MarkupDao markupDao = new MarkupDao();
        return markupDao.selectMarkupForCustomerCount(filter);
    }

    @Override
    public MarkupVo selectDetailByIds(MarkupFilter filter) throws DaoException {
        MarkupDao markupDao = new MarkupDao();
        return markupDao.selectDetailByIds(filter);
    }

    @Override
    public void updateCustomerProfile(Map<String, String> context, MarkupVo markupVo) throws DaoException {
        MarkupDao markupDao = new MarkupDao();
        markupDao.updateCustomerProfile(context, markupVo);
    }

    @Override
    public void insertCustomerProfileAccessorial(Map<String, String> context, MarkupVo markupVo) throws DaoException {
        MarkupDao markupDao = new MarkupDao();
        markupDao.insertCustomerProfileAccessorial(context, markupVo);

    }

    @Override
    public void insertCustomerAccessorial(Map<String, String> context, CustomerAccessorialVo accessorialVo) throws DaoException {
        CustomerAccessorialDao dao = new CustomerAccessorialDao();
        dao.insert(context, accessorialVo);
    }

    @Override
    public void updateCustomerAccessorial(Map<String, String> context, CustomerAccessorialVo accessorialVo) throws DaoException {
        CustomerAccessorialDao dao = new CustomerAccessorialDao();
        dao.update(context, accessorialVo);

    }
}
