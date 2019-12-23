package com.luxoft.analyzer.util;

public interface StringUtils {
    static String replace(String[] values){
        byte[] temp = values[0].getBytes();
        byte[] formatted = new byte[]{temp[3], temp[4], temp[5], temp[6]};
        return new String(formatted).intern();
    }
}
