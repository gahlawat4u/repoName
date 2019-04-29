package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.persistence.service.massedit.IMassEditService;
import com.gms.xms.persistence.service.massedit.MassEditServiceImp;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from Aug 4, 2016 9:42:57 AM
 * <p>
 * Author huynd
 */
public class ForceMarkupCustomerCostController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private String txtMarkup;
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
            if (validateMarkup()) {
                List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
                Long count = 0L;
                IMassEditService service = new MassEditServiceImp();
                for (ListAirbillForMassEditModel list : listAirbill) {
                    service.forceMarkupCustomerCost(this.getAddInfoMap(), Long.valueOf(list.getShipmentId()), list.getAirbillNumber(), Double.valueOf(this.getTxtMarkup()));
                    count++;
                }
                this.setCount(count);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private boolean validateMarkup() {
        if (StringUtils.isBlank(this.getTxtMarkup())) {
            addFieldError("txtMarkup", "Please enter markup");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public String getTxtMarkup() {
        return txtMarkup;
    }

    public void setTxtMarkup(String txtMarkup) {
        this.txtMarkup = txtMarkup;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
