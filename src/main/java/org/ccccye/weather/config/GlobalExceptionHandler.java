package org.ccccye.weather.config;

import org.ccccye.weather.common.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerResponse jsonErrorHandler(HttpServletRequest request, Exception e){
        ServerResponse response = ServerResponse.createByErrorMessage(e.getMessage());
        return response;
    }
}
