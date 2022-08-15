package com.olcow.tsso.utils;

public class StringUtils {

    /**
     * 字符串判空
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        if (cs != null) {
            int length = cs.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
