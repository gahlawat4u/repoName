package com.gms.xms.workflow.task2.webship.voids.ups;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.delivery.ups.service.GsonContextLoader;
import com.gms.delivery.ups.service.rest.shipment.pojo.response.ShippingResponse;
import com.gms.delivery.ups.service.rest.shipment.pojo.response.ShippingSHRoot;
import com.gms.delivery.ups.service.rest.shipping.UpsShippingServices;
import com.gms.xms.common.utils.GsonUtils;

public class UpsCancelPickupServiceApi {

	private static final Log log =LogFactory.getLog(UpsShippingServices.class);
	/**
	 * @param args
	 */
	public String getUpsCancel(UpsPickupCancelRequest upsPickupCancelRequest) {
		 //String UPS_URL = "http://52.77.254.46/crm/webapi/ups_cancel_collection.php";
		 
		//String UPS_URL="http://54.255.209.226/crm/webapi/ups_cancel_collection.php";  //pervious url
		
		//String UPS_URL="http://54.255.209.226/crm/webapi/ups_void.php"; //change by Anup sir
		String UPS_URL="http://127.0.0.1:8080/crm/webapi/ups_void.php"; //change by Anup sir
		   PostMethod post = new PostMethod(UPS_URL);
		HttpClient client = new HttpClient();
		ShippingResponse shippingResponse = null;  //code by rakesh sir
		//ShippingSHRoot shippingResponse = null; //shahabuddin
		String outPut = "";
		try {
			
			String json = GsonContextLoader.getGsonContext().toJson(upsPickupCancelRequest);
			
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
			
			
			shippingResponse = GsonContextLoader.getGsonContext().fromJson(outPut,ShippingResponse.class);// code by rakesh sir
			shippingResponse = GsonUtils.fromGson(outPut,ShippingResponse.class);  //code by rakesh sir
			
			String jsonNrew = GsonContextLoader.getGsonContext().toJson(shippingResponse);  //code by rakesh sir

			//code by shahabuddin
			
			/*shippingResponse = GsonContextLoader.getGsonContext().fromJson(outPut,ShippingSHRoot.class);
			shippingResponse = GsonUtils.fromGson(outPut,ShippingSHRoot.class); 
			
			String jsonNrew = GsonContextLoader.getGsonContext().toJson(shippingResponse); 
*/
			
			//end by shahabuddin
			
			
			System.out.println("RESPONSE JSON : " + jsonNrew);
			log.error("RESPONSE JSON : " + jsonNrew);
			post.releaseConnection();


		} catch (Exception e) {
			log.error(e.getMessage());
			
			System.out.println("Exception " + e);
		}

		return outPut;
	}

}
