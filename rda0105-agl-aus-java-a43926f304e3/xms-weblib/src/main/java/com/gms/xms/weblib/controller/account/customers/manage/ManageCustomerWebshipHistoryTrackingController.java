package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.DeliveredTrackingListFilterModel;
import com.gms.xms.model.TrackingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryDetailFilterModel;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.txndb.vo.DeliveredTrackingListFilter;
import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.HistoryTrackingServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.service.webship.history.IHistoryTrackingService;
import com.gms.xms.workflow.utils.ShipmentUtils;

import java.util.List;

/**
 * Posted from ManageCustomerWebshipHistoryTrackingController
 * <p>
 * Author TANDT 06-11-2015
 */
public class ManageCustomerWebshipHistoryTrackingController extends JsonBaseController {

    /**
     *
     */
    private static final long serialVersionUID = 8620576103161424236L;
    private String shipmentId;
    private DeliveredTrackingListFilterModel deliveredTrackingFilter;
    private List<TrackingModel> trackingModels;
    private HistoryDetailFilterModel historyDetailFilterModel;
    private HistoryDetailInfoModel detailInfoModel;

    public String tracking() {
        this.setPageTitle("Tracking");
        try {
            prepareShipmentDetail();
            preparetrackingModel();
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError("No Shipments Found For AWBNumber ".concat(detailInfoModel.getTracking()));
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    protected void preparetrackingModel() throws Exception {
        IHistoryTrackingService iService = new HistoryTrackingServiceImp();
        prepareDeliveredTrackingFilter();
        DeliveredTrackingListFilter filter = ModelUtils.createVoFromModel(deliveredTrackingFilter, DeliveredTrackingListFilter.class);
        List<TrackingVo> listTracking = iService.trackingResult(filter);
        this.setTrackingModels(ModelUtils.createListModelFromVo(listTracking, TrackingModel.class));
    }

    protected void prepareShipmentDetail() throws Exception {
        IHistoryTrackingService iService = new HistoryTrackingServiceImp();
        prepareShipmentDetailFilter();
        HistoryDetailFilter filter = ModelUtils.createVoFromModel(this.getHistoryDetailFilterModel(), HistoryDetailFilter.class);
        this.setDetailInfoModel(ModelUtils.createModelFromVo(iService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class));
    }

    protected void prepareShipmentDetailFilter() throws Exception {

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
        this.setHistoryDetailFilterModel(detailFilterModelN);

    }

    protected void prepareDeliveredTrackingFilter() {
        DeliveredTrackingListFilterModel filter = new DeliveredTrackingListFilterModel();
        filter.setShipmentId(shipmentId);
        filter.setDay(SystemSettingCache.getInstance().getValueByKey(AppConstants.DOWNLOAD_DHL_CHECKING_START_DAY));
        filter.setIsAll("all");
        this.setDeliveredTrackingFilter(filter);
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public DeliveredTrackingListFilterModel getDeliveredTrackingFilter() {
        return deliveredTrackingFilter;
    }

    public void setDeliveredTrackingFilter(DeliveredTrackingListFilterModel deliveredTrackingFilter) {
        this.deliveredTrackingFilter = deliveredTrackingFilter;
    }

    public List<TrackingModel> getTrackingModels() {
        return trackingModels;
    }

    public void setTrackingModels(List<TrackingModel> trackingModels) {
        this.trackingModels = trackingModels;
    }

    public HistoryDetailFilterModel getHistoryDetailFilterModel() {
        return historyDetailFilterModel;
    }

    public void setHistoryDetailFilterModel(HistoryDetailFilterModel historyDetailFilterModel) {
        this.historyDetailFilterModel = historyDetailFilterModel;
    }

    public HistoryDetailInfoModel getDetailInfoModel() {
        return detailInfoModel;
    }

    public void setDetailInfoModel(HistoryDetailInfoModel detailInfoModel) {
        this.detailInfoModel = detailInfoModel;
    }

}