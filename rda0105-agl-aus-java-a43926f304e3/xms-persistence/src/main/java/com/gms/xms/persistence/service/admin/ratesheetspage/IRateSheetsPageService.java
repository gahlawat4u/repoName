/**
 *
 */
package com.gms.xms.persistence.service.admin.ratesheetspage;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.admin.RateSheetsPageFilter;
import com.gms.xms.txndb.vo.admin.RateSheetsPageVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IRateSheetsPageService
 *
 * @author HungNT - @since Oct 1, 2015
 */
public interface IRateSheetsPageService {
    public List<RateSheetsPageVo> getRateSheetsList(RateSheetsPageFilter filter) throws DaoException;

    public Integer getCountRateSheets(RateSheetsPageFilter filter) throws DaoException;

    public void deleteRateSheet(Map<String, String> context, Long sheetId) throws Exception;

    public Integer getTotalUsed(Long sheetId) throws Exception;
}