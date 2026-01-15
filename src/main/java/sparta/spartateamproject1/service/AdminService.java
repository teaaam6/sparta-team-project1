package sparta.spartateamproject1.service;

import sparta.spartateamproject1.dto.*;

import java.util.List;

public interface AdminService {
    SignUpDto.Response signup(SignUpDto.Request request);

    LoginSessionAttribute login(LoginRequestDto requestDto);

    FindSelfResponseDto findSelf(Long id);

    //Todo
    //List<AdminGetDto> findAll(LoginSessionAttribute loginSessionAttribute, Pageable pageable, AdminGetDto request);

    List<AdminGetDto.Response> findAll();

    AdminGetDto.Response findOne(Long id);

    AdminUpdateDto.Response update(Long id, Long adminId, AdminUpdateDto.Request request);

    void delete(Long id, Long adminId);

    AdminApprovedDto.ApprovedResponse approve(Long id, Long adminId);

    AdminDeniedDto.DeniedResponse denied(Long id, Long adminId, AdminDeniedDto.DeniedRequest request);

    UpdateSelfDto.Response updateSelf(Long id, UpdateSelfDto.Request request);
}
