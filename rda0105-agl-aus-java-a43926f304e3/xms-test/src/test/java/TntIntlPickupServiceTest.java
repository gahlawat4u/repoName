import com.gms.delivery.httpclient.HttpClientFactory;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.StringBufferInputStream;
import java.net.URLEncoder;

@SuppressWarnings("deprecation")
public class TntIntlPickupServiceTest {
    public static void main(String[] args) {
        String xmlRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ESHIPPER><LOGIN><COMPANY>Agl</COMPANY><PASSWORD>essxms2014</PASSWORD><APPID>IN</APPID><APPVERSION>2.2</APPVERSION></LOGIN><CONSIGNMENTBATCH><SENDER><COMPANYNAME>test</COMPANYNAME><STREETADDRESS1>test</STREETADDRESS1><STREETADDRESS2></STREETADDRESS2><CITY>SYDNEY</CITY><PROVINCE>NSW</PROVINCE><POSTCODE>2000</POSTCODE><COUNTRY>AU</COUNTRY><ACCOUNT>30000859</ACCOUNT><CONTACTNAME>test</CONTACTNAME><CONTACTDIALCODE>24232</CONTACTDIALCODE><CONTACTTELEPHONE>3</CONTACTTELEPHONE><CONTACTEMAIL></CONTACTEMAIL><COLLECTION><COLLECTIONADDRESS><COMPANYNAME>test</COMPANYNAME><STREETADDRESS1>test</STREETADDRESS1><CITY>SYDNEY</CITY><PROVINCE>NSW</PROVINCE><POSTCODE>2000</POSTCODE><COUNTRY>AU</COUNTRY><CONTACTNAME>test</CONTACTNAME><CONTACTDIALCODE>24232</CONTACTDIALCODE><CONTACTTELEPHONE>3</CONTACTTELEPHONE></COLLECTIONADDRESS><SHIPDATE>03/02/2016</SHIPDATE><PREFCOLLECTTIME><FROM>0900</FROM><TO>1500</TO></PREFCOLLECTTIME><COLLINSTRUCTIONS>test</COLLINSTRUCTIONS></COLLECTION></SENDER><CONSIGNMENT><CONREF>test</CONREF><DETAILS><RECEIVER><COMPANYNAME>test</COMPANYNAME><STREETADDRESS1>test</STREETADDRESS1><STREETADDRESS2></STREETADDRESS2><CITY>Hanoi</CITY><PROVINCE></PROVINCE><POSTCODE>10000</POSTCODE><COUNTRY>VN</COUNTRY><CONTACTNAME>test</CONTACTNAME><CONTACTDIALCODE>24232</CONTACTDIALCODE><CONTACTTELEPHONE>3</CONTACTTELEPHONE><CONTACTEMAIL></CONTACTEMAIL></RECEIVER><DELIVERY><COMPANYNAME>test</COMPANYNAME><STREETADDRESS1>test</STREETADDRESS1><STREETADDRESS2></STREETADDRESS2><CITY>Hanoi</CITY><PROVINCE></PROVINCE><POSTCODE>10000</POSTCODE><COUNTRY>VN</COUNTRY><CONTACTNAME>test</CONTACTNAME><CONTACTDIALCODE>24232</CONTACTDIALCODE><CONTACTTELEPHONE>3</CONTACTTELEPHONE><CONTACTEMAIL></CONTACTEMAIL></DELIVERY><CUSTOMERREF>test</CUSTOMERREF><CONTYPE>N</CONTYPE><PAYMENTIND>S</PAYMENTIND><ITEMS>1</ITEMS><TOTALWEIGHT>1</TOTALWEIGHT><TOTALVOLUME>0.10</TOTALVOLUME><CURRENCY>AUD</CURRENCY><SERVICE>15N</SERVICE><DESCRIPTION>test</DESCRIPTION><DELIVERYINST>test</DELIVERYINST><PACKAGE><ITEMS>1</ITEMS><DESCRIPTION>Customer packaging</DESCRIPTION><LENGTH>0.01</LENGTH><HEIGHT>0.01</HEIGHT><WIDTH>0.01</WIDTH><WEIGHT>1</WEIGHT></PACKAGE></DETAILS></CONSIGNMENT></CONSIGNMENTBATCH><ACTIVITY><BOOK ShowBookingRef=\"Y\"><CONREF>483209</CONREF><CONNUMBER>GE880385785AU</CONNUMBER></BOOK><SHIP><CONREF>483209</CONREF></SHIP></ACTIVITY></ESHIPPER>";
        String xmlResponse = null;
        PostMethod post = null;
        try {
            post = new PostMethod("https://iconnection.tnt.com/shippergate2.asp");
            post.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlRequest = "xml_in=" + URLEncoder.encode(xmlRequest, "UTF-8");
            post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream(xmlRequest)));
            // Send request to get access key.
            int resultCode = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
            if (resultCode == 200) {
                xmlResponse = post.getResponseBodyAsString();
            } else {
                xmlResponse = null;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            post.releaseConnection();
        }

        // Get access key that returned from TNT server.
        String[] result = xmlResponse != null ? xmlResponse.split(":") : null;
        if (result != null && "COMPLETE".equalsIgnoreCase(result[0])) {
            String sendBackRequest = "GET_RESULT:" + result[1];
            // Send access key to get result.
            PostMethod postBack = null;
            try {
                sendBackRequest = "xml_in=" + URLEncoder.encode(sendBackRequest, "UTF-8");
                postBack = new PostMethod("https://iconnection.tnt.com/shippergate2.asp");
                postBack.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                postBack.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream(sendBackRequest)));
                int resultCode = HttpClientFactory.getInstance().getHttpClient().executeMethod(postBack);
                if (resultCode == 200) {
                    xmlResponse = postBack.getResponseBodyAsString();
                } else {
                    xmlResponse = null;
                }
            } catch (Exception e) {
            } finally {
                postBack.releaseConnection();
            }
        } else {
        }
    }
}
