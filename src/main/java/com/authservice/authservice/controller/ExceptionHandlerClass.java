package com.authservice.authservice.controller;

import com.authservice.authservice.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> globalExceptionHandler(
            Exception e,
            WebRequest request
    ){
        ErrorDto dto = new ErrorDto(e.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
