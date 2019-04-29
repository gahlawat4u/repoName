package com.gms.xms.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Posted from Nov 22, 2016 2:27:15 PM
 * <p>
 * Author dattrinh
 */
public class ZipFileUtils {
    public static List<String> unZipIt(String zipFile, String outputFolder) throws Exception {
        byte[] buffer = new byte[1024];
        // Create output directory is not exists.
        File folder = new File(outputFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
        // Get the zip file content.
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        // Get the zipped file list entry.
        ZipEntry ze = zis.getNextEntry();
        // Create files list.
        List<String> fileList = new LinkedList<String>();
        String newFileName;
        while (ze != null) {
            String fileName = ze.getName();
            newFileName = outputFolder + File.separator + fileName;
            fileList.add(newFileName);
            File newFile = new File(newFileName);
            // Create all non exists folders
            // else you will hit FileNotFoundException for compressed folder.
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        return fileList;
    }
}
