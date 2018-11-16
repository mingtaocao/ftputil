package top.mytao.ftputil.platform.util;

import top.mytao.ftputil.platform.domain.Result;
import top.mytao.ftputil.platform.enums.SysExpEnum;

/**
 * @Description: 数据返回结果统一处理
 * @Author: caomingtao
 * @Date Create In 14:12 2018/11/16
 * @Modified By:
 */
public class ResultUtil {

    /**
     * 返回成功(含参)
     *
     * @param token
     * @param object
     * @return
     */
    public static Result success(String token, Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setToken(token);
        result.setData(object);
        return result;
    }

    /**
     * 返回成功(含参)
     *
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setToken(null);
        result.setData(object);
        return result;
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 返回失败(含参)
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        result.setToken(null);
        result.setData(null);
        return result;
    }

    /**
     * 返回失败(含参)
     *
     * @param sysExpEnum
     * @return
     */
    public static Result error(SysExpEnum sysExpEnum) {
        Result result = new Result();
        result.setCode(sysExpEnum.getCode());
        result.setMessage(sysExpEnum.getMessage());
        result.setToken(null);
        result.setData(null);
        return result;
    }
}
