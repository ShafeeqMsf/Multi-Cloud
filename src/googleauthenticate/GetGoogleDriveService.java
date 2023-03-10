/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleauthenticate;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.nura.cloud.GoogleDriveAccess;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Arun Kumar
 */
public class GetGoogleDriveService {

    private static final String CLIENT_ID = "31964007377-808tbe5i928kek0r18ch9a36fotlpacp.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "ZDraFr2kc8pa34vckoS4IpuF";
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
    private static Drive service = null;

    static {
        System.out.println("Service value:-"+service);
        if (service == null) {
            try {
                HttpTransport httpTransport = new NetHttpTransport();
                JsonFactory jsonFactory = new JacksonFactory();

                GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
                        .setAccessType("online")
                        .setApprovalPrompt("auto").build();

                String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
                System.out.println("Please open the following URL in your browser then type the authorization code:");
                System.out.println("  " + url);
                try {
                    System.out.println("Opening the url");
                    Desktop.getDesktop().browse(new URL(url).toURI());
                } catch (URISyntaxException ex) {
                    Logger.getLogger(GoogleDriveAccess.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GetGoogleDriveService.class.getName()).log(Level.SEVERE, null, ex);
                }

                String code = JOptionPane.showInputDialog("Enter the google code");

                GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
                GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);

                //Create a new authorized API client
                service = new Drive.Builder(httpTransport, jsonFactory, credential).build();
            } catch (IOException ex) {
                Logger.getLogger(GetGoogleDriveService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Drive getDriveService() {
        return service;
    }
}
