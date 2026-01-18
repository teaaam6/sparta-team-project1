package sparta.spartateamproject1.dev;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sparta.spartateamproject1.config.PasswordEncoder;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.entity.ApprovalResult;
import sparta.spartateamproject1.repository.AdminRepository;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.Role;

@Component
@ConditionalOnProperty(
    name = "app.add-test-admins",
    havingValue = "true",
    matchIfMissing = false
)
@RequiredArgsConstructor
public class TestAdminAdder implements CommandLineRunner {
    
    private final AdminRepository adminRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        ApprovalResult result = new ApprovalResult("", null, LocalDateTime.now(), true);

        Admin superAdmin = Admin.builder()
            .name("super")
            .email("super@gmail.com")
            .password(passwordEncoder.encode("super1234"))
            .phoneNumber("010-1111-2222")
            .role(Role.SUPER_ADMIN)
            .status(AdminStatus.ACTIVE)
            .approvalResult(result)
            .build();

        adminRepository.save(superAdmin);

        // 활성 상태인 어드민 계정 생성
        Admin activeAdmin = Admin.builder()
                .name("activeUser")
                .email("active1@gmail.com")
                .password(passwordEncoder.encode("active1"))
                .phoneNumber("010-1234-5678")
                .role(Role.ADMIN)
                .status(AdminStatus.ACTIVE)
                .approvalResult(result)
                .build();
        adminRepository.save(activeAdmin);
    }
}
