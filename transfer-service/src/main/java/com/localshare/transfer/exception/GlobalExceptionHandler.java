package com.localshare.transfer.exception;

import com.localshare.common.dto.ApiErrorDTO;
import com.localshare.common.exception.FileSizeLimitExceededException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<ApiErrorDTO> handleFileSizeLimitExceededException(FileSizeLimitExceededException ex,  HttpServletRequest req) {
        String message = ex.getMessage();
        ApiErrorDTO error = new ApiErrorDTO(
                "PAYLOAD_TOO_LARGE",
                message,
                LocalDateTime.now(),
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String message = ex.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();
        ApiErrorDTO error = new ApiErrorDTO(
                "VALIDATION_ERROR",
                message,
                LocalDateTime.now(),
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDTO> handleGenericException(Exception ex, HttpServletRequest req) {
        String message = ex.getMessage();
        ApiErrorDTO error = new ApiErrorDTO(
                "INTERNAL_ERROR",
                message,
                LocalDateTime.now(),
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
