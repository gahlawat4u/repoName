package com.gms.xms.workflow.task.toll.priority;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.webship.ship.QuoteModel;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.ShipmentDetailDao;
import com.gms.xms.txndb.vo.ShipmentDetailVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Posted from SaveShipmentTask
 * <p>
 * Author TANDT
 */
public class SaveShipmentTask implements Task {
    private static final Log log = LogFactory.getLog(SaveShipmentTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentDao dao = new ShipmentDao();
        ShipmentDetailDao dDao = new ShipmentDetailDao();
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            QuoteModel quote = GsonUtils.fromGson(context.get(Attributes.QUOTE_VO), QuoteModel.class);
            ShipmentVo shipmentVo = new ShipmentVo();
            Date currentDate = new Date();
            // Not null
            shipmentVo.setShipmentId(0L);
            shipmentVo.setCustomerCode(0L);
            shipmentVo.setWebshipId(0L);
            shipmentVo.setAirbillNumber("");
            shipmentVo.setShipmentDate(currentDate);
            shipmentVo.setReceiverAddressId(0);
            shipmentVo.setShipmentTypeId(0);
            shipmentVo.setPackageId(0);
            shipmentVo.setWithInsurance(BigDecimal.valueOf(0D));
            shipmentVo.setCarrierCost(BigDecimal.valueOf(0D));
            shipmentVo.setDay(0);
            shipmentVo.setCurrencyCode("");
            shipmentVo.setSenderAddressId(0);
            shipmentVo.setReceiverAddressId(0);
            shipmentVo.setDimensionUnit("");
            shipmentVo.setWithInsurance(BigDecimal.valueOf(0D));
            shipmentVo.setShipmentTypeId(0);
            shipmentVo.setPackageId(0);
            shipmentVo.setContents(1);
            shipmentVo.setWeightUnit("");
            shipmentVo.setDimensionUnit("");
            shipmentVo.setCarrierCost(BigDecimal.valueOf(0D));
            shipmentVo.setCarrierPayment(1);
            shipmentVo.setDhlNote("");
            shipmentVo.setBillingCode("");
            shipmentVo.setCourierMessage("");
            shipmentVo.setDhlRoutingCode("");
            shipmentVo.setAwbBarcode("");
            shipmentVo.setOriginDestiBarcode("");
            shipmentVo.setDhlRoutingBarcode("");
            shipmentVo.setProductContentCode("");
            shipmentVo.setStatus(0);
            shipmentVo.setContentDescription("");
            shipmentVo.setCommercialInvoiceId(0);
            shipmentVo.setCollectionTypeId(0);
            shipmentVo.setBillingType(0);
            shipmentVo.setBillingAccount("");
            shipmentVo.setDutiesType(0);
            shipmentVo.setDutiesAccount("");
            shipmentVo.setTermOfTrade("");
            shipmentVo.setServiceAreaCodeOrigin("");
            shipmentVo.setServiceAreaCodeDestination("");
            shipmentVo.setReceiverTaxId("");
            shipmentVo.setReasonForExport("");
            shipmentVo.setPackingList(1);
            shipmentVo.setBoundStatus(1);
            shipmentVo.setSalesRepId(0L);
            shipmentVo.setOldCustomerCode(0L);
            if (quote.getBaseCharge() != null) {
                String baseCharge = StringUtils.substring(quote.getBaseCharge(), 2, quote.getBaseCharge().length());
                shipmentVo.setBaseCharge(BigDecimal.valueOf(Double.parseDouble(baseCharge)));
            }
            if (shipmentInfoVo.getReference() != null) {
                shipmentVo.setBillingAccount(shipmentInfoVo.getReference());
            }
            if (shipmentInfoVo.getBillingCode() != null) {
                shipmentVo.setBillingCode(shipmentInfoVo.getBillingCode());
            }
            if (shipmentInfoVo.getBillingType() != null) {
                shipmentVo.setBillingType(shipmentInfoVo.getBillingType());
            }
            if (shipmentInfoVo.getContentDescription() != null) {
                shipmentVo.setContentDescription(shipmentInfoVo.getContentDescription());
            }
            if (shipmentInfoVo.getContents() != null) {
                shipmentVo.setContents(shipmentInfoVo.getContents());
            }
            shipmentVo.setCreateDate(currentDate);
            if (shipmentInfoVo.getCustomerCode() != null) {
                if (StringUtils.isEmpty(shipmentInfoVo.getCustomerCode().toString())) {
                    shipmentVo.setCustomerCode(shipmentInfoVo.getCustomerCode());
                }
            }
            if (shipmentInfoVo.getWebshipId() != null) {
                shipmentVo.setWebshipId(shipmentInfoVo.getWebshipId());
            }
            if (quote.getAccessorial() != null) {
                shipmentVo.setNoOfPieces(quote.getAccessorial().size());
            }
            if (shipmentInfoVo.getPackageId() != null) {
                shipmentVo.setPackageId(shipmentInfoVo.getPackageId());
            }
            if (shipmentInfoVo.getReference() != null) {
                shipmentVo.setReference(shipmentInfoVo.getReference());
            }
            if (shipmentInfoVo.getShipmentTypeId() != null) {
                shipmentVo.setShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
            }
            if (shipmentInfoVo.getReceiverAddressId() != null) {
                shipmentVo.setReceiverAddressId(shipmentInfoVo.getReceiverAddressId());
            }
            if (shipmentInfoVo.getTermOfTrade() != null) {
                shipmentVo.setTermOfTrade(shipmentInfoVo.getTermOfTrade());
            }
            if (shipmentInfoVo.getWeightUnit() != null) {
                shipmentVo.setWeightUnit(shipmentInfoVo.getWeightUnit());
            }
            if (quote.getInsuranceValue() != null) {
                shipmentVo.setWithInsurance(BigDecimal.valueOf(Double.parseDouble(quote.getInsuranceValue())));
            }

            if (quote.getTotalCustomValue() != null) {
                shipmentVo.setTotalCustomValue(BigDecimal.valueOf(Double.parseDouble(quote.getTotalCustomValue())));
            }
            if (quote.getNonStandardCharge() != null) {
                shipmentVo.setNonStandardCharge(BigDecimal.valueOf(Double.parseDouble(quote.getNonStandardCharge())));
            }
            // Put signal to the context to log insert shipment as shipment request.
            addInfo.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Shipment Request");
            dao.insertShipment(addInfo, shipmentVo);
            for (AccessorialModel accessorial : quote.getAccessorial()) {
                if (accessorial.getAccessorialId() != null) {
                    ShipmentDetailVo shipmentDetailVo = new ShipmentDetailVo();
                    shipmentDetailVo.setAccessorialId(Long.parseLong(accessorial.getAccessorialId()));
                    shipmentDetailVo.setShipmentId(shipmentVo.getShipmentId());
                    String amout = StringUtils.substring(accessorial.getValue(), 2, accessorial.getValue().length());
                    shipmentDetailVo.setAmount(BigDecimal.valueOf(Double.parseDouble(amout)));
                    // Put signal to the context to log insert shipment detail as Shipment Detail.
                    addInfo.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Shipment Detail");
                    dDao.insert(addInfo, shipmentDetailVo);
                }
            }
            context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
            context.put(Attributes.SHIPMENT_REQUEST_VO, GsonUtils.toGson(shipmentVo));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
