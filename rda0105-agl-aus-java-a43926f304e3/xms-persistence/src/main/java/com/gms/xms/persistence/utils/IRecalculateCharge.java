package com.gms.xms.persistence.utils;

import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;

/**
 * Posted from Jul 13, 2016 5:39:16 PM
 * <p>
 * Author dattrinh
 */
public interface IRecalculateCharge {
    ContextBase2 recalculateBaseCharge(Long customerCode, ShipmentBillingVo shipmentBilling) throws Exception;

    ContextBase2 recalculateBaseCharge(Long customerCode, SaveImportBillingVo saveImportBillingVo) throws Exception;
}
