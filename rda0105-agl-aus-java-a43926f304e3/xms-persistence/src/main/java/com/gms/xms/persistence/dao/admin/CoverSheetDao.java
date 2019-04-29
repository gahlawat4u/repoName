/**
 *
 */
package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.CoverSheetFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.ratesheets.CoverSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ServiceCoverSheetVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CoverSheetDao
 *
 * @author HungNT - @since Oct 8, 2015
 */
public class CoverSheetDao extends BaseDao<CoverSheetVo> {

    public CoverSheetVo selectByCoverSheetId(Long coverSheetId) throws DaoException {
        return select(coverSheetId, "CoverSheet.selectByCoverSheetId");
    }

    public List<CoverSheetVo> getAllCoverSheets(CoverSheetFilter filter) throws DaoException {
        return selectList(filter, "CoverSheet.selectAllCoverSheets");
    }

    public Integer getCountAllCoverSheets() throws DaoException {
        return (Integer) selectObject(null, "CoverSheet.selectCountAllCoverSheets");
    }

    @SuppressWarnings("unchecked")
    public List<ServiceCoverSheetVo> selectAllServiceCoverSheets() throws DaoException {
        return (List<ServiceCoverSheetVo>) (Object) selectList(null, "CoverSheet.selectAllServiceCoverSheets");
    }

    public Integer selectCountAllServiceCoverSheets() throws DaoException {
        return (Integer) selectObject(null, "CoverSheet.selectCountAllServiceCoverSheets");
    }

    public void insertCoverSheet(Map<String, String> context, String fileName) throws DaoException {
        insert(context, fileName, "CoverSheet.insertCoverSheet");
    }

    public void deleteCoverSheet(Map<String, String> context, Long coverSheetId) throws DaoException {
        delete(context, coverSheetId, "CoverSheet.deleteCoverSheet");
    }
}
