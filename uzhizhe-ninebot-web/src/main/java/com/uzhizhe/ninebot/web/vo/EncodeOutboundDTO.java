package com.uzhizhe.ninebot.web.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.uzhizhe.ninebot.utils.AESCBC;
import com.uzhizhe.ninebot.utils.MD5;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-10-09
 * @Desc EncodeOutboundDTO 加密传输实体
 */
@Slf4j
@Getter
@AllArgsConstructor
public class EncodeOutboundDTO {
    private String inData;
    private String seed;
    private String sign;
    private String merchantId;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Object data;
        private String merchantId;
        private String merchantKey;
        private String aesKey;

        private Builder() {
        }

        public EncodeOutboundDTO build() {
            String seed = AESCBC.getRandomStringByLength(16);
            String mapSortJson = JSON.toJSONString(data, SerializerFeature.MapSortField);
            String inData = null;
            try {
                inData = AESCBC.getInstance().encrypt(mapSortJson, aesKey, seed);
            } catch (Exception e) {
                EncodeOutboundDTO.log.error("数据解析出错: {}", mapSortJson, e);
            }
            String sign = MD5.MD5Encode(merchantId + mapSortJson + seed + "@" + merchantKey).toUpperCase();
            return new EncodeOutboundDTO(inData, seed, sign, merchantId);
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public Builder setMerchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder setMerchantKey(String merchantKey) {
            this.merchantKey = merchantKey;
            return this;
        }

        public Builder setAesKey(String aesKey) {
            this.aesKey = aesKey;
            return this;
        }
    }
}
