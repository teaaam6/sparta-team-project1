package sparta.spartateamproject1.service;

import sparta.spartateamproject1.dto.*;

import java.util.List;

public interface AdminService {
    SignUpDto.Response signup(SignUpDto.Request request);

    LoginSessionAttribute login(LoginRequestDto requestDto);

    FindSelfResponseDto findSelf(String token);

    //Todo
    //List<AdminGetDto> findAll(LoginSessionAttribute loginSessionAttribute, Pageable pageable, AdminGetDto request);

    List<AdminGetDto.Response> findAll();

    AdminGetDto.Response findOne(Long id);

    AdminUpdateDto.Response update(String token, Long adminId, AdminUpdateDto.Request request);

    void delete(String token, Long adminId);

    AdminApprovedDto.ApprovedResponse approve(String token, Long adminId);

    AdminDeniedDto.DeniedResponse denied(String token, Long adminId, AdminDeniedDto.DeniedRequest request);

    UpdateSelfDto.Response updateSelf(String token, UpdateSelfDto.Request request);
}
