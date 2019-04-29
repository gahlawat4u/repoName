package com.gms.xms.workflow.task.dhl.international;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.DhlEsiZoneStationDao;
import com.gms.xms.persistence.dao.DhlZoneDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.dhl.DhlEsiZoneStationFilter;
import com.gms.xms.txndb.vo.dhl.DhlEsiZoneStationVo;
import com.gms.xms.txndb.vo.dhl.DhlZoneFilter;
import com.gms.xms.txndb.vo.dhl.DhlZoneVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetDhlIntlZone
 * <p>
 * Author HungNT Date May 18, 2015
 */
public class GetDhlIntlZoneTask implements Task {
    private static final Log log = LogFactory.getLog(GetDhlIntlZoneTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        DhlZoneFilter dhlZoneFilter = new DhlZoneFilter();
        DhlZoneVo dhlZoneVo = new DhlZoneVo();
        DhlZoneDao dhlZoneDao = new DhlZoneDao();
        String defaultCountryId = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            WebshipLoginVo webshipLogin = GsonUtils.fromGson(context.get(Attributes.USER_LOGGIN_INFO), WebshipLoginVo.class);
            AddressVo senderAddress = shipmentInfoVo.getSenderAddress();
            AddressVo receiverAddress = shipmentInfoVo.getReceiverAddress();
            Integer bound = shipmentInfoVo.getBound();
            String dhlZone = "";
            DhlEsiZoneStationVo dhlEsiZoneStationVo = new DhlEsiZoneStationVo();
            DhlEsiZoneStationFilter dhlEsiZoneStationFilter = new DhlEsiZoneStationFilter();
            DhlEsiZoneStationDao dhlEsiZoneStationDao = new DhlEsiZoneStationDao();
            dhlEsiZoneStationFilter.setBound(bound);

            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = new ShipmentTypeVo();
            shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentInfoVo.getShipmentTypeId());

            FranchiseDao franchiseDao = new FranchiseDao();
            FranchiseInfoVo franchiseInfoVo = new FranchiseInfoVo();
            String customerCode = String.valueOf(webshipLogin.getCustomerCode());
            franchiseInfoVo = franchiseDao.getFranchiseInfoByCode(customerCode.substring(0, 3));
            if (shipmentTypeVo.getShipmentTypeName().contains("DHL ETS") || shipmentTypeVo.getShipmentTypeName().contains("ETS") || shipmentTypeVo.getShipmentTypeName().equalsIgnoreCase("Dangerous Goods")) {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                context.put(Attributes.ERROR_MESSAGE, "Please contact your freight consultant at (" + franchiseInfoVo.getPhone() + ") or (" + franchiseInfoVo.getEmail() + ")");
                return false;
            }

            if (shipmentInfoVo.getShipmentTypeId() == 122) {
                if (bound == 0) {

                    dhlEsiZoneStationFilter.setCityName(senderAddress.getCity());
                    dhlEsiZoneStationFilter.setPostalCode(senderAddress.getPostalCode());

                    dhlEsiZoneStationVo = dhlEsiZoneStationDao.selectEsiZone(dhlEsiZoneStationFilter);
                } else {
                    dhlEsiZoneStationFilter.setCityName(receiverAddress.getCity());
                    dhlEsiZoneStationFilter.setPostalCode(receiverAddress.getPostalCode());

                    dhlEsiZoneStationVo = dhlEsiZoneStationDao.selectEsiZone(dhlEsiZoneStationFilter);
                }
                dhlZone = String.valueOf(dhlEsiZoneStationVo.getZone());
                if ((senderAddress.getCountry() == 16 && receiverAddress.getCountry() != 150) || (senderAddress.getCountry() != 150 && receiverAddress.getCountry() == 16) || StringUtils.isBlank(dhlZone)) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, "The selected service is not available from the origin to the destination.");
                    return false;
                } else {
                    shipmentInfoVo.setZone(dhlZone);
                    context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
                    return true;
                }
            }

            if (bound == 1) {
                if (StringUtils.equalsIgnoreCase(shipmentInfoVo.getReceiverAddress().getState(), "WA") && defaultCountryId.equalsIgnoreCase("16") && (senderAddress.getCountry() == 181 || senderAddress.getCountry() == 150)) {
                    if (senderAddress.getCountry() == 181) {
                        dhlZone = "1"; // SGZone
                    } else {
                        dhlZone = "4"; // NZ Zone
                    }
                } else {
                    dhlZoneFilter.setStateCode(senderAddress.getState());
                    dhlZoneFilter.setCityName(senderAddress.getCity());
                    dhlZoneFilter.setCountryId(String.valueOf(senderAddress.getCountry()));
                    dhlZoneFilter.setPostalCode(senderAddress.getPostalCode());
                    dhlZoneVo = dhlZoneDao.getDhlZoneByFilter(dhlZoneFilter);
                    dhlZone = ShipmentUtils.getDhlZone(dhlZoneVo.getDhlApZone(), bound);
                }
            } else {
                if (StringUtils.equalsIgnoreCase(shipmentInfoVo.getReceiverAddress().getState(), "WA") && defaultCountryId.equalsIgnoreCase("16") && (receiverAddress.getCountry() == 181 || receiverAddress.getCountry() == 150)) {
                    // + If shipment is going to or from Western Australia; New
                    // Zealand is Zone C, Singapore is Zone A. for SG181 NZ150;
                    if (receiverAddress.getCountry() == 181) {
                        dhlZone = "1.1"; // SG
                    } else {
                        dhlZone = "4"; // NZ
                    }
                } else {
                    dhlZoneFilter.setStateCode(receiverAddress.getState());
                    dhlZoneFilter.setCityName(receiverAddress.getCity());
                    dhlZoneFilter.setCountryId(String.valueOf(receiverAddress.getCountry()));
                    dhlZoneFilter.setPostalCode(receiverAddress.getPostalCode());
                    dhlZoneVo = dhlZoneDao.getDhlZoneByFilter(dhlZoneFilter);
                    dhlZone = ShipmentUtils.getDhlZone(dhlZoneVo.getDhlApZone(), bound);
                }
            }
            shipmentInfoVo.setZone(dhlZone);
            context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
