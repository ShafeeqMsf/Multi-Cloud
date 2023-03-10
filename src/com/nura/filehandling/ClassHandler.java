/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.filehandling;

import constants.Constants;
import static constants.Constants.FILE_PARTS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logger.LoggerUtil;

/**
 *
 * @author Arun kumar
 */
public class ClassHandler implements Constants {

    private FileOutputStream fosParts;
    private FileInputStream fis;
    private final FolderCreations folderCreator = new FolderCreations();
    private final FileSplitting fileSplit = new FileSplitting();
    private File file;
    private int avgSize = 0, remSize = 0;
    private static final LoggerUtil log = new LoggerUtil();

    public File performOperations() {
        log.addLog("Entry into performOperations() in class ClassHandler");
        folderCreator.setFileName(file.getName());
        folderCreator.createParentFolder();
        fileSplit.setFile(getFile());
        setAvgSize(fileSplit.getAvgSize());
        setRemSize(fileSplit.getRemSize());
        byte[][] getFileParts = fileSplit.splitFiles();
        byte[] temp = null;

        File tempFile = folderCreator.getFolder().getAbsoluteFile();
        String fileExt = folderCreator.getFileExt();

        int cond = 0;
        for (int i = 0; i < FILE_PARTS; i++) {
            int j = 0;
            if (i == FILE_PARTS - 1) {
                cond = fileSplit.getRemSize();
                temp = new byte[cond];
                log.addLog("Writing remaining file contents=>" + fileSplit.getRemSize());
                log.addLog("Writing remaining file contents=>" + cond);
            } else {
                cond = fileSplit.getAvgSize();
                temp = new byte[cond];
                log.addLog("Writing remaining file contents=>" + cond);
            }
            for (; j < cond; j++) {
                temp[j] = getFileParts[i][j];
            }

            try {
                fosParts = new FileOutputStream(tempFile.getAbsolutePath() + "/" + tempFile.getName()
                        + "_part_" + (i + 1) + fileExt);
                try {
                    //Storing files in parts
                    fosParts.write(temp);
                    fosParts.flush();
                    fosParts.close();

                } catch (IOException ex) {
                    log.addLog(ex.getMessage());
                    Logger.getLogger(ClassHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                log.addLog(ex.getMessage());
                Logger.getLogger(ClassHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        log.addLog("Exit from performOperations() in class ClassHandler");
        return tempFile;

    }

    //Getter and Setter methods
    /**
     * @return the file
     */
    public File getFile() {
        return this.file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the avgSize
     */
    public int getAvgSize() {
        return avgSize;
    }

    /**
     * @param avgSize the avgSize to set
     */
    public void setAvgSize(int avgSize) {
        this.avgSize = avgSize;
    }

    /**
     * @return the remSize
     */
    public int getRemSize() {
        return remSize;
    }

    /**
     * @param remSize the remSize to set
     */
    public void setRemSize(int remSize) {
        this.remSize = remSize;
    }

}
