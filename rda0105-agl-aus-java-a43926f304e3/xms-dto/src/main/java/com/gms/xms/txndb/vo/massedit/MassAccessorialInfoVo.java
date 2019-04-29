package com.gms.xms.txndb.vo.massedit;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;

import java.util.List;

/**
 * Posted from Jul 19, 2016 2:37:19 PM
 * <p>
 * Author huynd
 */
public class MassAccessorialInfoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    public List<AccessorialInfoVo> accessorialInfo;

    public List<AccessorialInfoVo> getAccessorialInfo() {
        return accessorialInfo;
    }

    public void setAccessorialInfo(List<AccessorialInfoVo> accessorialInfo) {
        this.accessorialInfo = accessorialInfo;
    }

    @Override
    public String toString() {
        return "MassAccessorialInfoVo [accessorialInfo=" + accessorialInfo + "]";
    }
}
