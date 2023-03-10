/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.cloud;

/**
 *
 * @author Arun Kumar
 */
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import constants.Constants;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GoogleDriveAccessDownload {

    public java.io.File downloadFile(Drive service, String fileId, String fileName) throws IOException {

        //Download the file now
        File file1 = service.files().get(fileId).execute();
        java.io.File downFile = new java.io.File(Constants.FILE_DOWNLOAD_LOCATION + fileName);
        FileOutputStream fos = new FileOutputStream(downFile);
        InputStream is = downloadFile(service, file1);
        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = is.read(bytes)) != -1) {
            fos.write(bytes, 0, read);
        }
        System.out.println("File downloaded");
        
        return downFile;
    }

    private static InputStream downloadFile(Drive service, File file) {
        if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
            try {
                HttpResponse resp
                        = service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
                        .execute();
                return resp.getContent();
            } catch (IOException e) {
                // An error occurred.
                e.printStackTrace();
                return null;
            }
        } else {
            // The file doesn't have any content stored on Drive.
            return null;
        }
    }
}
