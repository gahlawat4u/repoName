package com.gms.xms.persistence.dao.customers;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;

import java.util.Map;

/**
 * Posted from CustomerCollectionDao
 * <p>
 * Author DatTV Sep 28, 2015
 */
public class CustomerCollectionDao extends BaseDao<CustomerCollectionVo> {
    public CustomerCollectionDao() {
        super();
    }

    public CustomerCollectionDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public CustomerCollectionVo selectByCustCode(String customerCode) throws DaoException {
        return this.select(customerCode, "CustomerCollection.selectByCustCode");
    }

    public CustomerCollectionVo selectByFranCode(String franchiseCode) throws DaoException {
        return this.select(franchiseCode, "CustomerCollection.selectByFranCode");
    }

    public CustomerCollectionVo selectByProfileId(String profileId) throws DaoException {
        return this.select(profileId, "CustomerCollection.selectByProfileId");
    }

    public void update(Map<String, String> context, CustomerCollectionVo collectionVo) throws DaoException {
        this.update(context, collectionVo, "CustomerCollection.update");
    }

    public void insert(Map<String, String> context, CustomerCollectionVo collectionVo) throws DaoException {
        this.insert(context, collectionVo, "CustomerCollection.insert");
    }

    public CustomerCollectionVo selectCollectionByUser(CustomerCollectionVo customerVo) throws DaoException {
        return select(customerVo, "CustomerCollection.selectCollectionByUser");
    }
}