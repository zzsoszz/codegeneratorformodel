package com.bxtel.security5.auth;
import org.springframework.security.core.AuthenticationException;
public interface IAuthenticationManager {
    IAuthenticationResponse authenticate(AbsAuthenticationRequest authentication) throws AuthenticationException;
}