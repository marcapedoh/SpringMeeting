package com.marcRG.javaCommunity.handlers;

import com.marcRG.javaCommunity.exception.EntityNotFoundException;
import com.marcRG.javaCommunity.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleExcpetion(EntityNotFoundException exception, WebRequest webRequest){
        final HttpStatus notfound=HttpStatus.NOT_FOUND;
        final ErrorDTO errorDTO=ErrorDTO.builder()
                .codes(exception.getErrorCodes())
                .httpCode(notfound.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO,notfound);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDTO> handleException(InvalidEntityException exception, WebRequest request){
        final HttpStatus badRequest=HttpStatus.BAD_REQUEST;

        final ErrorDTO errorDTO= ErrorDTO.builder()
                .codes(exception.getErrorCodes())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .error(exception.getError())
                .build();
        return new  ResponseEntity<>(errorDTO,badRequest);
    }

}
