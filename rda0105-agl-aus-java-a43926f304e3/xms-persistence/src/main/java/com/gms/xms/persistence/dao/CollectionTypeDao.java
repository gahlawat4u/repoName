package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.CollectionTypeVo;

import java.util.List;

/**
 * Posted from CollectionTypeDaoService
 * <p>
 * Author DatTV Date Mar 27, 2015
 */
public class CollectionTypeDao extends BaseDao<CollectionTypeVo> {
    /**
     * Gets list of collection types
     *
     * @return List<{@link CollectionTypeVo}>
     * @throws DaoException
     */
    public List<CollectionTypeVo> getCollectionTypeList() throws DaoException {
        return selectList(new CollectionTypeVo(), "CollectionType.getCollectionTypeList");
    }
}
