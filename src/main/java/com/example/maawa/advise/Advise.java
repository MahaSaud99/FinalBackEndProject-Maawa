package com.example.maawa.advise;

import com.example.maawa.dto.apiResponse;
import com.example.maawa.exception.apiException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class Advise {

    @ExceptionHandler(value = apiException.class)
    public ResponseEntity<apiResponse> apiException(apiException e){
        String msg=e.getMessage();
        return ResponseEntity.status(400).body(new apiResponse(msg));
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<apiResponse> SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        String msg=e.getMessage();
        return ResponseEntity.status(400).body(new apiResponse(msg));
    }
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<apiResponse> EntityNotFoundException(EntityNotFoundException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new apiResponse(msg));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<apiResponse> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new apiResponse(msg));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<apiResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new apiResponse(msg));
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<apiResponse> IOException(IOException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new apiResponse(msg));
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<apiResponse> Exception(Exception e) {
        return ResponseEntity.status(400).body(new apiResponse("SERVER ERROR!"));
    }
}
