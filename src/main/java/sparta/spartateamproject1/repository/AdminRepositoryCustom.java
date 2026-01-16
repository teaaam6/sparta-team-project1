package sparta.spartateamproject1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.spartateamproject1.dto.AdminGetAllDto;
import sparta.spartateamproject1.dto.AdminGetDto;
import sparta.spartateamproject1.dto.AdminSearchCondition;

public interface AdminRepositoryCustom {
    Page<AdminGetAllDto.Response> findByOption(AdminSearchCondition dto, Pageable pageable);

}
