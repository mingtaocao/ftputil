package top.mytao.ftputil.platform.domain;

import top.mytao.ftputil.platform.enums.SysExpEnum;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description:
 * @Author: caomingtao
 * @Date Create In 14:25 2018/11/16
 * @Modified By:
 */
public class BaseController {

    /**
     * 返回成功(含参)
     *
     * @param object
     * @return
     */
    public Result success(Object object) {
        int code = SysExpEnum.SUCCESS.getCode();
        String msg = SysExpEnum.SUCCESS.getMessage();
        Result result = message(code, msg);
        result.setData(object);

        return result;
    }


    /**
     * 返回成功
     *
     * @return
     */
    public Result success() {
        return success(null);
    }

    /**
     * 返回信息
     *
     * @param code
     * @param msg
     * @return
     */
    public Result message(Integer code, String msg) {
        Result result = new Result(code, msg);
        return result;
    }

    /**
     * 返回信息
     *
     * @param sysExpEnum
     * @return
     */
    public Result message(SysExpEnum sysExpEnum) {
        Result result = new Result(sysExpEnum);
        return result;
    }

    /**
     * 返回信息
     *
     * @param code
     * @param msg
     * @return
     */
    public Result message(Integer code, String msg, Object data) {
        Result result = new Result(code, msg, data);
        return result;
    }

    /**
     * 返回失败
     *
     * @return
     */
    public Result error() {
        int code = SysExpEnum.UNKNOW_ERROR.getCode();
        String msg = SysExpEnum.UNKNOW_ERROR.getMessage();
        Result result = message(code, msg);

        return result;
    }

    /**
     * 返回失败
     *
     * @return
     */
    public Result error(int num, String meg) {
        Result result = new Result(num, meg);
        return result;
    }

    /**
     * 创建新增数据
     *
     * @return
     */
    public Map<String, Object> createData() {

        return null;

    }

}
