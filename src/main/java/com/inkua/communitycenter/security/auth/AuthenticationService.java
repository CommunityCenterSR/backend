package com.inkua.communitycenter.security.auth;

import com.inkua.communitycenter.entity.User;
import com.inkua.communitycenter.repository.IUserRepository;
import com.inkua.communitycenter.security.config.JwtService;
import com.inkua.communitycenter.security.token.Token;
import com.inkua.communitycenter.security.token.TokenRepository;
import com.inkua.communitycenter.security.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final IUserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .name(request.getNombre())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken((UserDetails) user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    var user = repository.findOneByEmail(request.getEmail())
        .orElseThrow();

    var jwtToken = jwtService.generateToken((UserDetails) user);

    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);

    return AuthenticationResponse.builder()
        .token(jwtToken)
        .userId(Math.toIntExact(user.getId()))// este lo cree yo para enviar la id del usuario actual
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUsuario(Math.toIntExact(user.getId()));
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
