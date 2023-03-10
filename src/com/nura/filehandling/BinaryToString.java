package com.nura.filehandling;

public class BinaryToString {

    public String getBinaryToString(String s1) throws Exception {
        String cmdStr;
        cmdStr = s1;
        int to;
        String result2 = "";
        for (int i = 0; i < cmdStr.length(); i += 8) {
            if ((i + 8) > cmdStr.length()) {
                to = cmdStr.length();
            } else {
                to = (i + 8);
                String tessst = cmdStr.substring(i, to);
                Long characterObj = Long.valueOf(tessst, 2);
                long character = characterObj.longValue();
                Character c = new Character((char) character);
                result2 += c;
            }
        }
        return result2;
    }
}
