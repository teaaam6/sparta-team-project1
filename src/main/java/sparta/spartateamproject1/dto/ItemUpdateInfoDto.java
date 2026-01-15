package sparta.spartateamproject1.dto;

import lombok.Builder;
import lombok.Getter;
import sparta.spartateamproject1.entity.Item;
import sparta.spartateamproject1.type.ItemStatus;
import sparta.spartateamproject1.type.ItemCategory;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ItemUpdateInfoDto {
    @Getter
    @Builder
    public static class Request {
        @NotBlank(message = "상품 이름은 비어있으면 않됩니다")
        private String name;
        private ItemCategory category;
        @Positive(message = "제품 가격은 0 이하여서는 않됩니다.")
        private Long price;
    }

    @Getter
    @Builder
    public static class Response {
        private final String name;
        private final ItemCategory category;
        private final Long price;

        public static ItemUpdateInfoDto.Response fromEntity(Item item){
            return ItemUpdateInfoDto.Response.builder()
                    .name(item.getName())
                    .category(item.getCategory())
                    .price(item.getPrice())
                    .build();
        }
    }
}
