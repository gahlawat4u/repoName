package com.gms.xms.persistence.dao.statistics;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.dto.statistics.*;
import com.gms.xms.filter.statistics.StatisticsFilter;
import com.gms.xms.persistence.dao.BaseDao;

import java.util.List;

/**
 * Posted from Aug 17, 2016 11:52:02 AM
 * <p>
 * Author dattrinh
 */
public class StatisticsDao extends BaseDao<Object> {
    public List<StatServiceTypeVo> getServiceTypeStats(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getServiceTypeStats");
    }

    public List<StatWebshipLogVo> getWebshipLogStats(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getWebshipLogStats");
    }

    public List<StatShipmentVo> getShipmentsToday(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getShipmentsToday");
    }

    public List<StatShipmentVo> getShipmentsWeek(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getShipmentsWeek");
    }

    public List<StatShipmentVo> getShipmentsMonth(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getShipmentsMonth");
    }

    public List<StatCostVsMarginVo> getCostVsMargin(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getCostVsMargin");
    }

    public List<StatFollowUpVo> getFollowUps(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getFollowUps");
    }

    public List<StatSalesRepByManagerVo> getSalesRepByManager(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getSalesRepByManager");
    }

    public List<StatSalesGraphVo> getSalesGraph(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getSalesGraph");
    }

    public List<StatSalesGraphTotalVo> getSalesGraphTotal(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getSalesGraphTotal");
    }

    public List<StatSalesRepByTeleMarketerVo> getSalesRepByTeleMarketer(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getSalesRepByTeleMarketer");
    }

    public List<StatSalesRepBySalePersonVo> getSalesRepBySalePerson(StatisticsFilter filter) throws DaoException {
        return selectList(filter, "Statistics.getSalesRepBySalePerson");
    }
}
