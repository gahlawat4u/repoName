/**
 *
 */
package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.SetCostBasisSearchFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.RateSheetFilter;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.SetCostBasisTableVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SetCostBasisDao
 *
 * @author HungNT - @since Oct 6, 2015
 */
public class SetCostBasisDao extends BaseDao<SetCostBasisTableVo> {
    public List<SetCostBasisTableVo> selectSetCostBasisTable() throws DaoException {
        return selectList(null, "SetCostBasisTable.selectSetCostBasisTable");
    }

    public List<SetCostBasisTableVo> selectCostBasisTableByFilter(SetCostBasisSearchFilter filter) throws DaoException {
        return selectList(filter, "SetCostBasisTable.selectSetCostBasisTableByFilter");
    }

    @SuppressWarnings("unchecked")
    public List<RateSheetVo> selectRateSheets(RateSheetFilter filter) throws DaoException {
        return (List<RateSheetVo>) (Object) selectObjectList(filter, "SetCostBasisTable.selectRateSheets");
    }

    public void setCostBasis(Map<String, String> context, RateSheetVo rateSheetVo) throws DaoException {
        update(context, rateSheetVo, "SetCostBasisTable.setCostBasis");
    }
}
