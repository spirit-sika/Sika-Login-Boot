package cc.sika.sikaloginserver.handler;

import cc.sika.sikaloginserver.exception.LoginException;
import cc.sika.sikaloginserver.vo.R;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody // 序列化为json
    public R<String> handleLoginException(LoginException loginException) {
        return R.fail(HttpStatus.UNAUTHORIZED.value(),
                loginException.getMessage());
    }
}
