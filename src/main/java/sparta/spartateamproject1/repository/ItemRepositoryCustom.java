package sparta.spartateamproject1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.spartateamproject1.dto.ItemGetPageDto;
import sparta.spartateamproject1.dto.ItemSearchCondition;

public interface ItemRepositoryCustom {

    Page<ItemGetPageDto.Response> findByOption(ItemSearchCondition dto, Pageable pageable);
}
