package com.gms.xms.workflow.task2.invoice.tnt.international;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.webship.TntInternationalAuZoneDao;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.TntInternationalAuZoneVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from May 19, 2016 4:05:17 PM
 * <p>
 * Author huynd
 */
public class GetTntIntlZoneBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTntIntlZoneBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentBillingVo shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
            TntInternationalAuZoneVo tntIntlZoneVo = new TntInternationalAuZoneVo();
            TntInternationalAuZoneDao tntIntlZoneDao = new TntInternationalAuZoneDao();
            String tntIntlzone = (shipmentBilling.getZone() == null) ? "" : shipmentBilling.getZone();
            Boolean billingBound = shipmentBilling.getBillingBound();
            Integer bound = 0; // Outbound
            Integer addressId;
            if (billingBound) { // Inbound
                bound = 1;
                addressId = shipmentBilling.getSenderAddressId();
            } else {
                addressId = shipmentBilling.getReceiverAddressId();
            }
            context.put(Attributes.BOUND, bound);

            Long countryId = 0L;
            AddressDao addressDao = new AddressDao();
            AddressVo address = addressDao.selectById(addressId);
            if (address != null) {
                countryId = (address.getCountry() == null) ? 0L : address.getCountry();
            }

            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ShipmentTypeFilter shipmentTypeFilter = new ShipmentTypeFilter();
            shipmentTypeFilter.setShipmentTypeName(shipmentBilling.getDescription());
            shipmentTypeFilter.setServiceId(Integer.valueOf(shipmentBilling.getCarrier().toString()));
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectByShipmentTypeNameAndCarrier(shipmentTypeFilter);
            context.put(Attributes.SHIPMENT_TYPE_RESULT, shipmentTypeVo);

            ICountryService countryService = new CountryServiceImp();
            CountryVo countryVo = new CountryVo();

            countryVo = countryService.getCountryByCountryId(countryId);
            tntIntlZoneVo = tntIntlZoneDao.getZoneByCountryName(countryVo.getCountryName());
            if (tntIntlZoneVo != null) {
                if ("express".equalsIgnoreCase(shipmentTypeVo.getServiceCode())) {
                    tntIntlzone = tntIntlZoneVo.getExpressInboundZone();
                } else {
                    tntIntlzone = tntIntlZoneVo.getEconomyExpressInboundZone();
                }
            }
            if (shipmentBilling.getZone() == null) {
                shipmentBilling.setZone(tntIntlzone);
            }
            context.put(Attributes.SHIPMENT_BILLING_VO, shipmentBilling);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
