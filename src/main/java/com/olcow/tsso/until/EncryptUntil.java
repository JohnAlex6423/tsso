package com.olcow.tsso.until;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class EncryptUntil {

    /**
     * 利用java原生的类实现SHA256加密
     *
     * @param str 加密后的报文
     * @param enType 加密类型
     * @return 加密结果
     */
    public String Encryption(String enType,String str) {
        String result;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(enType);
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            result = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "error";
        }
        return result;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes 字节组
     * @return 结果
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
