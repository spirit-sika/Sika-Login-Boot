package cc.sika.sikaloginserver.service;

import cc.sika.sikaloginserver.dto.LoginDTO;

public interface LoginService {

    /**
     * 后台登录的方法, 传递登录参数以及验证码之后进行登录操作
     * 登录成功后返回登录成功的token
     * 登录失败返回提示信息
     * @param loginDTO 登录表单信息
     * @return 成功返回token, 失败返回错误信息
     */
    String login(LoginDTO loginDTO);
}
