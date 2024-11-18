package com.spring.course.auth_server.controllers;

import com.spring.course.auth_server.dtos.TokenDTO;
import com.spring.course.auth_server.dtos.UserDTO;
import com.spring.course.auth_server.services.AuthService;
import jakarta.ws.rs.HeaderParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "login") // password: secret
    public ResponseEntity<TokenDTO> jwtCreate(@RequestBody UserDTO user) {
        return ResponseEntity.ok(authService.login(user));
    }

    @PostMapping(path = "jwt")
    public ResponseEntity<TokenDTO> jwtValidate(@RequestHeader String accessToken) {
        return ResponseEntity.ok(authService.validate(TokenDTO.builder().accessToken(accessToken).build()));
    }
}
