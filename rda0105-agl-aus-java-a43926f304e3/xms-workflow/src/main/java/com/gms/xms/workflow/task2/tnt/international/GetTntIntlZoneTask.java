package com.gms.xms.workflow.task2.tnt.international;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.webship.TntInternationalAuZoneDao;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.TntInternationalAuZoneVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetTntIntlZoneTask
 * <p>
 * Author TANDT
 */
public class GetTntIntlZoneTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTntIntlZoneTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        TntInternationalAuZoneVo tntIntlZoneVo = new TntInternationalAuZoneVo();
        TntInternationalAuZoneDao tntIntlZoneDao = new TntInternationalAuZoneDao();
        String zone = "";
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfo = context.get(Attributes.SHIPMENT_INFO_VO);
            Double weight = context.getDouble(Attributes.SHIPMENT_TOTAL_WEIGHT);
            Integer bound = shipmentInfo.getBound();
            Integer shipmentTypeId = shipmentInfo.getShipmentTypeId();
            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = new ShipmentTypeVo();
            shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentTypeId);

            ICountryService countryService = new CountryServiceImp();
            CountryVo countryVo = new CountryVo();

            if (shipmentTypeVo.getServiceCode().equalsIgnoreCase("economy_express") && weight < 10) {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                context.put(Attributes.ERROR_MESSAGE, "Weight must be at least 10 kgs.");
                log.warn("Weight must be at least 10 kgs.");
                return false;
            }

            if (bound == 1) { // Inbound
                Long countryId = shipmentInfo.getSenderAddress().getCountry();
                countryVo = countryService.getCountryByCountryId(countryId);
                tntIntlZoneVo = tntIntlZoneDao.getZoneByCountryName(countryVo.getCountryName());
                if (shipmentTypeVo.getServiceCode().equalsIgnoreCase("express")) {
                    zone = tntIntlZoneVo.getExpressInboundZone();
                } else {
                    zone = tntIntlZoneVo.getEconomyExpressInboundZone();
                }
            } else { // Outbound
                Long countryId = shipmentInfo.getReceiverAddress().getCountry();
                countryVo = countryService.getCountryByCountryId(countryId);
                tntIntlZoneVo = tntIntlZoneDao.getZoneByCountryName(countryVo.getCountryName());
                if (shipmentTypeVo.getServiceCode().equalsIgnoreCase("express")) {
                    zone = tntIntlZoneVo.getExpressOutboundZone();
                } else {
                    zone = tntIntlZoneVo.getEconomyExpressOutboundZone();
                }
            }

            if (StringUtils.isBlank(zone)) {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                context.put(Attributes.ERROR_MESSAGE, "The selected service is not available from origin to the destination.");
                return false;
            } else {
                shipmentInfo.setZone(zone);
                context.put(Attributes.SHIPMENT_INFO_VO, shipmentInfo);
            }
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
