package com.gms.xms.workflow.task2.invoice.toll.ipec.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.TollIpecTotalRateDao;
import com.gms.xms.txndb.vo.TollIpecTotalRateFilter;
import com.gms.xms.txndb.vo.TollIpecTotalRateVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jul 22, 2016 9:42:46 PM
 * <p>
 * Author huynd
 */
public class GetTollIpecImportBillingRateSheetBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollIpecImportBillingRateSheetBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {

        try {
            TollIpecTotalRateDao tollIpecTotalRateDao = new TollIpecTotalRateDao();
            TollIpecTotalRateVo rateSheetsVo = new TollIpecTotalRateVo();
            TollIpecTotalRateFilter filter = new TollIpecTotalRateFilter();
            filter.setFromZone(context.getString(Attributes.SENDER_ZONE_CODE));
            filter.setToZone(context.getString(Attributes.RECEIVER_ZONE_CODE));
            rateSheetsVo = tollIpecTotalRateDao.selectByFilter(filter);
            context.put(Attributes.TOLL_RATE_SHEET, rateSheetsVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            context.put(Attributes.ERROR_MESSAGE, "Not Found rate sheet width from zone ".concat(context.getString(Attributes.SENDER_ZONE_CODE)).concat(" to zone ").concat(context.getString(Attributes.RECEIVER_ZONE_CODE)));
            log.error(e);
            return false;
        }
        return true;
    }

}
