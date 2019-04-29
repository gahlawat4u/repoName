package com.gms.xms.workflow.task2.webship.history.tnt;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.piece.IPieceService;
import com.gms.xms.persistence.service.piece.PieceServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentProductDetailService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentProductDetailServiceImp;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentProductDetailVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.HistoryHelperUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from PrepareDataViewPackingListTask
 * <p>
 * Author TANDT
 */
public class PrepareDataViewPackingListTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataViewPackingListTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            HistoryViewFileRequest request = context.get(Attributes.HISTORY_VIEW_AIRBILL_REQUEST);
            Long shipmentId = request.getShipmentId();

            HistoryDetailFilter filter = HistoryHelperUtils.prepareHistoryDetailFilter(shipmentId);
            IHistoryDetailService detailService = new HistoryDetailServiceImp();
            HistoryDetailInfoVo historyDetailInfo = detailService.selectHistoryDetailInfo(filter);
            List<ShipmentProductDetailVo> shipmentProductDetailVos = new ArrayList<ShipmentProductDetailVo>();
            IShipmentProductDetailService smpService = new ShipmentProductDetailServiceImp();
            shipmentProductDetailVos = smpService.selectByShipment(shipmentId);
            List<PieceVo> pieceVos = new ArrayList<PieceVo>();
            IPieceService pService = new PieceServiceImp();
            pieceVos = pService.getPiecesByShipmentId(shipmentId);

            Integer totalQuantity = 0;
            Integer totalPiece = 0;
            for (ShipmentProductDetailVo smpPiece : shipmentProductDetailVos) {
                totalPiece = totalPiece + smpPiece.getNoOfCarton();
                totalQuantity = totalQuantity + smpPiece.getQty();
            }

            Double totalNetWeight = 0D;
            Double totalGrossWeight = 0D;
            for (PieceVo pieceVo : pieceVos) {
                totalNetWeight = totalNetWeight + pieceVo.getDeadWeight();
                totalGrossWeight = totalGrossWeight + pieceVo.getWeight();
            }

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("detailInfoModel", historyDetailInfo);
            data.put("shipmentProductDetailVos", shipmentProductDetailVos);
            data.put("totalQuantity", totalQuantity);
            data.put("totalPiece", totalPiece);
            data.put("totalNetWeight", totalNetWeight);
            data.put("totalGrossWeight", totalGrossWeight);
            data.put("pieces", pieceVos);
            data.put("isThermalLable", "0");

            context.put(Attributes.VIEW_AIRBILL_DATA, data);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
