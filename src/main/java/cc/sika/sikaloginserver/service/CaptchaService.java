package cc.sika.sikaloginserver.service;

import cc.sika.sikaloginserver.dto.CaptchaEntity;

public interface CaptchaService {

    CaptchaEntity getCaptcha(String codeKey);

    /**
     * 根据用户提交的codeKey和codeValue进行验证码校验
     * @param key 验证码id
     * @param code 验证码内容
     * @return 返回校验结果信息
     */
    boolean checkCaptcha(String key, String code);
}
