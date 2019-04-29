package com.gms.xms.workflow.task2.tnt.international.tracking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.address.IAddressService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from PrepareTntIntlTrackingRequest
 * <p>
 * Author dattrinh Feb 1, 2016 11:25:59 AM
 */
public class PrepareTntIntlTrackingRequestTask implements Task2 {

    private static final Log log = LogFactory.getLog(PrepareTntIntlTrackingRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get shipment id from the context.
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            // Get shipment info by shipment id.
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(shipmentId);
            // Get sender address info.
            IAddressService addressService = new AddressServiceImp();
            AddressVo senderAddressVo = addressService.getAddressById(shipmentVo.getSenderAddressId());
            // Get sender country info.
            ICountryService countryService = new CountryServiceImp();
            CountryVo senderCountryVo = countryService.getCountryByCountryId(senderAddressVo.getCountry());
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Put air-bill number into the context.
            context.put(Attributes.AIRBILL_NUMBER, shipmentVo.getAirbillNumber());
            // Put country code into the context.
            context.put(Attributes.COUNTRY_CODE, senderCountryVo.getCountryCode());
            context.put(Attributes.SHIPMENT_INFO_VO, shipmentVo);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
