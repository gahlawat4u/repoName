package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.QuotePieceVo;
import com.gms.xms.txndb.vo.WebshipQuoteLogDetailVo;
import com.gms.xms.txndb.vo.WebshipQuoteLogVo;

import java.util.List;

/**
 * Posted from ShipmentRequest
 * <p>
 * Author TanDT Date May 14, 2015
 */
public class ShipmentRequest extends BaseRequest {
    private AddressVo reciveAddress;
    private AddressVo senderAddress;
    private WebshipQuoteLogVo webshipQuoteLog;
    private List<WebshipQuoteLogDetailVo> listWebshipQuoteLogDetail;
    private List<QuotePieceVo> listQuotePiece;
    private Long quoteId;

    public AddressVo getReciveAddress() {
        return reciveAddress;
    }

    public void setReciveAddress(AddressVo reciveAddress) {
        this.reciveAddress = reciveAddress;
    }

    public AddressVo getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressVo senderAddress) {
        this.senderAddress = senderAddress;
    }

    public WebshipQuoteLogVo getWebshipQuoteLog() {
        return webshipQuoteLog;
    }

    public void setWebshipQuoteLog(WebshipQuoteLogVo webshipQuoteLog) {
        this.webshipQuoteLog = webshipQuoteLog;
    }

    public List<WebshipQuoteLogDetailVo> getListWebshipQuoteLogDetail() {
        return listWebshipQuoteLogDetail;
    }

    public void setListWebshipQuoteLogDetail(List<WebshipQuoteLogDetailVo> listWebshipQuoteLogDetail) {
        this.listWebshipQuoteLogDetail = listWebshipQuoteLogDetail;
    }

    public List<QuotePieceVo> getListQuotePiece() {
        return listQuotePiece;
    }

    public void setListQuotePiece(List<QuotePieceVo> listQuotePiece) {
        this.listQuotePiece = listQuotePiece;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    @Override
    public String toString() {
        return "ShipmentRequest [reciveAddress=" + reciveAddress + ", senderAddress=" + senderAddress + ", webshipQuoteLog=" + webshipQuoteLog + ", listWebshipQuoteLogDetail=" + listWebshipQuoteLogDetail + ", listQuotePiece=" + listQuotePiece + ", quoteId=" + quoteId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((listQuotePiece == null) ? 0 : listQuotePiece.hashCode());
        result = prime * result + ((listWebshipQuoteLogDetail == null) ? 0 : listWebshipQuoteLogDetail.hashCode());
        result = prime * result + ((quoteId == null) ? 0 : quoteId.hashCode());
        result = prime * result + ((reciveAddress == null) ? 0 : reciveAddress.hashCode());
        result = prime * result + ((senderAddress == null) ? 0 : senderAddress.hashCode());
        result = prime * result + ((webshipQuoteLog == null) ? 0 : webshipQuoteLog.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShipmentRequest other = (ShipmentRequest) obj;
        if (listQuotePiece == null) {
            if (other.listQuotePiece != null)
                return false;
        } else if (!listQuotePiece.equals(other.listQuotePiece))
            return false;
        if (listWebshipQuoteLogDetail == null) {
            if (other.listWebshipQuoteLogDetail != null)
                return false;
        } else if (!listWebshipQuoteLogDetail.equals(other.listWebshipQuoteLogDetail))
            return false;
        if (quoteId == null) {
            if (other.quoteId != null)
                return false;
        } else if (!quoteId.equals(other.quoteId))
            return false;
        if (reciveAddress == null) {
            if (other.reciveAddress != null)
                return false;
        } else if (!reciveAddress.equals(other.reciveAddress))
            return false;
        if (senderAddress == null) {
            if (other.senderAddress != null)
                return false;
        } else if (!senderAddress.equals(other.senderAddress))
            return false;
        if (webshipQuoteLog == null) {
            if (other.webshipQuoteLog != null)
                return false;
        } else if (!webshipQuoteLog.equals(other.webshipQuoteLog))
            return false;
        return true;
    }

}
