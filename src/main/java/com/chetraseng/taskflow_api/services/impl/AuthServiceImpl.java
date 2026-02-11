package com.chetraseng.taskflow_api.services.impl;

import com.chetraseng.taskflow_api.dto.requests.LoginRequest;
import com.chetraseng.taskflow_api.dto.requests.UserRequest;
import com.chetraseng.taskflow_api.dto.responses.AuthResponse;
import com.chetraseng.taskflow_api.dto.responses.UserResponse;
import com.chetraseng.taskflow_api.exception.EmailExistException;
import com.chetraseng.taskflow_api.mappers.UserMapper;
import com.chetraseng.taskflow_api.models.Role;
import com.chetraseng.taskflow_api.models.UserModel;
import com.chetraseng.taskflow_api.repositories.UserRepository;
import com.chetraseng.taskflow_api.services.AuthService;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    private final JwtEncoder accessTokenJwtEncoder;
    private final JwtEncoder refreshTokenEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse signUp(UserRequest userRequest) {


        boolean email = userRepository.existsByEmail(userRequest.email());

        if (email) {
            throw new EmailExistException("Username is already in use");
        }
        UserModel userModel = new UserModel();
        userModel.setEmail(userRequest.email());
        userModel.setPassword(passwordEncoder.encode(userRequest.password()));
        userModel.setLastname(userRequest.lastname());
        userModel.setFirstname(userRequest.firstname());
        userModel.setRole(Role.ADMIN);

        userModel = userRepository.save(userModel);
        return userMapper.toUserResponse(userModel);


    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(role -> role.startsWith("ROLE_")) // ✅ Only include your custom roles
                .collect(Collectors.joining(" "));

        Instant now = Instant.now();

        // Access Token
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(authentication.getName())
                .subject("Access Token")
                .issuer("taskflow-api")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES)) // ✅ Adjust expiration
                .audience(List.of("NextJs", "Android", "IOS"))
                .claim("role", scope)
                .claim("scope", scope)
                .build();

        // Refresh Token
        JwtClaimsSet jwtRefreshClaimsSet = JwtClaimsSet.builder()
                .id(authentication.getName())
                .subject("Refresh Token")
                .issuer("taskflow-api")
                .issuedAt(now)
                .expiresAt(now.plus(7, ChronoUnit.DAYS))
                .audience(List.of("NextJs", "Android", "IOS"))
                .claim("role", scope)
                .claim("scope", scope)
                .build();

        String accessToken = accessTokenJwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        String refreshToken = refreshTokenEncoder.encode(JwtEncoderParameters.from(jwtRefreshClaimsSet)).getTokenValue();

        return AuthResponse.builder()
                .TokenType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
