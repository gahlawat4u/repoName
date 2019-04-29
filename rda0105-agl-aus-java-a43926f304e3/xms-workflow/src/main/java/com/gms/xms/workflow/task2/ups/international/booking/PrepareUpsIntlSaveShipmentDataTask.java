package com.gms.xms.workflow.task2.ups.international.booking;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.ship.QuoteVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrepareUpsIntlSaveShipmentDataTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareUpsIntlSaveShipmentDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentRequestVo shipmentRequestPage1 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE1);
            ShipmentRequestVo shipmentRequestPage2 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE2);
            Integer selCommercialInvoice = context.getInt(Attributes.COMMERCIAL_LIST_SELECT);
            Integer selPackingList = context.getInt(Attributes.PACKING_LIST_SELECT);
            Integer selCollection = context.getInt(Attributes.SCHEDULE_LIST_SELECT);
            selCommercialInvoice = selCommercialInvoice != null ? selCommercialInvoice : 1;
            selPackingList = selPackingList != null ? selPackingList : 1;
            selCollection = selCollection != null ? selCollection : 3;

            if (shipmentRequestPage2.getShipmentInfo() == null) {
                shipmentRequestPage2.setShipmentInfo(new ShipmentInfoVo());
            }
            shipmentRequestPage2.getShipmentInfo().setPackingList(selPackingList);
            shipmentRequestPage2.getShipmentInfo().setCommercialInvoiceId(selCommercialInvoice);
            shipmentRequestPage2.getShipmentInfo().setCollectionTypeId(selCollection);

            ShipmentVo shipmentVo = this.buildShipmentVo(shipmentRequestPage1, shipmentRequestPage2);
            List<ShipmentDetailVo> shipmentDetailVos = new ArrayList<>();
            List<AccessorialVo> accessorialVos = shipmentRequestPage1.getQuote().getAccessorial();
            for (AccessorialVo accessorialVo : accessorialVos) {
                ShipmentDetailVo shipmentDetailVo = new ShipmentDetailVo();
                if (accessorialVo.getAccessorialId() != null) {
                    shipmentDetailVo.setAccessorialId(accessorialVo.getAccessorialId());
                    shipmentDetailVo.setAmount(new BigDecimal(accessorialVo.getValue()).setScale(2, RoundingMode.HALF_UP));
                    shipmentDetailVo.setType(accessorialVo.getType());
                    shipmentDetailVos.add(shipmentDetailVo);
                }
            }
            BookingDataVo bookingDataVo = new BookingDataVo();
            bookingDataVo.setQuoteVo(shipmentRequestPage1.getQuote());
            bookingDataVo.setShipmentDetails(shipmentDetailVos);
            bookingDataVo.setShipmentVo(shipmentVo);
            bookingDataVo.setsAddress(shipmentRequestPage1.getShipmentInfo().getSenderAddress());
            bookingDataVo.setrAddress(shipmentRequestPage1.getShipmentInfo().getReceiverAddress());
            bookingDataVo.setPieces(buildPieces(shipmentRequestPage1.getShipmentInfo()));
            bookingDataVo.setScheduleCollection(shipmentRequestPage2.getScheduleCollection());
            if (selPackingList == 2 || selCommercialInvoice == 3) {
                bookingDataVo.setShipmentProductDetail(shipmentRequestPage2.getShipmentProductDetails());
            }
            context.put(Attributes.BOOKING_DATA_VO, bookingDataVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    private ShipmentVo buildShipmentVo(ShipmentRequestVo shipmentRequestPage1, ShipmentRequestVo shipmentRequestPage2) throws Exception {
        ShipmentVo shipmentVo = new ShipmentVo();
        QuoteVo quote = shipmentRequestPage1.getQuote();
        ShipmentInfoVo shipmentInfoVoPage1 = shipmentRequestPage1.getShipmentInfo();
        ShipmentInfoVo shipmentInfoVoPage2 = shipmentRequestPage2.getShipmentInfo();
        WebshipLoginVo webshipLoginVo = shipmentRequestPage1.getWebshipLogin();
        Date currentDate = new Date();
        // Not null
        shipmentVo.setCustomerCode(0L);
        shipmentVo.setWebshipId(0L);
        shipmentVo.setAirbillNumber("");
        shipmentVo.setShipmentDate(shipmentInfoVoPage1.getShipmentDate());
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
        shipmentVo.setCarrierPayment(0);
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
        shipmentVo.setBillingType(1);
        shipmentVo.setBillingAccount(SystemSettingCache.getInstance().getValueByKey("TNT AU Account"));
        shipmentVo.setDutiesType(2);
        shipmentVo.setDutiesAccount("1");
        shipmentVo.setTermOfTrade("DTU");
        shipmentVo.setServiceAreaCodeOrigin("");
        shipmentVo.setServiceAreaCodeDestination("");
        shipmentVo.setReceiverTaxId("");
        shipmentVo.setReasonForExport("");
        shipmentVo.setPackingList(1);
        shipmentVo.setBoundStatus(0);
        shipmentVo.setSalesRepId(0L);
        shipmentVo.setOldCustomerCode(0L);
        shipmentVo.setAwbProductContentCode("");
        shipmentVo.setNonStandardCharge(BigDecimal.ZERO);

        if (shipmentInfoVoPage2.getReasonForExport() != null) {
            shipmentVo.setReasonForExport(shipmentInfoVoPage2.getReasonForExport());
        }
        if (shipmentInfoVoPage2.getCommercialInvoiceId() != null) {
            shipmentVo.setCommercialInvoiceId(shipmentInfoVoPage2.getCommercialInvoiceId());
        }
        if (shipmentInfoVoPage2.getPackingList() != null) {
            shipmentVo.setPackingList(shipmentInfoVoPage2.getPackingList());
        }

        if (shipmentInfoVoPage1.getBound() != null) {
            shipmentVo.setBoundStatus(shipmentInfoVoPage1.getBound());
        }
        if (quote.getBaseCharge() != null) {
            Double baseCharge = quote.getBaseCharge();
            shipmentVo.setBaseCharge(BigDecimal.valueOf(baseCharge));
        }
        if (shipmentInfoVoPage1.getContentType() != null) {
            shipmentVo.setAwbProductContentCode(shipmentInfoVoPage1.getContentType());
        }
        if (shipmentInfoVoPage1.getBillingCode() != null) {
            shipmentVo.setBillingCode(shipmentInfoVoPage1.getBillingCode());
        }
        if (shipmentRequestPage2.getContentDetail().getDescription() != null) {
            shipmentVo.setContentDescription(shipmentRequestPage2.getContentDetail().getDescription());
        }
        if (shipmentInfoVoPage1.getContents() != null) {
            shipmentVo.setContents(shipmentInfoVoPage1.getContents());
        }

        if (shipmentInfoVoPage1.getAwbBarcode() != null) {
            shipmentVo.setAwbBarcode(shipmentInfoVoPage1.getAwbBarcode());
        }

        if (shipmentInfoVoPage1.getOriginDestiBarcode() != null) {
            shipmentVo.setOriginDestiBarcode(shipmentInfoVoPage1.getOriginDestiBarcode());
        }

        if (shipmentInfoVoPage1.getDhlRoutingBarcode() != null) {
            shipmentVo.setDhlRoutingBarcode(shipmentInfoVoPage1.getDhlRoutingBarcode());
        }

        if (shipmentInfoVoPage1.getCourierMessage() != null) {
            shipmentVo.setCourierMessage(shipmentInfoVoPage1.getCourierMessage());
        }
        shipmentVo.setCreateDate(currentDate);

        if (webshipLoginVo.getCustomerCode() != null) {
            shipmentVo.setCustomerCode(webshipLoginVo.getCustomerCode());
        }
        if (webshipLoginVo.getWebshipId() != null) {
            shipmentVo.setWebshipId(webshipLoginVo.getWebshipId());
        }
        if (shipmentInfoVoPage1.getPieces() != null) {
            Integer noOfPieces = 0;
            for (PieceVo pieceVo : shipmentInfoVoPage1.getPieces()) {
                noOfPieces += pieceVo.getQuantity() == null ? 1 : pieceVo.getQuantity();
            }
            shipmentVo.setNoOfPieces(noOfPieces);
        }
        if (shipmentInfoVoPage1.getPackageId() != null) {
            shipmentVo.setPackageId(shipmentInfoVoPage1.getPackageId());
        }
        if (shipmentInfoVoPage1.getZone() != null) {
            shipmentVo.setZone(shipmentInfoVoPage1.getZone());
        }

        if (shipmentInfoVoPage1.getShipmentTypeId() != null) {
            shipmentVo.setShipmentTypeId(shipmentInfoVoPage1.getShipmentTypeId());
        }

        if (shipmentInfoVoPage2.getTermOfTrade() != null) {
            shipmentVo.setTermOfTrade(shipmentInfoVoPage2.getTermOfTrade());
        }
        if (shipmentInfoVoPage1.getWeightUnit() != null) {
            shipmentVo.setWeightUnit(shipmentInfoVoPage1.getWeightUnit().toLowerCase());
        }
        if (shipmentInfoVoPage1.getDimensionUnit() != null) {
            shipmentVo.setDimensionUnit(shipmentInfoVoPage1.getDimensionUnit().toLowerCase());
        }
        if (shipmentInfoVoPage1.getCurrencyCode() != null) {
            shipmentVo.setCurrencyCode(shipmentInfoVoPage1.getCurrencyCode());
        }
        if (shipmentRequestPage2.getContentDetail().getDescription() != null) {
            shipmentVo.setContentDescription(shipmentRequestPage2.getContentDetail().getDescription());
        }
        if (shipmentInfoVoPage2.getDutiesAccount() != null) {
            shipmentVo.setDutiesAccount(shipmentInfoVoPage2.getDutiesAccount());
        }
        if (shipmentInfoVoPage1.getSenderAddress().getState() != null) {
            shipmentVo.setServiceAreaCodeOrigin(shipmentInfoVoPage1.getSenderAddress().getState());
        }
        if (shipmentInfoVoPage1.getServiceAreaCodeDestination() != null) {
            shipmentVo.setServiceAreaCodeDestination(shipmentInfoVoPage1.getServiceAreaCodeDestination());
        }
        if (shipmentRequestPage2.getShipmentReference() != null) {
            shipmentVo.setReference(shipmentRequestPage2.getShipmentReference());
        }
        if (shipmentInfoVoPage2.getSpecialDelivery() != null) {
            shipmentVo.setSpecialDeliveryinst(shipmentInfoVoPage2.getSpecialDelivery());
        }
        if (quote.getInsuranceValue() != null) {
            shipmentVo.setWithInsurance(BigDecimal.valueOf(quote.getInsuranceValue()));
        }

        if (quote.getTotalCustomValue() != null) {
            shipmentVo.setTotalCustomValue(BigDecimal.valueOf(quote.getTotalCustomValue()));
        }
        if (quote.getNonStandardCharge() != null) {
            shipmentVo.setNonStandardCharge(BigDecimal.valueOf(quote.getNonStandardCharge()));
        }
        if (shipmentInfoVoPage2.getCommercialInvoiceId() != null) {
            shipmentVo.setCommercialInvoiceId(shipmentInfoVoPage2.getCommercialInvoiceId());
        }
        if (shipmentInfoVoPage2.getCollectionTypeId() != null) {
            shipmentVo.setCollectionTypeId(shipmentInfoVoPage2.getCollectionTypeId());
        }

        return shipmentVo;
    }

    private List<PieceVo> buildPieces(ShipmentInfoVo shipmentInfoVo) throws Exception {
        List<PieceVo> pieces = new ArrayList<PieceVo>();
        for (PieceVo piece : shipmentInfoVo.getShipmentRequestPieces()) {
            PieceVo pieceN = new PieceVo();
            pieceN.setCustomValue(0D);
            pieceN.setDataIdentifier("");
            pieceN.setDeadWeight(0D);
            pieceN.setDimensionH(1D);
            pieceN.setDimensionW(1D);
            pieceN.setDimensionL(1D);
            pieceN.setLicensePlate("");
            pieceN.setLicensePlateBarcode("");
            pieceN.setQuantity(0);
            pieceN.setWeight(0D);

            if (piece.getCustomValue() != null) {
                pieceN.setCustomValue(piece.getCustomValue());
            }
            if (piece.getWeight() != null) {
                pieceN.setWeight(piece.getWeight());
            }
            if (piece.getDeadWeight() != null) {
                pieceN.setDeadWeight(piece.getDeadWeight());
            }
            if (piece.getDimension() != null) {
                pieceN.setDimension(piece.getDimension());
            }
            if (piece.getDimensionH() != null) {
                pieceN.setDimensionH(piece.getDimensionH());
            }
            if (piece.getDimensionL() != null) {
                pieceN.setDimensionL(piece.getDimensionL());
            }
            if (piece.getDimensionW() != null) {
                pieceN.setDimensionW(piece.getDimensionW());
            }
            if (piece.getQuantity() != null) {
                pieceN.setQuantity(piece.getQuantity());
            }
            if (piece.getLicensePlate() != null) {
                pieceN.setLicensePlate(piece.getLicensePlate());
            }
            if (piece.getLicensePlateBarcode() != null) {
                pieceN.setLicensePlateBarcode(piece.getLicensePlateBarcode());
            }
            pieces.add(pieceN);
        }
        return pieces;
    }
}
