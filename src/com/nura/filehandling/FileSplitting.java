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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arun kumar
 */
public class FileSplitting implements Constants {

    private File file;
    private FileInputStream fis;
    private final int fileParts = FILE_PARTS;//Getting number of partition from constant interface
    private int avgSize;
    private int remSize;

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public byte[][] splitFiles() {
        byte[] temp = null;//To get the file contents 
        int avgSize = (int) file.length() / fileParts;
        this.setAvgSize(avgSize);
        int remSize = (int) (file.length() - (avgSize * (fileParts-1)));
        this.setRemSize(remSize);
        System.out.println("remm=>"+this.getRemSize()+"\\"+remSize);
        int arraySize;
        if (avgSize > remSize) {
            arraySize = avgSize;
        } else {
            arraySize = remSize;
        }
        byte[][] splittedParts = new byte[fileParts][arraySize];//To store splitted parts of the file

        
        try {
            fis = new FileInputStream(file);
            try {
                temp = new byte[fis.available()];
                fis.read(temp);
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileSplitting.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Split the files to form the tree
            int j = 0;
            for (int i = 0; i < (fileParts - 1); i++) {
                //System.out.println("" + i + ":" + j);
                for (int k = 0; k < avgSize; k++) {
                    splittedParts[i][k] = temp[j++];
                   // System.out.println("Split contents in " + i + ":" + k + "=>" + splittedParts[i][k]);
                }
            }
            //Add remaining byte array
            for (int i = 0; i < remSize; i++) {
                splittedParts[fileParts - 1][i] = temp[j++];
               
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSplitting.class.getName()).log(Level.SEVERE, null, ex);
        }

        return splittedParts;
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
