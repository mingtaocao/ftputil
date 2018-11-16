package top.mytao.ftputil.platform.domain;

import lombok.Data;
import top.mytao.ftputil.platform.enums.SysExpEnum;

import java.io.Serializable;

/**
 * @Description:
 * @Author: caomingtao
 * @Date Create In 14:06 2018/11/16
 * @Modified By:
 */
@Data
public class Result<T> implements Serializable {



    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * token信息
     */
    private String token;

    /**
     * 具体内容：泛型
     */
    private T data;

    public Result() {
    }

    public Result(SysExpEnum sysEnum) {

        this.code = sysEnum.getCode();
        this.message = sysEnum.getMessage();

    }

    public Result(Integer code, String message) {

        this.code = code;
        this.message = message;

    }

    public Result(Integer code, String message, T data) {

        this.code = code;
        this.message = message;
        this.data = data;

    }
}
