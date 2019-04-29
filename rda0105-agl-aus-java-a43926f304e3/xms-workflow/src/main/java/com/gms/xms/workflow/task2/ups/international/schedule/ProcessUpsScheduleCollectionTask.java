package com.gms.xms.workflow.task2.ups.international.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.shipment.TntDomShipmentRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.gms.xms.txndb.vo.schedulecollection.BookPickupRequestVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.model.webship.UPSSchedulingModel;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.schedule.IScheduleCollectionService;
import com.gms.xms.persistence.service.schedule.ScheduleCollectionServiceImp;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.delivery.ups.service.rest.shipping.UpsPickupServices;
import com.gms.delivery.ups.service.rest.shipping.ResponseUpsPickup;
import com.gms.delivery.ups.service.GsonContextLoader;
import com.gms.xms.common.exception.CustomException;

/**
 * Posted from ProcessUpsScheduleCollectionTask
 * <p>
 * Author @author HungNT Feb 3, 2016
 */
public class ProcessUpsScheduleCollectionTask implements Task2 {
private static final Log log = LogFactory.getLog(ProcessUpsScheduleCollectionTask.class);
	
	@Override
	public boolean execute(ContextBase2 context) throws Exception {

		try{
			 Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
			 // Get ModifyPickupRequest.
			    BookPickupRequestVo bookPickupRequestVo = context.get(Attributes.UPS_BOOK_SCHEDULE_REQUEST);
                List<PieceVo> lPieceVos = new ArrayList<PieceVo>();
                String authenticationKey = "gmsYupsZTimeXDel";
                String ShipperNumber = SystemSettingCache.getInstance().getValueByKey("UPS_Web_Service_Account");
     		  
                
             /* PieceVo pieceVo1 = new PieceVo();
                PieceVo pieceVo2 = new PieceVo();
                PieceVo pieceVo3 = new PieceVo();
                
                pieceVo1.setWeight(1D);
                pieceVo1.setDimensionH(1D);
                pieceVo1.setDimensionL(1D);
                pieceVo1.setDimensionW(1D);
                pieceVo1.setQuantity(1);
                lPieceVos.add(pieceVo1);*/
                
                /*pieceVo2.setWeight(1D);
                pieceVo2.setDimensionH(1D);
                pieceVo2.setDimensionL(1D);
                pieceVo2.setDimensionW(1D);
                pieceVo2.setQuantity(1);
                lPieceVos.add(pieceVo2);
                
                pieceVo3.setWeight(1D);
                pieceVo3.setDimensionH(1D);
                pieceVo3.setDimensionL(1D);
                pieceVo3.setDimensionW(1D);
                pieceVo3.setQuantity(1);*/
              
              //  lPieceVos.add(pieceVo3);
                
                
                
                CountryServiceImp countryServiceImp = new CountryServiceImp();
			    ScheduleCollectionVo scheduleCollectionVo = bookPickupRequestVo.getScheduleCollection();
			    UPSSchedulingModel upsSchedulingModel = new UPSSchedulingModel();
		        upsSchedulingModel.setCity(bookPickupRequestVo.getPickupAddress().getCity());
			    upsSchedulingModel.setAuthenticationKey(authenticationKey);
			    if(scheduleCollectionVo.getPickupTimeNoLater() == null){
			    	upsSchedulingModel.setCloseTime("06:00:00");
			    }else{
			    upsSchedulingModel.setCloseTime(scheduleCollectionVo.getPickupTimeNoLater());}
			    upsSchedulingModel.setCompanyName(bookPickupRequestVo.getPickupAddress().getCompanyName());
			    upsSchedulingModel.setCountryCode(countryServiceImp.getCountryByCountryId(bookPickupRequestVo.getPickupAddress().getCountry()).getCountryCode());
			    upsSchedulingModel.setContactName(bookPickupRequestVo.getPickupAddress().getContactName());
			    upsSchedulingModel.setCountryName(countryServiceImp.getCountryByCountryId(bookPickupRequestVo.getPickupAddress().getCountry()).getCountryName());
			    
			    ShipmentTypeServiceImp  shipmentTypeServiceImp = new ShipmentTypeServiceImp();
				ShipmentTypeVo shipmentTypeVo = shipmentTypeServiceImp.getShipmentTypeByShipmentTypeId(bookPickupRequestVo.getShipment().getShipmentTypeId());
				
			    upsSchedulingModel.setServiceCode(shipmentTypeVo.getServiceCode());
			    upsSchedulingModel.setPhone(bookPickupRequestVo.getPickupAddress().getPhone());
			    upsSchedulingModel.setBillingAccount(ShipperNumber);
			    upsSchedulingModel.setPieceList(bookPickupRequestVo.getPieceVos());
			    upsSchedulingModel.setAddressLine(bookPickupRequestVo.getPickupAddress().getAddress());
			    
			    
			    upsSchedulingModel.setAirbill(bookPickupRequestVo.getShipment().getAirbillNumber());
			    upsSchedulingModel.setPickupDate( new SimpleDateFormat("yyyy-MM-dd").format(scheduleCollectionVo.getPickupDate()));
			    upsSchedulingModel.setPostalCode(bookPickupRequestVo.getPickupAddress().getPostalCode().split(" ")[0]);
			    upsSchedulingModel.setReadyTime(scheduleCollectionVo.getPickupTime());
			    if(scheduleCollectionVo.getSpecialInstructions() != null)
			    upsSchedulingModel.setSpecialInstruction(scheduleCollectionVo.getSpecialInstructions());
			    else
			    	 upsSchedulingModel.setSpecialInstruction("On time ");
			    upsSchedulingModel.setStateProvince(bookPickupRequestVo.getPickupAddress().getState());
			   
                upsSchedulingModel.setToCountryCode(countryServiceImp.getCountryByCountryId(bookPickupRequestVo.getReceiverAddress().getCountry()).getCountryCode());
                upsSchedulingModel.setWeightUnit(bookPickupRequestVo.getShipment().getWeightUnit().toUpperCase());
                upsSchedulingModel.setTotalWeight( scheduleCollectionVo.getTotalWeight().toString());
                
                
			    
			  /* upsSchedulingModel.setCity("CANNES");
			    upsSchedulingModel.setAuthenticationKey(authenticationKey);
			    upsSchedulingModel.setCloseTime("16:30");
			    upsSchedulingModel.setCompanyName("GMS LOGISTICS FRANCE");
			    upsSchedulingModel.setCountryCode("FR");
			    upsSchedulingModel.setContactName("THIERRY MARTIN");
			    upsSchedulingModel.setServiceCode("07");
			    upsSchedulingModel.setPhone("9087654321");
			    upsSchedulingModel.setBillingAccount("7R7877");
			    upsSchedulingModel.setCountryName("FRANCE");
			    
			    upsSchedulingModel.setPieceList(lPieceVos);
			    upsSchedulingModel.setAddressLine("VILLA LES FLEURS ");
			    upsSchedulingModel.setAirbill("XF939248711FR");
			    upsSchedulingModel.setPickupDate("2017-05-02");
			    upsSchedulingModel.setPostalCode("06400");
			    upsSchedulingModel.setReadyTime("08:30");
			    upsSchedulingModel.setSpecialInstruction("no value");
			    upsSchedulingModel.setStateProvince("ALPES-MARITIMES");
                upsSchedulingModel.setToCountryCode("IN");
                upsSchedulingModel.setWeightUnit("KG");
                upsSchedulingModel.setTotalWeight("5");*/
               // upsSchedulingModel.setPieceVo(pieceVo);
              
                
                
			    UpsPickupServices upsPickupServices = new UpsPickupServices();
				
			    
				String upsPickup =      upsPickupServices.doShipmentPickup(upsSchedulingModel);
				   System.out.println(" upsPickup "+ upsPickup); 
			    
				   ResponseUpsPickup responseUpsPickup = GsonContextLoader.getGsonContext().fromJson(upsPickup,ResponseUpsPickup.class);  
				   
				   if (responseUpsPickup.getType().equals("error")) {
						String errorMsg = "Fail to send pick up request.";
						errorMsg += responseUpsPickup.getDesc();
						log.error(errorMsg);
						throw new CustomException(errorMsg) ;
					    } else {
						
						String comfirmationNumber = String.valueOf(responseUpsPickup.getConfirmationNumber());
						scheduleCollectionVo.setConfirmationNo(comfirmationNumber);
						scheduleCollectionVo.setStatus((byte) 1);
						IScheduleCollectionService scheduleCollectionService = new ScheduleCollectionServiceImp();
						scheduleCollectionService.doSchedule(addInfo, bookPickupRequestVo.getPickupAddress(), scheduleCollectionVo);
						context.put(Attributes.SCHEDULECOLLECTION_VO, scheduleCollectionVo);
					    }
		}
		catch(Exception e){
			log.error(e.getMessage(), e);
		    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
		    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
		    return false;	
		}
		return true;
	}


}
