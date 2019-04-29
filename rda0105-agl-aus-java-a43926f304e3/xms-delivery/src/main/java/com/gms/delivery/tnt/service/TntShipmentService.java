package com.gms.delivery.tnt.service;

import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.delivery.tnt.xmlpi.shipping.request.*;
import com.gms.delivery.tnt.xmlpi.shipping.request.Package;
import com.gms.delivery.tnt.xmlpi.shipping.response.Document;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.shipment.TntShipmentRequestVo;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.JAXBException;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Posted from TntShipmentService
 * <p>
 * Author dattrinh Jan 28, 2016 11:54:12 AM
 */
@SuppressWarnings("deprecation")
public class TntShipmentService {
    public String execute(String xmlRequest, String xmlResponseCached) {
        // Write xml-request to file.
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/tnt_intl_shipment_request_" + uniqueString + ".xml");
        // Return if the response was cached.
        if (!StringUtils.isBlank(xmlResponseCached)) {
            return xmlResponseCached;
        }
        // Create post object to send xml data to TNT server using post method.
        PostMethod post = null;
        String xmlResponse;
        try {
            post = new PostMethod(SystemSettingCache.getInstance().getValueByKey("TNT AU URL"));
            post.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream("xml_in=" + URLEncoder.encode(xmlRequest, "UTF-8"))));
            // Send request to get access key.
            int resultCode = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
            if (resultCode == 200) {
                xmlResponse = post.getResponseBodyAsString();
            } else {
                xmlResponse = null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            post.releaseConnection();
        }

        // Get access key that returned from TNT server.
        String[] result = xmlResponse != null ? xmlResponse.split(":") : null;
        if (result != null && "COMPLETE".equalsIgnoreCase(result[0])) {
            String sendBackRequest = "GET_RESULT:" + result[1];
            PostMethod postBack = null;
            // Send access key to get result.
            try {
                postBack = new PostMethod(SystemSettingCache.getInstance().getValueByKey("TNT AU URL"));
                postBack.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                postBack.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream("xml_in=" + URLEncoder.encode(sendBackRequest, "UTF-8"))));
                int resultCode = HttpClientFactory.getInstance().getHttpClient().executeMethod(postBack);
                if (resultCode == 200) {
                    xmlResponse = postBack.getResponseBodyAsString();
                    AppUtils.writeToFileByDate(xmlResponse, "/tnt_intl_shipment_response_" + uniqueString + ".xml");
                } else {
                    xmlResponse = null;
                }
                return xmlResponse;
            } catch (Exception e) {
                return null;
            } finally {
                postBack.releaseConnection();
            }
        } else {
            return null;
        }
    }

    public Document parseResponse(String xmlResponse) throws JAXBException {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        return AppUtils.xmlString2Object(xmlResponse, Document.class);
    }

    public String prepareXmlRequest(TntShipmentRequestVo shipmentRequestVo) throws JAXBException {
        EShipper eShipper = new EShipper();
        // Create Login.
        Login login = new Login();
        login.setCOMPANY(SystemSettingCache.getInstance().getValueByKey("TNT AU Web Service Company"));
        login.setPASSWORD(SystemSettingCache.getInstance().getValueByKey("TNT AU Web Service Password"));
        login.setAPPID(SystemSettingCache.getInstance().getValueByKey("TNT AU Web Service Appid"));
        login.setAPPVERSION(SystemSettingCache.getInstance().getValueByKey("TNT AU Web Service Appversion"));
        eShipper.setLOGIN(login);
        // Create ConsignmentBatch.
        ConsignmentBatch consignmentBatch = new ConsignmentBatch();
        String paymentInd = "S";
        if ("AU".equalsIgnoreCase(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getCountryDetail().getCountryCode()) && (shipmentRequestVo.getShipmentInfo().getSenderAddress().getCountry() != shipmentRequestVo.getShipmentInfo().getReceiverAddress().getCountry())) {
            paymentInd = "R";
        }
        // 1. ConsignmentBatch - Sender.
        Sender sender = new Sender();
        sender.setCOMPANYNAME(shipmentRequestVo.getShipmentInfo().getSenderAddress().getCompanyName());
        sender.setSTREETADDRESS1(shipmentRequestVo.getShipmentInfo().getSenderAddress().getAddress());
        sender.setSTREETADDRESS2(shipmentRequestVo.getShipmentInfo().getSenderAddress().getAddress2());
        sender.setCITY(shipmentRequestVo.getShipmentInfo().getSenderAddress().getCity());
        sender.setPROVINCE(shipmentRequestVo.getShipmentInfo().getSenderAddress().getState());
        sender.setPOSTCODE(shipmentRequestVo.getShipmentInfo().getSenderAddress().getPostalCode());
        String countryCode = shipmentRequestVo.getShipmentInfo().getSenderAddress().getCountryDetail().getCountryCode();
        sender.setCOUNTRY(countryCode);
        if (paymentInd.equalsIgnoreCase("S")) {
            sender.setACCOUNT(SystemSettingCache.getInstance().getValueByKey("TNT AU Account"));
        } else {
            sender.setACCOUNT("");
        }
        sender.setCONTACTNAME(shipmentRequestVo.getShipmentInfo().getSenderAddress().getContactName());
        String senderPhone = shipmentRequestVo.getShipmentInfo().getSenderAddress().getPhone();
        sender.setCONTACTDIALCODE(senderPhone.substring(0, 5));
        sender.setCONTACTTELEPHONE(senderPhone.substring(5, senderPhone.length()));
        sender.setCONTACTEMAIL(shipmentRequestVo.getShipmentInfo().getSenderAddress().getEmail());
        // Create Collection.
        Collection collection = new Collection();
        // 1. Collection - CollectionAddress.
        CollectionAddress collectionAddress = new CollectionAddress();
        collectionAddress.setCOMPANYNAME(shipmentRequestVo.getScheduleCollection().getPickupAddress().getCompanyName());
        collectionAddress.setSTREETADDRESS1(shipmentRequestVo.getScheduleCollection().getPickupAddress().getAddress());
        collectionAddress.setCITY(shipmentRequestVo.getScheduleCollection().getPickupAddress().getCity());
        collectionAddress.setPROVINCE(shipmentRequestVo.getScheduleCollection().getPickupAddress().getState());
        collectionAddress.setPOSTCODE(shipmentRequestVo.getScheduleCollection().getPickupAddress().getPostalCode());
        collectionAddress.setCOUNTRY(shipmentRequestVo.getScheduleCollection().getPickupAddress().getCountryDetail().getCountryCode());
        collectionAddress.setCONTACTNAME(shipmentRequestVo.getScheduleCollection().getPickupAddress().getContactName());
        String pickupPhone = shipmentRequestVo.getScheduleCollection().getPickupAddress().getPhone();
        collectionAddress.setCONTACTDIALCODE(pickupPhone.substring(0, 5));
        collectionAddress.setCONTACTTELEPHONE(pickupPhone.substring(5, pickupPhone.length()));
        collectionAddress.setCONTACTEMAIL(shipmentRequestVo.getScheduleCollection().getPickupAddress().getEmail());
        collection.setCOLLECTIONADDRESS(collectionAddress);
        // 2. Collection - ShipDate.
        String dateFormat = "dd/MM/yyyy";
        String shipDate = DateUtils.convertDateToString(shipmentRequestVo.getScheduleCollection().getPickupDate(), dateFormat, null);
        collection.setSHIPDATE(shipDate);
        // 3. PrefCollectTime.
        CollectTime prefCollectTime = new CollectTime();
        String pickupTime = shipmentRequestVo.getScheduleCollection().getPickupTime().replaceAll(":", "").substring(0, 4);
        String pickupTimeNoLater = shipmentRequestVo.getScheduleCollection().getPickupTimeNoLater().replaceAll(":", "").substring(0, 4);
        prefCollectTime.setFROM(pickupTime);
        prefCollectTime.setTO(pickupTimeNoLater);
        collection.setPREFCOLLECTTIME(prefCollectTime);
        // 4. CollInstructions.
        collection.setCOLLINSTRUCTIONS(shipmentRequestVo.getScheduleCollection().getDescription());
        sender.setCOLLECTION(collection);
        consignmentBatch.setSENDER(sender);
        // 2. ConsignmentBatch - Consignment.
        Consignment consignment = new Consignment();
        consignment.setCONREF(shipmentRequestVo.getShipmentReference());
        // Create Consignment Details.
        Details details = new Details();

        String contentType = "DOX".equalsIgnoreCase(shipmentRequestVo.getShipmentInfo().getContentType()) ? "D" : "N";
        details.setCONTYPE(contentType);
        details.setPAYMENTIND(paymentInd);
        // Create Receiver.
        Receiver receiver = new Receiver();

        receiver.setCOMPANYNAME(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getCompanyName());
        receiver.setSTREETADDRESS1(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getAddress());
        receiver.setSTREETADDRESS2(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getAddress2());
        receiver.setCITY(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getCity());
        receiver.setPROVINCE(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getState());
        receiver.setPOSTCODE(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getPostalCode());
        receiver.setCOUNTRY(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getCountryDetail().getCountryCode());
        receiver.setCONTACTNAME(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getContactName());
        receiver.setCONTACTEMAIL(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getEmail());
        String receiverPhone = shipmentRequestVo.getShipmentInfo().getReceiverAddress().getPhone();
        receiver.setCONTACTDIALCODE(receiverPhone.substring(0, 5));
        receiver.setCONTACTTELEPHONE(receiverPhone.substring(5, receiverPhone.length()));
        if (paymentInd.equalsIgnoreCase("R")) {
            receiver.setACCOUNT(SystemSettingCache.getInstance().getValueByKey("TNT AU Account"));
        } else {
            receiver.setACCOUNT("");
        }
        details.setRECEIVER(receiver);
        // Create Delivery.
        Delivery delivery = new Delivery();
        delivery.setCOMPANYNAME(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getCompanyName());
        delivery.setSTREETADDRESS1(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getAddress());
        delivery.setSTREETADDRESS2(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getAddress2());
        delivery.setCITY(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getCity());
        delivery.setPROVINCE(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getState());
        delivery.setPOSTCODE(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getPostalCode());
        delivery.setCOUNTRY(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getCountryDetail().getCountryCode());
        delivery.setCONTACTNAME(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getContactName());
        delivery.setCONTACTEMAIL(shipmentRequestVo.getShipmentInfo().getReceiverAddress().getEmail());
        String deliveryPhone = shipmentRequestVo.getShipmentInfo().getReceiverAddress().getPhone();
        delivery.setCONTACTDIALCODE(deliveryPhone.substring(0, 5));
        delivery.setCONTACTTELEPHONE(deliveryPhone.substring(5, deliveryPhone.length()));
        details.setDELIVERY(delivery);
        // Set customer ref.
        details.setCUSTOMERREF(shipmentRequestVo.getShipmentReference());

        Double totalWeight = 0.0;
        Integer totalItems = 0;
        Double totalVolume = 0.0;
        Double totalValue = 0.0;
        if (shipmentRequestVo.getShipmentInfo().getPieces() != null && shipmentRequestVo.getShipmentInfo().getPieces().size() > 0) {
            if (shipmentRequestVo.getShipmentInfo().getWeightUnit().equals("LB") && shipmentRequestVo.getShipmentInfo().getDimensionUnit().equals("IN")) {
                for (PieceVo piece : shipmentRequestVo.getShipmentInfo().getShipmentRequestPieces()) {
                    totalWeight += (piece.getWeight() * 0.453592) * piece.getQuantity();
                    totalItems += piece.getQuantity();
                    Double dimL = piece.getDimensionL() != 0 ? piece.getDimensionL() * 0.0254 : 1;
                    Double dimW = piece.getDimensionH() != 0 ? piece.getDimensionH() * 0.0254 : 1;
                    Double dimH = piece.getDimensionW() != 0 ? piece.getDimensionW() * 0.0254 : 1;
                    totalVolume += dimL * dimW * dimH * piece.getQuantity();
                    totalValue += piece.getCustomValue();
                }
            } else {
                for (PieceVo piece : shipmentRequestVo.getShipmentInfo().getShipmentRequestPieces()) {
                    totalWeight += piece.getWeight() * piece.getQuantity();
                    totalItems += piece.getQuantity();
                    Double dimL = piece.getDimensionL() != 0 ? piece.getDimensionL() / 100 : 1;
                    Double dimW = piece.getDimensionH() != 0 ? piece.getDimensionH() / 100 : 1;
                    Double dimH = piece.getDimensionW() != 0 ? piece.getDimensionW() / 100 : 1;
                    totalVolume += dimL * dimW * dimH * piece.getQuantity();
                    totalValue += piece.getCustomValue();
                }
            }

        }
        details.setITEMS(BigInteger.valueOf(totalItems));
        details.setTOTALWEIGHT(new BigDecimal(totalWeight).setScale(1, RoundingMode.HALF_UP));
        BigDecimal totVol = new BigDecimal(totalVolume).setScale(3, RoundingMode.HALF_UP);
        if (totVol.compareTo(BigDecimal.ZERO) <= 0) {
            totVol = new BigDecimal(0.001).setScale(3, RoundingMode.HALF_UP);
        }
        details.setTOTALVOLUME(totVol);
        details.setCURRENCY(shipmentRequestVo.getShipmentInfo().getCurrencyCode());
        if (totalValue > 0) {
            details.setGOODSVALUE(new BigDecimal(totalValue).setScale(2, RoundingMode.HALF_UP));
        }
        String serviceCode = shipmentRequestVo.getServiceCode();
        details.setSERVICE(serviceCode);
        details.setDESCRIPTION(shipmentRequestVo.getShipmentInfo().getContentDescription());
        details.setDELIVERYINST(shipmentRequestVo.getShipmentInfo().getSpecialDelivery() != null ? shipmentRequestVo.getShipmentInfo().getSpecialDelivery() : "");
        String packageName = shipmentRequestVo.getPackageInfo().getPackageName();
        if (shipmentRequestVo.getShipmentInfo().getShipmentRequestPieces() != null && shipmentRequestVo.getShipmentInfo().getShipmentRequestPieces().size() > 0) {
            Package pack = new Package();
            if (shipmentRequestVo.getShipmentInfo().getWeightUnit().equals("LB") && shipmentRequestVo.getShipmentInfo().getDimensionUnit().equals("IN")) {
                for (PieceVo piece : shipmentRequestVo.getShipmentInfo().getShipmentRequestPieces()) {
                    pack = new Package();
                    pack.setITEMS(BigInteger.valueOf(piece.getQuantity()));
                    pack.setDESCRIPTION(packageName);
                    Double weight = piece.getWeight() * 0.453592;
                    Double dimL = piece.getDimensionL() != 0 ? piece.getDimensionL() * 0.0254 : 1;
                    Double dimW = piece.getDimensionH() != 0 ? piece.getDimensionH() * 0.0254 : 1;
                    Double dimH = piece.getDimensionW() != 0 ? piece.getDimensionW() * 0.0254 : 1;
                    pack.setLENGTH(new BigDecimal(dimL).setScale(2, RoundingMode.HALF_UP));
                    pack.setHEIGHT(new BigDecimal(dimH).setScale(2, RoundingMode.HALF_UP));
                    pack.setWEIGHT(new BigDecimal(weight).setScale(1, RoundingMode.HALF_UP));
                    pack.setWIDTH(new BigDecimal(dimW).setScale(2, RoundingMode.HALF_UP));
                    details.getPACKAGE().add(pack);
                }
            } else {
                for (PieceVo piece : shipmentRequestVo.getShipmentInfo().getShipmentRequestPieces()) {
                    pack = new Package();
                    pack.setITEMS(BigInteger.valueOf(piece.getQuantity()));
                    pack.setDESCRIPTION(packageName);
                    Double dimL = piece.getDimensionL() != 0 ? piece.getDimensionL() / 100 : 1;
                    Double dimW = piece.getDimensionH() != 0 ? piece.getDimensionH() / 100 : 1;
                    Double dimH = piece.getDimensionW() != 0 ? piece.getDimensionW() / 100 : 1;
                    pack.setLENGTH(new BigDecimal(dimL).setScale(2, RoundingMode.HALF_UP));
                    pack.setHEIGHT(new BigDecimal(dimH).setScale(2, RoundingMode.HALF_UP));
                    pack.setWEIGHT(new BigDecimal(piece.getWeight()).setScale(1, RoundingMode.HALF_UP));
                    pack.setWIDTH(new BigDecimal(dimW).setScale(2, RoundingMode.HALF_UP));
                    details.getPACKAGE().add(pack);
                }
            }

        }
        consignment.setDETAILS(details);
        consignmentBatch.getCONSIGNMENT().add(consignment);
        eShipper.setCONSIGNMENTBATCH(consignmentBatch);
        // Create Activity.
        Activity activity = new Activity();
        // 1. Activity - Create.
        Create create = new Create();
        create.getCONREF().add(shipmentRequestVo.getShipmentReference());
        activity.setCREATE(create);
        // 2. Activity - Ship.
        Ship ship = new Ship();
        ship.getCONREF().add(shipmentRequestVo.getShipmentReference());
        activity.setSHIP(ship);
        // Return xml request.
        eShipper.setACTIVITY(activity);
        return AppUtils.Object2XmlString(eShipper, EShipper.class);
    }

    public String getErrorMessage(Document document) {
        String errorMessage = "";
        if (document.getERROR() == null) {
            return null;
        }
        for (com.gms.delivery.tnt.xmlpi.shipping.response.Error error : document.getERROR()) {
            errorMessage += error.getCODE() + " : " + error.getDESCRIPTION() + "\n";
        }
        return errorMessage;
    }
}
