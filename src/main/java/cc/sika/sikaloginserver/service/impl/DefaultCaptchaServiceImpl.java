package cc.sika.sikaloginserver.service.impl;

import cc.sika.sikaloginserver.dto.CaptchaEntity;
import cc.sika.sikaloginserver.exception.LoginException;
import cc.sika.sikaloginserver.service.CaptchaService;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service("captchaService")
public class DefaultCaptchaServiceImpl implements CaptchaService {

    private final StringRedisTemplate stringRedisTemplate;
    private final ValueOperations<String, String> ops;

    /**
     * 获取验证码后将验证码的值缓存到redis中,
     * 将验证码转为Base64字符串返回
     * @return 验证码的Base64字符串, 携带类型可以直接设置为img的src属性值
     */
    @Override
    public CaptchaEntity getCaptcha(String codeKey) {
        // 校验验证码键非空
        if (StringUtils.hasLength(codeKey)) {
            stringRedisTemplate.delete("imageCode:"+codeKey);
        }
        // 生成验证码信息
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 50);
        String snowflakeKey = IdUtil.getSnowflakeNextIdStr();
        String codeValue = lineCaptcha.getCode();
        // 将验证码存入redis
        saveCaptcha(snowflakeKey, codeValue);
        // 生成前端渲染vo
        CaptchaEntity captchaInfo = new CaptchaEntity();
        captchaInfo.setCodeKey(snowflakeKey);
        captchaInfo.setBase64(lineCaptcha.getImageBase64Data());
        return captchaInfo;
    }

    @Override
    public boolean checkCaptcha(String key, String code) {
        String codeValue = ops.get("imageCode:"+key);
        if (!StringUtils.hasText(codeValue)) {
            stringRedisTemplate.delete("imageCode:"+key);
            throw new LoginException("验证码已失效");
        }

        if (!codeValue.equalsIgnoreCase(code)) {
            stringRedisTemplate.delete("imageCode:"+key);
            throw new LoginException("验证码错误");
        }
        stringRedisTemplate.delete("imageCode:"+key);
        return true;
    }

    /**
     * 将验证码缓存到redis, 十五分钟后过期
     * @param codeKey 验证码的键
     * @param codeValue 验证码内容
     */
    private void saveCaptcha(String codeKey, String codeValue) {
        ops.set("imageCode:"+codeKey, codeValue, 15, TimeUnit.MINUTES);
    }


    public DefaultCaptchaServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.ops = stringRedisTemplate.opsForValue();
    }
}
