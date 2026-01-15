package sparta.spartateamproject1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sparta.spartateamproject1.type.AdminStatus;
import sparta.spartateamproject1.type.Role;

import java.time.LocalDateTime;

@Getter
@Builder
public class FindSelfResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Role role;
    private AdminStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime activeAt;
}
