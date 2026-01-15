package sparta.spartateamproject1.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sparta.spartateamproject1.type.ItemCategory;
import sparta.spartateamproject1.type.ItemStatus;

import java.time.LocalDateTime;

public class ItemAddDto {
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String name;

        private ItemCategory category;

        private Long price;

        private Long stock;

        private ItemStatus status;
    }
}
