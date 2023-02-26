package com.devo.product.web.error;

import com.devo.product.exception.CustomerNotFoundException;
import com.devo.product.exception.ProductNotFoundException;
import com.devo.product.exception.ProductOrderCreationException;
import com.devo.product.exception.ProductOrderNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler({
            CustomerNotFoundException.class,
            ProductNotFoundException.class,
            ProductOrderCreationException.class,
            ProductOrderNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleServletRequestBindingException(ServletRequestBindingException ex, HttpServletRequest request) {
        // todo: implement custom handlers for every exceptions and msg
        return makeResponseEntity(request, HttpStatus.BAD_REQUEST, "");
    }

    public static ResponseEntity<ErrorResponse> makeResponseEntity(HttpServletRequest request, HttpStatus status, String errorMessage) {
        var err = makeErrorResponse(request, status, errorMessage);
        return new ResponseEntity<>(err, status);
    }

    public static ErrorResponse makeErrorResponse(HttpServletRequest request, HttpStatus status, String errorMessage) {
        var err = new ErrorResponse();
        err.setTimestamp(new Date());
        err.setStatus(status.value());
        err.setPath(request.getRequestURI());
        err.setError(status.getReasonPhrase());
        err.setMessage(errorMessage);
        return err;
    }

}
