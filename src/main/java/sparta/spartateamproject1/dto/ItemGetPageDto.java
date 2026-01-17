package sparta.spartateamproject1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sparta.spartateamproject1.type.ItemStatus;
import sparta.spartateamproject1.type.ItemCategory;

import java.time.LocalDateTime;

public class ItemGetPageDto {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Response {
        private final Long id;
        private final String name;
        private final ItemCategory category;
        private final Long price;
        private final Long stock;
        private final ItemStatus status;
        private final LocalDateTime createdAt;

        private final Long adminId;
        private final String adminName;
    }
}
