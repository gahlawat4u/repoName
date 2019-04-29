import com.gms.delivery.httpclient.HttpClientFactory;
import org.apache.commons.httpclient.methods.PostMethod;

import java.nio.file.Files;
import java.nio.file.Paths;

public class StartTrackTracking {
    public static void main(String[] args) throws Exception {
        String xmlRequest = new String(Files.readAllBytes(Paths.get("d:/startrack_tracking_request_14667371232756123414667371232756.xml")));
        String xmlResponse = null;
        PostMethod post = null;
        try {
            post = new PostMethod("http://localhost/xms_au_dev/startrack/startrack_tracking_api.php");
            post.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            post.addParameter("xml", xmlRequest);
            post.addParameter("url", "https://services.startrackexpress.com.au:443/DMZExternalService/InterfaceServices/ExternalOps.serviceagent/OperationsEndpoint1");

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
        System.out.println(xmlResponse);
    }
}
