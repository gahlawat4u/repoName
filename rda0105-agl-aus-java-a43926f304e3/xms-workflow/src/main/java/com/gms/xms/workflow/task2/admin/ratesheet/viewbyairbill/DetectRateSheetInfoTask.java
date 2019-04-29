package com.gms.xms.workflow.task2.admin.ratesheet.viewbyairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.admin.customer.baserate.ServiceTypeVo;
import com.gms.xms.workflow.core2.Task2;

/**
 * Posted from May 16, 2016 3:45:33 PM
 * <p>
 * Author dattrinh
 */
public class DetectRateSheetInfoTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ServiceTypeVo serviceTypeVo = context.get(Attributes.SERVICE_TYPE_VO);
            // Detect rate sheet info.
            Long rateSheetId = 0L;
            Long perWeightRateSheetId = 0L;
            Long ncRateSheetId = 0L;
            Long ncPerWeightRateSheetId = 0L;
            switch (serviceTypeVo.getDocumentType()) {
                case 0: // Document
                    serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Documents");
                    if (serviceTypeVo.getAllowCarrier()) {
                        rateSheetId = serviceTypeVo.getCarrierDocRate();
                    }
                    if (serviceTypeVo.getAllowNonCarrier()) {
                        ncRateSheetId = serviceTypeVo.getNonCarrierDocRate();
                    }
                    break;
                case 1: // Document In-bound
                    serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Documents (Inbound)");
                    if (serviceTypeVo.getAllowCarrier()) {
                        rateSheetId = serviceTypeVo.getCarrierDocInRate();
                    }
                    if (serviceTypeVo.getAllowNonCarrier()) {
                        ncRateSheetId = serviceTypeVo.getNonCarrierDocInRate();
                    }
                    break;
                case 2: // Package
                    serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Package");
                    if (serviceTypeVo.getAllowCarrier()) {
                        rateSheetId = serviceTypeVo.getCarrierPackRate();
                        perWeightRateSheetId = serviceTypeVo.getCarrierPackPerWeightRate();
                    }
                    if (serviceTypeVo.getAllowNonCarrier()) {
                        ncRateSheetId = serviceTypeVo.getNonCarrierPackRate();
                        ncPerWeightRateSheetId = serviceTypeVo.getNonCarrierPackPerWeightRate();
                    }
                    break;
                case 3: // Package In-bound
                    serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Package (Inbound)");
                    if (serviceTypeVo.getAllowCarrier()) {
                        rateSheetId = serviceTypeVo.getCarrierPackInRate();
                        perWeightRateSheetId = serviceTypeVo.getCarrierPackInPerWeightRate();
                    }
                    if (serviceTypeVo.getAllowNonCarrier()) {
                        ncRateSheetId = serviceTypeVo.getNonCarrierPackInRate();
                        ncPerWeightRateSheetId = serviceTypeVo.getNonCarrierPackInPerWeightRate();
                    }
                    break;
                case 4: // Pak
                    serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Pak");
                    if (serviceTypeVo.getAllowCarrier()) {
                        rateSheetId = serviceTypeVo.getCarrierPakRate();
                        perWeightRateSheetId = serviceTypeVo.getCarrierPakPerWeightRate();
                    }
                    if (serviceTypeVo.getAllowNonCarrier()) {
                        ncRateSheetId = serviceTypeVo.getNonCarrierPakRate();
                        ncPerWeightRateSheetId = serviceTypeVo.getNonCarrierPakPerWeightRate();
                    }
                    break;
                case 5: // Pak In-bound
                    serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Pak (Inbound)");
                    if (serviceTypeVo.getAllowCarrier()) {
                        rateSheetId = serviceTypeVo.getCarrierPakInRate();
                        perWeightRateSheetId = serviceTypeVo.getCarrierPakInPerWeightRate();
                    }
                    if (serviceTypeVo.getAllowNonCarrier()) {
                        ncRateSheetId = serviceTypeVo.getNonCarrierPakInRate();
                        ncPerWeightRateSheetId = serviceTypeVo.getNonCarrierPakInPerWeightRate();
                    }
                    break;
                case 6: // Non Type
                    serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName());
                    if (serviceTypeVo.getAllowCarrier()) {
                        rateSheetId = serviceTypeVo.getCarrierDocRate();
                    }
                    if (serviceTypeVo.getAllowNonCarrier()) {
                        ncRateSheetId = serviceTypeVo.getNonCarrierDocRate();
                    }
                    break;
                default:
                    throw new Exception("Unknown document type.");
            }
            serviceTypeVo.setRateSheetId(rateSheetId);
            serviceTypeVo.setPerWeightRateSheetId(perWeightRateSheetId);
            serviceTypeVo.setNcRateSheetId(ncRateSheetId);
            serviceTypeVo.setNcPerWeightRateSheetId(ncPerWeightRateSheetId);
            // Update into the context.
            context.put(Attributes.SERVICE_TYPE_VO, serviceTypeVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
