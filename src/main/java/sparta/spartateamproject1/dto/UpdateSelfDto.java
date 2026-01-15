package sparta.spartateamproject1.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.type.Role;

public class UpdateSelfDto {

    @Getter
    public static class Request {
        @NotBlank(message = "이름은 필수 기입란 입니다.")
        private String name;

        @NotBlank(message = "이메일은 필수 기입란 입니다.")
        @Email
        private String email;

        @NotBlank(message = "비밀번호는 공백이 될 수 없습니다.")
        @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        private String password;

        @NotBlank(message = "폰번호는 필수 기입란 입니다.")
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "폰번호 형식이 올바르지 않습니다.")
        private String phoneNumber;

    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private final Long id;
        private final String name;
        private final String email;
        private final String phoneNumber;

        public static UpdateSelfDto.Response fromEntity(Admin admin){
            return UpdateSelfDto.Response.builder()
                    .id(admin.getId())
                    .name(admin.getName())
                    .email(admin.getEmail())
                    .phoneNumber(admin.getPhoneNumber())
                    .build();
        }
    }
}
