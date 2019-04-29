package com.gms.xms.workflow.task2.admin.ratesheet.viewbyairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.admin.customer.baserate.ManageCustBaseRateDao;
import com.gms.xms.txndb.vo.admin.customer.baserate.ServiceTypeVo;
import com.gms.xms.workflow.core2.Task2;

import java.util.List;

/**
 * Posted from May 16, 2016 3:45:33 PM
 * <p>
 * Author dattrinh
 */
public class GetZoneTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ServiceTypeVo serviceTypeVo = context.get(Attributes.SERVICE_TYPE_VO);
            // Get zones.
            if (serviceTypeVo.getRateSheetId() != 0) {
                ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
                List<String> zones = dao.getZonesBySheetId(serviceTypeVo.getRateSheetId());
                serviceTypeVo.setZones(zones);
            }
            // Update into the context.
            context.put(Attributes.SERVICE_TYPE_VO, serviceTypeVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
