package com.gms.xms.workflow.task2.tnt.international.schedule;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.address.IAddressService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.shipment.TntShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from PrepareTntIntlScheduleCollectionTask
 * <p>
 * Author @author HungNT Feb 2, 2016
 */
public class PrepareTntIntlScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(ValidateTntIntlScheduleCollectionDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            TntShipmentRequestVo tntShipmentRequestVo = new TntShipmentRequestVo();
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            ICountryService countryService = new CountryServiceImp();

            // Get shipment info
            ShipmentInfoVo shipmentInfoVo = new ShipmentInfoVo();
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(shipmentId);
            shipmentInfoVo = this.transferShipmentVo2ShipmentInfoVo(shipmentVo);

            // Get pieces
            List<PieceVo> pieceVos = shipmentService.selectPieceById(shipmentId);
            for (PieceVo pieceVo : pieceVos) {
                pieceVo.setDimensionH(pieceVo.getDimensionH() != 0 ? pieceVo.getDimensionH() : 1);
                pieceVo.setDimensionL(pieceVo.getDimensionL() != 0 ? pieceVo.getDimensionL() : 1);
                pieceVo.setDimensionW(pieceVo.getDimensionW() != 0 ? pieceVo.getDimensionW() : 1);
            }
            shipmentInfoVo.setPieces(pieceVos);

            // Get addresses
            IAddressService addressService = new AddressServiceImp();
            AddressVo senderAddressVo = addressService.getAddressById(shipmentVo.getSenderAddressId());
            CountryVo senderCountryVo = countryService.getCountryByCountryId(senderAddressVo.getCountry());
            senderAddressVo.setCountryDetail(senderCountryVo);
            shipmentInfoVo.setSenderAddress(senderAddressVo);
            AddressVo receiverAddressVo = addressService.getAddressById(shipmentVo.getReceiverAddressId());
            CountryVo receiverCountryVo = countryService.getCountryByCountryId(receiverAddressVo.getCountry());
            receiverAddressVo.setCountryDetail(receiverCountryVo);
            shipmentInfoVo.setReceiverAddress(receiverAddressVo);
            tntShipmentRequestVo.setShipmentInfo(shipmentInfoVo);

            // Set schedule collection info
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULECOLLECTION_VO);
            scheduleCollectionVo.getPickupAddress().setCountryDetail(senderCountryVo);
            scheduleCollectionVo.setShipmentId(shipmentId);
            scheduleCollectionVo.setStatus((byte) 1);
            tntShipmentRequestVo.setScheduleCollection(scheduleCollectionVo);

            //Get package info
            IPackageService packageService = new PackageServiceImp();
            PackageVo packageVo = packageService.getPackagebyId(shipmentVo.getPackageId());
            tntShipmentRequestVo.setPackageInfo(packageVo);

            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentVo.getShipmentTypeId());
            if (shipmentTypeVo.getServiceCode().equalsIgnoreCase("express")) {
                if (shipmentVo.getContents() == 1) {
                    tntShipmentRequestVo.setServiceCode("15N");
                    tntShipmentRequestVo.getShipmentInfo().setContentType("WPX");
                } else {
                    tntShipmentRequestVo.setServiceCode("15D");
                    tntShipmentRequestVo.getShipmentInfo().setContentType("DOX");
                }
            } else if (shipmentTypeVo.getServiceCode().equalsIgnoreCase("economy_express")) {
                tntShipmentRequestVo.getShipmentInfo().setContentType("WPX");
                tntShipmentRequestVo.setServiceCode("48N");
            }

            context.put(Attributes.TNT_SHIPMENT_REQUEST_VO, tntShipmentRequestVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    private ShipmentInfoVo transferShipmentVo2ShipmentInfoVo(ShipmentVo shipmentVo) {
        ShipmentInfoVo shipmentInfoVo = new ShipmentInfoVo();
        shipmentInfoVo.setShipmentId(shipmentVo.getShipmentId());
        shipmentInfoVo.setCustomerCode(shipmentVo.getCustomerCode());
        shipmentInfoVo.setWebshipId(shipmentVo.getWebshipId());
        shipmentInfoVo.setAirbillNumber(shipmentVo.getAirbillNumber());
        shipmentInfoVo.setShipmentDate(shipmentVo.getShipmentDate());
        shipmentInfoVo.setShipmentTypeId(shipmentVo.getShipmentTypeId());
        shipmentInfoVo.setPackageId(shipmentVo.getPackageId());
        shipmentInfoVo.setWeightUnit(shipmentVo.getWeightUnit());
        shipmentInfoVo.setContents(shipmentVo.getContents());
        shipmentInfoVo.setDimensionUnit(shipmentVo.getDimensionUnit());
        shipmentInfoVo.setNoOfPieces(shipmentVo.getNoOfPieces());
        shipmentInfoVo.setCurrencyCode(shipmentVo.getCurrencyCode());
        shipmentInfoVo.setContentDescription(shipmentVo.getContentDescription());
        shipmentInfoVo.setTotalCustomValue(shipmentVo.getTotalCustomValue());
        shipmentInfoVo.setCommercialInvoiceId(shipmentVo.getCommercialInvoiceId());
        shipmentInfoVo.setCollectionTypeId(shipmentVo.getCollectionTypeId());
        shipmentInfoVo.setDutiesType(shipmentVo.getDutiesType());
        shipmentInfoVo.setReference(shipmentVo.getReference());
        shipmentInfoVo.setReference2(shipmentVo.getReference2());
        shipmentInfoVo.setPackingList(shipmentVo.getPackingList());
        shipmentInfoVo.setBoundStatus(shipmentVo.getBoundStatus());
        shipmentInfoVo.setSalesRepId(shipmentVo.getSalesRepId());
        shipmentInfoVo.setCarrierPayment(shipmentVo.getCarrierPayment());
        shipmentInfoVo.setAwbProductContentCode(shipmentVo.getAwbProductContentCode());
        shipmentInfoVo.setZone(shipmentVo.getZone());
        return shipmentInfoVo;
    }

}
