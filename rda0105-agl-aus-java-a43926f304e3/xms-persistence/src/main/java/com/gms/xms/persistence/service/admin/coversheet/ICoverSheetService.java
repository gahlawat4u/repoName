/**
 *
 */
package com.gms.xms.persistence.service.admin.coversheet;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.CoverSheetFilter;
import com.gms.xms.txndb.vo.admin.ratesheets.CoverSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ServiceCoverSheetVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ICoverSheetService
 *
 * @author HungNT - @since Oct 8, 2015
 */
public interface ICoverSheetService {
    public List<CoverSheetVo> getAllCoverSheets(CoverSheetFilter filter) throws DaoException;

    public Integer getCountAllCoverSheets() throws DaoException;

    public List<ServiceCoverSheetVo> getAllServiceCoverSheets() throws DaoException;

    public Integer getCountServiceCoverSheets() throws DaoException;

    public void deleteCoverSheet(Map<String, String> context, Long coverSheetId) throws DaoException;

    public void addCoverSheet(Map<String, String> context, String fileName) throws DaoException;

    public CoverSheetVo getCoverSheetById(Long coverSheetId) throws DaoException;
}
