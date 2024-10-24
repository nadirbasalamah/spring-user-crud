package id.anggra.belajarspringrestapi.controller;

import id.anggra.belajarspringrestapi.model.Response;
import id.anggra.belajarspringrestapi.model.User;
import id.anggra.belajarspringrestapi.model.UserRequest;
import id.anggra.belajarspringrestapi.service.user.UserService;
import id.anggra.belajarspringrestapi.util.Util;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Response<List<User>>> getAllUser() {
        List<User> users = userService.getAllUsers();
        Response<List<User>> response = new Response<>(
                "all users",
                users
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<User>> getById(@PathVariable("id") Long id) {
        Optional<User> userData = userService.getUserById(id);

        if (userData.isEmpty()) {
            return new ResponseEntity<>(new Response<>("user not found",null), HttpStatus.NOT_FOUND);
        }

        Response<User> response = new Response<>("user found", userData.get());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<User>> create(@Valid @RequestBody UserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String validationErrors = Util.getValidationErrors(bindingResult);

            return new ResponseEntity<>(new Response<>(validationErrors,null), HttpStatus.BAD_REQUEST);
        }

        User user = userService.createUser(request);
        Response<User> response = new Response<>("user created", user);

        if (user == null) {
            response.setMessage("create user failed");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<User>> update(@PathVariable("id") Long id, @Valid @RequestBody UserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String validationErrors = Util.getValidationErrors(bindingResult);

            return new ResponseEntity<>(new Response<>(validationErrors,null), HttpStatus.BAD_REQUEST);
        }

        User user = userService.updateUser(request, id);

        if (user == null) {
            return new ResponseEntity<>(new Response<>("update user failed",null), HttpStatus.BAD_REQUEST);
        }

        Response<User> response = new Response<>("user updated", user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("id") Long id) {
        boolean isDeleted = userService.deleteUser(id);

        if (!isDeleted) {
            return new ResponseEntity<>(new Response<>("delete user failed",null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Response<>("user deleted",null),HttpStatus.OK);
    }
}
