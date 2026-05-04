package com.localshare.discovery.exception;

import com.localshare.common.dto.ApiErrorDTO;
import com.localshare.common.exception.DeviceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleDeviceNotFoundException(DeviceNotFoundException e, HttpServletRequest req) {
        String message = e.getMessage();
        ApiErrorDTO error = new ApiErrorDTO(
                "NOT_FOUND",
                message,
                LocalDateTime.now(),
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest req) {
        String message = e.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();
        ApiErrorDTO error = new ApiErrorDTO(
                "BAD_REQUEST",
                message,
                LocalDateTime.now(),
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDTO> handleException(Exception e, HttpServletRequest req) {
        String message = e.getMessage();
        ApiErrorDTO error = new ApiErrorDTO(
                "INTERNAL_SERVER_ERROR",
                message,
                LocalDateTime.now(),
                req.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
