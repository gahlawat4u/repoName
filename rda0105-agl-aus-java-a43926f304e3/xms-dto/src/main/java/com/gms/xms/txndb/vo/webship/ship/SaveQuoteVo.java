package com.gms.xms.txndb.vo.webship.ship;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;

/**
 * Posted from SaveQuoteVo
 * <p>
 * Author DatTV Date Jul 24, 2015 4:27:15 PM
 */
public class SaveQuoteVo extends BaseVo {

    private static final long serialVersionUID = 2905226127370431136L;

    private ShipmentInfoVo shipment;
    private QuoteVo quote;

    @Override
    public String toString() {
        return "SaveQuoteLogVo [shipment=" + shipment + ", quote=" + quote + "]";
    }

    public ShipmentInfoVo getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentInfoVo shipment) {
        this.shipment = shipment;
    }

    public QuoteVo getQuote() {
        return quote;
    }

    public void setQuote(QuoteVo quote) {
        this.quote = quote;
    }
}
