package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;

/**
 * Posted from ComponentDao
 * <p>
 * Author dattrinh Jan 21, 2016 5:30:05 PM
 */
public class ComponentDao extends BaseDao<Object> {

    public String getDHLAccount(String customerCode) throws DaoException {
        return (String) this.selectObject(customerCode, "Component.getDHLAccount");
    }

    public String getTollPriorityAccount(String customerCode) throws DaoException {
        return (String) this.selectObject(customerCode, "Component.getTollPriorityAccount");
    }

    public String getAccountNumberByScheduleCollectionId(Long scheduleCollectionId) throws DaoException {
        return (String) this.selectObject(scheduleCollectionId, "Component.getAccountNumberByScheduleCollectionId");
    }

    public String getOtherCarrierNumber(String customerCode) throws DaoException {
        return (String) this.selectObject(customerCode, "Component.getOtherCarrierAccountNumber");
    }
}
