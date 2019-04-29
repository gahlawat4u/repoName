package com.gms.xms.workflow.render.email.tollpriority;

import com.gms.xms.txndb.vo.email.tollpriority.TollPriorityShipmentRequestEmailVo;

public interface ITollPriorityEmailRender {
    public void generateShipmentRequestEmailMessage(TollPriorityShipmentRequestEmailVo requestEmailVo, String outputFilePath) throws Exception;
}
