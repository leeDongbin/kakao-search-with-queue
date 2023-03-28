package biz.kakao.search.api.search.exception;

import biz.kakao.common.code.ApiResponseCode;
import biz.kakao.common.generator.ApiResponseGenerator;
import biz.kakao.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;
import java.text.MessageFormat;

@Slf4j
@RestControllerAdvice
public class SearchApiServiceExceptionHandler {

    /**
     * 일반오류
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        String message = e.toString();

        log.error("ApiExceptionHandler > Exception > exception:{}", e);
        log.error("ApiExceptionHandler > Exception > userMessage:{}", message);
        log.error("ApiExceptionHandler > Exception > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.SYSTEM_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 요청한 URL 을 찾을 수 없는 경우
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse> handleException(NoHandlerFoundException e) {
        String message = "페이지를 찾을 수 없습니다.";

        log.error("ApiExceptionHandler > NoHandlerFoundException > userMessage:{}", message);
        log.error("ApiExceptionHandler > NoHandlerFoundException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * API 정의한 Method 와 요청을 준 Method 가 다른 경우
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> handleException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String message = MessageFormat.format("해당 API는 {0}를 지원하지 않습니다.", request.getMethod());

        log.error("ApiExceptionHandler > HttpRequestMethodNotSupportedException > userMessage:{}", message);
        log.error("ApiExceptionHandler > HttpRequestMethodNotSupportedException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * API 로 요청한 파라미터 또는 데이터가 없는 경우
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleException(HttpMessageNotReadableException e) {
        String message = MessageFormat.format("요청 파라미터 또는 데이터가 존재하지 않습니다. ({0})", e.getMostSpecificCause().getMessage());

        log.error("ApiExceptionHandler > HttpMessageNotReadableException > userMessage:{}", message);
        log.error("ApiExceptionHandler > HttpMessageNotReadableException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * API 로 요청한 파라미터들의 타입이 잘못 된 경우
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> handleException(MethodArgumentTypeMismatchException e) {
        String message = MessageFormat.format("입력값이 잘못되었습니다. ({0}의 요청타입은 {1}입니다.)", e.getName(), e.getRequiredType());

        log.error("ApiExceptionHandler > MethodArgumentTypeMismatchException > userMessage:{}", message);
        log.error("ApiExceptionHandler > MethodArgumentTypeMismatchException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * @Valid 가 선언된 객체의 유효성이 옳지 않은 경우
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse> handleException(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = MessageFormat.format("{0}는 {1}", fieldError.getField(), fieldError.getDefaultMessage());

        log.error("ApiExceptionHandler > BindException > userMessage:{}", message);
        log.error("ApiExceptionHandler > BindException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * @Valid 가 선언된 객체의 유효성이 옳지 않은 경우
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = MessageFormat.format("{0}는 {1}", fieldError.getField(), fieldError.getDefaultMessage());

        log.error("ApiExceptionHandler > MethodArgumentNotValidException > userMessage:{}", message);
        log.error("ApiExceptionHandler > MethodArgumentNotValidException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * 도메인 객체의 유효성이 옳지 않은 경우
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ApiResponse> handleException(InvalidParameterException e) {
        String message = e.getMessage();

        log.error("ApiExceptionHandler > InvalidParameterException > userMessage:{}", message);
        log.error("ApiExceptionHandler > InvalidParameterException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
