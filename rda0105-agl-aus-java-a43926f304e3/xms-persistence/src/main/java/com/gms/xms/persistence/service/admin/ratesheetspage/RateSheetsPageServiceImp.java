/**
 *
 */
package com.gms.xms.persistence.service.admin.ratesheetspage;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.RateSheetDao;
import com.gms.xms.persistence.dao.RateSheetDaoService;
import com.gms.xms.txndb.vo.admin.RateSheetsPageFilter;
import com.gms.xms.txndb.vo.admin.RateSheetsPageVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from RateSheetsPageServiceImp
 *
 * @author HungNT - @since Oct 1, 2015
 */
public class RateSheetsPageServiceImp implements IRateSheetsPageService {
    private RateSheetDao dao = new RateSheetDao();

    @Override
    public List<RateSheetsPageVo> getRateSheetsList(RateSheetsPageFilter filter) throws DaoException {
        return dao.selectRateSheetsListPage(filter);
    }

    @Override
    public Integer getCountRateSheets(RateSheetsPageFilter filter) throws DaoException {
        return dao.selectCountRateSheetsListPage(filter);
    }

    @Override
    public Integer getTotalUsed(Long sheetId) throws DaoException {
        return dao.selectTotalUsed(sheetId);
    }

    @Override
    public void deleteRateSheet(Map<String, String> context, Long sheetId) throws Exception {
        RateSheetDaoService daoService = new RateSheetDaoService();
        daoService.deleteRateSheet(context, sheetId);
    }
}