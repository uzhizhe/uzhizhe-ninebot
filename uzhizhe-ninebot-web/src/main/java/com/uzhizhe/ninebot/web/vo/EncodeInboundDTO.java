package com.uzhizhe.ninebot.web.vo;

import com.uzhizhe.ninebot.utils.AESCBC;
import com.uzhizhe.ninebot.utils.MD5;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-10-09
 * @Desc EncodeInboundDTO 加密传输实体
 */
@Data
@Slf4j
public class EncodeInboundDTO {
    @NotNull
    private String inData;
    @NotNull
    private String seed;
    @NotNull
    private String sign;
    @NotNull
    private String merchantId;

    public String getDecodeData(String aesKey, String merchantKey) throws Exception {
        String decodeData;
        try {
            decodeData = AESCBC.getInstance().decrypt(inData, aesKey, seed);
        } catch (Exception e) {
            log.error("Decrypt error, data:{}, aeskey:{}, merchantKey:{}", this, aesKey, merchantKey);
            throw new Exception(e.getMessage());
        }
        String sign = MD5.MD5Encode(merchantId + decodeData + seed + "@" + merchantKey).toUpperCase();
        if (!this.sign.equals(sign)) {
            log.error("Decrypt error, data:{}, aeskey:{}, merchantKey:{}", this, aesKey, merchantKey);
            throw new Exception("Illegal Signature");
        }
        return decodeData;
    }
}
