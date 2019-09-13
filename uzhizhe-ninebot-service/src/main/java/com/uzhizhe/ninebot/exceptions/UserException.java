package com.uzhizhe.ninebot.exceptions;

import com.monker.common.result.CodeMsg;

/**
 * @Desc user exception
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-13
 */
public class UserException extends RuntimeException {
    private static final long serialVersionUID = 8896797477402821708L;
    private int code;

    public UserException(int code, String message) {
        super(message);
        this.code = code;
    }

    public UserException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        code = codeMsg.getCode();
    }

    public UserException(int code) {
        this.code = code;
    }

    public UserException(String message) {
        super(message);
    }

    public UserException() {

    }

}
