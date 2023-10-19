package com.workshop.postal.excepciones;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

//    @ExceptionHandler(value = RequestException.class)
//    public ResponseEntity<ErrorP> requestExceptionHandler(RequestException ex){
//        ErrorP error = ErrorP.builder().code(ex.getCode()).message(ex.getMessage()).build();
//        return new ResponseEntity<>(error, ex.getHttpStatus());
//    }
//
//    @ExceptionHandler(value = PersonajeNotFoundException.class)
//    public ResponseEntity<ErrorP> PersonajeNotFoundExceptionHandler(PersonajeNotFoundException ex){
//        ErrorP error = ErrorP.builder().code(ex.getCode()).message(ex.getMessage()).build();
//        return new ResponseEntity<>(error, ex.getHttpStatus());
//    }
//
//    @ExceptionHandler(value = PeliculaNotFoundException.class)
//    public ResponseEntity<ErrorP> PeliculaNotFoundExceptionHandler(PeliculaNotFoundException ex){
//        ErrorP error = ErrorP.builder().code(ex.getCode()).message(ex.getMessage()).build();
//        return new ResponseEntity<>(error, ex.getHttpStatus());
//    }

}
