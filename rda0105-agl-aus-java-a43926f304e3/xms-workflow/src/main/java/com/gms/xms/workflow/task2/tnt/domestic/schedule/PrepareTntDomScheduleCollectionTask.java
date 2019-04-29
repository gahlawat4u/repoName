package com.gms.xms.workflow.task2.tnt.domestic.schedule;

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
import com.gms.xms.txndb.vo.shipment.TntDomShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from PrepareTntDomScheduleCollectionTask
 * <p>
 * Author @author HungNT Feb 3, 2016
 */
public class PrepareTntDomScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareTntDomScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            TntDomShipmentRequestVo tntDomShipmentRequestVo = new TntDomShipmentRequestVo();
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            ICountryService countryService = new CountryServiceImp();

            // Get shipment info
            ShipmentInfoVo shipmentInfoVo = new ShipmentInfoVo();
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(shipmentId);
            shipmentInfoVo = this.transferShipmentVo2ShipmentInfoVo(shipmentVo);

            // Get pieces
            List<PieceVo> pieceVos = shipmentService.selectPieceById(shipmentId);
            Double totalDeadWeight = 0d;
            for (PieceVo pieceVo : pieceVos) {
                totalDeadWeight += pieceVo.getDeadWeight();
            }
            shipmentInfoVo.setPieces(pieceVos);
            shipmentInfoVo.setTotalWeight(totalDeadWeight);

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
            tntDomShipmentRequestVo.setShipmentInfo(shipmentInfoVo);

            // Set schedule collection info
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULECOLLECTION_VO);
            scheduleCollectionVo.getPickupAddress().setCountryDetail(senderCountryVo);
            scheduleCollectionVo.setShipmentId(shipmentId);
            scheduleCollectionVo.setStatus((byte) 1);
            tntDomShipmentRequestVo.setScheduleCollection(scheduleCollectionVo);

            // Get package info
            IPackageService packageService = new PackageServiceImp();
            PackageVo packageVo = packageService.getPackagebyId(shipmentVo.getPackageId());
            tntDomShipmentRequestVo.setPackageInfo(packageVo);

            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentVo.getShipmentTypeId());
            String serviceCode = shipmentTypeVo.getServiceCode();
            String[] serviceCodeArr = serviceCode.split(",");
            serviceCode = serviceCodeArr[0];
            tntDomShipmentRequestVo.setServiceCode(serviceCode);
            context.put(Attributes.TNT_DOM_SHIPMENT_REQUEST_VO, tntDomShipmentRequestVo);
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
        shipmentInfoVo.setCourierMessage(shipmentVo.getCourierMessage());
        shipmentInfoVo.setDhlRoutingCode(shipmentVo.getDhlRoutingCode());
        return shipmentInfoVo;
    }

}
