/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.filehandling;

import java.io.File;
import javax.swing.JFileChooser;
import logger.LoggerUtil;

/**
 *
 * @author Arun kumar
 */
public class FileBrowser {

    private static final LoggerUtil log = new LoggerUtil();
    
    public static File selectFile() {
        log.addLog("Entry of the selectFile method");
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File file = null;
        int retVal = jf.showOpenDialog(jf);

        if (retVal == JFileChooser.APPROVE_OPTION) {
            file = jf.getSelectedFile();
            System.out.println("File Selected=>" + file.getName());
        }
        log.addLog("Exit of the selectFile method");
        return file;
    }

    public static File selectFolder() {
        log.addLog("Entry of the selectFolder method");
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File file = null;
        int retVal = jf.showOpenDialog(jf);

        if (retVal == JFileChooser.APPROVE_OPTION) {
            file = jf.getCurrentDirectory();
            System.out.println("File Selected=>" + file.getName());
        }
        log.addLog("Exit of the selectFolder method");
        return file;
    }
}
