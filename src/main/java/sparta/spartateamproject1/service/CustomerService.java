package sparta.spartateamproject1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.spartateamproject1.dto.*;

import java.util.List;

public interface CustomerService {

    //전체조회
    Page<CustomerGetDto.Response> findAll(Long id, CustomerSearchCondition dto, Pageable pageable);

    //세부조회
//    CustomerGetDto.Response findOne(Long id);
//    //수정
    CustomerUpdateDto.Response update(Long id, Long customerId, CustomerUpdateDto.Request request);

    //    //삭제
//    void delete(Long id, Long adminId);
    // 고객 상태 변경
    CustomerUpdateDto.StatusResponse updateStatus(Long id, Long customerId, CustomerUpdateDto.StatusRequest request);

}
