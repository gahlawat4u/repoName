package com.gms.xms.workflow.task2.generateetfile.tollipec.manifest;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.webship.TollManifestFilter;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.TollIpecManifestDao;
import com.gms.xms.persistence.dao.webship.TollIpecConnoteDao;
import com.gms.xms.txndb.vo.webship.toll.TollIpecManifestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.Map;

/**
 * Posted from Sep 23, 2016 5:37:54 PM
 * <p>
 * Author huynd
 */
public class UpdateTollIpecManifestIdentifierViewLocalTask implements Task2 {
    private static final Log log = LogFactory.getLog(UpdateTollIpecManifestIdentifierViewLocalTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            String filePath = context.getString(GenerateETFileConstants.FILE_PATH);
            filePath = filePath.replace("\\", "/").trim();
            String fileName = context.getString(GenerateETFileConstants.FILE_NAME);
            File file = new File(filePath + fileName);
            if (!StringUtils.isBlank(fileName)) {
                String shipmentIdStr = context.getString(GenerateETFileConstants.SHIPMENT_ID_STRING);
                TollIpecManifestDao tollIpecManifestDao = new TollIpecManifestDao();
                TollIpecConnoteDao tollIpecConnoteDao = new TollIpecConnoteDao();
                TollIpecManifestVo tollIpecManifestVo = new TollIpecManifestVo();

                tollIpecManifestVo.setShipmentId(shipmentIdStr);
                tollIpecManifestVo.setUploadFile(fileName);
                tollIpecManifestDao.insertTollIpecManifestIdentifier(addInfo, tollIpecManifestVo);
                Long manifestId = tollIpecManifestVo.getIpecManifestId();
                if (manifestId != 0) {
                    ShipmentDao shipmentDao = new ShipmentDao();
                    TollManifestFilter tollManifestFilter = new TollManifestFilter();
                    tollManifestFilter.setManifestId(manifestId);
                    String[] shipmentIdArr = shipmentIdStr.split(",");
                    for (String shipmentId : shipmentIdArr) {
                        tollManifestFilter.setShipmentId(Long.valueOf(shipmentId));
                        shipmentDao.updateTollManifestIdentifier(addInfo, tollManifestFilter);
                        tollIpecConnoteDao.updateConnoteGenManifest(addInfo,shipmentId);
                    }
                    context.put(GenerateETFileConstants.MANIFEST_ID, manifestId);
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
