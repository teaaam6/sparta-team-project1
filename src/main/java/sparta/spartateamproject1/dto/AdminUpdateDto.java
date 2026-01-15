package sparta.spartateamproject1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sparta.spartateamproject1.entity.Admin;

@Getter
public class AdminUpdateDto {

    @Getter
    public static class Request {
        @NotBlank(message = "이름은 필수 기입란 입니다.")
        private String name;

        @NotBlank(message = "이메일은 필수 기입란 입니다.")
        @Email
        private String email;

        @NotBlank(message = "폰번호는 필수 기입란 입니다.")
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "폰번호 형식이 올바르지 않습니다.")
        private String phoneNumber;
    }
    @Getter
    @Builder
    public static class Response {
        private final String name;
        private final String email;
        private final String phoneNumber;

        public static AdminUpdateDto.Response fromEntity(Admin admin){
            return AdminUpdateDto.Response.builder()
                    .name(admin.getName())
                    .email(admin.getEmail())
                    .phoneNumber(admin.getPhoneNumber())
                    .build();
        }


    }
}
