package sparta.spartateamproject1.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sparta.spartateamproject1.type.ItemCategory;
import sparta.spartateamproject1.type.ItemStatus;

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
