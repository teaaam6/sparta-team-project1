package sparta.spartateamproject1.exception;

import lombok.*;
import sparta.spartateamproject1.type.ErrorCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private ErrorCode errorCode;
    private String errorMessage;
}
