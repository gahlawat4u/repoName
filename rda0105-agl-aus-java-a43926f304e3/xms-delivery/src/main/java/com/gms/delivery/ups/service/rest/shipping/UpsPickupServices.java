package com.gms.delivery.ups.service.rest.shipping;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.delivery.ups.service.GsonContextLoader;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.ShippingRequest;
import com.gms.delivery.ups.service.rest.shipment.pojo.response.ShippingResponse;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.webship.UPSSchedulingModel;

public class UpsPickupServices {

	private static final Log log =LogFactory.getLog(UpsShippingServices.class);
	/**
	 * @param args
	 */
	public String doShipmentPickup(UPSSchedulingModel upsSchedulingModel) {
		
		//String UPS_URL = "http://52.77.254.46/crm/webapi/ups/pickup.php";  
		//String UPS_URL="http://54.255.209.226/crm/webapi/ups/pickup.php"; //by shahabuddin
		String UPS_URL="http://127.0.0.1:8080/crm/webapi/ups/pickup.php";
		   PostMethod post = new PostMethod(UPS_URL);
		HttpClient client = new HttpClient();
		
		ShippingResponse shippingResponse = null;
		String outPut = "";

		try {
			
			String json = GsonContextLoader.getGsonContext().toJson(upsSchedulingModel);
			
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
			
			shippingResponse = GsonContextLoader.getGsonContext().fromJson(outPut,ShippingResponse.class);
			shippingResponse = GsonUtils.fromGson(outPut,ShippingResponse.class);
			
			String jsonNrew = GsonContextLoader.getGsonContext().toJson(shippingResponse);

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
