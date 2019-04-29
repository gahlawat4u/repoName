package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.RateSheetFilter;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.txndb.vo.admin.RateSheetsPageFilter;
import com.gms.xms.txndb.vo.admin.RateSheetsPageVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from RateSheetDao
 * <p>
 * Author HungNT Date May 19, 2015
 */
public class RateSheetDao extends BaseDao<RateSheetVo> {

    public RateSheetDao() {
        super();
    }

    public RateSheetDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * Get count of column
     *
     * @param rateSheetFilter
     * @return total columns
     * @throws DaoException
     */
    public Integer selectCountColumn(RateSheetFilter rateSheetFilter) throws DaoException {
        return (Integer) selectObject(rateSheetFilter, "RateSheet.selectCountColumn");
    }

    @SuppressWarnings("unchecked")
    public List<RateSheetsPageVo> selectRateSheetsListPage(RateSheetsPageFilter filter) throws DaoException {
        return (List<RateSheetsPageVo>) (Object) selectObjectList(filter, "RateSheet.selectRateSheetsListPage");
    }

    public Integer selectCountRateSheetsListPage(RateSheetsPageFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "RateSheet.selectCountRateSheetsListPage");
    }

    public Integer selectTotalUsed(Long sheetId) throws DaoException {
        return (Integer) selectObject(sheetId, "RateSheet.selectTotalUsed");
    }

    public RateSheetVo selectBySheetId(Long sheetId) throws DaoException {
        return select(sheetId, "RateSheet.selectBySheetId");
    }

    public RateSheetVo selectRateSheetFull(Long sheetId) throws DaoException {
        return select(sheetId, "RateSheet.selectRateSheetFull");
    }

    public void deleteRateSheet(Map<String, String> context, Long sheetId) throws DaoException {
        delete(context, sheetId, "RateSheet.deleteRateSheet");
    }

    public RateSheetVo selectRateSheetFullTntDom(Long sheetId) throws DaoException {
        return select(sheetId, "RateSheet.selectRateSheetFullTntDom");
    }

    public RateSheetVo checkRateSheet(RateSheetVo filter) throws DaoException {
        return select(filter, "RateSheet.checkRateSheet");
    }

    public void insertRateSheet(Map<String, String> context, RateSheetVo rateSheetVo) throws DaoException {
        insert(context, rateSheetVo, "RateSheet.insertRateSheet");
    }

    public void updateRateSheet(Map<String, String> context, RateSheetVo rateSheetVo) throws DaoException {
        update(context, rateSheetVo, "RateSheet.updateRateSheet");
    }
}
