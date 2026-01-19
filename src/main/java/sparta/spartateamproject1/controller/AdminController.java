package sparta.spartateamproject1.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.spartateamproject1.dto.*;
import sparta.spartateamproject1.jwt.JWTUtil;
import sparta.spartateamproject1.service.AdminService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final JWTUtil jwtUtil;

    @PostMapping("/admins")
    public ResponseEntity<?> signup(
            @Valid @RequestBody SignUpDto.Request request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.signup(request));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest httpServletRequest) {
//        LoginSessionAttribute loginSessionAttribute = adminService.login(requestDto);
//
//        // 새로운 세션 생성
//        HttpSession session = httpServletRequest.getSession(true);
//        session.setAttribute("adminSession", loginSessionAttribute);
//        session.setMaxInactiveInterval(30 * 60);
//
//        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {
//        // 현재 세션 제거
//        httpServletRequest.getSession().invalidate();
//        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 완료");
//    }

    // @SessionAttribute의 required() default true 이므로 밑의 경로에 로그인하지 않은 사용자가 접근할 수 없다.
    // 오직 로그인에 성공하여 sessionAttribute를 가진 사용자 만이 접근 가능하다.
    @GetMapping("/self")
    public ResponseEntity<?> self(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(adminService.findSelf(id));
    }

    //관리자 전체 조회
    @GetMapping("/admins")
    public Page<AdminGetAllDto.Response> getAdmins(
            @RequestHeader("Authorization") String token,
            @ModelAttribute AdminSearchCondition conditionDto) {
        int pageNumber = conditionDto.getPageNumber() - 1;
        int pageSize = conditionDto.getPageSize();
        boolean asc = conditionDto.isAsc();
        String sortBy = conditionDto.getSortBy();

        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        // JPA는 0부터 시작
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        return adminService.findAll(id, conditionDto, pageRequest);
    }

//
//
//    @GetMapping("/admins")
//    public ResponseEntity<List<AdminGetDto.Response>> getAll() {
//        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
//
//    }

    //관리자 단건 조회
    @GetMapping("/admins/{adminId}")
    public ResponseEntity<AdminGetDto.Response> getOne(
            @RequestHeader("Authorization") String token,
            @PathVariable Long adminId) {

        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(adminService.findOne(id, adminId));

    }

    //관리자 수정
    @PatchMapping("/admins/{adminId}")
    public ResponseEntity<AdminUpdateDto.Response> update(
            @RequestHeader("Authorization") String token,
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateDto.Request request) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(adminService.update(id, adminId, request));
    }

    //관리자 역할 변경
    @PatchMapping("/admins/{adminId}/role")
    public ResponseEntity<AdminUpdateDto.RoleResponse> updateRole(
            @RequestHeader("Authorization") String token,
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateDto.RoleRequest request) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateRole(id, adminId, request));
    }

    //관리자 상태 변경
    @PatchMapping("/admins/{adminId}/status")
    public ResponseEntity<AdminUpdateDto.StatusResponse> updateStatus(
            @RequestHeader("Authorization") String token,
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateDto.StatusRequest request) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateStatus(id, adminId, request));
    }


    //관리자 삭제
    @DeleteMapping("/admins/{adminId}")
    public ResponseEntity<Void> delete(
            @RequestHeader("Authorization") String token,
            @PathVariable Long adminId) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        adminService.delete(id, adminId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //관리자 신청 승인
    //adminId: 승인할 관리자
    @PostMapping("/admins/{adminId}/approve")
    public ResponseEntity<AdminApprovedDto.ApprovedResponse> approve(
            @RequestHeader("Authorization") String token,
            @PathVariable Long adminId) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(adminService.approve(id, adminId));
    }

    //관리자 신청 거부
    //adminId: 거절할 관리자
    @PostMapping("/admins/{adminId}/deny")
    public ResponseEntity<AdminDeniedDto.DeniedResponse> denied(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody AdminDeniedDto.DeniedRequest request,
            @PathVariable Long adminId) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(adminService.denied(id, adminId, request));
    }

    //관리자 자신정보 수정
    @PatchMapping("/admins/self")
    public ResponseEntity<UpdateSelfDto.Response> updateSelf(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody UpdateSelfDto.Request request) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateSelf(id, request));
    }

    //관리자 비밀번호 변경
    @PatchMapping("/admins/{adminId}/password")
    public ResponseEntity<AdminUpdateDto.PasswordResponse> updatePassword(
            @RequestHeader("Authorization") String token,
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateDto.PasswordRequest request
    ) {
        token = token.replace("Bearer ", "").trim();

        Long id = jwtUtil.getId(token);

        return ResponseEntity.status(HttpStatus.OK).body(adminService.updatePassword(id, adminId, request));

    }

}
