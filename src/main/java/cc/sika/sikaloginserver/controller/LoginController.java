package cc.sika.sikaloginserver.controller;

import cc.sika.sikaloginserver.dto.CaptchaEntity;
import cc.sika.sikaloginserver.dto.LoginDTO;
import cc.sika.sikaloginserver.service.CaptchaService;
import cc.sika.sikaloginserver.service.LoginService;
import cc.sika.sikaloginserver.vo.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user/")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final CaptchaService captchaService;
    private final LoginService loginService;

    @Operation(summary = "登录接口, 接收用户名, 密码, 验证码key与值进行验证登录")
    @PostMapping("login")
    public R<String> doLogin(@RequestBody LoginDTO loginDTO) {
        return R.success(loginService.login(loginDTO));
    }

    @Operation(summary = "获取验证码接口")
    @ApiResponse(content = @Content(schema = @Schema(implementation = CaptchaEntity.class)))
    @GetMapping("captcha")
    public R<CaptchaEntity> getCaptcha(@Nullable @RequestParam String codeKey) {
        CaptchaEntity captcha = captchaService.getCaptcha(codeKey);
        return R.success(captcha);
    }

}
