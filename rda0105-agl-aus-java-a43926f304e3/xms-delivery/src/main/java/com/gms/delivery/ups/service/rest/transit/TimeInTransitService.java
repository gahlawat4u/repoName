package com.gms.delivery.ups.service.rest.transit;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.gms.delivery.ups.service.rest.transit.response.pojo.TimeInTransitResponseVO;


public class TimeInTransitService {

	/**
	 * @param args
	 */
	public TimeInTransitResponseVO getTransitTime(TimeInTransitVO timeInTransitVO) {
		PostMethod post = new PostMethod("https://wwwcie.ups.com/rest/TimeInTransit");
		HttpClient client = new HttpClient();
		TimeInTransitResponseVO timeInTransitResponseVO = null;
			try{				
//				Security security = new Security();
//				UsernameToken usernameToken = new UsernameToken();
//				usernameToken.setUsername("inayatAhmed");
//				usernameToken.setPassword("indic@4Soft");
//				security.setUsernameToken(usernameToken);
//				
//				UPSServiceAccessToken uPSServiceAccessToken = new UPSServiceAccessToken();
//				uPSServiceAccessToken.setAccessLicenseNumber("4D1E6848A188D418");
//				security.setUPSServiceAccessToken(uPSServiceAccessToken);
//				timeInTransitVO.setSecurity(security);
//				
//				
//				TimeInTransitRequest timeInTransitRequest = new TimeInTransitRequest();
//				
//				Request request = new Request();
//				TransactionReference transactionReference = new TransactionReference();
//				transactionReference.setCustomerContext("");
//				transactionReference.setTransactionIdentifier("");
//				
//				request.setTransactionReference(transactionReference);
//				request.setRequestOption("TNT");
//				
//				timeInTransitRequest.setRequest(request);
//				
//				ShipFrom shipFrom = new ShipFrom();
//				Address addressFrom= new Address();
//				addressFrom.setCountryCode("US");
//				addressFrom.setPostalCode("30076");
//				addressFrom.setStateProvinceCode("GA");
//				
//				shipFrom.setAddress(addressFrom);
//				
//				timeInTransitRequest.setShipFrom(shipFrom);
//				
//				
//				ShipTo shipTo = new ShipTo();
//				Address addressTo= new Address();
//				addressTo.setCountryCode("US");
//				addressTo.setPostalCode("30076");
//				addressTo.setStateProvinceCode("GA");
//				shipTo.setAddress(addressTo);
//				
//				timeInTransitRequest.setShipTo(shipTo);
//				
//				Pickup pickup = new Pickup();
//				pickup.setDate("20170121");
//				
//				timeInTransitRequest.setPickup(pickup);
//				
//				ShipmentWeight shipmentWeight = new ShipmentWeight();
//				shipmentWeight.setWeight("10");
//				
//				UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement();
//				unitOfMeasurement.setCode("KGS");
//				unitOfMeasurement.setDescription("Kilograms");
//				
//				shipmentWeight.setUnitOfMeasurement(unitOfMeasurement);
//				
//				timeInTransitRequest.setShipmentWeight(shipmentWeight);
//				
//				timeInTransitRequest.setMaximumListSize("1");
//				
//				timeInTransitVO.setTimeInTransitRequest(timeInTransitRequest);
				
				String json = GsonContextLoader.getGsonContext().toJson(timeInTransitVO);
				System.out.println(" REQUEST JSON :: "+json);
				RequestEntity entity = new StringRequestEntity(json, "application/json", null);
				post.setRequestEntity(entity);
	    //	post.setRequestBody(json);
			int status = client.executeMethod(post);
			System.out.println(" status :: "+status);
			
			//System.out.println(post.getResponseBodyAsString());
			String outPut= post.getResponseBodyAsString();
			timeInTransitResponseVO = GsonContextLoader.getGsonContext().fromJson(outPut, TimeInTransitResponseVO.class);
			post.releaseConnection();

			}catch (Exception e) {
				System.out.println("Exception "+e);
			}
		return timeInTransitResponseVO;
		}
}
