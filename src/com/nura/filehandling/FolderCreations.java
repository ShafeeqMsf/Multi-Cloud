/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.filehandling;

import constants.Constants;
import static constants.Constants.FILE_PARTS;
import java.io.File;

/**
 *
 * @author Arun kumar
 */
public class FolderCreations implements Constants {

    private File file;
    private File folder;
    private String fileName;
    private int fileParts;
    private String fileExt;
    
    
    public File createFolder(String folderName){
        
        File f = new File(folderName);
        if(!f.exists()){
            f.mkdirs();
        }        
        return f;        
    }
    public File createTempFolder(){
        
        File f =  new File("Temp");
        if(!f.exists()){
            f.mkdirs();
        }
        return f;
    }
    
    

    public void createParentFolder() {
        setFolder(fileName.substring(0, fileName.indexOf(".")));
        if (!folder.exists()) {
            folder.mkdirs();
        }
        System.out.println("" + folder.getAbsolutePath());
        //folder = folder.getAbsoluteFile();
        setFileExt();
       // createChildFolders();
       
    }
    
    public void createChildFolders() {
        System.out.println("Inside child folder creations");
        System.out.println("" + folder.getAbsolutePath());
        File tempFile = null;
        setFileParts();
        for (int i = 0; i < fileParts; i++) {
            tempFile = new File(folder.getAbsolutePath(), fileName.substring(0, fileName.indexOf(".")) + "_part_" + (i + 1));
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            tempFile = new File(folder.getAbsolutePath(), fileName.substring(0, fileName.indexOf(".")) + "_hash_" + (i + 1));
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
        }

    }

       //Getters and setter methods
    public void setFileParts() {
        this.fileParts = FILE_PARTS;
    }

    public int getFileParts() {
        return this.fileParts;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the folder
     */
    public File getFolder() {
        return folder;
    }

    /**
     * @param fileName
     */
    public void setFolder(String fileName) {
        this.folder = new File(fileName);
    }

    /**
     * @return the fileExt
     */
    public String getFileExt() {
        return fileExt;
    }

    /**
     * @param fileExt the fileExt to set
     */
    public void setFileExt() {
        this.fileExt = fileName.substring(fileName.indexOf("."));
    }

}
