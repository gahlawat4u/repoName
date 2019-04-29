/**
 *
 */
package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdministrationFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.administration.CountryListVo;

import java.util.List;

/**
 * Posted from CountryListDao
 *
 * @author TanDt
 */
public class CountryListDao extends BaseDao<CountryListVo> {
    public Long selectCountryListCount(AdministrationFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CountryList.selectCountryListCount");
    }

    public List<CountryListVo> selectCountryList(AdministrationFilter filter) throws DaoException {
        return this.selectList(filter, "CountryList.selectCountryList");
    }
}
