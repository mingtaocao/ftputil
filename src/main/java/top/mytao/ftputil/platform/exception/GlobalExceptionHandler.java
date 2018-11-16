package top.mytao.ftputil.platform.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.mytao.ftputil.platform.domain.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: caomingtao
 * @Date Create In 14:05 2018/11/16
 * @Modified By:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result jsonErrorHandler(HttpServletRequest req, Exception e) {

        logger.error("GlobalExceptionHandler ==== >> ", e);

        Result result = new Result(1, "系统异常");

        // 判断异常类型
//        if (e instanceof MissingServletRequestParameterException) {
//
//
//        }

        return result;
    }
}
