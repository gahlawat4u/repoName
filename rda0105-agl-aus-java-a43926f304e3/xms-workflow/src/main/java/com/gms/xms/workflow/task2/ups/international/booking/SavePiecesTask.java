package com.gms.xms.workflow.task2.ups.international.booking;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.workflow.core.Task;

public class SavePiecesTask implements Task {

	private static final Log log = LogFactory.getLog(SavePiecesTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
	try {
	    context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
	    ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
	    List<PieceVo> pieces = new ArrayList<PieceVo>();

	    for (int i = 0; i < shipmentInfoVo.getPieces().size(); i++) {
		PieceVo piece = shipmentInfoVo.getPieces().get(i);
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

		pieceN.setQuantity(1);
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
		if (piece.getLicensePlate() != null) {
		    pieceN.setLicensePlate(piece.getLicensePlate());
		}
		if (piece.getLicensePlateBarcode() != null) {
		    pieceN.setLicensePlateBarcode(piece.getLicensePlateBarcode());
		}
		pieces.add(pieceN);
	    }
	    BookingDataVo bookingDataVo = GsonUtils.fromGson(context.get(Attributes.BOOKING_DATA), BookingDataVo.class);
	    bookingDataVo.setPieces(pieces);
	    context.put(Attributes.BOOKING_DATA, GsonUtils.toGson(bookingDataVo));
	} catch (Exception e) {
	    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
	    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
	    log.error(e);
	    return false;
	}
	return true;
    }


}
