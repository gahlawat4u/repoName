package com.gms.xms.workflow.service.customeraging;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingVo;

import java.util.List;

/**
 * Posted from ICustomerAgingService
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface ICustomerAgingService {
    public List<CustomerAgingVo> selectByFilter(CustomerAgingFilter filter) throws Exception;

    public long countByFilter(CustomerAgingFilter filter) throws Exception;

    public CustomerAgingVo sumByFilter(CustomerAgingFilter filter) throws Exception;

    public CustomerAgingVo selectByCustomerCode(String customerCode) throws DaoException;
}