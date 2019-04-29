import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.delivery.tnt.service.TntTrackingService;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBException;
import java.io.StringBufferInputStream;

@SuppressWarnings("deprecation")
public class TntXmlParserTest {
    public static void main(String[] args) throws JAXBException {
        // Create Xml Tracking Request.
        TntTrackingService trackingService = new TntTrackingService();
        String xmlTrackingReq = trackingService.prepareXmlRequest("GE878927678AU", "AU");
        System.out.println(xmlTrackingReq);
        String xmlResponse = execute(xmlTrackingReq, null);
        System.out.println(xmlResponse);
    }

    public static String execute(String xmlRequest, String xmlResponseCached) {
        PostMethod post = new PostMethod("https://express.tnt.com/expressconnect/track.do");
        String userName = "agl";
        String password = "essxms2014";
        String param = userName + ":" + password;
        post.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        // post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
        post.setRequestHeader("Authorization", "Basic " + DatatypeConverter.printBase64Binary(param.getBytes()));
        post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream("xml_in=" + xmlRequest)));
        try {
            String xmlResponse;
            if (StringUtils.isBlank(xmlResponseCached)) {
                int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
                if (result == 200) {
                    xmlResponse = post.getResponseBodyAsString();
                } else {
                    xmlResponse = null;
                }
            } else {
                xmlResponse = xmlResponseCached;
            }
            return xmlResponse;
        } catch (Exception e) {
            return null;
        } finally {
            post.releaseConnection();
        }
    }
}
