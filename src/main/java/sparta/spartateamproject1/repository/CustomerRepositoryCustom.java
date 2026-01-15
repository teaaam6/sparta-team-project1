package sparta.spartateamproject1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.spartateamproject1.dto.CustomerGetDto;
import sparta.spartateamproject1.dto.CustomerSearchCondition;

public interface CustomerRepositoryCustom {

    Page<CustomerGetDto.Response> findByOption(CustomerSearchCondition dto, Pageable pageable);
}
