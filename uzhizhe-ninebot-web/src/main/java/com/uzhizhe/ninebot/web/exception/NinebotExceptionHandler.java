package com.uzhizhe.ninebot.web.exception;

import com.monker.common.exception.ErrorServiceException;
import com.monker.common.exception.NormalServiceException;
import com.monker.common.result.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
public class NinebotExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(NinebotExceptionHandler.class);

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


}
