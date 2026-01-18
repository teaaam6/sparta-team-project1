package sparta.spartateamproject1.exception;

import org.springframework.security.core.AuthenticationException;

public class AdminNotApprovedException extends AuthenticationException {
    public AdminNotApprovedException(String msg) {
        super(msg);
    }
}
