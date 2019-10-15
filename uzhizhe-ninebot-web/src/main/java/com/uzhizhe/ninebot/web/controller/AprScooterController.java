package com.uzhizhe.ninebot.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monker.common.http.HttpParams;
import com.monker.common.http.HttpResult;
import com.monker.common.http.HttpUtil;
import com.monker.common.http.enums.HttpMethod;
import com.monker.common.result.ResponseDto;
import com.uzhizhe.ninebot.common.Constant;
import com.uzhizhe.ninebot.entities.discovery.DSCVBean;
import com.uzhizhe.ninebot.entities.discovery.RobotLockRequestVo;
import com.uzhizhe.ninebot.utils.AESCBCUtil;
import com.uzhizhe.ninebot.web.vo.EncodeInboundDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc apr soccter controller
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-30
 */
@RestController
@Slf4j
public class AprScooterController {

    private final String unlockUrl = "http://scooter-discoveryAlpha.loomo.com/scooter/business/api/unlock";
    private final String lockUrl = "http://scooter-discoveryAlpha.loomo.com/scooter/business/api/lock";


    @GetMapping(value = "/scooter/unlock")
    @CrossOrigin("*")
    public Object unlock(@RequestParam String robotId) throws Exception {
        String merchantId = Constant.discoverMerchantId;
        String merchantKey = Constant.discoverMerchantKey;

        RobotLockRequestVo requestVo = new RobotLockRequestVo();
        requestVo.setVehicleNum(robotId);
        requestVo.setOrderNo(System.currentTimeMillis());
        String data = JSON.toJSONString(requestVo);

        String seed = AESCBCUtil.getRandomSeed();
        String inData = AESCBCUtil.encrypt(data, Constant.discoverAeskey, seed);

        String sign = AESCBCUtil.sign(merchantId, merchantKey, seed, data);

        DSCVBean vo = new DSCVBean();
        vo.setInData(inData);
        vo.setMerchantId(merchantId);
        vo.setSeed(seed);
        vo.setSign(sign);

        HttpParams httpParams = new HttpParams();
        httpParams.setJsonByObj(vo);
        HttpResult<String> http = HttpUtil.http(HttpMethod.POST, unlockUrl, httpParams);
        log.info("Http result unlock:{}", http);
        String result = http.getData();
        EncodeInboundDTO encodeInboundDTO = JSONObject.parseObject(result, EncodeInboundDTO.class);
        return ResponseDto.success(encodeInboundDTO.getDecodeData(Constant.discoverAeskey, Constant.discoverMerchantKey));
    }

    @GetMapping(value = "/scooter/lock")
    @CrossOrigin("*")
    public Object lock(@RequestParam String robotId) throws Exception {
        String merchantId = Constant.discoverMerchantId;
        String merchantKey = Constant.discoverMerchantKey;

        RobotLockRequestVo requestVo = new RobotLockRequestVo();
        requestVo.setVehicleNum(robotId);
        requestVo.setOrderNo(System.currentTimeMillis());
        String data = JSON.toJSONString(requestVo);

        String seed = AESCBCUtil.getRandomSeed();
        String inData = AESCBCUtil.encrypt(data, Constant.discoverAeskey, seed);

        String sign = AESCBCUtil.sign(merchantId, merchantKey, seed, data);

        DSCVBean vo = new DSCVBean();
        vo.setInData(inData);
        vo.setMerchantId(merchantId);
        vo.setSeed(seed);
        vo.setSign(sign);

        HttpParams httpParams = new HttpParams();
        httpParams.setJsonByObj(vo);
        HttpResult<String> http = HttpUtil.http(HttpMethod.POST, lockUrl, httpParams);
        log.info("Http result lock:{}", http);
        String result = http.getData();
        EncodeInboundDTO encodeInboundDTO = JSONObject.parseObject(result, EncodeInboundDTO.class);
        String decodeData = encodeInboundDTO.getDecodeData(Constant.discoverAeskey, Constant.discoverMerchantKey);
        return ResponseDto.success(decodeData);
    }

    @PostMapping(value = "/discovery/unlockCallback", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin("*")
    public Object unlockCallback(@RequestBody DSCVBean dscvBean) throws Exception {
        log.info("收到开锁回调, DSCVBean:{}", dscvBean);
        String merchantId = dscvBean.getMerchantId();
        String merchantKey = Constant.discoverMerchantKey;
        String discoverAeskey = Constant.discoverAeskey;
        String seed = dscvBean.getSeed();
        String sign = dscvBean.getSign();
        String inData = dscvBean.getInData();
        boolean verifyingSign = AESCBCUtil.verifyingSign(merchantId, merchantKey, seed, inData, sign);
        if (!verifyingSign) {
            log.info("VerifyingSign unlock callback result:{}", verifyingSign);
            return ResponseDto.error("VerifyingSign unlock failed!");
        }
        log.info("VerifyingSign unlock successful:{}", verifyingSign);
        String data = AESCBCUtil.decrypt(inData, discoverAeskey, seed);
        log.info("Request unlock data:{}", data);
        return ResponseDto.success();
    }

    @PostMapping(value = "/discovery/lockCallback", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin("*")
    public Object lockCallback(@RequestBody DSCVBean dscvBean) throws Exception {
        log.info("收到落锁回调, DSCVBean:{}", dscvBean);
        String merchantId = dscvBean.getMerchantId();
        String merchantKey = Constant.discoverMerchantKey;
        String discoverAeskey = Constant.discoverAeskey;
        String seed = dscvBean.getSeed();
        String sign = dscvBean.getSign();
        String inData = dscvBean.getInData();
        boolean verifyingSign = AESCBCUtil.verifyingSign(merchantId, merchantKey, seed, inData, sign);
        if (!verifyingSign) {
            log.info("VerifyingSign lock callback result:{}", verifyingSign);
            return ResponseDto.error("VerifyingSign lock failed!");
        }
        log.info("VerifyingSign lock successful:{}", verifyingSign);
        String data = AESCBCUtil.decrypt(inData, discoverAeskey, seed);
        log.info("Request lock data:{}", data);
        return ResponseDto.success();
    }

    @PostMapping(value = "/discovery/uploadRobotInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin("*")
    public Object uploadRobotInfo(@RequestBody DSCVBean dscvBean) throws Exception {
        log.info("SCOOTER 上报机器人消息, DSCVBean:{}", dscvBean);
        String merchantId = dscvBean.getMerchantId();
        String merchantKey = Constant.discoverMerchantKey;
        String discoverAeskey = Constant.discoverAeskey;
        String seed = dscvBean.getSeed();
        String sign = dscvBean.getSign();
        String inData = dscvBean.getInData();
        boolean verifyingSign = AESCBCUtil.verifyingSign(merchantId, merchantKey, seed, inData, sign);
        if (!verifyingSign) {
            log.info("VerifyingSign upload robot info result:{}", verifyingSign);
            return ResponseDto.error("VerifyingSign upload robot info  failed!");
        }
        log.info("VerifyingSign upload robot info  successful:{}", verifyingSign);
        String data = AESCBCUtil.decrypt(inData, discoverAeskey, seed);
        log.info("Request upload robot info  data:{}", data);
        return ResponseDto.success();
    }


}
