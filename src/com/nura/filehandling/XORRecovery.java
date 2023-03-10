/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.filehandling;

/**
 *
 * @author Arun Kumar
 */
/**
 * Overall flow of this program is byte[] b1,b2,b3, b4
 *
 */
public class XORRecovery {

    public static void main() {
    }

    public byte[] recoverPart1(byte[] b2, byte[] b3, byte[] b4,
            byte[] parityByte) {

        int size = 940;

        byte[] tempBytes = new byte[size];
        byte[] altBytes = new byte[size];



        for (int i = 0; i < size; i++) {
            try {
                tempBytes[i] = (byte) (b4[i] ^ parityByte[i]);
            } catch (Exception e) {
            }

        }

        for (int i = 0; i < size; i++) {
            try {
                altBytes[i] = (byte) (b3[i] ^ tempBytes[i]);
            } catch (Exception e) {
            }

        }

        for (int i = 0; i < size; i++) {
            try {
                tempBytes[i] = (byte) (b2[i] ^ altBytes[i]);
            } catch (Exception e) {
            }

        }
        return tempBytes;
    }

    public byte[] recoverPart2(byte[] b1, byte[] b3, byte[] b4,
            byte[] parityByte) {

        int size = parityByte.length;

        byte[] tempBytes = new byte[size];
        byte[] altBytes = new byte[size];

        for (int i = 0; i < size; i++) {
            tempBytes[i] = (byte) (b4[i] ^ parityByte[i]);
        }

        for (int i = 0; i < size; i++) {
            altBytes[i] = (byte) (b3[i] ^ tempBytes[i]);
        }

        for (int i = 0; i < size; i++) {
            tempBytes[i] = (byte) (b1[i] ^ altBytes[i]);
        }

        return tempBytes;
    }

    //Optimized code to overcome too many for loops 
    //if error occurs check here
    public byte[] recoverPart3(byte[] b1, byte[] b2, byte[] b4,
            byte[] parityByte) {

        int size = parityByte.length;

        byte[] tempBytes = new byte[size];
        byte[] altBytes = new byte[size];
        byte[] orgBytes = new byte[size];

        for (int i = 0; i < size; i++) {
            tempBytes[i] = (byte) (b4[i] ^ parityByte[i]);
            altBytes[i] = (byte) (b1[i] ^ b2[i]);
            orgBytes[i] = (byte) (tempBytes[i] ^ altBytes[i]);
        }
        return orgBytes;
    }

    public byte[] recoverpart4(byte[] b1, byte[] b2, byte[] b3,
            byte[] parityByte) {

        int size = parityByte.length;

        byte[] tempBytes = new byte[size];
        byte[] altBytes = new byte[size];
        byte[] orgBytes = new byte[size];

        for (int i = 0; i < size; i++) {
            tempBytes[i] = (byte) (b1[i] ^ b2[i]);
            altBytes[i] = (byte) (tempBytes[i] ^ b3[i]);
            orgBytes[i] = (byte) (altBytes[i] ^ parityByte[i]);
        }

        return orgBytes;
    }
}
