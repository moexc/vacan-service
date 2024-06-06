package cn.moexc.vcs.web.config;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.service.config.LockerException;
import cn.moexc.vcs.web.R;
import cn.moexc.vcs.web.config.auth.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AuthException.class)
    public R authExceptionHandler(AuthException authException, HttpServletResponse res){
        res.setStatus(authException.getStatus());
        return R.error(authException.getStatus(), authException.getMessage());
    }

    @ExceptionHandler(AlterException.class)
    public R alterExceptionHandler(AlterException alterException, HttpServletResponse res){
        res.setStatus(HttpStatus.NOT_EXTENDED.value());
        return R.error(alterException.getMessage());
    }

    @ExceptionHandler(LockerException.class)
    public R lockerException(LockerException lockerException, HttpServletResponse res){
        res.setStatus(HttpStatus.CONFLICT.value());
        return R.error("请稍后重试");
    }

    /**
     * Controller参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletResponse res){
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        return R.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * Assert校验异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public R illegalArgumentExceptionHandler (IllegalArgumentException e, HttpServletResponse res){
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        return R.error(e.getMessage());
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e, HttpServletRequest req, HttpServletResponse res){
        LOGGER.error("[服务异常] URI: {" + req.getRequestURI() +
                "}, \n\tRequestParameters: {" + this.getRequestParamters(req) +
                "}, \n\tRequestHeaders: {" + this.getRequestHeaders(req),
                "}, \n\tError: ", e);
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * 解析请求参数
     */
    private String getRequestParamters(HttpServletRequest request){
        Enumeration<String> paramks = request.getParameterNames();
        String paramk;
        StringBuilder sb = new StringBuilder();
        while (paramks.hasMoreElements()){
            paramk = paramks.nextElement();
            sb.append(paramk).append(":").append(request.getParameter(paramk)).append(",");
        }
        return sb.toString();
    }

    /**
     * 解析请求头
     */
    private String getRequestHeaders(HttpServletRequest request){
        Enumeration<String> headers = request.getHeaderNames();
        String header;
        StringBuilder sb = new StringBuilder();
        while (headers.hasMoreElements()){
            header = headers.nextElement();
            sb.append(header).append(":").append(request.getHeader(header)).append(",");
        }
        return sb.toString();
    }

}
