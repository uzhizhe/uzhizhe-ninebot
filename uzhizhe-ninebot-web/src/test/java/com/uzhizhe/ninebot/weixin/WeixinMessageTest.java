package com.uzhizhe.ninebot.weixin;

import com.monker.common.weixin.WeixinMessageSender;
import com.monker.common.weixin.domain.TextContext;
import com.uzhizhe.ninebot.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Desc
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-08-02
 */
@Slf4j
public class WeixinMessageTest extends TestApplication {
    @Test
    public void test13() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=9a408d7f-23c3-4bbf-bd45-0f7291da9744";
        TextContext textContext = new TextContext();
        textContext.setContent("test message");
        textContext.setMentioned_mobile_list(Arrays.asList("18339922767"));
        textContext.setMentioned_list(Arrays.asList("qingjiang.li", "@all"));
        WeixinMessageSender sender = new WeixinMessageSender();
        sender.sendMessage(url, textContext);

    }

    @Test
    public void test30() throws Exception {

        log.error("Test logback wx error logger");
        log.warn("Test logback wx warn logger");
        log.trace("Test loback wx trace logger");
        log.info("Test info logger");
        log.debug("Test debug logger");

        
    }


}
