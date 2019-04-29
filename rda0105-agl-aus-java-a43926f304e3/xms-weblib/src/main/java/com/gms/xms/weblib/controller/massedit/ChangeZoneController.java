package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.persistence.service.massedit.IMassEditService;
import com.gms.xms.persistence.service.massedit.MassEditServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.workflow.utils.RecalculateChargeImp;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from Aug 1, 2016 5:26:19 PM
 * <p>
 * Author huynd
 */
public class ChangeZoneController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private String txtZone;
    private Boolean checkRecalcCustomerCost;
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
            if (validateChangeZone()) {
                List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
                Long count = 0L;
                IMassEditService service = new MassEditServiceImp();
                Boolean checkRecalcCustomerCost = (this.getCheckRecalcCustomerCost() != null) ? true : false;
                for (ListAirbillForMassEditModel list : listAirbill) {
                    IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(this.getAddInfoMap());
                    service.changeZone(this.getAddInfoMap(), Long.valueOf(list.getShipmentId()), list.getAirbillNumber(), this.getTxtZone(), checkRecalcCustomerCost, iRecalculateCharge);
                    count++;
                }
                this.setCount(count);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private boolean validateChangeZone() {
        if (StringUtils.isBlank(this.getTxtZone())) {
            addFieldError("txtZone", "Please enter zone");
        }
        if (StringUtils.isBlank(this.getListAirbillStr())) {
            addFieldError("txtZone", "Please choose airbill(s)!");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public String getTxtZone() {
        return txtZone;
    }

    public void setTxtZone(String txtZone) {
        this.txtZone = txtZone;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Boolean getCheckRecalcCustomerCost() {
        return checkRecalcCustomerCost;
    }

    public void setCheckRecalcCustomerCost(Boolean checkRecalcCustomerCost) {
        this.checkRecalcCustomerCost = checkRecalcCustomerCost;
    }
}
