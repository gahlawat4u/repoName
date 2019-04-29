package com.gms.xms.workflow.task2.tnt.connote;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.tnt.TntViewConnoteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.tnt.TntViewConnoteVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Posted from GetTntDomesticBaseChargeTask
 * <p>
 * Author TANDT
 */
public class PrepareViewConnoteDataTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareViewConnoteDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            AddressDao addressDao = new AddressDao();
            CountryDao countryDao = new CountryDao();
            ShipmentDao shipmentDao = new ShipmentDao();
            PieceDao pieceDao = new PieceDao();
            FranchiseDao franchiseDao = new FranchiseDao();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            TntViewConnoteVo tntViewConnoteVo = new TntViewConnoteVo();

            ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
            tntViewConnoteVo.setShipment(shipmentVo);

            AddressVo senderAddress = addressDao.selectById(shipmentVo.getSenderAddressId());
            CountryVo senderCountry = countryDao.getCountryById(senderAddress.getCountry());
            senderAddress.setCountryDetail(senderCountry);
            tntViewConnoteVo.setSenderAddress(senderAddress);

            AddressVo receiverAddress = addressDao.selectById(shipmentVo.getReceiverAddressId());
            CountryVo receiverCountry = countryDao.getCountryById(receiverAddress.getCountry());
            receiverAddress.setCountryDetail(receiverCountry);
            tntViewConnoteVo.setReceiverAddress(receiverAddress);

            double inchToCm = 2.54;
            int cm3ToM3 = 1000000;
            List<PieceVo> pieceVos = pieceDao.selectByShipmentId(shipmentId);
            Double totalVolume = 0d;
            Double totalWeight = 0d;
            for (PieceVo pieceVo : pieceVos) {
                totalWeight += pieceVo.getDeadWeight();
                totalVolume += pieceVo.getDimensionH() * pieceVo.getDimensionL() * pieceVo.getDimensionW();
            }

            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentVo.getShipmentTypeId());
            tntViewConnoteVo.setShipmentType(shipmentTypeVo);

            if (shipmentVo.getDimensionUnit().equalsIgnoreCase("cm")) {
                totalVolume = totalVolume / cm3ToM3;
            } else if (shipmentVo.getDimensionUnit().equalsIgnoreCase("in")) {
                Double volumeConvert = totalVolume * inchToCm * inchToCm * inchToCm;
                totalVolume = volumeConvert / cm3ToM3;
            }
            tntViewConnoteVo.setTotalWeight(totalWeight);
            tntViewConnoteVo.setTotalVolume(new BigDecimal(totalVolume).setScale(2, RoundingMode.HALF_UP).doubleValue());

            // Get tnt account
            String franchiseCode = String.valueOf(shipmentVo.getCustomerCode()).substring(0, 3);
            FranchiseVo franchiseVo = franchiseDao.selectFranchiseByFranchiseCode(franchiseCode);
            String tntAccount = franchiseVo.getTntAccount();
            tntViewConnoteVo.setTntAccount(tntAccount);



            String systemAddress = receiverAddress.getCompanyName() + "\n" + receiverAddress.getAddress() + "\n" + receiverAddress.getCity() + "\n" + receiverAddress.getPostalCode() + "\n" + receiverAddress.getPhone();
            if (shipmentVo.getBoundStatus() == 1) {
                systemAddress = SystemSettingCache.getInstance().getValueByKey("System Address");
            }
            tntViewConnoteVo.setSystemAddress(systemAddress);

            String airbillNumber = shipmentVo.getAirbillNumber();
            String barcodePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/barcode_" + airbillNumber + ".png";
            AppUtils.createBarCode(airbillNumber, "png", barcodePath, 500, 500);
            File imgDefault = new File(barcodePath);
            byte[] imgBytesDefault = AppUtils.readContentIntoByteArray(imgDefault);
            byte[] imgBytesAsBase64Default = Base64.encodeBase64(imgBytesDefault);
            if (shipmentVo.getStatus() == 1) {
                InputStream inputStream = new ClassPathResource("templates/pdf/history/AWB_VOID.jpg").getInputStream();
                imgBytesAsBase64Default = Base64.encodeBase64( IOUtils.toByteArray(inputStream) );
            }
            String imgDataAsBase64Default = new String(imgBytesAsBase64Default);
            String imgAsBase64Default = "data:image/png;base64," + imgDataAsBase64Default;
            String barCode = imgAsBase64Default;
            tntViewConnoteVo.setBarCode(barCode);

            InputStream imgTnt = new ClassPathResource("templates/pdf/history/tnt.png").getInputStream();
            byte[] imgBytesAsBase64Tnt = Base64.encodeBase64(IOUtils.toByteArray(imgTnt));
            String imgDataAsBase64Tnt = new String(imgBytesAsBase64Tnt);
            String imgAsBase64Tnt = "data:image/png;base64," + imgDataAsBase64Tnt;
            String logo = imgAsBase64Tnt;
            tntViewConnoteVo.setLogo(logo);

            TntViewConnoteModel tntViewConnoteModel = ModelUtils.createModelFromVo(tntViewConnoteVo, TntViewConnoteModel.class);
            context.put(Attributes.TNT_VIEW_CONNOTE_DATA, tntViewConnoteModel);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }

        return true;
    }
}
