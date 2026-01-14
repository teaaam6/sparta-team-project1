package sparta.spartateamproject1.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.spartateamproject1.dto.LoginRequestDto;
import sparta.spartateamproject1.dto.LoginSessionAttribute;
import sparta.spartateamproject1.dto.SignUpDto;
import sparta.spartateamproject1.service.AdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/admins")
    public ResponseEntity<?> signup(
            @Valid @RequestBody SignUpDto.Request request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest httpServletRequest) {
        LoginSessionAttribute loginSessionAttribute = adminService.login(requestDto);

        // 기존 세션 있으면 제거
        httpServletRequest.getSession().invalidate();
        // 새로운 세션 생성
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("adminSession", loginSessionAttribute);
        session.setMaxInactiveInterval(30 * 60);

        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }

}
