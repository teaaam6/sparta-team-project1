package sparta.spartateamproject1.service;

import sparta.spartateamproject1.dto.AdminGetDto;
import sparta.spartateamproject1.dto.AdminUpdateDto;
import sparta.spartateamproject1.dto.CustomerGetDto;
import sparta.spartateamproject1.dto.LoginSessionAttribute;

import java.util.List;

public interface CustomerService {

    //전체조회
    List<CustomerGetDto.Response> findAll(LoginSessionAttribute loginSessionAttribute);
    //세부조회
    CustomerGetDto.Response findOne(Long id);
    //수정
    CustomerGetDto.Response update(Long id, Long adminId, AdminUpdateDto.Request request);
    //삭제
    void delete(Long id, Long adminId);
}
