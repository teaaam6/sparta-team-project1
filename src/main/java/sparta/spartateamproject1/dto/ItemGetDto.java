package sparta.spartateamproject1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sparta.spartateamproject1.entity.Item;
import sparta.spartateamproject1.type.ItemStatus;
import sparta.spartateamproject1.type.ItemCategory;

import java.time.LocalDateTime;

public class ItemGetDto {
    @Getter
    @Setter
    @Builder
    public static class Response {
        private final Long id;
        private final String name;
        private final ItemCategory category;
        private final Long price;
        private final Long stock;
        private final ItemStatus status;
        private final LocalDateTime createdAt;
        private final Long adminId;

        public static ItemGetDto.Response fromEntity(Item item){
            return ItemGetDto.Response.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .category(item.getCategory())
                    .price(item.getPrice())
                    .stock(item.getStock())
                    .status(item.getStatus())
                    .createdAt(item.getCreatedAt())
                    .adminId(item.getAdmin().getId())
                    .build();
        }
    }
}
