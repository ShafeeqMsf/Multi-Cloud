package com.nura.dropbox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxDownloadStyleBuilder;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.FullAccount;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class AuthenticateUser {

   private static final String ACCESS_TOKEN = "sl.BIkFXVH24pU179Tuocky0mQPxgfSsXzFaerD3OI7fja1uxnBQKe-JZEeCInocrHEwmCIAz2biWsYqEKs_4igtpjkkmu4jI59TaPtGRgu9WTGP0gkO8CNnZ09ahVu1UyCzy0JBvI";
    private static final String APP_KEY ="cjxacucmteyiq4z";
    private static final String APP_SECRET = "jky8m43ruvrzo18";

    public static void main() throws DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/connect");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());


    }
   public static String upload(String fileLoc, String location) throws DbxException, UploadErrorException, IOException {
        File tempFile = new File(fileLoc);
        FileInputStream fis = null;
        String url = "";
        String fileName = tempFile.getParentFile().getName();
       // System.out.println("*****Connected******");
         System.out.println("*****Connected******");
        System.out.println("File Name "+fileName);
        try {
 
             DbxRequestConfig config = new DbxRequestConfig("dropbox/connect");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            FullAccount account = client.users().getCurrentAccount();
              fis = new FileInputStream(fileLoc);
             
           FileMetadata metadata = client.files().uploadBuilder("/" + location + "/" + tempFile.getName())
                    .uploadAndFinish(fis);
        
          
                 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AuthenticateUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           // try {
               // fis.close();
           // } catch (IOException ex) {
              //  Logger.getLogger(AuthenticateUser.class.getName()).log(Level.SEVERE, null, ex);
           // }
        }
        return url;
    }
      public static String uploadAlter(String fileLoc, String location) throws DbxException, UploadErrorException, IOException {
        File tempFile = new File(fileLoc);
        FileInputStream fis = null;
        String url = "";
        String fileName = tempFile.getParentFile().getName();
       // System.out.println("*****Connected******");
         System.out.println("*****Connected******");
        System.out.println("File Name "+fileName);
        try {
 
             DbxRequestConfig config = new DbxRequestConfig("dropbox/connect");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            FullAccount account = client.users().getCurrentAccount();
              fis = new FileInputStream(fileLoc);
             
           FileMetadata metadata = client.files().uploadBuilder("/" + location + "/" + tempFile.getName())
                    .uploadAndFinish(fis);
        
          
                 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AuthenticateUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(AuthenticateUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return url;
    }
       public static File download(String location, String fileName) throws DbxException, FileNotFoundException, IOException {

        File file = null;
        FileOutputStream outputStream = null;
       
        try {
             DbxRequestConfig config = new DbxRequestConfig("dropbox/connect");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            FullAccount account = client.users().getCurrentAccount();
            file = new File("D://temp//stem//"+fileName);
             DbxDownloadStyleBuilder<FileMetadata> downloader = client.files().downloadBuilder("/" + location + "/" + fileName).range(0, 2464744);
        FileOutputStream fos = new FileOutputStream(file);
        downloader.download(fos);
        fos.close();
        System.out.println("Download finish path is"+file.getName());
            //outputStream = new FileOutputStream(file);
            //mDBApi.getFile("/" + location + "/" + fileName, null, outputStream, null);
        }
             catch (IOException ex) {
                //Logger.getLogger(AuthenticateUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return file;
    }
   
}
