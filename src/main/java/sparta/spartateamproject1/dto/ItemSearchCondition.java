package sparta.spartateamproject1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparta.spartateamproject1.type.ItemCategory;
import sparta.spartateamproject1.type.ItemStatus;

@Getter
@Setter
@NoArgsConstructor
public class ItemSearchCondition {
    // 검색 조건
    private String name;

    // 필터 조건
    private ItemCategory category;
    private ItemStatus status;

    // 페이징 
    private int pageNumber = 1;
    private int pageSize = 10;

    // 정렬
    private boolean asc = true;

    private String sortBy = "createdAt";
}
