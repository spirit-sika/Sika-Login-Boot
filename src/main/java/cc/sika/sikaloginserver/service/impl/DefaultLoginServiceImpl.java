package cc.sika.sikaloginserver.service.impl;

import cc.sika.sikaloginserver.common.Constants;
import cc.sika.sikaloginserver.dto.LoginDTO;
import cc.sika.sikaloginserver.entity.SikaUser;
import cc.sika.sikaloginserver.common.Device;
import cc.sika.sikaloginserver.exception.LoginException;
import cc.sika.sikaloginserver.mapper.SikaUserMapper;
import cc.sika.sikaloginserver.service.CaptchaService;
import cc.sika.sikaloginserver.service.LoginService;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("loginService")
@RequiredArgsConstructor
public class DefaultLoginServiceImpl implements LoginService {

    private final CaptchaService captchaService;
    private final SikaUserMapper sikaUserMapper;
    @Override
    public String login(LoginDTO loginDTO) {
        // 校验验证码
        captchaService.checkCaptcha(loginDTO.getCodeKey(), loginDTO.getCode());
        // 校验用户信息
        loginDTO.setPassword(SaSecureUtil.sha256(loginDTO.getPassword()));
        SikaUser sikaUser = sikaUserMapper.selectOne(
                new LambdaQueryWrapper<>(SikaUser.class)
                        .eq(SikaUser::getUsername, loginDTO.getUsername())
                        .eq(SikaUser::getPassword, loginDTO.getPassword()));
        if (Objects.isNull(sikaUser)) {
            throw new LoginException("用户名或密码错误");
        }
        // "记住我"处理
        if (loginDTO.getRememberMe()) {
            StpUtil.login(sikaUser.getUserId(),
                    new SaLoginModel().setTimeout(60 * 60 * 24 * 15).setDevice(Device.PC.name()));
        }
        else {
            StpUtil.login(sikaUser.getUserId(), Device.PC.name());
        }
        // 缓存用户信息
        StpUtil.getSession().set(Constants.CURRENT_USER,
                sikaUserMapper.selectOne(
                        new LambdaQueryWrapper<>(SikaUser.class)
                                .eq(SikaUser::getUserId, sikaUser.getUserId())));
        // 成功返回token
        return StpUtil.getTokenValue();
    }
}
