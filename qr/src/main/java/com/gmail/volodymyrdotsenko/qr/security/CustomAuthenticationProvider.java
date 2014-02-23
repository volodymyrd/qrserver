package com.gmail.volodymyrdotsenko.qr.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.gmail.volodymyrdotsenko.qr.domain.UserToken;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		// String name = authentication.getName();
		String token = authentication.getCredentials().toString();
		
		if (token != null && !token.isEmpty()) {
			UserToken uToken = UserToken.findUserToken(token);

			if (uToken == null)
				return null;

			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			//grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
			Authentication auth = new UsernamePasswordAuthenticationToken(
					uToken.getUser().getUserName(), token, grantedAuths);
			return auth;
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}