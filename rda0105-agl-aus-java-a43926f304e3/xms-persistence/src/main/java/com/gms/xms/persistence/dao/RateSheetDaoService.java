package com.gms.xms.persistence.dao;

import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.RateSheetDetailVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from RateSheetDaoService
 *
 * @author HungNT - @since Oct 1, 2015
 */
public class RateSheetDaoService {
    private static final Log log = LogFactory.getLog(RateSheetDaoService.class);

    private RateSheetDao rateSheetDao;
    private RateSheetDetailDao rateSheetDetailDao;
    private RateSheetColumnDao rateSheetColumnDao;
    private RateSheetRowDao rateSheetRowDao;

    public void deleteRateSheet(Map<String, String> context, Long sheetId) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        log.info("Start transaction.");
        rateSheetDao = new RateSheetDao(sessionClient);
        rateSheetDetailDao = new RateSheetDetailDao(sessionClient);
        rateSheetColumnDao = new RateSheetColumnDao(sessionClient);
        rateSheetRowDao = new RateSheetRowDao(sessionClient);

        try {
            log.info("Deleting rate sheet details for sheetId=" + String.valueOf(sheetId));
            rateSheetDetailDao.deleteRateSheetDetailBySheetId(context, sheetId);

            log.info("Deleting rate sheet columns for sheetId=" + String.valueOf(sheetId));
            rateSheetColumnDao.deleteColumnsBySheetId(context, sheetId);

            log.info("Deleting rate sheet rows for sheetId=" + String.valueOf(sheetId));
            rateSheetRowDao.deleteRateSheetRowsBySheetId(context, sheetId);

            log.info("Deleting rate sheet for sheetId=" + String.valueOf(sheetId));
            rateSheetDao.deleteRateSheet(context, sheetId);

            log.info("DELETED: sheetId=" + String.valueOf(sheetId));
            sessionClient.endTransaction();
            log.info("End Transaction.");
        } catch (Exception e) {
            log.error(e);
            sessionClient.rollback();
            throw e;
        }
    }

    public void saveRateSheetDetails(List<RateSheetDetailVo> details) {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        log.info("Start transaction.");
        for (RateSheetDetailVo rateSheetDetailVo : details) {
            log.info("Updating columId: " + String.valueOf(rateSheetDetailVo.getColumnId()) + " - rowId: " + rateSheetDetailVo.getRowId());

        }
    }
}
