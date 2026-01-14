package sparta.spartateamproject1.dto;

import lombok.Getter;
import sparta.spartateamproject1.type.Role;

@Getter
public class LoginSessionAttribute {
    private Long id;
    private String email;
    private Role role;

    public LoginSessionAttribute(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
