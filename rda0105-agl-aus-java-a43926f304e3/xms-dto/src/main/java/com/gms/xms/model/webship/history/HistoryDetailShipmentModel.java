package com.gms.xms.model.webship.history;

/**
 * Created by thinhdd on 2/15/2017.
 */
public class HistoryDetailShipmentModel {
    private String isDg;
    private String consignmentNo;
    private String senderRef;
    private String receiverName;
    private String destination;
    private Integer items;
    private String weight;
    private String cubic;

    public HistoryDetailShipmentModel() {
    }

    public HistoryDetailShipmentModel(String isDg, String consignmentNo, String senderRef, String receiverName, String destination, Integer items, String weight, String cubic) {
        this.isDg = isDg;
        this.consignmentNo = consignmentNo;
        this.senderRef = senderRef;
        this.receiverName = receiverName;
        this.destination = destination;
        this.items = items;
        this.weight = weight;
        this.cubic = cubic;
    }

    public String getIsDg() {
        return isDg;
    }

    public void setIsDg(String isDg) {
        this.isDg = isDg;
    }

    public String getConsignmentNo() {
        return consignmentNo;
    }

    public void setConsignmentNo(String consignmentNo) {
        this.consignmentNo = consignmentNo;
    }

    public String getSenderRef() {
        return senderRef;
    }

    public void setSenderRef(String senderRef) {
        this.senderRef = senderRef;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCubic() {
        return cubic;
    }

    public void setCubic(String cubic) {
        this.cubic = cubic;
    }
}
