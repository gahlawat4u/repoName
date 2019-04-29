/**
 *
 */
package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.parameter.RateSheetParam;
import com.gms.xms.txndb.vo.RateSheetColumnVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from RateSheetColumnDao
 *
 * @author HungNT - @since Oct 1, 2015
 */
public class RateSheetColumnDao extends BaseDao<RateSheetColumnVo> {
    public RateSheetColumnDao() {
        super();
    }

    public RateSheetColumnDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<RateSheetColumnVo> selectBySheetIdForUps(Long sheetId) throws DaoException {
        return selectList(sheetId, "RateSheetColumn.selectBySheetIdForUps");
    }

    public List<RateSheetColumnVo> selectBySheetId(Long sheetId) throws DaoException {
        return selectList(sheetId, "RateSheetColumn.selectBySheetId");
    }
    
    
    
    public void deleteColumnsBySheetId(Map<String, String> context, Long sheetId) throws DaoException {
        delete(context, sheetId, "RateSheetColumn.deleteColumnsBySheetId");
    }

    public RateSheetColumnVo getRateSheetColumn(RateSheetColumnVo filter) throws DaoException {
        return select(filter, "RateSheetColumn.getRateSheetColumn");
    }

    public void insertRateSheetColumn(Map<String, String> context, RateSheetColumnVo rateSheetColumnVo) throws DaoException {
        insert(context, rateSheetColumnVo, "RateSheetColumn.insertRateSheetColumn");
    }

    public void updateRateSheetColumns(Map<String, String> context, RateSheetParam rateSheetParam) throws DaoException {
        update(context, rateSheetParam, "RateSheetColumn.updateRateSheetColumns");
    }
}
