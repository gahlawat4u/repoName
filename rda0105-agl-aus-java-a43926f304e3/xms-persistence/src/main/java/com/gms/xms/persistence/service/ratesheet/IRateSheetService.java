/**
 *
 */
package com.gms.xms.persistence.service.ratesheet;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.*;

import java.util.List;
import java.util.Map;

/**
 * Posted from IRateSheetService
 *
 * @author HungNT - @since Oct 2, 2015
 */
public interface IRateSheetService {
    public List<RateSheetRowVo> getRowsBySheetId(Long sheetId) throws DaoException;

    public List<RateSheetColumnVo> getColumnsBySheetId(Long sheetId) throws DaoException;
    
    public List<RateSheetColumnVo> getUpsColumnsBySheetId(Long sheetId) throws DaoException;

    public List<RateSheetDetailVo> getRateSheetDetailsBySheetId(Long sheetId) throws DaoException;

    public RateSheetVo getRateSheetBySheetId(Long sheetId) throws DaoException;

    public List<RateSheetRowVo> getRowsBySheetIdWithZone(RateSheetFilter filter) throws DaoException;

    public void updateRateSheetDetail(Map<String, String> context, RateSheetDetailVo rateSheetDetailVo) throws DaoException;

    public RateSheetVo selectRateSheetFull(Long sheetId) throws DaoException;

    public RateSheetVo selectRateSheetFullTntDom(Long sheetId) throws DaoException;

}