package top.mytao.ftputil.platform.enums;

/**
 * @Description:
 * @Author: caomingtao
 * @Date Create In 14:04 2018/11/16
 * @Modified By:
 */
public enum SysExpEnum {

    UNKNOW_ERROR(-1, "unknow error"),

    SUCCESS(0, "success"),

    ERROR(1, "failed"),

    CLIENTID_NOT_EXIST(2, "clientId invalid"),


    USER_INVALID(3, "user invalid"),

    USER_EXIST(4, "user exist already"),

    PWD_INVALID(5, "password invalid"),

    REGISTER_INVALID(6, "register failed"),

    LOGIN_ALREADY(7, "user already login"),

    LOGOFF_ALREADY(8, "user already logoff"),

    TOKEN_NOT_EXIST(9, "no token accepted"),

    TOKEN_INVALID(10, "token verify failed"),

    TOKEN_NOT_EXIST_IN_REDIS(11, "no token in redis"),

    TOKEN_NOT_MATCH_IN_REDIS(12, "token not match in redis"),

    NEED_LOGIN(13, "you need login"),

    TOKEN_VERIFY_ERROR(14, "some problem happened during token verify");

    private int code;

    private String message;

    SysExpEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
