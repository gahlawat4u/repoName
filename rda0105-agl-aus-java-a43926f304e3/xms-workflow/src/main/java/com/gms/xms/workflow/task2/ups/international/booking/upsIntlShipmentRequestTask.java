package com.gms.xms.workflow.task2.ups.international.booking;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.request.Piece;
import com.gms.delivery.ups.service.GsonContextLoader;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.Address;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.BillShipper;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.DeclaredValue;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.DeliveryConfirmation;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.Dimensions;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.InsuredValue;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.PackageServiceOptions;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.PackageWeight;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.Packaging;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.PaymentInformation;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.Phone;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.Request;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.ReturnService;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.Service;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.ServiceAccessToken;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.ShipFrom;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.ShipTo;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.Shipment;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.ShipmentCharge;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.ShipmentRequest;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.ShipmentServiceOptions;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.Shipper;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.ShippingRequest;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.TransactionReference;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.Type;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.UPSSecurity;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.UnitOfMeasurement;
import com.gms.delivery.ups.service.rest.shipment.pojo.request.UsernameToken;
import com.gms.delivery.ups.service.rest.shipment.pojo.response.FaultResponse;
import com.gms.delivery.ups.service.rest.shipment.pojo.response.ShippingResponse;
import com.gms.delivery.ups.service.rest.shipping.UpsShippingServices;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.persistence.service.service.PackageShipmentCarrierImp;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.PackageShipmentCarrierVO;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core.Task;

public class upsIntlShipmentRequestTask implements Task {

	private static final Log log =LogFactory.getLog(upsIntlShipmentRequestTask.class);
	@Override
	public boolean execute(ContextBase context) throws Exception {
		context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
		try{
		   ShipmentRequestVo shipmentRequestVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_REQUEST_VO), ShipmentRequestVo.class);
		   ShipmentInfoVo shipmentInfoVo = shipmentRequestVo.getShipmentInfo();
		  
		   String Username = SystemSettingCache.getInstance().getValueByKey("UPS UserId");
		   String Password = SystemSettingCache.getInstance().getValueByKey("UPS Password");
		   String AccessLicenseNumber = SystemSettingCache.getInstance().getValueByKey("UPS AccessLicenseNumber");
		   String ShipperNumber = SystemSettingCache.getInstance().getValueByKey("UPS_Web_Service_Account");
	
		   AddressVo senderAddress = shipmentInfoVo.getSenderAddress();
		   AddressVo reciverAddress = shipmentInfoVo.getReceiverAddress() ;
		   
		   
		   
		   
		   UpsShippingServices upsShippingServices = new UpsShippingServices();
		   ShippingRequest shippingRequestVo = new ShippingRequest();
		   CountryServiceImp countryServiceImp = new CountryServiceImp();
			UPSSecurity uPSSecurity = new UPSSecurity();
			
	        UsernameToken usernameToken = new UsernameToken();
	      
			usernameToken.setUsername(Username);
			usernameToken.setPassword(Password);
			uPSSecurity.setUsernameToken(usernameToken);
			
			ServiceAccessToken serviceAccessToken = new ServiceAccessToken();
			serviceAccessToken.setAccessLicenseNumber(AccessLicenseNumber);
			
			uPSSecurity.setServiceAccessToken(serviceAccessToken);

			shippingRequestVo.setUPSSecurity(uPSSecurity);

			ShipmentRequest shipmentRequest = new ShipmentRequest();

			Request request = new Request();
			request.setRequestOption("validate");

			TransactionReference transactionReference = new TransactionReference();

			transactionReference.setCustomerContext("");
			request.setTransactionReference(transactionReference);

			shipmentRequest.setRequest(request);

		    Shipment shipment = new Shipment();
			shipment.setDescription("UPS");
			
			Shipper shipper = new Shipper();
			
			DeliveryConfirmation deliveryConfirmation = new DeliveryConfirmation();
			ShipmentServiceOptions shipmentServiceOption = new ShipmentServiceOptions();
			
			
			
			
			
			Type type = new Type();
			type.setCode("01");
			type.setDescription("");
			PackageServiceOptions packageServiceOptions = new PackageServiceOptions();
			InsuredValue insuredValue = new InsuredValue() ;
			DeclaredValue declaredValue = new DeclaredValue();
			
			if(shipmentInfoVo.getInsuranceUserAmount() != null ){
			declaredValue.setMonetaryValue(shipmentInfoVo.getInsuranceUserAmount());
			declaredValue.setType(type);
			declaredValue.setCurrencyCode(shipmentInfoVo.getCurrencyCode());;
			packageServiceOptions.setDeclaredValue(declaredValue);}
			
			//code by shahabuddin
			Address address = new Address();
			if(senderAddress.getCountry() != 12)
			{
			shipper.setAttentionName(reciverAddress.getCompanyName());
			shipper.setName(reciverAddress.getContactName());
			shipper.setShipperNumber(ShipperNumber);
			
			Phone shipperPhone = new Phone();
			shipperPhone.setNumber(reciverAddress.getPhone());
			shipper.setPhone(shipperPhone);
			 address.setAddressLine(reciverAddress.getAddress());
			    address.setStateProvinceCode(reciverAddress.getState());
				address.setCity(reciverAddress.getCity());
				address.setCountryCode(countryServiceImp.getCountryByCountryId(reciverAddress.getCountry()).getCountryCode());
				address.setPostalCode(reciverAddress.getPostalCode().split(" ")[0]);
			}
			else
			{
				shipper.setAttentionName(senderAddress.getCompanyName());
				shipper.setName(senderAddress.getContactName());
				shipper.setShipperNumber(ShipperNumber);
				
				Phone shipperPhone = new Phone();
				shipperPhone.setNumber(senderAddress.getPhone());
				shipper.setPhone(shipperPhone);	
				 address.setAddressLine(senderAddress.getAddress());
				    address.setStateProvinceCode(senderAddress.getState());
					address.setCity(senderAddress.getCity());
					address.setCountryCode(countryServiceImp.getCountryByCountryId(senderAddress.getCountry()).getCountryCode());
					address.setPostalCode(senderAddress.getPostalCode().split(" ")[0]);
			}
			
			//end by shahabuddin previously only else condition where present
		    //Address address = new Address(); //previous code 
		   // address.setAddressLine(senderAddress.getAddress());
		    //code by rakesh sir
			//address.setStateProvinceCode(countryServiceImp.getCountryByCountryId(senderAddress.getCountry()).getCountryName().substring(0, 4));
		    
			//code by shahabuddin
			/* address.setAddressLine(senderAddress.getAddress());
		    address.setStateProvinceCode(senderAddress.getState());
			address.setCity(senderAddress.getCity());
			address.setCountryCode(countryServiceImp.getCountryByCountryId(senderAddress.getCountry()).getCountryCode());
			address.setPostalCode(senderAddress.getPostalCode().split(" ")[0]);*/
		    

		    shipper.setAddress(address);
			shipment.setShipper(shipper);
			
			ShipTo shipTo = new ShipTo();
			shipTo.setAttentionName(reciverAddress.getCompanyName());
			shipTo.setName(reciverAddress.getContactName());

			Phone phone = new Phone();
			phone.setNumber(reciverAddress.getPhone());
		    shipTo.setPhone(phone);
			
		    Address addressshipTo = new Address();
		    addressshipTo.setAddressLine(reciverAddress.getAddress());
			addressshipTo.setCity(reciverAddress.getCity());
			
			//code by rakesh sir
			//addressshipTo.setStateProvinceCode(countryServiceImp.getCountryByCountryId(reciverAddress.getCountry()).getCountryName().substring(0, 4));
			//code by shahabuddin
			addressshipTo.setStateProvinceCode(reciverAddress.getState());   
			
			
			addressshipTo.setCountryCode(countryServiceImp.getCountryByCountryId(reciverAddress.getCountry()).getCountryCode());
			addressshipTo.setPostalCode(reciverAddress.getPostalCode().split(" ")[0]);
			shipTo.setAddress(addressshipTo);
			
			Phone phoneTo = new Phone();
			phoneTo.setNumber(reciverAddress.getPhone());
		    shipTo.setPhone(phoneTo);
    		shipment.setShipTo(shipTo);

			ShipFrom shipFrom = new ShipFrom();
			shipFrom.setAttentionName(senderAddress.getCompanyName());
			shipFrom.setName(senderAddress.getContactName());
			
			Phone phoneF = new Phone();
			phoneF.setNumber(senderAddress.getPhone());
			shipFrom.setPhone(phoneF);

			Address addressshipFrom = new Address();
			addressshipFrom.setAddressLine(senderAddress.getAddress());
			addressshipFrom.setCity(senderAddress.getCity());
			addressshipFrom.setCountryCode(countryServiceImp.getCountryByCountryId(senderAddress.getCountry()).getCountryCode());
			addressshipFrom.setPostalCode(senderAddress.getPostalCode().split(" ")[0]);
			
			//code by shahabuddin
			  
			addressshipFrom.setStateProvinceCode(senderAddress.getState());
			
			
			shipFrom.setAddress(addressshipFrom);

			shipment.setShipFrom(shipFrom);

			Service service = new Service();
			ShipmentTypeServiceImp  shipmentTypeServiceImp = new ShipmentTypeServiceImp();
			ShipmentTypeVo shipmentTypeVo = shipmentTypeServiceImp.getShipmentTypeByShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
			
			service.setCode(shipmentTypeVo.getServiceCode());
			service.setDescription(shipmentTypeVo.getShipmentTypeName());

			shipment.setService(service);
			
			//code for the ups shipment
			ReturnService returnService = new ReturnService();
			if(senderAddress.getCountry() != 12)
			{
			returnService.setCode("9");
			shipment.setReturnService(returnService);
			}
			

			PaymentInformation paymentInformation = new PaymentInformation();
			ShipmentCharge shipmentCharge = new ShipmentCharge();
			shipmentCharge.setType("01");//

			BillShipper billShipper = new BillShipper();
			billShipper.setAccountNumber(ShipperNumber);
			shipmentCharge.setBillShipper(billShipper);

			paymentInformation.setShipmentCharge(shipmentCharge);

			shipment.setPaymentInformation(paymentInformation);

			com.gms.delivery.ups.service.rest.shipment.pojo.request.Package packages = null ;
			List<com.gms.delivery.ups.service.rest.shipment.pojo.request.Package> packageList = new ArrayList< com.gms.delivery.ups.service.rest.shipment.pojo.request.Package>();
			
			

			
			Integer totalPieces = 0;
            for (PieceVo pieceVo : shipmentInfoVo.getPieces()) {
            	packages = new com.gms.delivery.ups.service.rest.shipment.pojo.request.Package();
				
            	Packaging packaging = new Packaging();
				PackageShipmentCarrierVO packageShipmentCarrierVO = new PackageShipmentCarrierVO() ;
	    		packageShipmentCarrierVO.setShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
	    		packageShipmentCarrierVO.setPackageId(shipmentInfoVo.getPackageId());
				IPackageService servicePack = new PackageServiceImp();
				PackageShipmentCarrierImp packageShipmentCarrierImp = new PackageShipmentCarrierImp();
				List<PackageShipmentCarrierVO> packageShipmentCarrierVOs =  packageShipmentCarrierImp.selectByShipmentTypeId(packageShipmentCarrierVO);
				
				if( packageShipmentCarrierVOs.size() > 0){
				packaging.setCode(packageShipmentCarrierVOs.get(0).getPackageTypeCode());
				packaging.setDescription(packageShipmentCarrierVOs.get(0).getPackageName());

				packages.setPackaging(packaging);}
            	
				Dimensions dimensions = new Dimensions();
				
			    Piece piece = new Piece();
			    for (int i = 0; i < pieceVo.getQuantity(); i++) {
				Double weight = pieceVo.getWeight();
				if (weight == 0) {
				    weight = 0.5D;
				}
				piece.setWeight(new BigDecimal(pieceVo.getWeight()).setScale(2, RoundingMode.HALF_UP));
				BigInteger dimH = pieceVo.getDimensionH() != null && pieceVo.getDimensionH() != 0 ? new BigDecimal(pieceVo.getDimensionH()).setScale(2).toBigInteger() : BigInteger.ONE;
				BigInteger dimW = pieceVo.getDimensionW() != null && pieceVo.getDimensionW() != 0 ? new BigDecimal(pieceVo.getDimensionW()).setScale(2).toBigInteger() : BigInteger.ONE;
				BigInteger dimL = pieceVo.getDimensionL() != null && pieceVo.getDimensionL() != 0 ? new BigDecimal(pieceVo.getDimensionL()).setScale(2).toBigInteger() : BigInteger.ONE;
				dimensions.setWidth(dimW.toString());
				dimensions.setHeight(dimH.toString());
				dimensions.setLength(dimL.toString());
				
				UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement();
				unitOfMeasurement.setCode(shipmentInfoVo.getDimensionUnit());
				unitOfMeasurement.setDescription(shipmentInfoVo.getDimensionUnit());
				dimensions.setUnitOfMeasurement(unitOfMeasurement);
				
				if(!packaging.getDescription().equalsIgnoreCase("UPS Envelope"))
				packages.setDimensions(dimensions);
								
				packages.setPackageServiceOptions(packageServiceOptions);
				
				UnitOfMeasurement weightUnit = new UnitOfMeasurement();
				
				
						if(shipmentInfoVo.getWeightUnit().equals("KG")){
							weightUnit.setCode("KGS");
							weightUnit.setDescription("Kilogram");
						}
						else if(shipmentInfoVo.getWeightUnit().equals("LB")){
							weightUnit.setCode("LBS");
							weightUnit.setDescription("Pounds");
						}
			
				
				PackageWeight packageWeight = new PackageWeight();
				packageWeight.setUnitOfMeasurement(weightUnit);
				Double totalShipmentValue = 0D;
				Double totalWeight = 0D;
				Double itemWeight = 0D;
				Integer itemQuantity = 0;
				    itemWeight = pieceVo.getWeight() == null ? 0 : pieceVo.getWeight();
				    itemQuantity = pieceVo.getQuantity() == null ? 1 : pieceVo.getQuantity();
				    itemQuantity = (itemQuantity == 0) ? 1 : itemQuantity;
				    totalWeight += itemWeight * itemQuantity;
			
				packageWeight.setWeight(totalWeight.toString());
				packages.setPackageWeight(packageWeight);
				
				
				
				totalPieces++;
			    }
			    
			    //code by shahabuddin
			    if(senderAddress.getCountry() != 12)
				{
			    	packages.setDescription("required for return shipments");
				}
			    packageList.add(packages); //code by shahabuddin tem
			}
			
			shipment.set_package(packageList); ////code by shahabuddin tem

			
			shipmentRequest.setShipment(shipment);				
			shippingRequestVo.setShipmentRequest(shipmentRequest);
			  String response =  upsShippingServices.doShipment(shippingRequestVo);
			
			 
				  ShippingResponse shippingResponse = GsonContextLoader.getGsonContext().fromJson(response,ShippingResponse.class); 
			 
			  
			FaultResponse errorResponse = null;
			String errorMsg=""; 
			
			List<String> awbBarcode = null; //code by shahabuddin
			List<String> airbillNumber = null; //code by shahabuddin
			
			if(null==shippingResponse.getShipmentResponse())   
			
			{
				ObjectMapper objectMapper = new ObjectMapper();
				
				errorResponse  = objectMapper.readValue(response.toLowerCase(), FaultResponse.class);
				
				errorResponse = GsonContextLoader.getGsonContext().fromJson(response.toLowerCase(),FaultResponse.class);
				errorMsg=errorResponse.getFault().getDetail().getErrors().getErrordetail().getPrimaryerrorcode().getDescription();
				shipmentInfoVo.setErrorMsg(errorMsg);
				context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
			    context.put(Attributes.ERROR_MESSAGE, errorMsg);
			    log.error(errorMsg);
			    return false;

			}else{
				
				
				 int totalAwb =shippingResponse.getShipmentResponse().getShipmentResults().getPackageResults().length;//tShipmentIdentificationNumber(); 
				//List<String> airbillNumber = new ArrayList<String>(); //previous code
				   // List<String> awbBarcode =  new ArrayList<String>(); //previous code
				awbBarcode =  new ArrayList<String>();
				airbillNumber = new ArrayList<String>();
				 for(int i = 0 ; i < totalAwb ;i++){
					
					String  airbillNumberLocal =  shippingResponse.getShipmentResponse().getShipmentResults().getPackageResults()[i].getTrackingNumber();
					String  awbBarcodeLocal =  shippingResponse.getShipmentResponse().getShipmentResults().getPackageResults()[i].getShippingLabel().getGraphicImage();
					 
					 	 airbillNumber.add(airbillNumberLocal);
					 awbBarcode.add(awbBarcodeLocal);
				 }
				    
				   shipmentInfoVo.setUpsAirbillNo(airbillNumber);
				   shipmentInfoVo.setUpsAwbBarCode(awbBarcode);
				   shipmentInfoVo.setAirbillNumber(airbillNumber.get(0));
				   shipmentInfoVo.setAwbBarcode(awbBarcode.get(0));
			}
			
			List<PieceVo> savePieces = new ArrayList<>();
			int i=0; //code by shahabuddin
			for (PieceVo piece : shipmentInfoVo.getPieces()) {
			    PieceVo savePiece = new PieceVo();
			    savePiece.setDimensionL(piece.getDimensionL());
			    savePiece.setDimensionH(piece.getDimensionH());
			    savePiece.setDimensionW(piece.getDimensionW());
			    savePiece.setWeight(piece.getWeight());
			    savePiece.setDeadWeight(piece.getWeight());
			    savePiece.setDataIdentifier(piece.getDataIdentifier());
			    savePiece.setLicensePlate(airbillNumber.get(i));
			    savePiece.setLicensePlateBarcode(awbBarcode.get(i));  //code by shahabuddin
			    if (savePiece.getWeight() == 0) {
				savePiece.setWeight(1.0);
			    }
			    savePieces.add(savePiece);
			    i++;
			}
			shipmentInfoVo.setPieces(savePieces);
			
			
		    
		  
		   
			   context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
		   
		}catch(Exception e){
			 	context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
			    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
			    log.error(e);
			    return false;
		}
		return true;
	}


}
