package sparta.spartateamproject1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import sparta.spartateamproject1.type.ErrorCode;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HttpStatus errorStatus;
    private final String errorMessage;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorStatus = errorCode.getStatus();
        this.errorMessage = errorCode.getDescription();
    }
}
