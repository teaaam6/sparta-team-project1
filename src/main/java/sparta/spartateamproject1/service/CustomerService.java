package sparta.spartateamproject1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.spartateamproject1.dto.*;

import java.util.List;

public interface CustomerService {

    //전체조회
    Page<CustomerGetDto.Response> findAll(LoginSessionAttribute loginSessionAttribute, CustomerSearchCondition dto, Pageable pageable);
    //세부조회
//    CustomerGetDto.Response findOne(Long id);
//    //수정
//    CustomerGetDto.Response update(Long id, Long adminId, AdminUpdateDto.Request request);
//    //삭제
//    void delete(Long id, Long adminId);
}
