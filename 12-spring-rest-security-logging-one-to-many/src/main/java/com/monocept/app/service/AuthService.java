package com.monocept.app.service;

import com.monocept.app.dto.LoginDto;
import com.monocept.app.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
