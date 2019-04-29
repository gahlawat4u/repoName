/**
 *
 */
package com.gms.xms.persistence.service.admin.setcostbasis;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.SetCostBasisSearchFilter;
import com.gms.xms.persistence.dao.admin.SetCostBasisDao;
import com.gms.xms.txndb.vo.RateSheetFilter;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.SetCostBasisTableVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SetCostBasisServiceImp
 *
 * @author HungNT - @since Oct 6, 2015
 */
public class SetCostBasisServiceImp implements ISetCostBasisService {
    private SetCostBasisDao dao = new SetCostBasisDao();

    @Override
    public List<SetCostBasisTableVo> getAllTableRecords() throws DaoException {
        return dao.selectSetCostBasisTable();
    }

    public List<SetCostBasisTableVo> getTableRecordsByFilter(SetCostBasisSearchFilter filter) throws DaoException {
        return dao.selectCostBasisTableByFilter(filter);
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
