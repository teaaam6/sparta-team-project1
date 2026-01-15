package sparta.spartateamproject1.dto;

import lombok.Builder;
import lombok.Getter;
import sparta.spartateamproject1.entity.Item;
import sparta.spartateamproject1.type.ItemStatus;
import sparta.spartateamproject1.type.ItemCategory;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ItemUpdateDto {
    @Getter
    @Builder
    public static class UpdateInfoRequest {
        @NotBlank(message = "상품 이름은 비어있으면 않됩니다")
        private String name;
        private ItemCategory category;
        @Positive(message = "제품 가격은 0 이하여서는 않됩니다.")
        private Long price;
    }

    @Getter
    @Builder
    public static class UpdateStockRequest {
        @PositiveOrZero(message = "제품 재고는 0 미만여서는 않됩니다.")
        private Long stock;
    }

    @Getter
    @Builder
    public static class UpdateStatusRequest {
        private ItemStatus status;
    }

    @Getter
    @Builder
    public static class Response {
        private final Long id;
        private final String name;
        private final ItemCategory category;
        private final Long price;
        private final Long stock;
        private final ItemStatus status;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;
        private final Long adminId;

        public static ItemUpdateDto.Response fromEntity(Item item){
            return ItemUpdateDto.Response.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .category(item.getCategory())
                    .price(item.getPrice())
                    .stock(item.getStock())
                    .status(item.getStatus())
                    .createdAt(item.getCreatedAt())
                    .modifiedAt(item.getModifiedAt())
                    .adminId(item.getAdmin().getId())
                    .build();
        }
    }
}
