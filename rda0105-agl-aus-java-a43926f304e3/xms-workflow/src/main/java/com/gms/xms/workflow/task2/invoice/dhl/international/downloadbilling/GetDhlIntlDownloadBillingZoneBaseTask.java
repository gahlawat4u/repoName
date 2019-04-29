package com.gms.xms.workflow.task2.invoice.dhl.international.downloadbilling;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.DhlEsiZoneStationDao;
import com.gms.xms.persistence.dao.DhlZoneDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.dhl.DhlEsiZoneStationFilter;
import com.gms.xms.txndb.vo.dhl.DhlEsiZoneStationVo;
import com.gms.xms.txndb.vo.dhl.DhlZoneFilter;
import com.gms.xms.txndb.vo.dhl.DhlZoneVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jul 25, 2016 4:29:21 PM
 * <p>
 * Author huynd
 */
public class GetDhlIntlDownloadBillingZoneBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetDhlIntlDownloadBillingZoneBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            String defaultCountryId = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
            SaveImportBillingVo saveImportBillingVo = context.get(Attributes.IMPORT_BILLING_VO);
            ShipmentBillingVo shipmentBilling = saveImportBillingVo.getBillingBaseCharge();
            Boolean billingBound = shipmentBilling.getBillingBound();
            Integer bound = 0;
            String dhlZone = (shipmentBilling.getZone() == null) ? "" : shipmentBilling.getZone();
            DhlEsiZoneStationVo dhlEsiZoneStationVo = new DhlEsiZoneStationVo();
            DhlEsiZoneStationFilter dhlEsiZoneStationFilter = new DhlEsiZoneStationFilter();
            DhlEsiZoneStationDao dhlEsiZoneStationDao = new DhlEsiZoneStationDao();
            dhlEsiZoneStationFilter.setBound(bound);
            AddressVo address;

            if ("ECONOMY SELECT (ESI)".equalsIgnoreCase(shipmentBilling.getDescription())) {
                if (billingBound) { // Inbound
                    bound = 1;
                    address = saveImportBillingVo.getReceiverAddress();
                } else { // Outbound
                    address = saveImportBillingVo.getSenderAddress();
                }
            } else {
                if (billingBound) { // Inbound
                    bound = 1;
                    address = saveImportBillingVo.getSenderAddress();
                } else { // Outbound
                    address = saveImportBillingVo.getReceiverAddress();
                }
            }
            context.put(Attributes.BOUND, bound);

            String state = "";
            String city = "";
            Long countryId = 0L;
            String postalCode = "";

            if (bound == 0) { // Outbound
                state = (shipmentBilling.getServiceAreaCodeDestination() == null) ? "" : shipmentBilling.getServiceAreaCodeDestination();
            } else { // Inbound
                state = (shipmentBilling.getServiceAreaCodeOrigin() == null) ? "" : shipmentBilling.getServiceAreaCodeOrigin();
            }

            state = (address.getState() == null) ? "" : address.getState();
            city = (address.getCity() == null) ? "" : address.getCity();
            countryId = (address.getCountry() == null) ? 0L : address.getCountry();
            postalCode = (address.getPostalCode() == null) ? "" : address.getPostalCode();

            if ("ECONOMY SELECT (ESI)".equalsIgnoreCase(shipmentBilling.getDescription())) {
                dhlEsiZoneStationFilter.setCityName(city);
                dhlEsiZoneStationFilter.setPostalCode(postalCode);

                dhlEsiZoneStationVo = dhlEsiZoneStationDao.selectEsiZone(dhlEsiZoneStationFilter);
                if (dhlEsiZoneStationVo != null) {
                    dhlZone = String.valueOf(dhlEsiZoneStationVo.getZone());
                    shipmentBilling.setZone(dhlZone);
                }
                context.put(Attributes.SHIPMENT_BILLING_VO, shipmentBilling);
                return true;
            }

            DhlZoneFilter dhlZoneFilter = new DhlZoneFilter();
            DhlZoneVo dhlZoneVo = new DhlZoneVo();
            DhlZoneDao dhlZoneDao = new DhlZoneDao();

            if ("WA".equalsIgnoreCase(state) && "16".equalsIgnoreCase(defaultCountryId) && (countryId == 181 || countryId == 150)) {
                if (countryId == 181) {
                    dhlZone = "1.1"; // SG Zone
                } else {
                    dhlZone = "4"; // NZ Zone
                }
            } else {
                dhlZoneFilter.setStateCode(state);
                dhlZoneFilter.setCityName(city);
                dhlZoneFilter.setCountryId(String.valueOf(countryId));
                dhlZoneFilter.setPostalCode(postalCode);
                dhlZoneVo = dhlZoneDao.getDhlZoneByFilter(dhlZoneFilter);
                if (dhlZoneVo != null) {
                    dhlZone = ShipmentUtils.getDhlZone(dhlZoneVo.getDhlApZone(), bound);
                }

            }
            shipmentBilling.setZone(dhlZone);
            context.put(Attributes.SHIPMENT_BILLING_VO, shipmentBilling);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
