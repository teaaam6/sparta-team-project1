package sparta.spartateamproject1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.Role;

import java.time.LocalDateTime;

public class AdminGetAllDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{
        private final Long id;
        private final String name;
        private final String email;
        private final String phoneNumber;
        private final Role role;
        private final AdminStatus status;
        private final LocalDateTime createdAt;
        private final LocalDateTime approvedAt;
    }
}
