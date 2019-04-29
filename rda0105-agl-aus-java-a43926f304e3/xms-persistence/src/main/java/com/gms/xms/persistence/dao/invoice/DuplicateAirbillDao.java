package com.gms.xms.persistence.dao.invoice;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.DuplicateAirbillFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.invoicing.DuplicateAirbillVo;

import java.util.List;
import java.util.Map;

/**
 * File Name: DuplicateAirbillDao.java <br/>
 * Author: TANDT <br/>
 * Create Date: 02-12-2015 <br/>
 * Project Name: xms-persistence <br/>
 * package Name: com.gms.xms.persistence.dao.invoice <br/>
 * Class: DuplicateAirbillDao
 */
public class DuplicateAirbillDao extends BaseDao<DuplicateAirbillVo> {
    public DuplicateAirbillDao() {
        super();
    }

    public DuplicateAirbillDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<DuplicateAirbillVo> getDuplicateAirbillByFilter(DuplicateAirbillFilter filter) throws DaoException {
        return selectList(filter, "DuplicateAirbill.getDuplicateAirbillByFilter");
    }

    public long countDuplicateAirbillByFilter(DuplicateAirbillFilter filter) throws DaoException {
        return (long) selectObject(filter, "DuplicateAirbill.countDuplicateAirbillByFilter");
    }

    public void saveDuplicateAirbill(Map<String, String> context, DuplicateAirbillVo duplicateAirbillVo) throws DaoException {
        insert(context, duplicateAirbillVo, "DuplicateAirbill.saveDuplicateAirbill");
    }
}
