package id.anggra.belajarspringrestapi.controller;

import id.anggra.belajarspringrestapi.model.*;
import id.anggra.belajarspringrestapi.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<Response<User>> register(@Valid @RequestBody UserRequest request) {
        User user = service.register(request);
        Response<User> response = new Response<>("user created", user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<String>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse login = service.login(request);
        Response<String> response = new Response<>("login success", login.getToken());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
