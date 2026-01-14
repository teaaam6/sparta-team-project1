package sparta.spartateamproject1.service;

import org.springframework.data.domain.Pageable;
import sparta.spartateamproject1.dto.*;

import java.util.List;

public interface AdminService {
    SignUpDto.Response signup(SignUpDto.Request request);

    LoginSessionAttribute login(LoginRequestDto requestDto);

    FindSelfResponseDto findSelf(Long id);

    //Todo
    //List<AdminGetDto> findAll(LoginSessionAttribute loginSessionAttribute, Pageable pageable, AdminGetDto request);

    List<AdminGetDto.Response> findAll(LoginSessionAttribute loginSessionAttribute);

    AdminGetDto.Response findOne(Long id);

    AdminUpdateDto.Response update(Long adminId, AdminUpdateDto.Request request);
}
