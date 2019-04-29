package com.gms.xms.workflow.render.othercarrier;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.webship.history.HistoryDetailAccessorialModel;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.model.webship.history.HistoryDetailPieceModel;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.util.IOUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtherCarrierRenderImp implements IOtherCarrierRender {
    @Override
    public void genShipmentReceiptHtml(HistoryDetailInfoModel detailInfoModel, List<HistoryDetailPieceModel> pieces, CustomerAddressModel franchiseAddress, String outPutFilePath, List<HistoryDetailAccessorialModel> detailAccessorialModels) throws Exception {
        String siteAddress = SystemSettingCache.getInstance().getValueByKey("Site Address");
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/shipment_receipt/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        Map<String, Object> data = new HashMap<>();
        data.put("logo", imgAsBase64);
        data.put("pieces", pieces);
        data.put("shipmentInfo", detailInfoModel);
        data.put("siteAddress", siteAddress);
        data.put("franchiseAddress", franchiseAddress);
        data.put("detailInfoModel", franchiseAddress);
        data.put("detailAccessorialModels", detailAccessorialModels);
        AppUtils.template2File(outPutFilePath, false, "templates/pdf/shipment_receipt/shipment_receipt.ftl", data);
    }
}
