package com.gms.xms.workflow.task2.dhl.tracking;

import com.gms.delivery.dhl.xmlpi.datatype.tracking.response.Condition;
import com.gms.delivery.dhl.xmlpi.datatype.tracking.response.ErrorResponse;
import com.gms.delivery.dhl.xmlpi.datatype.tracking.response.ShipmentEvent;
import com.gms.delivery.dhl.xmlpi.datatype.tracking.response.TrackingResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.dto.delivery.dhl.DhlTrackingResponseVo;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from LoadTrackingDataTask
 * <p>
 * Author dattrinh Feb 1, 2016 4:31:41 PM
 */
public class LoadDhlTrackingDataTask implements Task2 {

    private static final Log log = LogFactory.getLog(LoadDhlTrackingDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get Dhl Tracking Response.
            TrackingResponse trackingResponse = context.get(Attributes.TRACKING_RESPONSE);
            if (trackingResponse == null) {
                String errorMsg = "DHL Tracking Connection Error";
                errorMsg += "\n" + "There was an error while trying your shipment.";
                throw new Exception(errorMsg);
            } else {
                ErrorResponse response = trackingResponse.getResponse();
                // If there is an error.
                if (response.getStatus() != null && "Failure".equalsIgnoreCase(response.getStatus().getActionStatus())) {
                    String errorMsg = "";
                    List<Condition> conditions = response.getStatus().getCondition();
                    if (conditions != null && conditions.size() > 0) {
                        for (Condition condition : conditions) {
                            errorMsg += condition.getConditionCode() + " - " + condition.getConditionData() + "\n";
                        }
                    }
                    throw new Exception(errorMsg);
                } else if (response.getStatus() == null && trackingResponse.getAWBInfo().size() > 0 && trackingResponse.getAWBInfo().get(0).getStatus() != null && !"SUCCESS".equalsIgnoreCase(trackingResponse.getAWBInfo().get(0).getStatus().getActionStatus())) {
                    String errorMsg = "";
                    List<Condition> conditions = trackingResponse.getAWBInfo().get(0).getStatus().getCondition();
                    if (conditions != null && conditions.size() > 0) {
                        for (Condition condition : conditions) {
                            errorMsg += condition.getConditionCode() + " - " + condition.getConditionData() + "\n";
                        }
                    }
                    throw new Exception(errorMsg);
                } else { // If there isn't error.
                    // Create Dhl Tracking Response.
                    DhlTrackingResponseVo trackingResponseVo = new DhlTrackingResponseVo();
                    HistoryDetailInfoVo detailInfoVo = new HistoryDetailInfoVo();
                    List<TrackingVo> trackingList = new ArrayList<TrackingVo>();
                    // Get shipment detail.
                    ShipmentVo shipmentVo = context.get(Attributes.SHIPMENT_INFO_VO);
                    // Get detail info.
                    detailInfoVo.setTracking(trackingResponse.getAWBInfo().get(0).getAWBNumber());
                    detailInfoVo.setsCompanyName(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getShipperName());
                    detailInfoVo.setContentDescription(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getDestinationServiceArea().getDescription());
                    // Get service type.
                    IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
                    ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentVo.getShipmentTypeId());
                    detailInfoVo.setServiceType(shipmentTypeVo.getShipmentTypeName());
                    detailInfoVo.setrCompanyName(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getConsigneeName());
                    detailInfoVo.setShipmentDate(shipmentVo.getShipmentDate());
                    detailInfoVo.setStatus(shipmentVo.getStatus());
                    detailInfoVo.setWeightUnit(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getWeightUnit());
                    detailInfoVo.setActualWeight(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getWeight());
                    // Get tracking list info.
                    List<ShipmentEvent> shipmentEvents = trackingResponse.getAWBInfo().get(0).getShipmentInfo().getShipmentEvent();
                    if (shipmentEvents.size() > 0) {
                        for (ShipmentEvent shipmentEvent : shipmentEvents) {
                            TrackingVo trackingVo = new TrackingVo();
                            if (trackingResponse.getAWBInfo().get(0).getAWBNumber() != null) {
                                trackingVo.setAirbillNumber(trackingResponse.getAWBInfo().get(0).getAWBNumber());
                            } else {
                                trackingVo.setAirbillNumber("");
                            }
                            if (trackingResponse.getAWBInfo().get(0).getShipmentInfo().getConsigneeName() != null) {
                                trackingVo.setConsigneeName(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getConsigneeName());
                            }
                            if (trackingResponse.getAWBInfo().get(0).getShipmentInfo().getShipmentDate().toString() != null) {
                                Date trackShipmentDate = DateUtils.convertXMLGregorianCalendar2Date(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getShipmentDate());
                                trackingVo.setShipmentDate(trackShipmentDate);
                            }
                            if (trackingResponse.getAWBInfo().get(0).getShipmentInfo().getWeight() != null) {
                                trackingVo.setWeight(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getWeight());
                            }
                            if (trackingResponse.getAWBInfo().get(0).getShipmentInfo().getWeightUnit() != null) {
                                trackingVo.setWeightUnit(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getWeightUnit());
                            }
                            trackingVo.setServiceType(shipmentTypeVo.getShipmentTypeName());
                            if (trackingResponse.getAWBInfo().get(0).getShipmentInfo().getDestinationServiceArea().getDescription() != null) {
                                trackingVo.setDestinationServiceArea(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getDestinationServiceArea().getDescription());
                            }
                            if (shipmentEvent.getServiceArea() != null) {
                                trackingVo.setOriginServiceArea(shipmentEvent.getServiceArea().getDescription());
                            }
                            if (shipmentEvent.getDate().toString() != null) {
                                Date trackDate = DateUtils.convertXMLGregorianCalendar2Date(shipmentEvent.getDate());
                                trackingVo.setTrackDate(trackDate);
                            }
                            if (shipmentEvent.getTime().toString() != null) {
                                trackingVo.setTrackTime(shipmentEvent.getTime().toString());
                            }
                            if (shipmentEvent.getServiceEvent().getDescription() != null) {
                                trackingVo.setEventDescription(shipmentEvent.getServiceEvent().getDescription());
                            }
                            trackingList.add(trackingVo);
                        }
                    }
                    // Put History Detail into the context.
                    trackingResponseVo.setDetailInfo(detailInfoVo);
                    trackingResponseVo.setTrackingList(trackingList);
                    context.put(Attributes.TRACKING_RESPONSE, trackingResponseVo);
                }
            }
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
