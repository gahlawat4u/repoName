package com.gms.xms.weblib.controller.webship.history;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.dhl.DhlTrackingResponseVo;
import com.gms.xms.model.TrackingModel;
import com.gms.xms.model.tracking.startrack.StarTrackTrackingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.List;

/**
 * Posted from HistoryTrackingController
 * <p>
 * Author TanDT Date Jul 9, 2015
 */
public class HistoryTrackingController extends JsonBaseController {

    /**
     *
     */
    private static final long serialVersionUID = -7901889480717968757L;

    private String shipmentId;
    private List<TrackingModel> trackingModels;
    private HistoryDetailInfoModel detailInfoModel;
    private StarTrackTrackingModel starTrack;

    public String tracking() {
        this.setPageTitle("Tracking");
        try {
            IServiceService service = new ServiceServiceImp();
            ServiceVo serviceVo = service.selectAllByShipmentId(Long.parseLong(shipmentId));
            switch (serviceVo.getServiceId()) {
                case 1:
                case 15:
                    dhlTracking();
                    break;
                case 54:
                    tntInltTracking();
                    break;
                case 3:
                    tntDomTracking();
                    break;
                case 52:
                case 59:
                    tollTracking();
                    break;
                case 400:
                    upsTracking();
                    break;    
                case 72:
                    starTrackTracking();
                    return "star_track";
                default:
                    throw new Exception("This service wasn't supported. Please contact the administrator for helping.");
            }
        } catch (Exception e) {
            // Set error code.
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            // Set error message.
            this.setErrorMessage(e.getMessage());
            // Set action error.
            this.addActionError(e.getMessage());
            // Log error.
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    protected void starTrackTracking() throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfoMap());
        context.put(Attributes.SHIPMENT_ID, Long.valueOf(this.getShipmentId()));
        context.put(Attributes.LANG_CODE, this.getXmsLang());
        context.put(Attributes.WFP_NAME, "Wfl-StartrackTrackingAirbill");
        context = WorkFlowManager2.getInstance().process(context);
        if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
            throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
        } else {
            StarTrackTrackingModel trackingInfo = context.get(Attributes.STARTRACK_TRACKING_INFO);
            this.setStarTrack(trackingInfo);
        }
    }

    protected void upsTracking() throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfoMap());
        context.put(Attributes.SHIPMENT_ID, Long.valueOf(this.getShipmentId()));
        context.put(Attributes.LANG_CODE, this.getXmsLang());
        context.put(Attributes.WFP_NAME, "Wfl-UpsTrackingAirbill");
        context = WorkFlowManager2.getInstance().process(context);
        if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
            throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
        } else {
            DhlTrackingResponseVo trackingResponseVo = context.get(Attributes.TRACKING_RESPONSE);
            HistoryDetailInfoModel detailInfoModel = ModelUtils.createModelFromVo(trackingResponseVo.getDetailInfo(), HistoryDetailInfoModel.class);
            List<TrackingModel> trackingModels = ModelUtils.createListModelFromVo(trackingResponseVo.getTrackingList(), TrackingModel.class);
            this.setDetailInfoModel(detailInfoModel);
            this.setTrackingModels(trackingModels);
        }
    }
    
    protected void dhlTracking() throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfoMap());
        context.put(Attributes.SHIPMENT_ID, Long.valueOf(this.getShipmentId()));
        context.put(Attributes.LANG_CODE, this.getXmsLang());
        context.put(Attributes.WFP_NAME, "Wfl-DhlTrackingAirbill");
        context = WorkFlowManager2.getInstance().process(context);
        if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
            throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
        } else {
            DhlTrackingResponseVo trackingResponseVo = context.get(Attributes.TRACKING_RESPONSE);
            HistoryDetailInfoModel detailInfoModel = ModelUtils.createModelFromVo(trackingResponseVo.getDetailInfo(), HistoryDetailInfoModel.class);
            List<TrackingModel> trackingModels = ModelUtils.createListModelFromVo(trackingResponseVo.getTrackingList(), TrackingModel.class);
            this.setDetailInfoModel(detailInfoModel);
            this.setTrackingModels(trackingModels);
        }
    }


    protected void tntInltTracking() throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfoMap());
        context.put(Attributes.SHIPMENT_ID, Long.parseLong(this.getShipmentId()));
        context.put(Attributes.WFP_NAME, "Wfl-TntIntlTrackingAirbill");
        context = WorkFlowManager2.getInstance().process(context);
        if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
            throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
        } else {
            HistoryDetailInfoVo detailInfoVo = context.get(Attributes.HISTORY_DETAIL_INFO_VO);
            detailInfoVo.setShipmentId(Long.parseLong(shipmentId));
            HistoryDetailInfoModel detailInfoModel = ModelUtils.createModelFromVo(detailInfoVo, HistoryDetailInfoModel.class);
            List<TrackingVo> trackingVos = context.get(Attributes.TRACKING_LIST_RESULT);

            this.setDetailInfoModel(detailInfoModel);
            this.setTrackingModels(ModelUtils.createListModelFromVo(trackingVos, TrackingModel.class));
        }
    }

    protected void tollTracking() throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfoMap());
        context.put(Attributes.SHIPMENT_ID, Long.parseLong(this.getShipmentId()));
        context.put(Attributes.WFP_NAME, "Wfl-TollTrackingAirbill");
        context = WorkFlowManager2.getInstance().process(context);
        if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
            throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
        } else {
            HistoryDetailInfoVo detailInfoVo = context.get(Attributes.HISTORY_DETAIL_INFO_VO);
            detailInfoVo.setShipmentId(Long.parseLong(shipmentId));
            HistoryDetailInfoModel detailInfoModel = ModelUtils.createModelFromVo(detailInfoVo, HistoryDetailInfoModel.class);
            List<TrackingVo> trackingVos = context.get(Attributes.TRACKING_LIST_RESULT);

            this.setDetailInfoModel(detailInfoModel);
            this.setTrackingModels(ModelUtils.createListModelFromVo(trackingVos, TrackingModel.class));
        }
    }

    protected void tntDomTracking() throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfoMap());
        context.put(Attributes.SHIPMENT_ID, Long.parseLong(this.getShipmentId()));
        context.put(Attributes.WFP_NAME, "Wfl-TntDomTrackingAirbill");
        context = WorkFlowManager2.getInstance().process(context);
        if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
            throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
        } else {
            HistoryDetailInfoVo detailInfoVo = context.get(Attributes.HISTORY_DETAIL_INFO_VO);
            detailInfoVo.setShipmentId(Long.parseLong(shipmentId));
            HistoryDetailInfoModel detailInfoModel = ModelUtils.createModelFromVo(detailInfoVo, HistoryDetailInfoModel.class);
            List<TrackingVo> trackingVos = context.get(Attributes.TRACKING_LIST_RESULT);

            this.setDetailInfoModel(detailInfoModel);
            this.setTrackingModels(ModelUtils.createListModelFromVo(trackingVos, TrackingModel.class));
        }
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public List<TrackingModel> getTrackingModels() {
        return trackingModels;
    }

    public void setTrackingModels(List<TrackingModel> trackingModels) {
        this.trackingModels = trackingModels;
    }

    public HistoryDetailInfoModel getDetailInfoModel() {
        return detailInfoModel;
    }

    public void setDetailInfoModel(HistoryDetailInfoModel detailInfoModel) {
        this.detailInfoModel = detailInfoModel;
    }

    public StarTrackTrackingModel getStarTrack() {
        return starTrack;
    }

    public void setStarTrack(StarTrackTrackingModel starTrack) {
        this.starTrack = starTrack;
    }
}