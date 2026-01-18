package sparta.spartateamproject1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sparta.spartateamproject1.dto.*;

import java.util.List;

public interface AdminService {
    SignUpDto.Response signup(SignUpDto.Request request);

    LoginSessionAttribute login(LoginRequestDto requestDto);

    FindSelfResponseDto findSelf(Long id);

    //Todo
    //List<AdminGetDto> findAll(LoginSessionAttribute loginSessionAttribute, Pageable pageable, AdminGetDto request);

    Page<AdminGetAllDto.Response> findAll(Long id, AdminSearchCondition conditionDto, Pageable pageable);

    AdminGetDto.Response findOne(Long id, Long adminId);

    AdminUpdateDto.Response update(Long id, Long adminId, AdminUpdateDto.Request request);

    void delete(Long id, Long adminId);

    AdminApprovedDto.ApprovedResponse approve(Long id, Long adminId);

    AdminDeniedDto.DeniedResponse denied(Long id, Long adminId, AdminDeniedDto.DeniedRequest request);

    UpdateSelfDto.Response updateSelf(Long id, UpdateSelfDto.Request request);

    AdminUpdateDto.RoleResponse updateRole(Long id, Long adminId, AdminUpdateDto.RoleRequest request);

    AdminUpdateDto.StatusResponse updateStatus(Long id, Long adminId, AdminUpdateDto.StatusRequest request);

    AdminUpdateDto.PasswordResponse updatePassword(Long id, Long adminId, AdminUpdateDto.PasswordRequest request);
}
