import com.alibaba.fastjson.JSONObject;
import com.monker.common.http.HttpParams;
import com.monker.common.http.HttpResult;
import com.monker.common.http.HttpUtil;
import com.monker.common.http.enums.HttpMethod;
import com.uzhizhe.ninebot.utils.AESCBC;
import com.uzhizhe.ninebot.utils.MD5;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc test
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-19
 */
@Slf4j
public class AESCBCTest {


    @Test
    public void test() throws Exception {
        String url = "http://ninebot.com/users";

        JSONObject data = new JSONObject();
        data.put("id", 100);
        data.put("name", "LIQINGJIANG");
        data.put("status", 1);

        JSONObject inDataJson = new JSONObject();
        inDataJson.put("errorCode", 200);
        inDataJson.put("errorMsg", "SUCCESS");
        inDataJson.put("data", data);

        String inData = inDataJson.toJSONString();
        String seed = "";
        String sign = "";
        String merchantId = "";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("inData", inData);
        paramMap.put("seed", seed);
        paramMap.put("sign", sign);
        paramMap.put("merchantId", merchantId);

        HttpParams httpParams = new HttpParams();
        httpParams.setFormByMap(paramMap);
        HttpResult<String> result = HttpUtil.http(HttpMethod.POST, url, httpParams);
        log.info("Result:{}", result);

    }


    @Test
    public void test56() throws Exception {
        String randomSeed = AESCBC.getRandomStringByLength(16);
        String randomAesKey = AESCBC.getRandomStringByLength(16);

        //inData, seed, aesKey, merchantId

        String merchantId = "ABCDE";
        String seed = randomSeed;
        String aesKey = randomAesKey;
        String merchantKey = "segwayrobotics-scooter-1001";
        String requestParams = "{'id':1001, 'name':'liqingjiang'}";
        String singStr = merchantId + requestParams + seed + "@" + merchantKey;
        String sign = MD5.MD5Encode(singStr);


        String inData = AESCBC.getInstance().encrypt(requestParams, aesKey, seed);

        log.info("inData:{}", inData);

        String decrypt = AESCBC.getInstance().decrypt(inData, aesKey, seed);
        log.info("decrypt:{}", decrypt);


    }


    @Test
    public void test85() throws Exception {
        String merchantId = "10017";
        String merchantKey = "Irxekd7mrVBmrIdjDG5S3dIGFJDh1078";
        String aesKey = "genwd1dVVtxNzcRi";


        String inData = "uOJ5KyEXa5brprsKlN5L7Q==";
        String sign = "48B72774656F23C80832C375DE30E03E";
        String seed = "1ksSjrt9mrPAYGDZ";

        String decrypt = AESCBC.getInstance().decrypt(inData, aesKey, seed);


        System.out.println(decrypt);

        String singStr = merchantId + decrypt + seed + "@" + merchantKey;
        String sign2 = MD5.MD5Encode(singStr);

        System.out.println(sign);
        System.out.println(sign2);

    }
}










