package id.anggra.belajarspringrestapi.service.auth;

import id.anggra.belajarspringrestapi.model.LoginRequest;
import id.anggra.belajarspringrestapi.model.LoginResponse;
import id.anggra.belajarspringrestapi.model.User;
import id.anggra.belajarspringrestapi.model.UserRequest;

public interface AuthService {
    User register(UserRequest request);
    LoginResponse login(LoginRequest request);
}
