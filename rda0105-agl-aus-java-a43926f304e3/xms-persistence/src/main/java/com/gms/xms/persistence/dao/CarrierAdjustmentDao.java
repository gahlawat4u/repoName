package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentCountStaticVo;
import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentFilter;
import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentVo;

import java.util.List;

/**
 * Posted from CarrierAdjustmentDao
 * <p>
 * Author TanDT Date May 26, 2015
 */
public class CarrierAdjustmentDao extends BaseDao<CarrierAdjustmentVo> {
    public CarrierAdjustmentDao() {
        super();
    }

    public CarrierAdjustmentDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * Get List CarrierAdjustmentVo
     *
     * @param CarrierAdjustmentFilter
     * @return List<CarrierAdjustmentVo>
     * @throws DaoException
     */
    public List<CarrierAdjustmentVo> selectCarrierAdjustment(CarrierAdjustmentFilter filter) throws DaoException {
        return selectList(filter, "AirbillAdjustment.AirbillAdjustment_SelectCarrierAdjustment");
    }

    /**
     * Get Count List CarrierAdjustmentVo by CarrierAdjustmentFilter
     *
     * @param CarrierAdjustmentFilter
     * @return Long count
     * @throws DaoException
     */
    public Integer selectCountCarrierAdjustment(CarrierAdjustmentFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "AirbillAdjustment.AirbillAdjustment_SelectCountCarrierAdjustment");
    }

    /**
     * Select CarrierAdjustmentDetail
     *
     * @param adjustmentId
     * @return
     * @throws DaoException
     */
    public CarrierAdjustmentVo selectCarrierAdjustmentDetail(Long adjustmentId) throws DaoException {
        return select(adjustmentId, "AirbillAdjustment.SelectCarrierAdjustmentDetail");
    }

    /**
     * Static Carrier Adjustment by status
     *
     * @return
     * @throws DaoException
     */
    public CarrierAdjustmentCountStaticVo selectCarrierAdjustmentCountStaticVo() throws DaoException {
        return (CarrierAdjustmentCountStaticVo) selectObject("", "AirbillAdjustment.SelectCountCarrierAdjustmentByStatus");
    }

}