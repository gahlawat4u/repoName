package com.gms.xms.persistence.service.customer;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;

import java.util.Map;

/**
 * Posted from ICustomerCollectionService
 * <p>
 * Author DatTV Sep 28, 2015
 */
public interface ICustomerCollectionService {
    public CustomerCollectionVo selectByCustCode(String customerCode) throws DaoException;

    public void update(Map<String, String> context, CustomerCollectionVo collectionVo) throws DaoException;

    public void update(Map<String, String> context, CustomerCollectionVo collectionVo, SqlSessionClient sessionClient) throws DaoException;

    public CustomerCollectionVo selectCollectionByUser(CustomerCollectionVo customerVo) throws DaoException;
}
