package com.gms.xms.workflow.render.dhl;

import java.io.FileOutputStream;
import java.util.List;

import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.GifImage;

public class DhlRenderImp implements IDhlRender {
    @Override
    public void genAirbillFile(String outPutFilePath, Long shipmentId) throws Exception {
        try {
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(shipmentId);
            if (shipmentVo != null) {
                String awbBarcode = shipmentVo.getAwbBarcode();
                AppUtils.createPDFFromBarCode(outPutFilePath, awbBarcode);
            } else {
                throw new Exception("Cannot find the shipment.");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    @Override
    public void genAirbillUpsFile(String outPutFilePath, Long shipmentId) throws Exception {
    	try{
    		String  outPutFilePath1 = outPutFilePath.replace(".pdf", ".gif");
   		    IShipmentService shipmentService = new ShipmentServiceImp();
   		    List<PieceVo> shipmentVo = shipmentService.selectPieceById(shipmentId);
   		   
   		    Document convertGif2Pdf=new Document();
		    PdfWriter.getInstance(convertGif2Pdf, new FileOutputStream(outPutFilePath));
		    convertGif2Pdf.open();
   		      Image myimage=null;
   		      
   		     if (shipmentVo != null) {
   		   for(PieceVo pieceVoList : shipmentVo)
   			{
   			AppUtils.createPDFFromBarCode(outPutFilePath1,pieceVoList.getLicensePlateBarcode());
   		 GifImage myGif=new GifImage(outPutFilePath1);
   		   
   		   
   		    myimage=Image.getInstance(outPutFilePath1);
   		    myimage.scalePercent(60);
   		    myimage.setRotationDegrees(270);
   	
   		    convertGif2Pdf.add(myimage);
   			}
   		  
		    convertGif2Pdf.close();

   		    }
    		
    		
    		
    		/*String  outPutFilePath1 = outPutFilePath.replace(".pdf", ".gif");
    		 IShipmentService shipmentService = new ShipmentServiceImp();
    		    ShipmentVo shipmentVo = shipmentService.selectById(shipmentId);  //previous code
    		    //List<PieceVo> shipmentVo = shipmentService.selectPieceById(shipmentId); //code b
    		    if (shipmentVo != null) {
    			String awbBarcode = shipmentVo.getAwbBarcode();
    			
    			AppUtils.createPDFFromBarCode(outPutFilePath1, awbBarcode);
    			
    			
    			Document convertGif2Pdf=new Document();
    		  
    		    PdfWriter.getInstance(convertGif2Pdf, new FileOutputStream(outPutFilePath));
    		    convertGif2Pdf.open();
    		 
    		    GifImage myGif=new GifImage(outPutFilePath1);
    		   
    		   
    		    Image myimage=Image.getInstance(outPutFilePath1);
    		    myimage.scalePercent(60);
    		    myimage.setRotationDegrees(270);
    		 
    		    convertGif2Pdf.add(myimage);
    		    convertGif2Pdf.close();
    		  
    			
    		    }*/else{
    			throw new Exception("Cannot find the shipment.");
    		    }
    		
    		
    	}catch (Exception e) {
    		 throw e;
		}
    	
    }
}
