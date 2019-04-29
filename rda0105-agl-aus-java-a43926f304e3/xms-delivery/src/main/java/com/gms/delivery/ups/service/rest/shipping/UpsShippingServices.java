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

public class UpsShippingServices {

	private static final Log log =LogFactory.getLog(UpsShippingServices.class);
	/**
	 * @param args
	 */
	public String doShipment(ShippingRequest shippingRequestVo) {
		   String UPS_URL = SystemSettingCache.getInstance().getValueByKey("UPS_URL");
		   PostMethod post = new PostMethod(UPS_URL);
		HttpClient client = new HttpClient();
		ShippingResponse shippingResponse = null;
		String outPut = "";
		String newout = "";
		try {
			
			String json = GsonContextLoader.getGsonContext().toJson(shippingRequestVo);
			
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
			 
			int findValue = outPut.indexOf("\"PackageResults\":[{") ;
			
			 if( findValue <= 0){
			String outPutChange = outPut.replace("\"PackageResults\":{", "\"PackageResults\":[{");
			Integer  lengthObject = outPutChange.length();
			StringBuffer stringBuffer = new StringBuffer( outPutChange );
			
			stringBuffer = stringBuffer.replace( ( lengthObject - 3 ), ( lengthObject - 3 ), "]" );
			 newout = stringBuffer.toString() ;
			System.out.println(" stringBuffer find  :: " + stringBuffer.toString());
			 }else{
				 newout =  outPut ;
			 }
			
			
			
			shippingResponse = GsonContextLoader.getGsonContext().fromJson(newout,ShippingResponse.class);
			shippingResponse = GsonUtils.fromGson(newout,ShippingResponse.class);
			
			String jsonNrew = GsonContextLoader.getGsonContext().toJson(shippingResponse);

			System.out.println("RESPONSE JSON : " + jsonNrew);
			log.error("RESPONSE JSON : " + jsonNrew);
			post.releaseConnection();


		} catch (Exception e) {
			log.error(e.getMessage());
			
			System.out.println("Exception " + e);
		}

		return newout;
	}

}
	