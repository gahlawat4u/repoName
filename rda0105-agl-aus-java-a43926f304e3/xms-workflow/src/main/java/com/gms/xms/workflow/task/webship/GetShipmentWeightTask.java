package com.gms.xms.workflow.task.webship;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from GetShipmentWeightTask
 * <p>
 * Author HungNT Date Apr 18, 2015
 */
public class GetShipmentWeightTask implements Task {
    private static final Log log = LogFactory.getLog(GetShipmentWeightTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            Integer maxDocWebshipWeight = 0;
            try {
                maxDocWebshipWeight = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("Shipment Document Weight"));
            } catch (Exception e) {
            }
            Integer maxPackageWebshipWeight = 0;
            try {
                maxPackageWebshipWeight = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("Shipment Product Weight"));
            } catch (Exception e) {
            }
            List<PieceVo> shipmentRequestPieces = new ArrayList<>();

            // Get datas for calculating charges
            Double totalActualWeight = 0D;
            Double actualWeight = 0D;
            Double grossWeight = 0D;
            Double totalWeight = 0D;
            Double totalGrossWeight = 0D;
            Double totalCustomValue = 0D;
            String weightType = "Actual";
            String dimUnit = shipmentInfoVo.getDimensionUnit();
            Integer serviceId = shipmentInfoVo.getServiceId();
            String contentType = shipmentInfoVo.getContentType();
            Integer forceVolWeight = ShipmentUtils.getForceVolWeight(serviceId);
            for (PieceVo piece : shipmentInfoVo.getPieces()) {
                PieceVo shipmentRequestPiece = new PieceVo();
                // Get dimensions
                Double dimL = piece.getDimensionL();
                Double dimH = piece.getDimensionH();
                Double dimW = piece.getDimensionW();

                Integer quantity = piece.getQuantity();
                if (quantity == null || quantity == 0) {
                    quantity = 1;
                }

                actualWeight = piece.getWeight() * quantity;
                actualWeight = ShipmentUtils.weightUnitConvert(actualWeight, shipmentInfoVo.getWeightUnit());
                totalActualWeight += actualWeight;
                Double grossWeightRequest = 0D;
                if (dimW != null && dimH != null && dimL != null) {
                    grossWeight = ShipmentUtils.getGrossWeight(dimW, dimH, dimL, dimUnit, forceVolWeight);
                    grossWeightRequest = grossWeight;
                    grossWeight = grossWeight * quantity;
                } else {
                    grossWeight = 0D;
                }

                if (actualWeight >= grossWeight) {
                    shipmentRequestPiece.setWeight(actualWeight);
                } else {
                    shipmentRequestPiece.setWeight(grossWeightRequest);
                }
                totalGrossWeight += grossWeight;
                Double customValue = piece.getCustomValue() * quantity;
                totalCustomValue += customValue;
                shipmentRequestPiece.setDimensionH(dimH);
                shipmentRequestPiece.setDimensionL(dimL);
                shipmentRequestPiece.setDimensionW(dimW);
                shipmentRequestPiece.setQuantity(quantity);
                shipmentRequestPieces.add(shipmentRequestPiece);
            }
            if (totalActualWeight > totalGrossWeight) {
                totalWeight = totalActualWeight;
            } else {
                totalWeight = totalGrossWeight;
                weightType = "Dimensional";
            }
            if (totalWeight == 0) {
                totalWeight = 0.5;
            }
            if (shipmentInfoVo.getServiceId() == 1 || shipmentInfoVo.getServiceId() == 54 || shipmentInfoVo.getServiceId() == 15) {
                totalWeight = MathUtils.shipmentWeightRound(totalWeight, false);
            } else {
                totalWeight = MathUtils.shipmentWeightRound(totalWeight, true);
            }

            if (contentType.equalsIgnoreCase("WPX") && weightType.equalsIgnoreCase("Actual")) {
                if (totalWeight > maxPackageWebshipWeight) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, "The combined total weight of this shipment is above the allowed threshold of " + String.valueOf(maxPackageWebshipWeight) + "kgs");
                    return false;
                }
            } else if (contentType.equalsIgnoreCase("DOX") && weightType.equalsIgnoreCase("Actual")) {
                if (totalWeight > maxDocWebshipWeight) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, "The combined total weight of this shipment is above the allowed threshold of " + String.valueOf(maxDocWebshipWeight) + "kgs");
                    return false;
                }
            }

            shipmentInfoVo.setTotalWeight(totalWeight);
            shipmentInfoVo.setTotalCustomValue(new BigDecimal(totalCustomValue).setScale(2));
            shipmentInfoVo.setShipmentRequestPieces(shipmentRequestPieces);
            shipmentInfoVo.setWeightType(weightType);
            context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
            context.put(Attributes.SHIPMENT_WEIGHT_TYPE, weightType);
            context.put(Attributes.SHIPMENT_TOTAL_WEIGHT, String.valueOf(totalWeight));
            context.put(Attributes.TOTAL_CUSTOM_VALUE, String.valueOf(totalCustomValue));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
