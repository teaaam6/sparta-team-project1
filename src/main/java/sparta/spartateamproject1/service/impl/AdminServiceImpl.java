package sparta.spartateamproject1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.spartateamproject1.config.PasswordEncoder;
import sparta.spartateamproject1.dto.FindSelfResponseDto;
import sparta.spartateamproject1.dto.LoginRequestDto;
import sparta.spartateamproject1.dto.LoginSessionAttribute;
import sparta.spartateamproject1.dto.SignUpDto;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.exception.AdminNotFoundException;
import sparta.spartateamproject1.exception.CustomException;
import sparta.spartateamproject1.exception.LoginException;
import sparta.spartateamproject1.repository.AdminRepository;
import sparta.spartateamproject1.service.AdminService;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.ErrorCode;
import sparta.spartateamproject1.type.Role;

import java.time.LocalDateTime;

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
}
