package com.gms.xms.workflow.task2.generateetfile.tollipec;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.webship.TollConnoteDao;
import com.gms.xms.txndb.vo.toll.TollShipmentVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from Sep 26, 2016 10:59:19 AM
 * <p>
 * Author huynd
 */
public class GetTollIpecShipmentListTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollIpecShipmentListTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            TollConnoteDao tollConnoteDao = new TollConnoteDao();
            List<TollShipmentVo> tollShipmentVos = tollConnoteDao.getTollShipmentGenerateList(59L);
            if (tollShipmentVos.isEmpty()) {
                throw new Exception("There is no records to generate.");
            }
            context.put(GenerateETFileConstants.TOLL_SHIPMENT_GENERATE_LIST, tollShipmentVos);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
