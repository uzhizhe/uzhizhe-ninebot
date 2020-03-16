package com.uzhizhe.ninebot.web.exception;

import com.monker.common.exception.ErrorServiceException;
import com.monker.common.exception.NormalServiceException;
import com.monker.common.result.ResponseDto;
import com.uzhizhe.ninebot.exceptions.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;

@RestControllerAdvice
public class NinebotExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(NinebotExceptionHandler.class);

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseDto MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("MethodArgumentNotValidException:{}", e.getMessage());
        return ResponseDto.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseDto handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseDto.error("路径不存在，请检查路径是否正确");
    }


    @ExceptionHandler({NormalServiceException.class})
    public ResponseDto handleNormalServiceException(NormalServiceException e) {
        log.warn("NormalException:{}", e.getMessage());
        return ResponseDto.error(e.getMessage());
    }

    @ExceptionHandler({ErrorServiceException.class})
    public ResponseDto handleErrorServiceException(ErrorServiceException e) {
        log.error("ErrorException:", e);
        return ResponseDto.error(e.getMessage());
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseDto handleJsonParseException(JsonParseException e) {
        log.error("JsonParseException:", e);
        return ResponseDto.error(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseDto handleBindException(BindException e) {
        log.error("BindException:", e);
        return ResponseDto.error(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseDto handleException(Exception e) {
        log.error("UnknownException", e);
        return ResponseDto.error(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseDto handleRuntimeException(RuntimeException e) {
        log.error("RuntimeException:", e);
        return ResponseDto.error(e.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResponseDto handleUserException(UserException e) {
        log.error("UserException:", e);
        return ResponseDto.error(e.getMessage());
    }

}
