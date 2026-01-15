package sparta.spartateamproject1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparta.spartateamproject1.type.CustomerStatus;

@Getter
@Setter
@NoArgsConstructor
public class CustomerSearchCondition {
    // 검색 조건
    private String name;
    private String email;
    private CustomerStatus status;

    // 페이징 및 정렬 파라미터
    private int pageNumber = 1;
    private int pageSize = 10;
    private String sortBy = "createdAt";
    private boolean asc = true;
}
