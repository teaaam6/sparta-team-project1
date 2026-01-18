package sparta.spartateamproject1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sparta.spartateamproject1.entity.Item;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.type.ItemStatus;
import sparta.spartateamproject1.type.ItemCategory;

import java.time.LocalDateTime;
import java.util.Optional;

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
        private final String adminName;
        private final String adminEmail;

        public static ItemGetDto.Response fromEntity(Item item, Optional<Admin> admin){
            Long adminId = null;
            String adminName = null;
            String adminEmail = null;

            if (admin.isPresent()) {
                adminId = admin.get().getId();
                adminName = admin.get().getName();
                adminEmail = admin.get().getEmail();
            }

            return ItemGetDto.Response.builder()
                    .id(item.getId())

                    .name(item.getName())
                    .category(item.getCategory())
                    .price(item.getPrice())
                    .stock(item.getStock())
                    .status(item.getStatus())
                    .createdAt(item.getCreatedAt())

                    .adminId(adminId)
                    .adminName(adminName)
                    .adminEmail(adminEmail)

                    .build();
        }
    }
}
