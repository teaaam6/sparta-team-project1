package sparta.spartateamproject1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.signup(request));
    }
}
