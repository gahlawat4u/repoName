package com.gms.xms.workflow.task2.toll.tracking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.TrackingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryDetailFilterModel;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from LoadTollTrackingDataTask
 * <p>
 * Author TANDT
 */
public class LoadTollTrackingDataTask implements Task2 {

    private static final Log log = LogFactory.getLog(LoadTollTrackingDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get tracking response.
            List<TrackingModel> trackingModels = context.get(Attributes.TRACKING_RESPONSE);
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            // Create HistoryDetailInfo.
            HistoryDetailInfoVo detailInfoVo = new HistoryDetailInfoVo();
            // Create TrackingVo.
            List<TrackingVo> trackingVos = new ArrayList<TrackingVo>();
            if (trackingModels != null) {
                trackingVos = ModelUtils.createListVoFromModel(trackingModels, TrackingVo.class);
                detailInfoVo = prepareHistoryDetail(String.valueOf(shipmentId));
                context.put(Attributes.TRACKING_LIST_RESULT, trackingVos);
                context.put(Attributes.HISTORY_DETAIL_INFO_VO, detailInfoVo);
            } else {
                String errorMsg = "TNT Tracking Connection Error";
                errorMsg += "\n" + "There was an error while trying your shipment.";
                throw new Exception(errorMsg);
            }
        } catch (

                Exception e)

        {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;

    }

    protected HistoryDetailInfoVo prepareHistoryDetail(String shipmentId) throws Exception {
        HistoryDetailFilterModel detailFilterModelN = new HistoryDetailFilterModel();
        detailFilterModelN.setShipmentId(shipmentId);
        detailFilterModelN.setLbToKg("0.45359237");
        detailFilterModelN.setInToCm("2.54");
        detailFilterModelN.setWeightValue("5000");
        IHistoryDetailService detailService = new HistoryDetailServiceImp();
        HistoryDetailInfoModel historyDetailInfoModelN = new HistoryDetailInfoModel();
        HistoryDetailFilter filter = new HistoryDetailFilter();
        filter = ModelUtils.createVoFromModel(detailFilterModelN, HistoryDetailFilter.class);
        historyDetailInfoModelN = ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class);
        detailFilterModelN.setWeightValue(ShipmentUtils.getForceVolWeight(Integer.parseInt(historyDetailInfoModelN.getServiceId())).toString());

        filter = ModelUtils.createVoFromModel(detailFilterModelN, HistoryDetailFilter.class);
        HistoryDetailInfoVo detailInfoVo = detailService.selectHistoryDetailInfo(filter);
        return detailInfoVo;
    }

}
