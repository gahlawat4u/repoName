package com.gms.xms.persistence.service.customer;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.customers.CustomerCollectionDao;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;

import java.util.Map;

/**
 * Posted from CustomerCollectionServiceImp
 * <p>
 * Author DatTV Sep 28, 2015
 */
public class CustomerCollectionServiceImp implements ICustomerCollectionService {

    @Override
    public CustomerCollectionVo selectByCustCode(String customerCode) throws DaoException {
        CustomerCollectionDao collectionDao = new CustomerCollectionDao();
        return collectionDao.selectByCustCode(customerCode);
    }

    @Override
    public void update(Map<String, String> context, CustomerCollectionVo collectionVo) throws DaoException {
        CustomerCollectionDao collectionDao = new CustomerCollectionDao();
        collectionDao.update(context, collectionVo);
    }

    @Override
    public void update(Map<String, String> context, CustomerCollectionVo collectionVo, SqlSessionClient sessionClient) throws DaoException {
        CustomerCollectionDao collectionDao = new CustomerCollectionDao(sessionClient);
        collectionDao.update(context, collectionVo);
    }

    @Override
    public CustomerCollectionVo selectCollectionByUser(CustomerCollectionVo customerVo) throws DaoException {
        CustomerCollectionDao collectionDao = new CustomerCollectionDao();
        return collectionDao.selectCollectionByUser(customerVo);
    }
}
