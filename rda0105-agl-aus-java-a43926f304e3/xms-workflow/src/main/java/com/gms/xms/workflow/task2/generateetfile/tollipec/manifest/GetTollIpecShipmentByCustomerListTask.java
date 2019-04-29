package com.gms.xms.workflow.task2.generateetfile.tollipec.manifest;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.webship.TollConnoteDao;
import com.gms.xms.persistence.dao.webship.TollIpecConnoteDao;
import com.gms.xms.txndb.vo.toll.TollShipmentVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from Sep 15, 2016 4:26:11 PM
 * <p>
 * Author huynd
 */
public class GetTollIpecShipmentByCustomerListTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollIpecShipmentByCustomerListTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Long customerCode = context.get(GenerateETFileConstants.CUSTOMER_CODE);
            TollIpecConnoteDao tollIpecConnoteDao = new TollIpecConnoteDao();
            List<TollShipmentVo> tollShipmentVos = tollIpecConnoteDao.getTollIpecShipmentByCustomerGenerateList(customerCode);
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
