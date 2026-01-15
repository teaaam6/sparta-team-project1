package sparta.spartateamproject1.dev;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    name = "app.add-test-super-admin",
    havingValue = "true",
    matchIfMissing = false
)
@RequiredArgsConstructor
public class TestSuperAdminAdder implements CommandLineRunner {
    
    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        ApprovalResult result = new ApprovalResult();
        result.setApprovedAt(LocalDateTime.now());
        result.setIsApproved(true);

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
    }
}
