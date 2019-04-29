package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;
import com.gms.xms.txndb.vo.webship.settings.CustomerDefaultSettingDetailVo;

import java.util.Map;

/**
 * Posted from CustomerDefaultSettingDaoService
 * <p>
 * Author DatTV Date Mar 26, 2015
 */
public class CustomerDefaultSettingDao extends BaseDao<CustomerDefaultSettingVo> {

    public CustomerDefaultSettingVo selectByCustomerCode(Long customerCode) throws DaoException {
        return select(customerCode, "CustomerDefaultSetting.selectByCustomerCode");
    }

    /**
     * Gets customer default setting by customer code
     *
     * @param customerCode
     * @return {@link CustomerDefaultSettingVo}
     * @throws DaoException
     */
    public CustomerDefaultSettingDetailVo selectDetailByCustomerCode(Long customerCode) throws DaoException {
        return (CustomerDefaultSettingDetailVo) select(customerCode, "CustomerDefaultSetting.selectDetailByCustomerCode");
    }

    /**
     * Functions: selectReferenceUserSetting <br/>
     * Date Time Create: 19-12-2015 - 09:50:58 <br/>
     * Descriptions: Function selectReferenceUserSetting ....... <br/>
     *
     * @param customerCode
     * @return
     * @throws DaoException
     */
    public String selectReferenceUserSetting(Long customerCode) throws DaoException {
        return (String) selectObject(customerCode, "CustomerDefaultSetting.selectReferenceUserSetting");
    }

    /**
     * Adds customer default setting into the database
     *
     * @param defaultSetting
     * @throws DaoException
     */
    public void insert(Map<String, String> context, CustomerDefaultSettingVo defaultSetting) throws DaoException {
        insert(context, defaultSetting, "CustomerDefaultSetting.insert");
    }

    /**
     * Updates customer default setting
     *
     * @param defaultSetting
     * @throws DaoException
     */
    public void update(Map<String, String> context, CustomerDefaultSettingVo defaultSetting) throws DaoException {
        update(context, defaultSetting, "CustomerDefaultSetting.update");
    }
}