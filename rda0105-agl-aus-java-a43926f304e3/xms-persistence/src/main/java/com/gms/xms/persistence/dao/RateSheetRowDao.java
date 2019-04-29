package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.parameter.RateSheetParam;
import com.gms.xms.txndb.vo.RateSheetFilter;
import com.gms.xms.txndb.vo.RateSheetRowVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from RateSheetRowDao
 * <p>
 * Author HungNT Date May 19, 2015
 */
public class RateSheetRowDao extends BaseDao<RateSheetRowVo> {
    public RateSheetRowDao() {
        super();
    }

    public RateSheetRowDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * Get
     *
     * @param sheetId
     * @return
     * @throws DaoException
     */
    public List<RateSheetRowVo> selectBaseRateRow(Long sheetId) throws DaoException {
        return selectList(sheetId, "RateSheetRow.selectBaseRateRow");
    }

    public List<RateSheetRowVo> selectBaseRateRowWithZone(RateSheetFilter filter) throws DaoException {
        return selectList(filter, "RateSheetRow.selectBaseRateRowWithZone");
    }

    public void deleteRateSheetRowsBySheetId(Map<String, String> context, Long sheetId) throws DaoException {
        delete(context, sheetId, "RateSheetRow.deleteRateSheetRowsBySheetId");
    }

    public List<RateSheetRowVo> selectBySheetId(Long sheetId) throws DaoException {
        return this.selectList(sheetId, "RateSheetRow.selectBySheetId");
    }

    public RateSheetRowVo getRateSheetRow(RateSheetRowVo filter) throws DaoException {
        return select(filter, "RateSheetRow.getRateSheetRow");
    }

    public void insertRateSheetRow(Map<String, String> context, RateSheetRowVo rateSheetRowVo) throws DaoException {
        insert(context, rateSheetRowVo, "RateSheetRow.insertRateSheetRow");
    }

    public void updateRateSheetRows(Map<String, String> context, RateSheetParam rateSheetParam) throws DaoException {
        update(context, rateSheetParam, "RateSheetRow.updateRateSheetRows");
    }
}
