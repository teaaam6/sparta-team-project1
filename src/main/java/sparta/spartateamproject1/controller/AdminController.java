package sparta.spartateamproject1.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.service.AdminService;

import java.util.List;

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

        // 새로운 세션 생성
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("adminSession", loginSessionAttribute);
        session.setMaxInactiveInterval(30 * 60);

        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {
        // 현재 세션 제거
        httpServletRequest.getSession().invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 완료");
    }

    // @SessionAttribute의 required() default true 이므로 밑의 경로에 로그인하지 않은 사용자가 접근할 수 없다.
    // 오직 로그인에 성공하여 sessionAttribute를 가진 사용자 만이 접근 가능하다.
    @GetMapping("/self")
    public ResponseEntity<?> self(@SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findSelf(loginSessionAttribute.getId()));
    }

    //관리자 전체 조회
    //TODO: 조건별 검색
//    @GetMapping("/admins")
//    public ResponseEntity<List<AdminGetDto>> getAll(
//            @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestBody(required = false) AdminGetDto.Request request
//    ) {
//        //페이지 검증
//        if (page < 0 || size < 0) {
//            throw new IllegalNumberException("잘못된 페이지 번호입니다.");
//        }
//        Pageable pageable = PageRequest.of(page, size);
//        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll(loginSessionAttribute, pageable, request));
//    }
    @GetMapping("/admins")
    public ResponseEntity<List<AdminGetDto.Response>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());

    }

    //관리자 단건 조회
    @GetMapping("/admins/{adminId}")
    public ResponseEntity<AdminGetDto.Response> getOne(
            @PathVariable Long adminId) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findOne(adminId));

    }

    //관리자 수정
    @PatchMapping("/admins/{adminId}")
    public ResponseEntity<AdminUpdateDto.Response> update(
            @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateDto.Request request) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.update(loginSessionAttribute.getId(), adminId, request));
    }

    //관리자 역할 변경
    @PatchMapping("/admins/{adminId}/role")
    public ResponseEntity<AdminUpdateDto.RoleResponse> updateRole(
            @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateDto.RoleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateRole(loginSessionAttribute.getId(), adminId, request));
    }

    //관리자 상태 변경
    @PatchMapping("/admins/{adminId}/status")
    public ResponseEntity<AdminUpdateDto.StatusResponse> updateStatus(
            @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateDto.StatusRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateStatus(loginSessionAttribute.getId(), adminId, request));
    }


    //관리자 삭제
    @DeleteMapping("/admins/{adminId}")
    public ResponseEntity<Void> delete(
            @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
            @PathVariable Long adminId) {
        adminService.delete(loginSessionAttribute.getId(), adminId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //관리자 신청 승인
    //adminId: 승인할 관리자
    @PostMapping("/admins/{adminId}/approve")
    public ResponseEntity<AdminApprovedDto.ApprovedResponse> approve(
            @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
            @PathVariable Long adminId) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.approve(loginSessionAttribute.getId(), adminId));
    }

    //관리자 신청 거부
    //adminId: 거절할 관리자
    @PostMapping("/admins/{adminId}/deny")
    public ResponseEntity<AdminDeniedDto.DeniedResponse> denied(
            @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
            @RequestBody AdminDeniedDto.DeniedRequest request,
            @PathVariable Long adminId) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.denied(loginSessionAttribute.getId(), adminId, request));
    }

    //관리자 자신정보 수정
    @PatchMapping("/admins/self")
    public ResponseEntity<UpdateSelfDto.Response> updateSelf(
            @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
            @RequestBody UpdateSelfDto.Request request) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateSelf(loginSessionAttribute.getId(), request));
    }

    //관리자 비밀번호 변경
    @PatchMapping("/admins/{adminId}/password")
    public ResponseEntity<AdminUpdateDto.PasswordResponse> updatePassword(
            @SessionAttribute(name = "adminSession") LoginSessionAttribute loginSessionAttribute,
            @PathVariable Long adminId,
            @RequestBody AdminUpdateDto.PasswordRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updatePassword(loginSessionAttribute.getId(), adminId, request));

    }

}
