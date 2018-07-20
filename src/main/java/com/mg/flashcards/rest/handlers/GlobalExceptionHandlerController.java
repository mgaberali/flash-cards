package com.mg.flashcards.rest.handlers;

import com.mg.flashcards.exceptions.BadRequestException;
import com.mg.flashcards.rest.controllers.RestControllersMarker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = RestControllersMarker.class)
public class GlobalExceptionHandlerController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> badRequestException(BadRequestException ex){

        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


}
