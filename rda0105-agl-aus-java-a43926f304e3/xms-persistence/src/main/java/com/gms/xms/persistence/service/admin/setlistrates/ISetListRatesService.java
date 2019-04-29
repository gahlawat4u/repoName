/**
 *
 */
package com.gms.xms.persistence.service.admin.setlistrates;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.SetListRatesSearchFilter;
import com.gms.xms.txndb.vo.RateSheetFilter;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.SetListRatesVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ISetCostBasisService
 *
 * @author HungNT - @since Oct 6, 2015
 */
public interface ISetListRatesService {
    public List<SetListRatesVo> getAllTableRecords() throws DaoException;

    public List<SetListRatesVo> getTableRecordsByFilter(SetListRatesSearchFilter filter) throws DaoException;

    public List<RateSheetVo> getRateSheets(RateSheetFilter filter) throws DaoException;

    public void setCostBasis(Map<String, String> context, RateSheetVo rateSheetVo) throws DaoException;
}