/**
 *
 */
package com.gms.xms.persistence.service.admin.coversheet;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.CoverSheetFilter;
import com.gms.xms.persistence.dao.admin.CoverSheetDao;
import com.gms.xms.persistence.service.admin.carriercoversheet.CarrierCoverSheetServiceImp;
import com.gms.xms.txndb.vo.admin.ratesheets.CoverSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ServiceCoverSheetVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from CoverSheetServiceImp
 *
 * @author HungNT - @since Oct 8, 2015
 */
public class CoverSheetServiceImp implements ICoverSheetService {
    private static final Log log = LogFactory.getLog(CarrierCoverSheetServiceImp.class);
    private CoverSheetDao dao = new CoverSheetDao();

    @Override
    public CoverSheetVo getCoverSheetById(Long coverSheetId) throws DaoException {
        return dao.selectByCoverSheetId(coverSheetId);
    }

    @Override
    public List<CoverSheetVo> getAllCoverSheets(CoverSheetFilter filter) throws DaoException {
        return dao.getAllCoverSheets(filter);
    }

    @Override
    public Integer getCountAllCoverSheets() throws DaoException {
        return dao.getCountAllCoverSheets();
    }

    @Override
    public List<ServiceCoverSheetVo> getAllServiceCoverSheets() throws DaoException {
        return dao.selectAllServiceCoverSheets();
    }

    @Override
    public Integer getCountServiceCoverSheets() throws DaoException {
        return dao.selectCountAllServiceCoverSheets();
    }

    @Override
    public void addCoverSheet(Map<String, String> context, String fileName) throws DaoException {
        dao.insertCoverSheet(context, fileName);
    }

    @Override
    public void deleteCoverSheet(Map<String, String> context, Long coverSheetId) throws DaoException {
        dao.deleteCoverSheet(context, coverSheetId);
        log.info("Deleted coverSheetId: " + String.valueOf(coverSheetId));
    }
}
