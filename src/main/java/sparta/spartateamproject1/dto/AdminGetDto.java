package sparta.spartateamproject1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.Role;

import java.time.LocalDateTime;

public class AdminGetDto {

    @Getter
    public static class Request {
        private Long id;
        String name;
        String email;
        String role;
        String status;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String email;
        private String phoneNumber;
        private Role role;
        private AdminStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime approvedAt;

        public static AdminGetDto.Response fromEntity(Admin admin){
            return AdminGetDto.Response.builder()
                    .id(admin.getId())
                    .name(admin.getName())
                    .email(admin.getEmail())
                    .phoneNumber(admin.getPhoneNumber())
                    .role(admin.getRole())
                    .status(admin.getStatus())
                    .createdAt(admin.getCreatedAt())
                    .approvedAt(admin.getApprovalResult() == null ? null : admin.getApprovalResult().getApprovedAt())
                    .build();
        }
    }
}
