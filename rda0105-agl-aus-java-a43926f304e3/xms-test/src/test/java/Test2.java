import com.gms.delivery.toll.service.TollIpecPickupService;

import javax.xml.bind.JAXBException;

public class Test2 {
    public static void main(String[] args) throws JAXBException {
        TollIpecPickupService pickupService = new TollIpecPickupService();
        String xmlRequest = "<?xml version=\"1.0\"?><TollPickupRequest><Header><Sender>TollConnect</Sender><Receiver>Toll</Receiver><DocumentType>TollPickupRequest</DocumentType><DocumentID>20160216483254</DocumentID><DateTimeStamp>2016-02-16T10:53:54+1100</DateTimeStamp></Header><PickupRequest><CarrierID>IPEC</CarrierID><ConfirmationDetail><DeclarationAccepted>true</DeclarationAccepted><ConfirmationNumber></ConfirmationNumber></ConfirmationDetail><AccountDetail><AccountCode>pn5593</AccountCode><SubAccountID></SubAccountID></AccountDetail><InitiatorDetail><CompanyName>test</CompanyName><ContactName>test</ContactName><ContactAreaCode>24</ContactAreaCode><ContactPhoneNumber>2323</ContactPhoneNumber></InitiatorDetail><SenderDetail><CompanyName>test</CompanyName><AddressLine1>test</AddressLine1><Suburb>SYDNEY</Suburb><State>NSW</State><Postcode>2000</Postcode><Country>AU</Country><ContactName>test</ContactName><ContactAreaCode>24</ContactAreaCode><ContactPhoneNumber>2323</ContactPhoneNumber><SpecialInstructions>test</SpecialInstructions></SenderDetail><PickupDetail><Reference><Reference1>test</Reference1><Reference2></Reference2></Reference><PickupDateTime>2016-02-23T09:30:00+1100</PickupDateTime><OpenTime>2016-02-23T09:30:00+1100</OpenTime><CloseTime>2016-02-23T14:30:00+1100</CloseTime><RegularPickup>true</RegularPickup><BringConNote>true</BringConNote><SameLocation>true</SameLocation><PickupFromLocation>Front Desk</PickupFromLocation><ReturnJob>false</ReturnJob></PickupDetail><Items><Description>test</Description><NumberItems>1</NumberItems><NumberPalletSpaces></NumberPalletSpaces><Weight>2</Weight><Length>1</Length><Width>1</Width><Height>1</Height><Destination></Destination><DangerousGoods>false</DangerousGoods><DangerousGoodsUNCodel></DangerousGoodsUNCodel><Payer></Payer><PayerAccountCode></PayerAccountCode><ServiceID>H</ServiceID><ServiceName></ServiceName><ProductName></ProductName><Temperature></Temperature><Food>false</Food><EstimatedCartons></EstimatedCartons><EstimatedBags></EstimatedBags><EstimatedOther></EstimatedOther><LargestItemType>LESA</LargestItemType><ReceiverDetail><CompanyName>test</CompanyName><AddressLine1>test</AddressLine1><Suburb>ROSEBERY</Suburb><State>NSW</State><Postcode>2018</Postcode><Country>AU</Country><ContactName>test</ContactName><ContactAreaCode>00</ContactAreaCode><ContactPhoneNumber>00242323</ContactPhoneNumber></ReceiverDetail></Items></PickupRequest></TollPickupRequest>";
        String response = pickupService.execute(xmlRequest, null);
        System.out.println(response);
    }
}