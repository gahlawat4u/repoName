package com.gms.xms.workflow.render.email.tollpriority;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.email.tollpriority.TollPriorityShipmentRequestEmailModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.email.tollpriority.TollPriorityShipmentRequestEmailVo;

import java.util.HashMap;
import java.util.Map;

public class TollPriorityEmailRenderImp implements ITollPriorityEmailRender {
    @Override
    public void generateShipmentRequestEmailMessage(TollPriorityShipmentRequestEmailVo requestEmailVo, String outputFilePath) throws Exception {
        TollPriorityShipmentRequestEmailModel requestEmailModel = ModelUtils.createModelFromVo(requestEmailVo, TollPriorityShipmentRequestEmailModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", requestEmailModel);
        AppUtils.template2File(outputFilePath, false, "templates/email/toll_priority/email_toll_priority_shipment_request.ftl", data);
    }
}