package sparta.spartateamproject1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sparta.spartateamproject1.entity.Customer;
import sparta.spartateamproject1.type.CustomerStatus;

import java.time.LocalDateTime;

public class CustomerGetDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{

        private Long id;
        private String name;
        private String phoneNumber;
        private String email;
        private CustomerStatus status;
        private LocalDateTime createdAt;

        public static CustomerGetDto.Response fromEntity(Customer customer){
            return CustomerGetDto.Response.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .phoneNumber(customer.getPhoneNumber())
                    .status(customer.getStatus())
                    .createdAt(customer.getCreatedAt())
                    .build();
        }
    }
}
