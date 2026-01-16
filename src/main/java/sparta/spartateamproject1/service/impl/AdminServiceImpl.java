package sparta.spartateamproject1.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.spartateamproject1.config.PasswordEncoder;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.entity.ApprovalResult;
import sparta.spartateamproject1.exception.*;
import sparta.spartateamproject1.repository.AdminRepository;
import sparta.spartateamproject1.service.AdminService;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.ErrorCode;
import sparta.spartateamproject1.type.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public SignUpDto.Response signup(SignUpDto.Request request) {
        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }

        Admin admin = Admin.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .status(AdminStatus.WAITING)
                .build();

        adminRepository.save(admin);

        return SignUpDto.Response.fromEntity(admin);
    }

    @Transactional
    public LoginSessionAttribute login(LoginRequestDto requestDto) {
        // 먼저 이메일로 유저를 찾는다.
        Admin admin = adminRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new LoginException("회원가입된 이메일이 아닙니다."));

        if (!admin.getStatus().equals(AdminStatus.ACTIVE)) {
            throw new LoginException("계정이 " + admin.getStatus().name() + " 상태입니다.");
        }

        if (passwordEncoder.matches(requestDto.getPassword(), admin.getPassword())) {
            return new LoginSessionAttribute(admin.getId(), admin.getEmail(), admin.getRole());
        } else {
            throw new LoginException("비밀번호가 잘못되었습니다.");
        }
    }

    @Override
    public FindSelfResponseDto findSelf(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));

        return FindSelfResponseDto.builder()
                .email(admin.getEmail())
                .name(admin.getName())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }

//    //관리자 전체 조회
//    @Override
//    public List<AdminGetDto> findAll(LoginSessionAttribute loginSessionAttribute, Pageable pageable, AdminGetDto.request) {
//
//        //TODO: 정렬 쿼리 DSL
//        //조회 가능한 값: 이 이메일름,, 가입일, role, status
//        //정렬기준(오름, 내림차순) 이름, 이메일 , 가입일
//
//
//        Page<Admin> admins = adminRepository.findAll(pageable);
//        return;
//    }

    public List<AdminGetDto.Response> findAll() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminGetDto.Response> dtos = new ArrayList<>();
        for (Admin admin : admins) {
            dtos.add(AdminGetDto.Response.fromEntity(admin));
        }
        return dtos;
    }

    //관리자 단건 조회
    @Override
    public AdminGetDto.Response findOne(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));

        return AdminGetDto.Response.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .role(admin.getRole())
                .status(admin.getStatus())
                .createdAt(admin.getCreatedAt())
                .approvedAt(admin.getApprovalResult() == null ? null : admin.getApprovalResult().getApprovedAt())
                .build();
    }

    //관리자 수정
    @Override
    @Transactional
    //id 는 슈퍼관리자의 아이디
    //adminId 는 수정할 관리자의 아이디
    public AdminUpdateDto.Response update(Long id, Long adminId, AdminUpdateDto.Request request) {
        //슈퍼관리자인지 확인
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));

        admin.updateAdmin(request.getName(), request.getEmail(), request.getPhoneNumber());
        return AdminUpdateDto.Response.fromEntity(admin);
    }

    //관리자 역할 변경
    //id 는 슈퍼관리자의 아이디
    //adminId 는 수정할 관리자의 아이디
    @Override
    @Transactional
    public AdminUpdateDto.RoleResponse updateRole(Long id, Long adminId, AdminUpdateDto.RoleRequest request) {
        //슈퍼관리자인지 확인
        Admin superAdmin = checkSuperAdmin(id);
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));
        admin.updateRole(request.getRole());

        return AdminUpdateDto.RoleResponse.fromEntity(admin);
    }

    //관리자 상태 변경
    //id 는 슈퍼관리자의 아이디
    //adminId 는 수정할 관리자의 아이디
    @Override
    @Transactional
    public AdminUpdateDto.StatusResponse updateStatus(Long id, Long adminId, AdminUpdateDto.StatusRequest request) {
        //슈퍼관리자인지 확인
        Admin superAdmin = checkSuperAdmin(id);
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));

        admin.updateStatus(request.getStatus());
        return AdminUpdateDto.StatusResponse.fromEntity(admin);
    }


    //관리자 삭제
    @Override
    @Transactional
    //id 는 슈퍼관리자의 아이디
    //adminId 는 삭제할 관리자의 아이디
    public void delete(Long id, Long adminId) {
        //이 아이디가 슈퍼관리자인지 확인
        Admin superAdmin = checkSuperAdmin(id);
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));
        if(admin.getRole().equals(Role.SUPER_ADMIN)){
            throw new ForbiddenException("슈퍼관리자는 삭제할 수 없습니다.");
        }
        adminRepository.deleteById(adminId);
    }

    //관리자 신청 승인
    @Override
    @Transactional
    //id 는 슈퍼관리자의 아이디
    //adminId 는 승인할 관리자의 아이디
    public AdminApprovedDto.ApprovedResponse approve(Long id, Long adminId) {
        //이 아이디가 슈퍼관리자인지 확인
        Admin superAdmin = checkSuperAdmin(id);
        //승인하려는 관리자 확인
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));

        ApprovalResult result = new ApprovalResult("", null, LocalDateTime.now(), true);
        admin.setApprovalResult(result);
        admin.updateStatus(AdminStatus.ACTIVE);

        return AdminApprovedDto.ApprovedResponse.fromEntity(admin);
    }

    //관리자 신청 거절
    @Override
    @Transactional
    //id 는 슈퍼관리자의 아이디
    //adminId 는 거절할 관리자의 아이디
    public AdminDeniedDto.DeniedResponse denied(Long id, Long adminId, AdminDeniedDto.DeniedRequest request) {
        //이 아이디가 슈퍼관리자인지 확인
        Admin superAdmin = checkSuperAdmin(id);
        //승인하려는 관리자 확인
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));

        ApprovalResult result = new ApprovalResult(request.deniedReason, LocalDateTime.now(), null, false);
        admin.setApprovalResult(result);
        admin.updateStatus(AdminStatus.DENIED);

        return AdminDeniedDto.DeniedResponse.fromEntity(admin);
    }

    //관리자 자신의 정보수정
    @Override
    @Transactional
    public UpdateSelfDto.Response updateSelf(Long id, UpdateSelfDto.Request request) {

        //정보 업데이트
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));
        admin.updateAdmin(request.getName(), request.getEmail(), request.getPhoneNumber());

        return UpdateSelfDto.Response.fromEntity(admin);
    }

    //관리자 비밀번호 변경 - 자기자신만 가능
    //id 는 비밀번호 변경을 요청한 관리자의 아이디
    //adminId 는 비밀번호가 바뀔 관리자의 아이디
    @Override
    @Transactional
    public AdminUpdateDto.PasswordResponse updatePassword(Long id, Long adminId, AdminUpdateDto.PasswordRequest request) {

        Admin requestAdmin = adminRepository.findById(id).orElseThrow(()-> new AdminNotFoundException("존재하지 않는 관리자입니다."));
        Admin targetAdmin = adminRepository.findById(adminId).orElseThrow(()-> new AdminNotFoundException("존재하지 않는 관리자입니다."));
        //요청자가 자기자신인지 확인
        //요청자가 자기자신 아니라면 오류발생
        if(!id.equals(adminId)){
            throw new ForbiddenException("권한이 없습니다.");
        }
        //현재 비밀번호가 맞는지 확인 후 맞으면 수정, 틀리면 오류
        if(!passwordEncoder.matches(request.getOldPassword(), targetAdmin.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 다릅니다.");
        }

        //바꿀 비밀번호와 현재 비밀번호가 같다면 오류
        if(passwordEncoder.matches(request.getNewPassword(), targetAdmin.getPassword())) {
            throw new SamePasswordException("동일한 비밀번호로 바꿀 수 없습니다.");
        }
        String password = passwordEncoder.encode(request.getNewPassword());
        targetAdmin.updatePassword(password);

        return AdminUpdateDto.PasswordResponse.success("비밀번호 변경이 완료되었습니다.");
    }



    //이 아이디가 슈퍼관리자인지 확인 후 권한이 없다면 throw
    public Admin checkSuperAdmin(Long id) {
        Admin superAdmin = adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));
        if(!superAdmin.getRole().equals(Role.SUPER_ADMIN)){
            throw new ForbiddenException("권한이 없습니다.");
        }
        return superAdmin;
    }

}
