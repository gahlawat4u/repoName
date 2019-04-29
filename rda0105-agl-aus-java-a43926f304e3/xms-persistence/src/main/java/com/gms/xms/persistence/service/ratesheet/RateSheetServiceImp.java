/**
 *
 */
package com.gms.xms.persistence.service.ratesheet;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.RateSheetColumnDao;
import com.gms.xms.persistence.dao.RateSheetDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.dao.RateSheetRowDao;
import com.gms.xms.txndb.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from RateSheetServiceImp
 *
 * @author HungNT - @since Oct 2, 2015
 */
public class RateSheetServiceImp implements IRateSheetService {
    private static final Log log = LogFactory.getLog(RateSheetServiceImp.class);
    private RateSheetDao rateSheetDao = new RateSheetDao();
    private RateSheetRowDao rateSheetRowDao = new RateSheetRowDao();
    private RateSheetColumnDao rateSheetColumnDao = new RateSheetColumnDao();
    private RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();

    @Override
    public List<RateSheetRowVo> getRowsBySheetId(Long sheetId) throws DaoException {
        return rateSheetRowDao.selectBaseRateRow(sheetId);
    }

    @Override
    public List<RateSheetRowVo> getRowsBySheetIdWithZone(RateSheetFilter filter) throws DaoException {
        return rateSheetRowDao.selectBaseRateRowWithZone(filter);
    }

    @Override
    public List<RateSheetColumnVo> getColumnsBySheetId(Long sheetId) throws DaoException {
        return rateSheetColumnDao.selectBySheetId(sheetId);
    }
    
    @Override
    public List<RateSheetColumnVo> getUpsColumnsBySheetId(Long sheetId) throws DaoException {
        return rateSheetColumnDao.selectBySheetIdForUps(sheetId);
    }

    @Override
    public List<RateSheetDetailVo> getRateSheetDetailsBySheetId(Long sheetId) throws DaoException {
        return rateSheetDetailDao.selectBySheetId(sheetId);
    }

    @Override
    public RateSheetVo getRateSheetBySheetId(Long sheetId) throws DaoException {
        return rateSheetDao.selectBySheetId(sheetId);
    }

    @Override
    public void updateRateSheetDetail(Map<String, String> context, RateSheetDetailVo rateSheetDetailVo) throws DaoException {
        rateSheetDetailDao.updateRateSheetDetail(context, rateSheetDetailVo);
        log.info("Updated: " + GsonUtils.toGson(rateSheetDetailVo));
    }

    @Override
    public RateSheetVo selectRateSheetFull(Long sheetId) throws DaoException {
        RateSheetDao dao = new RateSheetDao();
        return dao.selectRateSheetFull(sheetId);
    }

    @Override
    public RateSheetVo selectRateSheetFullTntDom(Long sheetId) throws DaoException {
        RateSheetDao dao = new RateSheetDao();
        return dao.selectRateSheetFullTntDom(sheetId);
    }

}
