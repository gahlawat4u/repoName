package com.gms.xms.workflow.task2.toll.ipec;

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
 * @author tkvcl
 */
public class GetTollIpecRateSheetTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollIpecRateSheetTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        TollIpecTotalRateDao dao = new TollIpecTotalRateDao();
        TollIpecTotalRateVo rateSheetsVo = new TollIpecTotalRateVo();

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            TollIpecTotalRateFilter filter = new TollIpecTotalRateFilter();
            filter.setFromZone(context.getString(Attributes.SENDER_ZONE_CODE));
            filter.setToZone(context.getString(Attributes.RECEIVER_ZONE_CODE));
            rateSheetsVo = dao.selectByFilter(filter);
            if (rateSheetsVo != null) {
                context.put(Attributes.TOLL_RATE_SHEET, rateSheetsVo);
            } else {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                context.put(Attributes.ERROR_MESSAGE, "The selected service is not available for the given postal codes. Please kindly select other service type.");
                return false;
            }

        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            context.put(Attributes.ERROR_MESSAGE, "Not Found rate sheet width from zone ".concat(context.getString(Attributes.SENDER_ZONE_CODE)).concat(" to zone ").concat(context.getString(Attributes.RECEIVER_ZONE_CODE)));
            log.error(e);
            return false;
        }
        return true;
    }

}
