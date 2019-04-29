package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.ExtraRateSheetDetailVo;
import com.gms.xms.txndb.vo.RateSheetDetailFilter;
import com.gms.xms.txndb.vo.RateSheetDetailVo;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from RateSheetDetailDao
 * <p>
 * Author HungNT Date Apr 22, 2015
 */
public class RateSheetDetailDao extends BaseDao<Object> {

    public RateSheetDetailDao() {
        super();
    }

    public RateSheetDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * Get DHL domestic base charge rate
     *
     * @param {@link WebshipRateSheetDetailVo} WebshipRateSheetDetailVo
     * @return {@link WebshipRateSheetDetailVo} base charge rate
     * @throws DaoException
     */
    public WebshipRateSheetDetailVo selectDhlDomesticBaseChargeRate(RateSheetDetailFilter rateSheetDetailFilter) throws DaoException {
        return (WebshipRateSheetDetailVo) select(rateSheetDetailFilter, "RateSheetDetail.selectDhlDomesticBaseChargeRate");
    }

    /**
     * Get max weight and max value by sheet id and column name
     *
     * @param rateSheetDetailFilter
     * @return {@link WebshipRateSheetDetailVo}
     * @throws DaoException
     */
    public WebshipRateSheetDetailVo selectMinWeightAndMinValueByFilter(RateSheetDetailFilter rateSheetDetailFilter) throws DaoException {
        return (WebshipRateSheetDetailVo) select(rateSheetDetailFilter, "RateSheetDetail.selectMinWeightAndMinValueByFilter");
    }

    /**
     * Get min weight and min value by sheet id and column name
     *
     * @param rateSheetDetailFilter
     * @return {@link WebshipRateSheetDetailVo}
     * @throws DaoException
     */
    public WebshipRateSheetDetailVo selectMaxWeightAndMaxValueByFilter(RateSheetDetailFilter rateSheetDetailFilter) throws DaoException {
        return (WebshipRateSheetDetailVo) select(rateSheetDetailFilter, "RateSheetDetail.selectMaxWeightAndMaxValueByFilter");
    }

    /**
     * Get max over weight
     *
     * @param rateSheetDetailFilter
     * @return {@link WebshipRateSheetDetailVo}
     * @throws DaoException
     */
    public WebshipRateSheetDetailVo selectMaxOverWeight(RateSheetDetailFilter rateSheetDetailFilter) throws DaoException {
        return (WebshipRateSheetDetailVo) select(rateSheetDetailFilter, "RateSheetDetail.selectMaxOverWeight");
    }

    /**
     * Get base charge
     *
     * @param rateSheetDetailFilter
     * @return
     * @throws DaoException
     */
    public WebshipRateSheetDetailVo selectBaseChargeWebShip(RateSheetDetailFilter rateSheetDetailFilter) throws DaoException {
        return (WebshipRateSheetDetailVo) select(rateSheetDetailFilter, "RateSheetDetail.selectBaseChargeWebShip");
    }

    public WebshipRateSheetDetailVo selectBaseChargeByCharRowAndCol(RateSheetDetailFilter rateSheetDetailFilter) throws DaoException {
        return (WebshipRateSheetDetailVo) select(rateSheetDetailFilter, "RateSheetDetail.selectBaseChargeByCharRowAndCol");
    }

    public WebshipRateSheetDetailVo selectTntWeightBreak(RateSheetDetailFilter rateSheetDetailFilter) throws DaoException {
        return (WebshipRateSheetDetailVo) select(rateSheetDetailFilter, "RateSheetDetail.selectTntWeightBreak");
    }

    public List<RateSheetDetailVo> selectBySheetId(Long sheetId) throws DaoException {
        return selectList(sheetId, "RateSheetDetail.selectBySheetId");
    }

    public void updateRateSheetDetail(Map<String, String> context, RateSheetDetailVo rateSheetDetailVo) throws DaoException {
        update(context, rateSheetDetailVo, "RateSheetDetail.updateRateSheetDetail");
    }

    public void deleteRateSheetDetailBySheetId(Map<String, String> context, Long sheetId) throws DaoException {
        delete(context, sheetId, "RateSheetDetail.deleteRateSheetDetailBySheetId");
    }

    public ExtraRateSheetDetailVo getExtraRateSheetDetail(RateSheetDetailVo filter) throws DaoException {
        return (ExtraRateSheetDetailVo) this.select(filter, "RateSheetDetail.getExtraRateSheetDetail");
    }

    public void insertRateSheetDetail(Map<String, String> context, RateSheetDetailVo rateSheetDetailVo) throws DaoException {
        insert(context, rateSheetDetailVo, "RateSheetDetail.insertRateSheetDetail");
    }
}
