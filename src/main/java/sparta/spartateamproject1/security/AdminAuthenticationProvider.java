package sparta.spartateamproject1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sparta.spartateamproject1.dto.CustomUserDetails;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.exception.AdminNotApprovedException;
import sparta.spartateamproject1.repository.AdminRepository;
import sparta.spartateamproject1.type.AdminStatus;

@Component
@RequiredArgsConstructor
public class AdminAuthenticationProvider implements AuthenticationProvider {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String email = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BadCredentialsException("이메일 또는 비밀번호가 올바르지 않습니다")
                );

        if (!passwordEncoder.matches(rawPassword, admin.getPassword())) {
            throw new BadCredentialsException("이메일 또는 비밀번호가 올바르지 않습니다");
        }

        // 🔥 여기서 상태 체크
        if (admin.getStatus() != AdminStatus.ACTIVE) {
            throw new AdminNotApprovedException("승인되지 않은 관리자입니다");
        }

        CustomUserDetails userDetails = new CustomUserDetails(admin);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}