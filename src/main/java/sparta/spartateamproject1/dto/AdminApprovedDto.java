package sparta.spartateamproject1.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.type.AdminStatus;

import java.time.LocalDateTime;

public class AdminApprovedDto {

    //슈퍼 관리자가 pathvariable에 있는 admin을 아래 status로 변경하고 싶다고 서버에 request
    //승인 request


    @Getter
    @Builder
    public static class ApprovedResponse {
        private final Long id;
        private final String name;
        private final AdminStatus status;
        private final LocalDateTime approvedAt;

        public static AdminApprovedDto.ApprovedResponse fromEntity(Admin admin){
            return AdminApprovedDto.ApprovedResponse.builder()
                    .id(admin.getId())
                    .name(admin.getName())
                    .status(admin.getStatus())
                    .approvedAt(admin.getApprovalResult().getApprovedAt())
                    .build();
        }
    }
}
