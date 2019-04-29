package com.gms.xms.workflow.task2.admin.ratesheet.viewbycustomer;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.admin.ratesheets.ServiceMarkupRateFilter;
import com.gms.xms.persistence.dao.admin.ServiceMarkupRateDao;
import com.gms.xms.txndb.vo.admin.customer.baserate.ServiceTypeVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ViewRateSheetRequestVo;
import com.gms.xms.workflow.core2.Task2;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Posted from May 18, 2016 4:16:15 PM
 * <p>
 * Author dattrinh
 */
public class GetServiceMarkupRateTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get view rate sheet request vo.
            ViewRateSheetRequestVo rateSheetRequestVo = context.get(Attributes.VIEW_RATE_SHEET_REQUEST_VO);
            // Get service type info.
            ServiceTypeVo serviceTypeVo = context.get(Attributes.SERVICE_TYPE_VO);
            // Get service markup rate.
            Double serviceMarkupRate = null;
            ServiceMarkupRateFilter filter = new ServiceMarkupRateFilter();
            filter.setServiceId(serviceTypeVo.getServiceId());
            filter.setCode(rateSheetRequestVo.getCustomerCode());
            filter.setShipmentTypeId(serviceTypeVo.getShipmentTypeId());
            ServiceMarkupRateDao markupRateDao = new ServiceMarkupRateDao();
            switch (rateSheetRequestVo.getType()) {
                case 0:
                    serviceMarkupRate = markupRateDao.getServiceMarkupRateByCustomerCode(filter);
                    break;
                case 1:
                    serviceMarkupRate = markupRateDao.getServiceMarkupRateByFranchiseCode(filter);
                    break;
                case 2:
                    serviceMarkupRate = markupRateDao.getServiceMarkupRateByProfileId(filter);
                    break;
                default:
                    throw new CustomException("Unknown view rate sheet type.");
            }
            // Set default service markup rate is 0.
            if (serviceMarkupRate == null) {
                serviceMarkupRate = 0.0;
            } else {
                serviceMarkupRate = new BigDecimal(serviceMarkupRate).setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            // Put into the context.
            context.put(Attributes.SERVICE_MARKUP_RATE, serviceMarkupRate);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
