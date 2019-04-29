package com.gms.xms.workflow.task2.tracking.startrack;

import com.gms.delivery.startrack.xmlpi.tracking.response.Consignment;
import com.gms.delivery.startrack.xmlpi.tracking.response.GetConsignmentDetailResponse;
import com.gms.delivery.startrack.xmlpi.tracking.response.TrackingEvents;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.tracking.startrack.StarTrackTrackingEventModel;
import com.gms.xms.model.tracking.startrack.StarTrackTrackingModel;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.weight.BaseGrossWeight;
import com.gms.xms.workflow.utils.weight.GrossWeightFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * Posted from Jun 25, 2016 9:22:11 AM
 * <p>
 * Author dattrinh
 */
public class GetTrackingInfoTask implements Task2 {

    private static final Log log = LogFactory.getLog(GetTrackingInfoTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // 1. Get response tracking result.
            GetConsignmentDetailResponse trackingObject = context.get(Attributes.STARTRACK_TRACKING_RESULT);
            if (trackingObject == null) {
                throw new CustomException("There isn't tracking info for this airbill.");
            }
            // 2. Get tracking info.
            Consignment consignment = trackingObject.getConsignment();
            List<TrackingEvents> trackingEvents = consignment.getTrackingEvents();
            StarTrackTrackingModel result = new StarTrackTrackingModel();
            String deliveryDate = null;
            String finalStatus = null;
            int count = 0;
            List<StarTrackTrackingEventModel> eventList = new LinkedList<StarTrackTrackingEventModel>();
            StarTrackTrackingEventModel eventModel;
            Map<String, String> transitDesc = createTransitDescMap();
            for (TrackingEvents event : trackingEvents) {
                String transitState = transitDesc.get(event.getTransitState());
                String location = event.getScanningDepot();
                String dateTime = event.getEventDateTime();
                String signatoryName = event.getSignatoryName();
                String signatoryStr = "";
                if (!StringUtils.isBlank(signatoryName)) {
                    signatoryStr = ". Signatory by " + signatoryName;
                }
                Integer quantityDelivered = event.getQuantityDelivered();
                Integer quantityOnHand = event.getQuantityOnHand();
                String quantityStr = "";
                if (quantityDelivered > 0 || quantityOnHand > 0) {
                    if ("D".equalsIgnoreCase(event.getTransitState())) {
                        quantityStr = String.valueOf(quantityDelivered) + " of " + String.valueOf(quantityOnHand) + " ";
                    } else {
                        quantityStr = String.valueOf(quantityDelivered) + " item(s) ";
                    }
                }
                count++;
                if (count == 1) {
                    finalStatus = transitState;
                    deliveryDate = dateTime;
                }
                // Create new tracking event model.
                eventModel = new StarTrackTrackingEventModel();
                eventModel.setDateTime(this.convertGregorian2String(dateTime));
                eventModel.setLocation(location);
                eventModel.setDescription(quantityStr + transitState + signatoryStr);
                eventList.add(eventModel);
            }
            result.setEvents(eventList);
            // Get shipment info.
            ShipmentVo shipmentVo = context.get(Attributes.SHIPMENT_INFO_VO);
            // Get pieces info.
            PieceDao pieceDao = new PieceDao();
            List<PieceVo> pieceVos = pieceDao.selectByShipmentId(shipmentVo.getShipmentId());
            // Get tracking number.
            result.setTrackingNumber(consignment.getId());
            // Get shipped by.
            result.setShippedBy(consignment.getSender().getName());
            // Get destination.
            result.setDestination(consignment.getReceiver().getContactDetails().getAddress().getSuburbOrLocation());
            // Get ship date.
            result.setShipDate(DateUtils.convertDateToString(shipmentVo.getShipmentDate(), "dd-MM-yyyy", null));
            // Get consignee.
            result.setConsignee(consignment.getReceiver().getName().get(0));
            // Get delivery date.
            result.setDeliveryDate(this.convertGregorian2String(deliveryDate));
            // Get service type.
            result.setServiceType(this.getServiceType(consignment.getServiceCode()));
            // Get weight.
            result.setWeight(this.getWeight(shipmentVo, pieceVos));
            // Get dimension weight.
            result.setDimensionWeight(this.getDimensionWeight(shipmentVo, pieceVos));
            // Get status.
            result.setStatus(finalStatus);
            // Put result into the context.
            context.put(Attributes.STARTRACK_TRACKING_INFO, result);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    private Map<String, String> createTransitDescMap() {
        Map<String, String> transitDesc = new HashMap<String, String>();
        transitDesc.put("AT", "POD Attachment");
        transitDesc.put("B", "Booked in for Delivery");
        transitDesc.put("C", "Late Data");
        transitDesc.put("D", "Delivered");
        transitDesc.put("E", "Pickup Cancelled");
        transitDesc.put("F", "Final Shortage");
        transitDesc.put("G", "Refused - Pending further instructions");
        transitDesc.put("H", "Held");
        transitDesc.put("I", "Scanned in Transit");
        transitDesc.put("IM", "POD Image");
        transitDesc.put("J", "Held at Delivery Depot");
        transitDesc.put("L", "Label Scanned In Transit");
        transitDesc.put("M", "On Board for Delivery");
        transitDesc.put("N", "NZ Scanning");
        transitDesc.put("O", "POD On File");
        transitDesc.put("P", "Picked Up");
        transitDesc.put("Q", "Truck Out");
        transitDesc.put("QC", "Inspection Quality Control");
        transitDesc.put("R", "Unsuccessful Delivery");
        transitDesc.put("S", "Shortage");
        transitDesc.put("T", "POD Returned");
        transitDesc.put("U", "Left as Instructed");
        transitDesc.put("V", "Redeliver");
        transitDesc.put("W", "Transfer");
        transitDesc.put("X", "Reconsigned");
        transitDesc.put("Y", "Returned To Sender");
        transitDesc.put("Z", "Registered For Bookin");
        return transitDesc;
    }

    private String getWeight(ShipmentVo shipmentVo, List<PieceVo> pieceVos) throws Exception {
        if (pieceVos == null || pieceVos.size() == 0) {
            return "";
        }
        BaseGrossWeight grossWeight = GrossWeightFactory.getGrossWeight(72, shipmentVo.getShipmentTypeId());
        Double quoteWeight = grossWeight.getQuoteWeight(pieceVos, shipmentVo.getDimensionUnit(), shipmentVo.getWeightUnit());
        String weightUnit = StringUtils.isBlank(shipmentVo.getWeightUnit()) ? "" : " " + shipmentVo.getWeightUnit() + "(s)";
        return String.valueOf(quoteWeight.intValue()) + weightUnit;
    }

    private String getServiceType(String serviceCode) throws Exception {
        ShipmentTypeDao smtDao = new ShipmentTypeDao();
        ShipmentTypeFilter filter = new ShipmentTypeFilter();
        filter.setServiceCode(serviceCode);
        filter.setServiceId(72);
        ShipmentTypeVo shipmentTypeVo = smtDao.selectByServiceCodeAndCarrier(filter);
        return shipmentTypeVo != null ? shipmentTypeVo.getShipmentTypeName() : "";
    }

    private String getDimensionWeight(ShipmentVo shipmentVo, List<PieceVo> pieceVos) throws Exception {
        if (pieceVos == null || pieceVos.size() == 0) {
            return "";
        }
        if (shipmentVo.getContents() != 1) {
            return null;
        }
        BaseGrossWeight grossWeight = GrossWeightFactory.getGrossWeight(72, shipmentVo.getShipmentTypeId());
        Double totalGrossWeight = 0.0;
        totalGrossWeight = grossWeight.getTotalGrossWeight(pieceVos, shipmentVo.getDimensionUnit());
        return String.valueOf(totalGrossWeight.intValue()) + " " + shipmentVo.getWeightUnit() + "(s)";
    }

    private String convertGregorian2String(String gregorianDate) {
        Date deliveryTime = DateUtils.convertStringToDate(gregorianDate, "yyyy-MM-dd'T'HH:mm:ssX", null);
        return DateUtils.convertDateToString(deliveryTime, "dd-MM-yyyy HH:mm:ss", null);
    }
}
