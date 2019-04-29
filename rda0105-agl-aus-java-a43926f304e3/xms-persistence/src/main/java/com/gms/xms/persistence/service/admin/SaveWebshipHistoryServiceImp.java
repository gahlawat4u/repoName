package com.gms.xms.persistence.service.admin;

import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.ShipmentDetailDao;
import com.gms.xms.txndb.vo.ShipmentDetailVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.admin.SaveWebshipHistoryVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class SaveWebshipHistoryServiceImp implements ISaveWebshipHistoryService {
    private static final Log log = LogFactory.getLog(SaveWebshipHistoryServiceImp.class);

    @Override
    public void saveWebshipHistory(Map<String, String> context, SaveWebshipHistoryVo saveWebshipHistoryVo) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
        ShipmentDetailDao shipmentDetailDao = new ShipmentDetailDao(sessionClient);
        try {
            ShipmentVo shipmentVo = new ShipmentVo();
            shipmentVo.setShipmentId(saveWebshipHistoryVo.getShipmentId());
            if(saveWebshipHistoryVo.getBaseCharge() != null) {
                shipmentVo.setBaseCharge(new BigDecimal(saveWebshipHistoryVo.getBaseCharge()).setScale(2, RoundingMode.HALF_UP));
            }
            shipmentVo.setAirbillNumber(saveWebshipHistoryVo.getAirbillNumber());
            shipmentDao.update(context, shipmentVo);

            if (saveWebshipHistoryVo.getSurcharges() != null) {
                for (ShipmentDetailVo surcharge : saveWebshipHistoryVo.getSurcharges()) {
                    if (surcharge.getAmount() != null) {
                        shipmentDetailDao.update(context, surcharge);
                    }
                }
            }
            sessionClient.endTransaction();
        } catch (Exception e) {
            sessionClient.rollback();
            log.error(e);
            throw e;
        }
    }
}
