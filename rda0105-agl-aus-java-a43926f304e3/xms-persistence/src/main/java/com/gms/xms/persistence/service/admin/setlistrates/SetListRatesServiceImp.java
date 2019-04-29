/**
 *
 */
package com.gms.xms.persistence.service.admin.setlistrates;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.SetListRatesSearchFilter;
import com.gms.xms.persistence.dao.admin.SetListRatesDao;
import com.gms.xms.txndb.vo.RateSheetFilter;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.SetListRatesVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SetCostBasisServiceImp
 *
 * @author HungNT - @since Oct 6, 2015
 */
public class SetListRatesServiceImp implements ISetListRatesService {
    private SetListRatesDao dao = new SetListRatesDao();

    @Override
    public List<SetListRatesVo> getAllTableRecords() throws DaoException {
        return dao.selectSetListRates();
    }

    @Override
    public List<SetListRatesVo> getTableRecordsByFilter(SetListRatesSearchFilter filter) throws DaoException {
        return dao.selectSetListRatesByFilter(filter);
    }

    @Override
    public List<RateSheetVo> getRateSheets(RateSheetFilter filter) throws DaoException {
        return dao.selectRateSheets(filter);
    }

    @Override
    public void setCostBasis(Map<String, String> context, RateSheetVo rateSheetVo) throws DaoException {
        dao.setCostBasis(context, rateSheetVo);
    }
}
