package com.app.control.api.configuration.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.PasswordInvalidException;
import com.app.control.api.models.User;
import com.app.control.api.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiUserDetailService implements UserDetailsService {

	private final UserService userService;
	private final PasswordEncoder encoder;

	public UserDetails authentication(User user) {
		UserDetails userDetail = loadUserByUsername(user.getEmail());
		boolean isLoginValid = encoder.matches(user.getPassword(), userDetail.getPassword());

		if (isLoginValid) {
			return userDetail;
		}

		throw new PasswordInvalidException();
	}

	@Override
	public UserDetails loadUserByUsername(String emial) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.findByEmail(emial);
		return UserCustom.builder().username(user.getEmail()).password(user.getPassword()).roles("ADMIN").build();
	}

}
