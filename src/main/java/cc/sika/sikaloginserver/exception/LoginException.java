package cc.sika.sikaloginserver.exception;

public class LoginException extends RuntimeException{
    public LoginException() {
        super("登录出现异常");
    }

    public LoginException(String message) {
        super(message);
    }
}
