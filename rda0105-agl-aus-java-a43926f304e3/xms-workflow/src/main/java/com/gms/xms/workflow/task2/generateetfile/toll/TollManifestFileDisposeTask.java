package com.gms.xms.workflow.task2.generateetfile.toll;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.Task2;
import com.jcraft.jsch.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;

/**
 * Posted from Sep 24, 2016 10:22:20 AM
 * <p>
 * Author huynd
 */
public class TollManifestFileDisposeTask implements Task2 {
    private static final Log log = LogFactory.getLog(TollManifestFileDisposeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            String ftpServer = SystemSettingCache.getInstance().getValueByKey(GenerateETFileConstants.TOLL_FTP_SERVER);
            String ftpUserName = SystemSettingCache.getInstance().getValueByKey(GenerateETFileConstants.TOLL_FTP_USER_NAME);
            String ftpUserPass = SystemSettingCache.getInstance().getValueByKey(GenerateETFileConstants.TOLL_FTP_USER_PASS);
            String serverFolder = SystemSettingCache.getInstance().getValueByKey(GenerateETFileConstants.TOLL_SERVER_FOLDER);
            if (!serverFolder.substring(serverFolder.length() - 1).equalsIgnoreCase("/")) {
                serverFolder += "/";
            }
            JSch jsch = new JSch();
            Session session = null;
            try {
                // Connect sftp
                session = jsch.getSession(ftpUserName, ftpServer, 22);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setPassword(ftpUserPass);
                session.connect();
                // Open sftp
                Channel channel = session.openChannel("sftp");
                channel.connect();
                ChannelSftp sftpChannel = (ChannelSftp) channel;
                sftpChannel.cd(serverFolder);
                String filePath = context.getString(GenerateETFileConstants.FILE_PATH);
                String fileName = context.getString(GenerateETFileConstants.FILE_NAME);
                filePath = filePath.replace("\\", "/").trim();
                File file = new File(filePath + fileName);
                sftpChannel.put(new FileInputStream(file), file.getName());
                sftpChannel.exit();
                session.disconnect();
                context.put(GenerateETFileConstants.CONNECTED_ERROR, "0");
            } catch (JSchException e) {
                e.printStackTrace();
                context.put(GenerateETFileConstants.CONNECTED_ERROR, e.getMessage());
            } catch (SftpException e) {
                e.printStackTrace();
                context.put(GenerateETFileConstants.CONNECTED_ERROR, e.getMessage());
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
