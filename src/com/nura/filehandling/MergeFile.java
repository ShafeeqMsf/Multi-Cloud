/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.filehandling;

import constants.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arun Kumar
 */
public class MergeFile {

    public void mergeBytes(byte[] part1, byte[] part2,
            byte[] part3, byte[] part4, int totLen, String fileName, boolean flag,
            int size1, int size2, int size3, int size4) {

        System.out.println("part 1 lenght :-" + part1.length);
        System.out.println("part 2 length :-" + part2.length);
        System.out.println("part 3 lenght :-" + part3.length);
        System.out.println("part 4 length :-" + part4.length);
        System.out.println("File name:-" + fileName);
        System.out.println("Length:-" + totLen);

        if (flag) {
            ++totLen;
            System.out.println("Inside flag:-" + totLen);
        }
        byte getBytes[] = new byte[totLen];

        int x = 0, y = 0;

        for (int i = 0; i < size1; i++) {

            getBytes[x] = part1[i];
            x++;
        }
        System.out.println("size of x:" + x);

        for (int i = 0; i < size2; i++) {

            getBytes[x] = part2[i];
            x++;
        }
        System.out.println("size of x:" + x);

        for (int i = 0; i < size3; i++) {

            getBytes[x] = part3[i];
            x++;
        }
        System.out.println("size of x:" + x);

        for (int i = 0; i < size4; i++) {

            getBytes[x] = part4[i];
            x++;
        }
        System.out.println("size of x:" + x + " " + getBytes.length);

        File fout = new File(Constants.FILE_LOCATION, fileName);

        try {

            FileOutputStream fos = new FileOutputStream(fout);
            try {
                fos.write(getBytes);
                fos.flush();
                fos.close();
                System.out.println(fout.length());
            } catch (IOException ex) {
                Logger.getLogger(ChunkingFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChunkingFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
