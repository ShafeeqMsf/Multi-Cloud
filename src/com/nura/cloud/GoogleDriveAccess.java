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
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;
import googleauthenticate.GetGoogleDriveService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleDriveAccess {

   private static final String CLIENT_ID = "31964007377-808tbe5i928kek0r18ch9a36fotlpacp.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "ZDraFr2kc8pa34vckoS4IpuF";
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

    public File createFolder(Drive service, String title) throws IOException {
        File body = new File();
        body.setTitle(title);
        //body.setParents(listParentReference);
        body.setMimeType("application/vnd.google-apps.folder");
        File file = service.files().insert(body).execute();
        return file;

    }

    public String uploadFile(Drive service, java.io.File uploadFile, String fileName , String folderName) throws IOException {

        List<File> folderDtls = GoogleDriveAccess.retrieveSpecificFile(service, folderName);
        String folderId = folderDtls.get(0).getId();
        //Insert a file  
        File body = new File();
        body.setTitle(fileName);
        body.setDescription("A test document");
        body.setMimeType("text/plain");
        body.setParents(Arrays.asList(new ParentReference().setId(folderId)));  
        
        java.io.File fileContent = uploadFile;
        FileContent mediaContent = new FileContent("text/plain", fileContent);

        File file = service.files().insert(body, mediaContent).execute();
        System.out.println("File ID: " + file.getId());
        return file.getId();
    }

    public String updateFile(Drive service, java.io.File uploadFile, String fileName, String fileId) throws IOException {

        //Insert a file  
        File body = new File();
        body.setTitle(fileName);
        body.setDescription("A test document");
        body.setMimeType("text/plain");

        java.io.File fileContent = uploadFile;
        FileContent mediaContent = new FileContent("text/plain", fileContent);

        File file = service.files().update(fileId, body, mediaContent).execute();
        System.out.println("File ID: " + file.getId());
        return file.getId();
    }

    public ParentReference uploadFileToSpecificFolder(Drive service, String folderId,
            String fileId) {
        ParentReference newParent = new ParentReference();
        newParent.setId(folderId);
        try {
            return service.parents().insert(fileId, newParent).execute();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
        return null;
    }

    public static List<File> retrieveAllFiles(Drive service) throws IOException {
        List<File> result = new ArrayList<File>();
        Files.List request = service.files().list();
        request.setQ("fullText contains 'BANK2' and mimeType contains 'application/vnd.google-apps.folder'");
        do {
            try {
                FileList files = request.execute();
                result.addAll(files.getItems());
                request.setPageToken(files.getNextPageToken());
            } catch (IOException e) {
                System.out.println("An error occurred: " + e);
                request.setPageToken(null);
            }
        } while (request.getPageToken() != null
                && request.getPageToken().length() > 0);

        return result;
    }

    private static ParentReference insertFileIntoFolder(Drive service, String folderId,
            String fileId) {
        ParentReference newParent = new ParentReference();
        newParent.setId(folderId);
        try {
            return service.parents().insert(fileId, newParent).execute();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
        return null;
    }

    public static List<File> retrieveSpecificFile(Drive service, String fileName) throws IOException {
        List<File> result = new ArrayList<File>();
        Files.List request = service.files().list();
        request.setQ("fullText contains '" + fileName + "' and mimeType contains 'application/vnd.google-apps.folder'");
        do {
            try {
                FileList files = request.execute();
                result.addAll(files.getItems());
                request.setPageToken(files.getNextPageToken());
            } catch (IOException e) {
                System.out.println("An error occurred: " + e);
                request.setPageToken(null);
            }
        } while (request.getPageToken() != null
                && request.getPageToken().length() > 0);

        return result;
    }

    public static void main(String[] args) throws IOException {
        GoogleDriveAccess _gda = new GoogleDriveAccess();
        Drive service = GetGoogleDriveService.getDriveService();
        List<File> filesList = _gda.retrieveAllFiles(service);

        System.out.println(filesList);
    }
}
