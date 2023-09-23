package com.pavi.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavi.ecom.Exception.UserException;
import com.pavi.ecom.config.JWTProvider;
import com.pavi.ecom.dto.UsersDTO;
import com.pavi.ecom.model.AuthResponse;
import com.pavi.ecom.model.Cart;
import com.pavi.ecom.model.Users;
import com.pavi.ecom.model.loginRequest;
import com.pavi.ecom.repository.UsersRepository;
import com.pavi.ecom.service.CustomUserServiceImplementation;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private JWTProvider jwtProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserServiceImplementation customUserServiceImplementation;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createuserHandler(@RequestBody Users user) throws UserException {
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lasName = user.getLastName();

		Users isUserExist = usersRepository.findByEmail(email);
		if (isUserExist != null) {
			log.info("Email does not exists");
			throw new UserException("Email is already used" + email);
		}

		Users createuser = new Users();
		createuser.setEmail(email);
		createuser.setFirstName(firstName);
		createuser.setLastName(lasName);
		createuser.setPassword(passwordEncoder.encode(password));
		Cart cart=new Cart();
		//cart.setUser(createuser);
		createuser.setCart(cart);
		

		Users saveduser = usersRepository.save(createuser);
		log.info("User created" + saveduser.getEmail());
		Authentication authentication = new UsernamePasswordAuthenticationToken(saveduser.getEmail(),
				saveduser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);
		log.info("Token generated");
		AuthResponse authResponse = new AuthResponse(token, "Signup Successful");
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginRequest(@RequestBody loginRequest loginReq) throws UserException {
		String email = loginReq.getEmail();
		String password = loginReq.getPassword();

		Authentication authentication = authenticate(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);
		log.info("Token generated");
		AuthResponse authResponse = new AuthResponse(token, "Signin Successful");
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/email/{token}")
	private String getEmail(@PathVariable("token") String token) {
		return jwtProvider.getEmailFromToken(token);
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(email);
		if (userDetails == null) {
			throw new BadCredentialsException("Invalid user");
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid user");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
	
	@GetMapping("/{id}")
	private Users getuserById(@PathVariable("id") Long id) {
		Users u =  usersRepository.findById(id).get();
		return u;
	}
}