package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.client.integration.request.HistoryRequest;
import com.gms.xms.workflow.client.integration.response.HistoryResponse;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetShipmentInfoByShipID
 * <p>
 * Author TanDT Date Apr 18, 2015
 */
public class GetShipmentInfoByShipIDTask implements Task {
    private static final Log log = LogFactory.getLog(GetShipmentInfoByShipIDTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentDao shipmentDao = new ShipmentDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        HistoryRequest request = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESQUEST), HistoryRequest.class);
        HistoryResponse response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        try {
            if (request.getShipmentId() != null) {
                Long customerCode = shipmentDao.getCusCodeByShipmentId(request.getShipmentId());
                String from = this.getIsFranchiseFromCusCode(customerCode);
                ShipmentVo shipmentVo = new ShipmentVo();
                if (from.equals("franchise")) {
                    shipmentVo = shipmentDao.getShipmentInfoFranchiseByShipID(request.getShipmentId());
                    response.setShipmentInfoByShipIDShipmentVo(shipmentVo);
                } else {
                    shipmentVo = shipmentDao.getShipmentInfoCustomerByShipID(request.getShipmentId());
                    response.setShipmentInfoByShipIDShipmentVo(shipmentVo);
                }
            }
            context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    public String getIsFranchiseFromCusCode(Long cuscode) {
        String codeFranchise = String.valueOf(cuscode).substring(3);
        if (codeFranchise.equals("0001")) {
            return "franchise";
        } else {
            return "customer";
        }
    }

}
