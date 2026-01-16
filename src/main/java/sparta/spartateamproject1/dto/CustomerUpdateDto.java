package sparta.spartateamproject1.dto;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import sparta.spartateamproject1.entity.Customer;
import sparta.spartateamproject1.type.CustomerStatus;

import java.time.LocalDateTime;

public class CustomerUpdateDto {
    @Getter
    public static class Request {
        @Nullable
        private String name;
        @Nullable
        @Email
        private String email;
        @Nullable
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "폰번호 형식이 올바르지 않습니다.")
        private String phoneNumber;
    }

    @Getter
    @Builder
    public static class Response {
        private final String name;
        private final String email;
        private final String phoneNumber;
        private final CustomerStatus status;
        private final LocalDateTime createdAt;

        public static CustomerUpdateDto.Response fromEntity(Customer customer){
            return Response.builder()
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .phoneNumber(customer.getPhoneNumber())
                    .status(customer.getStatus())
                    .createdAt(customer.getCreatedAt())
                    .build();
        }
    }
}
