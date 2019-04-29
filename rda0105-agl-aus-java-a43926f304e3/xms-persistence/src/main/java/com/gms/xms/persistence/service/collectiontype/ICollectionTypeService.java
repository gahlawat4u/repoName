package com.gms.xms.persistence.service.collectiontype;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.CollectionTypeVo;

import java.util.List;

/**
 * Posted from ICollectionTypeService
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public interface ICollectionTypeService {
    public List<CollectionTypeVo> getCollectionTypeList() throws DaoException;
}
