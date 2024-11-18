package com.spring.course.auth_server.services;

import com.spring.course.auth_server.dtos.TokenDTO;
import com.spring.course.auth_server.dtos.UserDTO;

public interface AuthService {

    TokenDTO login(UserDTO user);
    TokenDTO validate(TokenDTO token);
}
