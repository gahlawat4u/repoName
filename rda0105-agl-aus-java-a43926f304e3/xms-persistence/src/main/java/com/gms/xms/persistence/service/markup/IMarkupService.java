package com.gms.xms.persistence.service.markup;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.customers.manage.MarkupFilter;
import com.gms.xms.txndb.vo.CustomerAccessorialVo;
import com.gms.xms.txndb.vo.account.customers.manage.MarkupVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IMarkupService
 * <p>
 * Author DatTV Oct 6, 2015
 */
public interface IMarkupService {
    public List<MarkupVo> selectByFilter(MarkupFilter filter) throws DaoException;

    public long countByFilter(MarkupFilter filter) throws DaoException;

    public void editMarkup(Map<String, String> context, CustomerAccessorialVo accessorialVo) throws DaoException;

    public List<MarkupVo> selectMarkupForCustomer(MarkupFilter filter) throws DaoException;

    public long selectMarkupForCustomerCount(MarkupFilter filter) throws DaoException;

    public MarkupVo selectDetailByIds(MarkupFilter filter) throws DaoException;

    public void updateCustomerProfile(Map<String, String> context, MarkupVo markupVo) throws DaoException;

    public void insertCustomerProfileAccessorial(Map<String, String> context, MarkupVo markupVo) throws DaoException;

    public void insertCustomerAccessorial(Map<String, String> context, CustomerAccessorialVo accessorialVo) throws DaoException;

    public void updateCustomerAccessorial(Map<String, String> context, CustomerAccessorialVo accessorialVo) throws DaoException;

}
