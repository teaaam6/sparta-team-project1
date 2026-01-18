package sparta.spartateamproject1.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.type.AdminStatus;

import java.time.LocalDateTime;

public class AdminApprovedDto {


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
                    .approvedAt(admin.getApprovalResult() == null ? null : admin.getApprovalResult().getApprovedAt())
                    .build();
        }
    }
}
