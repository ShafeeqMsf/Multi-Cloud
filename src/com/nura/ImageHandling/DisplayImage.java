/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.ImageHandling;

import com.nura.ui.view.UserLogin;
import javax.swing.ImageIcon;
import logger.LoggerUtil;

/**
 *
 * @author Arun kumar
 */
public class DisplayImage {
    static LoggerUtil log = new LoggerUtil();
    public static ImageIcon createImageIcon(String location){
        java.net.URL imgURL = UserLogin.class.getResource(location);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: ");
            log.addLog("Couldn't find file: ");
            return null;
        }
               
    }
}
