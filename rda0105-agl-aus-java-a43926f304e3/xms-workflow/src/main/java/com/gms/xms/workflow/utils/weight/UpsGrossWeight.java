package com.gms.xms.workflow.utils.weight;

import com.gms.xms.common.utils.GlobalFunctions;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.workflow.utils.ShipmentUtils;

import java.util.List;

/**
 * Posted from Jul 28, 2016 4:20:04 PM
 * <p>
 * Author dattrinh
 */
public class UpsGrossWeight extends BaseGrossWeight {

    @Override
    public Integer getForceVolWeight() throws Exception {
        Integer forceVolWeight = null;
        forceVolWeight = Integer.parseInt(this.getSystemSettingDao().getSystemSettingByName("Volume Weight Divided By (UPS)").getSettingValue().trim()); // 5000;
        return forceVolWeight == null ? 5000 : forceVolWeight;
    }

    @Override
    public double getQuoteWeight(List<PieceVo> pieceVos, String dimUnit, String weightUnit) throws Exception {
        Double actualWeight = 0D;
        Double actualWeightNotConvert = 0D;
        Double grossWeight = 0D;
        Double totalWeight = 0D;

        for (PieceVo piece : pieceVos) {
            // Get dimensions
            Double dimL = piece.getDimensionL();
            Double dimH = piece.getDimensionH();
            Double dimW = piece.getDimensionW();

            Integer quantity = piece.getQuantity();
            if (quantity == null || quantity == 0) {
                quantity = 1;
                piece.setQuantity(quantity);
            }

            actualWeightNotConvert = piece.getWeight() * quantity;
            actualWeight = ShipmentUtils.weightUnitConvert(actualWeightNotConvert, weightUnit);
            if (dimW != null && dimH != null && dimL != null) {
                grossWeight = ShipmentUtils.getGrossWeight(dimW, dimH, dimL, dimUnit, getForceVolWeight());
                grossWeight = grossWeight * quantity;
            } else {
                grossWeight = 0D;
            }
            if(grossWeight>actualWeight){
                if(weightUnit.equals("lb"))
                    grossWeight = GlobalFunctions.weightUnitConvertKgToPound(weightUnit, grossWeight);
                totalWeight += grossWeight;
            }else
            {
                totalWeight += actualWeightNotConvert;
            }
        }

        return totalWeight;
    }
}
