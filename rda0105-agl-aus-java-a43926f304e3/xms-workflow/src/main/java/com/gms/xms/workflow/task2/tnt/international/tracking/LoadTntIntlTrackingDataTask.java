package com.gms.xms.workflow.task2.tnt.international.tracking;

import com.gms.delivery.tnt.xmlpi.tracking.response.StatusStructure;
import com.gms.delivery.tnt.xmlpi.tracking.response.TrackResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.DateUtils;
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
import java.util.Date;
import java.util.List;

/**
 * Posted from DoTntIntlTrackingRequestTask
 * <p>
 * Author dattrinh Feb 1, 2016 11:57:19 AM
 */
public class LoadTntIntlTrackingDataTask implements Task2 {

    private static final Log log = LogFactory.getLog(LoadTntIntlTrackingDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get tracking response.
            TrackResponse trackResponse = context.get(Attributes.TRACKING_RESPONSE);
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            // Create HistoryDetailInfo.
            HistoryDetailInfoVo detailInfoVo = new HistoryDetailInfoVo();
            // Create TrackingVo.
            List<TrackingVo> trackingVos = new ArrayList<TrackingVo>();
            if (trackResponse != null) {
                // Get detail info.
                detailInfoVo = prepareHistoryDetail(String.valueOf(shipmentId));
                // Get tracking list info.
                List<StatusStructure> shipmentEvents = trackResponse.getConsignment().get(0).getStatusData();
                if (shipmentEvents.size() > 0) {
                    for (StatusStructure shipmentEvent : shipmentEvents) {
                        TrackingVo trackingVo = new TrackingVo();
                        trackingVo.setAirbillNumber(detailInfoVo.getAirbillNumber());
                        trackingVo.setConsigneeName("");
                        trackingVo.setShipmentDate(detailInfoVo.getShipmentDate());
                        trackingVo.setWeight(detailInfoVo.getActualWeight());
                        trackingVo.setWeightUnit(detailInfoVo.getWeightUnit());
                        trackingVo.setServiceType(detailInfoVo.getServiceType());
                        trackingVo.setDestinationServiceArea(detailInfoVo.getServiceAreaCodeDestination());
                        trackingVo.setOriginServiceArea(shipmentEvent.getDepotName());
                        Date trackDate = DateUtils.convertStringToDate(shipmentEvent.getLocalEventDate().getValue(), "yyyyMMdd", null);
                        trackingVo.setTrackDate(trackDate);
                        String trackTime = shipmentEvent.getLocalEventTime().getValue();
                        trackTime = trackTime.substring(0, 2) + ":" + trackTime.substring(2, 4) + ":00";
                        trackingVo.setTrackTime(trackTime);
                        trackingVo.setEventDescription(shipmentEvent.getStatusDescription());
                        trackingVo.setEventCode(shipmentEvent.getStatusCode());
                        trackingVos.add(trackingVo);
                    }
                }
                detailInfoVo.setrContactName(trackResponse.getConsignment().get(0).getSignatory());
                detailInfoVo.setrCountryName(trackResponse.getConsignment().get(0).getDestinationCountry().getCountryName());
                context.put(Attributes.TRACKING_LIST_RESULT, trackingVos);
                context.put(Attributes.HISTORY_DETAIL_INFO_VO, detailInfoVo);
            } else {
                String errorMsg = "TNT Tracking Connection Error";
                errorMsg += "\n" + "There was an error while trying your shipment.";
                throw new Exception(errorMsg);
            }
        } catch (Exception e) {
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
