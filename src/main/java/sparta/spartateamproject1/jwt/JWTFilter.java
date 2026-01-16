package sparta.spartateamproject1.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import sparta.spartateamproject1.dto.CustomUserDetails;
import sparta.spartateamproject1.entity.Admin;
import sparta.spartateamproject1.entity.ApprovalResult;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.Role;

import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")){
            System.out.println("token null");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)){
            System.out.println("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        String email = jwtUtil.getEmail(token);
        String type = jwtUtil.getType(token);
        Role role = Role.valueOf(type);

        System.out.println("Extracted email: " + email);
        System.out.println("Extracted role: " + role);  // 확인용 로그

        Admin admin = Admin.builder()
                .name("tempname")
                .email(email)
                .password("temppassword")
                .phoneNumber("tempphone")
                .role(role)
                .status(null)
                .approvalResult(null)
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(admin);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
