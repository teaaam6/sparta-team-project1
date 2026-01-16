package sparta.spartateamproject1.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "중복되는 이메일입니다."),
    NOT_FOUND_EMAIL(HttpStatus.NOT_FOUND, "존재하지 않는 이메일입니다."),
    NOT_APPROVED(HttpStatus.BAD_REQUEST, "승인되지 않은 계정입니다.");

    private final HttpStatus status;
    private final String description;
}
