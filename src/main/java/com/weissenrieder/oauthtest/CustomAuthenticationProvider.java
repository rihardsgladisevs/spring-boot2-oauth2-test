package com.weissenrieder.oauthtest;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        Object username = authentication.getPrincipal();
        Object password = authentication.getCredentials();
        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    }
}
