package com.gms.xms.workflow.task2.generateetfile.tollipec;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.webship.TollManifestFilter;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.TollIpecManifestDao;
import com.gms.xms.txndb.vo.webship.toll.TollIpecManifestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.Map;

/**
 * Posted from Sep 26, 2016 11:00:22 AM
 * <p>
 * Author huynd
 */
public class UpdateTollIpecManifestIdentifierTask implements Task2 {
    private static final Log log = LogFactory.getLog(UpdateTollIpecManifestIdentifierTask.class);

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
                TollIpecManifestDao tollIpecManifestDao = new TollIpecManifestDao();
                TollIpecManifestVo tollIpecManifestVo = new TollIpecManifestVo();
                tollIpecManifestVo.setShipmentId(shipmentIdStr);
                tollIpecManifestVo.setUploadFile(fileName);
                tollIpecManifestDao.insertTollIpecManifestIdentifier(addInfo, tollIpecManifestVo);
                Long ipecManifestId = tollIpecManifestVo.getIpecManifestId();
                if (ipecManifestId != 0) {
                    ShipmentDao shipmentDao = new ShipmentDao();
                    TollManifestFilter tollManifestFilter = new TollManifestFilter();
                    tollManifestFilter.setManifestId(ipecManifestId);
                    String[] shipmentIdArr = shipmentIdStr.split(",");
                    for (String shipmentId : shipmentIdArr) {
                        tollManifestFilter.setShipmentId(Long.valueOf(shipmentId));
                        shipmentDao.updateTollManifestIdentifier(addInfo, tollManifestFilter);
                    }
                    context.put(GenerateETFileConstants.MANIFEST_ID, ipecManifestId);
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
