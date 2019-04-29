package com.gms.xms.workflow.task2.ups.international;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.UpsZoneDao;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.ups.UpsZoneFilter;
import com.gms.xms.txndb.vo.ups.UpsZoneVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetUpsIntlZoneTask
 * <p>
 * Author Thinhdd Date Mar 29, 2017
 */
public class GetUpsIntlZoneTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetUpsIntlZoneTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
            UpsZoneFilter upsZoneFilter = new UpsZoneFilter();
            UpsZoneDao upsZoneDao = new UpsZoneDao();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            Integer bound = shipmentInfoVo.getBound();
            Long countryId;
            String postCode;
            if(bound==0)
            {
                countryId = shipmentInfoVo.getReceiverAddress().getCountry();
                postCode = shipmentInfoVo.getReceiverAddress().getPostalCode();
            }else
            {
                countryId = shipmentInfoVo.getSenderAddress().getCountry();
                postCode = shipmentInfoVo.getSenderAddress().getPostalCode();
            }

            upsZoneFilter.setBound(bound+1);
            upsZoneFilter.setCountryId(countryId);
            upsZoneFilter.setPostCode(postCode);

            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentInfoVo.getShipmentTypeId());
            upsZoneFilter.setServiceGroup(shipmentTypeVo.getServiceGroup());

            UpsZoneVo upsZoneVo = upsZoneDao.getZoneByFilter(upsZoneFilter);
            if(upsZoneVo==null || StringUtils.isEmpty(upsZoneVo.getZone())){
                throw new Exception(SystemSettingCache.getInstance().getValueByKey("UPS Fall Large Package Charge"));
            }
            shipmentInfoVo.setZone(upsZoneVo.getZone());
            context.put(Attributes.SHIPMENT_INFO_VO, shipmentInfoVo);
            context.put(Attributes.SHIPMENT_TYPE_RESULT, shipmentTypeVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
