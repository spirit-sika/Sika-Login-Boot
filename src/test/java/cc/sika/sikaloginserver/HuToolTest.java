package cc.sika.sikaloginserver;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HuToolTest {

    @Test
    void testCaptcha() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 50);
        String code = lineCaptcha.getCode();
        String imageBase64 = lineCaptcha.getImageBase64Data();
        System.out.println("code = " + code);
        System.out.println("imageBase64 = " + imageBase64);
    }

    @Test
    void testSnow() {
        String snowflakeId = IdUtil.getSnowflakeNextIdStr();
        System.out.println("snowflakeId = " + snowflakeId);
    }
}
