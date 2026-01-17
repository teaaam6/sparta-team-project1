package sparta.spartateamproject1.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class HttpMessageNotReadableExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e
    ) throws HttpMessageNotReadableException {
        // 우리가 핸들링 할 수 있는게 아니므로 다시 spring 한테 넘김
        if (!(e.getCause() instanceof InvalidFormatException formatException)) {
            throw e;
        }

        var paths = formatException.getPath();

        if (paths.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(String.format("값 %s이(가) 잘못 되었습니다.", formatException.getValue()));
        }

        Class<?> clazz = formatException.getTargetType();

        if (clazz.isEnum()) {
            @SuppressWarnings("unchecked")
            Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) formatException.getTargetType();
            Enum<?>[] enumConstants = enumClass.getEnumConstants();

            if (enumConstants.length <= 0) {
                return ResponseEntity.badRequest()
                    .body(String.format("값 %s이(가) 잘못 되었습니다.", formatException.getValue()));
            }

            StringBuilder sb = new StringBuilder();
            sb.append(paths.get(0).getFieldName());
            sb.append(": 값은 ");

            for(int i=0; i<enumConstants.length; i++) {
                Enum<?> constant = enumConstants[i];
                sb.append(constant.name());

                if (i != enumConstants.length-1) {
                    sb.append(", ");
                }
            }

            sb.append("중 하나여야 합니다.");

            return ResponseEntity.badRequest().body(sb.toString());
        }

        return ResponseEntity.badRequest().body(String.format("%s: %s은(는) 잘못된 값입니다", 
                paths.get(0).getFieldName(), formatException.getValue()));
    }
}
