package com.uzhizhe.ninebot.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Desc AESCBCUtil
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-26
 */
@Slf4j
public final class AESCBCUtil {

    private AESCBCUtil() {
    }

    /**
     * 获取指定长度的随机字符串
     *
     * @param length 指定长度
     * @return string
     */
    public static String getRandomSeed(Integer length) {
        return AESCBC.getRandomStringByLength(length);
    }

    /**
     * 获取默认16字节长度的字符串
     *
     * @return string
     */
    public static String getRandomSeed() {
        return getRandomSeed(16);
    }

    /**
     * 数据加密
     *
     * @param inData 数据
     * @param aesKey aesKey
     * @param seed   seed 16位长度字符串
     * @return string
     * @throws Exception exception
     */
    public static String encrypt(String inData, String aesKey, String seed) throws Exception {
        return AESCBC.getInstance().encrypt(inData, aesKey, seed);
    }

    /**
     * 数据解密
     *
     * @param inData 加密后的数据
     * @param aesKey aesKey
     * @param seed   seed
     * @return string
     * @throws Exception exception
     */
    public static String decrypt(String inData, String aesKey, String seed) throws Exception {
        return AESCBC.getInstance().decrypt(inData, aesKey, seed);
    }

    /**
     * 签名
     *
     * @param merchantId
     * @param merchantKey
     * @param seed
     * @param data
     * @return
     */
    public static String sign(String merchantId, String merchantKey, String seed, String data) {
        String singStr = merchantId + data + seed + "@" + merchantKey;
        String sign = MD5.MD5Encode(singStr);
        return sign.toUpperCase();
    }

    /**
     * 验签
     *
     * @param merchantId
     * @param merchantKey
     * @param seed
     * @param data
     * @param sign
     * @return
     */
    public static boolean verifyingSign(String merchantId, String merchantKey, String seed, String data, String sign) {
        try {
            String singResult = sign(merchantId, merchantKey, seed, data);
            return sign.equals(singResult);
        } catch (Exception e) {
            log.info("Verifying sign failed, merchantId:{}, merchantKey:{}, seed:{}, data:{}, sign:{}", merchantId, merchantKey, seed, data, sign);
        }
        return false;
    }

}
