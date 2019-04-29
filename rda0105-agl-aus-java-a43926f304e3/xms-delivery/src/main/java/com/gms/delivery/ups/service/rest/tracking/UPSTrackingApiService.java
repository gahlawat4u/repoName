package com.gms.delivery.ups.service.rest.tracking;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.delivery.ups.service.GsonContextLoader;
import com.gms.delivery.ups.service.rest.shipment.pojo.response.ShippingResponse;
import com.gms.xms.common.utils.GsonUtils;

public class UPSTrackingApiService {

	
	private static final Log log =LogFactory.getLog(UPSTrackingApiService.class);
	/**
	 * @param args
	 */
	public String getUPSTracking(UPSTrackingRequest upsTrackingRequest) {
		// String UPS_URL = "http://52.77.254.46/crm/webapi/ups/track.php";
		 //String UPS_URL = "http://54.255.209.226/crm/webapi/ups/track.php";
		//String UPS_URL = "http://127.0.0.1:8080/crm/webapi/ups/track.php";
		
		String UPS_URL = "http://127.0.0.1:8080/crm/webapi/track.php";
		
		//String UPS_URL = "http://54.255.209.226/crm/webapi/ups/track.php";
		   PostMethod post = new PostMethod(UPS_URL);
		HttpClient client = new HttpClient();
		UpsTrackingResponse upsTrackingResponse = null;
		String outPut = "";
		try {
			//upsTrackingRequest.setAWBNumber("1Z7R78770433751855");
			String json = GsonContextLoader.getGsonContext().toJson(upsTrackingRequest);
			
			System.out.println(" REQUEST JSON :: " + json);
			log.error(" REQUEST JSON :: " + json);
			
			RequestEntity entity = new StringRequestEntity(json, "application/json", null);
			post.setRequestEntity(entity);

			int status = client.executeMethod(post);
			log.error(" status :: " + status);
			System.out.println(" status :: " + status);
			
		        outPut = post.getResponseBodyAsString();
			System.out.println(" outPut :: " + outPut);
			log.error(" outPut :: " + outPut);
			
			/*upsTrackingResponse = GsonContextLoader.getGsonContext().fromJson(outPut,UpsTrackingResponse.class);
			upsTrackingResponse = GsonUtils.fromGson(outPut,UpsTrackingResponse.class);
			
			String jsonNrew = GsonContextLoader.getGsonContext().toJson(upsTrackingResponse);

			System.out.println("RESPONSE JSON : " + outPut);
			
*/			post.releaseConnection();


		} catch (Exception e) {
			log.error(e.getMessage());
			
			System.out.println("Exception " + e);
		}

		return outPut;
	}

}
