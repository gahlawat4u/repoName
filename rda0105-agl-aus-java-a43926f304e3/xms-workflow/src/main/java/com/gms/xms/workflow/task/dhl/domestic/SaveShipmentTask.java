package com.gms.xms.workflow.task.dhl.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.model.webship.ship.QuoteModel;
import com.gms.xms.txndb.vo.ShipmentDetailVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from SaveShipmentTask
 * <p>
 * Author TANDT
 */
public class SaveShipmentTask implements Task {
    private static final Log log = LogFactory.getLog(SaveShipmentTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentRequestVo shipmentRequestVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_REQUEST_VO), ShipmentRequestVo.class);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            WebshipLoginVo webshipLoginVo = GsonUtils.fromGson(context.get(Attributes.USER_LOGGIN_INFO), WebshipLoginVo.class);
            QuoteModel quote = GsonUtils.fromGson(context.get(Attributes.QUOTE_VO), QuoteModel.class);
            ShipmentVo shipmentVo = new ShipmentVo();

            Date currentDate = new Date();

            // Not null

            shipmentVo.setCustomerCode(0L);
            shipmentVo.setWebshipId(0L);

            shipmentVo.setAirbillNumber("");
            shipmentVo.setShipmentDate(currentDate);
            shipmentVo.setReceiverAddressId(0);
            shipmentVo.setShipmentTypeId(0);
            shipmentVo.setPackageId(0);
            shipmentVo.setWithInsurance(BigDecimal.valueOf(0D));
            shipmentVo.setCarrierCost(BigDecimal.valueOf(0D));
            shipmentVo.setDay(0);
            shipmentVo.setCurrencyCode("");
            shipmentVo.setSenderAddressId(0);
            shipmentVo.setReceiverAddressId(0);
            shipmentVo.setDimensionUnit("");
            shipmentVo.setWithInsurance(BigDecimal.valueOf(0D));
            shipmentVo.setShipmentTypeId(0);
            shipmentVo.setPackageId(0);
            shipmentVo.setContents(1);
            shipmentVo.setWeightUnit("");
            shipmentVo.setCarrierCost(BigDecimal.valueOf(0D));
            shipmentVo.setCarrierPayment(1);
            shipmentVo.setDhlNote("");
            shipmentVo.setBillingCode("");
            shipmentVo.setCourierMessage("");
            shipmentVo.setDhlRoutingCode("");
            shipmentVo.setAwbBarcode("");
            shipmentVo.setOriginDestiBarcode("");
            shipmentVo.setDhlRoutingBarcode("");
            shipmentVo.setProductContentCode("");
            shipmentVo.setStatus(0);
            shipmentVo.setContentDescription("");
            shipmentVo.setCommercialInvoiceId(0);
            shipmentVo.setCollectionTypeId(0);
            shipmentVo.setBillingType(0);
            shipmentVo.setBillingAccount("");
            shipmentVo.setDutiesType(0);
            shipmentVo.setDutiesAccount("");
            shipmentVo.setTermOfTrade("");
            shipmentVo.setServiceAreaCodeOrigin("");
            shipmentVo.setServiceAreaCodeDestination("");
            shipmentVo.setReceiverTaxId("");
            shipmentVo.setReasonForExport("");
            shipmentVo.setPackingList(1);
            shipmentVo.setBoundStatus(1);
            shipmentVo.setSalesRepId(0L);
            shipmentVo.setOldCustomerCode(0L);

            if (!StringUtils.isBlank(shipmentInfoVo.getReceiverTaxId())) {
                shipmentVo.setReceiverTaxId(shipmentInfoVo.getReceiverTaxId());
            }

            if (quote.getBaseCharge() != null) {
                String baseCharge = quote.getBaseCharge();
                shipmentVo.setBaseCharge(BigDecimal.valueOf(Double.parseDouble(baseCharge)));
            }
            if (shipmentInfoVo.getBillingCode() != null) {
                shipmentVo.setBillingCode(shipmentInfoVo.getBillingCode());
            }
            if (shipmentInfoVo.getBillingType() != null) {
                shipmentVo.setBillingType(shipmentInfoVo.getBillingType());
            }
            if (shipmentRequestVo.getContentDetail().getDescription() != null) {
                shipmentVo.setContentDescription(shipmentRequestVo.getContentDetail().getDescription());
            }
            if (shipmentInfoVo.getContents() != null) {
                shipmentVo.setContents(shipmentInfoVo.getContents());
            }

            if (shipmentInfoVo.getAirbillNumber() != null) {
                shipmentVo.setAirbillNumber(shipmentInfoVo.getAirbillNumber());
            }

            if (shipmentInfoVo.getAwbBarcode() != null) {
                shipmentVo.setAwbBarcode(shipmentInfoVo.getAwbBarcode());
            }

            if (shipmentInfoVo.getOriginDestiBarcode() != null) {
                shipmentVo.setOriginDestiBarcode(shipmentInfoVo.getOriginDestiBarcode());
            }

            if (shipmentInfoVo.getDhlRoutingBarcode() != null) {
                shipmentVo.setDhlRoutingBarcode(shipmentInfoVo.getDhlRoutingBarcode());
            }

            if (shipmentInfoVo.getCourierMessage() != null) {
                shipmentVo.setCourierMessage(shipmentInfoVo.getCourierMessage());
            }
            shipmentVo.setCreateDate(currentDate);

            if (webshipLoginVo.getCustomerCode() != null) {
                shipmentVo.setCustomerCode(webshipLoginVo.getCustomerCode());
            }
            if (webshipLoginVo.getWebshipId() != null) {
                shipmentVo.setWebshipId(webshipLoginVo.getWebshipId());
            }
            if (shipmentInfoVo.getPieces().size() > 0) {
                shipmentVo.setNoOfPieces(shipmentInfoVo.getPieces().size());
            }
            if (shipmentInfoVo.getPackageId() != null) {
                shipmentVo.setPackageId(shipmentInfoVo.getPackageId());
            }
            if (shipmentInfoVo.getZone() != null) {
                shipmentVo.setZone(shipmentInfoVo.getZone());
            }

            if (shipmentInfoVo.getReference() != null) {
                shipmentVo.setReference(shipmentInfoVo.getReference());
            }
            if (shipmentInfoVo.getShipmentTypeId() != null) {
                shipmentVo.setShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
            }
            if (shipmentInfoVo.getReceiverAddressId() != null) {
                shipmentVo.setReceiverAddressId(shipmentInfoVo.getReceiverAddressId());
            }
            if (shipmentInfoVo.getTermOfTrade() != null) {
                shipmentVo.setTermOfTrade(shipmentInfoVo.getTermOfTrade());
            }
            if (shipmentInfoVo.getWeightUnit() != null) {
                shipmentVo.setWeightUnit(shipmentInfoVo.getWeightUnit());
            }
            if (shipmentInfoVo.getDimensionUnit() != null) {
                shipmentVo.setDimensionUnit(shipmentInfoVo.getDimensionUnit());
            }
            if (shipmentInfoVo.getCurrencyCode() != null) {
                shipmentVo.setCurrencyCode(shipmentInfoVo.getCurrencyCode());
            }
            if (shipmentInfoVo.getContentType() != null) {
                shipmentVo.setProductContentCode(shipmentInfoVo.getContentType());
            }
            if (shipmentInfoVo.getDutiesAccount() != null) {
                shipmentVo.setDutiesAccount(shipmentInfoVo.getDutiesAccount());
            }
            if (shipmentInfoVo.getDutiesType() != null) {
                shipmentVo.setDutiesType(shipmentInfoVo.getDutiesType());
            }
            if (shipmentInfoVo.getShipperAccount() != null) {
                shipmentVo.setBillingAccount(shipmentInfoVo.getShipperAccount());
            }
            if (shipmentInfoVo.getServiceAreaCodeOrigin() != null) {
                shipmentVo.setServiceAreaCodeOrigin(shipmentInfoVo.getServiceAreaCodeOrigin());
            }
            if (shipmentInfoVo.getServiceAreaCodeDestination() != null) {
                shipmentVo.setServiceAreaCodeDestination(shipmentInfoVo.getServiceAreaCodeDestination());
            }
            if (shipmentRequestVo.getShipmentReference() != null) {
                shipmentVo.setReference(shipmentRequestVo.getShipmentReference());
            }
            if (quote.getInsuranceValue() != null) {
                shipmentVo.setWithInsurance(BigDecimal.valueOf(Double.parseDouble(quote.getInsuranceValue())));
            }

            if (quote.getTotalCustomValue() != null) {
                shipmentVo.setTotalCustomValue(BigDecimal.valueOf(Double.parseDouble(quote.getTotalCustomValue())));
            }
            if (quote.getNonStandardCharge() != null) {
                shipmentVo.setNonStandardCharge(BigDecimal.valueOf(Double.parseDouble(quote.getNonStandardCharge())));
            }

            BookingDataVo bookingDataVo = GsonUtils.fromGson(context.get(Attributes.BOOKING_DATA), BookingDataVo.class);

            bookingDataVo.setShipmentVo(shipmentVo);

            List<ShipmentDetailVo> shipmentDetails = new ArrayList<ShipmentDetailVo>();
            for (AccessorialModel accessorial : quote.getAccessorial()) {
                if (accessorial.getAccessorialId() != null) {
                    ShipmentDetailVo shipmentDetailVo = new ShipmentDetailVo();
                    shipmentDetailVo.setAccessorialId(Long.parseLong(accessorial.getAccessorialId()));
                    String amout = accessorial.getValue();
                    shipmentDetailVo.setAmount(BigDecimal.valueOf(Double.parseDouble(amout)));
                    shipmentDetails.add(shipmentDetailVo);
                }
            }
            bookingDataVo.setShipmentDetails(shipmentDetails);
            context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
            context.put(Attributes.BOOKING_DATA, GsonUtils.toGson(bookingDataVo));
            context.put(Attributes.SHIPMENT_REQUEST_VO, GsonUtils.toGson(shipmentVo));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
