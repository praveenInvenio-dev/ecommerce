package com.pavi.ecom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pavi.ecom.model.Users;
import com.pavi.ecom.repository.UsersRepository;

@Service
public class CustomUserServiceImplementation implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with the email id: " + username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();

		return new User(user.getEmail(), user.getPassword(), authorities);
	}

}
