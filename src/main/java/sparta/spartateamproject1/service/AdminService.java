package sparta.spartateamproject1.service;

import sparta.spartateamproject1.dto.SignUpDto;

public interface AdminService {
    SignUpDto.Response signup(SignUpDto.Request request);
}
