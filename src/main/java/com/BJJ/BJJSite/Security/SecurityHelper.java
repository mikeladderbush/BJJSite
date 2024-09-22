package com.BJJ.BJJSite.Security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityHelper {
    
    public static void setupSecurityContext(String username, String password, String... authorities) {
        List<SimpleGrantedAuthority> grantedAuthorities = Arrays.stream(authorities)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        User principal = new User(username, password, grantedAuthorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            principal, password, grantedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
