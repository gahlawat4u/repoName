package com.gms.xms.workflow.utils.weight;

import com.gms.xms.common.utils.GlobalFunctions;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.txndb.vo.PieceVo;

import java.util.List;

/**
 * Posted from Jul 28, 2016 4:18:36 PM
 * <p>
 * Author dattrinh
 */
public abstract class BaseGrossWeight {
    private SystemSettingDao systemSettingDao = new SystemSettingDao();
    private double totalActualWeightNotConvert;

    public double getGrossWeight(double l, double w, double h, String dimUnit) throws Exception {
        return GlobalFunctions.getGrossWeight(l, w, h, dimUnit, getForceVolWeight());
    }

    public double getTotalGrossWeight(List<PieceVo> pieceVos, String dimUnit) throws Exception {
        double total = 0.0;
        if (pieceVos != null && pieceVos.size() > 0) {
            for (PieceVo pieceVo : pieceVos) {
                total += getGrossWeight(pieceVo.getDimensionL(), pieceVo.getDimensionW(), pieceVo.getDimensionH(), dimUnit);
            }
        }
        return total;
    }

    public double getTotalActualWeight(List<PieceVo> pieceVos, String weightUnit) throws Exception {
        double total = 0.0;
        if (pieceVos != null && pieceVos.size() > 0) {
            double weight;
            for (PieceVo pieceVo : pieceVos) {
                weight = pieceVo.getDeadWeight();
                total += weight;
            }
        }
        return total;
    }

    private double getTotalActualWeightToCompare(List<PieceVo> pieceVos, String weightUnit) throws Exception {
        double total = 0.0;
        if (pieceVos != null && pieceVos.size() > 0) {
            double weight;
            for (PieceVo pieceVo : pieceVos) {
                weight = pieceVo.getDeadWeight();
                totalActualWeightNotConvert += weight;
                weight = GlobalFunctions.weightUnitConvert(weightUnit, weight);
                total += weight;
            }
        }
        return total;
    }

    public double getTotalGrossWeightCeilPiece(List<PieceVo> pieceVos, String dimUnit, boolean isCeil) throws Exception {
        double total = 0.0;
        if (pieceVos != null && pieceVos.size() > 0) {
            for (PieceVo pieceVo : pieceVos) {
                double grossWeight = getGrossWeight(pieceVo.getDimensionL(), pieceVo.getDimensionW(), pieceVo.getDimensionH(), dimUnit);
                total += isCeil?Math.ceil(grossWeight):grossWeight;
            }
        }
        return total;
    }

    public double getTotalActualWeightCeilPiece(List<PieceVo> pieceVos, boolean isCeil) throws Exception {
        double total = 0.0;
        if (pieceVos != null && pieceVos.size() > 0) {
            double weight;
            for (PieceVo pieceVo : pieceVos) {
                weight = pieceVo.getDeadWeight();
//                weight = GlobalFunctions.weightUnitConvert(weightUnit, weight);
                total += isCeil?Math.ceil(weight):weight;
            }
        }
        return total;
    }

    public double getQuoteWeight(List<PieceVo> pieceVos, String dimUnit, String weightUnit) throws Exception {
        double weightDisplay;
        double totalGrossWeight = getTotalGrossWeight(pieceVos, dimUnit);
        double totalActualWeightToCompare = getTotalActualWeightToCompare(pieceVos, weightUnit);
        if(totalGrossWeight <= totalActualWeightToCompare)
        {
            weightDisplay = totalActualWeightNotConvert;
        }
        else
        {
            weightDisplay = totalGrossWeight;
            if(weightUnit.equals("lb"))
                weightDisplay = GlobalFunctions.weightUnitConvertKgToPound(weightUnit, weightDisplay);
        }
        return weightDisplay;
    }

    public abstract Integer getForceVolWeight() throws Exception;

    public SystemSettingDao getSystemSettingDao() {
        return systemSettingDao;
    }

    public void setSystemSettingDao(SystemSettingDao systemSettingDao) {
        this.systemSettingDao = systemSettingDao;
    }
}
