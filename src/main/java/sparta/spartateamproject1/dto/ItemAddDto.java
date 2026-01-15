package sparta.spartateamproject1.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sparta.spartateamproject1.type.ItemCategory;
import sparta.spartateamproject1.type.ItemStatus;

import java.time.LocalDateTime;

// TODO : 잘못된 enum의 경우 spring은 
// org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type
//
// 던집니다.
//
// 아직 이를 처리하는 exception handler가 없기 때문에
// 서버는 단순히 badRequest를 던집니다. 물론 틀린건 아니지만 저희 처리를 할 수 있으면 좋을거 같습니다.

public class ItemAddDto {
    @Getter
    @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "상품 이름은 비어있으면 않됩니다")
        private String name;

        private ItemCategory category;

        @Positive(message = "제품 가격은 0 이하여서는 않됩니다.")
        private Long price;

        @PositiveOrZero(message = "제품 재고는 0 미만여서는 않됩니다.")
        private Long stock;

        private ItemStatus status;
    }
}
