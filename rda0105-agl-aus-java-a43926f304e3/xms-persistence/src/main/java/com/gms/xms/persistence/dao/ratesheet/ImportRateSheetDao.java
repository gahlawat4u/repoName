package com.gms.xms.persistence.dao.ratesheet;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.ImportRateSheetFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.RateSheetColumnVo;
import com.gms.xms.txndb.vo.RateSheetDetailVo;
import com.gms.xms.txndb.vo.RateSheetRowVo;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.RateSheetTypeVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from May 5, 2016 3:30:35 PM
 * <p>
 * Author huynd
 */
public class ImportRateSheetDao extends BaseDao<Object> {
    public ImportRateSheetDao() {
        super();
    }

    public ImportRateSheetDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<RateSheetTypeVo> getRateSheetTypeByCarrier(Long carrierId) throws DaoException {
        return this.selectList(carrierId, "ImportRateSheet.getRateSheetTypeByCarrier");
    }

    public long countRateSheetNameByFilter(ImportRateSheetFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "ImportRateSheet.countRateSheetNameByFilter");
    }

    public long countRateSheetDuplicate(ImportRateSheetFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "ImportRateSheet.countRateSheetDuplicate");
    }

    public void insertRateSheet(Map<String, String> context, RateSheetVo rateSheetVo) throws DaoException {
        this.insert(context, rateSheetVo, "ImportRateSheet.insertRateSheet");
    }

    public void insertDataCols(Map<String, String> context, RateSheetColumnVo rateSheetColumnVo) throws DaoException {
        this.insert(context, rateSheetColumnVo, "ImportRateSheet.insertDataCols");
    }

    public void insertDataRows(Map<String, String> context, RateSheetRowVo rateSheetRowVo) throws DaoException {
        this.insert(context, rateSheetRowVo, "ImportRateSheet.insertDataRows");
    }

    public void insertDataDetails(Map<String, String> context, RateSheetDetailVo rateSheetDetailVo) throws DaoException {
        this.insert(context, rateSheetDetailVo, "ImportRateSheet.insertDataDetails");
    }

    public Long selectRateSheetColumnId(RateSheetColumnVo rateSheetColumnVo) throws DaoException {
        return (Long) this.selectObject(rateSheetColumnVo, "ImportRateSheet.selectRateSheetColumnId");
    }

    public Long selectRateSheetRowId(RateSheetRowVo rateSheetRowVo) throws DaoException {
        return (Long) this.selectObject(rateSheetRowVo, "ImportRateSheet.selectRateSheetRowId");
    }

    public void updateCells(Map<String, String> context, RateSheetVo rateSheetVo) throws DaoException {
        this.update(context, rateSheetVo, "ImportRateSheet.updateCells");
    }
}
