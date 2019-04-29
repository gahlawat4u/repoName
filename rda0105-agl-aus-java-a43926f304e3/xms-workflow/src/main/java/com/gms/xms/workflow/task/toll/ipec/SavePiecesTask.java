package com.gms.xms.workflow.task.toll.ipec;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from SavePiecesTask
 * <p>
 * Author TANDT
 */
public class SavePiecesTask implements Task {
    private static final Log log = LogFactory.getLog(SavePiecesTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        PieceDao dao = new PieceDao();
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentVo shipmentVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_REQUEST_VO), ShipmentVo.class);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            for (PieceVo piece : shipmentInfoVo.getPieces()) {
                PieceVo pieceN = new PieceVo();
                pieceN.setCustomValue(0D);
                pieceN.setDataIdentifier("");
                pieceN.setDeadWeight(0D);
                pieceN.setDimensionH(0D);
                pieceN.setDimensionW(0D);
                pieceN.setDimensionL(0D);
                pieceN.setLicensePlate("");
                pieceN.setLicensePlateBarcode("");
                pieceN.setQuantity(0);
                pieceN.setWeight(0D);
                if (piece.getCustomValue() != null) {
                    pieceN.setCustomValue(piece.getCustomValue());
                }
                if (piece.getWeight() != null) {
                    pieceN.setWeight(piece.getWeight());
                }
                if (piece.getDeadWeight() != null) {
                    pieceN.setDeadWeight(piece.getDeadWeight());
                }
                if (piece.getDimension() != null) {
                    pieceN.setDimension(piece.getDimension());
                }
                if (piece.getDimensionH() != null) {
                    pieceN.setDimensionH(piece.getDimensionH());
                }
                if (piece.getDimensionL() != null) {
                    pieceN.setDimensionL(piece.getDimensionL());
                }
                if (piece.getDimensionW() != null) {
                    pieceN.setDimensionW(piece.getDimensionW());
                }
                if (piece.getQuantity() != null) {
                    pieceN.setQuantity(piece.getQuantity());
                }
                pieceN.setShipmentId(shipmentVo.getShipmentId());
                dao.insert(addInfo, pieceN);
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
