package com.gms.xms.weblib.controller.webship.history;

/**
 * Created by thinhdd
 * Date 6/9/2017.
 */
public enum HistoryDynamicCL {
    carrier("Carrier","serviceName"),
    void_status("Voided","voidStatus"),
    tracking("Tracking#","airbillNumber"),
    date("Date","createDate"),
    ship_date("Ship Date","shipDate"),
    pieces("Pieces","noOfPieces"),
    service("Service","shipmentType"),
    th_package("Package","packageName"),
    weight("Weight","weight"),
    th_dimensions("Dimension","dimensions"),
    quoted("Quoted","total"),
    insured("Insured Amount","withInsurance"),
    scheduled("Scheduled","schedule"),
    scheduled_collection_timestamp("Timestamp","schcollTimeStamp"),
    collection_info("Collection Information Timestamp","confirmationNo"),
    th_shipment_reference("Reference","reference"),
    th_billing_party("Billing Party","billingParty"),
    th_sender_company("Sender Company","senderCompany"),
    th_sender_contact("Sender Contact","senderName"),
    th_sender_location("Sender Location","senderLocation"),
    th_receiver_company("Receiver Company","reciverCompany"),
    th_receiver_contact("Receiver Contact","reciverContact"),
    destination("Destination","destinations"),
    dest_country("Dest. Country","destCountry");

    String title;
    String fielName;

    HistoryDynamicCL(String title, String fielName) {
        this.title = title;
        this.fielName = fielName;
    }

    HistoryDynamicCL() {
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFielName() {
        return fielName;
    }

    public void setFielName(String fielName) {
        this.fielName = fielName;
    }
}
