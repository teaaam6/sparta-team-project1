package sparta.spartateamproject1.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class ApprovalResult {
    private String deniedReason;
    private LocalDateTime deniedAt;
    private LocalDateTime approvedAt;
    private Boolean isApproved;
}
