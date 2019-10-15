package com.uzhizhe.ninebot.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理
 * 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public class AESCBC {
    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private final static String encodingFormat = "utf-8";
    private static AESCBC instance = null;

    private AESCBC() {

    }

    public static AESCBC getInstance() {
        if (instance == null) {
            instance = new AESCBC();
        }
        return instance;
    }

    // 加密
    public String encrypt(String data, String aesKey, String seed) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = aesKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(seed.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(data.getBytes(encodingFormat));
        return new String(Base64.encodeBase64(encrypted));
    }

    // 解密
    public String decrypt(String data, String aesKey, String seed) throws Exception {
        try {
            byte[] raw = aesKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(seed.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted = Base64.decodeBase64(data);
            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original, encodingFormat);
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String getRandomStringByLength(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String sign = AESCBC.getInstance().encrypt("d30ca540-3635-41c2-abbc-88ea53009be4", "pur-/qjgto6uLw&e", "QoZOgVp0z64OIBNO");
        System.out.println(sign);

    }

}

