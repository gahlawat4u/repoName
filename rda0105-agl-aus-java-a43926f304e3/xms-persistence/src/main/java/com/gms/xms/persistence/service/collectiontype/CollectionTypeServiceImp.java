package com.gms.xms.persistence.service.collectiontype;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CollectionTypeDao;
import com.gms.xms.txndb.vo.webship.CollectionTypeVo;

import java.util.List;

/**
 * Posted from CollectionTypeServiceImp
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public class CollectionTypeServiceImp implements ICollectionTypeService {
    CollectionTypeDao collectionTypeDao = new CollectionTypeDao();

    @Override
    public List<CollectionTypeVo> getCollectionTypeList() throws DaoException {
        List<CollectionTypeVo> collectionTypeVos = collectionTypeDao.getCollectionTypeList();
        return collectionTypeVos;
    }
}
