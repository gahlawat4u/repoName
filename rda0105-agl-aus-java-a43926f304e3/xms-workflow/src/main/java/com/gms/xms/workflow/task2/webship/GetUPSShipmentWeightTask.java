package com.gms.xms.workflow.task2.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from GetShipmentWeightTask
 * <p>
 * Author HungNT Date Apr 18, 2015
 */
public class GetUPSShipmentWeightTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetUPSShipmentWeightTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);

            List<PieceVo> shipmentRequestPieces = new ArrayList<>();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ShipmentTypeVo shipmentType = shipmentTypeDao.selectById(shipmentInfoVo.getShipmentTypeId());

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
            Integer forceVolWeight = (serviceId == 72) ? ShipmentUtils.getForceVolWeightStartrack(shipmentType.getShipmentTypeName()) : ShipmentUtils.getForceVolWeight(serviceId);
            for (PieceVo piece : shipmentInfoVo.getPieces()) {
                PieceVo shipmentRequestPiece = new PieceVo();
                // Get dimensions
                Double dimL = piece.getDimensionL();
                Double dimH = piece.getDimensionH();
                Double dimW = piece.getDimensionW();

                Integer quantity = piece.getQuantity();
                if (quantity == null || quantity == 0) {
                    quantity = 1;
                    piece.setQuantity(quantity);
                }

                actualWeight = piece.getWeight() * quantity;
                actualWeight = ShipmentUtils.weightUnitConvert(actualWeight, shipmentInfoVo.getWeightUnit());
                totalActualWeight += actualWeight;
                if (dimW != null && dimH != null && dimL != null) {
                    grossWeight = ShipmentUtils.getGrossWeight(dimW, dimH, dimL, dimUnit, forceVolWeight);
                    grossWeight = grossWeight * quantity;
                } else {
                    grossWeight = 0D;
                }
                totalWeight += Math.max(actualWeight, grossWeight);

                shipmentRequestPiece.setWeight(piece.getWeight());
                totalGrossWeight += grossWeight;
                Double customValue = piece.getCustomValue() * quantity;
                totalCustomValue += customValue;
                dimH = dimH != null ? dimH : 0;
                dimL = dimL != null ? dimL : 0;
                dimW = dimW != null ? dimW : 0;

                shipmentRequestPiece.setCustomValue(customValue);
                shipmentRequestPiece.setDimensionH(dimH);
                shipmentRequestPiece.setDimensionL(dimL);
                shipmentRequestPiece.setDimensionW(dimW);
                shipmentRequestPiece.setDeadWeight(piece.getWeight());
                shipmentRequestPiece.setQuantity(quantity);
                shipmentRequestPieces.add(shipmentRequestPiece);
            }
            if (totalActualWeight <= totalGrossWeight) {
                weightType = "Dimensional";
            }
            if (totalWeight == 0) {
                totalWeight = 0.5;
            }

            if (shipmentType.getShipmentTypeName().equals("Express Saver")) {
                totalWeight = MathUtils.shipmentWeightRound(totalWeight, false);
            } else {
                //totalWeight = MathUtils.shipmentWeightRound(totalWeight, true);  //code by rakesh sir
            	totalWeight = MathUtils.shipmentWeightRound(totalWeight, false);  //code by shahabuddin
            }

            shipmentInfoVo.setTotalWeight(totalWeight);
            shipmentInfoVo.setTotalCustomValue(BigDecimal.valueOf(totalCustomValue).setScale(2, RoundingMode.HALF_UP));
            shipmentInfoVo.setShipmentRequestPieces(shipmentRequestPieces);
            shipmentInfoVo.setWeightType(weightType);
            context.put(Attributes.SHIPMENT_INFO_VO, shipmentInfoVo);
            context.put(Attributes.SHIPMENT_WEIGHT_TYPE, weightType);
            context.put(Attributes.SHIPMENT_TOTAL_WEIGHT, totalWeight);
            context.put(Attributes.TOTAL_CUSTOM_VALUE, totalCustomValue);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
