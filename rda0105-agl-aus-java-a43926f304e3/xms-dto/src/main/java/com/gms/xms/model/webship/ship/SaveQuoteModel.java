package com.gms.xms.model.webship.ship;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.webship.ShipmentInfoModel;

/**
 * Posted from SaveQuoteModel
 * <p>
 * Author DatTV Date Jul 24, 2015 4:27:30 PM
 */
public class SaveQuoteModel extends BaseModel {

    private static final long serialVersionUID = 2905226127370431136L;

    private ShipmentInfoModel shipment;
    private QuoteModel quote;

    @Override
    public String toString() {
        return "SaveQuoteLogModel [shipment=" + shipment + ", quote=" + quote + "]";
    }

    public ShipmentInfoModel getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentInfoModel shipment) {
        this.shipment = shipment;
    }

    public QuoteModel getQuote() {
        return quote;
    }

    public void setQuote(QuoteModel quote) {
        this.quote = quote;
    }
}
