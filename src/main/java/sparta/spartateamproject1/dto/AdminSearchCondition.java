package sparta.spartateamproject1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.CustomerStatus;
import sparta.spartateamproject1.type.Role;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AdminSearchCondition {
    // 검색 조건
    // 정렬 기준: 이름, 이메일, createdAt
    private String name;
    private String email;
    private AdminStatus status;
    private Role role;
    private LocalDateTime createdAt;

    // 페이징 및 정렬 파라미터
    private int pageNumber = 1;
    private int pageSize = 10;
    private String sortBy = "createdAt";
    private boolean asc = true;
}
