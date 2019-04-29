/**
 *
 */
package com.gms.xms.persistence.service.admin.setcostbasis;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.SetCostBasisSearchFilter;
import com.gms.xms.txndb.vo.RateSheetFilter;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.SetCostBasisTableVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ISetCostBasisService
 *
 * @author HungNT - @since Oct 6, 2015
 */
public interface ISetCostBasisService {
    public List<SetCostBasisTableVo> getAllTableRecords() throws DaoException;

    public List<SetCostBasisTableVo> getTableRecordsByFilter(SetCostBasisSearchFilter filter) throws DaoException;

    public List<RateSheetVo> getRateSheets(RateSheetFilter filter) throws DaoException;

    public void setCostBasis(Map<String, String> context, RateSheetVo rateSheetVo) throws DaoException;
}