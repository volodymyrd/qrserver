package com.gmail.volodymyrdotsenko.qr.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.volodymyrdotsenko.qr.domain.UserData;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	public static boolean hasRole(String role) {
		for (GrantedAuthority ga : SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities()) {
			if (role.equals(ga.getAuthority()))
				return true;
		}

		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		
		UserData userData = UserData.findUserData(username);
		
		if (userData == null)
			throw new UsernameNotFoundException("user not found");

		return buildUserFromUserEntity(userData);
	}

	private User buildUserFromUserEntity(UserData userData) {
		Date currentDate = new Date();

		String email = userData.getEmail();
		String password = new String(userData.getPassword());

		boolean enabled = !userData.isLockUser();
		boolean accountNonLocked = !userData.isLockUser();

		boolean accountNonExpired = false;
		boolean credentialsNonExpired = false;
		if (userData.getExpired().after(currentDate)) {
			accountNonExpired = true;
			credentialsNonExpired = true;
		}

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		User user = new User(userData.getUserName(), password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);

		return user;
	}	
}