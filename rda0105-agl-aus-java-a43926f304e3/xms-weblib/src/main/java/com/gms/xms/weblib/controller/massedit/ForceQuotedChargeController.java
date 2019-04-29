package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.persistence.service.massedit.IMassEditService;
import com.gms.xms.persistence.service.massedit.MassEditServiceImp;

import java.util.List;

/**
 * Posted from Aug 3, 2016 2:49:18 PM
 * <p>
 * Author huynd
 */
public class ForceQuotedChargeController extends MassEditController {

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

    public String save() {
        try {
            determineAdminLevel();
            List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
            Long count = 0L;
            IMassEditService service = new MassEditServiceImp();
            for (ListAirbillForMassEditModel list : listAirbill) {
                service.forceQuotedCharge(this.getAddInfoMap(), Long.valueOf(list.getShipmentId()), list.getAirbillNumber(), this.getAdminLevel());
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
