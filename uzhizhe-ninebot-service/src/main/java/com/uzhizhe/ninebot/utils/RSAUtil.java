package com.uzhizhe.ninebot.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2020-01-19
 * @Desc RSAUtil RSA
 */
@Slf4j
public class RSAUtil {

    //region 属性
    /**
     * 定义加密方式
     */
    private static final String KEY_RSA = "RSA";
    /**
     * 定义公钥关键词
     */
    private static final String KEY_RSA_PUBLICKEY = "RSAPublicKey";
    /**
     * 定义私钥关键词
     */
    private static final String KEY_RSA_PRIVATEKEY = "RSAPrivateKey";
    /**
     * 定义签名算法
     */
    private final static String KEY_RSA_SIGNATURE = "MD5withRSA";
    /**
     * 定义字符集
     */
    private static final String CHARSET = "UTF-8";
    /**
     * key size
     */
    private static final int KEY_SIZE = 1024;
    //endregion

    //region Base64 编码-解码

    /**
     * BASE64 解码
     *
     * @param key 需要Base64解码的字符串
     * @return 字节数组
     */
    private static byte[] decryptBase64(String key) {
        return Base64.decodeBase64(key);
    }

    /**
     * BASE64 编码
     *
     * @param key 需要Base64编码的字节数组
     * @return 字符串
     */
    private static String encryptBase64(byte[] key) {
        return Base64.encodeBase64URLSafeString(key);
    }
    //endregion

    //region 签名&验签

    /**
     * 用私钥对加密数据进行签名
     *
     * @param encryptedStr 公钥加密后的数据
     * @param privateKey   私钥字符串
     * @return
     */
    public static String sign(String encryptedStr, String privateKey) {
        try {
            //将私钥加密数据字符串转换为字节数组
            byte[] data = encryptedStr.getBytes();
            // 解密由base64编码的私钥
            byte[] bytes = decryptBase64(privateKey);
            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);
            // 指定的加密算法
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            // 取私钥对象
            PrivateKey key = factory.generatePrivate(pkcs);
            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
            signature.initSign(key);
            signature.update(data);
            return encryptBase64(signature.sign());
        } catch (Exception e) {
            throw new RuntimeException("数据[" + encryptedStr + "]签名异常");
        }
    }

    /**
     * 校验数字签名
     *
     * @param encryptedStr 原始数据
     * @param publicKey    公钥字符串
     * @param sign         签名后字符串
     * @return 校验成功返回true，失败返回false
     */
    public static boolean verify(String encryptedStr, String publicKey, String sign) {
        try {
            //将私钥加密数据字符串转换为字节数组
            byte[] data = encryptedStr.getBytes();
            // 解密由base64编码的公钥
            byte[] bytes = decryptBase64(publicKey);
            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            // 指定的加密算法
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            // 取公钥对象
            PublicKey key = factory.generatePublic(keySpec);
            // 用公钥验证数字签名
            Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
            signature.initVerify(key);
            signature.update(data);
            return signature.verify(decryptBase64(sign));
        } catch (Exception e) {
            log.info("验签异常, 原始数据:{}, 公钥:{}, 签名:{}", encryptedStr, publicKey, sign);
            return false;
        }
    }
    //endregion

    //region 公钥加密&私钥解密

    /**
     * 公钥加密
     *
     * @param data         原始数据
     * @param publicKeyStr 公钥字符串
     * @return 加密结果
     */
    public static String publicEncrypt(String data, String publicKeyStr) {
        try {
            // 将公钥由字符串转为UTF-8格式的字节数组
            byte[] publicKeyBytes = decryptBase64(publicKeyStr);
            // 获得公钥
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            RSAPublicKey publicKey = (RSAPublicKey) factory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(KEY_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return encryptBase64(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }


    /**
     * 私钥解密
     *
     * @param data          公钥加密后的密文数据
     * @param privateKeyStr 私钥字符串
     * @return 解密结果
     */
    public static String privateDecrypt(String data, String privateKeyStr) {
        try {
            // 对私钥解密
            byte[] privateKeyBytes = decryptBase64(privateKeyStr);
            // 获得私钥
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            RSAPrivateKey privateKey = (RSAPrivateKey) factory.generatePrivate(keySpec);
//            RSAPublicKey publicKey = (RSAPublicKey)factory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(KEY_RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, decryptBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }
    //endregion

    //region 私有方法
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        return out.toByteArray();
    }
    //endregion

    //region 测试用例

    /**
     * 获取一对公私钥
     *
     * @return
     */
    private static Map<String, String> createKeys() {
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(KEY_RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + KEY_RSA + "]");
        }

        //初始化KeyPairGenerator对象,不要被initialize()源码表面上欺骗,其实这里声明的size是生效的
        kpg.initialize(KEY_SIZE);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = encryptBase64(publicKey.getEncoded());
        log.info("Public key GetEncoded:{}", publicKey.getEncoded());

        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = encryptBase64(privateKey.getEncoded());
        log.info("Private key GetEncoded:{}", privateKey.getEncoded());

        Map<String, String> keyPairMap = new HashMap<>(2);
        keyPairMap.put(KEY_RSA_PUBLICKEY, publicKeyStr);
        keyPairMap.put(KEY_RSA_PRIVATEKEY, privateKeyStr);
        return keyPairMap;
    }

    /**
     * 测试RSA
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, String> rsaKeys = createKeys();
        String publicKey = rsaKeys.get(KEY_RSA_PUBLICKEY);
        String privateKey = rsaKeys.get(KEY_RSA_PRIVATEKEY);

        log.info("PublicKey:{}", publicKey);
        log.info("PublicKey长度:{}", publicKey.length());
        log.info("PrivateKey:{}", privateKey);
        log.info("PrivateKey长度:{}", privateKey.length());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("taskId", "123456abcedf");
        jsonObject.put("commandId", "123321abccbaaaa");
        jsonObject.put("commandType", "CallLift");
        jsonObject.put("liftId", "LIFT-B6_B-1");
        jsonObject.put("robotId", "RobotA-123");
        jsonObject.put("liftFrom", 2);
        jsonObject.put("liftTo", 5);
        jsonObject.put("callbackUrl", "https://liftcenter.com.cn");

        String str = jsonObject.toJSONString();

        log.info("加密字符串是:{}", str);
        log.info("加密字符串长度:{}", str.length());

        //公钥加密
        String publicEncrypt = RSAUtil.publicEncrypt(str, publicKey);
        log.info("加密结果:{}", publicEncrypt);
        log.info("加密结果长度:{}", publicEncrypt.length());

        //私钥解密
        String privateDecrypt = RSAUtil.privateDecrypt(publicEncrypt, privateKey);
        log.info("解密结果:{}", privateDecrypt);

        //私钥签名
        String sign = RSAUtil.sign(publicEncrypt, privateKey);
        log.info("签名结果:{}", sign);
        log.info("签名结果长度:{}", sign.length());

        //公钥验签
        boolean verify = RSAUtil.verify(publicEncrypt, publicKey, sign);
        log.info("验签结果:{}", verify);

    }
    //endregion


}
