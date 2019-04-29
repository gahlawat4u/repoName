package com.gms.xms.workflow.task2.invoice.tnt.international.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.webship.TntInternationalAuZoneDao;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
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
public class GetTntIntlImportBillingZoneBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTntIntlImportBillingZoneBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            SaveImportBillingVo saveImportBillingVo = context.get(Attributes.IMPORT_BILLING_VO);
            ShipmentBillingVo shipmentBilling = saveImportBillingVo.getBillingBaseCharge();
            TntInternationalAuZoneVo tntIntlZoneVo = new TntInternationalAuZoneVo();
            TntInternationalAuZoneDao tntIntlZoneDao = new TntInternationalAuZoneDao();
            String tntIntlzone = (shipmentBilling.getZone() == null) ? "" : shipmentBilling.getZone();
            Boolean billingBound = shipmentBilling.getBillingBound();
            Integer bound = 0; // Outbound
            AddressVo address = new AddressVo();
            if (billingBound) { // Inbound
                bound = 1;
                address = saveImportBillingVo.getSenderAddress();
            } else {
                address = saveImportBillingVo.getReceiverAddress();
            }
            context.put(Attributes.BOUND, bound);

            Long countryId = 0L;
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
                if (bound == 1) {
                    if ("express".equalsIgnoreCase(shipmentTypeVo.getServiceCode())) {
                        tntIntlzone = tntIntlZoneVo.getExpressInboundZone();
                    } else {
                        tntIntlzone = tntIntlZoneVo.getEconomyExpressInboundZone();
                    }
                } else {
                    if ("express".equalsIgnoreCase(shipmentTypeVo.getServiceCode())) {
                        tntIntlzone = tntIntlZoneVo.getExpressOutboundZone();
                    } else {
                        tntIntlzone = tntIntlZoneVo.getEconomyExpressOutboundZone();
                    }
                }

            }
            shipmentBilling.setZone(tntIntlzone);
            saveImportBillingVo.setBillingBaseCharge(shipmentBilling);
            context.put(Attributes.IMPORT_BILLING_VO, saveImportBillingVo);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
