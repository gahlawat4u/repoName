package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.workflow.client.integration.response.BookingShipmentResponse;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.utils.ShipmentUtils;

import java.util.Map;

/**
 * Posted from BookingShipmentClient
 * <p>
 * Author TANDT
 */
public class BookingShipmentClient extends WorkflowBaseClient {
    public BookingShipmentClient(Map<String, String> context) {
        super(context);
    }

    public BookingShipmentResponse bookShipment(ShipmentRequestVo request) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        BookingShipmentResponse response = new BookingShipmentResponse();
        ShipmentInfoVo shipmentInfoVo = request.getShipmentInfo();
        Integer bound = ShipmentUtils.getBound(shipmentInfoVo.getSenderAddress().getCountry(), shipmentInfoVo.getReceiverAddress().getCountry());
        shipmentInfoVo.setBound(bound);

        if (shipmentInfoVo.getContentType().equalsIgnoreCase("DOX")) {
            shipmentInfoVo.setContents(0);
        } else {
            shipmentInfoVo.setContents(1);
        }

        BookingDataVo bookingDataVo = new BookingDataVo();
        context.put(Attributes.BOOKING_DATA, GsonUtils.toGson(bookingDataVo));

        if (request.getScheduleCollection() != null) {
            context.put(Attributes.SCHEDULECOLLECTION_VO, GsonUtils.toGson(request.getScheduleCollection()));
        } else {
            context.put(Attributes.SCHEDULECOLLECTION_VO, "");
        }
        context.put(Attributes.SHIPMENT_REQUEST_VO, GsonUtils.toGson(request));
        context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
        context.put(Attributes.QUOTE_VO, GsonUtils.toGson(request.getQuote()));
        String serviceId = String.valueOf(shipmentInfoVo.getServiceId());
        context.put(Attributes.USER_LOGGIN_INFO, GsonUtils.toGson(request.getWebshipLogin()));
        switch (serviceId) {
            case "15":
                context.put(Attributes.WFP_NAME, "Wfl-GetDhlDomesticBooking");
                break;
            case "1":
                context.put(Attributes.WFP_NAME, "Wfl-GetDhlIntlBooking");
                break;
            case "54":
                context.put(Attributes.WFP_NAME, "Wfl-GetTntIntlBooking");
                break;
            case "3":
                context.put(Attributes.WFP_NAME, "Wfl-GetTntDomesticBooking");
                break;
            case "59":
                context.put(Attributes.WFP_NAME, "Wfl-GetTollIpecBooking");
                break;
            case "16":
                context.put(Attributes.WFP_NAME, "Wfl-GetHiTransBooking");
                break;
            case "17":
                context.put(Attributes.WFP_NAME, "Wfl-GetCouriersPleaseBooking");
                break;
            case "18":
                context.put(Attributes.WFP_NAME, "Wfl-GetDirectCouriersBooking");
                break;
            case "19":
                context.put(Attributes.WFP_NAME, "Wfl-GetCapitalTransportBooking");
                break;
            case "20":
                context.put(Attributes.WFP_NAME, "Wfl-GetFastwayBooking");
                break;
            case "26":
                context.put(Attributes.WFP_NAME, "Wfl-GetFollowmontTransportBooking");
                break;
            case "27":
                context.put(Attributes.WFP_NAME, "Wfl-GetCopeTransportBooking");
                break;
            case "36":
                context.put(Attributes.WFP_NAME, "Wfl-GetNorthlineBooking");
                break;
            case "52":
                context.put(Attributes.WFP_NAME, "Wfl-GetTollPriorityBooking");
                break;
            case "400":
                context.put(Attributes.WFP_NAME, "Wfl-UpsBooking");
                break;   
            default:
                response.setErrorCode(ErrorCode.ERROR);
                response.setErrorMessage("This service is under construction. Please try again later.");
                return response;
        }
        context = WorkFlowManager.getInstance().process(context);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        if (response.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
            BookingDataVo bookingDataResultVo = GsonUtils.fromGson(context.get(Attributes.BOOKING_DATA), BookingDataVo.class);
            bookingDataResultVo.setShipmentInfoVo(shipmentInfoVo);
            response.setBookingDataVo(bookingDataResultVo);
        }

        return response;
    }
}