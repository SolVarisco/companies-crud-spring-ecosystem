package com.spring.course.auth_server.services;

import com.spring.course.auth_server.dtos.TokenDTO;
import com.spring.course.auth_server.dtos.UserDTO;
import com.spring.course.auth_server.entities.UserEntity;
import com.spring.course.auth_server.helpers.JwtHelper;
import com.spring.course.auth_server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@AllArgsConstructor
public class AuthServiceImpl implements  AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    @Override
    public TokenDTO login(UserDTO user) {
        final UserEntity entity = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username"));
        validPassword(user, entity);
        return TokenDTO.builder()
                .accessToken(jwtHelper.createToken(entity.getUsername()))
                .build();
    }

    @Override
    public TokenDTO validate(TokenDTO token) {
        if(jwtHelper.validateToken(token.getAccessToken()))
            return token;
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
    }

    private void validPassword(UserDTO dto, UserEntity entity) {
        if(!passwordEncoder.matches(dto.getPassword(), entity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }
    }
}
