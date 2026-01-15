package sparta.spartateamproject1.dto;

import lombok.Builder;
import lombok.Getter;
import sparta.spartateamproject1.entity.Item;
import sparta.spartateamproject1.type.ItemStatus;
import sparta.spartateamproject1.type.ItemCategory;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ItemUpdateStatusDto {
    @Getter
    @Builder
    public static class Request {
        private ItemStatus status;
    }

    @Getter
    @Builder
    public static class Response {
        private final ItemStatus status;

        public static ItemUpdateStatusDto.Response fromEntity(Item item){
            return ItemUpdateStatusDto.Response.builder()
                    .status(item.getStatus())
                    .build();
        }
    }
}
