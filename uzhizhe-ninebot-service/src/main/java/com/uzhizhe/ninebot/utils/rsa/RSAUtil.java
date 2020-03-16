package com.uzhizhe.ninebot.utils.rsa;

import com.alibaba.fastjson.JSONObject;
import com.uzhizhe.ninebot.utils.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
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
public final class RSAUtil {

    // region 属性

    private static final Logger log = LoggerFactory.getLogger(RSAUtil.class);

    // Generate key according to https://gist.github.com/destan/b708d11bd4f403506d6d5bb5fe6a82c5
    // For java, use pkcs8.pem
    /**
     * 本地私钥文件路径
     */
    private static final String PRIVATE_KEY_PATH = "./src/main/resources/rsa/private_key_pkcs8.pem";
    /**
     * 本地公钥文件路径
     */
    private static final String PUBLIC_KEY_PATH = "./src/main/resources/rsa/public_key.pem";

    /**
     * RSA 公钥-私钥 map
     */
    private static final Map<String, String> rsaMap = new HashMap<>(2);

    /**
     * 日志信息
     */
    /**
     * 定义加密方式
     */
    private static final String KEY_RSA = "RSA";

    /**
     * 公钥加密方式
     */
    private static final String KEY_RSA_OR_PKCS1 = "RSA/ECB/PKCS1Padding";
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
    private final static String KEY_RSA_SIGNATURE = "SHA256withRSA";
    /**
     * 定义字符集
     */
    private static final String CHARSET = "UTF-8";
    /**
     * key size
     */
    private static final int KEY_SIZE = 4096;
    // endregion

    // region Base64 编码-解码

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
        return Base64.encodeBase64String(key);
    }
    // endregion

    // region 签名&验签

    /**
     * 用私钥对加密数据进行签名
     *
     * @param encryptedStr 公钥加密后的数据
     * @param privateKey   私钥字符串
     * @return
     */
    public static String sign(String encryptedStr, String privateKey) {
        try {
            // 将私钥加密数据字符串转换为字节数组
            byte[] data = decryptBase64(encryptedStr);
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
            byte[] sign = signature.sign();
            return Base64.encodeBase64String(sign);
        } catch (Exception e) {
            throw new RuntimeException("数据[" + encryptedStr + "]签名异常");
        }
    }

    /**
     * 校验数字签名
     *
     * @param encryptedStr 加密后的数据
     * @param publicKey    公钥字符串
     * @param sign         签名后字符串
     * @return 校验成功返回true，失败返回false
     */
    public static boolean verify(String encryptedStr, String publicKey, String sign) {
        try {
            // 将私钥加密数据字符串转换为字节数组
            byte[] data = decryptBase64(encryptedStr);
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
            return false;
        }
    }
    // endregion

    // region 公钥加密&私钥解密

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
            Cipher cipher = Cipher.getInstance(KEY_RSA_OR_PKCS1);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
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
            Cipher cipher = Cipher.getInstance(KEY_RSA_OR_PKCS1);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(decryptBase64(data)));
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }
    //endregion

    // region 从本地文件获取公私钥

    /**
     * 从文件获取一对公私钥
     *
     * @return
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    private static Map<String, String> loadKeys() throws InvalidKeySpecException, IOException {
        String privateKeyContent = new String(Files.readAllBytes(Paths.get(PRIVATE_KEY_PATH)));
        String publicKeyContent = new String(Files.readAllBytes(Paths.get(PUBLIC_KEY_PATH)));

        privateKeyContent = privateKeyContent.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        publicKeyContent = publicKeyContent.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "");

        Map<String, String> keyPairMap = new HashMap<>(2);
        keyPairMap.put(KEY_RSA_PUBLICKEY, publicKeyContent);
        keyPairMap.put(KEY_RSA_PRIVATEKEY, privateKeyContent);
        log.info("公钥:{}", publicKeyContent);
        log.info("私钥:{}", privateKeyContent);
        return keyPairMap;
    }

    /**
     * 获取私钥
     *
     * @return
     */
    public static String getPrivateKey() {
        String privateKey = rsaMap.get(KEY_RSA_PRIVATEKEY);
        if (StringUtil.isBlank(privateKey)) {
            try {
                Map<String, String> stringStringMap = loadKeys();
                privateKey = stringStringMap.get(KEY_RSA_PRIVATEKEY);
                rsaMap.put(KEY_RSA_PRIVATEKEY, privateKey);
                rsaMap.put(KEY_RSA_PUBLICKEY, stringStringMap.get(KEY_RSA_PUBLICKEY));
            } catch (Exception e) {
                log.info("获取本地公私钥异常:{}", e.getMessage());
            }
        }
        return privateKey;
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

        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = encryptBase64(privateKey.getEncoded());

        Map<String, String> keyPairMap = new HashMap<>(2);
        keyPairMap.put(KEY_RSA_PUBLICKEY, publicKeyStr);
        keyPairMap.put(KEY_RSA_PRIVATEKEY, privateKeyStr);
        return keyPairMap;
    }


    /**
     * 测试RSA
     *
     * @param args
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {

        //verifyMethod();


        allMethod();


    }

    public static void verifyMethod() throws Exception {
        Map<String, String> rsaKeys = loadKeys();
        String privateKey = rsaKeys.get(KEY_RSA_PRIVATEKEY);

        //公钥加密后的数据
        String publicEncrypt = "MHpi0WNux74LDOzRw0rLt3FQNoGZbgmbjG8fqdZMSjm8JCmQaKwvAsDy5ZTxmck8cQhaGbI4AMPlY0h1XqXI3pFf3S3aOYesRocIViEOvw80B+YfR2jfV/9FvBSLKkcfpLoAJ8HCxLinOJTuZ2iyrBy65MKGR0aU75+6ZAOPrblK1MEZMKmpUz5TNVDAnNQBbhmcT56jSrBHOhHVDiIslKGj73P9YtNToqNDG59QvdUCzgZH/ID3Bg+GCeaN2ou3Ywa09e8gV+dZkmUC12tUB94a4IDEjsEFxZO7nemvhIc6wTgBBDv4GKWSlzzA5YhU2xmNeEl6/32A318lmCLhSCjWc4z3EZLKrcsRkudUZugI+soHCFvdKknqUSAysvIE2Ve9DAbUzRMBoLHrYpz0LdPezH5dwEXs+DP6VYl4Bc6wwo65wEyz+ph3TsqOEt4BOjHcfpzazLEOi4G7FJgjM9zbWpNmx5vTDkQitYwd0phgQ9bbghm2mB3pM+81+vTxqmwFlt1FveRjVv5ZKLVitZikMc2Vm/h7daX6xr/SXAL3MI6WxmYxk80s0SVL839XibpoN+saM1z07iyBGO7oMA+9iAszhJUr7LF+xSwinsi0Ow6VQerxohsIylhTtp6CNwnmpS8n6mrZN93dmrclwYspVJdnFh+qRuZaG2K6Sn0=";

        //图灵公钥
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAw4iyg+2dRlbTd3MHPlil0hmgc2Boksu13tJjxOk2Tq7+Y2qJVItenYDdsyHtWsjKmzIXaHNbH5NU5R4H6GEW/wy2XDZrE06Lo4fE8wAFdDpob+8ub7VKihAXe6K0XgmuDgBO/wvm2eiUVoNZAtxXedDHZhXeaI2cnPF7WlFWB/4XIQ9bVu2Ri9I3N8J0nr0nQ3+gOj7LMBMXfRx5fdD53D7cic4NBBj+wmw6VTmXC+nCNIQendgiuGIGgxC54qyhAyFdG2ZFAwbaoXeMoov+mtu7+kB/wIX3EgnPlgtZ9tnH31C/s3YrlK6or1pa78aAxEayGnr5r8roZTsSWVkhPKME3QlpissfHNYINBAXeA8ZxR/K3sSwZa6rcFieiwa4po0jYHSwUjr0PsQWMf/sZladzqtwzbK34N01D30qnrxujI4PpGnmmPyvLufvy2gVvk3aYqc8gWpxfhiHL8hjFqc0XyI/BYcDIsbim1tQ1wH5xe1QC5Fi8JZdNr08aFt/0EH9TjWuAx4+J/yNjnZPW4r9AjTjxjtAtLdbfdrlE9LtISBn8F2oAtcmkWvBPq86yp9UXNEffQKYeedDwhnZo3rTlIgkiaywpHcOui/SN4lh974+InlOKm4Sn3GA1EcaDaY9FYhx3sdF6RrSjFCGuLIVQw66KEZat+kLcFwgEe0CAwEAAQ==";

        //签名结果
        String sign = " tMq75N7vgRoDitsUqVRRhj+8S/pYlYSaoEbqR8RQnQEFIsASMZyjcjokh8HuiGgXbhrTUC5WcxUJuJhd9fuxIrRjFIGq7xaNbDxTX7MP1MqGJvyst6SyE28slu/HcoTIUL1ZogcU3Suey7Smeu/Ed/7g8Oo78ImexLAEmWnt98xuhP2LATLzHP1ZdU0JlEH1ELj35Gu3sEMSljSkQKhFlB3ZVXCG4pf3H76kj8zTCF0oRJ641fE2AvvW7j8nIIgVcCZE0lj1Jshr/dT+011wxHbuTHXQv2mnMoSyWkFThAw+x0sBrS/z0IDj7E5O3Bnsyp5OUXy00mElIEFgdWf1rS3qBvt6C4sgjsKPQtKgXBTiZEulVtNj1zeM7NB3f15J4Q8aNPKAmHeVmTg378YeIBeiTsFrxfm516cwhW35wqSQ906rSOlHTgm79EX5muAFJ5Y6hkGUfBs9D6miUfiynBNuaWKZ/fW6HATar0gLfgdEYVszV6xXYjEawFaVdFGnbfi3Z1cP89pW5zbl4xHZf52zP+5KkXo4ujC85w5Ku5XBVSnTo6iAA5fwvson450ZqzbcQUUb3HZMcFOPf/3vGmybvXqZ475/VJe+z1aIPqzQghQ1SlBjqwuMeT13MKNWsbAv7Ymu/LcYoGpxWsk9+dzF0r7IWvKzJSMRSokYqpI=";

        // 公钥验签
        boolean verify = RSAUtil.verify(publicEncrypt, publicKey, sign);
        log.info("验签结果:{}", verify);

        //私钥解密
        String str = RSAUtil.privateDecrypt(publicEncrypt, privateKey);
        log.info("解密结果:{}", str);
    }

    public static void allMethod() throws Exception {
        Map<String, String> rsaKeys = loadKeys();
        String our__publicKey = rsaKeys.get(KEY_RSA_PUBLICKEY);
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAw4iyg+2dRlbTd3MHPlil0hmgc2Boksu13tJjxOk2Tq7+Y2qJVItenYDdsyHtWsjKmzIXaHNbH5NU5R4H6GEW/wy2XDZrE06Lo4fE8wAFdDpob+8ub7VKihAXe6K0XgmuDgBO/wvm2eiUVoNZAtxXedDHZhXeaI2cnPF7WlFWB/4XIQ9bVu2Ri9I3N8J0nr0nQ3+gOj7LMBMXfRx5fdD53D7cic4NBBj+wmw6VTmXC+nCNIQendgiuGIGgxC54qyhAyFdG2ZFAwbaoXeMoov+mtu7+kB/wIX3EgnPlgtZ9tnH31C/s3YrlK6or1pa78aAxEayGnr5r8roZTsSWVkhPKME3QlpissfHNYINBAXeA8ZxR/K3sSwZa6rcFieiwa4po0jYHSwUjr0PsQWMf/sZladzqtwzbK34N01D30qnrxujI4PpGnmmPyvLufvy2gVvk3aYqc8gWpxfhiHL8hjFqc0XyI/BYcDIsbim1tQ1wH5xe1QC5Fi8JZdNr08aFt/0EH9TjWuAx4+J/yNjnZPW4r9AjTjxjtAtLdbfdrlE9LtISBn8F2oAtcmkWvBPq86yp9UXNEffQKYeedDwhnZo3rTlIgkiaywpHcOui/SN4lh974+InlOKm4Sn3GA1EcaDaY9FYhx3sdF6RrSjFCGuLIVQw66KEZat+kLcFwgEe0CAwEAAQ==";
        String privateKey = rsaKeys.get(KEY_RSA_PRIVATEKEY);

        //自我验证
        publicKey = our__publicKey;

        log.info("our publicKey:{}", our__publicKey);
        log.info("公钥:{}", publicKey);
        log.info("公钥长度736:{}", publicKey.length());
        log.info("私钥:{}", privateKey);
        log.info("私钥长度3168:{}", privateKey.length());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("taskId", "123456abcedf");
        jsonObject.put("commandId", "123321abccbaaaa");
        jsonObject.put("commandType", "CallLift");
        jsonObject.put("liftId", "LIFT-B6-B-1");
        jsonObject.put("robotId", "RobotA-123");
        jsonObject.put("liftFrom", 2);
        jsonObject.put("liftTo", 5);
        jsonObject.put("callbackUrl", "https://liftcenter.com.cn");

        String str = jsonObject.toString();
        log.info("原始数据:{}", str);

        // 公钥加密
        String publicEncrypt = RSAUtil.publicEncrypt(str, publicKey);
        log.info("加密结果: {}", publicEncrypt);

        // 私钥解密
        //String privateDecrypt = RSAUtil.privateDecrypt(publicEncrypt, privateKey);
        //log.info("解密结果:{}", privateDecrypt);

        // 私钥签名
        String sign = RSAUtil.sign(publicEncrypt, privateKey);
        log.info("签名结果:{}", sign);

        // 公钥验签
        boolean verify = RSAUtil.verify(publicEncrypt, publicKey, sign);
        log.info("验签结果:{}", verify);
    }
    //endregion
}
