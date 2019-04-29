package com.gms.delivery.ups.service.rest.label;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.gms.xms.txndb.vo.ShipmentInfoVo;



public class UpsLabelServices {

	/**
	 * @param args
	 */
	public String getLabel(ShipmentInfoVo shipmentInfoVo) {
		PostMethod post = new PostMethod("https://wwwcie.ups.com/rest/Ship");
		HttpClient client = new HttpClient();
		String response = null;
		try{
		LabelRecoveryRequestVo labelRecoveryRequestVo= new LabelRecoveryRequestVo();
		
		UPSSecurity uPSSecurity = new UPSSecurity();
		
		ServiceAccessToken serviceAccessToken = new ServiceAccessToken();
		
		serviceAccessToken.setAccessLicenseNumber("4D1E6848A188D418");
		uPSSecurity.setServiceAccessToken(serviceAccessToken);
		
		
		UsernameToken usernameToken = new UsernameToken();
		usernameToken.setUsername("gmsgroup1");
		usernameToken.setPassword("ups@2017");
		uPSSecurity.setUsernameToken(usernameToken);
		
		labelRecoveryRequestVo.setUPSSecurity(uPSSecurity);
		
		LabelRecoveryRequest labelRecoveryRequest= new LabelRecoveryRequest();
		
		LabelSpecification labelSpecification = new LabelSpecification();
		
		labelSpecification.setHTTPUserAgent("Mozilla/4.5");
		labelSpecification.setLabelImageFormatCode("PDF");
		
		labelRecoveryRequest.setLabelSpecification(labelSpecification);
		
		Translate translate = new Translate();
		translate.setCode("01");
		translate.setDialectCode("GB");
		translate.setLanguageCode("eng");
		
		labelRecoveryRequest.setTranslate(translate);
		
	//	labelRecoveryRequest.setTrackingNumber("3799AV");
		labelRecoveryRequest.setTrackingNumber("3000859");
		
		/*ReferenceNumber referenceNumber = new ReferenceNumber();
		referenceNumber.setValue("890765");//require for ups
		
		LabelDelivery  labelDelivery = new LabelDelivery();
		labelDelivery.setLabelLinkIndicator("");
		
		
		ReferenceValues referenceValues = new ReferenceValues();
		referenceValues.setReferenceNumber(referenceNumber);
		referenceValues.setShipperNumber("3799AV");
		
		labelRecoveryRequest.setLabelDelivery(labelDelivery);
		
		labelRecoveryRequest.setReferenceValues(referenceValues);*/
		
		labelRecoveryRequestVo.setLabelRecoveryRequest(labelRecoveryRequest);
		
		

		
		String json = GsonContextLoader.getGsonContext().toJson(labelRecoveryRequestVo);
		System.out.println(" REQUEST JSON :: "+json);
		RequestEntity entity = new StringRequestEntity(json, "application/json", null);
		post.setRequestEntity(entity);
        //post.setRequestBody(json);
	int status = client.executeMethod(post);
	System.out.println(" status :: "+status);
	
	//System.out.println(post.getResponseBodyAsString());
	response= post.getResponseBodyAsString();
	System.out.println(" outPut :: "+response);
	
	
		}catch (Exception e) {
			System.out.println("Exception "+e);
		}
		
return response;
	}

}
