import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.delivery.tntdom.jaxb.SubmitBookingResponse;
import com.gms.xms.common.utils.AppUtils;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;

/**
 * Posted from TntDomPickupServiceTest
 * <p>
 * Author dattrinh Feb 2, 2016 3:35:09 PM
 */
public class TntDomPickupServiceTest {
    public static void main(String[] args) throws Exception {
        TntDomPickupServiceTest.response();

    }

    public static void response() throws Exception {
        String xml = AppUtils.readXml4Example("D:/tmp", "tnt_dom_pickup_response_e797dda83b5f49cfa3fe3332611a6df4.xml");
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xml.getBytes()));
        Unmarshaller unmarshaller = JAXBContext.newInstance(SubmitBookingResponse.class).createUnmarshaller();
        SubmitBookingResponse bookingResponse = (SubmitBookingResponse) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
        System.out.println(bookingResponse);
    }

    public static void tracking() {
        String airbillNumber = "980053209088";
        PostMethod post = new PostMethod("https://www.tntexpress.com.au/cct/TrackResultsCon.asp");
        post.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        post.setParameter("User", "tntcct");
        post.setParameter("Password", "ccttnt");
        post.setParameter("con", airbillNumber);
        try {
            String xmlResponse;
            int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
            if (result == 200) {
                xmlResponse = post.getResponseBodyAsString();
            } else {
                xmlResponse = null;
            }
            System.out.println(xmlResponse);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            post.releaseConnection();
        }
    }

}
