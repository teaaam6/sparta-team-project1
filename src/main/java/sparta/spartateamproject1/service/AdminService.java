package sparta.spartateamproject1.service;

import sparta.spartateamproject1.dto.LoginRequestDto;
import sparta.spartateamproject1.dto.LoginSessionAttribute;
import sparta.spartateamproject1.dto.SignUpDto;

public interface AdminService {
    SignUpDto.Response signup(SignUpDto.Request request);

    LoginSessionAttribute login(LoginRequestDto requestDto);
}
