package sparta.spartateamproject1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.type.AdminStatus;

import java.time.LocalDateTime;

public class AdminDeniedDto {
    //슈퍼 관리자가 pathvariable에 있는 admin을 아래 status로 변경하고 싶다고 서버에 request
    //거절 request
    @Getter
    public static class DeniedRequest {

        @NotBlank(message = "승인 거절 사유를 작성해주세요.")
        public String deniedReason;

    }



    @Getter
    @Builder
    public static class DeniedResponse {
        private final Long id;
        private final String name;
        private final AdminStatus status;
        private final LocalDateTime deniedAt;
        private final String deniedReason;

        public static AdminDeniedDto.DeniedResponse fromEntity(Admin admin){
            return AdminDeniedDto.DeniedResponse.builder()
                    .id(admin.getId())
                    .name(admin.getName())
                    .status(admin.getStatus())
                    .deniedAt(admin.getApprovalResult().getDeniedAt())
                    .deniedReason(admin.getApprovalResult().getDeniedReason())
                    .build();
        }
    }
}
