package com.gms.xms.persistence.service;

import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.dto.statistics.*;
import com.gms.xms.filter.statistics.StatisticsFilter;
import com.gms.xms.persistence.dao.ProspectDao;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.persistence.dao.statistics.StatisticsDao;
import com.gms.xms.txndb.vo.UserVo;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from Aug 23, 2016 2:49:43 PM
 * <p>
 * Author dattrinh
 */
public class StatisticsService {

    private Long userId;
    private Integer userLevel;
    private String franchiseList;

    private StatisticsDao statisticsDao = new StatisticsDao();

    public StatisticsService(Long userId, Integer userLevel, String franchiseList) {
        this.userId = userId;
        this.userLevel = userLevel;
        this.franchiseList = franchiseList;
    }

    public List<StatServiceTypeVo> getServiceTypes(Integer periodType) throws Exception {
        StatisticsFilter filter = this.getFilter();
        filter.setPeriodType(periodType);
        return statisticsDao.getServiceTypeStats(filter);
    }

    public List<StatWebshipLogVo> getWebshipLog(Integer periodType) throws Exception {
        StatisticsFilter filter = this.getFilter();
        filter.setPeriodType(periodType);
        return statisticsDao.getWebshipLogStats(filter);
    }

    public List<StatShipmentVo> getShipments(Integer periodType) throws Exception {
        StatisticsFilter filter = this.getFilter();
        filter.setPeriodType(periodType);
        switch (periodType) {
            case 1:
                return statisticsDao.getShipmentsToday(filter);
            case 2:
                return statisticsDao.getShipmentsWeek(filter);
            case 3:
                return statisticsDao.getShipmentsMonth(filter);
            default:
                throw new CustomException("Get shipments statistics: unknown period type.");
        }
    }

    public List<StatCostVsMarginVo> getCostVsMargin() throws Exception {
        return statisticsDao.getCostVsMargin(this.getFilter());
    }

    public List<StatFollowUpVo> getFollowUps() throws Exception {
        return statisticsDao.getFollowUps(this.getFilter());
    }

    public List<StatSalesRepByManagerVo> getSalesRepByManager() throws Exception {
        UserDao userDao = new UserDao();
        List<Long> users = userDao.getByParentId(userId);
        String userList = "";
        for (Long item : users) {
            userList += String.valueOf(item) + ",";
        }
        userList = StringUtils.isBlank(userList) ? null : userList.substring(0, userList.length() - 1);
        StatisticsFilter filter = this.getFilter();
        filter.setUserList(userList);
        filter.setUserId(userId);
        List<StatSalesRepByManagerVo> managerVos = statisticsDao.getSalesRepByManager(filter);
        // Get total success count.
        long totalSuccess = 0;
        for (StatSalesRepByManagerVo managerVo : managerVos) {
            totalSuccess += managerVo.getCount();
        }
        // Recalculate success rate for each row.
        double successRate;
        for (StatSalesRepByManagerVo managerVo : managerVos) {
            successRate = ((double) 100 * managerVo.getCount()) / totalSuccess;
            managerVo.setSuccessRate(successRate);
        }
        return managerVos;
    }

    public long[] getSalesRepByTeleMarketer(Integer userLevelId, Long franchiseCode, Integer status) throws Exception {
        // Get prospect list.
        ProspectDao prospectDao = new ProspectDao();
        UserVo userVo = new UserVo();
        userVo.setUserId(userId);
        userVo.setUserCode(franchiseCode);
        userVo.setUserLevelId(userLevelId);
        String prospectList = AppUtils.array2String(prospectDao.getProspectByUser(userVo));
        prospectList = StringUtils.isBlank(prospectList) ? "-1" : prospectList;
        // Create filter.
        StatisticsFilter filter = this.getFilter();
        filter.setStatus(status);
        filter.setProspectList(prospectList);
        // Get sales rep for TeleMarketer.
        List<StatSalesRepByTeleMarketerVo> marketerVos = statisticsDao.getSalesRepByTeleMarketer(filter);
        long[] count = new long[]{0, 0, 0, 0};
        for (StatSalesRepByTeleMarketerVo marketerVo : marketerVos) {
            count[marketerVo.getNo()] = marketerVo.getCount();
        }
        return count;
    }

    public Map<String, String> getSalesRepBySalePerson(Integer month) throws Exception {
        StatisticsFilter filter = this.getFilter();
        filter.setMonth(month);
        List<StatSalesRepBySalePersonVo> personVos = statisticsDao.getSalesRepBySalePerson(filter);
        long cusTotal = 0;
        long cusActive = 0;
        long cusInactive = 0;
        long totalCall = 0;
        Double targetCall = 0.0;
        Double targetSuccess = 0.0;
        long count = 0;
        long overCount = 0;
        Double amount = 0.0;
        long successCount = 0;
        for (StatSalesRepBySalePersonVo personVo : personVos) {
            if ("cus_total".equalsIgnoreCase(personVo.getName())) {
                cusTotal = personVo.getTotal1();
            } else if ("cus_active".equalsIgnoreCase(personVo.getName())) {
                cusActive = personVo.getTotal1();
            } else if ("cus_inactive".equalsIgnoreCase(personVo.getName())) {
                cusInactive = personVo.getTotal1();
            } else if ("phone".equalsIgnoreCase(personVo.getName())) {
                totalCall = personVo.getTotal1();
                targetCall = personVo.getTotal2();
            } else if ("opportunity".equalsIgnoreCase(personVo.getName())) {
                count = personVo.getTotal1();
                amount = personVo.getTotal2();
            } else if ("opportunity_over".equalsIgnoreCase(personVo.getName())) {
                overCount = personVo.getTotal1();
            } else if ("success".equalsIgnoreCase(personVo.getName())) {
                successCount = personVo.getTotal1();
                targetSuccess = personVo.getTotal2();
            }
        }
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("cus_total", String.valueOf(cusTotal));
        resultMap.put("cus_active", String.valueOf(cusActive));
        resultMap.put("cus_inactive", String.valueOf(cusInactive));
        resultMap.put("total_call", String.valueOf(totalCall));
        resultMap.put("target_call", String.valueOf(targetCall));
        resultMap.put("count", String.valueOf(count));
        resultMap.put("amount", String.valueOf(amount));
        resultMap.put("over_count", String.valueOf(overCount));
        resultMap.put("success_count", String.valueOf(successCount));
        resultMap.put("target_success", String.valueOf(targetSuccess));
        return resultMap;
    }

    public List<StatSalesGraphVo> getSalesGraph(Integer userLevelId, Long franchiseCode) throws Exception {
        // Get prospect list.
        ProspectDao prospectDao = new ProspectDao();
        UserVo userVo = new UserVo();
        userVo.setUserId(userId);
        userVo.setUserLevelId(userLevelId);
        userVo.setUserCode(franchiseCode);
        // Create filter.
        StatisticsFilter filter = this.getFilter();
        String prospectList = AppUtils.array2String(prospectDao.getProspectByUser(userVo));
        prospectList = StringUtils.isBlank(prospectList) ? "-1" : prospectList;
        filter.setProspectList(prospectList);
        // Get sales graph.
        return statisticsDao.getSalesGraph(filter);
    }

    public double getTotalSales(Integer userLevelId, Long franchiseCode) throws Exception {
        // Get prospect list.
        ProspectDao prospectDao = new ProspectDao();
        UserVo userVo = new UserVo();
        userVo.setUserId(userId);
        userVo.setUserLevelId(userLevelId);
        userVo.setUserCode(franchiseCode);
        // Create filter.
        StatisticsFilter filter = this.getFilter();
        String prospectList = AppUtils.array2String(prospectDao.getProspectByUser(userVo));
        prospectList = StringUtils.isBlank(prospectList) ? "-1" : prospectList;
        filter.setProspectList(prospectList);
        // Get sales graph total.
        List<StatSalesGraphTotalVo> graphTotalVos = statisticsDao.getSalesGraphTotal(filter);
        int[] revPct = new int[]{0, 5, 15, 20, 30, 75, 100};
        double[] rev = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        for (StatSalesGraphTotalVo graph : graphTotalVos) {
            rev[graph.getSaleState()] = graph.getRevenue();
        }
        double amount = 0.0;
        for (int i = 0; i <= 6; i++) {
            amount += (rev[i] * revPct[i]) / 100;
        }
        return amount;
    }

    protected StatisticsFilter getFilter() {
        StatisticsFilter filter = new StatisticsFilter();
        filter.setUserId(userId);
        filter.setUserLevel(userLevel);
        filter.setFranchiseList(franchiseList);
        return filter;
    }
}
