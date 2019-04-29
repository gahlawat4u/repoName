package com.gms.xms.workflow.task.copetransport;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from ValidDataBookingTask
 * <p>
 * Author TANDT
 */
public class ValidDataBookingTask implements Task {
    private static final Log log = LogFactory.getLog(ValidDataBookingTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentTypeDao smtDao = new ShipmentTypeDao();
        ShipmentDao smDao = new ShipmentDao();
        CountryDao cDao = new CountryDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            ShipmentTypeVo shipmentTypeVo = smtDao.selectById(shipmentInfoVo.getShipmentTypeId());
            // Check EUROPLUS;
            if (shipmentTypeVo.getServiceCode().equals("EUROPLUS")) {

            }
            // check EU
            Integer checkEU = smDao.checkEU();
            if (checkEU >= 1) {
                if (cDao.checkCountryEU(shipmentInfoVo) >= 2) {
                }
            }

            // Check EA
            CountryVo countryVo = new CountryVo();
            countryVo = cDao.selectCountryDetail(shipmentInfoVo.getSenderAddress().getCountry());
            if (countryVo.getCountryCode().equals("US")) {
                if (countryVo.getDhlCountry().getDhlRegion().equals("EA")) {
                    // is SE
                } else {
                }
            }

        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
