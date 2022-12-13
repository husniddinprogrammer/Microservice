package com.microservice.admin.controller;


import com.microservice.admin.security.JwtUtil;
import com.microservice.admin.security.Token;
import com.microservice.admin.security.UserMaxsus;
import com.microservice.admin.security.UserProvider;
import com.microservice.admin.service.UserService;
import com.microservice.admin.service.dto.UserDTO;
import com.microservice.admin.service.vm.UserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/account")
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserMaxsus userMaxsus)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userMaxsus.getUsername(), userMaxsus.getPassword()));
        } catch (DisabledException e) {
            logger.error(e.getMessage());
            throw new BadCredentialsException(e.getMessage());
        } catch (BadCredentialsException e) {
            logger.error(e.getMessage());
            throw new BadCredentialsException("INVALID_CREDENTIALS");
        } 
         UserDetails userDetails = userProvider.loadUserByUsername(userMaxsus.getUsername());
         String token = jwtTokenUtil.generateToken(userDetails, userMaxsus.isRememberMe());
        return ResponseEntity.ok(new Token(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserVM userVM) throws Exception{
        userService.register(userVM);
        return ResponseEntity.accepted().build();
    }
    @PutMapping(value = "/edit")
    public ResponseEntity<?> editAccount(@RequestBody UserVM userVM) throws Exception {
        userService.update(userVM);
        return null;
    }
    @GetMapping()
    public ResponseEntity<UserDTO> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}













