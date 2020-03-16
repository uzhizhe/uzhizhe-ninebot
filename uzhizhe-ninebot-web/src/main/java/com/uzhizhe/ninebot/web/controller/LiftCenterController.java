package com.uzhizhe.ninebot.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.monker.common.http.HttpParams;
import com.monker.common.http.HttpResult;
import com.monker.common.http.HttpUtil;
import com.monker.common.http.enums.HttpMethod;
import com.monker.common.result.ResponseDto;
import com.uzhizhe.ninebot.utils.rsa.RSAUtil;
import com.uzhizhe.ninebot.web.vo.RobotLiftCallbackRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2020-02-14
 */
@Slf4j
@RestController
public class LiftCenterController {


    @PostMapping("/uzhizhe/robot/liftcenter")
    @CrossOrigin("*")
    public Object callback(@RequestBody RobotLiftCallbackRequestVo requestVo) {
        String self_publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAqhjrcJJDcaLdKVniEg6np9LJ9oMfWKn5mWT7zAtuVFCaujgoJ8ZxssxZxk2aZtFdX3AlWX/VSKE8ODcFPvF7hjbvaVfz478e3OUOLpiQg00HArMsOOLN5Ld2AAELofLp1i+Iiviq5ZykhFU6PILS+n6hlq5OIu93YJ+7T9/ZlskU5jWIMpljVC10hABbWxsXT5F12G5AfUgpQTrUUmnqJgYuZ9ZXV09CG2IY1rlH8i+Pc3XHXfOpOrHMyQ3iUEZKQ1GVezMNUp1uCF0IiIM40x3hmQvB9CLoG/l42uEBSGkdihOVncLyM8BoIH7KbwZP2MCzQvq3WrsFSR7MLdC/TSEIDGeafK9h4uQSdJSX+yoKe+UcFsMmfw+mXGMRW0HweLwubH3d2axkZiEyK/LL5qUTvDLd/L+ncQHLyv7x/ZpfOw2iHK9WUNBb2NYzyP3IDFt9OLA7jVO0gpvTjCaLvaE0V0U9xo9JY7Xp0cm3kDBYww28BayJO/kb1l9h6rgVPh67gugWMc1dRYNV/hSaAg63qVhjLLpGxtNoYkpfjmVy/60iP90LvzsVmnw5bwZUiIqEDDRj7BI0HbHJrYC7G+Wk+1B7EbiqEkdmxWnR+0xTD3D5sISqLCu/t+4Ks40TPflMmby6ch77D5/Pnt9iMQphaZ0ggRhoZDDVV9yl/z8CAwEAAQ==";
        String self_privateKey = "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";
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA6lhYGwDjwOhjjaOujFZLxQzfNDIJANIpcxLC8QEMAYfsSwxczH3fRKJj5S6OPdx+4MI00utQR1Sd6AQrTsdN54b4iOKPcnGxEvPTPJKIsp1wKj7wqfzT5GDC8NJCz/qaOa5fwT517z4CggVZNdXpz6Nvt+Mz2BqjOZm/9P0/JA2/Lr8DoyoJJoZSmmLJHnUlfx02vVqmjirZd5o+JNnLlLJo34CA0MmN69VWMp7PU07uolD0mTmOxRCGhQmuaZUHBq1E/i3ci5X89tfhF22IN/AJfTB0QknjOQhZVzXldG14sFV094cdkJCchYGPCP3sM1g5xy6JOigOokO7wyHIYTP57Hz3S3fdjIaFDUK7N5YAo9rwwrX9LFvy6KIm9T6X+QaiyS3BTEBonuea3lczValrHllw+XuRWDx8Pklpl9U2NdkE7DtKer/gy9eFoUgfIv1mqTbWAn0Nf96zHNhBpSXaOZkL3BnYJNeCV0tqHvdqMnG0booI321e/CGsL+KQQEVk9ILQGcwyIWGoBrxwVJhTu2nmDt95hXFY0ltXlG6tR8PNhQcePrCdoeCsxlrLftpf2iDLJhEG4cNIwQDXsV5ZMZzxkzVc1YaY03UwcgC+q/ou/xlbU9yjt/LIfyMsfReM7X9LuH/62+JlPY6fXK8CsNu1z+7BKHyTjRxvY7UCAwEAAQ==";

        log.info("RequestVo:{}", requestVo);
        String data = requestVo.getData();
        String sign = requestVo.getSign();
        String merchantId = requestVo.getMerchantId();
        log.info("data:{}", data);
        log.info("sign:{}", sign);
        log.info("merchantId:{}", merchantId);

        boolean verify = RSAUtil.verify(data, publicKey, sign);
        log.info("callback verify:{}", verify);

        String dataStr = RSAUtil.privateDecrypt(data, self_privateKey);
        log.info("dataStr:{}", dataStr);

        return ResponseDto.success();
    }

    @GetMapping("/uzhizhe/liftcenter/command")
    @CrossOrigin("*")
    public Object liftcenter(@RequestParam String commandType) {
        log.info("commandType:{}", commandType);
        String self_publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAqhjrcJJDcaLdKVniEg6np9LJ9oMfWKn5mWT7zAtuVFCaujgoJ8ZxssxZxk2aZtFdX3AlWX/VSKE8ODcFPvF7hjbvaVfz478e3OUOLpiQg00HArMsOOLN5Ld2AAELofLp1i+Iiviq5ZykhFU6PILS+n6hlq5OIu93YJ+7T9/ZlskU5jWIMpljVC10hABbWxsXT5F12G5AfUgpQTrUUmnqJgYuZ9ZXV09CG2IY1rlH8i+Pc3XHXfOpOrHMyQ3iUEZKQ1GVezMNUp1uCF0IiIM40x3hmQvB9CLoG/l42uEBSGkdihOVncLyM8BoIH7KbwZP2MCzQvq3WrsFSR7MLdC/TSEIDGeafK9h4uQSdJSX+yoKe+UcFsMmfw+mXGMRW0HweLwubH3d2axkZiEyK/LL5qUTvDLd/L+ncQHLyv7x/ZpfOw2iHK9WUNBb2NYzyP3IDFt9OLA7jVO0gpvTjCaLvaE0V0U9xo9JY7Xp0cm3kDBYww28BayJO/kb1l9h6rgVPh67gugWMc1dRYNV/hSaAg63qVhjLLpGxtNoYkpfjmVy/60iP90LvzsVmnw5bwZUiIqEDDRj7BI0HbHJrYC7G+Wk+1B7EbiqEkdmxWnR+0xTD3D5sISqLCu/t+4Ks40TPflMmby6ch77D5/Pnt9iMQphaZ0ggRhoZDDVV9yl/z8CAwEAAQ==";
        String self_privateKey = "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";
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA6lhYGwDjwOhjjaOujFZLxQzfNDIJANIpcxLC8QEMAYfsSwxczH3fRKJj5S6OPdx+4MI00utQR1Sd6AQrTsdN54b4iOKPcnGxEvPTPJKIsp1wKj7wqfzT5GDC8NJCz/qaOa5fwT517z4CggVZNdXpz6Nvt+Mz2BqjOZm/9P0/JA2/Lr8DoyoJJoZSmmLJHnUlfx02vVqmjirZd5o+JNnLlLJo34CA0MmN69VWMp7PU07uolD0mTmOxRCGhQmuaZUHBq1E/i3ci5X89tfhF22IN/AJfTB0QknjOQhZVzXldG14sFV094cdkJCchYGPCP3sM1g5xy6JOigOokO7wyHIYTP57Hz3S3fdjIaFDUK7N5YAo9rwwrX9LFvy6KIm9T6X+QaiyS3BTEBonuea3lczValrHllw+XuRWDx8Pklpl9U2NdkE7DtKer/gy9eFoUgfIv1mqTbWAn0Nf96zHNhBpSXaOZkL3BnYJNeCV0tqHvdqMnG0booI321e/CGsL+KQQEVk9ILQGcwyIWGoBrxwVJhTu2nmDt95hXFY0ltXlG6tR8PNhQcePrCdoeCsxlrLftpf2iDLJhEG4cNIwQDXsV5ZMZzxkzVc1YaY03UwcgC+q/ou/xlbU9yjt/LIfyMsfReM7X9LuH/62+JlPY6fXK8CsNu1z+7BKHyTjRxvY7UCAwEAAQ==";
        String merchantId = "80888";

        String robotId = "EVT6-6-6-6";
        String liftId = "BIZ-LIFT_Dingdong_Test";
        String callbackUrl = "http://localhost:8001/uzhizhe/robot/liftcenter";


        if (commandType.equalsIgnoreCase("CallLift")) {
            String taskId = System.currentTimeMillis() + "";
            String commandId = System.nanoTime() + "";

            int liftFrom = 2;
            int liftTo = 5;


            JSONObject json = new JSONObject();
            json.put("taskId", taskId);
            json.put("commandId", commandId);
            json.put("commandType", commandType);
            json.put("robotId", robotId);
            json.put("liftId", liftId);
            json.put("liftTo", liftTo);
            json.put("liftFrom", liftFrom);
            json.put("callbackUrl", callbackUrl);
            String data = json.toJSONString();

            String publicEncrypt = RSAUtil.publicEncrypt(data, publicKey);
            String sign = RSAUtil.sign(publicEncrypt, self_privateKey);

            JSONObject requestData = new JSONObject();
            requestData.put("merchantId", merchantId);
            requestData.put("data", publicEncrypt);
            requestData.put("sign", sign);

            HttpParams httpParams = new HttpParams();
            httpParams.setJson(requestData.toJSONString());
            String url = "http://localhost:9012/liftcenter/command";
            HttpResult<String> http = HttpUtil.http(HttpMethod.POST, url, httpParams);

            outReturnData(http);

        } else if (commandType.equalsIgnoreCase("RobotInLift")) {


            String taskId = System.currentTimeMillis() + "";
            String commandId = System.nanoTime() + "";
            String liftTripId = "123456789";

            JSONObject json = new JSONObject();
            json.put("taskId", taskId);
            json.put("commandId", commandId);
            json.put("commandType", commandType);
            json.put("robotId", robotId);
            json.put("liftTripId", liftTripId);
            String data = json.toJSONString();

            String publicEncrypt = RSAUtil.publicEncrypt(data, publicKey);
            String sign = RSAUtil.sign(publicEncrypt, self_privateKey);

            JSONObject requestData = new JSONObject();
            requestData.put("merchantId", merchantId);
            requestData.put("data", publicEncrypt);
            requestData.put("sign", sign);

            HttpParams httpParams = new HttpParams();
            httpParams.setJson(requestData.toJSONString());
            String url = "http://localhost:9012/liftcenter/command";
            HttpResult<String> http = HttpUtil.http(HttpMethod.POST, url, httpParams);

            outReturnData(http);


        } else if (commandType.equalsIgnoreCase("RobotOutLift")) {
            String taskId = System.currentTimeMillis() + "";
            String commandId = System.nanoTime() + "";
            String liftTripId = "123456789";

            JSONObject json = new JSONObject();
            json.put("taskId", taskId);
            json.put("commandId", commandId);
            json.put("commandType", commandType);
            json.put("robotId", robotId);
            json.put("liftTripId", liftTripId);
            String data = json.toJSONString();

            String publicEncrypt = RSAUtil.publicEncrypt(data, publicKey);
            String sign = RSAUtil.sign(publicEncrypt, self_privateKey);

            JSONObject requestData = new JSONObject();
            requestData.put("merchantId", merchantId);
            requestData.put("data", publicEncrypt);
            requestData.put("sign", sign);

            HttpParams httpParams = new HttpParams();
            httpParams.setJson(requestData.toJSONString());
            String url = "http://localhost:9012/liftcenter/command";
            HttpResult<String> http = HttpUtil.http(HttpMethod.POST, url, httpParams);

            outReturnData(http);
        } else if (commandType.equalsIgnoreCase("ReCallLift")) {
            String taskId = System.currentTimeMillis() + "";
            String commandId = System.nanoTime() + "";
            String liftTripId = "123456789";

            JSONObject json = new JSONObject();
            json.put("taskId", taskId);
            json.put("commandId", commandId);
            json.put("commandType", commandType);
            json.put("robotId", robotId);
            json.put("liftTripId", liftTripId);
            String data = json.toJSONString();

            String publicEncrypt = RSAUtil.publicEncrypt(data, publicKey);
            String sign = RSAUtil.sign(publicEncrypt, self_privateKey);

            JSONObject requestData = new JSONObject();
            requestData.put("merchantId", merchantId);
            requestData.put("data", publicEncrypt);
            requestData.put("sign", sign);

            HttpParams httpParams = new HttpParams();
            httpParams.setJson(requestData.toJSONString());
            String url = "http://localhost:9012/liftcenter/command";
            HttpResult<String> http = HttpUtil.http(HttpMethod.POST, url, httpParams);

            outReturnData(http);
        }

        return ResponseDto.success();
    }

    public void outReturnData(HttpResult<String> http) {
        String self_publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAqhjrcJJDcaLdKVniEg6np9LJ9oMfWKn5mWT7zAtuVFCaujgoJ8ZxssxZxk2aZtFdX3AlWX/VSKE8ODcFPvF7hjbvaVfz478e3OUOLpiQg00HArMsOOLN5Ld2AAELofLp1i+Iiviq5ZykhFU6PILS+n6hlq5OIu93YJ+7T9/ZlskU5jWIMpljVC10hABbWxsXT5F12G5AfUgpQTrUUmnqJgYuZ9ZXV09CG2IY1rlH8i+Pc3XHXfOpOrHMyQ3iUEZKQ1GVezMNUp1uCF0IiIM40x3hmQvB9CLoG/l42uEBSGkdihOVncLyM8BoIH7KbwZP2MCzQvq3WrsFSR7MLdC/TSEIDGeafK9h4uQSdJSX+yoKe+UcFsMmfw+mXGMRW0HweLwubH3d2axkZiEyK/LL5qUTvDLd/L+ncQHLyv7x/ZpfOw2iHK9WUNBb2NYzyP3IDFt9OLA7jVO0gpvTjCaLvaE0V0U9xo9JY7Xp0cm3kDBYww28BayJO/kb1l9h6rgVPh67gugWMc1dRYNV/hSaAg63qVhjLLpGxtNoYkpfjmVy/60iP90LvzsVmnw5bwZUiIqEDDRj7BI0HbHJrYC7G+Wk+1B7EbiqEkdmxWnR+0xTD3D5sISqLCu/t+4Ks40TPflMmby6ch77D5/Pnt9iMQphaZ0ggRhoZDDVV9yl/z8CAwEAAQ==";
        String self_privateKey = "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";
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA6lhYGwDjwOhjjaOujFZLxQzfNDIJANIpcxLC8QEMAYfsSwxczH3fRKJj5S6OPdx+4MI00utQR1Sd6AQrTsdN54b4iOKPcnGxEvPTPJKIsp1wKj7wqfzT5GDC8NJCz/qaOa5fwT517z4CggVZNdXpz6Nvt+Mz2BqjOZm/9P0/JA2/Lr8DoyoJJoZSmmLJHnUlfx02vVqmjirZd5o+JNnLlLJo34CA0MmN69VWMp7PU07uolD0mTmOxRCGhQmuaZUHBq1E/i3ci5X89tfhF22IN/AJfTB0QknjOQhZVzXldG14sFV094cdkJCchYGPCP3sM1g5xy6JOigOokO7wyHIYTP57Hz3S3fdjIaFDUK7N5YAo9rwwrX9LFvy6KIm9T6X+QaiyS3BTEBonuea3lczValrHllw+XuRWDx8Pklpl9U2NdkE7DtKer/gy9eFoUgfIv1mqTbWAn0Nf96zHNhBpSXaOZkL3BnYJNeCV0tqHvdqMnG0booI321e/CGsL+KQQEVk9ILQGcwyIWGoBrxwVJhTu2nmDt95hXFY0ltXlG6tR8PNhQcePrCdoeCsxlrLftpf2iDLJhEG4cNIwQDXsV5ZMZzxkzVc1YaY03UwcgC+q/ou/xlbU9yjt/LIfyMsfReM7X9LuH/62+JlPY6fXK8CsNu1z+7BKHyTjRxvY7UCAwEAAQ==";
        String merchantId = "80888";
        String httpData = http.getData();
        Integer code = http.getCode();
        String message = http.getMessage();
        log.info("HTTP:{}", http);
        log.info("httpData:{}", httpData);
        log.info("code:{}", code);
        log.info("message:{}", message);

        JSONObject jsonObject = JSONObject.parseObject(httpData);
        String data = jsonObject.getString("data");
        String sign = jsonObject.getString("sign");

        log.info("data:{}", data);
        log.info("sign:{}", sign);

        boolean verify = RSAUtil.verify(data, publicKey, sign);
        log.info("verify:{}", verify);

        String dataStr = RSAUtil.privateDecrypt(data, self_privateKey);
        log.info("dataStr:{}", dataStr);
    }

    public static void main(String[] args) {
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA6lhYGwDjwOhjjaOujFZLxQzfNDIJANIpcxLC8QEMAYfsSwxczH3fRKJj5S6OPdx+4MI00utQR1Sd6AQrTsdN54b4iOKPcnGxEvPTPJKIsp1wKj7wqfzT5GDC8NJCz/qaOa5fwT517z4CggVZNdXpz6Nvt+Mz2BqjOZm/9P0/JA2/Lr8DoyoJJoZSmmLJHnUlfx02vVqmjirZd5o+JNnLlLJo34CA0MmN69VWMp7PU07uolD0mTmOxRCGhQmuaZUHBq1E/i3ci5X89tfhF22IN/AJfTB0QknjOQhZVzXldG14sFV094cdkJCchYGPCP3sM1g5xy6JOigOokO7wyHIYTP57Hz3S3fdjIaFDUK7N5YAo9rwwrX9LFvy6KIm9T6X+QaiyS3BTEBonuea3lczValrHllw+XuRWDx8Pklpl9U2NdkE7DtKer/gy9eFoUgfIv1mqTbWAn0Nf96zHNhBpSXaOZkL3BnYJNeCV0tqHvdqMnG0booI321e/CGsL+KQQEVk9ILQGcwyIWGoBrxwVJhTu2nmDt95hXFY0ltXlG6tR8PNhQcePrCdoeCsxlrLftpf2iDLJhEG4cNIwQDXsV5ZMZzxkzVc1YaY03UwcgC+q/ou/xlbU9yjt/LIfyMsfReM7X9LuH/62+JlPY6fXK8CsNu1z+7BKHyTjRxvY7UCAwEAAQ==";
        String privateKey = "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";

        String data = "VzIBmvxJw2vKvV4cSpbZX0CBhRDzctZVIehumoEe6FashOBzs0onH6dEJSPDGBjPxDgfbqeM8VcO3OFA6CPdXnvVwye0qBsb8INZZENLnue7QeQR6Fx47/TBYvhuSNAzJd4Da8wMhKckGUL94d/78xLh3sOAhtgM8A22YVVddmTSvPeor5prdwEzzK4NRKg+SOkDa5Khjk/OGr8/qTtJiW1CNjeIkYdvReiMKTlwIOnJC+ugIcBm7YOrmrRksJ0BjhDJye/DOuPTLfG+Ib2wibYs6MTxT9iWDiw70B6YAXX6pOeX2Rx25Dxpbu+aeLmLbb8hLoX0lsVL8xQIW+rIouLPjIG8PwsYv1O+fD0CrSLn+8LTHR0qG/oiWDujZht2zogtIEST2Iz2FXJyCKchpLG+qxLC0BWDRqVwrWxpB9WjL1wSfDnA7hWjpgLqcELjazppyqtLtZ+UjXUpJfos4v8BY7rOC5/P3u8ynuE3bUgwnjJtCUPvJWeO5nEFNILgkrMr8Hjy591H6F8pUDHzf29yduCNlBT41jMXqgKNNV6rOnKvX43yPFGK0RzojL3x5E1LsF1tU5ZdjqfHqdFUbYvzYq0D3GYbTAnYMaE8qtxZVr70nAznqUHn/85E9rCyS5cACTkkdKjiO4QSUkqTGJUvNpO894sx8f7yP2qptPM=";
        String sign = "joR20mmWBrISmFEeIfpRDDhrucs7q/m3z/YOm/oyVzC3iEnPkJzQI3berbisB+9gdDparg+L4E7Th/9S7x9POkDdKnuVx1EXFeeR+5GzYyxoKBHN0vTGe+G/A5ucE9pZJuPxqnHd+jZSTcZ7BYEwmLHruUg/bRYOtgOZny+TB0S2Z2ZV0LsYvFt5yKI79WWmh1HyKUE62Pd152vyB783Y2LaHP1oaHHHfOzUXq1xJ9qy7rQNBg0RJSfkwE+cRpc1tFFa9KZ9m8LxQBOMmu8oQoei7vESt/U+J38DzIY/JkF+aNy6iAsfwux1s/l5GVkGkr8ZECdOgniLKNvbzcIf8/jNlbLW6tCujoNAyW+D73AsvF4yOmEdOfZmIhD58ykBcWCVzieUAHJkXdFOFBDeGJfKvS1zI/atCq1zON5j9zuTk32ukVbAXBa3y+6KROEpODmyXTHh/XO8BXnZ8ohpHRqdOzdr43b/Gu0AkHEMXmJcaYuCZ7uYGL8qcGXa1CNezvvOO0B+S3joVI+MLLu4nbJUPU9YRB+MljqYj5nsNgzzGmYPmU4Ymd86/GbPP1tUuBQa5IMpCBHqulahEV2Lzb4jJwmAEB4G2hXSnEJCb/iRfC+vqs1Z8K5B30UiorWFSgf2CcUf37n3iIS/1gi+Tqg/3g5ATIawsPjapPYvn0Y=";


        boolean verify = RSAUtil.verify(data, publicKey, sign);
        log.info("verify:{}", verify);


        String s = RSAUtil.privateDecrypt(data, privateKey);
        log.info("DATA:{}", s);

    }
}
