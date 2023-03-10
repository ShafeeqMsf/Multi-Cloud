/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.filehandling;

import com.nura.security.Encrypt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import logger.LoggerUtil;

/**
 *
 * @author Arun Kumar
 */
public class ChunkingFile {

    private final static LoggerUtil log = new LoggerUtil();

    public String[] chunkFileParts(File file) {
        log.addLog("Entry chunkFileParts method of Chunking class");
        String[] chunkedContents = new String[9];
        FileInputStream fis = null;
        
        try {
            File fileToChunk = file;
            fis = new FileInputStream(fileToChunk);
            //File f1=new File();
            int len = (int) fileToChunk.length();
            int size1 = len / 4, size2 = (len / 2) - (len / 4),
                    size3 = ((len * 3) / 4) - (len / 2), size4 = len - ((len * 3) / 4);
            int tempSize1 = 0;

            byte[] getBytes = new byte[len];
            byte[] part1 = new byte[size1];
            byte[] part2 = new byte[size2];
            byte[] part3 = new byte[size3];
            byte[] part4 = new byte[size4];

            //To find greatest size
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(size1);
            list.add(size2);
            list.add(size3);
            list.add(size4);

            Collections.sort(list);

            int greatestSize = list.get(list.size() - 1);//For xor operation

            byte[] xorByte = new byte[greatestSize];

            try {

                fis.read(getBytes);
                log.addLog("" + getBytes.length);

                for (int i = 0; i < size1; i++) {

                    part1[i] = getBytes[tempSize1++];

                }
                //fis.read(part1, 0,size1);
                log.addLog("part1 length:->" + part1.length);
                chunkedContents[5] = ""+part1.length;
                for (int i = 0; i < size2; i++) {

                    part2[i] = getBytes[tempSize1++];

                }
                log.addLog("part2 length:->" + part2.length);
                chunkedContents[6] = ""+part2.length;
                
                for (int i = 0; i < size3; i++) {

                    part3[i] = getBytes[tempSize1++];

                }
                log.addLog("part3 length:->" + part3.length);
                chunkedContents[7] = ""+part3.length;

                for (int i = 0; i < size4; i++) {

                    part4[i] = getBytes[tempSize1++];

                }
                log.addLog("part4 length:->" + part4.length);
                chunkedContents[8] = ""+part4.length;
                
                //Perform the xor operation for recovery purpose calling recovery package
                xorByte = XOR.getXOR(part1, part2, part3, part4);

                try {
                    //Encrypt the bytes
                    String encPart1 = Encrypt.main(part1);
                    log.addLog("Encrypted part1:-"+encPart1);
                    String encPart2 = Encrypt.main(part2);
                    log.addLog("Encrypted part2:-"+encPart2);
                    String encPart3 = Encrypt.main(part3);
                    log.addLog("Encrypted part3:-"+encPart3);
                    String encPart4 = Encrypt.main(part4);
                    log.addLog("Encrypted part4:-"+encPart4);
                    String xorString = Encrypt.main(xorByte);
                    log.addLog("XOR =>"+xorString);
                    //Converting String to Binary
                    String c1 = "0000$"+com.nura.filehandling.StringToBinary.main(encPart1);
                    String c2 = "1111$"+com.nura.filehandling.StringToBinary.main(encPart2);
                    String c3 = "0000$"+com.nura.filehandling.StringToBinary.main(encPart3);
                    String c4 = "1111$"+com.nura.filehandling.StringToBinary.main(encPart4);
                    //Performing the xor operation
                    String xorBinary = com.nura.filehandling.StringToBinary.main(xorString);
                    //Returning the cipher stream
                    chunkedContents[0] = c1;
                    chunkedContents[1] = c2;
                    chunkedContents[2] = c3;
                    chunkedContents[3] = c4;
                    chunkedContents[4] = xorBinary;//xor chunk

                } catch (Exception ex) {
                    log.addLog(ex.getMessage());
                    Logger.getLogger(ChunkingFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                log.addLog(ex.getMessage());
                Logger.getLogger(ChunkingFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            log.addLog(ex.getMessage());
            Logger.getLogger(ChunkingFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                log.addLog(ex.getMessage());
                Logger.getLogger(ChunkingFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        log.addLog("Exit chunkFileParts method of Chunking class");
        return chunkedContents;
    }
}
