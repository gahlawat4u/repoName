package com.gms.xms.workflow.task.dhl.domestic;

import com.gms.delivery.dhl.service.DhlShipmentRequestServiceClient;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.Condition;
import com.gms.delivery.dhl.xmlpi.datatype.shipment.response.ShipValResponsePiece;
import com.gms.delivery.dhl.xmlpi.datatype.shipment.response.ShipmentRequestResult;
import com.gms.delivery.dhl.xmlpi.datatype.shipment.response.ShipmentResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.dto.delivery.DhlCapabilityVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DhlDomesticShipmentRequestTask implements Task {
    private static final Log log = LogFactory.getLog(DhlDomesticShipmentRequestTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            ShipmentRequestVo shipmentRequestVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_REQUEST_VO), ShipmentRequestVo.class);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            DhlCapabilityVo dhlCapabilityVo = GsonUtils.fromGson(context.get(Attributes.DHL_CAPABILITY_RESULT), DhlCapabilityVo.class);
            DhlShipmentRequestServiceClient dhlShipmentRequestServiceClient = new DhlShipmentRequestServiceClient();
            String xmlRequest = dhlShipmentRequestServiceClient.parseShipmentXMLRequest(shipmentRequestVo, dhlCapabilityVo);
            String xmlResponse = dhlShipmentRequestServiceClient.executeGetPickup(xmlRequest, null);
            ShipmentRequestResult shipmentRequestResult = dhlShipmentRequestServiceClient.parseResponse(xmlResponse);
            if (shipmentRequestResult.getErrorResponse() == null && shipmentRequestResult.getShipmentValidateErrorResponse() == null) {
                ShipmentResponse shipmentResponse = shipmentRequestResult.getShipmentResponse();
                shipmentInfoVo.setAirbillNumber(shipmentResponse.getAirwayBillNumber());
                shipmentInfoVo.setAwbBarcode(shipmentResponse.getLabelImage().get(0).getOutputImage());
                shipmentInfoVo.setOriginDestiBarcode(shipmentResponse.getBarcodes().getOriginDestnBarcode());
                shipmentInfoVo.setDhlRoutingBarcode(shipmentResponse.getBarcodes().getDHLRoutingBarCode());
                shipmentInfoVo.setCourierMessage(shipmentResponse.getCourierMessage());
                shipmentInfoVo.setServiceAreaCodeOrigin(shipmentResponse.getOriginServiceArea().getServiceAreaCode());
                shipmentInfoVo.setServiceAreaCodeDestination(shipmentResponse.getDestinationServiceArea().getServiceAreaCode());
                List<ShipValResponsePiece> pieces = shipmentResponse.getPieces().getPiece();
                List<PieceVo> savePieces = new ArrayList<>();
                for (ShipValResponsePiece piece : pieces) {
                    PieceVo savePiece = new PieceVo();
                    savePiece.setDimensionL(new BigDecimal(piece.getDepth()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                    savePiece.setDimensionH(new BigDecimal(piece.getHeight()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                    savePiece.setDimensionW(new BigDecimal(piece.getWidth()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                    savePiece.setWeight(piece.getDimWeight().setScale(2, RoundingMode.HALF_UP).doubleValue());
                    savePiece.setDeadWeight(piece.getWeight().setScale(2, RoundingMode.HALF_UP).doubleValue());
                    savePiece.setDataIdentifier(piece.getDataIdentifier());
                    savePiece.setLicensePlate(piece.getLicensePlate());
                    savePiece.setLicensePlateBarcode(piece.getLicensePlateBarCode());
                    if (savePiece.getWeight() == 0) {
                        savePiece.setWeight(1.0);
                    }
                    savePieces.add(savePiece);
                }
                shipmentInfoVo.setPieces(savePieces);
                context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
            } else {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                String errorMsg = "Fail to send shipment request.";
                if (shipmentRequestResult.getErrorResponse() != null) {
                    for (Condition condition : shipmentRequestResult.getErrorResponse().getResponse().getStatus().getCondition()) {
                        errorMsg += condition.getConditionCode() + " - " + condition.getConditionData() + "\n";
                    }
                } else {
                    for (Condition condition : shipmentRequestResult.getShipmentValidateErrorResponse().getResponse().getStatus().getCondition()) {
                        errorMsg += condition.getConditionCode() + " - " + condition.getConditionData() + "\n";
                    }
                }
                context.put(Attributes.ERROR_MESSAGE, errorMsg);
                log.error(errorMsg);
                return false;
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
