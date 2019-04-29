package com.gms.xms.workflow.task2.generateetfile.toll;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.webship.TollManifestFilter;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.webship.TollManifestDao;
import com.gms.xms.txndb.vo.toll.TollManifestVo;
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
public class UpdateTollManifestIdentifierTask implements Task2 {
    private static final Log log = LogFactory.getLog(UpdateTollManifestIdentifierTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            String filePath = context.getString(GenerateETFileConstants.FILE_PATH);
            filePath = filePath.replace("\\", "/").trim();
            String fileName = context.getString(GenerateETFileConstants.FILE_NAME);
            File file = new File(filePath + fileName);
            if (!StringUtils.isBlank(fileName) && file.isFile()) {
                String shipmentIdStr = context.getString(GenerateETFileConstants.SHIPMENT_ID_STRING);
                TollManifestDao tollManifestDao = new TollManifestDao();
                TollManifestVo tollManifestVo = new TollManifestVo();
                tollManifestVo.setShipmentId(shipmentIdStr);
                tollManifestVo.setUploadFile(fileName);
                tollManifestDao.insertTollManifestIdentifier(addInfo, tollManifestVo);
                Long manifestId = tollManifestVo.getManifestId();
                if (manifestId != 0) {
                    ShipmentDao shipmentDao = new ShipmentDao();
                    TollManifestFilter tollManifestFilter = new TollManifestFilter();
                    tollManifestFilter.setManifestId(manifestId);
                    String[] shipmentIdArr = shipmentIdStr.split(",");
                    for (String shipmentId : shipmentIdArr) {
                        tollManifestFilter.setShipmentId(Long.valueOf(shipmentId));
                        shipmentDao.updateTollManifestIdentifier(addInfo, tollManifestFilter);
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
