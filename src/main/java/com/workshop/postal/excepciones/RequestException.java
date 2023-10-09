package com.workshop.postal.excepciones;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RequestException extends RuntimeException {
private HttpStatus httpStatus;
private String code;

    public RequestException(HttpStatus httpStatus, String code,String message) {
        super(message);
        this.httpStatus=httpStatus;
        this.code=code;
    }
}
