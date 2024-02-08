package com.example.StudentLib.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.StudentLib.Dto.ResponseDTO;
import com.example.StudentLib.exception.StudentNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {
	
      @ExceptionHandler
     public ResponseDTO studentNotFoundExceptionHandler(StudentNotFoundException ex) {
         return new ResponseDTO(404, "Error", ex.getMessage());    	  
      }
}