package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.persistence.service.massedit.IMassEditService;
import com.gms.xms.persistence.service.massedit.MassEditServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.workflow.utils.RecalculateChargeImp;

import java.util.List;

/**
 * Posted from Aug 3, 2016 10:43:11 AM
 * <p>
 * Author huynd
 */
public class RecalcCustomerCostController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private Long count;

    public String show() {
        try {
            this.buildListAirbill();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String recalculate() {
        try {
            List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
            Long count = 0L;
            IMassEditService service = new MassEditServiceImp();
            for (ListAirbillForMassEditModel list : listAirbill) {
                IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(this.getAddInfoMap());
                service.recalcCustomerCost(this.getAddInfoMap(), Long.valueOf(list.getShipmentId()), list.getAirbillNumber(), iRecalculateCharge);
                count++;
            }
            this.setCount(count);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
