package com.gms.xms.workflow.task2.tracking.startrack;

import com.gms.delivery.startrack.xmlpi.tracking.request.StartrackTrackingRequest;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.StarTrackConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * Posted from Jun 24, 2016 5:09:42 PM
 * <p>
 * Author dattrinh
 */
public class PrepareXmlRequestTask implements Task2 {

    private static final Log log = LogFactory.getLog(PrepareXmlRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // 1. Get shipment id from request.
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            // 2. Get shipment info.
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(shipmentId);
            if (shipmentVo == null) {
                throw new CustomException("No shipment found ...\nPlease select another shipment.");
            }
            // Put shipment info into the context.
            context.put(Attributes.SHIPMENT_INFO_VO, shipmentVo);
            // 3. Get Startrack Tracking API settings.
            StartrackTrackingRequest trackingRequest = new StartrackTrackingRequest();
            trackingRequest.setUserName(SystemSettingCache.getInstance().getValueByKey(StarTrackConstants.STARTRACK_TRACKING_USERNAME));
            trackingRequest.setPassword(SystemSettingCache.getInstance().getValueByKey(StarTrackConstants.STARTRACK_TRACKING_PASSWORD));
            trackingRequest.setAccountNo(SystemSettingCache.getInstance().getValueByKey(StarTrackConstants.STARTRACK_TRACKING_ACCOUNT_NO));
            trackingRequest.setUserAccessKey(SystemSettingCache.getInstance().getValueByKey(StarTrackConstants.STARTRACK_TRACKING_API_KEY));
            trackingRequest.setSource("TEAM");
            trackingRequest.setCreateDate(new Date());
            Double randomNumber = Math.random() * Integer.MAX_VALUE;
            String nonce = Long.toHexString(randomNumber.longValue()).toUpperCase();
            nonce = new String(Base64.encodeBase64(nonce.getBytes()));
            trackingRequest.setNonce(nonce);
           // 	trackingRequest.setNonce("clrf4rWgAaHdN+ZqngPOIw==");
            // 4. Get airbill number for tracking request.
            trackingRequest.setConsignmentId(shipmentVo.getAirbillNumber());
            // 5. Parse as xml request.
            context.put(Attributes.STARTRACK_TRACKING_XML_REQUEST, trackingRequest.getXml());
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
