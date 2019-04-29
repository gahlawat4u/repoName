package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.persistence.dao.massedit.MassEditDao;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from May 11, 2016 11:34:19 AM
 * <p>
 * Author huynd
 */
public class ChangeWeightController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private String txtWeight;
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
            if (validateChangeWeight()) {
                List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
                ShipmentBillingVo shipmentBilling;
                MassEditDao massEditDao = new MassEditDao();
                Long count = 0L;
                for (ListAirbillForMassEditModel list : listAirbill) {
                    shipmentBilling = new ShipmentBillingVo();
                    shipmentBilling.setWeight(Float.valueOf(this.getTxtWeight()));
                    shipmentBilling.setShipmentId(Long.valueOf(list.getShipmentId()));
                    shipmentBilling.setAirbillNumber(list.getAirbillNumber());
                    massEditDao.massEditChangeWeight(this.getAddInfoMap(), shipmentBilling);
                    count++;
                }
                this.setCount(count);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private boolean validateChangeWeight() {
        if (StringUtils.isBlank(this.getTxtWeight())) {
            addFieldError("txtWeight", "Please enter weight");
        }
        if (StringUtils.isBlank(this.getListAirbillStr())) {
            addFieldError("txtWeight", "Please choose airbill(s)!");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public String getTxtWeight() {
        return txtWeight;
    }

    public void setTxtWeight(String txtWeight) {
        this.txtWeight = txtWeight;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
