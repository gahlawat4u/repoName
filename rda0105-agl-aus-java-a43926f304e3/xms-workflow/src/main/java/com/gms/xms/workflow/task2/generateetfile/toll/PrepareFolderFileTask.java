package com.gms.xms.workflow.task2.generateetfile.toll;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Posted from Sep 26, 2016 10:59:33 AM
 * <p>
 * Author huynd
 */
public class PrepareFolderFileTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareFolderFileTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Prepare file name
            String fileName = "T1250";
            String conn = "AQRD";
            fileName += "_" + conn + "_";
            DateFormat dateFormat = new SimpleDateFormat("yMMddHms");
            fileName += dateFormat.format(new Date()) + ".dat";
            context.put(GenerateETFileConstants.FILE_NAME, fileName);
            // Prepare folder
            String filePath = context.get(GenerateETFileConstants.FILE_PATH);
            filePath = filePath.replace("\\", "/").trim();
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            context.put(GenerateETFileConstants.FILE_PATH, filePath);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
