package com.gms.xms.model.massedit;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.editairbill.AccessorialInfoModel;

import java.util.List;

/**
 * Posted from Jul 19, 2016 2:37:19 PM
 * <p>
 * Author huynd
 */
public class MassAccessorialInfoModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    public List<AccessorialInfoModel> accessorialInfo;

    public List<AccessorialInfoModel> getAccessorialInfo() {
        return accessorialInfo;
    }

    public void setAccessorialInfo(List<AccessorialInfoModel> accessorialInfo) {
        this.accessorialInfo = accessorialInfo;
    }

    @Override
    public String toString() {
        return "MassAccessorialInfoModel [accessorialInfo=" + accessorialInfo + "]";
    }
}
