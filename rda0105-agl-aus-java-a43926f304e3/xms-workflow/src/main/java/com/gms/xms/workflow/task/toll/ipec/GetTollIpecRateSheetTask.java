package com.gms.xms.workflow.task.toll.ipec;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.TollIpecTotalRateDao;
import com.gms.xms.txndb.vo.TollIpecTotalRateFilter;
import com.gms.xms.txndb.vo.TollIpecTotalRateVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author tkvcl
 */
public class GetTollIpecRateSheetTask implements Task {
    private static final Log log = LogFactory.getLog(GetTollIpecRateSheetTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        TollIpecTotalRateDao dao = new TollIpecTotalRateDao();
        TollIpecTotalRateVo rateSheetsVo = new TollIpecTotalRateVo();

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            TollIpecTotalRateFilter filter = new TollIpecTotalRateFilter();
            filter.setFromZone(context.get(Attributes.SENDER_ZONE_CODE));
            filter.setToZone(context.get(Attributes.RECEIVER_ZONE_CODE));
            rateSheetsVo = dao.selectByFilter(filter);
            if (rateSheetsVo != null) {
                context.put(Attributes.TOLL_RATE_SHEET, GsonUtils.toGson(rateSheetsVo));
            } else {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                context.put(Attributes.ERROR_MESSAGE, "The selected service is not available for the given postal codes. Please kindly select other service type.");
                return false;
            }

        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            context.put(Attributes.ERROR_MESSAGE, "Not Found rate sheet width from zone ".concat(context.get(Attributes.SENDER_ZONE_CODE)).concat(" to zone ").concat(context.get(Attributes.RECEIVER_ZONE_CODE)));
            log.error(e);
            return false;
        }
        return true;
    }

}
