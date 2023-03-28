package biz.kakao.external.client.kakao.exception;

public class ApiStatusFailException extends RuntimeException {
    public ApiStatusFailException() { super("Api 상태 실패 오류가 발생했습니다.");}
    public ApiStatusFailException(String message) { super(message); }
    public ApiStatusFailException(String message, Throwable cause) { super(message, cause); }
}
