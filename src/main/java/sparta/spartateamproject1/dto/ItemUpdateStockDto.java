package sparta.spartateamproject1.dto;

import lombok.Builder;
import lombok.Getter;
import sparta.spartateamproject1.entity.Item;
import sparta.spartateamproject1.type.ItemStatus;
import sparta.spartateamproject1.type.ItemCategory;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ItemUpdateStockDto {
    @Getter
    @Builder
    public static class Request {
        @PositiveOrZero(message = "제품 재고는 0 미만여서는 않됩니다.")
        private Long stock;
    }

    @Getter
    @Builder
    public static class Response {
        private final Long stock;

        public static ItemUpdateStockDto.Response fromEntity(Item item){
            return ItemUpdateStockDto.Response.builder()
                    .stock(item.getStock())
                    .build();
        }
    }
}
