/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erasurecode;

import com.nura.dropbox.AuthenticateUser;
import com.nura.ui.view.ActivityMenu;
import com.nura.ui.view.UserLogin;
//import com.sun.glass.ui.android.Activity;
import logger.LoggerUtil;

/**
 *
 * @author Arun Kumar
 */
public class ErasureCode {

    private static final LoggerUtil log = new LoggerUtil();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        log.addLog("Program started");
        try {
             //new ActivityMenu(;
            googleauthenticate.GetGoogleDriveService.getDriveService();
           // AuthenticateUser.main();
        } catch (Exception ex) {
            log.addLog(ex.getMessage());
        }
        new UserLogin().main();
    }
    
}
