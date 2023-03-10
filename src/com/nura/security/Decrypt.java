/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.security;

import java.security.spec.AlgorithmParameterSpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import logger.LoggerUtil;
import sun.misc.BASE64Decoder;

/**
 *
 * @author 
 */
public class Decrypt {

    Cipher cipher;
    private static final LoggerUtil log = new LoggerUtil();

    public static byte[] main(String recData) throws Exception {
        log.addLog("Entry main method of Decrypt class");
        // Input encrypted String
        String input = recData;
        log.addLog("Received string is:-" + recData);
        // password to decrypt 16 bit
        String strPassword = "password12345678";

        // put this as key in AES
        SecretKeySpec key = new SecretKeySpec(strPassword.getBytes(), "AES");

        AlgorithmParameterSpec paramSpec = new IvParameterSpec(strPassword.getBytes());
        //Whatever you want to encrypt/decrypt using AES /CBC padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //You can use ENCRYPT_MODE or DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        //decode data using standard decoder
        byte[] output = new BASE64Decoder().decodeBuffer(input.toString());

        // Decrypt the data
        byte[] decrypted = cipher.doFinal(output);
        System.out.println("Decrypted string length:-" + decrypted.length);
        log.addLog("Exit main method of Decrypt class");
        return decrypted;
    }

    public static void main(String[] args) {
        try {
            String out = new String(Decrypt.main("GsD/a8znrFHLhyg+A/8RjA=="));
            System.out.println("" + out);
        } catch (Exception ex) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
