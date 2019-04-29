package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.TradeTypeDao;
import com.gms.xms.txndb.vo.webship.TradeTypeVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class GetTradeTypeListTask implements Task {
    private static final Log log = LogFactory.getLog(GetTradeTypeListTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        TradeTypeDao tradeTypeDao = new TradeTypeDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            List<TradeTypeVo> tradeTypeVoList = tradeTypeDao.getTradeTypeList();
            context.put(Attributes.TRADE_TYPE_LIST_RESULT, GsonUtils.toGson(tradeTypeVoList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
