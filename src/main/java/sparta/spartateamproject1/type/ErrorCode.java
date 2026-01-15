package sparta.spartateamproject1.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "중복되는 이메일입니다.");

    private final HttpStatus status;
    private final String description;
}
