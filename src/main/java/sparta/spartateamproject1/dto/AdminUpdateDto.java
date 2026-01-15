package sparta.spartateamproject1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.Role;

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

    @Getter
    public static class RoleRequest {
        @NotNull(message = "역할을 설정해주세요.")
        private Role role;
    }


    @Getter
    @Builder
    public static class RoleResponse {
        private final Long id;
        private final String name;
        private final String email;
        private final Role role;
        private final AdminStatus status;

        public static AdminUpdateDto.RoleResponse fromEntity(Admin admin){
            return AdminUpdateDto.RoleResponse.builder()
                    .id(admin.getId())
                    .name(admin.getName())
                    .email(admin.getEmail())
                    .role(admin.getRole())
                    .status(admin.getStatus())
                    .build();
        }

    }

    @Getter
    public static class StatusRequest {
        @NotNull(message = "상태를 설정해주세요.")
        private AdminStatus status;
    }


    @Getter
    @Builder
    public static class StatusResponse {
        private final Long id;
        private final String name;
        private final String email;
        private final Role role;
        private final AdminStatus status;

        public static AdminUpdateDto.StatusResponse fromEntity(Admin admin){
            return AdminUpdateDto.StatusResponse.builder()
                    .id(admin.getId())
                    .name(admin.getName())
                    .email(admin.getEmail())
                    .role(admin.getRole())
                    .status(admin.getStatus())
                    .build();
        }

    }

    @Getter
    public static class PasswordRequest {
        private String oldPassword;
        private String newPassword;
    }

    @Getter
    public static class PasswordResponse {
        private String message;
    }

}
