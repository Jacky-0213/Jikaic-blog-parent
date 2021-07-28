package top.jiakaic.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jiakaic.blog.vo.Result;

/**
 * @author JK
 * @date 2021/7/26 -15:34
 * @Description
 **/
//aop
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handlerException(Exception e){
        e.printStackTrace();
        return Result.fail(500,"系统异常");
    }
}
