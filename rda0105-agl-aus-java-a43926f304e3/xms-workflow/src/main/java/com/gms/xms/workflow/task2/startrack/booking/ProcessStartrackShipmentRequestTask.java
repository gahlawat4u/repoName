package com.gms.xms.workflow.task2.startrack.booking;

import com.gms.delivery.startrack.service.StarTrackBookingService;
import com.gms.delivery.startrack.xmlpi.booking.tempuri.CreateConnoteBinaryResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.startrack.StartrackShipmentRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jun 15, 2016 3:04:57 PM
 * <p>
 * Author huynd
 */
public class ProcessStartrackShipmentRequestTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessStartrackShipmentRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            StartrackShipmentRequestVo startrackShipmentRequestVo = context.get(Attributes.STARTRACK_SHIPMENT_REQUEST_VO);
            ShipmentRequestVo shipmentRequestPage2 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE2);
            BookingDataVo bookingDataVo = context.get(Attributes.BOOKING_DATA_VO);
            ShipmentVo shipmentVo = bookingDataVo.getShipmentVo();
            StarTrackBookingService startrackShipmentService = new StarTrackBookingService();
            String xmlRequest = startrackShipmentService.prepareXmlRequest(startrackShipmentRequestVo);
            String xmlResponse = startrackShipmentService.execute(xmlRequest, null);
            if (xmlResponse == null) {
                throw new Exception("Connection failed!");
            }
            String errorMessage = startrackShipmentService.getErrorMessage(xmlResponse);
            if (!StringUtils.isBlank(errorMessage)) {
                String errMsg = "Error message:" + errorMessage;
                throw new Exception(errMsg);
            } else {
                CreateConnoteBinaryResponse document = startrackShipmentService.parseResponse(xmlResponse);
                String connotNumber = document.getCreateConnoteBinaryResult().getValue().getConnoteNumber().getValue();
                String labelsPDF = AppUtils.byteArray2String(document.getCreateConnoteBinaryResult().getValue().getLabelsPDF().getValue());
                String manifestPDF = AppUtils.byteArray2String(document.getCreateConnoteBinaryResult().getValue().getManifestPDF().getValue());
                shipmentVo.setAirbillNumber(connotNumber);
                shipmentVo.setLabelPdf(labelsPDF);
                shipmentVo.setManifestPdf(manifestPDF);
            }
            // Set back the shipment vo to booking data to save
            bookingDataVo.setShipmentProductDetail(shipmentRequestPage2.getShipmentProductDetails());
            context.put(Attributes.BOOKING_DATA_VO, bookingDataVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
