package sparta.spartateamproject1.dto;

import lombok.Builder;
import lombok.Getter;
import sparta.spartateamproject1.type.CustomerStatus;

import java.time.LocalDateTime;

public class CustomerGetDto {

    @Getter
    @Builder
    public static class Response{

        private Long id;
        private String name;
        private String phoneNumber;
        private String email;
        private CustomerStatus status;
        private LocalDateTime createdAt;

    }
}
