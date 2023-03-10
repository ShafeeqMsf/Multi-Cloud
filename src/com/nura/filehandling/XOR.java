/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nura.filehandling;

import com.nura.security.Decrypt;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Arun kumar
 */
public class XOR {

    public static byte[] getXOR(byte[] p1, byte[] p2, byte[] p3, byte[] p4) {

        byte[] xorByte = null;
        byte[] tempXor = null;

        ArrayList list = new ArrayList<Integer>();
        list.add(p1.length);
        list.add(p2.length);
        list.add(p3.length);
        list.add(p4.length);

        Collections.sort(list);
        System.out.println(list);

        int size = (Integer) list.get(list.size() - 1);
        System.out.println(size);

        xorByte = new byte[size];
        tempXor = new byte[size];

        xorByte = XOR.performXOR(p1, p2);
        tempXor = XOR.performXOR(xorByte, p3);
        xorByte = XOR.performXOR(tempXor, p4);

        return xorByte;
    }

    public static byte[] recovery(byte[] p1, byte[] p2, byte[] p3, byte[] xorByte, int flag) {
        byte[] tempByte = new byte[xorByte.length];
        byte[] recover = new byte[xorByte.length];

        switch (flag) {
            case 1://For recovering block1
                recover = XOR.performXOR(xorByte, p3);
                tempByte = XOR.performXOR(recover, p2);
                recover = XOR.performXOR(tempByte, p1);
                break;
            case 2://For recovering block2
                recover = XOR.performXOR(xorByte, p3);
                tempByte = XOR.performXOR(recover, p2);
                recover = XOR.performXOR(tempByte, p1);
                break;
            case 3://For recovering block3
                recover = XOR.performXOR(p1, p2);
                tempByte = XOR.performXOR(p3, xorByte);
                recover = XOR.performXOR(tempByte, recover);
                break;
            case 4://For recovering block4
                recover = XOR.performXOR(p1, p2);
                tempByte = XOR.performXOR(recover, p3);
                recover = XOR.performXOR(tempByte, xorByte);
                break;
        }
        return recover;
    }

    public static byte[] performXOR(byte[] p1, byte[] p2) {
        byte[] tempByte = null;
        int size1 = 0, size2 = 0;

        size1 = p1.length;
        size2 = p2.length;

        if (size1 < size2) {
            tempByte = new byte[size2];
            for (int i = 0; i < size1; i++) {
                tempByte[i] = (byte) (p1[i] ^ p2[i]);
            }
            for (int i = size1; i < size2; i++) {
                tempByte[i] = (byte) (p2[i]);
            }
        } else if (size2 < size1) {
            tempByte = new byte[size1];
            for (int i = 0; i < size2; i++) {
                tempByte[i] = (byte) (p1[i] ^ p2[i]);
            }
            for (int i = size2; i < size1; i++) {
                tempByte[i] = (byte) (p1[i]);
            }
        } else if (size1 == size2) {
            tempByte = new byte[size1];
            for (int i = 0; i < size1; i++) {
                tempByte[i] = (byte) (p1[i] ^ p2[i]);
            }
        }

        return tempByte;
    }
}
