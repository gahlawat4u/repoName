package com.gms.xms.workflow.task2.toll.ipec.schedule;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.dto.delivery.toll.TollIpecPickupRequestVo;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.address.IAddressService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;

/**
 * Posted from PrepareTntDomScheduleCollectionTask
 * <p>
 * Author @author HungNT Feb 3, 2016
 */
public class PrepareTollIpecScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareTollIpecScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            TollIpecPickupRequestVo tollIpecPickupRequestVo = new TollIpecPickupRequestVo();
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
            Double compareWeight = 0d;
            Integer maxL = 0;
            Integer maxH = 0;
            Integer maxW = 0;
            Double maxWeight = 0d;
            for (PieceVo pieceVo : pieceVos) {
                Double weightPiece = ShipmentUtils.weightUnitConvert(pieceVo.getWeight(), shipmentVo.getWeightUnit());
                weightPiece = MathUtils.shipmentWeightRound(weightPiece, false);
                totalDeadWeight +=weightPiece;
                Double weight = pieceVo.getDimensionH() * pieceVo.getDimensionL() * pieceVo.getDimensionW();
                if (compareWeight <= weight) {
                    compareWeight = weight;
                    maxL = pieceVo.getDimensionL().intValue();
                    maxH = pieceVo.getDimensionH().intValue();
                    maxW = pieceVo.getDimensionW().intValue();
                    maxWeight = weightPiece;
                }
            }
            tollIpecPickupRequestVo.setWeight(maxWeight);
            tollIpecPickupRequestVo.setLength(maxL==0?1:maxL);
            tollIpecPickupRequestVo.setWidth(maxW==0?1:maxW);
            tollIpecPickupRequestVo.setHeight(maxH==0?1:maxH);

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
            tollIpecPickupRequestVo.setShipment(shipmentInfoVo);

            // Set schedule collection info
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULECOLLECTION_VO);
            scheduleCollectionVo.getPickupAddress().setCountryDetail(senderCountryVo);
            scheduleCollectionVo.setShipmentId(shipmentId);
            scheduleCollectionVo.setStatus((byte) 1);
            tollIpecPickupRequestVo.setScheduleCollection(scheduleCollectionVo);

            tollIpecPickupRequestVo.setDocumentType(SystemSettingCache.getInstance().getValueByKey("TOLLIPEC DocumentType"));
            String documentId = DateUtils.convertDateToString(new Date(), "Ymd", null) + String.valueOf(shipmentInfoVo.getShipmentId());
            tollIpecPickupRequestVo.setDocumentId(documentId);
            String accountCode = "";
            if (shipmentVo.getBillingAccount() == null) {
                accountCode = SystemSettingCache.getInstance().getValueByKey("TOLLIPEC-Third-Party-Billing-Account");
            } else {
                accountCode = shipmentVo.getBillingAccount();
            }
            tollIpecPickupRequestVo.setAccountCode(accountCode);

            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
            if (shipmentTypeVo.getPerWeightStatus() == 1) {
                tollIpecPickupRequestVo.setBringConNote(false);
            } else {
                tollIpecPickupRequestVo.setBringConNote(true);
            }
            tollIpecPickupRequestVo.setServiceId(shipmentTypeVo.getServiceCode());
            String productId = "";
            if (!StringUtils.isBlank(shipmentTypeVo.getServiceGroup())) {
                String[] serviceGroup = shipmentTypeVo.getServiceGroup().split("#");
                productId = serviceGroup[0];
            }
            tollIpecPickupRequestVo.setProductId(productId);

            String largestItemType = "BXCA";
            switch (shipmentInfoVo.getPackageId()) {
                case 5:
                    largestItemType = "LESA";
                    break;
                case 6:
                    largestItemType = "BXCA";
                    break;
                case 7:
                    largestItemType = "PALL";
                    break;
                case 8:
                    largestItemType = "OTHE";
                    break;
            }
            tollIpecPickupRequestVo.setLargestItemType(largestItemType);


            context.put(Attributes.TOLL_IPEC_PICKUP_REQUEST_VO, tollIpecPickupRequestVo);
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
