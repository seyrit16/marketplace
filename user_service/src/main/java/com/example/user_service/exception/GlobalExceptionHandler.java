package com.example.user_service.exception;

import com.example.user_service.dto.response.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("Validation error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        List<String> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errors(errorMessages)
                .timestamp(timestamp)
                .build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SellerClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDTO> handleSellerClientException(SellerClientException exception, HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("Seller client exception error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDTO> handleOrderClientException(OrderClientException exception, HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("Order client exception error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConflictResourceByUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponseDTO> handleConflictResourceByUserException(ConflictResourceByUserException exception,
                                                            HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("Conflict resource by user error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                            HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("Resource not found error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VerificationServiceWorkException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDTO> handleVerificationServiceWorkException(VerificationServiceWorkException exception,
                                                            HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("Verification service work error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistException(UserAlreadyExistException exception,
                                                            HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("User already exist error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PasswordIsMissingException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDTO> handlePasswordIsMissingException(PasswordIsMissingException exception,
                                                            HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("Password is missing error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDTO> handleAuthenticationFailedException(AuthenticationFailedException exception,
                                                            HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("Authentication failed error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidVerificationCodeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDTO> handleInvalidVerificationCodeException(InvalidVerificationCodeException exception,
                                                                                   HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("Invalid verification code error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleException(UserNotFoundException exception, HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("User not found error: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception exception, HttpServletRequest request) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("Internal server error occurred: {} {}", request.getMethod(), request.getRequestURI(), exception);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
