package sparta.spartateamproject1.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.spartateamproject1.config.PasswordEncoder;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.exception.AdminNotFoundException;
import sparta.spartateamproject1.exception.CustomException;
import sparta.spartateamproject1.exception.IllegalNumberException;
import sparta.spartateamproject1.exception.LoginException;
import sparta.spartateamproject1.repository.AdminRepository;
import sparta.spartateamproject1.service.AdminService;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.ErrorCode;

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
        System.out.println(admin.getRole());

        if (passwordEncoder.matches(requestDto.getPassword(), admin.getPassword())) {
            return new LoginSessionAttribute(admin.getId(), admin.getEmail(), admin.getRole());
        } else {
            throw new LoginException("비밀번호가 잘못되었습니다.");
        }
    }

    @Override
    public FindSelfResponseDto findSelf(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException("존재하지 않는 관리자입니다."));
        LocalDateTime activeAt = admin.getApprovalResult() == null ? null : admin.getApprovalResult().getApprovedAt();

        return FindSelfResponseDto.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .status(admin.getStatus())
                .role(admin.getRole())
                .name(admin.getName())
                .phoneNumber(admin.getPhoneNumber())
                .createdAt(admin.getCreatedAt())
                .activeAt(activeAt)
                .updatedAt(admin.getModifiedAt())
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

    public List<AdminGetDto.Response> findAll(LoginSessionAttribute loginSessionAttribute) {
        List<Admin> admins = adminRepository.findAll();
        List<AdminGetDto.Response> dtos = new ArrayList<>();
        for (Admin admin : admins) {
            dtos.add(
                    AdminGetDto.Response.builder()
                            .id(admin.getId())
                            .name(admin.getName())
                            .email(admin.getEmail())
                            .phoneNumber(admin.getPhoneNumber())
                            .role(admin.getRole())
                            .status(admin.getStatus())
                            .createdAt(admin.getCreatedAt())
                            .approvedAt(admin.getApprovalResult() == null ? null : admin.getApprovalResult().getApprovedAt())
                            .build()
            );
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




}
