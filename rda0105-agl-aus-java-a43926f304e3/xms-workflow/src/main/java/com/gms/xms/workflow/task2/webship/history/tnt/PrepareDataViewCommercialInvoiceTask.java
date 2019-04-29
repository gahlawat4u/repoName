package com.gms.xms.workflow.task2.webship.history.tnt;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryProductAirbillModel;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryProductAirbillVo;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.HistoryHelperUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from PrepareDataViewPackingListTask
 * <p>
 * Author TANDT
 */
public class PrepareDataViewCommercialInvoiceTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataViewCommercialInvoiceTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            HistoryViewFileRequest request = context.get(Attributes.HISTORY_VIEW_AIRBILL_REQUEST);
            Long shipmentId = request.getShipmentId();

            HistoryDetailFilter filter = HistoryHelperUtils.prepareHistoryDetailFilter(shipmentId);
            IHistoryDetailService detailService = new HistoryDetailServiceImp();
            HistoryDetailInfoVo historyDetailInfo = detailService.selectHistoryDetailInfo(filter);
            IHistoryDetailService service = new HistoryDetailServiceImp();
            List<HistoryProductAirbillModel> productAirbillModels = new ArrayList<HistoryProductAirbillModel>();
            List<HistoryProductAirbillVo> productAirbillVos = new ArrayList<HistoryProductAirbillVo>();
            productAirbillVos = service.selectHistoryProductAirbill(shipmentId);
            Double totalAmount = 0D;
            if (productAirbillVos.size() > 0) {
                for (HistoryProductAirbillVo productAirbillVo : productAirbillVos) {
                    if (productAirbillVo.getQty() > 0) {
                        productAirbillVo.setAmount(MathUtils.round(productAirbillVo.getAmount(), 2));
                        productAirbillVo.setPrice(productAirbillVo.getAmount() / productAirbillVo.getQty());
                        productAirbillVo.setPrice(MathUtils.round(productAirbillVo.getPrice(), 2));
                        totalAmount += productAirbillVo.getAmount();
                    }
                }
            }
            productAirbillModels = ModelUtils.createListModelFromVo(productAirbillVos, HistoryProductAirbillModel.class);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("detailInfoModel", historyDetailInfo);
            data.put("ttAmount", totalAmount);
            data.put("productAirbillModels", productAirbillModels);
            data.put("isThermalLable", "0");

            context.put(Attributes.VIEW_AIRBILL_DATA, data);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
