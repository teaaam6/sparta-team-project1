package sparta.spartateamproject1.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidLoginRequestException extends AuthenticationException {

    public InvalidLoginRequestException(String msg) {
        super(msg);
    }
}