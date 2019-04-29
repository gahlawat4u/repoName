package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.ContentDetailVo;

import java.util.List;

/**
 * Posted from ContentDetailDao
 * <p>
 * Author HungNT Date Apr 25, 2015
 */
public class ContentDetailDao extends BaseDao<ContentDetailVo> {

    /**
     * Get all content detail
     *
     * @return the list of {@link ContentDetailVo}
     * @throws DaoException
     */
    public List<ContentDetailVo> selectAllContentDetail() throws DaoException {
        return selectList(new ContentDetailVo(), "ContentDetail.selectAllContentDetail");
    }
}
