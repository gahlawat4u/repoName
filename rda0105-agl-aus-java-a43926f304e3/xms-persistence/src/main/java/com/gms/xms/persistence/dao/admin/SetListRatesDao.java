/**
 *
 */
package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.SetListRatesSearchFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.RateSheetFilter;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.SetListRatesVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SetListRatesDao
 *
 * @author HungNT - @since Oct 8, 2015
 */
public class SetListRatesDao extends BaseDao<SetListRatesVo> {
    public List<SetListRatesVo> selectSetListRates() throws DaoException {
        return selectList(null, "SetListRates.selectSetListRates");
    }

    public List<SetListRatesVo> selectSetListRatesByFilter(SetListRatesSearchFilter filter) throws DaoException {
        return selectList(filter, "SetListRates.selectSetListRatesByFilter");
    }

    @SuppressWarnings("unchecked")
    public List<RateSheetVo> selectRateSheets(RateSheetFilter filter) throws DaoException {
        return (List<RateSheetVo>) (Object) selectObjectList(filter, "SetListRates.selectRateSheets");
    }

    public void setCostBasis(Map<String, String> context, RateSheetVo rateSheetVo) throws DaoException {
        update(context, rateSheetVo, "SetListRates.setListRates");
    }
}
