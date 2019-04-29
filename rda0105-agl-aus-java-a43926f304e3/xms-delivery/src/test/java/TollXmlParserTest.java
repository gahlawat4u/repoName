import com.gms.delivery.toll.xmlpi.pickup.request.TollPickupRequest;
import com.gms.delivery.toll.xmlpi.pickup.response.TollPickupResponse;
import com.gms.delivery.toll.xmlpi.pickup.response.XmlEncodeError;
import com.gms.xms.common.utils.AppUtils;

import javax.xml.bind.JAXBException;

/**
 * Posted from TollXmlParserTest
 * <p>
 * Author dattrinh Feb 15, 2016 2:47:26 PM
 */
public class TollXmlParserTest {
    public static void main(String[] args) throws JAXBException {
        // Convert xml to Toll Pickup Request object.
        String xmlReq = "<?xml version=\"1.0\"?><TollPickupRequest><Header><Sender>TollConnect</Sender><Receiver>Toll</Receiver><DocumentType>TollPickupRequest</DocumentType><DocumentID>200612174036</DocumentID><DateTimeStamp>2006-01-12T17:04:00+1100</DateTimeStamp></Header><PickupRequest><CarrierID>PRIO</CarrierID><ConfirmationDetail><DeclarationAccepted>true</DeclarationAccepted><ConfirmationNumber></ConfirmationNumber><ChargeAmount></ChargeAmount></ConfirmationDetail><AccountDetail><AccountCode>200A4T</AccountCode><SubAccountID></SubAccountID></AccountDetail><InitiatorDetail><CompanyName>TOLL HOLDINGS</CompanyName><ContactName>WILLY LEGIMAN</ContactName><ContactAreaCode>03</ContactAreaCode><ContactPhoneNumber>93607172</ContactPhoneNumber><ContactEmail>Willy_Legiman@toll.com.au</ContactEmail></InitiatorDetail><SenderDetail><CompanyName>TEST SENDER</CompanyName><AddressLine1>43 - 63 PRINCES HIGHWAY</AddressLine1><AddressLine2></AddressLine2><Suburb>DOVETON</Suburb><State>VIC</State><Postcode>3177</Postcode><Country>AU</Country><ContactName>WILLY LEGIMAN</ContactName><ContactAreaCode>03</ContactAreaCode><ContactPhoneNumber>93607172</ContactPhoneNumber><ContactEmail>Willy_Legiman@toll.com.au</ContactEmail><SpecialInstructions>B2B TEST SPECIAL INSTRUCTIONS.</SpecialInstructions></SenderDetail><PickupDetail><Reference><Reference1>TESTING 1</Reference1><Reference2>TESTING 2</Reference2></Reference><PickupDateTime>2006-01-12T18:00:00+1100</PickupDateTime><OpenTime></OpenTime><CloseTime>2006-01-12T19:00:00+1100</CloseTime><RegularPickup>true</RegularPickup><BringConNote>true</BringConNote><SameLocation>true</SameLocation><PickupFromLocation>Despatch</PickupFromLocation><ReturnJob>false</ReturnJob></PickupDetail><Items><Description>TEST DESCRIPTION</Description><NumberItems>1</NumberItems><NumberPalletSpaces></NumberPalletSpaces><Weight>2</Weight><Length>10</Length><Width>20</Width><Height>30</Height><Destination></Destination><DangerousGoods>false</DangerousGoods><DangerousGoodsUNCode></DangerousGoodsUNCode><Payer></Payer><PayerAccountCode></PayerAccountCode><ServiceID>01</ServiceID><ServiceName></ServiceName><ProductID>02</ProductID><ProductName></ProductName><Temperature></Temperature><Food>false</Food><EstimatedCartons></EstimatedCartons><EstimatedBags></EstimatedBags><EstimatedOther></EstimatedOther><LargestItemType></LargestItemType><ReceiverDetail><CompanyName>TEST RECEIVER</CompanyName><AddressLine1>TEST RECEIVER ADDRESS 1</AddressLine1><AddressLine2>TEST RECEIVER ADDRESS 2</AddressLine2><Suburb>SYDNEY</Suburb><State>NSW</State><Postcode>2000</Postcode><Country>AU</Country><ContactName>TEST RECEIVER CONTACT</ContactName><ContactAreaCode>02</ContactAreaCode><ContactPhoneNumber>87100855</ContactPhoneNumber><ContactEmail>totestreceiver@toll.com.au</ContactEmail></ReceiverDetail></Items></PickupRequest></TollPickupRequest>";
        TollPickupRequest pickupReqquest = AppUtils.xmlString2Object(xmlReq, TollPickupRequest.class);
        System.out.println(pickupReqquest);

        // Convert xml to Toll Pickup Response object
        // Pickup Confirmation.
        String xmlRes = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><TollPickupResponse><Header><Sender>Toll</Sender><Receiver>TollConnect</Receiver><DocumentType>TollPickupResponse</DocumentType><DocumentID>200612171220227</DocumentID><DateTimeStamp>2006-01-12T17:12:27+1100</DateTimeStamp></Header><PickupConfirmation><ConfirmationNumber>PRIO:562582</ConfirmationNumber></PickupConfirmation></TollPickupResponse>";
        TollPickupResponse pickupResponse = AppUtils.xmlString2Object(xmlRes, TollPickupResponse.class);
        System.out.println(pickupResponse.getPickupConfirmation().getConfirmationNumber());

        // Pickup Error.
        xmlRes = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><TollPickupResponse><Header><Sender>Toll</Sender><Receiver>TollConnect</Receiver><DocumentType>TollPickupResponse</DocumentType><DocumentID>2006121783371</DocumentID><DateTimeStamp>2006-01-12T17:08:03+1100</DateTimeStamp></Header><Error><Message># ERROR (0): Error while processing B2B Pickup Request.  -&gt; original exception: # ERROR (0): The Carrier (GOOG) does not support B2B Pickup, OR you do not have the required privileges to perform B2B Pickup request.</Message></Error></TollPickupResponse>";
        pickupResponse = AppUtils.xmlString2Object(xmlRes, TollPickupResponse.class);
        System.out.println(pickupResponse.getError().getMessage());

        // Xml Encode Error.
        xmlRes = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><errors><error># ERROR (0): Could not find Mandatory XML field [CarrierID]</error><error>Loi gi thi bao de</error></errors>";
        XmlEncodeError xmlEncodeError = AppUtils.xmlString2Object(xmlRes, XmlEncodeError.class);
        System.out.println(xmlEncodeError.getError().get(1));
    }
}
