package sparta.spartateamproject1.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sparta.spartateamproject1.dto.CustomUserDetails;
import sparta.spartateamproject1.dto.LoginRequestDto;
import sparta.spartateamproject1.exception.AdminNotApprovedException;
import sparta.spartateamproject1.exception.InvalidLoginRequestException;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    private String useremailParameter = "email";

    @Nullable
    protected String obtainUseremail(HttpServletRequest request) {
        return request.getParameter(this.useremailParameter);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LoginRequestDto loginRequest =
                    objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);

            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            if (email == null || email.isBlank() ||
                    password == null || password.isBlank()) {

                throw new InvalidLoginRequestException("email과 password는 필수입니다");
            }

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(email, password);

            return authenticationManager.authenticate(authToken);

        } catch (InvalidLoginRequestException e) {
            throw e; // 아래 unsuccessfulAuthentication으로 전달

        } catch (IOException e) {
            throw new InvalidLoginRequestException("요청 JSON 형식이 올바르지 않습니다");
        }

        //        String email = obtainUseremail(request);
//        String password = obtainPassword(request);
//
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);
//
//        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Long id = customUserDetails.getId();
        String email = customUserDetails.getEmail();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String type = auth.getAuthority();

        String token = jwtUtil.createJwt(id, email, type, 60 * 60 * 1000L);

        response.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed) throws IOException {

        String message;
        int status;

        if (failed instanceof InvalidLoginRequestException) {
            status = 400;
            message = failed.getMessage();

        } else if (failed instanceof AdminNotApprovedException) {
            status = 403;
            message = failed.getMessage();

        } else {
            status = 401;
            message = "이메일 또는 비밀번호가 올바르지 않습니다";
        }

        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("""
    {
      "status": %d,
      "message": "%s"
    }
    """.formatted(status, message));
    }
}
