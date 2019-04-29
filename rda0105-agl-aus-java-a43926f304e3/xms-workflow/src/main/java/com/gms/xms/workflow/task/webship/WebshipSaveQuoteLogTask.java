package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.QuotePieceDao;
import com.gms.xms.persistence.dao.WebshipQuoteLogDao;
import com.gms.xms.persistence.dao.WebshipQuoteLogDetailDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.QuotePieceVo;
import com.gms.xms.txndb.vo.WebshipQuoteLogDetailVo;
import com.gms.xms.txndb.vo.WebshipQuoteLogVo;
import com.gms.xms.workflow.client.integration.request.ShipmentRequest;
import com.gms.xms.workflow.client.integration.response.ShipmentResponse;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Posted from WebshipSaveQuoteLogTask
 * <p>
 * Author TanDT Date May 14, 2015
 */
public class WebshipSaveQuoteLogTask implements Task {
    private static final Log log = LogFactory.getLog(WebshipSaveQuoteLogTask.class);

    public boolean execute(ContextBase context) throws Exception {
        AddressDao addressDao = new AddressDao();
        WebshipQuoteLogDao webshipQuoteLogDao = new WebshipQuoteLogDao();
        WebshipQuoteLogDetailDao webshipQuoteLogDetailDao = new WebshipQuoteLogDetailDao();
        QuotePieceDao quotePieceDao = new QuotePieceDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            ShipmentRequest request = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_REQUEST), ShipmentRequest.class);
            ShipmentResponse response = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_RESPONSE), ShipmentResponse.class);
            // Insert Address
            AddressVo reciveAddress = request.getReciveAddress();
            AddressVo senderAddress = request.getSenderAddress();
            // Put signal to the context to log insert address as receiver address.
            addInfo.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Receiver Address");
            addressDao.insert(addInfo, reciveAddress);
            // Put signal to the context to log insert address as sender address.
            addInfo.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Sender Address");
            addressDao.insert(addInfo, senderAddress);
            // -----Set address for response
            response.setReciveAddress(reciveAddress);
            response.setSenderAddress(senderAddress);
            // Insert WebshipQuoteLog using addressId
            WebshipQuoteLogVo webshipQuoteLogVo = request.getWebshipQuoteLog();
            webshipQuoteLogVo.setReceiverAddressId(reciveAddress.getAddressId());
            webshipQuoteLogVo.setSenderAddressId(senderAddress.getAddressId());
            webshipQuoteLogDao.insert(addInfo, webshipQuoteLogVo);
            // -------- Set WebshipQuoteLog for response
            response.setWebshipQuoteLog(webshipQuoteLogVo);
            response.setQuoteId(webshipQuoteLogVo.getQuoteId());
            // Insert Quote Piece
            List<QuotePieceVo> listQuotePiece = request.getListQuotePiece();
            List<QuotePieceVo> listQuotePieceResponse = new ArrayList<QuotePieceVo>();
            for (QuotePieceVo quotePieceVo : listQuotePiece) {
                quotePieceVo.setQuoteId(response.getQuoteId());
                quotePieceDao.insert(addInfo, quotePieceVo);
                listQuotePieceResponse.add(quotePieceVo);
            }
            // ------Set list quote piece for response
            response.setListQuotePiece(listQuotePieceResponse);
            // Insert Webship Quote Log Detail
            List<WebshipQuoteLogDetailVo> listWebshipQuoteLogDetail = request.getListWebshipQuoteLogDetail();
            List<WebshipQuoteLogDetailVo> listWebshipQuoteLogDetailResponse = new ArrayList<WebshipQuoteLogDetailVo>();
            for (WebshipQuoteLogDetailVo webshipQuoteLogDetailVo : listWebshipQuoteLogDetail) {
                webshipQuoteLogDetailVo.setQuoteId(response.getQuoteId());
                webshipQuoteLogDetailDao.insert(addInfo, webshipQuoteLogDetailVo);
                listWebshipQuoteLogDetailResponse.add(webshipQuoteLogDetailVo);
            }
            // Set Webship Quote Log Detail for response
            response.setListWebshipQuoteLogDetail(listWebshipQuoteLogDetailResponse);
            context.put(Attributes.SHIPMENT_RESPONSE, GsonUtils.toGson(response));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
