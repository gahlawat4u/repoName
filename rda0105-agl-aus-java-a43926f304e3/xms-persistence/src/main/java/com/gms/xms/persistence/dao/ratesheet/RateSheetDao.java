package com.gms.xms.persistence.dao.ratesheet;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.txndb.vo.ratesheet.CarrierRateFilterVo;
import com.gms.xms.txndb.vo.ratesheet.CarrierRateVo;
import com.gms.xms.txndb.vo.ratesheet.MaxMinWeightVo;
import com.gms.xms.txndb.vo.ratesheet.QuoteBaseChargeVo;

/**
 * Posted from RateSheetDao
 * <p>
 * Author TANDT
 */
public class RateSheetDao extends BaseDao<Object> {
    public RateSheetDao() {
        super();
    }

    public RateSheetDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public CarrierRateVo selectCarrierRate(CarrierRateFilterVo carrierRateFilterVo) throws DaoException {
        return (CarrierRateVo) select(carrierRateFilterVo, "RateSheet.selectCarrierRate");
    }

    public QuoteBaseChargeVo selectQuoteBaseChargeWebship(CarrierRateFilterVo carrierRateFilterVo) throws DaoException {
        return (QuoteBaseChargeVo) select(carrierRateFilterVo, "RateSheet.selectQuoteBaseChargeWebship");
    }

    public MaxMinWeightVo getMaxMinWeight(CarrierRateFilterVo carrierRateFilterVo) throws DaoException {
        return (MaxMinWeightVo) select(carrierRateFilterVo, "RateSheet.getMaxMinWeight");
    }

    public RateSheetVo selectBySheetId(Long sheetId) throws DaoException {
        return (RateSheetVo) this.select(sheetId, "RateSheet.selectBySheetId");
    }
}
