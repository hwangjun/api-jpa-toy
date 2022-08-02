package dev.backend.hw.exception;

import dev.backend.hw.common.BasicResponse;
import dev.backend.hw.common.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 유효성 검사 실패 에러
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<Error> errorList = new ArrayList<>();
        BindingResult bindingResult = e.getBindingResult();

        bindingResult.getAllErrors().forEach(error -> {
            FieldError field = (FieldError) error;

            Error errorMessage = Error.builder()
                    .field(field.getField())
                    .message(field.getDefaultMessage())
                    .invalidValue(field.getRejectedValue().toString())
                    .build();
            errorList.add(errorMessage);
        });

        String errorMessage = "유효성 검사 실패";
        BasicResponse<Object> errorResponse = getApiResponse(e, request, errorMessage, errorList);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = {MemberNotFoundException.class, MemberEmailAlreadyExistsException.class})
    public ResponseEntity memberNotFoundException(MemberNotFoundException e, HttpServletRequest request) {
        BasicResponse<Object> errorResponse = getApiResponse(e, request);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private BasicResponse<Object> getApiResponse(Exception e, HttpServletRequest request, String errorMessage, List<Error> errorList) {
        return BasicResponse.error(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                request.getRequestURI(),
                errorList);
    }

    private BasicResponse<Object> getApiResponse(Exception e, HttpServletRequest request) {
        return BasicResponse.error(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI(),
                null);
    }
}
