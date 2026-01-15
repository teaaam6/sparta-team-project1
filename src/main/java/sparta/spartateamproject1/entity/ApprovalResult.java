package sparta.spartateamproject1.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalResult {
    private String deniedReason;
    private LocalDateTime deniedAt;
    private LocalDateTime approvedAt;
    private Boolean isApproved;



    // status 상태는 계속 바뀔 수 있고, 바뀔 때마다 시간 update
    // 승인되지 않은사람의 승인시간, 승인된 사람의 거절시간을 null로 두기
    public ApprovalResult approve() {

        return new ApprovalResult("", null, LocalDateTime.now(), true);
    }

    public ApprovalResult deny(String deniedReason) {

        return new ApprovalResult(deniedReason, LocalDateTime.now(), null, false);
    }

}
