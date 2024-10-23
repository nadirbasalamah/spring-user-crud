package id.anggra.belajarspringrestapi.controller;

import id.anggra.belajarspringrestapi.model.User;
import id.anggra.belajarspringrestapi.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> GetAllUser() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
