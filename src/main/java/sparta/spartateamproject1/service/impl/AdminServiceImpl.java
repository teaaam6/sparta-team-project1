package sparta.spartateamproject1.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.spartateamproject1.config.PasswordEncoder;
import sparta.spartateamproject1.dto.SignUpDto;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.exception.CustomException;
import sparta.spartateamproject1.repository.AdminRepository;
import sparta.spartateamproject1.service.AdminService;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.ErrorCode;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
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
}
