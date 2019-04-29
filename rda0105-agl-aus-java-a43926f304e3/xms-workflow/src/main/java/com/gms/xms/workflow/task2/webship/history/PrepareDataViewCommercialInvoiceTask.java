package com.gms.xms.workflow.task2.webship.history;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.model.webship.history.HistoryProductAirbillModel;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryProductAirbillVo;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.HistoryHelperUtils;
import org.apache.commons.lang.StringUtils;
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
            HistoryDetailInfoModel detailInfoModel = ModelUtils.createModelFromVo(historyDetailInfo, HistoryDetailInfoModel.class);
            Map<String, String> termOfTrade = this.getMapTermOfTrade();
            String termOfTradeStr = termOfTrade.get(detailInfoModel.getTermOfTrade());
            if (!StringUtils.isBlank(termOfTradeStr)) {
                detailInfoModel.setTermOfTrade(termOfTradeStr);
            }
            productAirbillModels = ModelUtils.createListModelFromVo(productAirbillVos, HistoryProductAirbillModel.class);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(AppConstants.CURRENCY_SYMBOL));
            data.put("detailInfoModel", detailInfoModel);
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

    private Map<String, String> getMapTermOfTrade() {
        Map<String, String> termOfTrade = new HashMap<>();
        termOfTrade.put("CFR", "CFR - Cost & Freight");
        termOfTrade.put("CIF", "CIF - Cost, Insurance");
        termOfTrade.put("CIP", "CIP - Carriage & Insur Paid To");
        termOfTrade.put("CPT", "CPT - Carriage Paid To");
        termOfTrade.put("DAF", "DAF - Delivered At Frontier");
        termOfTrade.put("DTP", "DTP - Delivery Duty Paid");
        termOfTrade.put("DDP", "DDP - Delivery Duty Paid");
        termOfTrade.put("DDU", "DDU - Delivery Duty Unpaid");
        termOfTrade.put("DTU", "DTU - Delivery Duty Unpaid");
        termOfTrade.put("DEQ", "DEQ - Delivered Ex Quay");
        termOfTrade.put("DES", "DES - Delivered Ex Ship");
        termOfTrade.put("EXW", "EXW - Ex Works");
        return termOfTrade;
    }
}
